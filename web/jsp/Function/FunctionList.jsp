
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
        <title>Group 1 - Function List</title>
        <!-- Custom fonts for this template-->
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

        <!-- Custom styles for this template-->
        <link href="css/sb-admin-2.min.css" rel="stylesheet">
        <link href="vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
        <link rel="icon" href="img/cai nay hoi la.png" type="image/gif" sizes="16x16">
        <link href="css/SenCss.css" rel="stylesheet">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
        <link href="css/PhanhCss.css" rel="stylesheet">
        <!-- Need copy for use alert-->
        <link rel="stylesheet" href="fnon.min.css">
        <!-- End Need copy for use alert-->
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
                        <div class="container-fluid">          

                            <h1 style="font-weight: bold" class="h3 mb-2 text-gray-800"> Function List</h1>   

                            <li>
                                <a type="submit" href="FunctionUploadExcel?go=Show&classId=" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"/>Upload</a>
                                <a href="DownloadFunction?ClassId=" 
                                   class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                                        class="fas fa-download fa-sm text-white-50"></i>Download list Function</a>
                                <a href="\g1\Template_Function.xlsx" 
                                   class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                                        class="fas fa-download fa-sm text-white-50"></i>Download template</a>
                            </li>           
                            <ul class="spbw" id="filtera" >  

                                <li>  <form action="FunctionSearch" method="POST">
                                        <label for="class_id"></label>
                                        <select onchange="" class="SelectDrop" name="class" id="">
                                            <option value="all">All Class</option>
                                        <c:forEach var="o" items="${ClassList}">
                                            <option value="${o.feature_id}" ${Id==o.feature_id?"selected" : ""}>${o.feature_name}</option>
                                        </c:forEach>
                                    </select>
                                    <label for="team_id"></label>
                                    <select onchange="" class="SelectDrop" name="team" id="">
                                        <option value="all">All Team</option>
                                        <c:forEach var="o" items="${FeIDList}">
                                            <option value="${o.feature_id}" ${Id==o.feature_id?"selected" : ""}>${o.feature_name}</option>
                                        </c:forEach>
                                    </select>
                                    <select onchange="" class="SelectDrop" name="statusSearch" id="statusSearch">
                                        <option ${o.status == 0 ? "selected" : ""} value="all">All Status</option>
                                        <option ${o.status == 1 ? "selected" : ""} value="1">Pending</option>
                                        <option ${o.status == 2 ? "selected" : ""} value="2">Planned</option>
                                        <option ${o.status == 3 ? "selected" : ""} value="3">Evaluated</option>
                                        <option ${o.status == 4 ? "selected" : ""} value="4">Rejected</option>
                                    </select>
                                    <input  type="text" name="searchName"  placeholder="Search function name" value="${txtSearch}">
                                    <input type="submit" value="Search" name="submit" style="
                                           background: #0073ca;
                                           border-radius: .35rem;
                                           color: white
                                           " >
                                </form>
                        </ul>
                        <a id="add" style="" type="submit" href="FunctionDetail?go=add" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"/>Add new function</a>

                        <!-- DataTales Example -->
                        <div class="card shadow mb-4">
                            <div class="card-header py-3">                           
                            </div>
                            <div class="card-body">
                                <div class="table-responsive">

                                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">                         
                                        <thead>
                                             <tr>                           
                                                <th>Function</th>
                                                <th>Feature</th>
                                                <th>Access_roles</th>
                                                <th>Level</th> 
                                                <th>In charge</th>
                                                <th>Priority</th>                                                                       
                                                <th>Status</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="o" items="${FunctionList}">
                                                <tr>
                                                    <td visibility: hidden>${o.function_id}</td>
                                                    <td visibility: hidden>${o.feature_id}</td>

                                                    <td>${o.team_name}</td>
                                                    <td>${o.function_name}</td>
                                                    <td>${o.feature_name}</td>
                                                    <td visibility: hidden>${o.team_id}</td>  
                                                    <td visibility: hidden>${o.owner_id}</td>  
                                                    <td>${o.access_roles}</td>
                                                    <td>${o.setting_value}</td>    
                                                    <td> ${o.fullname} </td>
                                                    <td>${o.priority}</td>

                                                    <td>
                                                        <c:if test="${o.status == 1}">
                                                            <% out.print("Pending"); %>
                                                        </c:if>
                                                        <c:if test="${o.status == 2}">
                                                            <% out.print("Planned"); %>
                                                        </c:if>
                                                        <c:if test="${o.status == 3}">
                                                            <% out.print("Evaluated"); %>
                                                        </c:if>
                                                        <c:if test="${o.status == 4}">
                                                            <% out.print("Rejected"); %>
                                                        </c:if>
                                                    </td>
                                                    <td>
                                                        <a class="EditLink" href="FunctionDetail?go=Update&fid=${o.function_id}&Owner=${o.owner_id}&Fe=${o.feature_id}&Com=${o.complexity_id}"><span class="material-symbols-outlined">
                                                                edit
                                                            </span>
                                                        </a>
                                                        <a class="" href="FunctionDetail?go=Delete&fid=${o.function_id}" onclick="return confirm('Do you sure want to remove?')"><span class="material-symbols-outlined">
                                                                delete
                                                            </span></a>
                                                    </td>
                                                </tr>                    
                                            </c:forEach>

                                        </tbody>
                                    </table>
                                    <div class="paging">
                                        <c:forEach begin="1" end="${maxP}" var="i"  >    
                                            <a class ="active" href="FunctionList?index=${i}">${i}</a>                 
                                        </c:forEach>

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
            <script src="vendor/datatables/jquery.dataTables.min.js"></script>
            <script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>

            <!-- Page level custom scripts -->
            <script src="js/demo/datatables-demo.js"></script>
            <!-- Need copy for use alert-->
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

        <script src="js/sweetalert.min.js"></script>

        <!-- End Need copy for use alert-->

    </body>

</html>
