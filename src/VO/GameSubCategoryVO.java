package VO;

import java.io.Serializable;

public class GameSubCategoryVO implements Serializable{
	private GameCategoryVO cat_id;
	private long scat_id;
	private String scat_name;
	private String scat_description;
	
	public GameSubCategoryVO() {}
	
	public GameSubCategoryVO(GameCategoryVO cat_id, String scat_name, String scat_description) {
		this.cat_id = cat_id;
		this.scat_name = scat_name;
		this.scat_description = scat_description;
	}
	
	public GameSubCategoryVO(long scat_id){
		this.scat_id = scat_id;
	}
	
	public GameCategoryVO getCat_id() {
		return cat_id;
	}

	public void setCat_id(GameCategoryVO cat_id) {
		this.cat_id = cat_id;
	}

	public long getScat_id() {
		return scat_id;
	}

	public void setScat_id(long scat_id) {
		this.scat_id = scat_id;
	}

	public String getScat_name() {
		return scat_name;
	}

	public void setScat_name(String scat_name) {
		this.scat_name = scat_name;
	}

	public String getScat_description() {
		return scat_description;
	}

	public void setScat_description(String scat_description) {
		this.scat_description = scat_description;
	}
};