<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page import="Enitiy.SubjectSetting"%>
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
                    <%
                    SubjectSetting set = (SubjectSetting)request.getAttribute("Setting");
                    List<SubjectSetting> vect = (List<SubjectSetting>) request.getAttribute("typeList");
                    int type = Integer.parseInt((String) request.getAttribute("type"));
                  
                    %>
                    <!-- Begin Page Content -->
                    <div class="container-fluid">

                        <!-- 404 Error Text -->
                        <h1>Subject Setting Details</h1>
                        <form action="SubjectSettingDetail">
                            <input type="hidden" name="go" value="UpdateDetail" />
                            <table border="0">
                                <tbody>
                                    <tr>
                                        <td>Group*
                                            <br><select name="group" style="width: 200px" required>
                                                <%
                                                    for (Setting o : vect) { %>
                                                <option value="<%=o.getType_id() %>" <%=o.getType_id() == type ? "selected" : "" %>><%=o.getType_id() %></option>>  
                                                <%       }
                                                %>
                                            </select></td>
                                        <td>Order*
                                            <br><input type="text" name="order" value="<%=set.getDisplay_order() %>" required/></td>
                                    </tr>

                                </tbody>
                            </table>
                            Name*
                            <br> <input type="text" name="lessontype" value="<%=set.getSetting_title() %>" style="width: 400px" required /></td>
                            <table border="0">
                                <tbody>
                                    <tr>
                                        <td>Value
                                            <br><input type="text" name="value" value="<%=set.getSetting_value()%>" style="width: 230px"/></td>
                                        <td>Status 
                                            <br><input type="radio" name="status" value="1" <%=set.getStatus() == 1 ? "checked" : "" %> style="margin-left: 20px" /><a style="font-size: 13px">Active</a>
                                            <input type="radio" name="status" value="2" <%=set.getStatus() == 2 ? "checked" : "" %> style="margin-left: 30px" /><a style="font-size: 13px">Deactive</a>
                                        </td>
                                    </tr>           
                                </tbody>
                            </table>
                            Description
                            <br>
                            <textarea name="scription" style="width: 400px;"><%if (set.getNote() == null) {
                                    set.setNote("");
                                }else set.setNote(set.getNote().trim());
                            out.print(set.getNote());
                                %>
                            </textarea>
                            <br> <br> <input name= "submit"type="submit" value="Submit" style="width: 80px" />
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

