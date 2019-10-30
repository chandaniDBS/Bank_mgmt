
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class Register extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String name = request.getParameter("userName");
		String password = request.getParameter("userPass");
		String email = request.getParameter("userEmail");
		String country = request.getParameter("userCountry");
		String mobile="8080808080";
		String address=request.getParameter("useraddress");
		String pincode=request.getParameter("Pincode");
		String state=request.getParameter("userState");
		

		try {
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);// ("com.mysql.jdbc.Driver");
			String connectionUrl = "jdbc:mysql://localhost:3306/bank_mgmt";
			String dbUser = "root";
			String dbPwd = "root";
			Connection con = DriverManager.getConnection(connectionUrl, dbUser, dbPwd);
			PreparedStatement ps = con.prepareStatement("insert into registeruser values(?,?,?,?,?,?,?,?)");
			ps.setString(1, name);
			ps.setString(2, password);
			ps.setString(3, email);
			ps.setString(4, mobile);
			ps.setString(5, address);
			ps.setString(6, pincode);
			ps.setString(7, state);
			ps.setString(8, country);
			
			int i = ps.executeUpdate();
			if (i > 0)
				out.print("You are successfully registered...");

		} catch (Exception e2) {
			System.out.println(e2);
		}

		out.close();
	}

}
