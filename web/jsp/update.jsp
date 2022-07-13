<%-- 
    Document   : Home
    Created on : May 20, 2022, 12:55:19 PM
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

        <title>G1 - Dashboard</title>

        <!-- Custom fonts for this template-->
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

        <!-- Custom styles for this template-->
        <link href="css/sb-admin-2.min.css" rel="stylesheet">
        <link href="css/SenCss.css" rel="stylesheet">
        <link rel="icon" href="img/cai nay hoi la.png" type="image/gif" sizes="16x16">

    </head>

    <body id="page-top">


        <!-- Page Wrapper -->
        <div id="wrapper">

            <!-- Sidebar -->
            <jsp:include page="Sidebar.jsp"></jsp:include>
                <!-- End of Sidebar -->

                <!-- Content Wrapper -->
                <div id="content-wrapper" class="d-flex flex-column">

                    <!-- Main Content -->
                    <div id="content">

                        <!-- Topbar -->
                    <jsp:include page="Header.jsp"></jsp:include>
                        <!-- End of Topbar -->

                        <!-- Begin Page Content -->
                        <div class="container">
                            <div class="card o-hidden border-0 shadow-lg my-5">
                                <div class="card-body p-0">
                                    <!-- Nested Row within Card Body -->
                                    <div class="row">
                                        <div class="col-lg-5 d-none d-lg-block">
                                            <form class="user" action="UpdateImg" method="post" enctype="multipart/form-data">
                                                <div class="form-group">
                                                    <input type="hidden" class="form-control form-control-user" name="userid"
                                                           value="${Loged.user_id}">
                                            </div>
                                            <div class="text-center">
                                                <br>
                                                <img src="uploads/${Loged.avatar_link}" style="border-radius: 50%" class="avatar img-circle img-thumbnail" alt="avatar" width="300" height="300">
                                                <h6>Upload a different photo...</h6>
                                                <span style="color: red">${haizz}</span>
                                                <input type="file" name="image" class="file form-control text-center center-block file-upload" >
                                                <input type="submit" value="Save Image" name="SaveImage"/>
                                            </div></hr><br>
                                        </form>    
                                    </div>
                                    <div class="col-lg-7">
                                        <form class="user" action="Update" method="post">
                                            <div class="p-5">
                                                <div class="text-center">
                                                    <h1 class="h4 text-gray-900 mb-4">Update your User Profile</h1>
                                                </div>
                                                <div class="form-group">
                                                    <input type="hidden" class="form-control form-control-user" name="userid"
                                                           value="${Loged.user_id}">
                                                </div>
                                                <div class="form-group">
                                                    <label>Fullname:</label>
                                                    <input type="text" class="form-control form-control-user" name="fullname"
                                                           value="${Loged.fullname}">
                                                </div>
                                                <div class="form-group row">
                                                    <div class="col-sm-12 mb-6 mb-sm-0">
                                                        <label>Gender: </label>
                                                        <select name="gender" class="Gender" >
                                                            <option value="0" ${Loged.gender == 0 ? "selected" : ""}>Gender</option>
                                                            <option value="1" ${Loged.gender == 1 ? "selected" : ""}>Male</option>
                                                            <option value="2" ${Loged.gender == 2 ? "selected" : ""}>Female</option>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label>Date of Birth</label>
                                                    <input type="date" name="dateofbirth" class="form-control form-control-user" 
                                                           value="${Loged.date_of_birth}"
                                                           min="1980-01-01" max="${DateNow}">

                                                </div>
                                                <div class="form-group">
                                                    <label>E-mail</label>
                                                    <input type="email" class="form-control form-control-user" name="email"
                                                           value="${Loged.email}">
                                                </div>
                                                <div class="form-group">
                                                    <label>Mobile</label>
                                                    <input type="text" class="form-control form-control-user" name="mobile"
                                                           value="${Loged.mobile == 0 ? "" : Loged.mobile}">

                                                </div>
                                                <div class="form-group">
                                                    <label>Facebook link</label>
                                                    <input type="text" class="form-control form-control-user" name="fblink"
                                                           value="${Loged.facebook_link == 0 ? "" : Loged.facebook_link}">
                                                </div>
                                                <div>
                                                    ${updated}
                                                </div>
                                                <input type="submit" value="Update" name="UpdateProfile"/>
                                                <hr>
                                                <hr>
                                            </div>                       
                                        </form>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- /.container-fluid -->

                </div>
                <!-- End of Main Content -->

                <!-- Footer -->
                <footer class="sticky-footer bg-white">
                    <div class="container my-auto">
                        <div class="copyright text-center my-auto">
                            <span>Copyright &copy; Your Website 2021</span>
                        </div>
                    </div>
                </footer>
                <!-- End of Footer -->

            </div>
            <!-- End of Content Wrapper -->

        </div>
        <!-- End of Page Wrapper -->

        <!-- Scroll to Top Button-->
        <a class="scroll-to-top rounded" href="#page-top">
            <i class="fas fa-angle-up"></i>
        </a>

        <!-- Logout Modal-->
        <jsp:include page="LogOut.jsp"></jsp:include>

        <!-- Bootstrap core JavaScript-->
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

        <!-- Core plugin JavaScript-->
        <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

        <!-- Custom scripts for all pages-->
        <script src="js/sb-admin-2.min.js"></script>

        <!-- Page level plugins -->
        <script src="vendor/chart.js/Chart.min.js"></script>

        <!-- Page level custom scripts -->
        <script src="js/demo/chart-area-demo.js"></script>
        <script src="js/demo/chart-pie-demo.js"></script>

    </body>

</html>
