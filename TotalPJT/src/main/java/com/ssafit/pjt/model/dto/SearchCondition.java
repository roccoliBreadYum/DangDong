package com.ssafit.pjt.model.dto;

public class SearchCondition {
	private String category = "none";
	private String key = "none";
	private String word;
	private String orderBy = "none";
	private String orderByDir;
	
	public SearchCondition() {
	}
	
	public SearchCondition(String category, String key, String word, String orderBy, String orderByDir) {
		this.category = category;
		this.key = key;
		this.word = word;
		this.orderBy = orderBy;
		this.orderByDir = orderByDir;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getOrderByDir() {
		return orderByDir;
	}

	public void setOrderByDir(String orderByDir) {
		this.orderByDir = orderByDir;
	}
	

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "SearchCondition [category=" + category + ", key=" + key + ", word=" + word + ", orderBy=" + orderBy
				+ ", orderByDir=" + orderByDir + "]";
	}

}
