package cn.java.interceptor;

import java.util.Map;

import cn.java.controller.BaseAction;
import cn.java.model.Emp;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class LoginInterceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation ai) throws Exception {
		String result = null;
		//获取到Action的容器
		ActionContext context = ai.getInvocationContext();
		//获取到session
		Map<String, Object> session = context.getSession();
		//在session中获取用户
		Emp emp = (Emp) session.get("user");
		if(emp != null){
			//如果存在，继续执行
			result = ai.invoke();
		}else{
			//返回到登录页
			result = BaseAction.LOGIN;
		}
		return result;
	}

}
