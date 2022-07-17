
<%@page import="java.util.ArrayList"%>
<%@page import="Enitiy.Function1"%>
<%@page import="java.util.Vector"%>
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

        <title>G1 - Update Function</title>

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
        <style>
            input[type="reset"] {
                border: none;
                outline: none;
                background: repeating-linear-gradient(45deg, #ff000080, #0000ff85);
                border-radius: 15px;
                padding: 5px 15px;
                color: white;
            }
            .update{
                margin-top: 10px;
            }
        </style>
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
                                 ArrayList<Function1> vect = (ArrayList<Function1>) request.getAttribute("LevelList");
                                  ArrayList<Function1> vect1 = (ArrayList<Function1>) request.getAttribute("OwnerList");
                                  ArrayList<Function1> vect2 = (ArrayList<Function1>) request.getAttribute("FeatureList");
                                 int owner = Integer.parseInt((String)request.getAttribute("owner"));
                                 int fe = Integer.parseInt((String)request.getAttribute("fe"));
                                 int com = Integer.parseInt((String)request.getAttribute("com"));
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
                                            <form class="user" action="FunctionDetail" method="post" >
                                                <input type="hidden" name="go" value="Update"/>
                                                <input type="hidden" name="function_id" value="${fu.function_id}"/>

                                                <h3>Update Function</h3>
                                                Class-Team:
                                                <input class="form-control" type="hidden" name="team_class" value="${fu.team_id}" >${fu.class_code}-${fu.team_name}
                                                <br>   Function Name:
                                                <input  class="form-control" type="text" name="function_name" value="${fu.function_name}" required/>
                                                Feature Name:
                                                <select class="form-control"  name="feature_id" >
                                                    <%
                                         for (Function1 o : vect2) { %>
                                                    <option hidden  value="<%=o.getFeature_id()%>" <%=o.getFeature_id() == fe ? "selected" : "" %>><%=o.getFeature_name()%></option>>  
                                                    <%       }
                                                    %>
                                                </select> 

                                                Level:
                                                <select class="form-control"  name="complexity_id" >
                                                    <%
                                         for (Function1 o : vect) { %>
                                                    <option  value="<%=o.getComplexity_id()%>" <%=o.getComplexity_id() == com ? "selected" : "" %>><%=o.getSetting_value()%></option>>  
                                                    <%       }
                                                    %>
                                                </select> 
                                                In charge: 
                                                <select class="form-control"  name="owner_id" >
                                                    <%
                                         for (Function1 o : vect1) { %>
                                                    <option  value="<%=o.getUser_id()%>" <%=o.getUser_id() == owner ? "selected" : "" %>><%=o.getFullname()%></option>>  
                                                    <%       }
                                                    %>
                                                </select> 
                                                Access Roles:
                                                <input  class="form-control" type="text" name="access_roles" value="${fu.access_roles}" required/>
                                                Priority:
                                                <input  class="form-control" type="text" name="priority" value="${fu.priority}" required/>
                                                <span style="color: red; font-weight: bold;">${err}</span>
                                                Status:</br>
                                                <select class="form-control" name="status">
                                                    <option ${fu.status == 1 ? "selected" : ""} value="1">Pending</option>
                                                    <option ${fu.status == 2 ? "selected" : ""} value="2">Planned</option>
                                                    <option ${fu.status == 3 ? "selected" : ""} value="3">Evaluated</option>
                                                    <option ${fu.status == 4 ? "selected" : ""} value="4">Rejected</option>
                                                </select>
                                                Description:
                                                <textarea  class="form-control" name="description">${fu.description}</textarea>
                                                <input type="submit" name="submit" class="update" onclick="return confirm('Are you sure you want to update?')" value="Update" />

                                                <br>
                                                <div>

                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->


            <script>
                function submitForm(form) {
                    swal({
                        title: "Are you sure?",
                        text: "This form will be submitted",
                        icon: "warning",
                        buttons: true,
                        dangerMode: true,
                    })
                            .then(function (isOkay) {
                                if (isOkay) {
                                    form.submit();
                                }
                            });
                    return false;
                }
            </script>
            <script src="js/SenJS.js"></script>
            <script src="js/fnon.min.js"></script>
            <!-- Footer -->
            <!--            <footer class="sticky-footer bg-white">
                            <div class="container my-auto">
                                <div class="copyright text-center my-auto">
                                    <span>Copyright &copy; Your Website 2021</span>
                                </div>
                            </div>
                        </footer>-->
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

    <!-- Page level custom scripts -->
    <script src="js/demo/chart-area-demo.js"></script>
    <script src="js/demo/chart-pie-demo.js"></script>

    <script src="js/sweetalert.min.js"></script>

</body>

</html>

