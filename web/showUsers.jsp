<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="service.UserServiceImp"%>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Product Page - Admin HTML Template</title>
    <link
            rel="stylesheet"
            href="https://fonts.googleapis.com/css?family=Roboto:400,700"
    />
    <!-- https://fonts.google.com/specimen/Roboto -->
    <link rel="stylesheet" href="css/fontawesome.min.css" />
    <!-- https://fontawesome.com/ -->
    <link rel="stylesheet" href="css/bootstrap.min.css" />
    <!-- https://getbootstrap.com/ -->
    <link rel="stylesheet" href="css/templatemo-style.css">
    <!--
	Product Admin CSS Template
	https://templatemo.com/tm-524-product-admin
	-->
    <script>
        if (!!window.performance && window.performance.navigation.type === 2) {
            // value 2 means "The page was accessed by navigating into the history"
            console.log('Reloading');
            window.location.reload(); };// reload whole page

    </script>
</head>

<body id="reportsPage">
<nav class="navbar navbar-expand-xl">
    <div class="container h-100">
        <a class="navbar-brand" href="#">
            <h1 class="tm-site-title mb-0">Product Admin</h1>
        </a>
        <button
                class="navbar-toggler ml-auto mr-0"
                type="button"
                data-toggle="collapse"
                data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent"
                aria-expanded="false"
                aria-label="Toggle navigation"
        >
            <i class="fas fa-bars tm-nav-icon"></i>
        </button>
        <div class="container h-100">
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mx-auto h-100">

                    <li class="nav-item">
                        <a class="nav-link" href="ShowProducts">
                            <i class="fas fa-shopping-cart"></i> Products
                        </a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link" href="ShowUsers">
                            <img src="img/user.png" style="width: 30px">
                            <span> ShowCustomers  </span>
                        </a>

                    </li>
                    <li class="nav-item dropdown">
                        <a
                                class="nav-link dropdown-toggle"
                                href="#"
                                id="navbarDropdown"
                                role="button"
                                data-toggle="dropdown"
                                aria-haspopup="true"
                                aria-expanded="false"
                        >
                            <i class="fas fa-cog"></i>
                            <span> Settings <i class="fas fa-angle-down"></i> </span>
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="#">Profile</a>

                        </div>
                    </li>
                </ul>
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link d-block" href="login.jsp">
                            Admin, <b>Logout</b>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
</nav>
<div class="container mt-5" style="width: 1000px;">
    <div class="row tm-content-row" style="width: 1000px;">
        <div class="col-sm-12 col-md-12 col-lg-8 col-xl-8 tm-block-col"  style="width: 1000px;">
            <div class="tm-bg-primary-dark tm-block tm-block-products" style="width: 1000px;">
                <div class="tm-product-table-container" >
                    <table class="table table-hover tm-table-small tm-product-table">
                        <thead>
                        <tr>
                            <th scope="col">&nbsp;</th>
                            <th scope="col">FIRST NAME</th>
                            <th scope="col">LAST NAME</th>
                            <th scope="col">EMAIL</th>
                            <th scope="col">Address</th>
                            <th scope="col">&nbsp;</th>
                        </tr>
                        </thead>
                        <tbody style="height: 500px;">
                        <c:forEach items="${list}" var="row">
                            <%--                  <tr onclick="window.location='accounts.jsp';">--%>
                            <tr>
                                <td></td>
                                <td class="tm-product-name"  onclick="update(${row.id});"><c:out value="${row.firstName}"/></td>
                                <td><c:out value="${row.lastName}"/></td>
                                <td><c:out value="${row.email}"/></td>
                                <td><c:out value="${row.address}"/></td>

                            </tr>
                        </c:forEach>
                        <%--                      --%>
                        <%--                  <tr onclick="window.location='accounts.jsp';">--%>
                        <%--    --%>
                        <%--                    <td></td>  --%>
                        <%--                    <td>shimaa</td>--%>
                        <%--                    <td>octobar</td>--%>
                        <%--                    <td>shimaa</td> --%>
                        <%--                     <td>Elnady</td>--%>
                        <%--                     <td>alshimaa@gmail.com</td> --%>
                        <%--                                  --%>
                        <%--                  </tr>--%>
                        </tbody>

                    </table>
                </div>
                <%--              <footer>--%>
                <%--            <!-- table container -->--%>
                <%--            <a--%>
                <%--              href="add-product.jsp"--%>
                <%--              class="btn btn-primary btn-block text-uppercase mb-3">Add new User</a>--%>
                <%--            <button class="btn btn-primary btn-block text-uppercase">--%>
                <%--              Delete selected USER--%>
                <%--            </button>--%>
                <%--                  </footer>--%>
            </div>
        </div>



        <script src="js/jquery-3.3.1.min.js"></script>
        <!-- https://jquery.com/download/ -->
        <script src="js/bootstrap.min.js"></script>
        <!-- https://getbootstrap.com/ -->
        <%--    <script>--%>
        <%--      $(function() {--%>
        <%--        $(".tm-product-name").on("click", function() {--%>
        <%--          window.location.href = "edit-user.jsp";--%>
        <%--        });--%>
        <%--      });--%>
        <%--    </script>--%>
</body>
</html>