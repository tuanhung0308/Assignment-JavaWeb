
<%@page import="entity.Product"%>
<%@page import="cart.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">

    </head>
    <body>
        <h2>Giỏ hàng</h2>
        <%
            Cart cart = (Cart) session.getAttribute("CART");
            if (cart == null) {
        %>
        <h1 class="message">Not have any products!!!</h1>
        <%
        } else {
        %>
        <%
            String message = (String) request.getAttribute("SHOPPING_MESSAGE");
            if (message == null) {
                message = "";
            }
        %>
        <h1 class="message"><%=message%></h1></br>
        <div class="content">               
            <table border="1"  class="table">
                <thead class="thead-dark">
                    <tr>
                        <th>No</th>
                        <th>ID</th>
                        <th>Product Name</th>                      
                        <th>Quantity</th>
                        <th>Price</th>
                        <th>Image</th>                       
                        <th>Description</th>   
                        <th></th>        
                        <th></th>          
                        <th></th>                        
                    </tr>
                </thead>
                <tbody>
                    <%  int count = 1;
                        float total = 0;
                        for (Product product : cart.getCart().values()) {
                            total += product.getPrice() * product.getQuantity();
                    %>                             
                <form action="MainController">
                    <tr>
                        <td><%=count++%></td>
                        <td><%=product.getPid()%></td>
                        <td><%=product.getPname()%></td>
                        <td>                               
                            <input type="number" name="quantity" value="<%=product.getQuantity()%>" required="" min="1"/>
                        </td>
                        <td><%=product.getPrice()%></td>
                        <td><img src="<%=product.getImage()%>" alt="Image" width=250 height=250></td>
                        <td><%=product.getDescription()%></td>


                        <td>
                            <input type="hidden" name="cid" required="" value="<%= request.getParameter("cid")%>">
                            <input type="hidden" name="pid" value="<%=product.getPid()%>"/>
                            <input type="hidden" name="username" required="" value="<%= request.getParameter("username")%>">
                            <input type="hidden" name="productQuantityAvailable" value="<%=product.getQuantityAvaiable()%>"/>
                            <input type="submit" name="action" value="UpdateCart" class="btn btn-warning"/>
                        </td>

                    </tr>
                </form>                               
                <%
                    }
                %>
                </tbody>
            </table>
        </div>
        <h4>Total: <%=total%></h4>


        <form action="MainController">
            <b>Information</b></br>

            <input type="hidden" name="cid" required="" value="<%= request.getParameter("cid")%>">
            <input type="hidden" name="username" required="" value="<%= request.getParameter("username")%>">
            <input type="hidden" name="total" required="" value="<%= total%>"></br>
            Address: <input type="text" name="recAddress" required="" value=""></br>
            Phone: <input type="text" name="recPhone" required="" value=""></br>
            Note: <input type="text" name="note" required="" value=""></br>

            <input type="hidden" name="status" readonly="" value="0">

            <input type="submit" name="action" value="Check out"/>
        </form>

        <%
            }
        %>
        
    </body>
</html>
