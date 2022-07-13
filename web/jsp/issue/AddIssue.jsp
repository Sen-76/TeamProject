<%-- 
    Document   : AddIssue
    Created on : Jun 16, 2022, 11:38:56 AM
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

        <title>G1 - Add new Issue</title>

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
        <!-- Page Wrapper -->
        <style>
            input[type="reset"] {
                border: none;
                outline: none;
                background: repeating-linear-gradient(45deg, #ff000080, #0000ff85);
                border-radius: 15px;
                padding: 5px 15px;
                color: white;
            }
            .update{
                margin-top: 10px;
            }
        </style>
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
                                                <div class="text-center">
                                                </div>
                                                <form class="user" action="IssueController?go=addIssue" method="post">
                                                    <h3>Add new Issue</h3>
                                                    Issue Title:
                                                    <input value="${title}"  class="form-control" type="text" name="title" placeholder="issue title" />
                                                Description
                                                <textarea  class="form-control" name="descrip" placeholder="description">${description}</textarea>
                                                GitLab URL
                                                <input class="form-control" type="url" name="urlgit" placeholder="gitlab url" value="${urlgit}" />
                                                Create at 
                                                <input  class="form-control" max="${date}" type="date" name="createAT" value="${date}"/>
                                                Due date
                                                <input  class="form-control" type="date" min="${date}" value="${dueDA}" name="dueDA"/>
                                                Assign
                                                <select class="form-control" name="assID">
                                                    <c:forEach var="a" items="${listAss}">
                                                        <option value = "${a.user_id}">${a.fullname}</option> 
                                                    </c:forEach>
                                                </select>
                                                Milestone</br>
                                                <select class="form-control" name="mile">
                                                    <c:forEach var="o" items="${lstMile}">
                                                        <option value="${o.milestone_id}" >${o.iterationName}</option>
                                                    </c:forEach>
                                                </select></br>
                                                Function</br>
                                                <select class="form-control" name="funtion">
                                                    <c:forEach var="a" items="${lstFunc}">
                                                        <option value="${a.function_id}">${a.function_name}</option>
                                                    </c:forEach>
                                                </select></br>
                                                Label:
                                                <input value="${label}" class="form-control" type="text" name="label" placeholder="label" />
                                                Status</br>
                                                <select class="form-control" name="status">
                                                    <option selected value="1">To Do</option>
                                                    <option value="2">Pending</option>
                                                    <option value="3">Done</option>
                                                </select> <br>
                                                <input type="submit" name="addnew" class="update" onclick="return confirm('Are you sure you want to add this issue?')" value="Add" />
                                                <input type="reset" name="update" class="update" value="Reset" /> <br>
                                                <span style="color: red; font-weight: bold;">${err}</span>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->


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
            <script src="js/SenJS.js"></script>
            <script src="js/fnon.min.js"></script>
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
