<%-- 
    Document   : HomeExample
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

        <title>G1 - ClassUser Detail</title>

        <!-- Custom fonts for this template-->
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">
        <!-- Custom styles for this template-->
        <link href="css/sb-admin-2.min.css" rel="stylesheet">
        <link rel="icon" href="img/cai nay hoi la.png" type="image/gif" sizes="16x16">
        <link rel="stylesheet" href="fnon.min.css">
        <link href="css/SenCss.css" rel="stylesheet">
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

                        <!-- Begin Page Content -->
                        <div class="container">
                            <div class="container_head">
                                <div class="link" style="color: white">
                                    <a href="Home"> Dashboard </a>
                                    <p> / </p>
                                    <a href="#"> ${oneClass.subject_code} </a>
                                <p> / </p>
                                <a href="ClassUser4Admin?class_id=${oneClass.class_id}"> ${oneClass.class_code}</a>
                                <p> / </p>
                                <a href="#"> ${Student.userFullName}</a>
                            </div>
                            <h4 class="h4 two-lines">${oneClass.subject_code} - ${oneClass.subject_name}</h4>
                            <span class="span">Class: ${oneClass.class_code}</span>
                        </div>
                        <div style="margin-top: 200px"></div>
                        <form method="POST" action="StudentInClassDetail">
                            <li>
                                <input class="form-control form-control-user class_id" type="hidden" name="class" value="${Student.class_id}" />
                                <input class="form-control form-control-user user_id" type="hidden" name="user" value="${Student.user_id}" />
                            </li>
                            <li>
                                Student Fullname: <br><input readonly style="cursor: auto;" class="form-control form-control-user" type="text" name="name" value="${Student.userFullName}" />
                            </li>
                            <li>
                                Team Name: <br>
                                <c:if test="${teamid == null}">
                                    <select class="form-control form-control-user teamid" name="teamid">
                                        <c:forEach var="o" items="${lteam}">
                                            <option value="${o.team_id}" ${Student.team_id == o.team_id ? "selected" : ""}>${o.team_name}</option>
                                        </c:forEach>
                                    </select>
                                </c:if>
                                <c:if test="${teamid != null}">
                                    <select class="form-control form-control-user teamid" name="teamid">
                                        <c:forEach var="o" items="${lteam}">
                                            <option ${teamid == o.team_id ? "selected" : ""}>${o.team_id}</option>
                                        </c:forEach>
                                    </select>
                                </c:if>
                            </li>
                            <li>
                                Team Leader:
                                <c:if test="${lead != null}">
                                    <label>
                                        <input type="checkbox" class="lead" value="1" ${lead == 1 ? "checked" : ""} name="lead">
                                        <span><i></i></span>
                                    </label>
                                </c:if>
                                <c:if test="${lead == null}">
                                    <label>
                                        <input type="checkbox" class="lead" value="1" ${Student.team_leader == 1 ? "checked" : ""} name="lead">
                                        <span><i></i></span>
                                    </label>
                                </c:if>
                                <style>
                                    label {
                                        position: relative;
                                        width: 60px;
                                        cursor: pointer;
                                    }
                                    label input{
                                        position: relative;
                                        z-index: 1;
                                        appearance: none;
                                    }
                                    label span{
                                        position: absolute;
                                        top: 0;
                                        left: 0;
                                        width: 100%;
                                        height: 100%;
                                        background-color: #fe0000;
                                        border-radius: 80px;
                                        transition: 0.5s;
                                    }
                                    label input:checked ~ span{
                                        background-color: #05be05;
                                    }
                                    label span i{
                                        position: absolute;
                                        top: 3px;
                                        left: 3px;
                                        width: 20px;
                                        height: 20px;
                                        background-color: #fff;
                                        border-radius: 50%;
                                        transition: 0.5s;
                                    }
                                    label input:checked ~ span i{
                                        left: 36px;
                                    }
                                    label input:checked ~ span i::before{
                                        background-color: #05be05;
                                    }
                                    label input:checked ~ span i::after{
                                        background-color: #05be05;
                                        border-radius: 0;
                                        border-bottom-left-radius: 15px;
                                        border-bottom-right-radius: 15px;
                                        height: 15px;
                                        bottom: 12px;
                                    }
                                </style>
                            </li>
                            <li>
                                Dropout Date: <br><input min="1980-01-01" max="${DateNow}" class="form-control form-control-user date" type="date" name="dropout" value="${dropout == null ? Student.dropout_date : dropout}" />
                            </li>
                            <li>
                                Note: <br>
                                <textarea name="note" oninput="validateNote()" class="form-control form-control-user note" rows="4" cols="20">${note == null ? Student.user_notes : note}</textarea>
                                <span id="alertnote"></span>
                            </li>
                            <li>
                                Ongoing Evalution: <br><input oninput="validateOngoingeval()" class="form-control form-control-user ongoingeval" type="number" name="ongoingeval" value="${ongoingeval == null ? Student.ongoing_eval : ongoingeval}" />
                                <span id="alertongoingeval"></span>
                            </li>
                            <li>
                                Final Press Evalution: <br><input oninput="validateFpresseval()" class="form-control form-control-user fpresseval" type="number" name="fpresseval" value="${fpresseval == null ? Student.final_pres_eval : fpresseval}" />
                                <span id="alertfpresseval"></span>
                            </li><li>
                                Final Topic Evalution: <br><input oninput="validateFtopiceval()" class="form-control form-control-user ftopiceval" type="number" name="ftopiceval" value="${ftopiceval == null ? Student.final_topic_eval : ftopiceval}" />
                                <span id="alertftopiceval"></span>
                            </li>
                        </form>                            <input type="submit" class="suubmit" value="Update" name="update" onclick="Confirm()"/>

                    </div>
                    <!-- /.container-fluid -->
                    <div id="confirm"></div>
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
            <script>
                const txtcode1 = document.querySelector(".ftopiceval").value;
                const alert1 = document.getElementById("alertftopiceval");
                if (txtcode1.length > 145) {
                    alert1.innerHTML = "*Final Topic Evalution must be less than 145 character";
                    alert1.style.color = "#ff0000";
                }
                const txtcode2 = document.querySelector(".fpresseval").value;
                const alert2 = document.getElementById("alertfpresseval");
                if (txtcode2.length > 145) {
                    alert2.innerHTML = "*Final Press Evalution must be less than 145 character";
                    alert2.style.color = "#ff0000";
                }
                const txtcode3 = document.querySelector(".ongoingeval").value;
                const alert3 = document.getElementById("alertongoingeval");
                if (txtcode3.length > 145) {
                    alert3.innerHTML = "*Ongoing Evalution must be less than 145 character";
                    alert3.style.color = "#ff0000";
                }
                const txtcode4 = document.querySelector(".note").value;
                const alert4 = document.getElementById("alertnote");
                if (txtcode4.length > 545) {
                    alert4.innerHTML = "*Note must be less than 545 character";
                    alert4.style.color = "#ff0000";
                }
                function validateFtopiceval() {
                    var txtcode = document.querySelector(".ftopiceval").value;
                    var alert = document.getElementById("alertftopiceval");
                    if (txtcode.length > 145) {
                        alert.innerHTML = "*Final Topic Evalution must be less than 145 character";
                        alert.style.color = "#ff0000";
                    } else {
                        alert.innerHTML = "*Final Topic Evalution is Valid";
                        alert.style.color = "#00ff00";
                    }
                }
                function validateFpresseval() {
                    var txtcode = document.querySelector(".fpresseval").value;
                    var alert = document.getElementById("alertfpresseval");
                    if (txtcode.length > 145) {
                        alert.innerHTML = "*Final Press Evalution must be less than 145 character";
                        alert.style.color = "#ff0000";
                    } else {
                        alert.innerHTML = "*Final Press Evalution is Valid";
                        alert.style.color = "#00ff00";
                    }
                }
                function validateOngoingeval() {
                    var txtcode = document.querySelector(".ongoingeval").value;
                    var alert = document.getElementById("alertongoingeval");
                    if (txtcode.length > 145) {
                        alert.innerHTML = "*Ongoing Evalution must be less than 145 character";
                        alert.style.color = "#ff0000";
                    } else {
                        alert.innerHTML = "*Ongoing Evalution is Valid";
                        alert.style.color = "#00ff00";
                    }
                }
                function validateNote() {
                    var txtcode = document.querySelector(".note").value;
                    var alert = document.getElementById("alertnote");
                    if (txtcode.length > 545) {
                        alert.innerHTML = "*Note must be less than 545 character";
                        alert.style.color = "#ff0000";
                    } else {
                        alert.innerHTML = "*Note is Valid";
                        alert.style.color = "#00ff00";
                    }
                }
                function Confirm() {
                    var fpresseval = document.querySelector(".fpresseval").value;
                    var note = document.querySelector(".note").value;
                    var ongoingeval = document.querySelector(".ongoingeval").value;
                    var ftopiceval = document.querySelector(".ftopiceval").value;
                    var date = document.querySelector(".date").value;
                    var lead = 0;
                    var teamid = document.querySelector(".teamid").value;
                    var class_id = document.querySelector(".class_id").value;
                    var user_id = document.querySelector(".user_id").value;
                    if (document.querySelector(".lead").checked)
                    {
                        lead = "1";
                    }
                    var idConfirm = "UpdateClassUser";
                    $.ajax({
                        url: "/g1/Confirm",
                        type: "get",
                        data: {
                            idConfirm: idConfirm,
                            class_id: class_id,
                            teamid: teamid,
                            user_id: user_id,
                            lead: lead,
                            date: date,
                            note: note,
                            ongoingeval: ongoingeval,
                            fpresseval: fpresseval,
                            ftopiceval: ftopiceval
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
            </script>
            <script src="js/SenJS.js"></script>
            <script src="js/fnon.min.js"></script>
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
