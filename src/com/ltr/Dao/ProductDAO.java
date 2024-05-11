package com.ltr.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.ltr.Beans.Availability;
import com.ltr.Beans.Category;
import com.ltr.Beans.Clothes;
import com.ltr.Beans.Color;
import com.ltr.Beans.CustomerBean;
import com.ltr.Beans.Mobiles;
import com.ltr.Beans.Product;
import com.ltr.Beans.Rating;
import com.ltr.Beans.Size;

public class ProductDAO 
{
	
	
	
	public ArrayList<Product> getProduct()  
	{
		ArrayList<Product> list=null;
		HashMap <Color,String>  color=null;
		HashMap <Size,String>  size=null;
		ArrayList<Rating> ratingList =null;
		Map<Category,Product> categoryList =null;
		categoryList = new HashMap<Category, Product>();
		Map<Color,Availability> stocklist = null;
		Category c1 = null;
		list = new ArrayList<Product>();
		try(Connection con= ConnectionDAO.getDBConnection();) 
		{  
			
			 PreparedStatement ps = con.prepareStatement("select * from ProductData");
			 
			 ResultSet rs = ps.executeQuery();
				
			 while(rs.next())
			 { 
				Product pb = new Product(); 
				 System.out.println(" in while");
				 
				 
				 pb.setId(rs.getInt(1)); pb.setName(rs.getString(2)); 
				pb.setDescription(rs.getString(3)); pb.setPrice(rs.getDouble(4)); 
				pb.setCategory(rs.getString(5));
				
			
				PreparedStatement ps1 = con.prepareStatement("select psize from productSize where pid=?");
				
				ps1.setInt(1,pb.getId());
				ResultSet rs1 = ps1.executeQuery();
		          size = new HashMap<Size, String>();		 
				while(rs1.next())
				{
					Size ab = new Size();
				           	
					
					
					 ab.setSize("Size");
					String ob2 = rs1.getString(1);
					size.put(ab, ob2);
					
					
					
					
				}//size while
				pb.setSize(size);
             PreparedStatement ps2 = con.prepareStatement("select color,stock from productStock where pid=?");
				
				ps2.setInt(1,pb.getId());
				ResultSet rs2 = ps2.executeQuery();
				 color = new HashMap<Color, String>();
				 stocklist = new HashMap<Color, Availability>();
				while(rs2.next())
				{
					Color ab = new Color();
				           	
					// map = new HashMap<Attribute,String>();
					
					ab.setColor("color");
					String ob2 = rs2.getString(1);
					color.put(ab, ob2);
					
					 
					Availability av =new Availability();	 
						  
						  av.setQuantity(rs2.getInt(2));
						if(av.getQuantity()>0)
						{
							av.setInStock(true);
						}else av.setInStock(false);
						
						stocklist.put(ab, av);
						//pb.setAvailability(av); 
						 	
					
				}//color while
				
				   pb.setAvailability(stocklist);
					pb.setColor(color);
				 
			    
					 PreparedStatement ps5 = con.prepareStatement("select name,rating,comments from Rating where pid=?");
						
						ps5.setInt(1,pb.getId());
						ResultSet rs5 = ps5.executeQuery();
					ratingList=	new ArrayList<Rating>();
						while(rs5.next())
						{
							
							Rating ab = new Rating();
						           	
									
							ab.setUserId(rs5.getString(1));
							ab.setRating(rs5.getInt(2));
							ab.setComment(rs5.getString(3));
						
							ratingList.add(ab);
							
						}//rating while
					pb.setRatings(ratingList);
			
					
					if(Mobiles.type.equalsIgnoreCase(pb.getCategory()))
					{	
						c1 = new Mobiles();
					
					categoryList.put(c1,pb);
					}
					else {
						c1= new Clothes();
						categoryList.put(c1, pb);
					}
					pb.setCategories(categoryList);
			
					list.add(pb);		

						 
			 }//outer while
			 
			 
			 
			
			
			
		}catch (Exception e) {e.printStackTrace(); e.getMessage(); }
		
		
		
		
		
		
		
		return list;
	}
	
	
	
	
	
	
	
