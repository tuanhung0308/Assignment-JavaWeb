

<%@page import="entity.BillDetail"%>
<%@page import="entity.Bill"%>
<%@page import="entity.Customer"%>
<%@page import="entity.Product"%>
<%@page import="java.util.List"%>
<%@page import="entity.Category"%>
<%@page import="entity.Category"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
    </head>
    <body>
        <a href="MainController?action=updateStatus&status=<%= request.getParameter("status")%>&admin=<%= request.getParameter("admin")%>&bid=<%= request.getParameter("bid")%>">Change Status</a>

        <%

            List<BillDetail> listCProduct = (List<BillDetail>) session.getAttribute("LIST_PRODUCT_ID");
            if (listCProduct != null) {
                if (!listCProduct.isEmpty()) {

        %>

        <%if (request.getParameter("admin") == null) {%>
        <div>           
            <a href="login.jsp" >Log in</a>
            <a href="register.jsp">Register</a>
        </div>

        <%} else if (request.getParameter("admin").equals("null")) {%>
        <div>           
            <a href="login.jsp" >Log in</a>
            <a href="register.jsp">Register</a>
        </div>
        <%} else {%>
        <h3><%=request.getParameter("admin")%></h3>
        <a href="MainController?action=Logout" >Log out</a>
        <%}%>


        <div class="container mt-3">
            <table border="1">
                <thead>
                    <tr>
                        <th>Bill ID</th>
                        <th>Product ID</th>
                        <th>Quantity</th>
                        <th>Price</th> 
                        <th>Subtotal</th> 
                        <th>Bill ID</th>                    
                    </tr>
                </thead>
                <tbody>
                    <%

                        for (BillDetail pro : listCProduct) {
                    %>
                <form action="MainController">
                    <tr>
                        <td><%=pro.getBid()%></td>
                        <td><%=pro.getPid()%></td>
                        <td><%=pro.getBuyQuantity()%></td>
                        <td><%=pro.getBuyPrice()%></td> 
                        <td><%=pro.getSubtotal()%></td>
                        <td><%= pro.getBillId()%></td>
                    </tr>                                                                                        
                </form>

                <%
                    }
                %>
                <%
                    String error = (String) session.getAttribute("ERROR");
                    if (error == null) {
                        error = "";
                    }
                %>
                <%=error%>
                <%
                        }
                    }
                %>
            </table>
        </div>
    </body>
</html>
