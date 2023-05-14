

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
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">

    </head>
    <body>
        <header> 
            <style>
                .Welcome{
                    display: flex;
                    justify-content: space-between;
                    height: 100px;
                    align-items: center;
                    padding: 0 150px;
                }
                .he{
                    display: flex;
                    justify-content: space-between;
                    height: 100px;
                    align-items: center;
                    padding: 0 100px;
                }
            </style>
            <div class="Welcome">
                <h1 class="Welcome">Welcome <%= request.getParameter("admin")%> </h1>
                <a class="btn btn-danger" href="MainController?action=Logout" >Log out</a><br><br>
            </div>
        </header>
        <div class="he">
            <div>

                <a class="btn btn-primary" href="MainController?action=displayCus&admin=<%= request.getParameter("admin")%>" >Customer Manager</a>
                <a class="btn btn-primary" href="MainController?action=displayAllPro&admin=<%= request.getParameter("admin")%>" >Product Manager</a>
                <a class="btn btn-primary" href="MainController?action=displayBill&admin=<%= request.getParameter("admin")%>" >Bill Manager</a>
            </div>
            <%

                        List<Customer> listCProduct = (List<Customer>) session.getAttribute("LIST_CUS");
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
            <%} %>

            <a class="btn btn-success" href="MainController?action=createCus&admin=<%=request.getParameter("admin")%>">Create Customer</a>
            <%
                String search = request.getParameter("search");
                if (search == null) {
                    search = "";
                }

            %>

            <form action="MainController" >
                Search customer: <input type="text" name="search"  value="<%= search%>" placeholder="input your text"/>
                <input type="submit" name="action"  value="searchCus"/>
                <input type="hidden" name="admin" value="<%=request.getParameter("admin")%>"/>
            </form>       

        </div>

        <div class="container mt-3">
            <table border="1">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Customer Name</th>
                        <th>User Name</th>
                        <th>Password</th>  
                        <th>Address</th>
                        <th>Phone</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    <%

                        for (Customer pro : listCProduct) {
                    %>
                <form action="MainController">
                    <tr>
                        <td><%=pro.getCid()%></td>
                        <td><%=pro.getCname()%></td>
                        <td><%=pro.getUsername()%></td>
                        <td><%=pro.getPassword()%></td> 
                        <td><%=pro.getAddress()%></td>
                        <td><%=pro.getPhone()%></td>
                        <td><%=pro.getTatus()%></td>
                        <td>
                            <input type="hidden" name="admin" value="<%= request.getParameter("admin")%>"/>
                            <input type="hidden" name="cid" value="<%=pro.getCid()%>"/>
                            <input type="submit"  name="action" value="deleteCus"/>                        
                        </td>
                        <td>
                            <input type="hidden" name="admin" value="<%= request.getParameter("admin")%>"/>
                            <input type="hidden" name="cid" value="<%=pro.getCid()%>"/>
                            <input type="hidden" name="cname" value="<%=pro.getCname()%>"/>
                            <input type="hidden" name="username" value="<%=pro.getUsername()%>"/>
                            <input type="hidden" name="password" value="<%=pro.getPassword()%>"/>
                            <input type="hidden" name="address" value="<%=pro.getAddress()%>"/>
                            <input type="hidden" name="phone" value="<%=pro.getPhone()%>"/>
                            <input type="hidden" name="status" value="<%=pro.getTatus()%>"/>                       
                            <input type="submit"  name="action" value="editCus"/>                        
                        </td>
                    </tr>                                                                                        
                </form>

                <%
                    }
                        }
                    }
                %>
            </table>
        </div>
    </body>
</html>
