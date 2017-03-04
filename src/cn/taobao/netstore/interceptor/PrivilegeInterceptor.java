package cn.taobao.netstore.interceptor;

import org.apache.struts2.ServletActionContext;

import cn.taobao.netstore.adminuser.bean.AdminUser;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class PrivilegeInterceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		// 登录放行,否则跳转到登录页面
		AdminUser adminUser = (AdminUser) ServletActionContext.getRequest()
				.getSession().getAttribute("existAdminUser");
		
		if(adminUser !=null){
			return actionInvocation.invoke();
		}else{
			ActionSupport support = (ActionSupport) actionInvocation.getAction();
			support.addActionError("您还没有登录!没有权限访问!");
			return ActionSupport.LOGIN;
		}
	}

}
