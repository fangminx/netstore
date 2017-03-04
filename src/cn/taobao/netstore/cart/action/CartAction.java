package cn.taobao.netstore.cart.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import cn.taobao.netstore.cart.bean.Cart;
import cn.taobao.netstore.cart.bean.CartItem;
import cn.taobao.netstore.product.bean.Product;
import cn.taobao.netstore.product.service.ProductService;

public class CartAction extends ActionSupport{
	//接收pid
	private Integer pid;
	//接收数量count
	private Integer count;
	//注入商品service
	private ProductService productService;
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	//将购物项加入到购物车
	public String addCart(){
		CartItem cartItem = new CartItem();
		cartItem.setCount(count);
		Product product = productService.findByPid(pid);
		cartItem.setProduct(product);
		Cart cart = getCart();
		cart.addCart(cartItem);
		
		return "addCart";
	}
	
	//清空购物车
	public String clearCart(){
		Cart cart = getCart();
		cart.clearCart();
		return "clearCart";
	}
	
	//从购物车中移除购物项
	public String removeCart(){
		Cart cart = getCart();
		cart.removeCart(pid);
		return "removeCart";
	}
	
	
	//跳转到购物车
	public String myCart(){
		return "myCart";
	}
	
	/**
	 * 从session中获得购物车
	 * @return
	 */
	private Cart getCart() {
		Cart cart = (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if(cart == null){
			cart = new Cart();
			ServletActionContext.getRequest().getSession().setAttribute("cart", cart);
		}
		return cart;
	}
	
	
}
