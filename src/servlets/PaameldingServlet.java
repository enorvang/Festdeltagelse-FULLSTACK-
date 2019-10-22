package servlets;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Deltager;
import utilities.InnloggingUtil;
import utilities.Validering;

@WebServlet(name = "Påmelding", urlPatterns = "/paamelding")
public class PaameldingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		String feilmelding = "";
		String feilkode = request.getParameter("feilkode");

		@SuppressWarnings("unchecked")
		HashMap<String, String> feilmeldinger = (HashMap<String, String>) request.getAttribute("errors");
		if (feilkode != null && feilmeldinger != null && !feilmeldinger.isEmpty()) {
			if (feilkode.equals("1")) {
				feilmelding = "Skjemaet inneholder en eller flere feil...";
			}
		}

		System.out.println("Feilmeldinger(GET): " + feilmeldinger);
		System.out.println("Feilkode: " + feilkode);
		System.out.println("Feilmelding: " + feilmelding);
		request.setAttribute("errors", feilmeldinger);

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
		HashMap<String, String> errors = new HashMap<String, String>();

		if (!Validering.erGyldigFornavn(fornavn)) {
			errors.put("fornavn", "Ugyldig fornavn");
		}
		if (!Validering.erGyldigEtternavn(etternavn)) {
			errors.put("etternavn", "Ugyldig etternavn");
		}
		if (!Validering.erGyldigMobil(mobil)) {
			errors.put("mobil", "Ugyldig mobilnummer");
		}
		if (!Validering.erGyldigPassord(passord)) {
			errors.put("passord", "Ugyldig passord");
		}
		if (!Validering.erLikePassord(passord, passordRepetert)) {
			errors.put("ulikePassord", "Passordene må være like");
		}
		if (!Validering.erGyldigKjonn(kjonn)) {
			errors.put("kjonn", "Du må oppgi kjønn");
		}

		System.out.println("Feilmeldinger(POST): " + errors);

		// TODO MÅ OGSÅ SJEKKE AT MOBILNUMMERET IKKE FINNES I DATABASEN!

		if (!errors.isEmpty()) {
			request.setAttribute("errors", errors);
			response.sendRedirect("paamelding?feilkode=1");
		} else {
			HttpSession sesjon = InnloggingUtil.loggInnMedTimeout(request, 120);

			// TODO Registrere deltager i databasen.
			// generere passordsalt
			// hashe inntastet passord
			//

//			Deltager deltager = new Deltager(fornavn, etternavn, mobil, passord, kjonn);

			sesjon.setAttribute("fornavn", fornavn);
			sesjon.setAttribute("etternavn", etternavn);
			sesjon.setAttribute("mobil", mobil);
			response.sendRedirect("bekreftelse");
		}
//	

	}

}
