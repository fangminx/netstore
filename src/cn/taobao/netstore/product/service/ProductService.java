package cn.taobao.netstore.product.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.taobao.netstore.product.bean.Product;
import cn.taobao.netstore.product.dao.ProductDao;
import cn.taobao.netstore.utils.PageBean;

/**
 * 商品业务层
 * @author fangmin
 *
 */
@Transactional
public class ProductService {
	//注入DAO
	private ProductDao productDao;

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	
	//首页上热门商品查询
	public List<Product> findHot(){
		return productDao.findHot();
	}
	
	//首页上最新商品查询
	public List<Product> findNew(){
		return productDao.findNew();
	}
	
	//根据商品id查询商品
	public Product findByPid(Integer pid){
		return productDao.findByPid(pid);
	}
	
	//根据一级分类的cid带有分页查询商品
	public PageBean<Product> findByPageCid(Integer cid,int page){
		PageBean<Product> pageBean = new PageBean<Product>();
		//设置当前页，每页显示数
		pageBean.setPage(page);
		int limit = 8;
		pageBean.setLimit(limit);
		//设置总记录数
		int totalCount = 0;
		totalCount = productDao.findCountCid(cid);
		pageBean.setTotalCount(totalCount);
		//设置总页数
		int totalPage = 0;
		if(totalCount % limit == 0) {
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		//每页显示的数据集合
		int begin = (page-1)*limit;
		List<Product> list = productDao.findByPageCid(cid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
	
	//根据二级分类的ccid带有分页查询商品
	public PageBean<Product> findByPageCCid(Integer ccid,int page){
		PageBean<Product> pageBean = new PageBean<Product>();
		//设置当前页数，总记录数
		pageBean.setPage(page);
		int limit = 8;
		pageBean.setLimit(limit);
		//设置总记录数
		int totalCount = 0;
		totalCount = productDao.findCountCCid(ccid);
		pageBean.setTotalCount(totalCount);
		//设置总页数
		int totalPage = 0;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		//每页显示数据的集合
		int begin = (page - 1)*limit;
		List<Product> list = productDao.findByPageCCid(ccid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
	
	//查询所有商品，带分页
	public PageBean<Product> findByPage(Integer page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		//设置当前页数，总记录数
		pageBean.setPage(page);
		int limit = 10;
		pageBean.setLimit(limit);
		//设置总记录数
		int totalCount = 0;
		totalCount = productDao.findCount();
		pageBean.setTotalCount(totalCount);
		//设置总页数
		int totalPage = 0;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		//每页显示数据的集合
		int begin = (page - 1)*limit;
		List<Product> list = productDao.findByPage(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
	
	//保存商品
	public void save(Product product){
		productDao.save(product);
	}
	
	
	//删除商品
	public void delete(Product product){
		productDao.delete(product);
	}
	
	//更新商品
	public void update(Product product){
		productDao.update(product);
	}
	
}
