package com.idiot.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/deleteurl")
public class DeleteServlet extends HttpServlet {
	private static final String query="delete from BookData where id=?";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	//get PrintWriter
		PrintWriter pw=res.getWriter();
	//set content type
		res.setContentType("text/html");
	//get id of record
		int id=Integer.parseInt(req.getParameter("id"));
	//Load jdbc driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException cnf) {
			// TODO Auto-generated catch block
			cnf.printStackTrace();
		}
	//generate the connection

	try
	{
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Book","root","root");
		PreparedStatement ps=con.prepareStatement(query);
		ps.setInt(1,id);
		int count=ps.executeUpdate();
		if(count==1)
		{
			pw.println("<h2>Deleted Sucessfully</h2>");
		}
		else
		{
			pw.println("<h2> Not Deleted </h2>");
		}
    
}catch(SQLException se)
	{
		se.printStackTrace();
		pw.println("<h1>"+se.getMessage()+"</h2>");
	}catch(Exception e) {
		e.printStackTrace();
		pw.println("<h1>"+e.getMessage()+"</h2>");
	}
         pw.println("<a href='home.html'>Home</a>");
         pw.println("<br>");
         pw.println("<a href='bookList'>Book List</a>");

	}
	@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	   doGet(req,res);
		

		}
	
}
