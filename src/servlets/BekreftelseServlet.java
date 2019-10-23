package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Deltager;

/**
 * Servlet implementation class BekreftelseServlet
 */
@WebServlet(name = "Bekreftelse", urlPatterns = "/bekreftelse")
public class BekreftelseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession sesjon = request.getSession(false);
		if (sesjon == null) {
			// Ved fors�k p� � g� direkte til bekreftelse uten � g� gjennom innloggingssiden
			response.sendRedirect("login?feilkode=9"); // m� fikse feilkode
		} else {

			Deltager deltager = (Deltager) sesjon.getAttribute("innloggetDeltager");
			request.setAttribute("deltager", deltager);
			request.getRequestDispatcher("WEB-INF/jsp/paameldingsbekreftelse.jsp").forward(request, response);
		}

	}

}
