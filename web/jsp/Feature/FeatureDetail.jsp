<%-- 
    Document   : FeatureDetail
    Created on : 15-Jun-2022, 09:02:51
    Author     : Admin
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="Enitiy.Feature"%>
<%@page import="java.util.Vector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Group 1 - Feature Detail</title>
        <!-- Custom fonts for this template-->
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

        <!-- Custom styles for this template-->
        <link href="css/sb-admin-2.min.css" rel="stylesheet">
        <link rel="icon" href="img/cai nay hoi la.png" type="image/gif" sizes="16x16">
        <link href="css/PhanhCss.css" rel="stylesheet">
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
                        <!-- Page Heading -->
                    <%
                                  ArrayList<Feature> vect = (ArrayList<Feature>) request.getAttribute("ClassList");
                                   int teamid = Integer.parseInt((String)request.getAttribute("class"));
                    %>


                    <h1 style="font-weight: bold">Feature Details</h1>

                    <form action="FeatureDetail" method="post">
                        <input type="hidden" name="go" value="updateFeature"/>

                        <table border="0">
                            <tbody>
                                <tr>                                                           
                                    <td>  <input type="text" name="feature_id" value="${fe.feature_id}" hidden/>
                                        <a style="margin-left: 200px">Class-Team: </a>
                                        <input class="search" type="hidden" name="team_class" value="${fe.team_id}" >${fe.class_code}-${fe.team_name}                       
                                        </td>
                                </tr>

                                <tr>
                                    <td><a style="margin-left: 200px">Feature Name:</a>
                                        <br><input style="margin-left: 200px;" maxlength="140" type="text" name="feature_name" value="${fe.feature_name}" required/>
                                    </td> 
                                </tr>                  
                                <tr>

                                    <td><a style="margin-left: 200px">Status:</a> 
                                        <br><input style="margin-left: 200px" type="radio" name="status" value="1" ${fe.status == 1?"checked":""}>Active
                                        <input style="margin-left: 25px" type="radio" name="status" value="2"  ${fe.status == 2?"checked":""}>Deactive
                                    </td>
                                </tr> 

                                <tr>
                                    <td><a style="margin-left: 200px">Description:</a>
                                        <br><textarea style="width: 300px; height: 100px; margin-left: 200px" name="description" rows="1" cols="1">${fe.description}
                                        </textarea>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <br><input style="margin-left: 200px"  name= "submit"type="submit" value="Submit" style="width: 80px" />
                    </form>  

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
<script src="https://unpkg.com/ionicons@4.5.10-0/dist/ionicons.js"></script>
<!-- Page level custom scripts -->
<script src="js/demo/chart-area-demo.js"></script>
<script src="js/demo/chart-pie-demo.js"></script>

</body>

</html>
