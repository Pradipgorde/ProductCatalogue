package com.ltr.Beans;

public class Rating 
{
	
	private String userName;
    private int rating;
    private String comment;
    
    
	public String getUserId() {
		return userName;
	}
	public void setUserId(String userId) {
		this.userName = userId;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	@Override
	public String toString() {
		return "Rating [userName=" + userName + ", rating=" + rating + ", comment=" + comment + "]";
	}
	
	
	    
    
    

}
