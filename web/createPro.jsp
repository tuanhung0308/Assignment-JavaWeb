
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

            Product ID: <input type="text" name="pid" required="" value=""></br>
            Product Name <input type="text" name="pname" required="" value=""></br>
            Quantity <input type="number" name="quantity" required="" value=""></br>
            Price: <input type="text" name="price" required="" value=""></br>
            Image: <input type="text" name="image" required="" value=""></br>
            Description: <input type="text" name="description" required="" value=""></br>
            Status: <input type="number" name="status" required="" value=""></br>
            Category ID: <input type="number" name="cateId" required="" value="">

            <input type="submit" name="action" value="createPro"/>
            Do you want back ? <a href="MainController?action=displayAllPro&admin=<%= request.getParameter("admin")%>">Back</a>

        </form>
    </body>
</html>