    public int addProduct(Product pb) 
    {
    	int k=0;
    	
    	try(Connection con = ConnectionDAO.getDBConnection();)
    	{
    		PreparedStatement ps = con.prepareStatement("insert into productdata values(?,?,?,?,?)");
    		
    		ps.setInt(1, pb.getId()); ps.setString(2, pb.getName()); ps.setString(3, pb.getDescription()); ps.setDouble(4, pb.getPrice());
    		ps.setString(5, pb.getCategory());
    		
    		k = ps.executeUpdate();
    		if(k>0)
    		{
    			PreparedStatement ps1 = con.prepareStatement("insert into productsize values(?,?)");

    			HashMap<Size,String> size = pb.getSize();
    		
    				Set<Size> set = size.keySet();
    				Iterator<Size> itr = set.iterator();
    				
    				while(itr.hasNext())
    				{
    					Size s1 = itr.next();
    			
    			ps1.setInt(1, pb.getId());   
    			  ps1.setString(2, size.get(s1));
    			  
    			  ps1.executeUpdate();
    			  
    			}
    				int[] kk = ps1.executeBatch();
    				System.out.println("kk"+Arrays.toString(kk));
    			
    		}
    		if(k>0)
    		{
    			System.out.println(" in stock");
    			int flag=0;
    			PreparedStatement ps2 = con.prepareStatement("insert into productstock values(?,?,?)");

    			HashMap<Color,String> color = pb.getColor();
    		
    				Set<Color> set = color.keySet();
    				Iterator<Color> itr = set.iterator();
    			
    				Map<Color, Availability> stocklist = pb.getAvailability();
    				Set<Color> set2 = stocklist.keySet();
    				Iterator<Color> itr2 = set2.iterator();
    				
    				System.out.println(" in stock 2");
    				while(itr.hasNext())
    				{
    					System.out.println(" in stock 3");
    					
    					Color s1 = itr.next();
    			
    			ps2.setInt(1, pb.getId());   
    			  ps2.setString(2, color.get(s1));
    			  if(flag==0) {
    				  
    			  if(itr2.hasNext()) 
    			  { flag=1; } }
    			  System.out.println(" in stock 4");
    				  Color s2 = itr2.next();
    				  Availability av1 = stocklist.get(s2);
    				  ps2.setInt(3, av1.getQuantity()); 
    			  	  
    			  ps2.executeUpdate();
    			  
    			}
    				
    			//	int[] kk = ps2.executeBatch();
    				//System.out.println("kk"+Arrays.toString(kk));
    			
    		}

    		
    		if(k>0) return k;
    		
    		
    		
    		
    	}catch(Exception e) { e.printStackTrace();}
    	
    	
    	
    	
    	return k;
    }

    public int addRating(CustomerBean cust)  
    {
    	int k=0;
    	Scanner s = new Scanner(System.in);
    	
    	System.out.print(" Enter Product Id:"); Integer pid = Integer.parseInt(s.nextLine());
    	System.out.print("Enter Product Rate out of 5:"); Integer rate = Integer.parseInt(s.nextLine());
    	System.out.println("Enter Comment: "); String comment = s.nextLine();
    		
    	Product pb = new Product();
    	Rating rating = new Rating();
    	
    	pb.setId(pid);  
    	rating.setUserId(cust.getUsername()); rating.setRating(rate); rating.setComment(comment);
    	
    	try(Connection con = ConnectionDAO.getDBConnection();)
    	{
        	PreparedStatement ps = con.prepareStatement("insert into rating values(?,?,?,?)");
        	ps.setInt(1, pb.getId());  ps.setString(2, rating.getUserId());
            ps.setInt(3, rating.getRating()); ps.setString(4, rating.getComment());
            
            k = ps.executeUpdate();
        	s.close();
        	if(k>0) return k;
        
    		
    	}catch(Exception e) {e.getMessage();}
    	
    	
    	return k;
    }
    
    
    
