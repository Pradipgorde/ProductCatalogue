package com.ltr.Beans;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Product 
{
	private Integer id;
    private String name;
    private String description;
    private double price;
    private Map<Category,Product> categories;
    private HashMap<Color, String> color;
    private HashMap<Size,String>size;
    
    private Map<Color,Availability> availability;
    private List<Rating> ratings;
    private String category;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Map<Category, Product> getCategories() {
		return categories;
	}
	public void setCategories(Map<Category, Product> categories) {
		this.categories = categories;
	}
	public HashMap<Color, String> getColor() {
		return color;
	}
	public void setColor(HashMap<Color, String> color) {
		this.color = color;
	}
	public HashMap<Size, String> getSize() {
		return size;
	}
	public void setSize(HashMap<Size, String> size) {
		this.size = size;
	}
	public Map<Color,Availability> getAvailability() {
		return availability;
	}
	public void setAvailability(Map<Color,Availability> availability) {
		this.availability = availability;
	}
	public List<Rating> getRatings() {
		return ratings;
	}
	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price + ", color="
				+ color + ", size=" + size + ", availability=" + availability + ", ratings=" + ratings + ", category="
				+ category + "]";
	}
		
    
    

}
