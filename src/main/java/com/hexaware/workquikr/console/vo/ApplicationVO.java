package com.hexaware.workquikr.console.vo;

import java.util.ArrayList;


public class ApplicationVO {

	private int id;
	private String context;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	 
	public ArrayList<SlaveVO> getSlvaeList() {
		return slvaeList;
	}
	public void setSlvaeList(ArrayList<SlaveVO> slvaeList) {
		this.slvaeList = slvaeList;
	}

	private ArrayList<SlaveVO> slvaeList;
 
	 
	@Override
	public String toString() {
	
		return "depVo : "+id+","+context+","+slvaeList+"\n";
		//return super.toString();
	}
}
