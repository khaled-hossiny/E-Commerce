<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Login Page - Product Admin Template</title>
    <link
            rel="stylesheet"
            href="https://fonts.googleapis.com/css?family=Roboto:400,700"
    />
    <!-- https://fonts.google.com/specimen/Open+Sans -->
    <link rel="stylesheet" href="css/fontawesome.min.css" />
    <!-- https://fontawesome.com/ -->
    <link rel="stylesheet" href="css/bootstrap.min.css" />
    <!-- https://getbootstrap.com/ -->
    <link rel="stylesheet" href="css/templatemo-style.css">
    <!--
	Product Admin CSS Template
	https://templatemo.com/tm-524-product-admin
	-->
</head>

<body>

<div class="container tm-mt-big tm-mb-big">
    <div class="row">
        <div class="col-12 mx-auto tm-login-col">
            <div class="tm-bg-primary-dark tm-block tm-block-h-auto">
                <div class="row">
                    <div class="col-12 text-center">
                        <h2 class="tm-block-title mb-4">Welcome to Dashboard, Sign Up</h2>
                    </div>
                </div>
                <div class="row mt-2">
                    <div class="col-12">
                        <form method="post" class="tm-login-form" action="register">
                            <div class="form-group">
                                <label for="firstName">First Name</label>
                                <input
                                        name="firstName"
                                        type="text"
                                        class="form-control validate"
                                        id="firstName"
                                        value=""
                                        required
                                />
                            </div>
                            <div class="form-group">
                                <label for="lastName">Last Name</label>
                                <input
                                        name="lastName"
                                        type="text"
                                        class="form-control validate"
                                        id="lastName"
                                        value=""
                                        required
                                />
                            </div>
                            <div class="form-group">
                                <label for="email">Email</label>
                                <input
                                        name="email"
                                        type="text"
                                        class="form-control validate"
                                        id="email"
                                        value=""
                                        required
                                />
                            </div>
                            <div class="form-group mt-3">
                                <label for="password">Password</label>
                                <input
                                        name="password"
                                        type="password"
                                        class="form-control validate"
                                        id="password"
                                        value=""
                                        required
                                />
                            </div>
                            <div class="form-group">
                                <label for="address">Address</label>
                                <input
                                        name="address"
                                        type="text"
                                        class="form-control validate"
                                        id="address"
                                        value=""
                                        required
                                />
                            </div>
                            <div class="form-group mt-4">
                                <button
                                        type="submit"
                                        class="btn btn-primary btn-block text-uppercase"
                                >
                                    Sign Up
                                </button>
                            </div>
                            <button class="mt-5 btn btn-primary btn-block text-uppercase">
                                Forgot your password?
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="js/jquery-3.3.1.min.js"></script>
<!-- https://jquery.com/download/ -->
<script src="js/bootstrap.min.js"></script>
<!-- https://getbootstrap.com/ -->
</body>
</html>
