<%-- 
    Document   : register
    Created on : Jun 23, 2023, 4:49:49 AM
    Author     : Admin
--%>

<%@page import="entity.CustomerError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">

    </head>
    <body>
        <div class="container" style="width: 50%;">
            <p>

                <%
                    CustomerError proError = (CustomerError) request.getAttribute("ACCOUNT_ERROR");
                    if (proError == null) {
                        proError = new CustomerError();
                    }
                %>
            </p>
            <div class="card">
                <article class="card-body">
                    <a href="loginAdmin.jsp" class="float-right btn btn-outline-primary">Login with role Admin</a>
                    <a href="login.jsp" class="float-right btn btn-outline-primary">Login</a>
                    <h4 class="card-title mb-4 mt-1">Register Account</h4>
                    <form action="MainController" method="post">
                        <div class="form-group">
                            <label>Customer ID</label> <%= proError.getCidError()%>
                            <input class="form-control" placeholder="customer ID" type="text" name="cid">
                        </div> 
                        <div class="form-group">
                            <label>username</label> <%= proError.getCnameError()%>
                            <input class="form-control" placeholder="username" type="text" name="username">
                        </div> 
                        <div class="form-group">
                            <label>Customer Name</label> <%= proError.getCnameError()%>
                            <input class="form-control" placeholder="Customer Name" type="text" name="cname">
                        </div> 
                        <div class="form-group">
                            <label>Your password</label> 
                            <input class="form-control" placeholder="******" type="password" name="password">
                        </div> 
                        <div class="form-group">
                            <label>Confirm Password</label> <%= proError.getConfirm()%>
                            <input class="form-control" placeholder="******" type="password" name="confirm">
                        </div> 
                        <div class="form-group" >
                            <label>Address </label> <%= proError.getAddressError()%>
                            <input class="form-control" placeholder="Address:" type="text" name="address">
                        </div> 
                        <div class="form-group"> 
                            <label>Phone </label> <%= proError.getPhoneError()%>
                            <input class="form-control" placeholder="Phone:" type="text" name="phone">
                        </div> 
                        <div class="form-group">
                            <input type="hidden" name="status" readonly="" value="1">
                            <input type="submit" name="action" value="Register" class="btn btn-primary btn-block"/>
                            Do you want back ? <a href="MainController?action=displayProduct">Back</a>
                        </div>                                                         
                    </form>
                           
                </article>
            </div>
        </div>
    </body>
</html>
