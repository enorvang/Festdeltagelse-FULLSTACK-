package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Deltager;
import utilities.InnloggingUtil;

@WebServlet(name = "Bekreftelse", urlPatterns = "/bekreftelse")
public class BekreftelseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (!InnloggingUtil.erInnlogget(request)) {
			// Ved forsøk på uautorisert tilgang
			response.sendRedirect("login?feilkode=1"); // feilkode=1: "Du må logge inn for å se deltagerlisten"
		} else {
			HttpSession sesjon = request.getSession(false);
			Deltager deltager = (Deltager) sesjon.getAttribute("innloggetDeltager");
			request.setAttribute("deltager", deltager);
			request.getRequestDispatcher("WEB-INF/jsp/paameldingsbekreftelse.jsp").forward(request, response);
		}

	}

}
