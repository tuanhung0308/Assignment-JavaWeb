
<%@page import="entity.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Product proError = (Product) request.getAttribute("ACCOUNT_ERROR");
            if (proError == null) {
                proError = new Product();
            }
        %>

        <form action="MainController" method="post">
            <b>Create Product</b></br>

            <input type="hidden" name="pid" required="" value="<%= request.getParameter("pid")%>"></br>
            Product Name <input type="text" name="pname" required="" value="<%= request.getParameter("pname")%>"></br>
            Quantity <input type="number" name="quantity" required="" value="<%= request.getParameter("quantity")%>"></br>
            Price: <input type="text" name="price" required="" value="<%= request.getParameter("price")%>"></br>
            Image: <input type="text" name="image" required="" value="<%= request.getParameter("image")%>"></br>
            Description: <input type="text" name="description" required="" value="<%= request.getParameter("description")%>"></br>
            Status: <input type="number" name="status" required="" value="<%= request.getParameter("status")%>"></br>
            Category ID: <input type="number" name="cateId" required="" value="<%= request.getParameter("cateId")%>">

            <input type="submit" name="action" value="updatePro"/>
            Do you want back ? <a href="MainController?action=displayAllPro&admin=<%= request.getParameter("admin")%>">Back</a>

        </form>
    </body>
</html>
