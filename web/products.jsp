<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="service.AdminServiceImpl"%>
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
        window.location.reload();
      };// reload whole page

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
                id="navbarDropdown"
                role="button"
                data-toggle="dropdown"
                aria-haspopup="true"
                aria-expanded="false">
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
    <div class="container mt-5">
      <div class="row tm-content-row">
        <div class="col-sm-12 col-md-12 col-lg-8 col-xl-8 tm-block-col">
          <div class="tm-bg-primary-dark tm-block tm-block-products">
            <div class="tm-product-table-container">
              <table class="table table-hover tm-table-small tm-product-table" id="products" name="products">
                <thead>
                  <tr>
                    <th scope="col">&nbsp;</th>
                    <th scope="col">PRODUCT NAME</th>
                    <th scope="col">Price</th>
                    <th scope="col">STOCK</th>
                    <th scope="col">Description</th>
                    <th scope="col">&nbsp;</th>
                  </tr>
                </thead>
                <tbody>
                <c:forEach items="${list}" var="row">
                  <tr >
                    <th scope="row"><input type="checkbox" /></th>
                    <td class="tm-product-name" onclick="update(${row.id});"><c:out value="${row.name}"/>
<%--                      <html:param name="id" value="${row.id}" />--%>

<%--                      <html:link action="update();">--%>
<%--                      --%>
<%--                      </html:link>--%>
                    </td>
                    </a>
                    <td><c:out value="${row.price}"/></td>
                    <td><c:out value="${row.quantity}"/></td>
                    <td><c:out value="${row.description}"/></td>
                    <td onclick="remove(${row.id});">
                      <a href="#" class="tm-product-delete-link" >
                        <i class="far fa-trash-alt tm-product-delete-icon"></i>
                      </a>
                    </td>

                  </tr>
                </c:forEach>
<%--                  <tr>--%>
<%--                    <th scope="row"><input type="checkbox" /></th>--%>
<%--                    <td class="tm-product-name">Lorem Ipsum Product 2</td>--%>
<%--                    <td>1,250</td>--%>
<%--                    <td>750</td>--%>
<%--                    <td>21 March 2019</td>--%>
<%--                    <td>--%>
<%--                      <a href="#" class="tm-product-delete-link">--%>
<%--                        <i class="far fa-trash-alt tm-product-delete-icon"></i>--%>
<%--                      </a>--%>
<%--                    </td>--%>
<%--                  </tr>--%>
<%--                  <tr>--%>
<%--                    <th scope="row"><input type="checkbox" /></th>--%>
<%--                    <td class="tm-product-name">Lorem Ipsum Product 3</td>--%>
<%--                    <td>1,100</td>--%>
<%--                    <td>900</td>--%>
<%--                    <td>18 Feb 2019</td>--%>
<%--                    <td>--%>
<%--                      <a href="#" class="tm-product-delete-link">--%>
<%--                        <i class="far fa-trash-alt tm-product-delete-icon"></i>--%>
<%--                      </a>--%>
<%--                    </td>--%>
<%--                  </tr>--%>
<%--                  <tr>--%>
<%--                    <th scope="row"><input type="checkbox" /></th>--%>
<%--                    <td class="tm-product-name">Lorem Ipsum Product 4</td>--%>
<%--                    <td>1,400</td>--%>
<%--                    <td>600</td>--%>
<%--                    <td>24 Feb 2019</td>--%>
<%--                    <td>--%>
<%--                      <a href="#" class="tm-product-delete-link">--%>
<%--                        <i class="far fa-trash-alt tm-product-delete-icon"></i>--%>
<%--                      </a>--%>
<%--                    </td>--%>
<%--                  </tr>--%>
<%--                  <tr>--%>
<%--                    <th scope="row"><input type="checkbox" /></th>--%>
<%--                    <td class="tm-product-name">Lorem Ipsum Product 5</td>--%>
<%--                    <td>1,800</td>--%>
<%--                    <td>200</td>--%>
<%--                    <td>22 Feb 2019</td>--%>
<%--                    <td>--%>
<%--                      <a href="#" class="tm-product-delete-link">--%>
<%--                        <i class="far fa-trash-alt tm-product-delete-icon"></i>--%>
<%--                      </a>--%>
<%--                    </td>--%>
<%--                  </tr>--%>
<%--                  <tr>--%>
<%--                    <th scope="row"><input type="checkbox" /></th>--%>
<%--                    <td class="tm-product-name">Lorem Ipsum Product 6</td>--%>
<%--                    <td>1,000</td>--%>
<%--                    <td>1,000</td>--%>
<%--                    <td>20 Feb 2019</td>--%>
<%--                    <td>--%>
<%--                      <a href="#" class="tm-product-delete-link">--%>
<%--                        <i class="far fa-trash-alt tm-product-delete-icon"></i>--%>
<%--                      </a>--%>
<%--                    </td>--%>
<%--                  </tr>--%>
<%--                  <tr>--%>
<%--                    <th scope="row"><input type="checkbox" /></th>--%>
<%--                    <td class="tm-product-name">Lorem Ipsum Product 7</td>--%>
<%--                    <td>500</td>--%>
<%--                    <td>100</td>--%>
<%--                    <td>10 Feb 2019</td>--%>
<%--                    <td>--%>
<%--                      <a href="#" class="tm-product-delete-link">--%>
<%--                        <i class="far fa-trash-alt tm-product-delete-icon"></i>--%>
<%--                      </a>--%>
<%--                    </td>--%>
<%--                  </tr>--%>
<%--                  <tr>--%>
<%--                    <th scope="row"><input type="checkbox" /></th>--%>
<%--                    <td class="tm-product-name">Lorem Ipsum Product 8</td>--%>
<%--                    <td>1,000</td>--%>
<%--                    <td>600</td>--%>
<%--                    <td>08 Feb 2019</td>--%>
<%--                    <td>--%>
<%--                      <a href="#" class="tm-product-delete-link">--%>
<%--                        <i class="far fa-trash-alt tm-product-delete-icon"></i>--%>
<%--                      </a>--%>
<%--                    </td>--%>
<%--                  </tr>--%>
<%--                  <tr>--%>
<%--                    <th scope="row"><input type="checkbox" /></th>--%>
<%--                    <td class="tm-product-name">Lorem Ipsum Product 9</td>--%>
<%--                    <td>1,200</td>--%>
<%--                    <td>800</td>--%>
<%--                    <td>24 Jan 2019</td>--%>
<%--                    <td>--%>
<%--                      <a href="#" class="tm-product-delete-link">--%>
<%--                        <i class="far fa-trash-alt tm-product-delete-icon"></i>--%>
<%--                      </a>--%>
<%--                    </td>--%>
<%--                  </tr>--%>
<%--                  <tr>--%>
<%--                    <th scope="row"><input type="checkbox" /></th>--%>
<%--                    <td class="tm-product-name">Lorem Ipsum Product 10</td>--%>
<%--                    <td>1,600</td>--%>
<%--                    <td>400</td>--%>
<%--                    <td>22 Jan 2019</td>--%>
<%--                    <td>--%>
<%--                      <a href="#" class="tm-product-delete-link">--%>
<%--                        <i class="far fa-trash-alt tm-product-delete-icon"></i>--%>
<%--                      </a>--%>
<%--                    </td>--%>
<%--                  </tr>--%>
<%--                  <tr>--%>
<%--                    <th scope="row"><input type="checkbox" /></th>--%>
<%--                    <td class="tm-product-name">Lorem Ipsum Product 11</td>--%>
<%--                    <td>2,000</td>--%>
<%--                    <td>400</td>--%>
<%--                    <td>21 Jan 2019</td>--%>
<%--                    <td>--%>
<%--                      <a href="#" class="tm-product-delete-link">--%>
<%--                        <i class="far fa-trash-alt tm-product-delete-icon"></i>--%>
<%--                      </a>--%>
<%--                    </td>--%>
<%--                  </tr>--%>
                </tbody>
              </table>
            </div>
            <!-- table container -->
            <a
              href="AddProduct"
              class="btn btn-primary btn-block text-uppercase mb-3">Add new product</a>
