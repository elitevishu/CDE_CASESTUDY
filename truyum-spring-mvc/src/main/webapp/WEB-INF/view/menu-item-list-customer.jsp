<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta charset="UTF-8">
<link rel="stylesheet" href="/Style/style.css">
</head>
<body>

	<header>
		<div id="header-container">
			<span>
				<h2 class="title">truYum</h2>
			</span> <span><img src="/images/Logo.png" id="logo"> <!--used for logo insertion-->
			</span> <span class="col-2">
				<ul id="my-list">
					<li><a href="/show-menu-list-customer" class="menu-link">Menu</a></li>
					<li><a href="/show-cart?userId=1" class="menu-link">Cart</a></li>
				</ul>
			</span>
		</div>
		<!--End of header Container-->
	</header>

	<div class="menu-heading">
		<h2>Menu Items</h2>
	</div>

	<div class="content">
		<table class="center">
			<tr>
				<td><b>Name</b></td>
				<td><b>Free Delivery</b></td>
				<td><b>Price</b></td>
				<td><b>Category</b></td>
				<td><b>Action</b></td>
			</tr>
			<tr>
				<c:forEach var="menuItem" items="${itemList}">
					<tr>
						<td>${menuItem.name}</td>
						<td><c:choose >
						<c:when test="${menuItem.freeDelivery}" >
						Yes</c:when>
						<c:otherwise>
						 No
						 </c:otherwise>
						 </c:choose>
						</td>
						<td>${menuItem.price}</td>
						<td>${menuItem.category}</td>



						<td><a href="/add-to-cart?id=${menuItem.id}&userId=1">Add to cart</a></td>
					</tr>
				</c:forEach>
				<c:if test="${addCartStatus}">
					<h3 class="success">Item added to Cart Successfully</h3>
				</c:if>
				
		</table>
	</div>
	<!--End of content div-->

	<footer>
		<h3>Copyright &copy; 2019</h3>
	</footer>

</body>
</html>