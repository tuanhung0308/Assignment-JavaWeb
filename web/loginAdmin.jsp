

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">

    </head>
    <body>

        <div class="container" style="width: 50%;">
            <p>        <%
    String error = (String) request.getAttribute("ERROR");
    if (error == null) {
        error = "";
    }
                %>
                <%= error%></p>
            <div class="card">
                <article class="card-body">
                    <a href="register.jsp" class="float-right btn btn-outline-primary">Register</a>
                    <a href="login.jsp" class="float-right btn btn-outline-primary">Login</a>
                    <h4 class="card-title mb-4 mt-1">Sign in with role Admin</h4>
                    <form action="MainController">
                        <div class="form-group">
                            <label>username</label>
                            <input class="form-control" placeholder="username" type="text" name="admin">
                        </div> 
                        <div class="form-group">
                            <a class="float-right" href="#">Forgot?</a>
                            <label>Your password</label>
                            <input class="form-control" placeholder="******" type="password" name="password">
                        </div> 
                        <div class="form-group"> 
                            <div class="checkbox">
                                <label> <input type="checkbox"> Save password </label>
                            </div>
                        </div> 
                        <div class="form-group">
                            <input type="submit" name="action" value="Login Admin" class="btn btn-primary btn-block"/>
                            <!--<input type="reset" value="Reset"/>-->
                        </div>                                                         
                    </form>
                </article>
            </div>
        </div>
        <!--        
                <form action="MainController">
                    User Name<input type="text" name="admin"/><br/>
                    Password<input type="password" name="password"/><br/>
                    <input type="submit" name="action" value="Login Admin"/>
                    <input type="reset" value="Reset"/>
                </form>
        
                <a href="login.jsp">Back</a><br/>-->

    </body>
</html>
