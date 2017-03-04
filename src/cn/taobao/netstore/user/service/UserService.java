package cn.taobao.netstore.user.service;

import java.util.List;

import cn.taobao.netstore.user.bean.User;
import cn.taobao.netstore.user.dao.UserDao;
import cn.taobao.netstore.utils.PageBean;

/**
 * 用户业务层
 * @author fangmin
 *
 */
public class UserService {
	//注入dao
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	//按用户名查询用户
	public User findByUsername(String username){
		return userDao.findByUsername(username);
	}
	
	//用户注册
	public void save(User user){
		user.setState(1);
		userDao.save(user);
	}
	
	//修改用户
	public void update(User existUser){
		userDao.update(existUser);
	}
	
	//用户登录
	public User login(User user){
		return userDao.login(user);
	}
	
	//带分页查所有用户
	public PageBean<User> findByPage(Integer page){
		PageBean<User> pageBean = new PageBean<User>();
		
		pageBean.setPage(page);
		int limit = 5;
		pageBean.setLimit(limit);
		
		int totalCount = 0;
		totalCount = userDao.findCount();
		pageBean.setTotalCount(totalCount);
		
		int totalPage = 0;
		if(totalCount % limit ==0){
			totalPage = totalCount/limit;
		}else{
			totalPage = totalCount/limit +1;
		}
		pageBean.setTotalPage(totalPage);
		int begin = (page - 1)*limit;
		List<User> list = userDao.finByPage(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
	
	public User findByUid(Integer uid) {
		return userDao.findByUid(uid);
	}
	
	public void delete(User existUser){
		userDao.delete(existUser);
	}
}
