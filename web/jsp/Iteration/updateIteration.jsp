<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : addSetting
    Created on : May 18, 2022, 3:54:11 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Group 1 - Add Setting</title>

        <!-- Custom fonts for this template-->
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

        <!-- Custom styles for this template-->
        <link href="css/sb-admin-2.min.css" rel="stylesheet">
        <link href="css/CaiNayCuaThanh.css" rel="stylesheet">

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

                            <div class="row">
                                <div class="col-lg-12 card" >
                                    <div class="p-5 card-body">
                                        <div>
                                            <h5 style="font-weight: bold">Update Iteration</h5>
                                        </div>
                                        <br>
                                        <form  action="IterationListServlet" method="POST">
                                            <input type="hidden" name="go" value="updateIteration" />
                                            <input type="hidden" name="iteId" value="${iteUpdate.iteration_id}" />

                                        <strong>Subject Code:</strong>
                                        <input class="form-control form-control-user id" hidden value="${iteUpdate.subject_id}" name="subjectId" >  ${iteUpdate.subject_code}
                                        <br> 
                                        <br>
                                        <strong>Iteration Name:</strong>
                                        <input class="form-control form-control-user id" type="text" name="name" value="${iteUpdate.iteration_name}" required>
                                        <br> 
                                        <strong>Duration: </strong>
                                        <input class="form-control form-control-user id" type="text" name="duration" value="${iteUpdate.duration}">
                                        <br> 
                                        <strong>Status:</strong>
                                        <input type="radio" name="status" value="1" ${iteUpdate.status == 1 ? "checked" : ""}>Active
                                        <input type="radio" name="status" value="2" ${iteUpdate.status == 2 ? "checked" : ""}>Deactivate
                                        <br>
                                        <br>
                                        <strong>Note: </strong>
                                        <textarea class="form-control form-control-user" name="note" value="${iteUpdate.note}"></textarea>
                                        <br>

                                        <input class="btn btn-primary" type="submit" value="Save" name="submit" >
                                        <input class="btn btn-primary" type="reset" value="Reset">

                                    </form>
                                    <span style="color: red; font-weight: bold;">${thongbao}</span>
                                </div> 
                                <!-- /.container-fluid -->
                            </div>
                        </div>

                        <!-- Page Heading -->

                    </div>
                    <!-- /.container-fluid -->

                </div>
                <!-- End of Main Content -->

                <!-- Footer -->
                <footer class="sticky-footer bg-white">
                    <div class="container my-auto">
                        <div class="copyright text-center my-auto">
                            <span>Copyright &copy; Your Website 2020</span>
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
        </script>

        <!-- Core plugin JavaScript-->
        <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

        <!-- Custom scripts for all pages-->
        <script src="js/sb-admin-2.min.js"></script>

    </body>
</html>