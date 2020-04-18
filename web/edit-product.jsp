<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <!--
	Product Admin CSS Template
	https://templatemo.com/tm-524-product-admin
	-->
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
              <a class="nav-link active" href="products.jsp">
                <i class="fas fa-shopping-cart"></i> Products
              </a>
            </li>

            <li class="nav-item">
              <a class="nav-link" href="showUsers.jsp">
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
                <form action="ShowProducts" method="post" class="tm-edit-product-form" method="post"
                      enctype="multipart/form-data">
                  <div class="form-group mb-3">
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
                      class="form-control validate tm-small"
                      rows="5"
                      required
                    >${product.description}</textarea>
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
                            type="text"
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
                            type="text"
                            value="${product.quantity}"
                            class="form-control validate"
                          />
                        </div>
                  </div>
                  
                </form></div>
                <div class="col-xl-6 col-lg-6 col-md-12 mx-auto mb-4" id="back"
                style="position:absolute;top:30px;right:50px;">
                <div style="position:absolute;top:30px;right:50px; background-color: #f8694a;height: 200px;width: 300px;">
                <%--                  <label style="display: block;"><b>Product Image</b></label>--%>
                <img id="productImageDisplay" hidden="true" src="${product.image}"/>
                <input type="file" onchange="loadFile(event)" id="file" name="file" size="60"
                required>
                <!-- To change the product image based on the uploaded image -->
                <script>
                var loadFile = function (event) {
                var output = document.getElementById('productImageDisplay');
                output.height =300;
                output.width = 300;
                output.hidden = false;
                output.src = URL.createObjectURL(event.target.files[0]);

                };
                </script>
                <%--              <input--%>
                <%--                      id="imageBtn"--%>
                <%--                      required--%>
                <%--                      type="button"--%>
                <%--                      style="position:absolute;top:100px;right:30px;background-color: #f8694a;height: 50px;width: 200px;"--%>
                <%--                      class="btn btn-primary btn-block mx-auto"--%>
                <%--                      value="UPLOAD PRODUCT IMAGE"--%>
                <%--                      onclick="document.getElementById('fileName').click();">--%>
                </div>

                <%--                <label><b><br>Description</b></label>--%>
                <%--                <textarea placeholder="Enter product description" name="productDescription" required></textarea>--%>
                <%--               <br><br><br><br><br><br><br><br><br>--%>
                <%--              <div>--%>
                <%--                <input--%>
                <%--                                    id="imageBtn"--%>
                <%--                                    required--%>
                <%--                                    type="button"--%>
                <%--                                    style="position:absolute;top:100px;right:30px;background-color: #f8694a;height: 50px;width: 200px;"--%>
                <%--                                    value="UPLOAD PRODUCT IMAGE"--%>
                <%--                                    onclick="document.getElementById('productImage').click();">--%>
                <%--              </div>--%>
                <%--              </div>--%>

                <%--                <div  class="tm-product-img-dummy mx-auto">--%>
                <%--                  <i--%>
                <%--                    class="fas fa-cloud-upload-alt tm-upload-icon"--%>
                <%--                    onclick="document.getElementById('fileName').click();"--%>

                <%--                  ></i>--%>
                <%--                </div>--%>
                <%--                <div   class="custom-file mt-3 mb-3">--%>

                <%--                  <input id="fileName"  name="fileName" type="file"  onchange="loadFile(event)" style="display:none; background-color: #1d2124"; size="30" required />--%>

                <%--                  <input--%>
                <%--                    id="imageBtn"--%>
                <%--                    required--%>
                <%--                    type="button"--%>
                <%--                    class="btn btn-primary btn-block mx-auto"--%>
                <%--                    value="UPLOAD PRODUCT IMAGE"--%>
                <%--                    onclick="document.getElementById('fileName').click();"--%>
                <%--                  />--%>
                <%--                  <script>--%>
                <%--                    var loadFile = function (event) {--%>
                <%--                      var output = document.getElementById('back');--%>
                <%--                      output.style.backgroundImage = URL.createObjectURL(event.target.files[0]);--%>
                <%--                    }--%>
                <%--                  </script>--%>

                <%--                </div>--%>
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
<%--    <footer class="tm-footer row tm-mt-small">--%>
<%--        <div class="col-12 font-weight-light">--%>
<%--          <p class="text-center text-white mb-0 px-4 small">--%>
<%--            Copyright &copy; <b>2018</b> All rights reserved. --%>
<%--            --%>
<%--            Design: <a rel="nofollow noopener" href="https://templatemo.com" class="tm-footer-link">Template Mo</a>--%>
<%--        </p>--%>
<%--        </div>--%>
<%--    </footer> --%>

    <script src="js/jquery-3.3.1.min.js"></script>
    <!-- https://jquery.com/download/ -->
    <script src="jquery-ui-datepicker/jquery-ui.min.js"></script>
    <!-- https://jqueryui.com/download/ -->
    <script src="js/bootstrap.min.js"></script>
    <!-- https://getbootstrap.com/ -->
<%--    <script>--%>
<%--      $(function() {--%>
<%--        $("#expire_date").datepicker({--%>
<%--          defaultDate: "10/22/2020"--%>
<%--        });--%>
<%--      });--%>
<%--    </script>--%>
  </body>
</html>
