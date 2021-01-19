<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/Style/style.css">
</head>
<body>

    <header >
        <div id="header-container">
            <span >
                <h2 class="title">truYum</h2>
            </span>
            <span><img src="/images/Logo.png" id="logo" > <!--Used for logo insertion-->
            </span>
            <span class="col-2">
                <ul id="my-list">
                    <li> <a href="/show-menu-list-customer" class="menu-link">Menu</a></li>
                    <li> <a href="/show-cart" class="menu-link">Cart</a></li>
                </ul>
            </span>
        </div> <!--End of header container-->
    </header>

    <div class="cart-empty-heading">
        <h2>Cart</h2>
    </div>

    <h3 class="cart-empty-notification">No items in cart. Use 'Add to Cart' option in
        <a href="/show-menu-list-customer">Menu Item List</a></h3>
     
    <footer>
       <h3> Copyright &copy; 2019 </h3>
    </footer>
        
</body>
</html>