package ro.oho.rest.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ro.oho.rest.facadeDataBase.HobbyFacade;
import ro.oho.rest.model.User;

/**
 * Servlet pentru identificare unui hobby ex /hobby/56684648 da hobby ul cu nr
 * acesta
 */
@WebServlet("/hobby/*")
public class GetHobbyId extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			HttpSession sessionUser = request.getSession(false);

			if (sessionUser != null) {
				if (sessionUser.getAttribute("user") != null) {
					HobbyFacade hobbyFacade = new HobbyFacade();
					User user = (User) sessionUser.getAttribute("user");

					StringBuilder hobbyIDString = new StringBuilder();
					String hobbyRequest = (String) request.getRequestURI();
					for (int iterator = hobbyRequest.indexOf("hobby/"); iterator < hobbyRequest.length(); iterator++) {
						if (Character.isDigit(hobbyRequest.charAt(iterator)) == true) {
							hobbyIDString.append(hobbyRequest.charAt(iterator));
						}
					}
					int hobbyId = 0;

					if (hobbyIDString.length() != 0) {
						hobbyId = Integer.parseInt(hobbyIDString.toString());
						if (hobbyFacade.getAllUserHobby(user.getIdUser()).contains(hobbyFacade.getHobbyForId(hobbyId)) == false) {
							RequestDispatcher rd1 = request.getRequestDispatcher("/jsp/hobby.jsp");
							request.setAttribute("urlString", request.getRequestURI());
							rd1.forward(request, response);
						} else {
							request.setAttribute("urlString", request.getRequestURI());
							RequestDispatcher rd1 = request.getRequestDispatcher("/jsp/comunicare.jsp");
							rd1.forward(request, response);																																						// comunicare
						}
					} else {
						RequestDispatcher rd1 = request.getRequestDispatcher("/jsp/first_page.jsp");
						request.setAttribute("urlString", request.getRequestURI());
					}
				} else {
					response.setStatus(HttpServletResponse.SC_FORBIDDEN);
				}
			}
		} finally {
			out.close();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}
}
