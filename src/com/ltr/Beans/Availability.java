package com.ltr.Beans;

public class Availability 
{
	
	private boolean inStock;
    private Integer quantity;
    
    
	public boolean isInStock()
	{
		return inStock;
	}
	public void setInStock(boolean inStock) {
		this.inStock = inStock;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "Availability [inStock=" + inStock + ", quantity=" + quantity + "]";
	}
	
	    
    
    

}
