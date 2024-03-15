package com.FaultOperatorActions;

import java.sql.*;
public class JdbcFault 
{

	public static Connection initializeDatabase() throws ClassNotFoundException, SQLException 
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost/network?allowPublicKeyRetrieval=true&useSSL=false","root","root");
		return con;

	}

}
