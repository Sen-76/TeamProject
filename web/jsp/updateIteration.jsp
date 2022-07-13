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
                            <h1 class="h3 mb-4 text-gray-800">Update Iteration here</h1>
                            <div class="InputForm">
                                <form method="POST" action="IterationListServlet">
                                    <input type="hidden" name="go" value="updateIteration">
                                    
                                    <table >
                                        <tbody>
                                            <tr>
                                                <td> iteration_Id:</td>
                                            <c:if test="${iteUpdate.iteration_id !=null}">
                                                <td> <input type="hidden" value="${iteUpdate.iteration_id}" name="iteId"> ${iteUpdate.iteration_id}   </td>
                                            </c:if>
                                            <c:if test="${iteUpdate.iteration_id ==null}">
                                                <td> <input type="hidden" value="${iteUpdate.iteration_id}" name="iteId"> helo ${iteUpdate.iteration_id}  </td>
                                            </c:if>
                                                
                                        </tr>
                                        <tr>
                                            <td>subject_code: <td>
                                            <c:if test="${iteUpdate.subject_id != null}">
                                                <td><input type="hidden" value="${iteUpdate.subject_id}" name="subjectId">  ${iteUpdate.subject_code}  </td> 
                                            </c:if>
                                            <c:if test="${iteUpdate.subject_id == null}">
                                                <td><input type="hidden" value="${iteUpdate.subject_id}" name="subjectId">  helo ${iteUpdate.subject_code}  </td> 
                                            </c:if>                                          

                                        </tr>
                                        <tr>
                                            <td> iteration_name: </td>
                                            <td><input class="search" type="text" name="name" value="${iteUpdate.iteration_name}" required></td>
                                        </tr>
                                        <tr>
                                            <td>duration: </td>
                                            <td><input class="search" type="text" name="duration" value="${iteUpdate.duration}"></td>
                                        </tr>                                     
                                        <tr>
                                            <td>Status: </td>
                                            <td><input type="radio" name="status" value="1" checked>Active
                                                <input type="radio" name="status" value="2">Deactive</td>
                                        </tr>
                                        <tr>
                                            <td></td>
                                            <td><input class="SubBut" type="submit" value="Update" name="submit"></td>
                                        </tr>
                                    </tbody>
                                </table>                     
                            </form>
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

    </body>
</html>