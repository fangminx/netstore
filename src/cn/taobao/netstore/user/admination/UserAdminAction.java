package cn.taobao.netstore.user.admination;

import cn.taobao.netstore.user.bean.User;
import cn.taobao.netstore.user.service.UserService;
import cn.taobao.netstore.utils.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 后台用户管理的action
 * 
 * @author fangmin
 * 
 */
public class UserAdminAction extends ActionSupport implements ModelDriven<User> {
	// 模型驱动
	private User user = new User();

	public User getModel() {
		return user;
	}

	// 注入用户service
	private UserService userService;
	// 注入page
	private Integer page;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	// 后台查询所有用户带分页
	public String findAll() {
		PageBean<User> pageBean = userService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}

	// 后台删除用户
	public String delete() {
		User existUser = userService.findByUid(user.getUid());
		userService.delete(existUser);
		return "deleteSuccess";
	}
	//后台准备修改用户
	public String edit(){
		user = userService.findByUid(user.getUid());
		return "editSuccess";
	}
	
	//后台用户的修改
	public String update(){
		userService.update(user);
		return "updateSuccess";
	}
}
