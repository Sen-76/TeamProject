<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : ClassByTrain
    Created on : Jun 13, 2022, 4:31:04 PM
    Author     : ASUS
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


        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />

        <!-- Custom styles for this template-->
        <link href="css/sb-admin-2.min.css" rel="stylesheet">
        <link rel="icon" href="img/cai nay hoi la.png" type="image/gif" sizes="16x16">

        <link href="css/HomeTrain.css" rel="stylesheet">
        <link href="css/SenCss.css" rel="stylesheet">

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
                        <div class="container-fluid">
                            <div class="card shadow mb-4">
                                <div class="card-header py-3">
                                    <h3 class="m-0 font-weight-bold text-primary">All your class</h3>
                                </div>

                                <div>

                                    <nav class="navbar navbar-expand-lg navbar-light ">
                                        <span class="navbar-brand" >Filter</span>
                                        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                                            <span class="navbar-toggler-icon"></span>
                                        </button>

                                        <div class="collapse navbar-collapse" id="navbarSupportedContent">
                                            <form class="form-inline my-2 my-lg-0" action="ClassTrainer?go=showAllByTrain" method="POST">
                                                <ul class="navbar-nav mr-auto">
                                                    <li class="nav-item dropdown">
                                                        Trainer: <strong>${Loged.fullname}</strong>
                                                </li>

                                                <li class="nav-item dropdown">
                                                    Status:
                                                    <select class="form-control" name="ClaSta">
                                                        <option value="2" ${sta == 2 ? "selected":""}>All</option>
                                                        <option value="1" ${sta == 1 ? "selected":""}>Ongoing</option>
                                                        <option value="0" ${sta == 0 ? "selected":""}>Ended</option>
                                                    </select>
                                                </li>

                                            </ul>

                                            <input class="form-control mr-sm-2" type="text" name="searchClass" value="${search}" placeholder="Search" aria-label="Search">
                                            <button class="btn btn-outline-primary my-2 my-sm-0" type="submit">Search</button>



                                        </form>
                                    </div>
                                            <c:if test="${Loged.role_id > 2}">
                                                <div class="float-right">
                                        <a class="text text--" href="ShowAllClass?go=AddClass">Add more class</a>
                                    </div>
                                            </c:if>        
                                    
                                </nav>



                            </div>

                            <div class="card-body">
                                <div class="table-responsive">
                                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                        <thead>
                                            <tr>
                                                <th>Class code</th>

                                                <th>subject name</th>
                                                <th>class year</th>
                                                <th>class term</th>
                                                <th>block 5 ?</th>
                                                <th>Status</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tfoot>
                                            <tr>
                                                <th>Class code</th>

                                                <th>subject name</th>
                                                <th>class year</th>
                                                <th>class term</th>
                                                <th>block 5 ?</th>
                                                <th>Status</th>
                                                <th>Action</th>
                                            </tr>
                                        </tfoot>
                                        <tbody>

                                            <c:forEach var="o" items="${vectC}">
                                                <tr>
                                                    <td>
                                                        <a href="ClassTrainer?go=detailClass&cid=${o.id}&sid=${o.subjectId}">${o.classCode}</a></td>

                                                    <td>${o.subjectId}</td>
                                                    <td>${o.classYear}</td>
                                                    <td>${o.classTerm}</td>
                                                    <td>${o.block5Class == 1 ? "<i>True</i>":"False"}</td>
                                                    <td>${o.status == 1 ? "<strong>Ongoing</strong>" : "Ended"}</td>
                                                    <td class="text-center">   <a href="TeamList?go=listAllTeam&cid=${o.id}">
                                                            <span class="material-symbols-outlined">
                                                                group
                                                            </span>
                                                        </a> 


                                                    </td>
                                                </tr>
                                            </c:forEach>             

                                        </tbody>
                                    </table>
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



        <script src="js/sweetalert.min.js"></script>


        <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
    </body>

</html>
