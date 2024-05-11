package com.ltr.Client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.ltr.Beans.*;
import com.ltr.Dao.CustomerDAO;
import com.ltr.Dao.ProductDAO;

public class MainClass {

	public static void main(String[] args) 
	{
		Scanner s = new Scanner(System.in);
		
		System.out.println("********Welcome to Product Store******** ");
	
		while(true)
		{
			
			
			int flag = 0;
			System.out.println(
					"\n Choose Below Option.... \t \n1.Customer Login \t \n2.New Login \t \n3.Exit \t \n Enter Your Choice:-");

			switch (Integer.parseInt(s.nextLine())) 
			{
			
			case 1: {

				System.out.print(" Enter Your name");
				String name = s.nextLine();
				System.out.println(" Enter your Password");
				String pass = s.nextLine();
				CustomerBean cust = new CustomerBean();
				cust.setUsername(name);
				cust.setPassword(pass);
			
				Boolean login = new CustomerDAO().getLogin(cust);

				if (login) {
					System.out.println(" Login Sucess...");
					
					while (true) {
						System.out.println(
								" \n Enter Your Choice....\n \t 1.View All Product \n \t 2.Insert new Product  \n \t 3.give rating  ."
								+ " \n \t 4.Update product. \n 5.Delete product. \n 6.View by product Category.       \n  7.Exit. \n  \t Enter Choice:-");

							switch (Integer.parseInt(s.nextLine())) {

						case 1://  view all product
						{
							Product pb = new Product();
							
							ArrayList<Product> pblist = new ProductDAO().getProduct();
							
							
							System.out.println(pblist.size()); //System.out.println(pblist.toString());
							
							Iterator<Product> itr = pblist.iterator();
							while(itr.hasNext())
							{
								Product pb1 = itr.next();
						
								System.out.println(pb1);
							}
							

						}break; // inner case 1

						
						
						case 2: {
							
							Product pb = new Product();
							System.out.print(" Enter product id:"); Integer pid = Integer.parseInt(s.nextLine());
							System.out.print(" Enter Product name:"); String pname = s.nextLine();
							System.out.print(" Enter product Desc:"); String pdesc = s.nextLine();
							System.out.print(" Enter Product Price"); Double price = Double.parseDouble(s.nextLine());
						
							System.out.print(" Enter Color qty  "); Integer cqty = Integer.parseInt(s.nextLine());
							
							pb.setId(pid); pb.setName(pname); pb.setDescription(pdesc);
							pb.setPrice(price); 
						
							HashMap<Color,String> ablist = new HashMap<Color,String>();
							Map<Color,Availability> stocklist = new HashMap<Color, Availability>();
							for(int i=1;i<=cqty;i++)
							{
								Color ab =  new Color();
								
								System.out.print(" Enter "+i+" Color name"); String color = s.nextLine();
								
								
								System.out.print("Enter product qty :"); Integer pqty = Integer.parseInt(s.nextLine());;
								
								Availability av = new Availability();
								
								
								 ab.setColor("color");
								
								
								ablist.put(ab, color);
								av.setQuantity(pqty); if(av.getQuantity()>0) av.setInStock(true);
								stocklist.put(ab, av);
								
							}   pb.setColor(ablist); pb.setAvailability(stocklist);
							HashMap<Size,String> sizelist = new HashMap<Size,String>();
							System.out.print(" Enter Size qty  "); Integer sqty = Integer.parseInt(s.nextLine());
							for(int i=1;i<=sqty;i++)
							{
								Size ab =  new Size();
								
								System.out.print(" Enter "+i+" Size:"); String size = s.nextLine();
								
								ab.setSize("size");
								
								sizelist.put(ab, size);
								
							}pb.setSize(sizelist);
							
							System.out.print(" Enter Product Category"); 
							pb.setCategory(s.nextLine());
							
							
							int k = new ProductDAO().addProduct(pb);
							if(k>0) System.out.println(" Sucessfully inserted...");
							else System.err.println(" Not Inserted");
							
							
							
						}break; // case2 
						
						case 3:{
							
							
							int k= new ProductDAO().addRating(cust);
							if(k>0)
								System.out.println(" Rating Sucessfully added.");
							else System.err.println(" rating Not adedd");
							
						}break;// case 3
						
						case 4: {
							
							Product pb = new Product();
							System.out.print(" Enter product id:"); Integer pid = Integer.parseInt(s.nextLine());
						
							System.out.print(" Enter product Desc:"); String pdesc = s.nextLine();
							System.out.print(" Enter Product Price"); Double price = Double.parseDouble(s.nextLine());
						
							System.out.print(" Enter Color qty  "); Integer cqty = Integer.parseInt(s.nextLine());
							
							pb.setId(pid); pb.setDescription(pdesc);
							pb.setPrice(price); 
						
							HashMap<Color,String> ablist = new HashMap<Color,String>();
							Map<Color,Availability> stocklist = new HashMap<Color, Availability>();
							for(int i=1;i<=cqty;i++)
							{
								Color ab =  new Color();
								
								System.out.print(" Enter "+i+" Color name"); String color = s.nextLine();
								
								
								System.out.print("Enter product qty :"); Integer pqty = Integer.parseInt(s.nextLine());;
								
								Availability av = new Availability();
								
								
								 ab.setColor("color");
								
								
								ablist.put(ab, color);
								av.setQuantity(pqty); if(av.getQuantity()>0) av.setInStock(true);
								stocklist.put(ab, av);
								
							}   pb.setColor(ablist); pb.setAvailability(stocklist);
							HashMap<Size,String> sizelist = new HashMap<Size,String>();
							System.out.print(" Enter Size qty  "); Integer sqty = Integer.parseInt(s.nextLine());
							for(int i=1;i<=sqty;i++)
							{
								Size ab =  new Size();
								
								System.out.print(" Enter "+i+" Size:"); String size = s.nextLine();
								
								ab.setSize("size");
								
								sizelist.put(ab, size);
								
							}pb.setSize(sizelist);
						
							int k = new ProductDAO().updateStock(pb);
							if(k>0) System.out.println(" Sucessfully Update...");
							else System.err.println(" Not updated");
							
						}break; //inner case 4
						
						case 5: {
							
							System.out.println(" Enter Product id "); Integer pid = Integer.parseInt(s.nextLine());
							Product pb = new Product();
							pb.setId(pid);
							
							int k = new ProductDAO().deleteProduct(pb);
							if(k>0) System.out.println("Deleted Sucessfully...");
							else System.err.println(" Not deleted");
							
							
						} break;// inner case 5
						
						
						case 6: {
							
                            Product pb = new Product();
							
							ArrayList<Product> pblist = new ProductDAO().getProduct();
							
							
							System.out.println(pblist.size()); //System.out.println(pblist.toString());
							
							Iterator<Product> itr = pblist.iterator();
							Product pb1 = itr.next();

							System.out.println("Choose following category:");
							System.out.println("\t 1.mobile \n \t 2.clothes \n \t Enter Choice:-"); Integer choice = Integer.parseInt(s.nextLine());
							
							Map<Category, Product> cat = pb1.getCategories();

							Set<Category> keys = cat.keySet();
							
							Iterator<Category> itr2 = keys.iterator();
							if(choice==1)
							{
							while(itr2.hasNext())
							{
								Category c1 =  itr2.next();
							 	//Category c2 =  new Mobiles();
							 
								System.out.println(" in mobiles");
								Product product = cat.get(c1);
								if(product.getCategory().equalsIgnoreCase("mobile")) {		
						//		
								
								 System.out.println("Mobiles product"+product);
								}
							}}
							else {
								while(itr2.hasNext())
								{
									Category c1 = itr2.next();
								 	//Category c2 =  new Mobiles();
								 System.out.println(" in clothes");
									Product product = cat.get(c1);
								if(product.getCategory().equalsIgnoreCase("clothes")) {		
							
									
									 System.out.println(product);
								}
								}
							}
							

							
							
						}break;
						
						
						case 7:{System.out.println(" Sucesfully exit from app."); System.exit(0);} break;// case 4
						
						default : System.out.println(" wrong choice");break;
						
						}//inner switch
						
						
				
					}//inner while
				
				}else System.out.println(" Incorrect Crenditial ");
			
			
			
			}// outer case1			
			
			case 2: {
				System.out.println("**********welcome to Registraion**********");

				System.out.print(" Enter username:");
				String name = s.nextLine();
				System.out.println("Create Password:");
				String pass = s.nextLine();
				
				CustomerBean cust = new CustomerBean();
				cust.setUsername(name);
				cust.setPassword(pass);
				
				int k = new CustomerDAO().getRegister(cust);

				if (k > 0)
					System.out.println(" Register Sucessfully....");
				else
					System.err.println(" Not Register fill form carefully....");

			}
				break; // close outer case2
		
			
			case 3:{
				 System.out.println(" Exit Sucessfully..."); System.exit(0);
			
			flag=1;}break;
			
			default : System.out.println(" incorrect choice.."); break;
			
			}//outer switch
if(flag>0) break;
			
		}// outer while
		
		
	s.close();

	}

}
