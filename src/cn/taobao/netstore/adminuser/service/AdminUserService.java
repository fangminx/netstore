package cn.taobao.netstore.adminuser.service;

import org.springframework.transaction.annotation.Transactional;

import cn.taobao.netstore.adminuser.bean.AdminUser;
import cn.taobao.netstore.adminuser.dao.AdminUserDao;

@Transactional
public class AdminUserService {
	//注入dao
	private AdminUserDao adminUserDao;

	public void setAdminUserDao(AdminUserDao adminUserDao) {
		this.adminUserDao = adminUserDao;
	}
	
	public AdminUser login(AdminUser adminUser){
		return adminUserDao.login(adminUser);
	}
}
