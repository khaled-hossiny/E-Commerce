<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page import="service.AdminServiceImpl"%>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Edit Product - Dashboard Admin Template</title>
    <link
            rel="stylesheet"
            href="https://fonts.googleapis.com/css?family=Roboto:400,700"
    />
    <!-- https://fonts.google.com/specimen/Roboto -->
    <link rel="stylesheet" href="css/fontawesome.min.css" />
    <!-- https://fontawesome.com/ -->
    <link rel="stylesheet" href="jquery-ui-datepicker/jquery-ui.min.css" type="text/css" />
    <!-- http://api.jqueryui.com/datepicker/ -->
    <link rel="stylesheet" href="css/bootstrap.min.css" />
    <!-- https://getbootstrap.com/ -->
    <link rel="stylesheet" href="css/templatemo-style.css">
    <link rel="stylesheet" href="css/checky.css">
    <!--
	Product Admin CSS Template
	https://templatemo.com/tm-524-product-admin
	-->
    <script>
        if (!!window.performance && window.performance.navigation.type === 2) {
            console.log('Reloading');
            window.location.reload();
        };

    </script>
</head>

<body>
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

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mx-auto h-100">
                <li class="nav-item">
                    <a class="nav-link active" href="ShowProducts">
                        <i class="fas fa-shopping-cart"></i> Products
                    </a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="ShowUsers">
                        <img src="img/user.png" style="width: 30px">
                        <span> ShowCustomers  </span>
                    </a>
                </li>
                <li class="nav-item dropdown">
                    <a
                            class="nav-link dropdown-toggle"
                            href="#"
                            id="navbarDropdown2"
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
<div class="container tm-mt-big tm-mb-big">
    <div class="row">
        <div class="col-xl-9 col-lg-10 col-md-12 col-sm-12 mx-auto">
            <div class="tm-bg-primary-dark tm-block tm-block-h-auto">
                <div class="row">
                    <div class="col-12">
                        <h2 class="tm-block-title d-inline-block">Edit Product</h2>
                    </div>
                </div>
                <div class="row tm-edit-product-row">
                    <div class="col-xl-6 col-lg-6 col-md-12">
                        <form action="EditProducts" method="post" class="tm-edit-product-form" method="post"
                              enctype="multipart/form-data" >
                            <div class="form-group mb-3">
                                <input type="hidden" id="id" name="id" value="${product.id}">
                                <label
                                        for="name"
                                >Product Name
                                </label>
                                <input
                                        id="name"
                                        name="name"
                                        type="text"
                                        value="${product.name}"
                                        class="form-control validate"
                                />
                            </div>
                            <div class="form-group mb-3">
                                <label
                                        for="desc"
                                >Description</label
                                >
                                <textarea
                                        id="desc"
                                        name="desc"
                                        class="form-control validate tm-small"
                                        rows="5"
                                        required
                                >${product.description}</textarea>
                            </div>
                            <div class="form-group mb-3">
                                    <c:forEach  items="${product.categories}" var="row">
                                        <label class="checky"><c:out value="${row.name}"></c:out>
                                            <input type="checkbox" id="category" name="category" value="${row.id}" checked>
                                            <span class="checkmark"></span>
                                        </label>
                                    </c:forEach>

                             <c:forEach items="${categories}" var="row2">
                                 <c:set var="string2" value="1" />
                                 <c:forEach  items="${product.categories}" var="row">
                                 <c:choose>
                                     <c:when test="${row.name == row2.name || string2 == 0}">
                                     <c:set var="string2" value="0" />

                                     </c:when>
                                     <c:when test="${row.name != row2.name || string2 != 0}">
                                         <c:set var="string2" value="1" />
                                     </c:when>
                                 </c:choose>
                             </c:forEach>
                                <c:if test="${string2 == '1'}">
                            <label class="checky"><c:out value="${row2.name}"></c:out>
                                <input type="checkbox" id="category" name="category" value="${row2.id}">
                                <span class="checkmark"></span>
                            </label>
                                </c:if>
                            </c:forEach>

                            </div>
                            <div class="row">
                                <div class="form-group mb-3 col-xs-12 col-sm-6">
                                    <label
                                            for="price"
                                    >Price
                                    </label>
                                    <input
                                            id="price"
                                            name="price"
                                            type="number"
                                            value="${product.price}"
                                            class="form-control validate"
                                            data-large-mode="true"
                                    />
                                </div>
                                <div class="form-group mb-3 col-xs-12 col-sm-6">
                                    <label
                                            for="stock"
                                    >Units In Stock
                                    </label>
                                    <input
                                            id="stock"
                                            name="stock"
                                            type="number"
                                            value="${product.quantity}"
                                            class="form-control validate"
                                    />
                                </div>
                            </div>

                    </div>
                    <div class="col-xl-6 col-lg-6 col-md-12 mx-auto mb-4" id="back"
                         style="position:absolute;top:30px;right:50px;">
                        <div style="position:absolute;top:30px;right:50px; background-color: #f8694a;height: 200px;width: 300px;">
                            <%--                  <label style="display: block;"><b>Product Image</b></label>--%>
                            <img id="productImageDisplay" src="${pageContext.request.contextPath}/file/${product.image}" height="300" width="300"/>
                            <input type="file" onchange="loadFile(event)" id="uploadFile" name="uploadFile" size="60"
                                   value="${product.image}">
                            <!-- To change the product image based on the uploaded image -->
                            <script>
                                var loadFile = function (event) {
                                    var output = document.getElementById('productImageDisplay');
                                    output.height =300;
                                    output.width = 300;
                                    output.src = URL.createObjectURL(event.target.files[0]);
                                    $('#img').val(output.src);
                                };
                            </script>
                        </div>
                    </div>
                    <div class="col-12">
                        <button type="submit" class="btn btn-primary btn-block text-uppercase">Update Now</button>
                    </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="js/jquery-3.3.1.min.js"></script>
<!-- https://jquery.com/download/ -->
<script src="jquery-ui-datepicker/jquery-ui.min.js"></script>
<!-- https://jqueryui.com/download/ -->
<script src="js/bootstrap.min.js"></script>
</body>
</html>