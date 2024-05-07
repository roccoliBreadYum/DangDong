package com.ssafit.pjt.model.dto;

public class Category {
	private int excersiceId;
	private String category;
	
	public Category() {
	}

	public Category(int excersiceId, String category) {
		this.excersiceId = excersiceId;
		this.category = category;
	}

	public int getExcersiceId() {
		return excersiceId;
	}

	public void setExcersiceId(int excersiceId) {
		this.excersiceId = excersiceId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Category [excersiceId=" + excersiceId + ", category=" + category + "]";
	}
	
}
