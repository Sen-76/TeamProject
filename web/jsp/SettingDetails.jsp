<%@page import="java.util.ArrayList"%>
<%@page import="Enitiy.Setting"%>
<%@page import="java.util.Vector"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>G1 - Update Setting</title>

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
                    Setting set = (Setting)request.getAttribute("Setting");
                    ArrayList<Setting> vect = (ArrayList<Setting>) request.getAttribute("typeList");
                    int type = Integer.parseInt((String) request.getAttribute("type"));
                  
                    %>
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
                                            <form action="SettingDetail">
                                                <input type="hidden" name="go" value="UpdateDetail" />


                                                <h3>Update Setting</h3>
                                                Group:
                                                <select  class="form-control" name="group" required>
                                                    <%
                                                    for (Setting o : vect) { %>
                                                    <option value="<%=o.getType_id() %>" <%=o.getType_id() == type ? "selected" : "" %>><%=o.getType_id() %></option>>  
                                                    <%       }
                                                    %>
                                                </select>
                                                Order:
                                                <input  class="form-control" type="text" name="order" value="<%=set.getDisplay_order() %>" required/>

                                                Name:
                                                <input class="form-control" type="text" name="lessontype" value="<%=set.getSetting_title() %>" required />

                                                Value:
                                                <input class="form-control"  type="text" name="value" value="<%=set.getSetting_value()%>" style=""/>
                                                Status: 
                                                <br>   <input type="radio" name="status" value="1" <%=set.getStatus() == 1 ? "checked" : "" %>/>Active
                                                <input type="radio" name="status" value="2" <%=set.getStatus() == 2 ? "checked" : "" %> style="margin-left: 100px" />Deactive

                                                <br>Description:
                                                <textarea  class="form-control" name="description">${fu.description}</textarea>
                                                <br>
                                                <input type="submit" name="submit" class="update" onclick="return confirm('Are you sure you want to update?')" value="Update" />


                                            </form>
                                        </div>
                                    </div>
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

    </body>

</html>
