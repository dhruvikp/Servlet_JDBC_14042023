package com.simplilearn.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.simplilearn.db.DbUtil;

/**
 * Servlet implementation class ListProductsServlet
 */
@WebServlet("/listProducts")
public class ListProductsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListProductsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

		PrintWriter pw = response.getWriter();
		pw.println("<html><body>");
		pw.println("<table>");
		pw.println("<tr>");
		pw.println("<th> Id </th>");
		pw.println("<th> Name </th>");
		pw.println("<th> Price </th>");
		pw.println("<th> Date Added </th>");
		pw.println("</tr>");
		
		String jdbcUrl = "jdbc:mysql://localhost:3306/phase2";
		String username = "root";
		String password = "root";

		try {
			DbUtil dbObj = new DbUtil(jdbcUrl, username, password);

			// STEP 1&2: Gets connection object
			Connection con = dbObj.getConnection();

			// STEP 3: Create Statement Object
			Statement stmt = con.createStatement();

			// STEP 4: Execute Query
			ResultSet rs = stmt.executeQuery("select * from eproduct");
			
			
			// STEP 4: Execute Parameterized query
			/*
			 * PreparedStatement preparedStatement =
			 * con.prepareStatement("insert into eproduct values (?,?,?)");
			 * preparedStatement.setInt(1, 7); preparedStatement.setString(2,
			 * "Simplilearn"); preparedStatement.setDate(3, new Date());
			 */
			

			// STEP 5: Iterate Resultset
			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				double price = rs.getDouble(3);
				Date date_added = rs.getDate(4);
				
				pw.println("<tr>");
				pw.println("<td>"+id+"</td>");
				pw.println("<td>"+name+"</td>");
				pw.println("<td>"+price+"</td>");
				pw.println("<td>"+date_added+"</td>");
				pw.println("</tr>");
			}

			// STEP 6: Close Connection
			dbObj.closeConnection();
			pw.println("</body></html>");
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
