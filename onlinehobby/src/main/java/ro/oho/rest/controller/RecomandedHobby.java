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
import ro.oho.rest.model.Hobby;
import ro.oho.rest.model.User;

@WebServlet(urlPatterns = {"/RecomandedHobby"})
public class RecomandedHobby extends HttpServlet
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	void requestProc(HttpServletRequest request, HttpServletResponse response){
		try
		{
			if(request.getSession().getAttribute("user")!=null){
			int page=Integer.parseInt(request.getParameter("page"));
			User user=(User) request.getSession().getAttribute("user");
			
			List<Hobby> hobbies=new HobbyFacade().getRecommendation(page,user);
			
			String html="<button class='button_next' onclick='lessFunction(); return false;' id='buttonLess'> Less </button>";
	
			/*
			 * 
			 */
			for(Hobby iterator:hobbies){
				html+="<div class=\"hobbiesComunicare\">";
				html+="<div class=\"head\"><a href=\"http://localhost:8017/onlinehobby/hobby/"+iterator.getIdHobby()+"\">" +iterator.getHobbyName() + "</a></div>";
				html+="<div class=\"hobby-content\"><a>" + iterator.getDescription() + "</a></div>";
			}
			html+="<button class='button_next' id='btnNextHobby' onclick='moreFunction(); return false;'> MORE </button>";
					
			PrintWriter out=response.getWriter();
			out.println(html);
			out.flush();
			}else{
				response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			}
		}
		catch(IOException ioe)
		{
			System.out.println(ioe.getMessage());
		}
		catch(SQLException sqle)
		{
			System.out.println(sqle.getMessage());
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        requestProc(request,response);
    }
	
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
	}
}