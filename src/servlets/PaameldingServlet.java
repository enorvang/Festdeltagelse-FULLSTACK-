package servlets;

import java.io.IOException;

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
import utilities.RegistreringsSkjema;
import utilities.Validering;

@WebServlet(name = "Påmelding", urlPatterns = "/paamelding")
public class PaameldingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	DeltagerEAO deltagerEAO;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		RegistreringsSkjema rs;
		HttpSession sesjon = request.getSession(false);
		
		if(sesjon != null) {
			rs = (RegistreringsSkjema) sesjon.getAttribute("registreringsskjema");
		}else {
			
			sesjon = request.getSession(true);
			sesjon.setMaxInactiveInterval(40);
			rs = new RegistreringsSkjema();
		}
		
		String feilkode = request.getParameter("feilkode");
		String feilmelding = "";

		if (feilkode != null) {
			if (feilkode.contentEquals("1")) {
				feilmelding = "Mobilnummeret er allerede registrert. Logg inn i stedet.";
			}
		}
		
		request.setAttribute("feilmelding", feilmelding);
		sesjon.setAttribute("registreringsskjema", rs);

		request.getRequestDispatcher("WEB-INF/jsp/paameldingsskjema.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String fornavn = request.getParameter("fornavn");
		String etternavn = request.getParameter("etternavn");
		String mobil = request.getParameter("mobil");
		String passord = request.getParameter("passord");
		String passordRepetert = request.getParameter("passordRepetert");
		String kjonn = request.getParameter("kjonn");

		HttpSession sesjon = request.getSession(false);
		if (sesjon != null) {
			RegistreringsSkjema rs = (RegistreringsSkjema) sesjon.getAttribute("registreringsskjema");

			rs.setFornavn(fornavn);
			rs.setEtternavn(etternavn);
			rs.setMobil(mobil);
			rs.setPassord(passord);
			rs.setPassordRepetert(passordRepetert);
			rs.setKjonn(kjonn);
			
			sesjon.setAttribute("registreringsskjema", rs);

			if (!Validering.erAlleGyldige(rs)) {
				
				response.sendRedirect("paamelding");

			} else {
				if (deltagerEAO.erMobilBrukt(mobil)) {
					rs.setPassord("");
					rs.setPassordRepetert("");
					response.sendRedirect("paamelding?feilkode=1");
				} else {
					sesjon = InnloggingUtil.loggInnMedTimeout(request, 120);
					HashingUtil hashing = new HashingUtil("SHA-256");

					hashing.generateHashWithSalt(passord, hashing.generateSalt());

					String passordHash = hashing.getPasswordHashinHex();
					String passordSalt = hashing.getPasswordSalt();
					Deltager d = new Deltager(fornavn, etternavn, mobil, passordHash, kjonn, passordSalt);

					deltagerEAO.leggTilDeltager(d);
					sesjon.setAttribute("innloggetDeltager", d);
					response.sendRedirect("bekreftelse");

				}
			}
		}
	}

}
