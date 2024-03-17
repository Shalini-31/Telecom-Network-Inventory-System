package com.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmpDaoAD {

    public static Connection getConnection(){
        Connection con=null;
        try{
        	 con=Databaseconnection.initializeDataBase();
           
        }catch(Exception e){System.out.println(e);}
        return con;
    }

    public static int save(EmpAD e){
        int status1=0;
        try{
            Connection con=EmpDaoAD.getConnection();
            PreparedStatement ps=con.prepareStatement("insert into details(Type,Model,Status,Location,VendorID) values (?,?,?,?,?)");
            ps.setString(1,e.getType());
            ps.setString(2,e.getModel());
            ps.setString(3,e.getStatus());
            ps.setString(4,e.getLocation());
            ps.setString(5,e.getVid()); // Corrected to setInt for VendorID
 
            status1=ps.executeUpdate();
 
            con.close();
        }catch(Exception ex){ex.printStackTrace();}
 
        return status1;
    }
 
    public static int update(EmpAD e){
        int status1=0;
        try{
            Connection con=EmpDaoAD.getConnection();
            PreparedStatement ps=con.prepareStatement("update details set Type=?,Model=?,Status=?,Location=?,VendorID=? where ComponentId=?");
            ps.setString(1,e.getType());
            ps.setString(2,e.getModel());
            ps.setString(3,e.getStatus());
            ps.setString(4,e.getLocation());
            ps.setString(5,e.getVid());
            ps.setString(6,e.getCid());
 
 
            status1=ps.executeUpdate();
 
            con.close();
        }catch(Exception ex){ex.printStackTrace();}
 
        return status1;
    }
 
    public static int delete(String cid){
        int status1=0;
        try{
            Connection con=EmpDaoAD.getConnection();
            PreparedStatement ps=con.prepareStatement("delete from details where ComponentId=?");
            ps.setString(1,cid);
            status1=ps.executeUpdate();
 
            con.close();
        }catch(Exception e){e.printStackTrace();}
 
        return status1;
    }
 
    public static EmpAD getComponentById(String cid){
        EmpAD e=new EmpAD();
 
        try{
            Connection con=EmpDaoAD.getConnection();
            PreparedStatement ps=con.prepareStatement("select * from details where ComponentId=?");
            ps.setString(1,cid);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                e.setCid(rs.getString(1));
                e.setType(rs.getString(2));
                e.setModel(rs.getString(3));
                e.setStatus(rs.getString(4));
                e.setLocation(rs.getString(5));
                e.setVid(rs.getString(6)); // Corrected to getInt for VendorID
            }
            con.close();
        }catch(Exception ex){ex.printStackTrace();}
 
        return e;
    }
 
    public static List<EmpAD> getAllComponents(String search, String type, String status, String location){
        List<EmpAD> list=new ArrayList<EmpAD>();
 
        try{
            Connection con=EmpDaoAD.getConnection();
            StringBuilder queryBuilder = new StringBuilder("select * from details where 1=1");
 
            // Add search condition
            if (search != null && !search.isEmpty()) {
                queryBuilder.append(" and (ComponentId like ? or Type like ? or Model like ?)");
            }
 
            // Add type filter condition
            if (type != null && !type.isEmpty()) {
                queryBuilder.append(" and Type like ?");
            }
 
            // Add status filter condition
            if (status != null && !status.isEmpty()) {
                queryBuilder.append(" and Status = ?");
            }
 
            // Add location filter condition
            if (location != null && !location.isEmpty()) {
                queryBuilder.append(" and Location like ?");
            }
 
            PreparedStatement ps=con.prepareStatement(queryBuilder.toString());
 
            // Set parameters for search and filters
            int parameterIndex = 1;
            if (search != null && !search.isEmpty()) {
                ps.setString(parameterIndex++, "%" + search + "%");
                ps.setString(parameterIndex++, "%" + search + "%");
                ps.setString(parameterIndex++, "%" + search + "%");
            }
            if (type != null && !type.isEmpty()) {
                ps.setString(parameterIndex++, "%" + type + "%");
            }
            if (status != null && !status.isEmpty()) {
                ps.setString(parameterIndex++, status);
            }
            if (location != null && !location.isEmpty()) {
                ps.setString(parameterIndex++, "%" + location + "%");
            }
 
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                EmpAD e=new EmpAD();
                e.setCid(rs.getString(1));
                e.setType(rs.getString(2));
                e.setModel(rs.getString(3));
                e.setStatus(rs.getString(4));
                e.setLocation(rs.getString(5));
                e.setVid(rs.getString(6));
                list.add(e);
            }
            con.close();
        }catch(Exception e){e.printStackTrace();}
 
        return list;
    }
}