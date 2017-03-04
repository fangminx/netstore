package cn.taobao.netstore.categorysecond.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.taobao.netstore.categorysecond.bean.Categorysecond;
import cn.taobao.netstore.categorysecond.dao.CategorySecondDao;
import cn.taobao.netstore.utils.PageBean;

/**
 * 二级分类业务层
 * @author fangmin
 *
 */
@Transactional
public class CategorySecondService {
	//注入DAO
	private CategorySecondDao categorySecondDao;

	public void setCategorySecondDao(CategorySecondDao categorySecondDao) {
		this.categorySecondDao = categorySecondDao;
	}
	
	//带有分页的查询
	public PageBean<Categorysecond> findByPage(Integer page){
		PageBean<Categorysecond> pageBean = new PageBean<Categorysecond>();
		//设置当前页，每页显示条数
		System.out.println(page==null);
		System.out.println("=========================================");
		pageBean.setPage(page);
		int limit = 10;
		pageBean.setLimit(limit);
		//设置总记录数
		int totalCount = categorySecondDao.findCount();
		pageBean.setTotalCount(totalCount);
		//设置总页数
		int totalPage = 0;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		//设置页面显示数据的集合
		int begin = (page-1)*limit;
		List<Categorysecond> list = categorySecondDao.findByPage(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
	
	//保存二级分类
	public void save(Categorysecond categorysecond){
		categorySecondDao.save(categorysecond);
	}
	//删除二级分类
	public void delete(Categorysecond categorysecond){
		categorySecondDao.delete(categorysecond);
	}
	//根据二级分类的id查询二级分类
	public Categorysecond findByCCid(Integer ccid){
		return categorySecondDao.findByCCid(ccid);
	}
	//修改二级分类
	public void update(Categorysecond categorysecond){
		categorySecondDao.update(categorysecond);
	}
	//不带分页地查询所有二级分类
	public List<Categorysecond>findAll(){
		return categorySecondDao.findAll();
	}
}
