package VO;

import java.io.Serializable;

public class CartVO implements Serializable{

	private long cart_id;
	private BuyerVO buyer_id;
	private GameVO game_id;
	private int game_quantity;
	private long cart_addedDT;

	public CartVO() {}

	public CartVO(BuyerVO buyer_id, GameVO game_id, int game_quantity, long cart_addedDT) {
		this.buyer_id = buyer_id;
		this.game_id = game_id;
		this.game_quantity = game_quantity;
		this.cart_addedDT = cart_addedDT;
	}
	
	public CartVO(long cart_id){
		this.cart_id = cart_id;
	}

	public long getCart_id() {
		return cart_id;
	}

	public void setCart_id(long cart_id) {
		this.cart_id = cart_id;
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

	public int getGame_quantity() {
		return game_quantity;
	}

	public void setGame_quantity(int game_quantity) {
		this.game_quantity = game_quantity;
	}

	public long getCart_addedDT() {
		return cart_addedDT;
	}

	public void setCart_addedDT(long cart_addedDT) {
		this.cart_addedDT = cart_addedDT;
	}
};
