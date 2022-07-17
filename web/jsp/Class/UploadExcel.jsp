<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- 
    Document   : UploadExcel
    Created on : Jun 18, 2022, 10:31:50 PM
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

                        <div class="container-fluid">
                            <div class="card shadow mb-4">
                                <div class="card-header py-3">
                                    <form action="uploadExcel?go=uploadFile" method="POST" enctype="multipart/form-data"> 
                                        <input type="file" name="excelFile" accept=".xlsx,.xls,.csv">
                                        <input type="submit" value="Submit">
                                    </form>    
                                </div>

                                <div class="card-body">

                                    <a href="\g1\Template_G1.xlsx" 
                                       class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                                            class="fas fa-download fa-sm text-white-50"></i>Download template</a>
                                </div>
                            </div>

                            <div class="card shadow mb-4">
                                <div class="card-header py-3">
                                    <h6 class="m-0 font-weight-bold text-primary">
                                        Import student for class    
                                    <c:if test="${count<=30 } ">
                                        Take your time to confirm this data
                                    </c:if>


                                </h6>
                            </div>
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                        <thead>
                                            <tr>
                                                <th>Team name</th>
                                                <th>username</th>
                                                <th>roll number</th>
                                                <th>full name</th>
                                                <th>leader</th>
                                            </tr>
                                        </thead>

                                        <tfoot>
                                            <tr>
                                                <th>Team name</th>
                                                <th>username</th>
                                                <th>roll number</th>
                                                <th>full name</th>
                                                <th>leader</th>
                                            </tr>
                                        </tfoot>
                                        <tbody>

                                            <c:forEach var="o" items="${vect}">
                                                <tr>
                                                    <td>${o.groupID}</td>
                                                    <td>${o.username}</td>
                                                    <td>${o.rollNum}</td>
                                                    <td>${o.fullName}</td>
                                                    <td>${o.leader}</td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>

                                    </table>

                                    ${messE} <br>
                                    <a class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm" href="uploadExcel?go=cfDTB">Import yourdata</a>




                                </div>
                            </div>
                        </div>    

                        <div class="card shadow mb-4">
                            <div class="card-body">
                                <p>

                                    <button class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm" type="button" data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
                                        Change class
                                    </button>
                                </p>
                                <div class="collapse" id="collapseExample">

                                    <c:forEach var="o" items="${vectC}">
                                        <a href="uploadExcel?go=uploadE&class_id=${o.id}"
                                           class="${classID == o.id ? "text-info" : ""}"
                                           >${o.classCode}</a> |
                                    </c:forEach>

                                </div>

                                <div>
                                    Some infomation about this class: 
                                    <ul>
                                        <c:forEach var="o" items="${team}">
                                            <li>  ${o.team_name} - ${o.topic_code} - ${o.topic_name} </li>
                                            </c:forEach>
                                    </ul>

                                </div>
                            </div>
                        </div>




                        <script>
                            function submitForm(form) {
                                swal({
                                    title: "Are you sure?",
                                    text: "This form will be submitted",
                                    icon: "warning",
                                    buttons: true,
                                    dangerMode: true,
                                })
                                        .then(function (isOkay) {
                                            if (isOkay) {
                                                form.submit();
                                            }
                                        });
                                return false;
                            }

                            function Cool() {
                                swal("Good job!", "You clicked the button!", "success");
                            }
                        </script>

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

    </body>

</html>
