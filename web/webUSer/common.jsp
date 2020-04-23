<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
    <!-- top Header -->
    <div id="top-header">
        <div class="container">
            <div class="pull-left">
                <span>Welcome to E-shop!</span>
            </div>
        </div>
    </div>
    <!-- /top Header -->

    <!-- header -->
    <div id="header">
        <div class="container">
            <div class="pull-left">
                <!-- Logo -->
                <div class="header-logo">
                    <a class="logo" href="home">
                        <img src="img/logo.png" alt="">
                    </a>
                </div>
                <!-- /Logo -->

                <!-- Search -->
                <div class="header-search">
                    <form method="get" action="home">
                        <input class="input search-input" type="text" placeholder="Enter your keyword"
                               name="searchQuery">
                        <select class="input search-categories">
                        <c:forEach items="${categories}" var="categories">
                            <option value="${categories.name}"><c:out value="${categories.name}"/></option>
                        </c:forEach>

                        </select>
                        <button class="search-btn"><i class="fa fa-search"></i></button>
                    </form>
                </div>
                <!-- /Search -->
            </div>
            <div class="pull-right">
                <ul class="header-btns">
                    <!-- Account -->
                    <li class="header-account dropdown default-dropdown">
                        <div class="dropdown-toggle" role="button" data-toggle="dropdown" aria-expanded="true">
                            <div class="header-btns-icon">
                                <i class="fa fa-user-o"></i>
                            </div>
                            <strong class="text-uppercase">My Account <i class="fa fa-caret-down"></i></strong>
                        </div>
                        <h4>Balance : $${balance}</h4>
                        <ul class="custom-menu">
                            <li><a href="../UpdateServlet"><i class="fa fa-user-o"></i> My Account</a></li>
                            <li><a href="../logout"><i class="fa fa-sign-out"></i> Sign out</a></li>
                        </ul>
                    </li>
                    <!-- /Account -->

                    <!-- Cart -->
                    <li class="header-cart dropdown default-dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">
                            <div class="header-btns-icon">
                                <i class="fa fa-shopping-cart"></i>
                                <span class="qty">${cartSize}</span>
                            </div>
                            <strong class="text-uppercase">My Cart:</strong>
                            <br>
                            <span>$${cartCost}</span>
                        </a>
                        <div class="custom-menu">
                            <div id="shopping-cart">
                                <c:forEach items="${cartProducts}" var="cartProduct">
                                    <div class="shopping-cart-list">
                                        <div class="product product-widget">
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
                                            <button class="cancel-btn"><i class="fa fa-trash"></i></button>
                                        </div>
                                    </div>
                                </c:forEach>
                                <div class="shopping-cart-btns">
                                    <a class="main-btn" href="cart">View Cart</a>
                                    <a class="primary-btn" href="checkout">Checkout <i
                                            class="fa fa-arrow-circle-right"></i>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </li>
                    <!-- /Cart -->

                    <!-- Mobile nav toggle-->
                    <li class="nav-toggle">
                        <button class="nav-toggle-btn main-btn icon-btn"><i class="fa fa-bars"></i></button>
                    </li>
                    <!-- / Mobile nav toggle -->
                </ul>
            </div>
        </div>
        <!-- header -->
    </div>
    <!-- container -->
</header>
<!-- /HEADER -->

<!-- NAVIGATION -->
<div id="navigation">
    <!-- container -->
    <div class="container">
        <div id="responsive-nav">
            <!-- category nav -->
            <div class="category-nav show-on-click">
                <span class="category-header">Categories <i class="fa fa-list"></i></span>
                <ul class="category-list">
             <c:forEach items="${categories}" var="categories">
                    <li><a href="#"><c:out value="${categories.name}"/></a></li>
             </c:forEach>
                </ul>
            </div>
            <!-- /category nav -->
        </div>
    </div>
    <!-- /container -->
</div>
<!-- /NAVIGATION -->
