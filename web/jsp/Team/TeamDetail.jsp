<%@page import="java.util.ArrayList"%>
<%@page import="Enitiy.Team"%>
<%@page import="java.util.Vector"%>

<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Group 1 - Team Detail</title>

        <!-- Custom fonts for this template-->
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

        <!-- Custom styles for this template-->
        <link href="css/sb-admin-2.min.css" rel="stylesheet">
        <link rel="icon" href="img/cai nay hoi la.png" type="image/gif" sizes="16x16">

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
                    Team team = (Team)request.getAttribute("Team");
                    //ArrayList<Team> vect = (ArrayList<Team>) request.getAttribute("classList");                  
                    %>
                    <!-- Begin Page Content -->
                    <div class="container-fluid">

                        <!-- 404 Error Text -->
                        <h1>Team Details</h1>
                        <form action="TeamDetail">
                            <input type="hidden" name="go" value="UpdateDetail" />
                            <table border="0">
                                <tbody>
                                    <tr>
                                        <td>Class Name
                                            <br><input type="text" name="order" value="<%= team.getClass_id()%>" required/></td>
                                    </tr>

                                </tbody>
                            </table>
                            Topic Code
                            <br> <input type="text" name="lessontype" value="<%= team.getTopic_code()%>" style="width: 400px" required /></td>
                            <br>
                            Topic Name
                            <br> <input type="text" name="lessontype" value="<%= team.getTopic_name()%>" style="width: 400px" required /></td>
                            <br>                        
                            GitLab URL
                            <br> <input type="text" name="lessontype" value="<%= team.getGitlab_url()%>" style="width: 400px" required /></td>
                            <table border="0">
                                <tbody>
                                    <tr>
                                        <td>Leader Name
                                            <br><select name="group" style="width: 230px" required>
                                                <option><%= team.getTeam_name()%></option>
                                            </select></td>
                                        <td>Status 
                                            <br>
                                            <input type="radio" name="status" value="1" style="margin-left: 20px" /><a style="font-size: 13px">Active</a>
                                            <input type="radio" name="status" value="2" style="margin-left: 30px" /><a style="font-size: 13px">Deactive</a>
                                        </td>
                                    </tr>           
                                </tbody>
                            </table>
                            <br> <br> <input name="submit" type="submit" value="Submit" style="width: 80px" />
                        </form>  


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

