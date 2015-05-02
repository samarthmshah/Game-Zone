package VO;

import java.io.Serializable;

public class BuyerKeyVO implements Serializable{
	private long buyerKey_id;
	private String username;
	private String rand_key;
	
	public BuyerKeyVO() {}
	
	public BuyerKeyVO(String username, String rand_key){
		this.username = username;
		this.rand_key = rand_key;
	}

	public long getBuyerKey_id() {
		return buyerKey_id;
	}

	public void setBuyerKey_id(long buyerKey_id) {
		this.buyerKey_id = buyerKey_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRand_key() {
		return rand_key;
	}

	public void setRand_key(String rand_key) {
		this.rand_key = rand_key;
	}

};
