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
<title>Oho</title>
</head >
<body onload="loadDoc(); loadMembers(); loadPosts();">
	<%
		HttpSession sessionUser = request.getSession(false);
		User user = (User) sessionUser.getAttribute("user");

		String userRequest = (String) request.getAttribute("urlString");

		StringBuilder userIDString = new StringBuilder();

		for (int iterator = userRequest.indexOf("usr/"); iterator < userRequest.length(); iterator++) {
			if (Character.isDigit(userRequest.charAt(iterator)) == true) {
				userIDString.append(userRequest.charAt(iterator));
			}
		}
		long userId = 0;

		if (userIDString.length() != 0) {
			userId = Long.parseLong(userIDString.toString());
		}

		User userVisited;
		UserFacade userFacade = new UserFacade();
		userVisited = userFacade.getUserById(userId);

		HobbyFacade hobbyFacade = new HobbyFacade();
		List<Hobby> allHobby = hobbyFacade.getAllUserHobby(userVisited.getIdUser());

		PostariFacade postariFacade = new PostariFacade();
		List<Postare> allPostari = postariFacade.getAllUserPostariForId(userVisited.getIdUser());//apelat doar pt acest usr
	%>
	<div class="site-wrapper">
		<div class="site-wrapper-inner">
			<div class="cover-container">
				<div class="content">
					<header>
						<div class="header-wrapper">
							<div class="leftMenuBar">
								<ul>

									<li>
									<li><a href="http://localhost:8017/onlinehobby/home"><img
											align="center" width="40px" height="40px"
											src="/onlinehobby/images/logo.png" /></a></li>
									</li>


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
						<div class="main-wrapper">
							<div class="profilePlusFeed">
								<div class="profilFeed">
									<div class="profile">
										<div class="profileContent">

											<div class="profilePictureVisitor">
												<a
													href="http://localhost:8017/onlinehobby/usr/<%out.print(userVisited.getIdUser());%>">
													<img src="<%out.print(userVisited.getPicture());%>"
													alt="Profile Picture" />
												</a>
											</div>



											<div class="hobbies">
												<div class="head">
													<a>Hobbies that the user follows:</a>
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

											<div class="hobbies">
												<div class="head">
													<a>User info's:</a>
												</div>
												<%
													out.print("<div class=\"hobby-content\"> Name:");
													out.print(userVisited.getNume());
													out.print("</div>");
													out.print("<div class=\"hobby-content\"> Second Name:");
													out.print(userVisited.getPrenume());
													out.print("</div>");
													out.print("<div class=\"hobby-content\"> Birthday: ");
													out.print(userVisited.getDataNasterii());
													out.print("</div>");
													out.print("<div class=\"hobby-content\"> Email: ");
													out.print(userVisited.getEmail());
													out.print("</div>");
												%>

											</div>

										</div>
									</div>
								</div>
								<div class="feed">
									<div class="feedContent">
										<div class="comments-container">

											<ul id="comments-list" class="comments-list">
												<%
													for (Postare iterator : allPostari) {
														User userPost = new User();
														userPost = userFacade.getUserById(iterator.getIdUser());

														out.print("<li><div class=\"comment-main-level\"><div class=\"comment-avatar\"><img" + " src=\""
																+ userPost.getPicture() + "\"" + "alt=\"Profile picture\">" + "</div>"
																+ "<div class=\"comment-box\">" + "<div class=\"comment-head\">"
																+ "<h6 class=\"comment-name by-author\">");
														out.print("<a href=\"http://localhost:8017/onlinehobby/usr/" + userPost.getIdUser() + "\">"
																+ userPost.getNume() + " " + userPost.getPrenume() + "</a>");
														out.print("</h6><span> At " + iterator.getDate_of_post()
																+ " in <a href=\"http://localhost:8017/onlinehobby/hobby/" + iterator.getIdHobby() + "\">"
																+ hobbyFacade.getHobbyNameForId(iterator.getIdHobby())
																+ "</a></span><i class=\"fa fa-reply\"></i></div>");
														out.print("<div class=\"comment-content\">" + iterator.getMesaj() + "</div></div></div></li>");
													}
												%>
											</ul>
										</div>
									</div>
								</div>
							</div>
							<div class="hobby_suggestions">
								<div id="hobbies"></div>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="/onlinehobby/js/comunicare.js"></script>

</body>
</html>
