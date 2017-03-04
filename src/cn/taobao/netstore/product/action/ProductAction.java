package cn.taobao.netstore.product.action;

import cn.taobao.netstore.category.service.CategoryService;
import cn.taobao.netstore.product.bean.Product;
import cn.taobao.netstore.product.service.ProductService;
import cn.taobao.netstore.utils.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ProductAction extends ActionSupport implements ModelDriven<Product> {
	//模型驱动
	private Product product = new Product();
	public Product getModel() {
		// TODO Auto-generated method stub
		return product;
	}
	
	//注入商品service
	private ProductService productService;
	//接收一级分类id
	private Integer cid;
	//接收二级分类id
	private Integer ccid;
	//注入一级分类service
	private CategoryService categoryService;
	//注入当前页
	 private int page;
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public Integer getCcid() {
		return ccid;
	}
	public void setCcid(Integer ccid) {
		this.ccid = ccid;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	public void setPage(int page) {
		this.page = page;
	}
	
	//根据商品id查询商品
	public String findByPid(){
		product = productService.findByPid(product.getPid());
		return "findByPid";
	}
	
	//根据一级分类id查询商品
	public String findByCid(){
		PageBean<Product> pageBean = productService.findByPageCid(cid, page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCid";
	}
	
	//根据二级分类id查询商品
	public String findByCsid(){
		PageBean<Product> pageBean = productService.findByPageCCid(ccid, page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCsid";
	}

}
