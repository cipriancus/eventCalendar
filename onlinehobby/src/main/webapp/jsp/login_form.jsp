<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="ro.oho.rest.login.facebook.FBConnection"%>
<%
	FBConnection fbConnection = new FBConnection();
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="google-signin-client_id" content="522117879645-lktnvqpcploj11rcpttk50ctk6r8ilvp.apps.googleusercontent.com">
<title>Login Form</title>
<link href="css/index.css" rel="stylesheet">
</head>
<body>
	<div class="site-wrapper">
		<div class="site-wrapper-inner">
			<div class="cover-container">
				<div class="content">
					<div class="login-wrapper">
						<script>
							window.fbAsyncInit = function() {
								FB.init({
									appId : '1581560208841106',
									xfbml : true,
									version : 'v2.6'
								});
							};

							(function(d, s, id) {
								var js, fjs = d.getElementsByTagName(s)[0];
								if (d.getElementById(id)) {
									return;
								}
								js = d.createElement(s);
								js.id = id;
								js.src = "//connect.facebook.net/en_US/sdk.js";
								fjs.parentNode.insertBefore(js, fjs);
							}(document, 'script', 'facebook-jssdk'));
						</script>
						
						<script src="https://apis.google.com/js/platform.js" async defer></script>
					
						<script>
						function onSignIn(googleUser) {
							  var id_token = googleUser.getAuthResponse().id_token;
							  var xhr = new XMLHttpRequest();
							  xhr.open('POST', 'http://localhost:8017/onlinehobby/glogin');
							  xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
							  xhr.onload = function() {
							    console.log('Signed in as: ' + xhr.responseText);
							  };
							  xhr.send('idtoken=' + id_token);
							}
						</script>
					
						<div class="login-form">

							<div class="login-form-logo">
								<img width="150px" height="150px" src="images/logo.png"
									alt="red dot">
							</div>

							<div class="login-form-buttons-wrapper">
								<div class="login-form-buttons">
									<p>Login:</p>
									<br> <a href="<%=fbConnection.getFBAuthUrl()%>"
										class="btn btn-fb"> <i class="fa fa-facebook"></i>Sign &nbsp;Facebook
									</a>
									<div class="g-signin2" data-onsuccess="onSignIn"></div>
								</div>
								<div class="fb-like" data-share="true" data-width="450"
									data-show-faces="true"></div>
							</div>

						</div>

					</div>

				</div>
				<!-- CONTENT END -->

				<div class="footer">
					<div class="footer_wrapper">
						<div class="footer_content">
							<b>Broblem ? <a
								href="https://support.google.com/mail/bin/answer.py?hl=en&amp;answer=32050">Clear
									cookies !</a></b>

							<p>
								Follow us on: <a href="#"><img
									src="images/mini/facebook.png" alt="facebook" /></a> <a href="#"><img
									src="images/mini/twitter.png" alt="twitter" /></a>

							</p>
						</div>
					</div>
				</div>
				<!-- FOOTER END -->

			</div>
		</div>
	</div>
</body>
</html>
