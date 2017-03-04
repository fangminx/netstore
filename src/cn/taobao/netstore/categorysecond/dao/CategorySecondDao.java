package cn.taobao.netstore.categorysecond.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.taobao.netstore.categorysecond.bean.Categorysecond;
import cn.taobao.netstore.utils.PageHibernateCallback;

/**
 * 二级分类DAO层
 * 
 * @author fangmin
 * 
 */
public class CategorySecondDao extends HibernateDaoSupport {

	// 统计二级分类个数
	public int findCount() {
		String hql = "select count(*) from Categorysecond";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	// 分页查询
	public List<Categorysecond> findByPage(int begin, int limit) {
		String hql = "from Categorysecond order by ccid desc";// desc 倒序排序
		List<Categorysecond> list = this.getHibernateTemplate().execute(
				new PageHibernateCallback<Categorysecond>(hql, null, begin,
						limit));
		return list;
	}
	
	//保存二级分类
	public void save(Categorysecond categorysecond) {
		this.getHibernateTemplate().save(categorysecond);
	}
	
	//删除二级分类
	public void delete(Categorysecond categorysecond) {
		this.getHibernateTemplate().delete(categorysecond);
	}
	
	//根据二级分类id查询二级分类
	public Categorysecond findByCCid(Integer ccid) {
		return this.getHibernateTemplate().get(Categorysecond.class, ccid);
	}
	
	//修改二级分类
	public void update(Categorysecond categorysecond) {
		this.getHibernateTemplate().update(categorysecond);
	}
	
	//查询所有二级分类
	public List<Categorysecond> findAll() {
		String hql = "from Categorysecond";
		return this.getHibernateTemplate().find(hql);
	}

}
