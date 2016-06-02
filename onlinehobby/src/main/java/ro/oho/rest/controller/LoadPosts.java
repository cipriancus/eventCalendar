package ro.oho.rest.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ro.oho.rest.facadeDataBase.PostariFacade;
import ro.oho.rest.facadeDataBase.UserFacade;
import ro.oho.rest.model.Postare;
import ro.oho.rest.model.User;

@WebServlet(urlPatterns = { "/LoadPosts" })
public class LoadPosts extends HttpServlet {

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

				PostariFacade postariFacade = new PostariFacade();
				List<Postare> allPostari = postariFacade.getAllHobbyPost((hobbyId));

				String html = "";
				for (Postare iterator : allPostari) {

					User user = new UserFacade().getUserById(iterator.getIdUser());
					html += "<p><h4 style=\"font-size:18px;color:black;margin-left:5%;\">"+"<img src=\""+user.getPicture()+"\" alt=\"user photo\"/> "+"<a href=\"http://localhost:8017/onlinehobby/usr/"+user.getIdUser()+ "\">"+ user.getNume() + " " + user.getPrenume() + "</a>: " + iterator.getMesaj()
							+ "</h4></p>";
				}
				PrintWriter out = response.getWriter();
				out.println(html);
				out.flush();
			}
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
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
