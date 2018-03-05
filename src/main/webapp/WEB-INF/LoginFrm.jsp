<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<%@include file="HeadSection.jsp"%>
<body>
	<header>
		<h2>
			<span class="one">YourKart</span> <span class="two">${initParam.subtitle}</span>
		</h2>
	</header>
	<section>
		<div align="center" style="color: red">${param.msg }</div>
		<div class="login">
			<h1>Login</h1>
			<form action="login" method="post">
				<input type="text" name="txtuname" placeholder="Username"
					required="required" /> <input type="password" name="txtpwd"
					placeholder="Password" required="required" />
				<button type="submit" class="btn btn-primary btn-block btn-large">Let
					me in.</button>
			</form>
			<p>
				<span>Not Registered Yet??<a href="getregister"><button
							class="btn btn-success btn-large">Register Now</button></a></span>
			</p>
		</div>
	</section>

	<%@include file="Footer.jsp"%>


</body>
</html>