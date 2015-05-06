package VO;

import java.io.Serializable;

public class OrderVO implements Serializable{
	private long order_id;
	private BuyerVO buyer_id;
	private GameVO game_id;
	private int quantity;
	private String orderDT;
	private String shippingDT;
	private int order_status;
	private double order_total_cost;
	private String order_shipping_address;
	
	public OrderVO() {
		// TODO Auto-generated constructor stub
	}
	
	public OrderVO(long order_id){
		this.order_id = order_id;
	}
	
	public OrderVO(BuyerVO buyer_id, GameVO game_id, int quantity, String orderDT, String shippingDT, 
				int order_status, double order_total_cost, String order_shipping_address){
		this.buyer_id = buyer_id;
		this.game_id = game_id;
		this.quantity = quantity;
		this.orderDT = orderDT;
		this.shippingDT = shippingDT;
		this.order_status = order_status;
		this.order_total_cost = order_total_cost;
		this.order_shipping_address = order_shipping_address;
	}

	public long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(long order_id) {
		this.order_id = order_id;
	}

	public BuyerVO getBuyer_id() {
		return buyer_id;
	}

	public void setBuyer_id(BuyerVO buyer_id) {
		this.buyer_id = buyer_id;
	}

	public GameVO getGame_id() {
		return game_id;
	}

	public void setGame_id(GameVO game_id) {
		this.game_id = game_id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getOrderDT() {
		return orderDT;
	}

	public void setOrderDT(String orderDT) {
		this.orderDT = orderDT;
	}

	public String getShippingDT() {
		return shippingDT;
	}

	public void setShippingDT(String shippingDT) {
		this.shippingDT = shippingDT;
	}

	public int getOrder_status() {
		return order_status;
	}

	public void setOrder_status(int order_status) {
		this.order_status = order_status;
	}

	public double getOrder_total_cost() {
		return order_total_cost;
	}

	public void setOrder_total_cost(double order_total_cost) {
		this.order_total_cost = order_total_cost;
	}

	public String getOrder_shipping_address() {
		return order_shipping_address;
	}

	public void setOrder_shipping_address(String order_shipping_address) {
		this.order_shipping_address = order_shipping_address;
	}
};
