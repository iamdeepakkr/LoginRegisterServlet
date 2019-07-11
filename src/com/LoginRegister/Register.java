package com.LoginRegister;

import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


@WebServlet("/register")
public class Register extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String pnumber= request.getParameter("pnumber");
		String gender= request.getParameter("gender");
		String password= request.getParameter("password");

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "DEEPAK93", "DEEPAK");

			PreparedStatement ps = con.prepareStatement("insert into LOGINREGISTERSERVLET values(?,?,?,?,?)");

			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, pnumber);
			ps.setString(4, gender);
			ps.setString(5, password);

			int i = ps.executeUpdate();
			if (i > 0)
				out.print("You are successfully registered...");

		} catch (Exception e) {
			out.print(e);
		}

		out.close();
	}

}
