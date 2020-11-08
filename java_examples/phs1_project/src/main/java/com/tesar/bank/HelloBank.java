package com.tesar.bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.lang.System;


import com.tesar.bank.sql.BankQueries;
import com.tesar.bank.sql.PostresSqlConnection;
import java.util.List;
import java.util.Scanner;
//import User.java;

public class HelloBank {

	public static void main(String[] args) {
		Connection connection=null;
		User user=null;
		PreparedStatement preparedStatement=null;
		ResultSet rs=null;
		String sql=null;
		String username;
		String password;
		String firstname;
		String lastname;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to Tesar's Bank app");
		System.out.println("--------------------------------------------------");
		
		int ch = 0;
		do {
			System.out.println("Enter 1 in order to log in, 2 to make a new account enter 3 to exit");
			try {
				ch = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {

			}
			switch(ch){
			case(1):
				System.out.println("Enter Username");
				username = scanner.nextLine();
				System.out.println("Enter password");
				password= scanner.nextLine();
				try {
					//Step 1 - Load/Register the Driver
					
					
					
					//Step 2 - Open Connection(url,username,password)
					
					connection=PostresSqlConnection.getConnection();
					//System.out.println("Connection Successfull");
					
					//Step 3 - Create Statement
					//Statement statement=connection.createStatement();
					sql=BankQueries.GETUSERLOGIN;
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setString(1, username);
					preparedStatement.setString(2, password);
					//Step 4 - Execute Query
					rs = preparedStatement.executeQuery();
					
					
					//ResultSet rs=statement.executeQuery(sql);
					//System.out.println("Query Executed");
					
					//Step 5 - Process Results
					if(rs.next()) {
						user = new User(rs.getInt("id"),rs.getString("username"),rs.getString("password"),
								rs.getString("lastname"),rs.getString("firstname"),rs.getString("position"));
						//while(rs.next()) {
							//System.out.print("Id = "+rs.getInt("id"));
						//}
						
					}
					else
					System.out.println("Invalid username or password");
				} catch (ClassNotFoundException e) {
					System.out.println(e);
				} catch (SQLException e) {
					System.out.println(e);
				}
				finally {
					try {
						//Step 6 - Close Connection
					connection.close();
						System.out.println("Connection closed");
						if(user!=null)
						user.customerOptions();
					} catch (SQLException e) {
						System.out.println(e);
					}
				}
				break;
				
			case(2):
				System.out.println("Enter Username");
				username = scanner.nextLine();
				System.out.println("Enter password");
				password= scanner.nextLine();
				System.out.println("Enter first name");
				firstname= scanner.nextLine();
				System.out.println("Enter Last name");
				lastname=scanner.nextLine();
				
				
			
			
					
				
			try {
					connection=PostresSqlConnection.getConnection();
				   // sql=BankQueries.CHECKIFUSERNAMEUSED;
				    //preparedStatement = connection.prepareStatement(sql);
				    //preparedStatement.setString(1, username);
				    //rs = preparedStatement.executeQuery();
			
				
					/*sql=BankQueries.GETMAXUSERID;
					preparedStatement = connection.prepareStatement(sql);
					rs = preparedStatement.executeQuery();
					int newID =rs.getInt("max")+1;*/
					
					
					sql=BankQueries.INSERTNEWUSER;
					preparedStatement = connection.prepareStatement(sql);
					//preparedStatement.setInt(1, default);
					preparedStatement.setString(1, username);
					preparedStatement.setString(2, password);
					preparedStatement.setString(3, lastname);
					preparedStatement.setString(4, firstname);
					//preparedStatement.setString(6, "c");
					preparedStatement.executeUpdate();
					
					sql=BankQueries.GETUSERLOGIN;
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setString(1, username);
					preparedStatement.setString(2, password);
					rs= preparedStatement.executeQuery();
					if(rs.next()) {
					user = new User(rs.getInt("id"),username,password,
							lastname,firstname,"c");
					}
					else {System.out.println("something failed");}
				
				//ResultSet rs=statement.executeQuery(sql);
				//System.out.println("Query Executed");
				
				//Step 5 - Process Results
				
			} catch (ClassNotFoundException e) {
				System.out.println(e);} 
			catch (SQLException e) {
				System.out.println(e);
			}
			finally {
				try {
					//Step 6 - Close Connection
				connection.close();
					System.out.println("Connection closed");
					if(user!=null)
					user.customerOptions();
				} catch (SQLException e) {
					System.out.println(e);
				}
			}
				
			
			break;
				
			
			default: System.out.println("Invalid");
				break;
			
			}
			
			
		} while (ch != 3);

	}

}