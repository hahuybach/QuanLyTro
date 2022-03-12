<%-- 
    Document   : login
    Created on : 27-Feb-2022, 15:38:46
    Author     : Bach
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="./css/login.css" rel="stylesheet" type="text/css"/>
        <title>Rent</title>
    </head>
    <body>
        <div class="container">
            <h1>Login</h1>
            <form action="login" method="POST">
                <div class="form-control">
                    <input type="text" id="username" placeholder="Username" name="username" />
                    <span></span>
                    <small></small>
                </div>
                <div class="form-control">
                    <input type="password" id="password" placeholder="Password" name="password" />
                    <span></span>
                    <small></small>
                </div>
                <input type="submit" value="Login" />
                <div class="signup_link">Not a member ? <a href="#">Sign Up</a></div>
            </form>
        </div>
    </body>
</html>
