

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

        <title>G1 - List All Issue</title>

        <!-- Custom fonts for this template-->
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

        <!-- Custom styles for this template-->
        <link href="css/sb-admin-2.min.css" rel="stylesheet">
        <link href="css/SenCss.css" rel="stylesheet">
        <link rel="icon" href="img/cai nay hoi la.png" type="image/gif" sizes="16x16">

        <link rel="stylesheet" href="fnon.min.css">

    </head>

    <body id="page-top">


        <style>
            .EditLink {
                background-color: #a054e8d8;
                color: rgb(209, 216, 222);
                padding: 1px 15px;
                border-radius: 15px;
                border: solid 1px black;
                color: aliceblue;
                box-shadow: 1px 1px rgb(159 156 156);
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
                            <div class="card shadow mb-4">
                                <div class="card-header py-3">
                                    <h6 class="m-0 font-weight-bold text-primary">All Issues:</h6>
                                </div>
                                <div class="card-body">
                                    <div class="table-responsive">
                                        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                            <thead>
                                                <tr>
                                                    <th>Title</th>
                                                    <th>Description</th>              
                                                    <th>Gitlab URL</th>
                                                    <th>Created_at</th>
                                                    <th>Due date</th>
                                                    <th>Label</th>
                                                    <th>Status</th>
                                                    <th>Action</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach var="o" items="${listByUser}">
                                                <tr>
                                                    <td>${o.issue_title}</td>
                                                    <td>${o.description}</td>
                                                    <td>${o.gitlab_url}</td>
                                                    <td>${o.created_at}</td>
                                                    <td>${o.due_date}</td>
                                                    <td>${o.label}</td>
                                                    <td>
                                                        <form action="IssueController?go=changeStatus" method="post">
                                                            <input type="hidden" name="issueid" value="${o.issue_id}"/>
                                                            <select class="form-control form-control-user" name="status" onchange="this.form.submit()">
                                                                <option ${o.status == 1 ? "selected" : ""} value="1">To Do</option>
                                                                <option ${o.status == 2 ? "selected" : ""} value="2">Pending</option>
                                                                <option ${o.status == 3 ? "selected" : ""} value="3">Done</option>
                                                            </select>
                                                        </form>
                                                    </td>
                                                    <td>
                                                        <a class="EditLink" href="IssueController?go=updateIssue&issueID=${o.issue_id}">Edit</a>
                                                        <a onclick="return confirm('Are you sure you want to delete this issue?')" class="EditLink" href="IssueController?go=removeIssue&issueID=${o.issue_id}">Delete</a>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                                </div>
                            </div>    
                        </div>
                    </div>
                    <!-- /.container-fluid -->

                </div>
                <!-- End of Main Content -->

                <script src="js/SenJS.js"></script>
                <script src="js/fnon.min.js"></script>
                <script src="js/sweetalert.min.js"></script>
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
                <!-- Footer -->
                <!--            <footer class="sticky-footer bg-white">
                                <div class="container my-auto">
                                    <div class="copyright text-center my-auto">
                                        <span>Copyright &copy; Your Website 2021</span>
                                    </div>
                                </div>
                            </footer>-->
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
