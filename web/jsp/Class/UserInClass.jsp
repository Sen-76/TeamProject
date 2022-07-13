<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : UserInClass
    Created on : Jun 11, 2022, 8:15:58 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
        String nameF = (String) request.getAttribute("nameD");
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=StudentInClass_"+nameF+".xls");
        %>
        <table border="1">
            <thead>
                <tr>
                    
                    <th>roll_number</th>
                    <th>fullname</th>
                    <th>gender</th>
                    <th>date_of_birth</th>
                    <th>email</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="o" items="${vect}">
                    <tr>
                        
                        <td>${o.roll_number}</td>
                        <td>${o.fullname}</td>
                        <td>${o.gender == 1 ? "Male" : "Female"}</td>
                        <td>${o.date_of_birth}</td>
                        <td>${o.email}</td>

                    </tr>
                </c:forEach>


            </tbody>
        </table>



    </body>
</html>
