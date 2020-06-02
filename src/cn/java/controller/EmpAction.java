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
	//��������roleIds
	private String roleIds;
	//����������֤��
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
		//��ѯ���в���
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
	 * �û����
	 * @return
	 */
	public String emp_input(){
		//��ѯ���в���
		ActionContext context = ActionContext.getContext();
		List<Dep> list = depService.list();
		context.put("dList", list);
		return SUCCESS;
	}
	
	/**
	 * �û��޸�
	 * @return
	 */
	public String emp_update(){
		//��ѯ���в���
		ActionContext context = ActionContext.getContext();
		List<Dep> list = depService.list();
		context.put("dList", list);
		emp = empService.getObj(emp.getEmpId());
		return SUCCESS;
	}
	
	/**
	 * �û�ɾ��
	 * @return
	 */
	public String emp_delete(){
		empService.delete(emp);
		return LIST;
	}
	
	/**
	 * ���û��������Ƿ����У��
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
	 * �û����
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
	 * �û��޸�
	 */
	public void ajax_emp_update() throws IOException{
		//updateEmp�Լ����壬��Ϊ�û�������Ҫ�����޸ģ�������
		empService.updateEmp(emp);
		response.getWriter().write("success");
	}
	
	/**
	 * �����ǰ�û��µĽ�ɫ�����н�ɫ�ļ���
	 */
	public String emp_listpop(){
		List<Role> roles = empService.getStateRoles(emp.getEmpId());
		ActionContext context = ActionContext.getContext();
		context.put("roles", roles);
		return SUCCESS;
	}
	
	/**
	 * ��ɫ����
	 * @throws IOException
	 */
	public void ajax_emp_grantrole() throws IOException{
		empService.updateEmpRole(emp.getEmpId(), roleIds);
		response.getWriter().write("success");
	}
	
	/**
	 * ��¼��ת
	 * @return
	 */
	public String emp_toLogin(){
		
		return SUCCESS;
	}
	
	/**
	 * ��¼
	 * @return
	 */
	public String emp_login(){
		ActionContext context = ActionContext.getContext();
		//��ȡ��ȷ����֤��
		String rightCap = (String) session.getAttribute("piccode");
		//�����ִ�Сд�ıȽ�
		if(!StringUtils.equalsIgnoreCase(rightCap, captcha)){
			context.put("tip", "caperror");
			return LOGIN;
		}
		
		//��ȡ����password���м���
		String newPass = MD5Utils.md5(emp.getPassword());
		Emp emp1 = empService.getEmpByUnameAndPWord(emp.getUsername(), newPass);
		if(emp1 == null){
			context.put("tip", "userpasserror");
			return LOGIN;
		}
		//��ȡ��session����
		Map<String, Object> session2 = context.getSession();
		//���û��ŵ�session��
		session2.put("user", emp1);
		//��ȡ�û��µĽ�ɫ
		Set<Role> roles = emp1.getRoles();
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		//������ɫ����ȡ����ɫ�µĲ˵�
		for(Role role : roles){
			Set<Menu> menus = role.getMenus();
			createTreeDate(list,menus);
		}
		//������listת��ΪJSON����
		JSONArray ja = new JSONArray().fromObject(list);
		context.getSession().put("zNodes", ja);
		return MAIN;
	}
	/**
	 * �˳�
	 * @return
	 */
	public String emp_logOut(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session2 = context.getSession();
		session2.remove("user");
		return MAIN;
	}
	/**
	 * ��װ���ݣ��������ݣ����õݹ�
	 * @param menu ���в˵�
	 * @param list 
	 * @param roleMenus ��ɫ���еĲ˵�
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
	 * ������֤���ͼƬ
	 * @throws Exception
	 */
	public void ajax_emp_getImage() throws Exception{
		 System.out.println("#######################�������ֺ���ĸ����֤��#######################");  
	        BufferedImage img = new BufferedImage(68, 22,  
	  
	        BufferedImage.TYPE_INT_RGB);  
	  
	        // �õ���ͼƬ�Ļ�ͼ����    
	  
	        Graphics g = img.getGraphics();  
	  
	        Random r = new Random();  
	  
	        Color c = new Color(200, 150, 255);  
	  
	        g.setColor(c);  
	  
	        // �������ͼƬ����ɫ    
	  
	        g.fillRect(0, 0, 68, 22);  
	  
	        // ��ͼƬ��������ֺ���ĸ    
	  
	        StringBuffer sb = new StringBuffer();  
	        
	  
	        char[] ch = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();  
	  
	        int index, len = ch.length;  
	  
	        for (int i = 0; i < 4; i++) {  
	  
	            index = r.nextInt(len);  
	  
	            g.setColor(new Color(r.nextInt(88), r.nextInt(188), r.nextInt  
	  
	            (255)));  
	  
	            g.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 22));  
	            // �����  ����ʹ�С                      
	  
	            g.drawString("" + ch[index], (i * 15) + 3, 18);  
	            //дʲô���֣���ͼƬ ��ʲôλ�û�    
	  
	            sb.append(ch[index]);  
	  
	        }  
	        //����֤���ֵ����session��
	        request.getSession().setAttribute("piccode", sb.toString());  
	        //����֤���ͼƬд�������
	        ImageIO.write(img, "JPG", response.getOutputStream());  
	}
	
	
}
