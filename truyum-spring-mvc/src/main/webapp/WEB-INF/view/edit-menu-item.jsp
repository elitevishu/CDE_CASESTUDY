<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta charset="UTF-8">
<link rel="stylesheet" href="/Style/style.css">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src=https://code.jquery.com/jquery-1.12.4.js></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<!--  <script type="text/javascript" src="truYum-ui/js/script.js"></script>   -->
</head>
<body>

	<header>
		<div id="header-container">
			<span>
				<h2 class="title">truYum</h2>
			</span> <span><img src="/images/Logo.png" id="logo"> <!--used for logo insertion-->
			</span> <span class="col-2">
				<ul id="my-list">
					<li><a href="/show-menu-list-admin" class="menu-link">Menu</a></li>
				</ul>
			</span>
		</div>
		<!--end of header container-->
	</header>

	<div class="edit-menu-heading">
		<h2>Edit Menu Items</h2>
	</div>




	<form:form action="edit-menu-item" method="post"
		modelAttribute="menuItem">
		<form:hidden path="id" />
		<table class="edit-center">
			<tr>
				<td colspan="4"><label for="name">Name</label><br> <form:input
						size="100" class="name-text-size" path="name" /><br> <span
					style="color: red"><form:errors path="name" /> </span></td>
			</tr>
			<tr>
				<td><label for="price">Price(Rs.)</label><br> <form:input
						path="price" /> <span style="color: red"><br>
					<form:errors path="price" /></span><br></td>

				<td><label for="Active">Active</label><br> <form:radiobutton
						path="active" value="true" />Yes<form:radiobutton path="active"
						value="false" />No</td>


				<td><label for="dateOfLaunch">Date of Launch</label><br> <form:input
						path="dateOfLaunch" id="datepicker" /><br><span style="color: red"><form:errors
							path="dateOfLaunch" /></span></td>
				<td><label for="category">Category</label><br> <form:select
						path="category" items="${categoryList}" /><br></td>
			</tr>
			<tr>
				<td colspan="4"><form:checkbox path="freeDelivery" /><br>

					<label for="freeDelivery"> Free Delivery</label><br></td>
			</tr>
			<tr>
				<td><input type="submit" id="submit" name="submit" value="save">
				</td>
			</tr>

		</table>
	</form:form>

	<script>
		$(function() {
			$("#datepicker").datepicker();
		});
	</script>
	<footer>
		<h3>Copyright &copy; 2019</h3>
	</footer>

</body>
</html>