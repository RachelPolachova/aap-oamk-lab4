package com.rachel.polachova.aaplab4.DataModel;

public class Stock {
	private String name;
	private String price;
	private String id;

	public Stock(String name, String id, String price) {
		this.name = name;
		this.price = price;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
