<%-- 
    Document   : UpdateUser
    Created on : May 30, 2022, 11:05:37 PM
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

        <title>G1 - Dashboard</title>

        <!-- Custom fonts for this template-->
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

        <!-- Custom styles for this template-->
        <link href="css/sb-admin-2.min.css" rel="stylesheet">
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
                            <form action="UserController" method="POST">
                                <input type="hidden" name="go" value="editUser" />
                                <input type="hidden" name="${listUpdate.user_id}" />
                                <table>
                                    <tbody>
                                        <tr>
                                            <td>Roll Number:</td>
                                            <td><input type="text" name="rollNumber" value="${listUpdate.roll_number}"></td>
                                        </tr>
                                        <tr>
                                            <td>Full name:</td>
                                            <td><input type="text" name="fullName" value="${listUpdate.fullname}"></td>
                                        </tr>
                                        <tr>
                                            <td>Gender:</td>
                                            <td><input type="radio" name="gender" value="1" ${listUpdate.gender == 1 ? "checked" : ""}>Male
                                                <input type="radio" name="gender" value="2" ${listUpdate.gender == 2 ? "checked" : ""}>Female</td>
                                        </tr>
                                        <tr>
                                            <td>Date of birth:</td>
                                            <td><input type="date" name="dob" value="${listUpdate.date_of_birth}"></td>
                                        </tr>
                                        <tr>
                                            <td>Email:</td>
                                            <td><input type="email" name="email" value="${listUpdate.email}" /></td>
                                        </tr>
                                        <tr>
                                            <td>Mobile:</td>
                                            <td><input type="text" name="phone" value="${listUpdate.mobile}" /></td>
                                        </tr>
                                        <tr>
                                            <td>Status:</td>
                                            <td><input type="radio" name="status" value="1" ${listUpdate.status == 1 ? "checked" : ""}>Activate
                                                <input type="radio" name="status" value="0" ${listUpdate.status == 0 ? "checked" : ""}>Deactivate</td>
                                        </tr>
                                        <tr>
                                            <td></td>
                                            <td><input class="SubBut" type="submit" value="Save" name="submit"></td>
                                            <td><input class="SubBut" type="reset" value="Reset"></td>
                                        </tr>
                                    </tbody>
                                </table>
                            </form>
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

