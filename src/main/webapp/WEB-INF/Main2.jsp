<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@include file="HeadSection.jsp"%>
<body>
	<%@include file="Header.jsp"%>
	<c:if test="${sessionScope.login eq null }">
		<jsp:forward page="/">
			<jsp:param value="Please Login" name="msg" />
		</jsp:forward>
	</c:if>
	<div align="center">
		<h2 style="color: red">${param.msg }</h2>
	</div>
	<div id="container">
		<c:forEach items="${catlist }" var="cat">
			<div id="div1">
				<img src="img?pimg=${cat.catImg}" />
				<ul>
					<li>${cat.catName}</li>
				</ul>
			</div>
		</c:forEach>
	</div>




	<%@include file="Footer.jsp"%>
</body>
</html>