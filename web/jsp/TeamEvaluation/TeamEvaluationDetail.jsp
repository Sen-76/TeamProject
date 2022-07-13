<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Vector"%>
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

        <title>Group 1 - Team evaluation detail</title>

        <!-- Custom fonts for this template -->
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
        <link href="css/CaiNayCuaThanh.css" rel="stylesheet">
    </head>
    <body id="page-top">
        <div id="wrapper">

            <!-- Sidebar -->
            <jsp:include page="../Sidebar.jsp"></jsp:include>
                <div id="content-wrapper" class="d-flex flex-column">
                    <div id="content">
                    <jsp:include page="../Header.jsp"></jsp:include>
                    <%
                        Team team = (Team) request.getAttribute("Team");
                        TeamEvaluation tEva = (TeamEvaluation)request.getAttribute("TeamEvaluation");
                        User user = (User)request.getAttribute("User");
                        Class_s class_s = (Class_s)request.getAttribute("Class_s");
                    %>
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="p-5">
                                    <div class="text-center">
                                        <h1 class="h4 text-gray-900 mb-4">Team Evaluation Details</h1>
                                    </div>
                                    <form action="TeamList?go=listAllTeam&cid=4" method="post" onsubmit="return ValidateForm(this);">
                                        <input type="hidden" name="go" value="UpdateEval" />
                                        <c:if test="${Loged.role_id == 2}">
                                            <table style="width:100%;max-width: 700px; border: 0;" cellpadding="4" cellspacing="0">
                                                <tr>
                                                    <td colspan="2">
                                                        <br /> <b>Team</b>
                                                    </td>
                                                </tr>
                                                <td style="width:50%">
                                                    <label for="LeaderName">Team leader name*:</label><br />
                                                    <input name="LeaderName" type="text" maxlength="100" value="<%= user.getFullname()%>" class="form-control" style="width:100%;max-width: 300px;" readonly />
                                                    <label for="LeaderName">Evaluation ID:</label><br />
                                                    <input name="LeaderName" type="text" maxlength="100" value="${o.eva_id}" class="form-control" style="width:100%;max-width: 300px;" readonly />
                                                    <label for="LeaderName">Criteria ID:</label><br />
                                                    <input name="LeaderName" type="text" maxlength="100" value="${o.criteria_id}" class="form-control" style="width:100%;max-width: 300px;" readonly />
                                                    <label for="Grade">Grade:</label><br />
                                                    <input name="Grade" type="text" maxlength="100" pattern="\d*" title="You must input a number" value="<%= tEva.getGrade()%>" class="form-control" style="width:100%;max-width: 300px;" />
                                                </td>
                                                <tr>
                                                    <td>

                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td colspan="2">
                                                        <br /> <b>Student's performance</b>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td colspan="2">
                                                        <label for="comment">What comment for this team?</label>
                                                        <textarea name="comment" rows="7" cols="40" value="<%= tEva.getNote()%>" class="form-control note" style="width:100%;max-width: 650px;"></textarea>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <br />
                                                        * - required fields
                                                        <input name="skip_Submit" type="submit" value="Update" />
                                                    </td>
                                                </tr>
                                            </table>
                                        </c:if>
                                        <c:if test="${Loged.role_id == 1}">
                                            <table style="width:100%;max-width: 700px; border: 0;" cellpadding="4" cellspacing="0">
                                                <tr>
                                                    <td colspan="2">
                                                        <br /> <b>Trainer</b>
                                                    </td>
                                                </tr>
                                                <td style="width:50%">
                                                    <label for="lblEvaluatorName">Trainer name: <label for="EvaluatorName"><%= class_s.getTrainerId()%></label></label><br />
                                                </td>
                                                <tr>
                                                    <td colspan="2">
                                                        <br /> <b>Team</b>
                                                    </td>
                                                </tr>
                                                <td style="width:50%">
                                                    <label for="lblLeaderName">Team leader name: <label for="LeaderName"><%= user.getFullname()%></label></label><br />
                                                    <label for="lblGrade">Grade: <label for="Grade"><%= tEva.getGrade()%></label></label><br />
                                                </td>
                                                <tr>
                                                    <td>

                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td colspan="2">
                                                        <br /> <b>Student's performance</b>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td colspan="2">
                                                        <label for="comment"><%= tEva.getNote()%></label>
                                                    </td>
                                                </tr>
                                            </table>
                                        </c:if>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
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