    public int updateStock(Product pb)
    {
    	int k=0;
    	
    	try(Connection con = ConnectionDAO.getDBConnection();)
    	{
    		PreparedStatement ps = con.prepareStatement("update productdata set pid=?,pdesc=?,pprice=?");
    		
    		ps.setInt(1, pb.getId()); ps.setString(2, pb.getDescription()); ps.setDouble(3, pb.getPrice());
    		
    		k = ps.executeUpdate();
     		
    		if(k>0)
    		{
    			PreparedStatement ps1 = con.prepareStatement("insert into productsize values(?,?)");

    			HashMap<Size,String> size = pb.getSize();
    		
    				Set<Size> set = size.keySet();
    				Iterator<Size> itr = set.iterator();
    				
    				while(itr.hasNext())
    				{
    					Size s1 = itr.next();
    			
    			ps1.setInt(1, pb.getId());   
    			  ps1.setString(2, size.get(s1));
    			  
    			  ps1.executeUpdate();
    			  
    			}
    				int[] kk = ps1.executeBatch();
    				System.out.println("kk"+Arrays.toString(kk));
    			
    		}
    		if(k>0)
    		{
    			System.out.println(" in stock");
    			int flag=0;
    			PreparedStatement ps2 = con.prepareStatement("insert into productstock values(?,?,?)");

    			HashMap<Color,String> color = pb.getColor();
    		
    				Set<Color> set = color.keySet();
    				Iterator<Color> itr = set.iterator();
    			
    				Map<Color, Availability> stocklist = pb.getAvailability();
    				Set<Color> set2 = stocklist.keySet();
    				Iterator<Color> itr2 = set2.iterator();
    				
    				System.out.println(" in stock 2");
    				while(itr.hasNext())
    				{
    					System.out.println(" in stock 3");
    					
    					Color s1 = itr.next();
    			
    			ps2.setInt(1, pb.getId());   
    			  ps2.setString(2, color.get(s1));
    			  if(flag==0) {
    				  
    			  if(itr2.hasNext()) 
    			  { flag=1; } }
    			  System.out.println(" in stock 4");
    				  Color s2 = itr2.next();
    				  Availability av1 = stocklist.get(s2);
    				  ps2.setInt(3, av1.getQuantity()); 
    			  	  
    			 k= ps2.executeUpdate();
    			  
    			}
    		}
    		if(k>0) return k;
    		
    		
    		
    		
    	}catch(Exception e) {e.printStackTrace();}
    	
    	
    	
    	return k;
    }
    
   
    public int deleteProduct(Product pb)
    {
    	int k=0;
    	
    	try(Connection con = ConnectionDAO.getDBConnection();)
    	{
    		PreparedStatement ps = con.prepareStatement("delete productdata where pid=?");
    		ps.setInt(1, pb.getId());
    		k = ps.executeUpdate();
    		if(k>0)
    		{
    			PreparedStatement ps1 = con.prepareStatement("delete productsize where pid=?");
    			ps1.setInt(1, pb.getId());
    			 ps1.executeUpdate();
    			
    			PreparedStatement ps2 = con.prepareStatement("delete productstock where pid=?");
    			ps2.setInt(1, pb.getId());
    			 ps2.executeUpdate();
    			
    			PreparedStatement ps3 = con.prepareStatement("delete rating where pid=?");
    			ps3.setInt(1, pb.getId());
    			 ps3.executeUpdate();
    			
    		}
    		
    		if(k>0) return k;
    		
    	}catch (Exception e) {e.printStackTrace(); System.out.println(e.getMessage());}
    	
    	
    	
    	
    	
    	return k;
    }
    
    
    

}

