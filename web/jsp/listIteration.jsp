<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : ListSetting
    Created on : May 17, 2022, 9:12:50 PM
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

        <title>Group 1 - List Setting</title>

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
        <link href="css/CaiNayCuaThanh.css" rel="stylesheet">

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
                        <div class="container-fluid">

                            <!-- Page Heading -->



                            <div>   

                            </div>                                                             


                            <!-- DataTales Example -->
                            <div class="card shadow mb-4">
                                <div class="card-header py-3">
                                    <h6 class="m-0 font-weight-bold text-primary">Iteration Table</h6>
                                </div>
                                <div style="padding-left: 30px">
                                    <form action="IterationListServlet" method="POST">
                                        Subject Code:
                                        <select id ="subjectId" name="subjectId">
                                            <option value="all">List all</option>
                                        <c:forEach var="o" items="${listSubId}">
                                            <option value="${o.subject_id}" ${subId == o.subject_id ? "selected" :""}>${o.subject_code}</option>
                                        </c:forEach>

                                            </select>
                                        Iteration Name : <input  type="text" name="IteName">
                                        <input class="btn btn-primary" type="submit" name="submit" value="search">
                                </form>
                                <div><a class="btn btn-outline-primary" href="IterationListServlet?go=addIteration">Add Iteration</a></div>
                            </div>

                            <div class="card-body">
                                <div class="table-responsive">

                                    <table class="table table-bordered" width="100%" cellspacing="0">

                                        <thead>
                                            <tr>
                                                <th>iteration_id</th>
                                                <th>subject_code</th>
                                                <th>iteration_name</th>                                                                                           
                                                <th>duration</th>
                                                <th>Status</th>
                                                <th>Action</th>

                                            </tr>
                                        </thead>                                   
                                        <tbody>
                                            <c:forEach var="o" items="${list}">
                                                <tr>
                                                    <td>${o.iteration_id}</td>
                                                    <td>${o.subject_code}</td>
                                                    <td>${o.iteration_name}</td>
                                                    <td>${o.duration}</td>
                                                    <td><form action="IterationListServlet?go=updateStatus" method="Post">
                                                            <input type="hidden" name="iteId" value="${o.iteration_id}">
                                                            <select class="form-control form-control-user id" id ="status" name="status" onchange="this.form.submit()">
                                                                <option value="1" ${o.status == 1 ? "selected" : ""}>Active</option>
                                                                <option value="2" ${o.status == 2 ? "selected" : ""}>Deactive</option>                  
                                                            </select>
                                                        </form></td>                                                    
                                                    <td><a class="btn btn-outline-primary" href="IterationListServlet?go=updateIteration&iteId=${o.iteration_id}&subjectId=${o.subject_id}">Edit</a>
                                                        
                                                        <a class="btn btn-outline-danger" href="IterationListServlet?go=deleteIteration&iteId=${o.iteration_id}&subjectId=${o.subject_id}">Delete</a></td>
                                                </tr>                    
                                            </c:forEach>

                                        </tbody>
                                    </table>

                                    <c:if test="${page>1}">
                                        <a class="btn btn-outline-secondary" href="IterationListServlet?go=listAllIteration&page=${page - 1}" >pre</a>
                                    </c:if>

                                    ${page}
                                    <c:if test="${page!=countPage}">
                                        <a class="btn btn-outline-secondary" href="IterationListServlet?go=listAllIteration&page=${page + 1}">next</a>
                                    </c:if>



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
        <jsp:include page="LogOut.jsp"></jsp:include>

        <!-- Bootstrap core JavaScript-->
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

        <!-- Core plugin JavaScript-->
        <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

        <!-- Custom scripts for all pages-->
        <script src="js/sb-admin-2.min.js"></script>

        <!-- Page level plugins -->
        <script src="vendor/datatables/jquery.dataTables.min.js"></script>
        <script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>

        <!-- Page level custom scripts -->
        <script src="js/demo/datatables-demo.js"></script>

    </body>
</html>
