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
			<h1>Register</h1>
			<form action="register" method="post">
				<input type="text" name="txtuname" placeholder="Username"
					required="required" /> <input type="password" name="txtpwd"
					id="pwd" placeholder="Password" required="required" /> <input
					type="password" name="txctpwd" id="cpwd"
					placeholder="Confirm Password" required="required"
					onkeyup="checkMatch()" />
				<td><div id="passwarn"
						style="display: none; color: red; position: relative;">Password
						Doesn't Match</div></td>
				<button type="submit" class="btn btn-primary btn-block btn-large">Register
					Me</button>
			</form>
			<p>
				<span>Already Registered ?? <a href="/EShopping/"><button
							class="btn btn-success btn-large">Login Now</button></a></span>
			</p>
		</div>
	</section>

	<%@include file="Footer.jsp"%>


</body>
</html>
