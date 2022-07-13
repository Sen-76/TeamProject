<%-- 
    Document   : HomeExample
    Created on : May 20, 2022, 12:55:19 PM
    Author     : asus
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

        <title>G1 - All Class User</title>

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
                            <div class="container_head">
                                <div class="link" style="color: white">
                                    <a href="Home"> Dashboard </a>
                                    <p> / </p>
                                    <a href="ClassUser"> Class User </a>
                            </div>
                                <h4 class="h4 two-lines">List All Subject you have joined</h4>
                        </div>
                            <ul id="slide" style="margin-top: 7rem">
                            <span class="spn">${count} Subject(s) found</span>
                            <input oninput="searchByName(this)" class="search form-control form-control-user" name="txt" type="hidden" value="" 
                                   placeholder="Search mã môn hoặc tên môn học ở đây">
                            <c:forEach var="o" items="${listCU}">
                                <li>
                                    <div class="count box" style="height: 160px">
                                        <a href="ClassUserDetail?classid=${o.class_id}" style="color: black"><h5 class="two-lines">(${o.subject_code}) ${o.subject_name}</h5></a>
                                        <span><ion-icon name="briefcase"></ion-icon> Class:</span><a href="#" > ${o.class_code}</a><br>
                                        <span><ion-icon name="person"></ion-icon> Lecturer:<a href="#" > ${o.trainer_name}</a></span><br>
                                        <div class="aa">
                                            <a class="a" href="ClassUserDetail?classid=${o.class_id}">Go to your course  <ion-icon style="margin-left: 3px" name="arrow-forward"></ion-icon></a>
                                        </div>   
                                    </div>
                                </li>
                            </c:forEach>
                        </ul>
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
        <script src="https://unpkg.com/ionicons@4.5.10-0/dist/ionicons.js"></script>
        <!-- Page level custom scripts -->
        <script src="js/demo/chart-area-demo.js"></script>
        <script src="js/demo/chart-pie-demo.js"></script>

    </body>

</html>
