package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utilities.InnloggingUtil;

/**
 * Servlet implementation class LoggUtServlet
 */
@WebServlet(name = "LoggUt", urlPatterns = "/loggut")
public class LoggUtServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		InnloggingUtil.loggUt(request);

		request.getRequestDispatcher("WEB-INF/jsp/loggetut.jsp").forward(request, response);
	}

}
