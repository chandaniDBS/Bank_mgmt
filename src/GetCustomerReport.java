
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

public class GetCustomerReport extends HttpServlet {
	static Logger log = Logger.getLogger(GetCustomerReport.class.getName());
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		JSONObject js = null;
		BufferedReader reader = request.getReader();
		String line = null;
		StringBuffer sbuff = new StringBuffer();

		while ((line = reader.readLine()) != null) {
			sbuff.append(line);
		}

		try {
			js = new JSONObject(sbuff.toString());
			String data = null;
			String type = js.getString("type");
			log.info("type" + type);
			String operation;
			if (type != null && type.equalsIgnoreCase("customer")) {
				operation = js.getString("fromDate");
				String toDate = js.getString("toDate");
				log.info(" ::fromDate::" + operation + "::toDate::" + toDate);
				GetCustomerReportDao report = new GetCustomerReportDao();
				data = report.getreport(operation, toDate);
				log.info("data:::" + data);
				out.write(data);
				out.flush();
				out.close();
			} else {
				
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
