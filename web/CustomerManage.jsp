

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
        <style>
            .Panner{
                display: flex;
                justify-content: space-between;
                height: 100px;
                align-items: center;
                padding: 0 20px;
            }
            .Panner1{
                display: flex;
            }
            .Panner2{
                display: flex;
                flex-direction: column;
                /*margin-top: 20px*/
            }

        </style>

        <div class="Panner" >

            <div class="Panner">
                <a href="MainController?action=viewCart&cid=<%= request.getAttribute("cid")%>&cid=<%= request.getParameter("cid")%>&cid=<%= request.getAttribute("customerId")%>&username=<%= request.getParameter("username")%> "><button type="button" class="btn btn-success">View Cart</button></a>
                <%
                    String search = request.getParameter("search");
                    if (search == null) {
                        search = "";
                    }

                %>



                <form action="MainController" class="pl-3">
                    Search<input type="text" name="search"  value="<%= search%>" placeholder="input your text"/>
                    <input type="submit" name="action"  value="Search"/>
                    <input type="hidden" name="username" value="<%=request.getParameter("username")%>"/>
                    <input type="hidden" name="cid" required="" value="<%= request.getAttribute("cid")%>">
                    <input type="hidden" name="cid" required="" value="<%= request.getParameter("cid")%>">
                </form>   

            </div>
            <div class="Panner">
                <%if (request.getParameter("username") == null) {%>
                <div class="Panner">           
                    <a href="login.jsp" ><button type="button" class="btn btn-danger">Log in</button></a>
                    <a href="register.jsp" class="pl-3"><button type="button" class="btn btn-primary">Register</button></a>
                </div>

                <%} else if (request.getParameter("username").equals("null")) {%>
                <div>           
                    <a href="login.jsp" ><button type="button" class="btn btn-danger">Log in</button></a>
                    <a href="register.jsp"><button type="button" class="btn btn-primary">Register</button></a>
                </div>
                <%} else {%>
                <h3><%=request.getParameter("username")%></h3>
                <a href="MainController?action=Logout"  class="pl-3"><button type="button" class="btn btn-danger">Log out</button></a>
                <%}%>
            </div>

        </div>
        <div>
            <%
            List<Category> listCategory = (List<Category>) request.getAttribute("LIST_CATEGORY");
            String message = (String) request.getAttribute("SHOPPING_MESSAGE");
            if (message
            == null) {
            message = "";
            }
            String messageSucess = (String) request.getAttribute("MESSAGE_SUCCESS");
            if (messageSucess
            == null) {
            messageSucess = "";
            }
            %>
            <h2 class="message"><%=message%></h1></br>
                <h2 class="message"><%=messageSucess%></h1></br>
                    </div>
                    <div class="Panner1">
                        <div class="d-flex mt-3" style="width: 100%;">
                            <div class="Panner2" style="width: 20%;background: #ddd;padding: 60px;">
                                <%
                                    for (Category ca : listCategory) {
                                %>
                                <a href="MainController?action=listByCa&cateId=<%= ca.getCateId()%>&cid=<%= request.getAttribute("cid")%>&cid=<%= request.getParameter("cid")%>&&username=<%= request.getParameter("username")%>"><%= ca.getCateName()%></a>
                                <%}%>


                                <%

                                    List<Product> listCProduct = (List<Product>) session.getAttribute("LIST_PRODUCT");
                                    if (listCProduct != null) {
                                        if (!listCProduct.isEmpty()) {

                                %>
                            </div>
                            <table border="1"  class="table" style="width: 80%;">
                                <thead class="thead-dark">
                                    <tr>
                                        <th>ID</th>
                                        <th>Product Name</th>
                                        <th>Quantity</th>
                                        <th>Price</th>  
                                        <th>Image</th>
                                        <th>Description</th>
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
                                        <td>
                                            <input type="hidden" name="username" value="<%= request.getParameter("username")%>"/>
                                            <input type="hidden" name="cid" value="<%= request.getAttribute("cid")%>"/>
                                            <input type="hidden" name="cid" value="<%= request.getParameter("cid")%>"/>
                                            <input type="hidden" name="pid" value="<%=pro.getPid()%>"/>
                                            <input type="hidden" name="pname" value="<%=pro.getPname()%>"/>
                                            <input type="hidden" name="quantityAvailable" value="<%=pro.getQuantity()%>"/>
                                            <input type="hidden" name="price" value="<%=pro.getPrice()%>"/>
                                            <input type="hidden" name="image" value="<%=pro.getImage()%>"/>
                                            <input type="hidden" name="description" value="<%=pro.getDescription()%>"/>
                                            <input type="hidden" name="status" value="<%=pro.getStatus()%>"/>
                                            <input type="hidden" name="cateId" value="<%=pro.getCateId()%>"/>
                                            <%if (request.getParameter("username") != null) {%>
                                            <input type="submit"  name="action" value="Add to cart" class="btn btn-info"/>
                                            <%} else {%>
                                            <a href="login.jsp"><button type="button" class="btn btn-info">Add to cart</button></a>
                                            <%}%>

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
                        </div></div>
                    </body>
                    </html>
