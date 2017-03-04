package cn.taobao.netstore.cart.bean;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Cart implements Serializable {
	//key商品id，value购物项
	private Map<Integer, CartItem> map = new HashMap<Integer, CartItem>();
	
	public Collection<CartItem> getCartItems(){
		return map.values();
	}
	
	//购物总计
	private double total;

	public double getTotal() {
		return total;
	}
	
	//将购物项添加到购物车
	public void addCart(CartItem cartItem){
		Integer pid = cartItem.getProduct().getPid();
		if(map.containsKey(pid)){
			//存在则计数
			CartItem cartItem2 = map.get(pid);
			cartItem2.setCount(cartItem2.getCount()+cartItem.getCount());
		}else{
			map.put(pid, cartItem);
		}
		
		//设置总计
		total += cartItem.getSubtotal();
	}
	
	//购物车中移除购物项
	public void removeCart(Integer pid){
		CartItem cartItem = map.remove(pid);
		total -= cartItem.getSubtotal();
	}
	
	//清空购物车
	public void clearCart(){
		map.clear();
		total = 0;
	}
}
