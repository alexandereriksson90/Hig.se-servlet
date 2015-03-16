import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.owlike.genson.Genson;

import domain.DataMatcher;
import domain.DataPair;
import domain.DataSource;
import domain.Resolution;
import InputData.FootballGoalsSource;
import InputData.SineWave;

@WebServlet("/Servlet")
public class Servlet extends HttpServlet {

	private static final long serialVersionUID = 7500209226833846085L;

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		try (PrintWriter out = response.getWriter()) {
			DataSource source1 = new FootballGoalsSource();
			DataSource source2 = new SineWave();
			DataMatcher monthDm = new DataMatcher(source1, source2,
					Resolution.MONTH);
			Map<String, DataPair> monthResult = monthDm.searchDataForMatch()
					.getData();
			Genson genson = new Genson();
			String str;
			JsonFormater jsonf = new JsonFormater();

			try {
				if (request.getParameter("pretty").equals("true")) {
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
			} catch (NullPointerException e) {
				str = genson.serialize(monthResult);
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