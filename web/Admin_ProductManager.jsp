

<%@page import="entity.Product"%>
<%@page import="java.util.List"%>
<%@page import="entity.Category"%>
<%@page import="entity.Category"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Products Page</title>
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

            <a class="btn btn-success" href="MainController?action=createPro&admin=<%= request.getParameter("admin")%>">Import New Product</a>
            <%
                String search = request.getParameter("search");
                if (search == null) {
                    search = "";
                }

            %>

            <form action="MainController" >
                Search product: <input type="text" name="search"  value="<%= search%>" placeholder="input your text"/>
                <input type="submit" name="action"  value="searchPro"/>
                <input type="hidden" name="admin" value="<%=request.getParameter("admin")%>"/>
            </form>       


            <%

                List<Product> listCProduct = (List<Product>) session.getAttribute("LIST_ALL_PRODUCT");
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
            <%}%>

        </div>
        <div class="container mt-3">
            <table border="1">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Product Name</th>
                        <th>Quantity</th>
                        <th>Price</th>  
                        <th>Image</th>
                        <th>Description</th>
                        <th>Status</th>
                        <th>Cate Id</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        int count = 1;
                        for (Product pro : listCProduct) {
                    %>
                <form action="MainController">
                    <tr>
                        <td><%=pro.getPid()%></td>
                        <td><%=pro.getPname()%></td>
                        <td><input type="number" min="1"  name="quantity" value="1"/></td>
                        <td><%=pro.getPrice()%></td>
                        <td><img src="<%=pro.getImage()%>" alt="Image" width=250 height=250></td>
                        <td><%=pro.getDescription()%></td>   
                        <td><%=pro.getStatus()%></td>   
                        <td><%=pro.getCateId()%></td>   
                        <td>
                            <input type="hidden" name="admin" value="<%= request.getParameter("admin")%>"/>
                            <input type="hidden" name="cid" value="<%= request.getAttribute("cid")%>"/>
                            <input type="submit"  name="action" value="deletePro"/>       
                        </td>   
                        <td>
                            <input type="hidden" name="admin" value="<%= request.getParameter("admin")%>"/>
                            <input type="hidden" name="cid" value="<%= request.getAttribute("cid")%>"/>
                            <input type="hidden" name="pid" value="<%=pro.getPid()%>"/>
                            <input type="hidden" name="pname" value="<%=pro.getPname()%>"/>
                            <input type="hidden" name="quantityAvailable" value="<%=pro.getQuantity()%>"/>
                            <input type="hidden" name="price" value="<%=pro.getPrice()%>"/>
                            <input type="hidden" name="image" value="<%=pro.getImage()%>"/>
                            <input type="hidden" name="description" value="<%=pro.getDescription()%>"/>
                            <input type="hidden" name="status" value="<%=pro.getStatus()%>"/>
                            <input type="hidden" name="cateId" value="<%=pro.getCateId()%>"/>

                            <input type="submit"  name="action" value="editPro"/>                     
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
