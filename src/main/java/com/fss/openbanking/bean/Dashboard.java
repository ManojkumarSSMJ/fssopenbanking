package com.fss.openbanking.bean;

import java.util.List;

public class Dashboard {
	
	private String label;
	private String color;
	private int value;
	private List<Integer> valueList;
	private String bankName;
	private int count;
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public List<Integer> getValueList() {
		return valueList;
	}
	public void setValueList(List<Integer> valueList) {
		this.valueList = valueList;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
