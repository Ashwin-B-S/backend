import java.io.*;
import java.util.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.google.gson.*;
import org.json.simple.JSONObject;

public class getData extends HttpServlet {
	
	HashMap <String,Object> res = new HashMap<>();
	
	public void doPost(HttpServletRequest request ,HttpServletResponse response) throws IOException, ServletException {
		
		HttpSession session = request.getSession();
		String number = (String)session.getAttribute("mobile");
		try{
			JSONObject obj = new JSONObject();
			Class.forName("org.postgresql.Driver");
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/demo","postgres","123456789");
			Statement stm = conn.createStatement();
			stm.execute("select * from users;");
			ResultSet rst = stm.getResultSet();
			HashSet <HashMap<String,Object>> hm = new HashSet<>(); 
			while(rst.next()){
				HashMap <String,Object> rs = new HashMap<>();
				rs.put("name",rst.getString("name"));
				rs.put("email",rst.getString("email"));
				rs.put("mobile",rst.getString("mobile"));
				hm.add(rs);
			}
			res.put("people",hm);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.write(new Gson().toJson(res));
			conn.close();
		}catch(Exception e){
			System.out.println(e+"getdata");
		}
		
	}
	
	public void doGet(HttpServletRequest request ,HttpServletResponse response) throws IOException, ServletException {
		doPost(request,response);
	}
}