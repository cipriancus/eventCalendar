package ro.oho.rest.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ro.oho.rest.facadeDataBase.HobbyFacade;
import ro.oho.rest.model.User;

@WebServlet(urlPatterns = { "/HobbysUsers" })
public class GetHobbyMembers extends HttpServlet {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	void requestProc(HttpServletRequest request, HttpServletResponse response) {

		try {
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
				List<User> allUsers = new HobbyFacade().getAllUsersForHobby(hobbyId);
				String html = "";

				for (User user : allUsers) {
					html += "<div class=\"hobby-content\"><a href=\"http://localhost:8017/onlinehobby/usr/";
					html += user.getIdUser();
					html += "\">";
					html +=	"<img src=\""+user.getPicture()+"\" alt=\"user photo\"/> ";
					html += user.getPrenume() + " " + user.getNume();
					html += "</a></div>";
				}

				PrintWriter out = response.getWriter();
				out.println(html);
				out.flush();
			}
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		requestProc(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		requestProc(request, response);
	}
}
