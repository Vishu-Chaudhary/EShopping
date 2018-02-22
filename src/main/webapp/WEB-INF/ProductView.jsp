<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@include file="HeadSection.jsp"%>
<body>
	<%@include file="Header.jsp"%>
	<section>
		<h2>Product Details</h2>
		<div id='catlog'>
			<table>
				<tr>
					<th>IMAGE</th>
					<th>PID</th>
					<th>PRODUCT NAME</th>
					<th>PRICE</th>
					<th colspan="2">CATEGORY</th>
				</tr>

				<c:forEach items="${elist}" var="prod">
					<tr>
						<td><img src="photos/${emp.img}"></td>
						<td>${prod.pid}</td>
						<td>${prod.pName}</td>
						<td>${prod.price}</td>
						<td>${prod.catId}</td>
						<td><input type="button" value="edit"  onclick="display(${prod.pid})"/>
							<div id="${prod.pid}">
								<article>Update Product Price</article>

								<form action="edit">
									<table>
										<tr>
											<td>Product Id</td>
											<td>${prod.pid}</td>
										</tr>
										<tr>
											<td>Product Name</td>
											<td>${prod.pName}</td>
										</tr>
										<tr>
											<td>Price</td>
											<td>
											<input type="hidden" name="txtpg" value="${currpg}"/>
											<input type="hidden" name="txteid" value="${prod.pid}"/>
											<input type="text" name="txtsal"/></td>
										</tr>
										<tr>
											<td colspan="2" align="center"><input type="submit" value="submit"/></td>
										</tr>
									</table>
								</form>
							</div>
						</td>
					</tr>
				</c:forEach>
			</table>
			<div align="center">
			<c:if test="${currpg gt 1}">
				<a href="viewall?pgno=${currpg-1}">prev</a>
			</c:if>
			<c:if test="${currpg lt totpgs}">
				<a href="viewall?pgno=${currpg+1}">next</a>
			</c:if>
			</div>
		</div>
	</section>
	<%@include file="Footer.jsp"%>
</body>
</html>