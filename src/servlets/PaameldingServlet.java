package servlets;

import java.io.IOException;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utilities.InnloggingUtil;
import utilities.Validering;

@WebServlet(name = "Påmelding", urlPatterns = "/paamelding")
public class PaameldingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Hashtable<String, String> errors;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String feilmeldingFornavn = "", feilmeldingEtternavn = "", feilmeldingMobil = "", feilmeldingPassord = "",
				feilmeldingUlikePassord = "", feilmeldingKjonn = "";
		String feilkode = request.getParameter("feilkode");
//		if (feilkode != null) {
//			if (feilkode.contentEquals("1")) {
//				feilmeldingFornavn = "Ugyldig fornavn";
//			}
//			if (feilkode.contentEquals("2")) {
//				feilmeldingEtternavn = "Ugyldig etternavn";
//			}
//			if (feilkode.contentEquals("3")) {
//				feilmeldingMobil = "Ugyldig mobilnummer";
//			}
//			if (feilkode.contentEquals("4")) {
//				feilmeldingPassord = "Ugyldig passord";
//			}
//			if(feilkode.contentEquals("5")) {
//				feilmeldingUlikePassord = "Passordene må være like";
//			}
//			if(feilkode.contentEquals("6")) {
//				feilmeldingKjonn = "Du må oppgi kjønn";
//			}
//		}
		@SuppressWarnings("unchecked")
		Hashtable<String, String> errors = (Hashtable<String, String>) request.getAttribute("errors");
		if (feilkode != null && errors != null && !errors.isEmpty()) {
			feilmeldingFornavn = errors.get("fornavn");
			feilmeldingEtternavn = errors.get("etternavn");
			feilmeldingMobil = errors.get("mobil");
			feilmeldingPassord = errors.get("passord");
			feilmeldingUlikePassord = errors.get("ulikePassord");
			feilmeldingKjonn = errors.get("kjonn");
		}
		
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
		errors = new Hashtable<String, String>();
		

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
		if (errors.isEmpty()) {
			HttpSession sesjon = InnloggingUtil.loggInnMedTimeout(request, 120);
			sesjon.setAttribute("fornavn", fornavn);
			sesjon.setAttribute("etternavn", etternavn);
			sesjon.setAttribute("mobil", mobil);
			response.sendRedirect("bekreftelse");
		} else {
			request.setAttribute("errors", errors);
			response.sendRedirect("paamelding?feilkode=1");

		}

//		if (!Validering.erGyldigFornavn(fornavn)) {
//			
//			response.sendRedirect("paamelding?feilkode=1");
//		}else {
//			HttpSession sesjon = InnloggingUtil.loggInnMedTimeout(request, 120);
//			
//			sesjon.setAttribute("fornavn", fornavn);
//			response.sendRedirect("bekreftelse");
//		}

	}

}
