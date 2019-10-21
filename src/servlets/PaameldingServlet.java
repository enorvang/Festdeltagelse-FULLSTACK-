package servlets;

import java.io.IOException;
import java.util.Hashtable;

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

		String feilmeldingFornavn = "", feilmeldingEtternavn = "", feilmeldingMobil = "", feilmeldingPassord = "",
				feilmeldingUlikePassord = "", feilmeldingKjonn = "";
		String feilmelding = "";
		String feilkode = request.getParameter("feilkode");
		
		@SuppressWarnings("unchecked")
		Hashtable<String, String> feilmeldinger = (Hashtable<String, String>) request.getAttribute("errors");
		if (feilkode != null && feilmeldinger != null && !feilmeldinger.isEmpty()) {
			if (feilkode.equals("1")) {
				feilmelding = "Skjemaet inneholder en eller flere feil...";
			}
			feilmeldingFornavn = feilmeldinger.get("fornavn");
			feilmeldingEtternavn = feilmeldinger.get("etternavn");
			feilmeldingMobil = feilmeldinger.get("mobil");
			feilmeldingPassord = feilmeldinger.get("passord");
			feilmeldingUlikePassord = feilmeldinger.get("ulikePassord");
			feilmeldingKjonn = feilmeldinger.get("kjonn");
		}
		System.out.println("Error table: " + feilmeldinger);
		System.out.println("Feilkode: " + feilkode);
		System.out.println("Feilmelding: " + feilmelding);

		//TODOBruke datastruktur for sending av parametere i stedet for enkeltvis? 
		request.setAttribute("feilmeldingFornavn", feilmeldingFornavn);
		request.setAttribute("feilmeldingEtternavn", feilmeldingEtternavn);
		request.setAttribute("feilmeldingMobil", feilmeldingMobil);
		request.setAttribute("feilmeldingPassord", feilmeldingPassord);
		request.setAttribute("feilmeldingUlikePassord", feilmeldingUlikePassord);
		request.setAttribute("feilmeldingKjonn", feilmeldingKjonn);
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
		Hashtable<String, String> errors = new Hashtable<String, String>();

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

		System.out.println(errors);

		
		//TODO MÅ OGSÅ SJEKKE AT MOBILNUMMERET IKKE FINNES I DATABASEN!
		
		if (!errors.isEmpty()) {
			request.setAttribute("errors", errors);
			response.sendRedirect("paamelding?feilkode=1");
		} else {
			HttpSession sesjon = InnloggingUtil.loggInnMedTimeout(request, 120);
			
			//TODO Registrere deltager i databasen.
			//generere passordsalt
			//hashe inntastet passord
			//
			
			Deltager deltager = new Deltager(fornavn, etternavn, mobil, passord, kjonn);
			
			sesjon.setAttribute("fornavn", fornavn);
			sesjon.setAttribute("etternavn", etternavn);
			sesjon.setAttribute("mobil", mobil);
			response.sendRedirect("bekreftelse");
		}
//	

	}

}
