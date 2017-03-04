package cn.taobao.netstore.order.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.taobao.netstore.order.bean.Order;
import cn.taobao.netstore.order.bean.OrderItem;
import cn.taobao.netstore.order.dao.OrderDao;
import cn.taobao.netstore.utils.PageBean;

//订单业务层
@Transactional
public class OrderService {
	//注入dao
	private OrderDao orderDao;

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}
	
	//保存订单
	public void save(Order order){
		orderDao.save(order);
	}
	
	//根据用户id查询订单，带分页
	public PageBean<Order> findByUid(Integer uid,Integer page) {
		PageBean<Order> pageBean = new PageBean<Order>();
		//设置当前页，每页显示数
		pageBean.setPage(page);
		int limit = 5;
		pageBean.setLimit(limit);
		//设置总记录数
		int totalCount = 0;
		totalCount = orderDao.findCountByUid(uid);
		pageBean.setTotalCount(totalCount);
		//设置总页数
		int totalPage = 0;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		//设置每页显示数据的集合
		int begin = (page - 1)*limit;
		List<Order> list = orderDao.findPageByUid(uid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
	
	//根据订单id查询订单
	 public Order findByOid(Integer oid){
		 return orderDao.findByOid(oid);
	 }
	 
	 //更新订单
	 public void update(Order currOrder){
		 orderDao.update(currOrder);
	 }
	 
	 //查询所有订单,分页
	 public PageBean<Order> findAll(Integer page){
		 PageBean<Order> pageBean = new PageBean<Order>();
		 //设置当前页，每页显示数
		 pageBean.setPage(page);
		 int limit = 10;
		 pageBean.setLimit(limit);
		 //设置总记录数
		 int totalCount = orderDao.findCount();
		 pageBean.setTotalCount(totalCount);
		 //设置总页数
		 int totalPage = 0;
		 if(totalCount % limit == 0){
			 totalPage = totalCount / limit;
		 }else{
			 totalPage = totalCount / limit + 1;
		 }
		 pageBean.setTotalPage(totalPage);
		 //设置每页显示数据集合
		 int begin = (page-1)*limit;
		 List<Order> list = orderDao.findByPage(begin,limit);
		 pageBean.setList(list);
		 return pageBean;
	 }
	 
	 //根据订单id查所有订单项
	 public List<OrderItem> findOrderItem(Integer oid){
		 return orderDao.findOrderItem(oid);
	 }
}
