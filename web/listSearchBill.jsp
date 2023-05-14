

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


        <%
            String search = request.getParameter("search");
            if (search == null) {
                search = "";
            }

        %>

        <form action="MainController" >
            Search<input type="date" name="search"  value="<%= search%>"/>
            <input type="submit" name="action"  value="searchBill"/>
            <input type="hidden" name="admin" value="<%=request.getParameter("admin")%>"/>
        </form>       





        <%

            List<Bill> listCProduct = (List<Bill>) session.getAttribute("LIST_SEARCH");
            if (listCProduct == null) {
        %>    

        <h1>No Info</h1>
        <%  } else {
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
                        <th>Date Create</th>
                        <th>Address</th>
                        <th>Phone</th>  
                        <th>Note</th>
                        <th>Total Money</th>
                        <th>Status</th>
                        <th>Customer ID</th>
                    </tr>
                </thead>
                <tbody>
                    <%

                        for (Bill pro : listCProduct) {
                    %>
                <form action="MainController">
                    <tr>
                        <td><%=pro.getBid()%></td>
                        <td><%=pro.getDateCreate()%></td>
                        <td><%=pro.getRecAddress()%></td>
                        <td><%=pro.getRecPhone()%></td> 
                        <td><%=pro.getNote()%></td>
                        <td><%=pro.getTotalMoney()%></td>

                        <% if (pro.getStatus() == 0) {
                        %>
                        <td>Wait</td>
                        <%}%>
                        <% if (pro.getStatus() == 1) {
                        %>
                        <td>Process</td>
                        <%}%>
                        <% if (pro.getStatus() == 2) {
                        %>
                        <td>Done</td>
                        <%}%>

                        <td><%=pro.getCid()%></td>
                        <td>
                            <input type="hidden" name="admin" value="<%= request.getParameter("admin")%>"/>
                            <input type="hidden" name="bid" value="<%=pro.getBid()%>"/>
                            <input type="hidden" name="status" value="<%=pro.getStatus()%>"/>
                            <input type="submit"  name="action" value="displayBillDetail"/>                        
                        </td>
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
