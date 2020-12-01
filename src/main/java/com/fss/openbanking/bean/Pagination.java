package com.fss.openbanking.bean;

public class Pagination {
	
	private String totalCount;
	private Self self;
	
	public String getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}
	public Self getSelf() {
		return self;
	}
	public void setSelf(Self self) {
		this.self = self;
	}

}
