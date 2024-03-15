package com.main;

import java.sql.*;
public class Databaseconnection 
{
	protected static Connection initializeDataBase() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost/telecom?useSSL=false","root","root");
		return connection;
	}
}
