package VO;

public class LinkBuyerVO {
	private long link_id;
	private long dispatchDT;
	private long activationDT;
	private String link;
	private BuyerVO buyer_id;
	private int status;
	
	public LinkBuyerVO() {}

	public LinkBuyerVO(long dispatchDT, long activationDT, String link, BuyerVO buyer_id, int status){
		this.dispatchDT = dispatchDT;
		this.activationDT = activationDT;
		this.link = link;
		this.buyer_id = buyer_id;
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

	public BuyerVO getBuyer_id() {
		return buyer_id;
	}

	public void setBuyer_id(BuyerVO buyer_id) {
		this.buyer_id = buyer_id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
};
