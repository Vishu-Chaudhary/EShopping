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
	<div align="center">
		<div class="container" style="min-height: 300px">
			<div class="row" align="center">
				<c:forEach items="${catlist }" var="cat">
					<div class="col-sm-4">
						<a href="viewbycategory?txtcat=${cat.catId }">
							<div class="panel panel-default cat-background">
								<div class="panel-heading"
									style="background-color: transparent;">
									<input type="image" src="img?pimg=${cat.catImg}"
										height="200px" />
								</div>
								<div class="panel-body"
									style="text-align: center; text-decoration: none">
									${cat.catName}</div>

							</div>
						</a>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>


	<%@include file="Footer.jsp"%>
</body>
</html>