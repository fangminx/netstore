package cn.taobao.netstore.product.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.taobao.netstore.product.bean.Product;
import cn.taobao.netstore.utils.PageHibernateCallback;

/**
 * 商品DAO层
 * 
 * @author fangmin
 * 
 */
public class ProductDao extends HibernateDaoSupport {

	// 首页上查询热门商品
	public List<Product> findHot() {
		// 使用离线条件查询
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		criteria.add(Restrictions.eq("is_hot", 1));
		criteria.addOrder(Order.desc("pdate"));// 日期倒序
		List list = this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
		return list;
	}

	// 首页上最新商品查询
	public List<Product> findNew() {
		// 使用离线条件查询
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		criteria.addOrder(Order.desc("pdate"));// 日期倒序
		List list = this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
		return list;
	}

	// 根据商品id查询商品
	public Product findByPid(Integer pid) {
		return this.getHibernateTemplate().get(Product.class, pid);
	}

	// 根据一级分类id查询所有商品个数
	public int findCountCid(Integer cid) {
		String hql = "select count(*) from Product p where p.categorysecond.category.cid=?";
		List<Long> list = this.getHibernateTemplate().find(hql, cid);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	// 根据一级分类id带分页查询该页商品集合
	public List<Product> findByPageCid(Integer cid, int begin, int limit) {
		// 根据一级分类id查询所有商品的hql语句
		String hql = "select p from Product p join p.categorysecond cs join cs.category c where c.cid=?";
		// 分页
		List<Product> list = this.getHibernateTemplate().execute(
				new PageHibernateCallback<Product>(hql, new Object[] { cid },
						begin, limit));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	// 根据二级分类id查询商品个数
	public int findCountCCid(Integer ccid) {
		String hql = "select count(*) from Product p where p.categorysecond.ccid=?";
		List<Long> list = this.getHibernateTemplate().find(hql, ccid);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	// 根据二级分类id带分页查询该页商品集合
	public List<Product> findByPageCCid(Integer ccid, int begin, int limit) {
		// 根据一级分类id查询所有商品的hql语句
		String hql = "select p from Product p join p.categorysecond cs where cs.ccid=?";
		// 分页
		List<Product> list = this.getHibernateTemplate().execute(
				new PageHibernateCallback<Product>(hql, new Object[] { ccid },
						begin, limit));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	// 统计商品总个数
	public int findCount() {
		String hql = "select count(*) from Product";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	// 带分页查询所有在该页商品集合
	public List<Product> findByPage(int begin, int limit) {
		// 查询所有商品的hql语句
		String hql = "from Product order by pdate desc";
		List<Product> list = this.getHibernateTemplate().execute(
				new PageHibernateCallback<Product>(hql, null, begin, limit));
		if(list !=null && list.size()>0){
			return list;
		}
		return null;
	}
	
	//保存商品
	public void save(Product product) {
		this.getHibernateTemplate().save(product);
	}
	
	//删除商品
	public void delete(Product product) {
		this.getHibernateTemplate().delete(product);
	}
	
	// 更新商品
	public void update(Product product) {
		this.getHibernateTemplate().update(product);
	}

}
