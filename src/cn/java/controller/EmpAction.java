package cn.java.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.imageio.ImageIO;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;

import cn.java.model.Dep;
import cn.java.model.Emp;
import cn.java.model.Menu;
import cn.java.model.Role;
import cn.java.query.EmpQuery;
import cn.java.service.DepService;
import cn.java.service.EmpService;
import cn.java.utils.MD5Utils;
import cn.java.utils.Page;

import com.opensymphony.xwork2.ActionContext;

public class EmpAction extends BaseAction {
	

	private EmpQuery query = new EmpQuery();
	
	private Emp emp = new Emp();
	//用来接收roleIds
	private String roleIds;
	//用来接收验证码
	private String captcha;
	
	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public Emp getEmp() {
		return emp;
	}

	public void setEmp(Emp emp) {
		this.emp = emp;
	}

	public EmpQuery getQuery() {
		return query;
	}

	public void setQuery(EmpQuery query) {
		this.query = query;
	}

	private EmpService empService;
	
	private DepService depService;
	
	public DepService getDepService() {
		return depService;
	}

	public void setDepService(DepService depService) {
		this.depService = depService;
	}

	public EmpService getEmpService() {
		return empService;
	}

	public void setEmpService(EmpService empService) {
		this.empService = empService;
	}
	
	public String emp_list(){
		//查询所有部门
		ActionContext context = ActionContext.getContext();
		List<Dep> list = depService.list();
		context.put("dList", list);
		Integer pageNo = query.getPageNo();
		if(pageNo == null){
			query.setPageNo(1);
		}
		Page page = empService.queryObjByCondition(query, exclude);
		context.put("page", page);
		return SUCCESS;
	}
	/**
	 * 用户添加
	 * @return
	 */
	public String emp_input(){
		//查询所有部门
		ActionContext context = ActionContext.getContext();
		List<Dep> list = depService.list();
		context.put("dList", list);
		return SUCCESS;
	}
	
	/**
	 * 用户修改
	 * @return
	 */
	public String emp_update(){
		//查询所有部门
		ActionContext context = ActionContext.getContext();
		List<Dep> list = depService.list();
		context.put("dList", list);
		emp = empService.getObj(emp.getEmpId());
		return SUCCESS;
	}
	
	/**
	 * 用户删除
	 * @return
	 */
	public String emp_delete(){
		empService.delete(emp);
		return LIST;
	}
	
	/**
	 * 对用户名进行是否存在校验
	 */
	public void ajax_emp_validUname() throws IOException{
		String result = "yes";
		Emp username = empService.getEmpByUname(emp.getUsername());
		if(username != null){
			result = "no";
		}
		response.getWriter().write(result);
	}
	
	/**
	 * 用户添加
	 * @throws IOException
	 */
	public void ajax_emp_add() throws IOException{
		String password = emp.getPassword();
		 password = MD5Utils.md5(password);
		 emp.setPassword(password);
		empService.save(emp);
		response.getWriter().write("success");
	}
	
	/**
	 * 用户修改
	 */
	public void ajax_emp_update() throws IOException{
		//updateEmp自己定义，因为用户密码需要单独修改，密码会空
		empService.updateEmp(emp);
		response.getWriter().write("success");
	}
	
	/**
	 * 查出当前用户下的角色和所有角色的集合
	 */
	public String emp_listpop(){
		List<Role> roles = empService.getStateRoles(emp.getEmpId());
		ActionContext context = ActionContext.getContext();
		context.put("roles", roles);
		return SUCCESS;
	}
	
	/**
	 * 角色分配
	 * @throws IOException
	 */
	public void ajax_emp_grantrole() throws IOException{
		empService.updateEmpRole(emp.getEmpId(), roleIds);
		response.getWriter().write("success");
	}
	
	/**
	 * 登录跳转
	 * @return
	 */
	public String emp_toLogin(){
		
		return SUCCESS;
	}
	
	/**
	 * 登录
	 * @return
	 */
	public String emp_login(){
		ActionContext context = ActionContext.getContext();
		//获取正确的验证码
		String rightCap = (String) session.getAttribute("piccode");
		//不区分大小写的比较
		if(!StringUtils.equalsIgnoreCase(rightCap, captcha)){
			context.put("tip", "caperror");
			return LOGIN;
		}
		
		//获取到的password进行加密
		String newPass = MD5Utils.md5(emp.getPassword());
		Emp emp1 = empService.getEmpByUnameAndPWord(emp.getUsername(), newPass);
		if(emp1 == null){
			context.put("tip", "userpasserror");
			return LOGIN;
		}
		//获取到session对象
		Map<String, Object> session2 = context.getSession();
		//将用户放到session中
		session2.put("user", emp1);
		//获取用户下的角色
		Set<Role> roles = emp1.getRoles();
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		//遍历角色，获取到角色下的菜单
		for(Role role : roles){
			Set<Menu> menus = role.getMenus();
			createTreeDate(list,menus);
		}
		//将集合list转换为JSON对象
		JSONArray ja = new JSONArray().fromObject(list);
		context.getSession().put("zNodes", ja);
		return MAIN;
	}
	/**
	 * 退出
	 * @return
	 */
	public String emp_logOut(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session2 = context.getSession();
		session2.remove("user");
		return MAIN;
	}
	/**
	 * 组装数据，树形数据，采用递归
	 * @param menu 所有菜单
	 * @param list 
	 * @param roleMenus 角色已有的菜单
	 */
	public void createTreeDate(List<Map<String,Object>> list,Set<Menu> menus){
		if(menus != null){
			for(Menu menu : menus){
				Map<String,Object> map = new HashMap<String,Object>();
				Integer id = menu.getMenuId();
				Integer pId = menu.getParentMenuId();
				String name = menu.getName();
				map.put("id", id);
				map.put("pId", pId);
				map.put("name", name);
				map.put("url", menu.getUrl());
				map.put("target", "main");
				list.add(map);
			}
		}
	}

	
	
	/**
	 * 生成验证码的图片
	 * @throws Exception
	 */
	public void ajax_emp_getImage() throws Exception{
		 System.out.println("#######################生成数字和字母的验证码#######################");  
	        BufferedImage img = new BufferedImage(68, 22,  
	  
	        BufferedImage.TYPE_INT_RGB);  
	  
	        // 得到该图片的绘图对象    
	  
	        Graphics g = img.getGraphics();  
	  
	        Random r = new Random();  
	  
	        Color c = new Color(200, 150, 255);  
	  
	        g.setColor(c);  
	  
	        // 填充整个图片的颜色    
	  
	        g.fillRect(0, 0, 68, 22);  
	  
	        // 向图片中输出数字和字母    
	  
	        StringBuffer sb = new StringBuffer();  
	        
	  
	        char[] ch = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();  
	  
	        int index, len = ch.length;  
	  
	        for (int i = 0; i < 4; i++) {  
	  
	            index = r.nextInt(len);  
	  
	            g.setColor(new Color(r.nextInt(88), r.nextInt(188), r.nextInt  
	  
	            (255)));  
	  
	            g.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 22));  
	            // 输出的  字体和大小                      
	  
	            g.drawString("" + ch[index], (i * 15) + 3, 18);  
	            //写什么数字，在图片 的什么位置画    
	  
	            sb.append(ch[index]);  
	  
	        }  
	        //把验证码的值放入session中
	        request.getSession().setAttribute("piccode", sb.toString());  
	        //把验证码的图片写回浏览器
	        ImageIO.write(img, "JPG", response.getOutputStream());  
	}
	
	
}
