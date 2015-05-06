package VO;

import java.io.Serializable;

public class GameVO implements Serializable{
	private long game_id;
	private SellerVO seller_id;
	private String game_poster_name;
	private GameCategoryVO cat_id;
	private GameSubCategoryVO scat_id;
	private String game_console;
	private String game_name;
	private double game_price;
	private double game_shipping_charges;
	private int game_stock;
	private String game_youtube_url;
	private String game_description;
	private int game_ratings;
	private long game_addedDT;
	
	public GameVO() {}

	public GameVO(long game_id) {
		this.game_id = game_id;
	}

	public GameVO(SellerVO seller_id, String game_poster_name, GameCategoryVO cat_id, GameSubCategoryVO scat_id, String game_console, 
				  String game_name, double game_price, double game_shipping_charges, int game_stock, String game_youtube_url, String game_description, int game_ratings,
				  long game_addedDT) {
		this.seller_id = seller_id;
		this.game_poster_name = game_poster_name;
		this.cat_id = cat_id;
		this.scat_id = scat_id;
		this.game_console = game_console;
		this.game_name = game_name;
		this.game_price = game_price;
		this.game_shipping_charges = game_shipping_charges;
		this.game_stock = game_stock;
		this.game_youtube_url = game_youtube_url;
		this.game_description = game_description;
		this.game_ratings = game_ratings;
		this.game_addedDT = game_addedDT;
	}

	public long getGame_id() {
		return game_id;
	}

	public void setGame_id(long game_id) {
		this.game_id = game_id;
	}

	public SellerVO getSeller_id() {
		return seller_id;
	}

	public void setSeller_id(SellerVO seller_id) {
		this.seller_id = seller_id;
	}

	public String getGame_poster_name() {
		return game_poster_name;
	}

	public void setGame_poster_name(String game_poster_name) {
		this.game_poster_name = game_poster_name;
	}

	public GameCategoryVO getCat_id() {
		return cat_id;
	}

	public void setCat_id(GameCategoryVO cat_id) {
		this.cat_id = cat_id;
	}

	public GameSubCategoryVO getScat_id() {
		return scat_id;
	}

	public void setScat_id(GameSubCategoryVO scat_id) {
		this.scat_id = scat_id;
	}

	public String getGame_console() {
		return game_console;
	}

	public void setGame_console(String game_console) {
		this.game_console = game_console;
	}

	public String getGame_name() {
		return game_name;
	}

	public void setGame_name(String game_name) {
		this.game_name = game_name;
	}

	public double getGame_price() {
		return game_price;
	}

	public void setGame_price(double game_price) {
		this.game_price = game_price;
	}

	public double getGame_shipping_charges() {
		return game_shipping_charges;
	}

	public void setGame_shipping_charges(double game_shipping_charges) {
		this.game_shipping_charges = game_shipping_charges;
	}

	public int getGame_stock() {
		return game_stock;
	}

	public void setGame_stock(int game_stock) {
		this.game_stock = game_stock;
	}

	public String getGame_youtube_url() {
		return game_youtube_url;
	}

	public void setGame_youtube_url(String game_youtube_url) {
		this.game_youtube_url = game_youtube_url;
	}

	public String getGame_description() {
		return game_description;
	}

	public void setGame_description(String game_description) {
		this.game_description = game_description;
	}

	public int getGame_ratings() {
		return game_ratings;
	}

	public void setGame_ratings(int game_ratings) {
		this.game_ratings = game_ratings;
	}

	public long getGame_addedDT() {
		return game_addedDT;
	}

	public void setGame_addedDT(long game_addedDT) {
		this.game_addedDT = game_addedDT;
	}
};
