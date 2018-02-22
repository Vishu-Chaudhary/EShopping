<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@include file="HeadSection.jsp" %>
<body>
	<%@include file="Header.jsp"%>
	
	<section id="home">
		<h2>Home Page</h2>
		<h3>${msg}</h3>
		<div>
			<nav>
				<table>
					<tr>
						<td><a href="viewall">View All Products</a></td>
						<!-- <td> <a href="DeptView.jsp">View Departments</a></td>
						<td><a href="AddEmpFrm.jsp">Add Employee</a></td> -->
					</tr>
					
				</table>
				
			</nav>
		</div>

	</section>

	<%@include file="Footer.jsp"%>
</body>
</html>