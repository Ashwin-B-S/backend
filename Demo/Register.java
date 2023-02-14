
import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Register extends HttpServlet {
	
	public void doPost(HttpServletRequest request ,HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		String title = request.getParameter("title"),first_name = request.getParameter("first_name");
		String last_name = request.getParameter("last_name"),position = request.getParameter("position");
		String company = request.getParameter("company"),business = request.getParameter("business");
		String employees = request.getParameter("employees"),street = request.getParameter("street");
		String additional = request.getParameter("additional");
		String zip = request.getParameter("zip"),place = request.getParameter("place");
		String country = request.getParameter("country"),code = request.getParameter("code");
		String phone = request.getParameter("phone"),your_email = request.getParameter("your_email");
		try{
			Class.forName("org.postgresql.Driver");
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/demo","postgres","123456789");
			Statement stm = conn.createStatement();
			stm.execute("insert into register values('"+title+"','"+first_name+"','"+last_name+"','"+position+"','"+company+"','"+business+"','"+employees+"','"+street+"','"+additional+"',"+zip+",'"+place+"','"+country+"','"+code+"','"+phone+"','"+your_email+"');");
			//ResultSet rst = stm.getResultSet();
			
		}catch(Exception e){
			System.out.println(e);
		}
	}
}