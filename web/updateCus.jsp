
<%@page import="entity.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Customer proError = (Customer) request.getAttribute("ACCOUNT_ERROR");
            if (proError == null) {
                proError = new Customer();
            }
        %>

        <form action="MainController" method="post">
            <b>Update Account</b></br>

            <input type="hidden" name="cid" required="" value="<%= request.getParameter("cid")%>"></br>
            Customer Name <input type="text" name="cname" required="" value="<%= request.getParameter("cname")%>"></br>
            User Name <input type="text" name="username" required="" value="<%= request.getParameter("username")%>"></br>
            Password: <input type="password" name="password" required="" value="<%= request.getParameter("password")%>"></br>
            Address: <input type="text" name="address" required="" value="<%= request.getParameter("address")%>"></br>
            Phone: <input type="text" name="phone" required="" value="<%= request.getParameter("phone")%>"></br>
            <input type="hidden" name="status" readonly="" value="<%= request.getParameter("status")%>">

            <input type="submit" name="action" value="updateCus"/>
            Do you want back ? <a href="MainController?action=displayCus&admin=<%= request.getParameter("admin")%>">Back</a>

        </form>
    </body>
</html>