<%--            <button class="btn btn-primary btn-block text-uppercase">--%>
<%--              Delete selected products--%>
<%--            </button>--%>
          </div>
        </div>
        <div class="col-sm-12 col-md-12 col-lg-4 col-xl-4 tm-block-col">
          <div class="tm-bg-primary-dark tm-block tm-block-product-categories">
            <h2 class="tm-block-title">Product Categories</h2>
            <div class="tm-product-table-container">

              <table class="table tm-table-small tm-product-table">
                <tbody>

                <c:forEach items="${categories}" var="row2">
                  <tr>
                    <td class="tm-product-name"> <c:out value="${row2.name}"/></td>
                  </tr>
                </c:forEach>

                </tbody>
              </table>
            </div>
            <a
                    href="add-category.jsp"
                    class="btn btn-primary btn-block text-uppercase mb-3"> Add new category</a>

          </div>
        </div>
      </div>
    </div>
<%--    <footer class="tm-footer row tm-mt-small">--%>
<%--      <div class="col-12 font-weight-light">--%>
<%--        <p class="text-center text-white mb-0 px-4 small">--%>
<%--          Copyright &copy; <b>2018</b> All rights reserved.--%>

<%--          Design: <a rel="nofollow noopener" href="https://templatemo.com" class="tm-footer-link">Template Mo</a>--%>
<%--        </p>--%>
<%--      </div>--%>
<%--    </footer>--%>

    <script src="js/jquery-3.3.1.min.js"></script>
    <!-- https://jquery.com/download/ -->
    <script src="js/bootstrap.min.js"></script>
    <!-- https://getbootstrap.com/ -->
<%--                        <script>--%>
<%--                          $(function() {--%>
<%--                            $(".tm-product-name").on("click", function(event) {--%>
<%--                              window.location.href = "EditProducts?id="+event.data.id;--%>
<%--                              &lt;%&ndash;<a href="EditProducts?id="+<%="${row.id}"%>/>&ndash;%&gt;--%>
<%--                            });--%>
<%--                          });--%>
<%--                        </script>--%>
  <script>
    function update(id) {
      window.location.href = "EditProducts?id="+id;
    }
  </script>
    <script>
      function remove(id) {
        window.location.href = "RemoveProduct?id="+id;
      }
    </script>
    <script>
      function removeCat(id) {
        window.location.href = "RemoveCategory?id="+id;
      }
    </script>


  </body>
</html>