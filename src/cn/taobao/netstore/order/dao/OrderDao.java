package cn.taobao.netstore.order.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.taobao.netstore.order.bean.Order;
import cn.taobao.netstore.order.bean.OrderItem;
import cn.taobao.netstore.utils.PageHibernateCallback;

//订单dao层
public class OrderDao extends HibernateDaoSupport {
	// 保存订单
	public void save(Order order) {
		this.getHibernateTemplate().save(order);
	}

	// 查询用户总订单数
	public int findCountByUid(Integer uid) {
		String hql = "select count(*) from Order o where o.user.uid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql, uid);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	// 查询用户在某页的订单集合
	public List<Order> findPageByUid(Integer uid, int begin, int limit) {
		// 查询所有订单的hql语句
		String hql = "from Order o where o.user.uid = ? order by o.ordertime desc";
		List<Order> list = this.getHibernateTemplate().execute(
				new PageHibernateCallback<Order>(hql, new Object[] { uid },
						begin, limit));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	// 根据订单id查询订单
	public Order findByOid(Integer oid) {
		return this.getHibernateTemplate().get(Order.class, oid);
	}

	// 更新订单
	public void update(Order currOrder) {
		this.getHibernateTemplate().update(currOrder);
	}

	// 统计订单总数
	public int findCount() {
		String hql = "select count(*) from Order";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	// 分页查询订单
	public List<Order> findByPage(int begin, int limit) {
		// 查所有订单的hql语句
		String hql = "from Order order by ordertime desc";
		List<Order> list = this.getHibernateTemplate().execute(
				new PageHibernateCallback<Order>(hql, null, begin, limit));
		return list;
	}
	
	//根据订单id查询订单项
	public List<OrderItem> findOrderItem(Integer oid) {
		String hql = "from OrderItem oi where oi.order.oid=?";
		List<OrderItem> list = this.getHibernateTemplate().find(hql,oid);
		if(list !=null && list.size()>0){
			return list;
		}
		return null;
	}

}
