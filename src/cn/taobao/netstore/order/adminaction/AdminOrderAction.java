package cn.taobao.netstore.order.adminaction;

import java.util.List;

import cn.taobao.netstore.order.bean.Order;
import cn.taobao.netstore.order.bean.OrderItem;
import cn.taobao.netstore.order.service.OrderService;
import cn.taobao.netstore.utils.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 后台订单管理的action
 * @author fangmin
 *
 */
public class AdminOrderAction extends ActionSupport implements ModelDriven<Order>{
	
	//模型驱动
	private Order order = new Order();
	public Order getModel() {
		return order;
	}
	
	//接收page
	private Integer page;
	public void setPage(Integer page) {
		this.page = page;
	}
	
	//注入orderService
	private OrderService orderService;
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	//查询所有订单
	public String findAll(){
		PageBean<Order> pageBean = orderService.findAll(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	
	//修改订单状态
	public String updateState(){
		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setState(3);
		orderService.update(currOrder);
		return "updateStateSuccess";
	}
	
	//根据订单id查订单项
	public String findOrderItem(){
		List<OrderItem> list = orderService.findOrderItem(order.getOid());
		ActionContext.getContext().getValueStack().set("list", list);
		return "findOrderItem";
	}
}
