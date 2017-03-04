package cn.taobao.netstore.categorysecond.bean;

import java.util.HashSet;
import java.util.Set;

import cn.taobao.netstore.category.bean.Category;
import cn.taobao.netstore.product.bean.Product;

/**
 * 二级分类实体类
 * @author fangmin
 *
 */
public class Categorysecond {
	private Integer ccid;
	private String ccname;
	//所属一级分类
	private Category category;
	//商品的集合
	private Set<Product> products = new HashSet<Product>();
	public String getCcname() {
		return ccname;
	}
	public void setCcname(String ccname) {
		this.ccname = ccname;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Set<Product> getProducts() {
		return products;
	}
	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	public Integer getCcid() {
		return ccid;
	}
	public void setCcid(Integer ccid) {
		this.ccid = ccid;
	}
	
	
}
