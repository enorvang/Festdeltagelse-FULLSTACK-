package servlets;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import eao.DeltagerEAO;
import entities.Deltager;
import utilities.Hashing;
import utilities.HashingUtil;
import utilities.InnloggingUtil;
import utilities.RegistreringsSkjema;
import utilities.Validering;

@WebServlet(name = "Påmelding", urlPatterns = "/paamelding")
public class PaameldingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RegistreringsSkjema rs = new RegistreringsSkjema();
	
	@EJB
	DeltagerEAO deltagerEAO;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String feilkode = request.getParameter("feilkode");
		String feilmelding = "";
		String loginLink = "";
		if (feilkode != null) {
			if (feilkode.contentEquals("1")) {
				feilmelding = "Mobilnummeret er allerede registrert. ";
				loginLink = "Logg inn";
			}
		}
		request.setAttribute("feilmelding", feilmelding);
		request.setAttribute("loginLink", loginLink);
		request.setAttribute("registreringsskjema", rs);
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

		rs.setFornavn(fornavn);
		rs.setEtternavn(etternavn);
		rs.setMobil(mobil);
		rs.setPassord(passord);
		rs.setPassordRepetert(passordRepetert);
		rs.setKjonn(kjonn);

		if (!Validering.erAlleGyldige(rs)) {
			response.sendRedirect("paamelding");
		} else {
			HttpSession sesjon = InnloggingUtil.loggInnMedTimeout(request, 12000);

			HashingUtil hashing = new HashingUtil("SHA-256");
			
			try {
				hashing.generateHashWithSalt(passord, hashing.generateSalt());
				
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}

			
			String passordHash = hashing.getPasswordHashinHex();
			String passordSalt = hashing.getPasswordSalt();
			

			if (deltagerEAO.erMobilBrukt(mobil)) {
				response.sendRedirect("paamelding?feilkode=1");
			} else {
				Deltager d = new Deltager(fornavn, etternavn, mobil, passordHash, kjonn, passordSalt);

				deltagerEAO.leggTilDeltager(d);
				request.setAttribute("registreringsskjema", rs);
				sesjon.setAttribute("innloggetDeltager", d);
				response.sendRedirect("bekreftelse");
			}
		}

	}

}
