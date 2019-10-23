package servlets;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import eao.DeltagerEAO;
import entities.Deltager;

@WebServlet(name="Deltagerliste", urlPatterns="/deltagere")
public class DeltagerlisteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	DeltagerEAO deltagerEAO;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesjon = request.getSession(false);
		
		
		if(sesjon == null) {
			response.sendRedirect("login");
		}else {
		
		Deltager innloggetDeltager = (Deltager) sesjon.getAttribute("innloggetDeltager");
		List<Deltager> deltagerliste = deltagerEAO.hentDeltagerliste();
		request.setAttribute("deltagerliste", deltagerliste);
		request.setAttribute("innloggetDeltager", innloggetDeltager);
		request.getRequestDispatcher("WEB-INF/jsp/deltagerliste.jsp").forward(request, response);
		}
	}



}
