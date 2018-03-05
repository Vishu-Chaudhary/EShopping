<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<%@include file="HeadSection.jsp"%>
<body>
	<%@include file="Header.jsp"%>
	<c:if test="${sessionScope.login eq null}">
		<jsp:forward page="/">
			<jsp:param value="Please Login" name="msg" />
		</jsp:forward>
	</c:if>
	<div id="catlog">
		<div align="center" style="color: red">${msg}</div>
		<div align="center">
			<div class="container" style="min-height: 300px">
				<div class="row">
					<c:forEach items="${prodlist }" var="prod" varStatus="st">
						<div class="col-sm-4">
							<div class="panel panel-default"
								style="background-color: transparent;">

								<div class="panel-heading"
									style="background-color: transparent;">
									<input type="image" src="img?pimg=${prod.prodImg}"
										height="200px" onclick="display('${st.index}')" value="view" />


									<div class="review">
										<a href="showreviews">Reviews</a>
									</div>
								</div>
								<c:if test="${sessionScope.login.role eq 'admin' }">
									<div id="${st.index}" class="panel-body" align="center"
										style="text-align: center; text-decoration: none; display: none; background-color: white">
										<article>Update Product Price</article>
										<form action="edit" method="post">

											<table id="editprod">
												<tr>
													<td>Product Id</td>
													<td>${prod.pid}</td>
												</tr>
												<tr>
													<td>Product Name</td>
													<td>${prod.pName}</td>
												</tr>
												<tr>
													<td>Current price</td>
													<td>${prod.price}</td>
												</tr>
												<tr>
													<td>New Price</td>
													<td><input type="hidden" name="txtpg"
														value="${currpg}" /> <input type="hidden" name="txtpid"
														value="${prod.pid}" /> <input type="text" name="txtprice"
														required="required" /></td>
												</tr>
												<tr>
													<td><input type="submit"
														class="btn btn-primary btn-large" value="edit" /></td>
													<td><input type="button"
														class="btn btn-danger btn-large" value="cancel"
														onclick="hide(${st.index})" /></td>
												</tr>
											</table>
										</form>
									</div>
								</c:if>


								<c:if test="${sessionScope.login.role eq 'user' }">
									<div id="${st.index}" class="panel-body"
										style="text-align: center; text-decoration: none; display: none; background-color: white">
										<article>Product Details</article>
										<table id="editprod">
											<tr>
												<td>Product Name</td>
												<td>${prod.pName}</td>
											</tr>
											<tr>
												<td>Price</td>
												<td>${prod.price}</td>
											</tr>
											<tr>
												<td><a class="btn btn-primary btn-large"
													href="addtocart?pid=${prod.pid}&pgurl=${pgurl}">Add To
														Cart</a></td>
												<td><input type="button"
													class="btn btn-danger btn-large" value="cancel"
													onclick="hide(${st.index})" /></td>
											</tr>
										</table>
									</div>
								</c:if>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
		<div align="center">
			<c:if test="${currpg gt 1}">
				<a href="${pgurl}pgno=${currpg-1}" class="btn btn-primary btn-large">prev</a>
			</c:if>
			<c:if test="${currpg lt totpgs}">
				<a href="${pgurl}pgno=${currpg+1}" class="btn btn-primary btn-large">next</a>
			</c:if>
		</div>
	</div>
	<%@include file="Footer.jsp"%>
</body>
</html>