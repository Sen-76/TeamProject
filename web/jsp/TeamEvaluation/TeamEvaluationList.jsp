<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="Enitiy.Team"%>
<%@page import="Enitiy.User"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.0/font/bootstrap-icons.css">

        <title>Group 1 - Team evaluation list</title>

        <!-- Custom fonts for this template -->
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="css/sb-admin-2.min.css" rel="stylesheet">
        <link rel="icon" href="img/cai nay hoi la.png" type="image/gif" sizes="16x16">

        <!-- Custom styles for this page -->
        <link href="vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
        <link href="css/css379.css" rel="stylesheet">
        <link href="css/SenCss.css" rel="stylesheet">
        <link rel="stylesheet" href="fnon.min.css">

        <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>

    </head>
    <body id="page-top">
        <div id="wrapper">
            <jsp:include page="../Sidebar.jsp"></jsp:include>
                <div id="content-wrapper" class="d-flex flex-column">
                    <div id="content">
                    <jsp:include page="../Header.jsp"></jsp:include>
                        <div class="container-fluid">
                            <h1 class="h3 mb-2 text-gray-800">Team Evaluation List</h1>
                            <div class="card shadow mb-4">
                                <nav class="navbar navbar-expand-lg navbar-light bg-light">
                                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                                        <ul class="navbar-nav mr-auto">

                                        </ul>
                                    </div>
                                </nav>
                                <div class="card-body">
                                    <div class="table-responsive">
                                        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                            <thead>
                                                <tr>
                                                    <th>Team Evaluation ID</th>
                                                    <th>Evaluation name</th>
                                                    <th>Criteria</th>
                                                    <th>Team Name</th>
                                                    <th>Grade</th>
                                                    <th>Note</th>
                                                    <th>Action</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach var="o" items="${TeamEvalList}">
                                                <tr>
                                                    <td>${o.team_eva_id}</td>
                                                    <td>${o.eva_id}</td>
                                                    <td>${o.criteria_id}</td>
                                                    <td>${o.team_id}</td>
                                                    <td>${o.grade}</td>
                                                    <td>${o.note}</td>
                                                    <td><a class="" href="TeamEvaluationDetail?go=UpdateEval&team_id=${o.team_id}"><ion-icon name="document-text-outline"></ion-icon>View evaluation</a></td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <footer class="sticky-footer bg-white">
                    <div class="container my-auto">
                        <div class="copyright text-center my-auto">
                            <span>Copyright &copy; Your Website 2020</span>
                        </div>
                    </div>
                </footer>
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
        </script>
        <script>
            document.addEventListener('DOMContentLoaded', function () {
                Fnon.Hint.Init({
                    zIndex: 9900,
                });
                // Hint
                var message = "${message}";
                var theme = "${theme}";
                var title = "${title}";
                var position = "right-top";
                var animation = "slide-left";
                Fnon.Hint[theme](message, {
                    title,
                    position,
                    animation,
                })
            });
        </script>
        <script src="js/sweetalert.min.js"></script>
        <script src="js/SenJS.js"></script>
        <script src="js/fnon.min.js"></script>
        <a class="scroll-to-top rounded" href="#page-top">
            <i class="fas fa-angle-up"></i>
        </a>
        <jsp:include page="../LogOut.jsp"></jsp:include>
            <script src="vendor/jquery/jquery.min.js"></script>
            <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
            <script src="vendor/jquery-easing/jquery.easing.min.js"></script>
            <script src="js/sb-admin-2.min.js"></script>
            <script src="vendor/chart.js/Chart.min.js"></script>
            <script src="js/demo/chart-area-demo.js"></script>
            <script src="js/demo/chart-pie-demo.js"></script>
            <script src="vendor/datatables/jquery.dataTables.min.js"></script>
            <script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>
            <script src="js/demo/datatables-demo.js"></script>
            <script>
                function do_back() {
                    var url = new URL(window.location.href);
                    url.searchParams.set("page", '${page-1}');
                    document.location.search = url.search;
                }
        </script>
        <script>
            function do_next() {
                var url = new URL(window.location.href);
                url.searchParams.set("page", '${page+1}');
                document.location.search = url.search;
            }
        </script>
    </body>
</html>
