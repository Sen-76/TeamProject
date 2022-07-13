<%-- 
    Document   : AddNewUser
    Created on : Jul 8, 2022, 10:54:23 PM
    Author     : tiena
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>G1 - Add new User</title>

        <!-- Custom fonts for this template-->
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

        <!-- Custom styles for this template-->
        <link href="css/sb-admin-2.min.css" rel="stylesheet">
        <link rel="icon" href="img/cai nay hoi la.png" type="image/gif" sizes="16x16">
        <link href="css/SenCss.css" rel="stylesheet">

    </head>

    <body id="page-top">

        <style>
            input[type="reset"] {
                border: none;
                outline: none;
                background: repeating-linear-gradient(45deg, #ff000080, #0000ff85);
                border-radius: 15px;
                padding: 5px 15px;
                margin-right: 100px;
                color: white;
            }
            input[type="reset"]:hover {
                background: repeating-linear-gradient(45deg, #ff0000e8, #0000ffde);
            }
        </style>
        
        <!-- Page Wrapper -->
        <div id="wrapper">

            <!-- Sidebar -->
            <jsp:include page="../Sidebar.jsp"></jsp:include>
                <!-- End of Sidebar -->

                <!-- Content Wrapper -->
                <div id="content-wrapper" class="d-flex flex-column">

                    <!-- Main Content -->
                    <div id="content">

                        <!-- Topbar -->
                    <jsp:include page="../Header.jsp"></jsp:include>
                        <!-- End of Topbar -->

                        <!-- Begin Page Content -->
                        <div class="container">
                            <div class="card o-hidden border-0 shadow-lg my-5">
                                <div class="card-body p-0">
                                    <!-- Nested Row within Card Body -->
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <div class="p-5">
                                                <div>
                                                    <h5 style="font-weight: bold">Add new User:</h5>
                                                </div>
                                                <form action="UserController" method="POST">
                                                    <input type="hidden" name="go" value="addNewUser" />
                                                <table>
                                                    <tbody>
                                                        <tr>
                                                            <td>Roll Number:</td>
                                                            <td><input class="form-control form-control-user" id="name" type="text" name="rollNumber" value="${roll_number}"></td>
                                                        </tr>
                                                        <tr>
                                                            <td>Full name:</td>
                                                            <td><input class="form-control form-control-user" id="rollNumber" type="text" name="fullName" value="${FullNAME}"></td>
                                                        </tr>
                                                        <tr>
                                                            <td>Gender:</td>
                                                            <td class="form-control form-control-user"><input type="radio" name="gender" value="1" checked >Male
                                                                <input type="radio" name="gender" value="0">Female</td>
                                                        </tr>
                                                        <tr>
                                                            <td>Date of birth:</td>
                                                            <td><input class="form-control form-control-user" id="dob" max="${dateNow}" type="date" name="dob" value="${date_of_birth}"></td>
                                                        </tr>
                                                        <tr>
                                                            <td>Email:</td>
                                                            <td><input class="form-control form-control-user" id="email" type="email" name="email" value="${email}" /></td>
                                                        </tr>
                                                        <tr>
                                                            <td>Role:</td>
                                                            <td>
                                                                <select class="form-control form-control-user" name="roleID" >
                                                                    <option value="4">Admin</option>
                                                                    <option value="3" >Author</option>
                                                                    <option value="2" >Trainer</option>
                                                                    <option value="1" >Student</option>
                                                                </select>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>Note: </td>
                                                            <td><textarea class="form-control form-control-user" name="NOTE">${note}</textarea></td>
                                                        </tr>
                                                        <tr>
                                                            <td></td>
                                                            <td><input class="form-control form-control-user" onclick="return confirm('Are you sure you want to change?')" type="submit" value="Save" name="submitAd"></td>
                                                            <td><input class="form-control form-control-user" type="reset" value="Reset"></td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                            </form>
                                            <span style="color: red; font-weight: bold;">${err}</span>
                                        </div> 
                                        <!-- /.container-fluid -->
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
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
        <jsp:include page="../LogOut.jsp"></jsp:include>

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


