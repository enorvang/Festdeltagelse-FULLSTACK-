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
@WebServlet(name="LoggInn", urlPatterns="/login")
public class LoggInnServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashingUtil hashing = new HashingUtil("SHA-256");
	
	@EJB
	DeltagerEAO deltagerEAO;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		HttpSession sesjon = request.getSession(false);
		if (sesjon == null) {
			response.sendRedirect("login");
		}
		
		String mobil = request.getParameter("mobil");
		String passord = request.getParameter("passord");
		
		Deltager d = deltagerEAO.finnDeltagerMedMobil(mobil);
		
		try {
			if(hashing.validatePasswordWithSalt(passord, d.getPassordSalt(), d.getPassordHash())) {
				
				sesjon = InnloggingUtil.loggInnMedTimeout(request, 120);
				sesjon.setAttribute("innloggetDeltager", d);
				request.setAttribute("innloggetDeltager", d);
				response.sendRedirect("deltagere");
			}else {
				response.sendRedirect("login");
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
