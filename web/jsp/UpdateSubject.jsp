<%-- 
    Document   : Home
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

        <title>G1 - Update Subject</title>
        <link rel="stylesheet" href="fnon.min.css">
        <!-- Custom fonts for this template-->
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">
        <link href="css/SenCss.css" rel="stylesheet">
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
                            <h3>Update Subject</h3>
                        ${err}
                        <!--<form action="UpdateSubject" method="POST">-->
                        <input  class="form-control form-control-user id" type="hidden" name="id" value="${id}" />
                        Subject Code 
                        <input  class="form-control form-control-user subject_code" type="text" name="subject_code" value="${code != null ? code : s.subject_code}"/>
                        Subject Name 
                        <input class="form-control form-control-user subject_name" type="text" name="subject_name" value="${name != null ? name : s.subject_name}" />
                        Author Name 
                        <select name="author" class="form-control form-control-user author">
                            <c:forEach var="o" items="${listU}">
                                <c:if test="${author == null}">
                                    <option ${s.author_name.equals(o.fullname) ? "selected" : ""} value="${o.user_id}">${o.fullname}</option>
                                </c:if>
                                <c:if test="${author != null}">
                                    <option ${author == o.user_id ? "selected" : ""} value="${o.user_id}">${o.fullname}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                        Status <br>
                        <input type="radio" ${s.status == 1 ? "checked" : ""} class="status" name="status" value="1" /><span style="margin: 0 100px 0 10px">Active</span>
                        <input type="radio" ${s.status == 2 ? "checked" : ""} class="status" name="status" value="2" /><span style="margin: 0 100px 0 10px">Not Active</span><br>
                        <input type="submit" onclick="Confirm()" name="update" class="update" value="Update" />
                        <!--</form>-->
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
        <div id="confirm"></div>

        <!-- Scroll to Top Button-->
        <a class="scroll-to-top rounded" href="#page-top">
            <i class="fas fa-angle-up"></i>
        </a>


        <!-- Logout Modal-->
        <jsp:include page="LogOut.jsp"></jsp:include>
            <script src="js/fnon.min.js"></script>
            <!-- Bootstrap core JavaScript-->
            <script src="vendor/jquery/jquery.min.js"></script>
            <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
            
            <!-- Core plugin JavaScript-->
            <script src="vendor/jquery-easing/jquery.easing.min.js"></script>
            
            <!-- Need for confirm-->
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
            <!-- End Need for confirm-->
            
            <script>

                function Confirm() {
                    const id = document.querySelector(".id").value;
                    const subject_code = document.querySelector(".subject_code").value;
                    const subject_name = document.querySelector(".subject_name").value;
                    const author = document.querySelector(".author").value;
                    const status = document.querySelector(".status").value;
                    const update = document.querySelector(".update").value;

                    var idConfirm = "updateSubject";
                    $.ajax({
                        url: "/g1/Confirm",
                        type: "get",
                        data: {
                            idConfirm: idConfirm,
                            id: id,
                            subject_code: subject_code,
                            subject_name: subject_name,
                            author: author,
                            status: status,
                            update: update
                        },
                        success: function (data) {
                            var confirm = document.getElementById("confirm");
                            confirm.innerHTML = data;
                            $("#confirm1").modal("show");
                        },
                        error: function (xhr) {
                        }
                    });
                }
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
        <!-- Custom scripts for all pages-->
        <script src="js/sb-admin-2.min.js"></script>
        <script src="js/SenJS.js"></script>
        <!-- Page level plugins -->
        <script src="vendor/chart.js/Chart.min.js"></script>

        <!-- Page level custom scripts -->
        <script src="js/demo/chart-area-demo.js"></script>
        <script src="js/demo/chart-pie-demo.js"></script>

    </body>

</html>
