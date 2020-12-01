package com.fss.openbanking.bean;

public class Meta {
	
	private String tracingId;
	private int count;
	private Pagination pagination;

	public String getTracingId() {
		return tracingId;
	}

	public void setTracingId(String tracingId) {
		this.tracingId = tracingId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

}
