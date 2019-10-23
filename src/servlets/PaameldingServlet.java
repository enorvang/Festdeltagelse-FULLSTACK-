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
		if(feilkode != null) {
			if(feilkode.contentEquals("1")) {
				feilmelding = "Mobilnummeret er allerede registrert";
				
			}
		}
		request.setAttribute("feilmelding", feilmelding);
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

		rs.setFornavn(request.getParameter("fornavn"));
		rs.setEtternavn(request.getParameter("etternavn"));
		rs.setMobil(request.getParameter("mobil"));
		rs.setPassord(request.getParameter("passord"));
		rs.setPassordRepetert(request.getParameter("passordRepetert"));
		rs.setKjonn(request.getParameter("kjonn"));

		// TODO MÅ OGSÅ SJEKKE AT MOBILNUMMERET IKKE FINNES I DATABASEN!

		if (!Validering.erAlleGyldige(rs)) {
			response.sendRedirect("paamelding");
		} else {
			HttpSession sesjon = InnloggingUtil.loggInnMedTimeout(request, 12000);

			
			//TODO generere passordsalt
			// hashe inntastet passord
			Hashing hash = new Hashing("SHA-256");
			try {
				hash.generateHashWithSalt(passord, hash.getSalt());
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			String passordHash = hash.getPasswordHashinHex();
			

			Deltager d = new Deltager(fornavn, etternavn, mobil, passordHash, kjonn);
			if (deltagerEAO.erMobilBrukt(d.getMobil())) {
				response.sendRedirect("paamelding?feilkode=1");
			} else {
				deltagerEAO.leggTilDeltager(d);
				request.setAttribute("registreringsskjema", rs);
				sesjon.setAttribute("deltager", d);
				response.sendRedirect("bekreftelse");
			}
		}
//	

	}

}
