package com.servlet;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
public class Databaseconnection 
{
 
	protected static Connection initializeDataBase() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost/demo?useSSL=false","root","root");
		return connection;
	}
}