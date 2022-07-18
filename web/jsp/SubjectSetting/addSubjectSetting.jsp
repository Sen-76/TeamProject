<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : addSubject
    Created on : Jun 2, 2022, 10:04:30 PM
    Author     : tqbao
--%>
<%@page import="Enitiy.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Group 1 - Add Subject Setting</title>

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
        <div id="wrapper">
            <jsp:include page="../Sidebar.jsp"></jsp:include>
                <div id="content-wrapper" class="d-flex flex-column">
                    <div id="content">
                    <jsp:include page="../Header.jsp"></jsp:include>
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="p-5">
                                        <div class="text-center">
                                            <h1 class="h4 text-gray-900 mb-4">Add more subject setting here</h1>
                                        </div>
                                        <form method="POST" action="SubjectSettingList">
                                            <input type="hidden" name="go" value="addSubjectSetting">
                                            <table style="width:100%;max-width: 700px; border: 0;" cellpadding="4" cellspacing="0">
                                                <tbody>
                                                    <tr>
                                                        <td>Setting Type:
                                                            <br>
                                                        <select id="subjectSettingType" name="subjectSettingType" class="form-control" style="width:100%;max-width: 300px;">
                                                            <c:forEach var="o" items="${listType}">
                                                                <option value="${o.setting_id}">${o.setting_title}</option>
                                                            </c:forEach>
                                                        </select>
                                                        </td>
                                                </tr>
                                                <td style="width:50%">
                                                    <label for="title">Setting Title:</label><br />
                                                    <input type="text" placeholder="${o.setting_title}" class="form-control" style="width:100%;max-width: 300px;" name="title" required>
                                                    <label for="value">Value:</label><br />
                                                    <input type="text" pattern="\d*" title="You must input a number" class="form-control" style="width:100%;max-width: 300px;" name="value">
                                                    <label for="order">Order:</label><br />
                                                    <input type="text" pattern="\d*" title="You must input a number" class="form-control" style="width:100%;max-width: 300px;" name="order">
                                                    <label for="subjectCode">Subject Code:</label><br />
                                                    <input type="text" pattern="\d*" title="You must input a number" class="form-control" style="width:100%;max-width: 300px;" name="subjectID">
                                                </td>
                                                <tr>
                                                    <td>Status:</td>
                                                    <td><input type="radio" name="status" value="1" checked>Active
                                                        <input type="radio" name="status" value="2">Deactive</td>
                                                </tr>
                                                <tr>
                                                    <td></td>
                                                    <td><input class="SubBut" type="submit" value="Add" name="submit"></td>
                                                </tr>
                                            </tbody>
                                        </table>                 
                                    </form>
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
