
import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class RegisterServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest request ,HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		String name = request.getParameter("uname"),email = request.getParameter("email");
		String mobile = request.getParameter("mobile"),pass = request.getParameter("newpass");
		try{
			Class.forName("org.postgresql.Driver");
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/demo","postgres","123456789");
			Statement stm = conn.createStatement();
			stm.execute("insert into users values('"+name+"','"+email+"','"+mobile+"','"+pass+"');");
			response.sendRedirect("http://localhost:4200/login");
		}catch(Exception e){
			System.out.println(e);
		}
	}
}