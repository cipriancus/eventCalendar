<%@page import="ro.oho.rest.facadeDataBase.PostariFacade"%>
<%@page import="ro.oho.rest.facadeDataBase.HobbyFacade"%>
<%@page import="java.util.ArrayList"%>
<%@page import="ro.oho.rest.facadeDataBase.UserFacade"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="ro.oho.rest.model.User"%>
<%@page import="ro.oho.rest.model.Hobby"%>
<%@page import="ro.oho.rest.model.Postare"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.SQLException"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="/onlinehobby/css/profile.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" href="/onlinehobby/images/logo.png">
<title>OHO- Chat page</title>
</head>
<body onload="loadDoc(); loadMembers(); loadPosts();">
	<%
		HttpSession sessionUser = request.getSession(false);
		User user = (User) sessionUser.getAttribute("user");
		HobbyFacade hobbyFacade = new HobbyFacade();
		List<Hobby> allHobby = hobbyFacade.getAllUserHobby(user.getIdUser());
	%>
	<div class="site-wrapper">
		<div class="site-wrapper-inner">
			<div class="cover-container">
				<div class="content">
					<header>
						<div class="header-wrapper">
							<div class="leftMenuBar">
								<ul>

									<li><a href="http://localhost:8017/onlinehobby/home"><img
											align="center" width="40px" height="40px"
											src="/onlinehobby/images/logo.png" /></a></li>


									<li><a
										href="http://localhost:8017/onlinehobby/usr/<%out.print(user.getIdUser());%>">
											<img align="center" src="<%out.print(user.getPicture());%>"
											alt="Profile Picture" /> <%
 	out.print(user.getNume() + " " + user.getPrenume());
 %>
									</a></li>
								</ul>
							</div>

							<div class="rightMenuBar">
								<ul>
									<li id="menuItem"><a
										href="http://localhost:8017/onlinehobby/home">Home</a></li>

									<li id="menuItem"><a href="#">Hobbies</a></li>
									<li id="menuItem"><a
										href="http://localhost:8017/onlinehobby/usr/<%out.print(user.getIdUser());%>">Profile</a></li>
									<%
										if (user.getGrad() == 1) {
											out.print("<li id=\"menuItem\"><a href=\"http://localhost:8017/onlinehobby/admin\">Admin</a></li>");
										}
									%>
									<li id="menuItem"><a href="logout">Logout</a></li>

								</ul>
							</div>

						</div>
					</header>
					<div class="main">
						<div id="bigone">
							<div id="leftList">
								<ul id="hobbies" class="specialul">
								</ul>
							</div>
							<div class="grupCentru">
								<div id="editInput">

									<div id="inputSend">
										<form action="" onsubmit="return false;" id="formText"
											name="formSendMessage">

											<div id="inputText">
												<textarea name="nume" placeholder="Write me..." rows="4"
													maxlength="500" id="textToAppend"
													onkeyup="enterPressed(this)"></textarea>
											</div>

											<div id="buttonDiv">
												<button id="sendBtn" class="sendButton"
													onclick="postMessage()">SEND</button>
											</div>
										</form>
									</div>

									<div>
										<h5 class="h3styl">Font-size:</h5>
										<input type="number" min="15" max="40" value="15"
											id="textSize" />
									</div>
								</div>
								<div class="obx" id="scroly">
									<%
										if (application.getAttribute("nume") != null) {
									%>
									<%=application.getAttribute("nume")%>
									<%
										}
									%>

								</div>

							</div>
							<div class="grupRight">
								<div class="hobbiesComunicare">
									<div class="head">
										<a>Hobby members:</a>
									</div>
									<div id="members"></div>
								</div>

								<div class="hobbiesComunicare">
									<div class="head">
										<a>Hobbies you follow</a>
									</div>
									<%
										for (Hobby iterator : allHobby) {
											out.print("<div class=\"hobby-content\"><a href=\"http://localhost:8017/onlinehobby/hobby/");
											out.print(iterator.getIdHobby());
											out.print("\">");
											out.print(iterator.getHobbyName());
											out.print("</a></div>");
										}
									%>

								</div>

							</div>
						</div>

						<script type="text/javascript" src="/onlinehobby/js/comunicare.js"></script>

					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
