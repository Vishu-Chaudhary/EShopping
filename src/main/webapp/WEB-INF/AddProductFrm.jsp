<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html >
<html>
<%@ include file="HeadSection.jsp"%>
<body>
	<%@ include file="Header.jsp"%>
	<c:if test="${sessionScope.login eq null }">
		<jsp:forward page="/">
			<jsp:param value="Please Login" name="msg" />
		</jsp:forward>
	</c:if>
	<c:if test="${sessionScope.login.role eq 'user' }">
		<jsp:forward page="/main">
			<jsp:param value="Sorry, you are not authorize to view this page"
				name="msg" />
		</jsp:forward>
	</c:if>
	<section>
		<div align="center" style="color: red">${msg}</div>
		<h2>Add Product</h2>
		<div>
			<form:form action="addproduct" modelAttribute="pbean" method="post"
				enctype="multipart/form-data">
				<table cellpadding="10" style="margin: 0 auto;">
					<tr>
						<td>Product Id</td>
						<td><form:input path="pid" value="${idx}" readonly="true" /></td>
						<td><form:errors path="pid" element="div"
								cssStyle="color:red" /></td>
					</tr>
					<tr>
						<td>Product Name</td>
						<td><form:input path="pName" /></td>
						<td><form:errors path="pName" element="div"
								cssStyle="color:red" /></td>
					</tr>
					<tr>
						<td>Brand</td>
						<td><form:input path="brand" /></td>
						<td><form:errors path="brand" element="div"
								cssStyle="color:red" /></td>
					</tr>
					<tr>
						<td>Price</td>
						<td><form:input path="price" /></td>
						<td><form:errors path="price" element="div"
								cssStyle="color:red" /></td>
					</tr>
					<tr>
						<td>Category</td>
						<td><form:select items="${cmap}" path="cat.catId" /></td>
					</tr>
					<tr>
						<td>Choose Image To Upload</td>
						<td><input type="file" name="fname"></td>
					</tr>
					<tr>
						<td colspan="2" align="left"><input type="submit" value="Add"></td>
						<td></td>
					</tr>

				</table>
			</form:form>
		</div>
	</section>
	<%@ include file="Footer.jsp"%>
</body>
</html>