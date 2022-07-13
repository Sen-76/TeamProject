
<%-- 
    Document   : UpdateIssue
    Created on : Jun 15, 2022, 8:06:59 PM
    Author     : tiena
bug in update function and milestone, no selected!
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

        <title>G1 - Update Issue</title>

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
                                                <form class="user" action="IssueController?go=updateIssue" method="post" >
                                                    <input type="hidden" name="issueID" value="${issueid}"/>
                                                <h3>Update Issue</h3>
                                                Issue Title:
                                                <input  class="form-control" type="text" name="title" value="${listUpdate.issue_title}"/>
                                                Description
                                                <textarea  class="form-control" name="descrip">${listUpdate.description}</textarea>
                                                GitLab URL
                                                <input class="form-control" type="text" name="urlgit" value="${listUpdate.gitlab_url}" />
                                                Create at 
                                                <input  class="form-control" type="date" name="createAT" max="${date}" value="${listUpdate.created_at}"/>
                                                Due date
                                                <input  class="form-control" type="date" name="dueDA" min="${listUpdate.created_at}" value="${listUpdate.due_date}"/>
                                                Assign
                                                <select class="form-control" name="assID">
                                                     <c:forEach var="a" items="${listAss}">
                                                         <option value = "${listUpdate.assignee_id}" ${a.user_id == listUpdate.assignee_id}>${a.fullname}</option> 
                                                        </c:forEach>
                                                </select>
                                                Milestone</br>
                                                <select class="form-control" name="milestone">
                                                    <c:forEach var="o" items="${lstMile}">
                                                        <option value="${o.milestone_id}" ${listUpdate.milestone_id == o.milestone_id ? "selected" : ""} >${o.iterationName}</option>   

                                                    </c:forEach>
                                                </select><br>
                                                Function</br>
                                                <select class="form-control" name="function">
                                                    <c:forEach var="o" items="${lstFuntion}">
                                                        <option value="${o.function_id}" ${listUpdate.function_ids == o.function_id ? "selected" : ""} >${o.function_name}</option>   
                                                    </c:forEach>
                                                </select>
                                                Label:
                                                <input class="form-control" type="text" name="label" value="${listUpdate.label}" />
                                                Status</br>
                                                <select class="form-control" name="status">
                                                    <option ${listUpdate.status == 1 ? "selected" : ""} value="1">To Do</option>
                                                    <option ${listUpdate.status == 2 ? "selected" : ""} value="2">Pending</option>
                                                    <option ${listUpdate.status == 3 ? "selected" : ""} value="3">Done</option>
                                                </select>
                                                <input type="submit" name="update" class="update" onclick="return confirm('Are you sure you want to change?')" value="Update" />
                                                <input type="reset" name="update" class="update" value="Reset" />
                                                <br>
                                                <div>
                                                    <span style="color: red; font-weight: bold;">${err}</span>
                                                </div>
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

