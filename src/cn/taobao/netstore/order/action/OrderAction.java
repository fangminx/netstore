package cn.taobao.netstore.order.action;

import java.util.Date;

import org.apache.struts2.ServletActionContext;

import cn.taobao.netstore.cart.bean.Cart;
import cn.taobao.netstore.cart.bean.CartItem;
import cn.taobao.netstore.order.bean.Order;
import cn.taobao.netstore.order.bean.OrderItem;
import cn.taobao.netstore.order.service.OrderService;
import cn.taobao.netstore.user.bean.User;
import cn.taobao.netstore.utils.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 前台订单action类
 * @author fangmin
 *
 */
public class OrderAction extends ActionSupport implements ModelDriven<Order>{
	//模型驱动
	private Order order = new Order();
	public Order getModel() {
		// TODO Auto-generated method stub
		return order;
	}
	
	//接收page
	private Integer page;
	public void setPage(Integer page) {
		this.page = page;
	}
	
	//注入orderservice
	private OrderService orderService;
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	//生成订单
	public String saveOrder(){
		Cart cart = (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if(cart == null){
			this.addActionMessage("您还没有购物！");
			return "msg";
		}
		order.setTotal(cart.getTotal());
		order.setState(1);
		order.setOrdertime(new Date());
		User existUser = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		if(existUser == null){
			this.addActionError("您还没有登录!");
			return "msg";
		}
		
		order.setUser(existUser);
		//设置订单项集合
		for(CartItem cartItem : cart.getCartItems()){
			OrderItem orderItem = new OrderItem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setOrder(order);
			
			order.getOrderItems().add(orderItem);
		}
		
		orderService.save(order);
		cart.clearCart();
		
		return "saveOrder";
	}
	
	//根据用户id查询订单
	public String findByUid(){
		User existUser = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		
		Integer uid = existUser.getUid();
		PageBean<Order> pageBean = orderService.findByUid(uid, page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByUid";
	}
	
	//根据订单id查询订单
	public String findByOid(){
		order = orderService.findByOid(order.getOid());
		return "findByOid";
	}
	
	//更新订单状态
	public String updateState(){
		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setState(4);
		orderService.update(currOrder);
		return "updateStateSuccess";
	}
	
	//为订单付款
	public String payOrder(){
		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setAddr(order.getAddr());
		currOrder.setName(order.getName());
		currOrder.setPhone(order.getPhone());
		currOrder.setState(2);
		orderService.update(currOrder);
		this.addActionMessage("支付成功,等待发货!");
		return "msg";
	}
}
