<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <title>E-SHOP HTML Template</title>

    <!-- Google font -->
    <link href="https://fonts.googleapis.com/css?family=Hind:400,700" rel="stylesheet">

    <!-- Bootstrap -->
    <link type="text/css" rel="stylesheet" href="css/bootstrap.min.css"/>

    <!-- Slick -->
    <link type="text/css" rel="stylesheet" href="css/slick.css"/>
    <link type="text/css" rel="stylesheet" href="css/slick-theme.css"/>

    <!-- nouislider -->
    <link type="text/css" rel="stylesheet" href="css/nouislider.min.css"/>

    <!-- Font Awesome Icon -->
    <link rel="stylesheet" href="css/font-awesome.min.css">

    <!-- Custom stlylesheet -->
    <link type="text/css" rel="stylesheet" href="css/style.css"/>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>
<!-- HEADER -->
<!-- BREADCRUMB -->
<jsp:include page="/nav"/>


<!-- section -->
<div class="section">
    <!-- container -->
    <div class="container">
        <c:forEach items="${cartProducts}" var="cartProduct">
            <div class="row">
                <div class="col-md-3 col-sm-6 col-xs-6">
                    <div class="product product-single">
                        <div class="product-thumb">
                            <img src="${pageContext.request.contextPath}/file/${cartProduct.pk.product.image}"
                                 alt="">
                        </div>
                        <div class="product-body">
                            <h3 class="product-price">$${cartProduct.pk.product.price} <span
                                    class="qty">x${cartProduct.quantity}</span></h3>
                            <h2 class="product-name"><a href="#">${cartProduct.pk.product.name}</a>
                            </h2>
                        </div>
                        <form action="remove" method="post">
                            <div class="product-btns">
                                <div class="qty-input">
                                    <input type="hidden" value="${cartProduct.pk.product.id}" name="id">
                                    <span class="text-uppercase">QTY: </span>
                                    <input class="input" type="number" name="quantity">
                                </div>
                                <button class="cancel-btn" type="submit"><i class="fa fa-trash"></i></button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <!-- /Product Single -->
        </c:forEach>
        <!-- ASIDE -->
        <!-- /ASIDE -->

        <!-- /MAIN -->
    </div>
    <!-- /row -->
</div>
<!-- /container -->
</div>
<!-- /section -->

<!-- FOOTER -->
<!-- /FOOTER -->

<!-- jQuery Plugins -->
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/slick.min.js"></script>
<script src="js/nouislider.min.js"></script>
<script src="js/jquery.zoom.min.js"></script>
<script src="js/main.js"></script>

</body>

</html>
