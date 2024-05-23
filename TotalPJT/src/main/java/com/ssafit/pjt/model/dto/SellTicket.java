package com.ssafit.pjt.model.dto;

// StoreControllor 에서 관리.
public class SellTicket {
	private int ticketId;
	private int storeId;
	private String name;
	private int category;
	private int expireDate;
	private int quantity;
	private int price;
	
	public SellTicket() {}

	public SellTicket(int ticketId, int storeId, String name, int category, int expireDate, int quantity, int price) {
		this.ticketId = ticketId;
		this.storeId = storeId;
		this.name = name;
		this.category = category;
		this.expireDate = expireDate;
		this.quantity = quantity;
		this.price = price;
	}

	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public int getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(int expireDate) {
		this.expireDate = expireDate;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "SellTicket [ticketId=" + ticketId + ", storeId=" + storeId + ", name=" + name + ", category=" + category
				+ ", expireDate=" + expireDate + ", quantity=" + quantity + ", price=" + price + "]";
	}
	
	

}
