
<%@page import="entity.CustomerError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            CustomerError proError = (CustomerError) request.getAttribute("ACCOUNT_ERROR");
            if (proError == null) {
                proError = new CustomerError();
            }
        %>

        <form action="MainController" method="post">
            <b>Register Account</b></br>

            Customer ID: <input type="text" name="cid" required="" value=""><%= proError.getCidError()%></br>
            Customer Name <input type="text" name="cname" required="" value=""><%= proError.getCnameError()%></br>
            User Name <input type="text" name="username" required="" value=""><%= proError.getCnameError()%></br>
            Password: <input type="password" name="password" required="" value=""></br>
            Confirm Password: <input type="password" name="confirm" required="" value=""><%= proError.getConfirm()%></br>
            Address: <input type="text" name="address" required="" value=""><%= proError.getAddressError()%></br>
            Phone: <input type="text" name="phone" required="" value=""><%= proError.getPhoneError()%></br>
            <input type="hidden" name="status" readonly="" value="1">

            <input type="submit" name="action" value="createCus"/>
            Do you want back ? <a href="MainController?action=displayCus&admin=<%= request.getParameter("admin")%>">Back</a>

        </form>
    </body>
</html>
