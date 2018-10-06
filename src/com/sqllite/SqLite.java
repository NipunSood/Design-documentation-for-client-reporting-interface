package com.sqllite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bean.ClientDetails;


public class SqLite {
	String DbLocation = "jdbc:sqlite:E:/Bank.db";
	public List<ClientDetails> fetchAllClient(){
		 
		 List<ClientDetails> details = new ArrayList<ClientDetails>();
		 Connection c = null;
		 Statement stmt = null;
		   try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection(DbLocation);
		      c.setAutoCommit(false);
		      stmt = c.createStatement();
		      ResultSet rs = stmt.executeQuery( "select Client_Name from Client_Details;" );
		      
		      while ( rs.next() ) {
		    	  ClientDetails cd = new ClientDetails();  
		         String  client_name = rs.getString("Client_Name");

		         cd.setClient_name(client_name);
		         details.add(cd);		         
		      }
		      rs.close();
		      stmt.close();
		      c.close();
		   } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		   }
		   return details;
	}

	
	
	public List<ClientDetails> fetchDetails(){
	 
	 List<ClientDetails> details = new ArrayList<ClientDetails>();
	 Connection c = null;
	 Statement stmt = null;
	   try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection(DbLocation);
	      c.setAutoCommit(false);
	      stmt = c.createStatement();
	      ResultSet rs = stmt.executeQuery( "SELECT * FROM Transactions_Details;" );
	      
	      while ( rs.next() ) {
	    	  ClientDetails cd = new ClientDetails();  
	    	 String fund_name = rs.getString("Fund_Name");
	    	 int amount_invested = rs.getInt("Amount_Invested");
	    	 String date_time = rs.getString("Created_At");
	         String client_name = rs.getString("Client_Name");
	         String  client_type = rs.getString("Type_Client");
	         String investment_type = rs.getString("Type_Investment");
	         int current_value = rs.getInt("Current_Value");

	         cd.setFundname(fund_name);
	         cd.setAmount_invested(amount_invested);
	         cd.setCurrent_value(current_value);
	         cd.setDate_time(date_time);
	         cd.setClient_name(client_name);
	         cd.setClient_type(client_type);
	         cd.setInvestment_type(investment_type);
	         details.add(cd);
	         
	      }
	      rs.close();
	      stmt.close();
	      c.close();
	   } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	   }
	   return details;
}
	
	public List<ClientDetails> fetchDetail(String Id){
		 List<ClientDetails> details = new ArrayList<ClientDetails>();
		 Connection c = null;
		   try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection(DbLocation);
		      c.setAutoCommit(false);
		      String selectQueryByName = "select * from Transactions_Details where Client_Name=?";
		      PreparedStatement statement = c.prepareStatement(selectQueryByName);
		      statement.setString(1, Id);
		      ResultSet rs = statement.executeQuery();
		      while ( rs.next() ) {
		    	 ClientDetails cd = new ClientDetails();
		    	 String fund_name = rs.getString("Fund_Name");
		    	 int amount_invested = rs.getInt("Amount_Invested");
		    	 String date_time = rs.getString("Created_At");
		         String client_name = rs.getString("Client_Name");
		         String  client_type = rs.getString("Type_Client");
		         String investment_type = rs.getString("Type_Investment");
		         int current_value = rs.getInt("Current_Value");
		         
		         cd.setFundname(fund_name);
		         cd.setAmount_invested(amount_invested);
		         cd.setCurrent_value(current_value);
		         cd.setDate_time(date_time);
		         cd.setClient_name(client_name);
		         cd.setClient_type(client_type);
		         cd.setInvestment_type(investment_type);
		         
		         details.add(cd);
		      }
		      rs.close();
		      statement.close();
		      c.close();
		   } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		   }
		   return details;
	}
	
	public List<ClientDetails> fetchDetailsDatewise(String from, String to){

		List<ClientDetails> details = new ArrayList<ClientDetails>();
	 Connection c = null;
	 Statement stmt = null;
	   try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection(DbLocation);
	      c.setAutoCommit(false);
	      
	      String selectQueryByName = "select * from Transactions_Details where date(Created_At) >=? and date(Created_At)<=?";
	      PreparedStatement stmp = c.prepareStatement(selectQueryByName);
	      stmp.setString(1, from);
	      stmp.setString(2, to);
	      ResultSet rs = stmp.executeQuery();
	      
	      while ( rs.next() ) {
	    	  ClientDetails cd = new ClientDetails();  
	    	 String fund_name = rs.getString("Fund_Name");
	    	 int amount_invested = rs.getInt("Amount_Invested");
	    	 String date_time = rs.getString("Created_At");
	         String client_name = rs.getString("Client_Name");
	         String  client_type = rs.getString("Type_Client");
	         String investment_type = rs.getString("Type_Investment");
	         int current_value = rs.getInt("Current_Value");

	         cd.setFundname(fund_name);
	         cd.setAmount_invested(amount_invested);
	         cd.setCurrent_value(current_value);
	         cd.setDate_time(date_time);
	         cd.setClient_name(client_name);
	         cd.setClient_type(client_type);
	         cd.setInvestment_type(investment_type);
	         details.add(cd);
	         
	      }
	      rs.close();
	      stmp.close();
	      c.close();
	   } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	   }
	   return details;
}
	
	public List<ClientDetails> fetchClientDetailsDatewise(String client, String from, String to){
		 List<ClientDetails> details = new ArrayList<ClientDetails>();
		 Connection c = null;
		 Statement stmt = null;
		   try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection(DbLocation);
		      c.setAutoCommit(false);
		      
		      String selectQueryByName = "select * from Transactions_Details where date(Created_At) >=? and date(Created_At)<=? and Client_Name=?";
		      PreparedStatement stmp = c.prepareStatement(selectQueryByName);
		      stmp.setString(1, from);
		      stmp.setString(2, to);
		      stmp.setString(3, client);
		      ResultSet rs = stmp.executeQuery();
		      
		      while ( rs.next() ) {
		    	  ClientDetails cd = new ClientDetails();  
		    	 String fund_name = rs.getString("Fund_Name");
		    	 int amount_invested = rs.getInt("Amount_Invested");
		    	 String date_time = rs.getString("Created_At");
		         String client_name = rs.getString("Client_Name");
		         String  client_type = rs.getString("Type_Client");
		         String investment_type = rs.getString("Type_Investment");
		         int current_value = rs.getInt("Current_Value");

		         cd.setFundname(fund_name);
		         cd.setAmount_invested(amount_invested);
		         cd.setCurrent_value(current_value);
		         cd.setDate_time(date_time);
		         cd.setClient_name(client_name);
		         cd.setClient_type(client_type);
		         cd.setInvestment_type(investment_type);
		         details.add(cd);
		         
		      }
		      rs.close();
		      stmp.close();
		      c.close();
		   } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		   }
		   return details;
	}
	
	
	
	
}