package cn.taobao.netstore.index.action;

import java.util.List;

import cn.taobao.netstore.category.bean.Category;
import cn.taobao.netstore.category.service.CategoryService;
import cn.taobao.netstore.product.bean.Product;
import cn.taobao.netstore.product.service.ProductService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 首页访问的Action
 * @author fangmin
 *
 */
public class IndexAction extends ActionSupport{
	//注入一级分类Service
	private CategoryService categoryService;

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	//注入商品Service
	private ProductService productService;

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	/**
	 * 执行首页访问
	 */
	public String execute(){
		//查询所有一级分类，放到Session里
		List<Category> cList = categoryService.findAll();
		ActionContext.getContext().getSession().put("cList", cList);
		//查询热门商品，放入值栈中
		List<Product> hList = productService.findHot();
		ActionContext.getContext().getValueStack().set("hList", hList);
		//查询最新商品，放入值栈
		List<Product> nList = productService.findNew();
		ActionContext.getContext().getValueStack().set("nList", nList);
		return "index";
	}
}
