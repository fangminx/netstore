package cn.taobao.netstore.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import cn.taobao.netstore.user.bean.User;
import cn.taobao.netstore.user.service.UserService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 用户模块Action类
 * 
 * @author fangmin
 * 
 */
public class UserAction extends ActionSupport implements ModelDriven<User> {

	// 模型驱动
	private User user = new User();

	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}

	// 接收验证码
	private String checkcode;

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}

	// 注入UserService
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	// 跳转到注册页面
	public String registPage() {
		return "registPage";
	}

	// 跳转到登录页面
	public String loginPage() {
		return "loginPage";
	}

	// AJAX异步校验用户名是否存在
	public String findByName() throws IOException {
		User existUser = userService.findByUsername(user.getUsername());
		// 获得response对象
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		// 输出
		if (existUser != null) {
			response.getWriter().println("<font color='red'>用户名已经存在</font>");
		} else {
			response.getWriter().println("<font color='green'>用户名可以使用</font>");
		}

		return NONE;
	}

	/**
	 * 用户注册
	 */
	public String regist() {
		// 从session中获取验证码的随机值
		String checkcode1 = (String) ServletActionContext.getRequest()
				.getSession().getAttribute("checkcode");
		if (!checkcode.equalsIgnoreCase(checkcode1)) {
			this.addActionError("验证码输入错误！");
			return "checkcodeFail";
		}
		userService.save(user);
		this.addActionMessage("注册成功!");
		return "msg";

	}

	/**
	 * 登录
	 */
	public String login() {
		User existUser = userService.login(user);
		if (existUser == null) {
			this.addActionError("登录失败！（用户名或密码错误）");
			return LOGIN;
		} else {
			// 将用户信息存到session中
			ServletActionContext.getRequest().getSession()
					.setAttribute("existUser", existUser);
			return "loginSuccess";
		}
	}

	/**
	 * 退出登录
	 */
	public String quit() {
		// 销毁session
		ServletActionContext.getRequest().getSession().invalidate();
		return "quit";
	}
}
