package ro.oho.rest.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ro.oho.rest.facadeDataBase.HobbyFacade;
import ro.oho.rest.model.User;

@WebServlet("/AddUser")
public class AddUserToHobbyController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			HttpSession sessionUser = request.getSession(false);

			User user = null;
			if (sessionUser != null) {
				user = (User) sessionUser.getAttribute("user");
			}

			if (user == null) {
				RequestDispatcher rd = request.getRequestDispatcher("jsp/login_form.jsp");
				rd.forward(request, response);
			} else {
				if (request.getParameter("hobby") != null) {
					String hobbyRequest = (String) request.getParameter("hobby");

					StringBuilder hobbyIDString = new StringBuilder();

					for (int iterator = hobbyRequest.indexOf("hobby/"); iterator < hobbyRequest.length(); iterator++) {
						if (Character.isDigit(hobbyRequest.charAt(iterator)) == true) {
							hobbyIDString.append(hobbyRequest.charAt(iterator));
						}
					}
					int hobbyId = 0;

					if (hobbyIDString.length() != 0) {
						hobbyId = Integer.parseInt(hobbyIDString.toString());
						if (new HobbyFacade().addUserToHobby(user, hobbyId) == true) {
							out.println("true");
							out.flush();
						} else {
							// ar trebui sa il anuntam ca a esuat
							out.println("false");
							out.flush();
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}
}
