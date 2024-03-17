package com.main;

import java.io.IOException;
import java.io.PrintWriter;
 
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
 
@WebServlet("/SaveServletAD")
public class SaveServletAD extends HttpServlet {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String type=request.getParameter("type");
		String model=request.getParameter("model");
		String status=request.getParameter("status");
		String location=request.getParameter("location");
		String vid=request.getParameter("vid");
 
		EmpAD e=new EmpAD();
		e.setType(type);
		e.setModel(model);
		e.setStatus(status);
		e.setLocation(location);
		e.setVid(vid);
		
		int status1=EmpDaoAD.save(e);
		if(status1>0){
			//out.print("<p>Record saved successfully!</p>");
			
			request.getRequestDispatcher("sam.html").include(request, response);
		}else{
			out.println("Sorry! unable to save record");
		}
 
		out.close();
	}
 
}