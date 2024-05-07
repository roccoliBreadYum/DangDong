package com.ssafit.pjt.util;

public class StoreSearchCondition {
	private int category = -1;
	private String key = null;
	private String word;
	private String orderBy = null;
	private String orderByDir;
	
	public StoreSearchCondition() {
	}
	
	public StoreSearchCondition(int category, String key, String word, String orderBy, String orderByDir) {
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
	

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "SearchCondition [category=" + category + ", key=" + key + ", word=" + word + ", orderBy=" + orderBy
				+ ", orderByDir=" + orderByDir + "]";
	}

}
