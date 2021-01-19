<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
			</span> <span><img src="/images/Logo.png" id="logo">
				<!--Used for logo--> </span> <span class="col-2">
				<ul id="my-list">
					<li><a href="/show-menu-list-admin" class="menu-link">Menu</a></li>
				</ul>
			</span>
		</div>
	</header>

	<div class="admin-menu-heading">
		<h2>Menu Items</h2>
	</div>

	<!--Contents of menu-->
	<div class="content">
		<table class="center">
			<tr>
				<td><b>Name</b></td>
				<td><b>Price</b></td>
				<td><b>Active</b></td>
				<td><b>Date of Launch</b></td>
				<td><b>Category</b></td>
				<td><b>Free Delivery</b></td>
				<td><b>Action</b></td>
			</tr>
			<c:forEach var="menuItem" items="${itemList}">
				<tr>
					<td>${menuItem.name}</td>
					<td>${menuItem.price}</td>
					<td><c:choose >
						<c:when test="${menuItem.active}" >
						Yes</c:when>
						<c:otherwise>
						 No
						 </c:otherwise>
						 </c:choose>
						</td>
				<%-- 	<td>${menuItem.active}</td> --%>
					<td>${menuItem.dateOfLaunch}</td>
					<td>${menuItem.category}</td>
					<td><c:choose >
						<c:when test="${menuItem.freeDelivery}" >
						Yes</c:when>
						<c:otherwise>
						 No
						 </c:otherwise>
						 </c:choose>
						</td>
					<%-- <td>${menuItem.freeDelivery}</td> --%>
					<td><a href="/show-edit-menu-item?id=${menuItem.id}">Edit</a></td>
				</tr>
			</c:forEach>

			
		</table>
	</div>
	<!--End of content -->

	<footer>
		<h3>Copyright &copy; 2019</h3>
	</footer>

</body>
</html>