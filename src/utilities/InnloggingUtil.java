package utilities;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class InnloggingUtil {

	public static boolean erGyldigPassord(String pass, String riktigPass) {
		return pass != null && pass.contentEquals(riktigPass);
	}

	public static boolean erInnlogget(HttpServletRequest req) {
		HttpSession sesjon = req.getSession(false);
		return (sesjon != null) && (sesjon.getAttribute("innlogget") != null);
	}

	public static HttpSession loggInnMedTimeout(HttpServletRequest req, int sekunder) {
		loggUt(req);
		HttpSession sesjon = req.getSession(true);
		sesjon.setMaxInactiveInterval(sekunder);
		sesjon.setAttribute("innlogget", "JEPP");
		return sesjon;
	}

	public static void loggUt(HttpServletRequest req) {
		HttpSession sesjon = req.getSession(false);
		if (sesjon != null) {
			sesjon.invalidate();
		}
	}
}
