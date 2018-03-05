<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<header>
	<h2>
		<span class="one">YourKart</span> <span class="two">${initParam.subtitle}</span>
	</h2>
	<div align="right" style="font-weight: bolder; color: white;">
	<c:if test="${sessionScope.login.role eq 'user' }">
		<a href="#"><button
			class="btn btn-primary btn-large"><i class="material-icons" style="position:relative;top:5px">shopping_cart</i> ${cartcount}</button>
		</a> 
		</c:if>
		<span style="color: black">Welcome ! ${login.userName }</span>
		<c:if test="${sessionScope.login  eq null}">
			<a href="/EShopping/"><button class="btn btn-success btn-large">Login</button></a>
		</c:if>
		<c:if test="${sessionScope.login  ne null}">
			<a href="logout"><button class="btn btn-danger btn-large">Logout</button></a>
		</c:if>
	</div>
	<hr />
	<div>
		<nav>
			<ul>
				<li><a href="main" class="">Home</a></li>
				<li><a href="viewall" class="">View All Products</a></li>
				<c:if test="${sessionScope.login.role eq 'admin' }">
					<li><a href="addprodfrm">Add Product</a></li>
					<li><a href="addcatfrm">Add Category</a></li>
				</c:if>


			</ul>
		</nav>
	</div>
	<br>
	<hr>
</header>
