package ro.oho.rest.login.facebook;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ro.oho.rest.facadeDataBase.UserFacade;
import ro.oho.rest.model.User;

@WebServlet("/fblogin")
public class ManageFacebookLogin extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private String code = "";

	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		code = req.getParameter("code");
		if (code == null || code.equals("")) {
			throw new RuntimeException("ERROR: Didn't get code parameter in callback.");
		}
		FBConnection fbConnection = new FBConnection();
		String accessToken = fbConnection.getAccessToken(code);

		FBGraph fbGraph = new FBGraph(accessToken);
		String graph = fbGraph.getFBGraph();
		User user = fbGraph.getGraphData(graph);
  
		UserFacade userFacade=new UserFacade();
		try {
			
			if(userFacade.loginUser(user.getIdUser())==false){//nu are cont facut
			userFacade.createUser(user);
			}else{
			HttpSession sessionUser = req.getSession();
			//iau gradul, il pun in user si fac update daca nu cumva a schimbat pe fb
			user.setGrad(userFacade.getUserById(user.getIdUser()).getGrad());
			userFacade.updateUser(user);
			sessionUser.setAttribute("user", user);
			}
			RequestDispatcher rd1 = req.getRequestDispatcher("jsp/first_page.jsp");
			rd1.forward(req, res);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
}
