package servlet;

import controller.UserController;
import utils.Dispatcher;
import utils.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet implementation class Action
 */
public class Action extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    private static final String JSP_PATH = "/WEB-INF/jsp/";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Action() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Initialisation de la session
		HttpSession session = request.getSession(true); // create if doesn't exist
		
		// Variables utiles
		String route = request.getPathInfo();	
		final UserController userController = new UserController();
		
		// Si la route est null, c'est que c'est la racine
		if (route == null) {
			route = "/";
		}	
		
		// get user is logged
		final Boolean userIsLogged = userController.isLogged(session);
		
		// Set user is connect in view
		request.setAttribute("user_connected", userIsLogged);
		
		// set user in view if user is logged
		if (userIsLogged) {
			request.setAttribute("user", userController.getCurrentUser(session));
		}
		
		// router
		switch (route) {
			case "/":
                request.setAttribute("informations", Service.getInformations());
				Dispatcher.forward(request, response, JSP_PATH + "index.jsp");
				break;
				
			case "/competitions":
				request.setAttribute("competitions", Service.getCompetitions());
				Dispatcher.forward(request, response, JSP_PATH + "competitions.jsp");
				break;
				
			case "/profile":
				request.setAttribute("grades", Service.getGrades());
				Dispatcher.forward(request, response, JSP_PATH + "profile.jsp");
				break;
				
			case "/informations":
				Dispatcher.forward(request, response, JSP_PATH + "informations.jsp");
				break;
				
			case "/login":
				userController.login(session, request);
				response.sendRedirect(request.getRequestURI().replace(request.getPathInfo(), "/profile"));
				break;
				
			case "/logout":
				userController.logout(session);
				response.sendRedirect(request.getRequestURI().replace(request.getPathInfo(), ""));
				break;
				
			case "/update":
				userController.updateUser(session, request);
				response.sendRedirect(request.getRequestURI().replace(request.getPathInfo(), "/profile"));
				break;
	
			default:
				response.sendError(404, "page not found");
				break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
