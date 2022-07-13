<%-- 
    Document   : HomeForAd
    Created on : May 25, 2022, 10:40:50 PM
    Author     : ASUS
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<h1>Hello Author, <strong> ${Loged.fullname} </strong>!!!</h1>

<div class="row">

    <!-- Earnings (Monthly) Card Example -->

    <div class="col-xl-3 col-md-6 mb-4">
        <a href="IterationListServlet">
            <div class="card border-left-primary shadow h-100 py-2">

                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">

                            <div class="h5 mb-0 font-weight-bold text-gray-800">Iteration management</div>

                        </div>
                    </div>
                </div>

            </div>
        </a>
    </div>
    
     <div class="col-xl-3 col-md-6 mb-4">
        <a href="Criteria">
            <div class="card border-left-primary shadow h-100 py-2">

                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">

                            <div class="h5 mb-0 font-weight-bold text-gray-800">Criteria management</div>

                        </div>
                    </div>
                </div>

            </div>
        </a>
    </div>

     <div class="col-xl-3 col-md-6 mb-4">
        <a href="ClassTrainer?go=showAllByTrain&Tid=${Loged.user_id}">
            <div class="card border-left-primary shadow h-100 py-2">

                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">

                            <div class="h5 mb-0 font-weight-bold text-gray-800">View all your class</div>

                        </div>
                    </div>
                </div>

            </div>
        </a>
    </div>
    

    <!-- Pending Requests Card Example -->
    <div class="col-xl-3 col-md-6 mb-4">
        <div class="card border-left-warning shadow h-100 py-2">
            <div class="card-body">
                <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                        <div class="text-xs font-weight-bold text-warning text-uppercase mb-1">
                            Pending Requests</div>
                        <div class="h5 mb-0 font-weight-bold text-gray-800">18</div>
                    </div>
                    <div class="col-auto">
                        <i class="fas fa-comments fa-2x text-gray-300"></i>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>