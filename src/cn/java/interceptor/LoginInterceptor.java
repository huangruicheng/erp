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
		//��ȡ��Action������
		ActionContext context = ai.getInvocationContext();
		//��ȡ��session
		Map<String, Object> session = context.getSession();
		//��session�л�ȡ�û�
		Emp emp = (Emp) session.get("user");
		if(emp != null){
			//������ڣ�����ִ��
			result = ai.invoke();
		}else{
			//���ص���¼ҳ
			result = BaseAction.LOGIN;
		}
		return result;
	}

}
