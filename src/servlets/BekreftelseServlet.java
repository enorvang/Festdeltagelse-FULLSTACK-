package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class BekreftelseServlet
 */
@WebServlet(name="Bekreftelse", urlPatterns="/bekreftelse")
public class BekreftelseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sesjon = request.getSession(false);
		if (sesjon == null) {
			//Ved forsøk på å gå direkte til handleliste uten å gå gjennom innloggingssiden
			response.sendRedirect("login?feilkode=9");
		} else {
			String fornavn = (String) sesjon.getAttribute("fornavn");
			request.setAttribute("fornavn", fornavn);
			request.getRequestDispatcher("WEB-INF/jsp/paameldingsbekreftelse.jsp").forward(request, response);
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
