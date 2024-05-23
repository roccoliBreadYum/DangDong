package com.ssafit.pjt.model.dto;

public class Category {
	private int excerciseId;
	private String category;
	
	public Category() {
	}

	public Category(int excersiceId, String category) {
		this.excerciseId = excersiceId;
		this.category = category;
	}

	public int getExcersiceId() {
		return excerciseId;
	}

	public void setExcersiceId(int excersiceId) {
		this.excerciseId = excersiceId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Category [excerciseId=" + excerciseId + ", category=" + category + "]";
	}
	
}
