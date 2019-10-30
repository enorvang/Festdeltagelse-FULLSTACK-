package servlets;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import eao.DeltagerEAO;
import entities.Deltager;
import utilities.HashingUtil;
import utilities.InnloggingUtil;

/**
 * Servlet implementation class LoggInnServlet
 */
@WebServlet(name = "LoggInn", urlPatterns = "/login")
public class LoggInnServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashingUtil hashing = new HashingUtil("SHA-256");

	@EJB
	DeltagerEAO deltagerEAO;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String feilkode = request.getParameter("feilkode");
		String feilmelding = "";
		if (feilkode != null) {
			if (feilkode.contentEquals("1")) {
				feilmelding = "Du må logge inn for å se deltagerlisten";
			} else if (feilkode.contentEquals("2")) {
				feilmelding = "Ugyldig brukernavn og/eller passord";
			}
		}

		request.setAttribute("feilmelding", feilmelding);

		request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String mobil = request.getParameter("mobil");
		String passord = request.getParameter("passord");

		try {
			Deltager d = deltagerEAO.finnDeltagerMedMobil(mobil);
			if (hashing.validatePasswordWithSalt(passord, d.getPassordSalt(), d.getPassordHash())) {
				HttpSession sesjon = InnloggingUtil.loggInnMedTimeout(request, 120);
				sesjon.setAttribute("innloggetDeltager", d);
				request.setAttribute("innloggetDeltager", d);
				response.sendRedirect("deltagere");
			} else {
				response.sendRedirect("login?feilkode=2");
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			response.sendRedirect("login?feilkode=2");
		}

	}

}
