<%-- 
    Document   : register
    Created on : May 20, 2022, 10:07:23 AM
    Author     : asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Group 1 - Register</title>

        <!-- Custom fonts for this template-->
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

        <!-- Custom styles for this template-->
        <link rel="icon" href="img/cai nay hoi la.png" type="image/gif" sizes="16x16">
        <link href="css/sb-admin-2.min.css" rel="stylesheet">
        <link href="css/SenCss.css" rel="stylesheet">

    </head>

    <body class="bg-gradient-primary">

        <div class="container">

            <div class="card o-hidden border-0 shadow-lg my-5">
                <div class="card-body p-0">
                    <!-- Nested Row within Card Body -->
                    <div class="row">
                        <div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
                        <div class="col-lg-7">
                            <div class="p-5">
                                <div class="text-center">
                                    <h1 class="h4 text-gray-900 mb-4">Create an Account!</h1>
                                </div>
                                <form id="form" action="Register" method="POST" class="user">
                                    <div class="form-group">
                                        <input name="user" type="text" class="form-control form-control-user" id="user"
                                               oninput="validation1()" placeholder="Username" value="${puser}">
                                        <span id="text1"></span>
                                    </div>
                                    <div class="form-group">
                                        <input name="name" type="text" class="form-control form-control-user" id="name"
                                               oninput="validation2()" placeholder="Full Name" value="${pname}">
                                        <span id="text2"></span>
                                    </div>
                                    <div class="form-group">
                                        <input name="email" type="email" class="form-control form-control-user" id="email"
                                               oninput="validation()" placeholder="Email Address" value="${pemail}">
                                        <span id="text"></span>
                                    </div>
                                    <div class="form-group row">
                                        <div class="col-sm-6 mb-3 mb-sm-0">
                                            <input name="pass" type="password" class="form-control form-control-user"
                                                   oninput="validation3()" id="pass" placeholder="Password" value="${ppass}">
                                            <span id="text3"></span>
                                        </div>
                                        <div class="col-sm-6">
                                            <input name="repass" type="password" class="form-control form-control-user"
                                                   id="exampleRepeatPassword" placeholder="Repeat Password" value="${prepass}">
                                        </div>
                                    </div>
                                    <div>${err}</div>
                                    <div class="log">
                                        <input type="submit" value="Register Account" name="Register"/>
                                    </div>
                                    <hr>
                                    <a href="index.html" class="btn btn-google btn-user btn-block">
                                        <i class="fab fa-google fa-fw"></i> Register with Google
                                    </a>
                                    <a href="index.html" class="btn btn-facebook btn-user btn-block">
                                        <i class="fab fa-facebook-f fa-fw"></i> Register with Facebook
                                    </a>
                                </form>
                                <hr>
                                <div class="text-center">
                                    <a class="small" href="Forgot_Password">Forgot Password?</a>
                                </div>
                                <div class="text-center">
                                    <a class="small" href="Login">Already have an account? Login!</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>

        <!-- Bootstrap core JavaScript-->
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="js/SenJS.js"></script>
        <!-- Core plugin JavaScript-->
        <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

        <!-- Custom scripts for all pages-->
        <script src="js/sb-admin-2.min.js"></script>

    </body>

</html>
