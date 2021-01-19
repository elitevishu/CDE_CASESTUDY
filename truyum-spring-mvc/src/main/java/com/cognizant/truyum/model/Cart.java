package com.cognizant.truyum.model;
import java.util.ArrayList;
public class Cart {
	private double total;
	private int quantity;
	private ArrayList<MenuItem> menuItemList;
	public Cart() {
		// TODO Auto-generated constructor stub
	}
	public Cart(double total, ArrayList<MenuItem> menuItemList) 
	{
		this.total = total;
		this.menuItemList = menuItemList;
	}
	
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public ArrayList<MenuItem> getMenuItemList() { 
		return menuItemList;
	}
	public void setMenuItemList(ArrayList<MenuItem> menuItemList) {
		this.menuItemList = menuItemList;
	}
	
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "Cart [total=" + total + ", menuItemList=" + menuItemList + "]";
	}

}
