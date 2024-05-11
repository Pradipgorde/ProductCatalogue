package com.ltr.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.ltr.Beans.*;
public class CustomerDAO
{
	public Boolean getLogin(CustomerBean cust) {

		Connection con = ConnectionDAO.getDBConnection();

		try (con) {

			PreparedStatement ps = con.prepareStatement("select * from CustomerData where name=? AND password=?");

			ps.setString(1, cust.getUsername());
			ps.setString(2, cust.getPassword());

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				String name = rs.getString(1);
				String pass = rs.getString(2);

				if (name.equals(cust.getUsername()) && pass.equals(cust.getPassword())) {
					return true;
				}
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return false;
	}


	
	public int getRegister(CustomerBean cust) {
		int k = 0;

		Connection con = ConnectionDAO.getDBConnection();
		try (con) {

			PreparedStatement ps = con.prepareStatement("Insert into CustomerData values(?,?)");

			ps.setString(1, cust.getUsername());
			ps.setString(2, cust.getPassword());
			

			k = ps.executeUpdate();
			
			if(k>0) return k;

		} catch (Exception e) {
			e.getMessage();
		}

		return k;
	}
	

	
	
	
	
	
	
	
	
}
