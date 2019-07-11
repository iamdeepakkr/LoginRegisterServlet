package com.LoginRegister;

import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/login")
public class Login extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "DEEPAK93", "DEEPAK");
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM LOGINREGISTERSERVLET WHERE email='" + email + "'");

			if (rs.next()) {
				if (rs.getString(5).equals(password)) {
					out.print("welcome: " +rs.getString(1));
				} else {
					out.print("Oops! you are not valid user please try again....");

				}
			}
		} catch (Exception e) {
			out.print(e);
		}
	}
}
