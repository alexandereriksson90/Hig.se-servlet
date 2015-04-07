
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import InputData.DataSourceFactory;
import com.owlike.genson.Genson;

import domain.DataMatcher;
import domain.DataPair;
import domain.DataSource;
import domain.Resolution;

@WebServlet("/Servlet")
public class Servlet extends HttpServlet {

	private static final long serialVersionUID = 7500209226833846085L;

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		try (PrintWriter out = response.getWriter()) {
			String str;
			Genson genson = new Genson();
			JsonFormater jsonf = new JsonFormater();
			Map<String, DataPair> monthResult = null;

			try {
				DataSource source1 = DataSourceFactory.getDataSource(request
						.getParameter("datasource1"));
				DataSource source2 = DataSourceFactory.getDataSource(request
						.getParameter("datasource2"));
				DataMatcher monthDm = new DataMatcher(source1, source2,
						Resolution.MONTH);
				monthResult = monthDm.searchDataForMatch().getData();
				if (monthResult.isEmpty()) {
					str = "Inga matchande data";
					out.println(str);
				} else if (request.getParameter("pretty").equals("true")) {
					str = genson.serialize(monthResult);
					str = jsonf.format(str);
					out.println(str);
				} else if (request.getParameter("pretty").equals("false")) {
					str = genson.serialize(monthResult);
					out.println(str);
				} else {
					str = genson.serialize(monthResult);
					out.println(str);
				}

			} catch (RuntimeException e) {
				str = "Felinparametrar valda!";
				out.println(str);
			}

		}
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	public String getServletInfo() {
		return "Statistik-servlet";
	}

}