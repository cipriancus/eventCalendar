package ro.oho.rest.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ro.oho.rest.facadeDataBase.HobbyFacade;
import ro.oho.rest.facadeDataBase.PostariFacade;
import ro.oho.rest.model.Hobby;
import ro.oho.rest.model.User;
import ro.oho.rest.utils.XssParse;

@WebServlet("/iLikeHobby")
public class ILikeHobby extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			HttpSession sessionUser = request.getSession(false);

			if (sessionUser != null) {
				if (sessionUser.getAttribute("user") != null) {

					String iLikeHobby=XssParse.parseXSS( request.getHeader("iLikeHobby"));
					String reasons= XssParse.parseXSS(request.getHeader("reasons"));
					
					List<String> allHobbies=new HobbyFacade().getAllHobbies();
					User user=(User) request.getSession(false).getAttribute("user");
					
					if(allHobbies.contains(iLikeHobby)==true){
						boolean flagContaing=false;
						for(Hobby iterator:new HobbyFacade().getAllUserHobby(user.getIdUser())){
							if(iterator.getHobbyName().equals(iLikeHobby)==true){
								flagContaing=true;
								break;
							}
						}
						Hobby iLikeHobbyObject=new HobbyFacade().getHobbyForId(new HobbyFacade().getHobbyIdForName(iLikeHobby));

						if(flagContaing==true){//are deja hobby ul
							if(new PostariFacade().postInHobby(user, iLikeHobby,reasons)==true){
								//succes
								String message="<div class=\"comment-main-level\"><div class=\"comment-avatar\"><img" + " src=\""
										+ user.getPicture() + "\"" + "alt=\"Profile picture\">" + "</div>"
										+ "<div class=\"comment-box\">" + "<div class=\"comment-head\">"
										+ "<h6 class=\"comment-name by-author\">"
								        +"<a href=\"http://localhost:8017/onlinehobby/usr/" + user.getIdUser() + "\">"
										+ user.getNume() + " " + user.getPrenume() + "</a>"
								        +"</h6><span> At " + LocalDate.now().format(DateTimeFormatter.ISO_DATE)
								 		+ " in <a href=\"http://localhost:8017/onlinehobby/hobby/" + iLikeHobbyObject.getIdHobby() + "\">"
										+ iLikeHobbyObject.getHobbyName()
										+ "</a></span><i class=\"fa fa-reply\"></i></div>"
								        +"<div class=\"comment-content\">" + reasons + "</div></div></div>";	
								out.print("true without add hobby");
								response.setHeader("message", message);
							}else{
								//esec
								out.print("false");
							}
						}else{//nu il are
						
							if(new HobbyFacade().addUserToHobby(user, iLikeHobbyObject.getIdHobby())==true){
								//succes adaugare hobby
								//ar trebui sa ii dea si hobby ul ca sa faca append la hobby-urile sale
								if(new PostariFacade().postInHobby(user, iLikeHobby, reasons)){
									//succes postare
									String hobby="<div class=\"hobby-content\"><a href=\"http://localhost:8017/onlinehobby/hobby/"+iLikeHobbyObject.getIdHobby()+"\">"+iLikeHobbyObject.getHobbyName()+"</a></div>";
									String message="<li><div class=\"comment-main-level\"><div class=\"comment-avatar\"><img" + " src=\""
													+ user.getPicture() + "\"" + "alt=\"Profile picture\">" + "</div>"
													+ "<div class=\"comment-box\">" + "<div class=\"comment-head\">"
													+ "<h6 class=\"comment-name by-author\">"
											        +"<a href=\"http://localhost:8017/onlinehobby/usr/" + user.getIdUser() + "\">"
													+ user.getNume() + " " + user.getPrenume() + "</a>"
											        +"</h6><span> At " + LocalDate.now().format(DateTimeFormatter.ISO_DATE)
											 		+ " in <a href=\"http://localhost:8017/onlinehobby/hobby/" + iLikeHobbyObject.getIdHobby() + "\">"
													+ iLikeHobbyObject.getHobbyName()
													+ "</a></span><i class=\"fa fa-reply\"></i></div>"
											        +"<div class=\"comment-content\">" + reasons + "</div></div></div></li>";		
									response.setHeader("hobby", hobby);
									response.setHeader("message", message);
									out.print("true with add hobby");
								}else{
									out.print("false");
								}
							}else{
								//esec
								out.print("false");
							}
						}
					}else{//nu exista hobby-ul
						
					}
					
					out.flush();
				} else {
					response.setStatus(HttpServletResponse.SC_FORBIDDEN);
				}
			}
		} catch (SQLException e) {
			out.print("false");
			e.printStackTrace();
		}
		
		finally {
			out.close();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
			processRequest(request, response);
	}
}
