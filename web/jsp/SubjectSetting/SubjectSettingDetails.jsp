<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page import="Enitiy.SubjectSetting"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Vector"%>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Group 1 - Subject Setting Detail</title>

        <!-- Custom fonts for this template-->
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="css/sb-admin-2.min.css" rel="stylesheet">
        <link href="css/SenCss.css" rel="stylesheet">
        <link rel="stylesheet" href="fnon.min.css">
        <link rel="icon" href="img/cai nay hoi la.png" type="image/gif" sizes="16x16">

        <!-- Custom styles for this page -->
        <link href="vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
        <link href="css/css379.css" rel="stylesheet">

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
                    <%
                    SubjectSetting sset = (SubjectSetting)request.getAttribute("SubjectSetting");
                    List<SubjectSetting> vect = (List<SubjectSetting>) request.getAttribute("typeList");
                    int type = Integer.parseInt((String) request.getAttribute("type"));
                    %>
                    <!-- Begin Page Content -->
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="p-5">
                                    <div class="text-center">
                                        <h1 class="h4 text-gray-900 mb-4">Subject Setting Details</h1>
                                    </div>
                                    <form action="SubjectSettingDetail">
                                        <input type="hidden" name="go" value="UpdateDetail" />
                                        <table style="width:100%;max-width: 700px; border: 0;" cellpadding="4" cellspacing="0">
                                            <td style="width:50%">
                                                <input type="setting_id" class="form-control" value="<%=sset.getSetting_id() %>" style="width:100%;max-width: 300px;" name="order" hidden>

                                                <label for="subjectCode">Subject Code:</label><br />
                                                <input type="text" pattern="\d*" title="You must input a number" class="form-control" value="<%=sset.getSetting_id() %>" style="width:100%;max-width: 300px;" name="subject_id">

                                                <label for="settingTitle">Setting Title:</label><br />
                                                <input type="text" class="form-control" value="<%=sset.getSetting_title() %>" style="width:100%;max-width: 300px;" name="setting_title">

                                                <label for="Value">Value:</label><br />
                                                <input type="text" pattern="\d*" title="You must input a number" class="form-control" value="<%=sset.getSetting_value() %>" style="width:100%;max-width: 300px;" name="value">

                                                <label for="Order">Order:</label><br />
                                                <input type="text" pattern="\d*" title="You must input a number" class="form-control" value="<%=sset.getDisplay_order() %>" style="width:100%;max-width: 300px;" name="order">
                                                <label for="status">Status:</label><br />
                                            <tr>
                                                <td>
                                                    <br><input type="radio" name="status" value="1" <%=sset.getStatus() == 1 ? "checked" : "" %> style="margin-left: 20px" /><a style="font-size: 13px">Active</a>
                                                    <input type="radio" name="status" value="2" <%=sset.getStatus() == 2 ? "checked" : "" %> style="margin-left: 30px" /><a style="font-size: 13px">Deactive</a>
                                                </td>
                                            </tr>
                                            </td>
                                            <br>
                                        </table>
                                        <br> <br> <input name="submit" type="submit" value="Update" style="width: 80px" />
                                    </form> 
                                </div>
                            </div>
                        </div>
                    </div>
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

        <!-- Core plugin JavaScript-->
        <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

        <!-- Custom scripts for all pages-->
        <script src="js/sb-admin-2.min.js"></script>

    </body>

</html>

