package com.cognizant.truyum.model;
import java.util.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class MenuItem {

	@NotBlank(message = "Title is required")
	@Size(min = 2,max = 65,message = "Title should have 2 to 65 characters")
	private String name;
	private long id;
	private String category;
	@NotNull(message = "Price is required")
	@NotNull(message="Price has to be a number")
	private Float price;
	private boolean active,freeDelivery;
	@NotNull(message = "Launch Date is required")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dateOfLaunch;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public boolean isFreeDelivery() {
		return freeDelivery;
	}
	public void setFreeDelivery(boolean freeDelivery) {
		this.freeDelivery = freeDelivery;
	}
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getDateOfLaunch() {
		return dateOfLaunch;
	}
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public void setDateOfLaunch(Date dateOfLaunch) {
		this.dateOfLaunch = dateOfLaunch;
	}
   public MenuItem(long id, String name, String category, Float price, boolean active, boolean freeDelivery,@DateTimeFormat(pattern = "dd/MM/yyyy") Date dateOfLaunch) 
   {
		this.id = id;
		this.name = name;
		this.category = category;
		this.price = price;
		this.active = active;
		this.freeDelivery = freeDelivery;
		this.dateOfLaunch = dateOfLaunch;
	}
   @Override
	public String toString() {
	   return "MenuItem [id=" + id + ", name=" + name + ", category=" + category + ", price=" + price + ", active="
			+ active + ", freeDelivery=" + freeDelivery + ", dateOfLaunch=" + dateOfLaunch + "]";
   }
   @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
   }
@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MenuItem other = (MenuItem) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
