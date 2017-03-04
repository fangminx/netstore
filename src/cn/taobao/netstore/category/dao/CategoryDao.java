package cn.taobao.netstore.category.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.taobao.netstore.category.bean.Category;
/**
 * 一级分类DAO层
 * @author fangmin
 *
 */
public class CategoryDao extends HibernateDaoSupport{
	//查询所有一级分类
	public List<Category> findAll() {
		String hql = "from Category";
		return this.getHibernateTemplate().find(hql);
	}
	
	//保存一级分类
	public void save(Category category) {
		this.getHibernateTemplate().save(category);
	}
	
	//根据一级分类id查询一级分类
	public Category findByCid(Integer cid) {
		return this.getHibernateTemplate().get(Category.class, cid);
	}
	
	//删除一级分类
	public void delete(Category category) {
		this.getHibernateTemplate().delete(category);
	}
	
	//修改一级分类
	public void update(Category category) {
		this.getHibernateTemplate().update(category);
	}

	

}
