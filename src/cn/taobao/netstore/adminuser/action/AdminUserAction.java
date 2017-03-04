package cn.taobao.netstore.adminuser.action;

import org.apache.struts2.ServletActionContext;

import cn.taobao.netstore.adminuser.bean.AdminUser;
import cn.taobao.netstore.adminuser.service.AdminUserService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminUserAction extends ActionSupport implements
		ModelDriven<AdminUser> {
	// 模型驱动
	private AdminUser adminUser = new AdminUser();

	public AdminUser getModel() {
		return adminUser;
	}

	// 注入adminuserservice
	private AdminUserService adminUserService;

	public void setAdminUserService(AdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}

	// 后台登录
	public String login() {
		AdminUser exist = adminUserService.login(adminUser);
		if(exist==null){
			this.addActionError("用户名或密码错误!");
			return "loginFail";
		}else{
			ServletActionContext.getRequest().getSession().setAttribute("existAdminUser", exist);
			return "loginSuccess";
		}
	}
}
