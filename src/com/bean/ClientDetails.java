package com.bean;

public class ClientDetails {
	
	private String client_name;
	private String client_type;
	private String fundname;
	private int amount_invested;
	private int current_value;
	private String date_time;
	private String investment_type ;
	
	public String getInvestment_type() {
		return investment_type;
	}
	public void setInvestment_type(String investment_type) {
		this.investment_type = investment_type;
	}
	public String getClient_type() {
		return client_type;
	}
	public void setClient_type(String client_type) {
		this.client_type = client_type;
	}
	public String getFundname() {
		return fundname;
	}
	public int getCurrent_value() {
		return current_value;
	}
	public void setCurrent_value(int current_value) {
		this.current_value = current_value;
	}
	public void setFundname(String fundname) {
		this.fundname = fundname;
	}
	public int getAmount_invested() {
		return amount_invested;
	}
	public void setAmount_invested(int amount_invested) {
		this.amount_invested = amount_invested;
	}
	public String getDate_time() {
		return date_time;
	}
	public void setDate_time(String date_time) {
		this.date_time = date_time;
	}
	public String getClient_name() {
		return client_name;
	}
	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}
	
	
	
	
}
