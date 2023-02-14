
import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest request ,HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		String name = request.getParameter("uname"),pass = request.getParameter("pass");
		try{
			Class.forName("org.postgresql.Driver");
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/demo","postgres","123456789");
			Statement stm = conn.createStatement();
			stm.execute("select * from users where name = '"+name+"' and password = '"+pass+"';");
			ResultSet rst = stm.getResultSet();
			if(rst.next()){
				response.sendRedirect("http://localhost:4200/profile");
				System.out.println(rst.getString("mobile"));
				session.setAttribute("mobile",rst.getString("mobile"));
			}else{
				out.println("<h1>Login failed</h1>");
			}
		}catch(Exception e){
			System.out.println(e);
		}
	}
}