package VO;

public class LinkSellerVO {
	private long link_id;
	private long dispatchDT;
	private long activationDT;
	private String link;
	private SellerVO seller_id;
	private int status;
	
	public LinkSellerVO() {}

	public LinkSellerVO(long dispatchDT, long activationDT, String link, SellerVO seller_id, int status){
		this.dispatchDT = dispatchDT;
		this.activationDT = activationDT;
		this.link = link;
		this.seller_id = seller_id;
		this.status = status;
	}

	public long getLink_id() {
		return link_id;
	}

	public void setLink_id(long link_id) {
		this.link_id = link_id;
	}

	public long getDispatchDT() {
		return dispatchDT;
	}

	public void setDispatchDT(long dispatchDT) {
		this.dispatchDT = dispatchDT;
	}

	public long getActivationDT() {
		return activationDT;
	}

	public void setActivationDT(long activationDT) {
		this.activationDT = activationDT;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public SellerVO getSeller_id() {
		return seller_id;
	}

	public void setSeller_id(SellerVO seller_id) {
		this.seller_id = seller_id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
};