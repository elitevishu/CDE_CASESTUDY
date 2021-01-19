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
			</span> <span><img src="/images/Logo.png" id="logo"> <!--logo insertion in header-->
			</span> <span class="col-2">
				<ul id="my-list">
					<li><a href="/show-menu-list-customer" class="menu-link">Menu</a></li>
					<li><a href="/show-cart" class="menu-link">Cart</a></li>
				</ul>
			</span>
		</div>
	</header>

	<div class="cart-menu-heading">
		<h2>Menu Items</h2>
	</div>

	<div class="content">
		<table class="center">
			<tr>
				<td><b>Name</b></td>
				<td><b>Free Delivery</b></td>
				<td><b>Price</b></td>
			</tr>


			<c:if test="${removeCartItemStatus}">
				<h3 class="success">Item removed from Cart successfully</h3>
			</c:if>
			<tr>
				<c:forEach var="cartItem" items="${cart.menuItemList}">
					<tr>
						<td>${cartItem.name}</td>
						<td><c:choose>
								<c:when test="${cartItem.freeDelivery}">
						Yes</c:when>
								<c:otherwise>
						 No
						 </c:otherwise>
							</c:choose></td>
						<td>${cartItem.price}</td>



						<td><a href="/remove-cart?id=${cartItem.id}&userId=1">delete</a></td>
					</tr>
				</c:forEach>
			<tr>
				<td></td>
				<td><b>Total</b></td>
				<td><b>Rs. ${cart.total}</b></td>
			</tr>
		</table>
	</div>
	<!--End of content div-->

	<footer>
		<h3>Copyright &copy; 2019</h3>
	</footer>

</body>
</html>