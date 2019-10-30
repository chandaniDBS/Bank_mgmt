
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONObject;

public class GetCustomerReportDao {
	static Logger log = Logger.getLogger(GetCustomerReportDao.class.getName());

	public String getreport(String fromDate, String toDate) {
		log.info("CustomerReport.java:::::::::::::");
		log.info("CustomerReport.java::fromDate::" + fromDate + "::toDate::" + toDate);
		JSONArray arr = new JSONArray();
		Connection con = null;
		CallableStatement cs = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		JSONObject obj = null;

		try {
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);// ("com.mysql.jdbc.Driver");
			String connectionUrl = "jdbc:mysql://localhost:3306/bank_mgmt";
			String dbUser = "root";
			String dbPwd = "root";
			Connection con1 = DriverManager.getConnection(connectionUrl, dbUser, dbPwd);
			String sql = "select from registeruser '"
					+ fromDate + "' AND '" + toDate + "'";
			log.info("Query::" + sql);
			ps1 = con1.prepareStatement(sql);
			rs1 = ps1.executeQuery();
			boolean var12 = false;

			while (rs1.next()) {
				obj = new JSONObject();
				obj.put("Name", rs1.getString("userName"));
				obj.put("Email", rs1.getString("userEmail"));
				obj.put("Mobile", rs1.getString("usermobile"));
				obj.put("Address", rs1.getString("useraddress"));
				obj.put("State", rs1.getString("userState"));
				obj.put("Pincode", rs1.getString("Pincode"));
				obj.put("Country", rs1.getString("userCountry"));
			
				arr.put(obj);
			}

			log.info("::json data::" + arr.toString());
		} catch (Exception var21) {
			var21.printStackTrace();
		} finally {
			try {
				if (cs != null) {
					((CallableStatement) cs).close();
				}

				if (rs1 != null) {
					rs1.close();
				}

				if (rs2 != null) {
					((ResultSet) rs2).close();
				}

				if (ps1 != null) {
					ps1.close();
				}

				if (ps2 != null) {
					((PreparedStatement) ps2).close();
				}

				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return arr.toString();
	}

	public static void main(String[] args) {
		
	  }
	}

	

