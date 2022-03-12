<%-- 
    Document   : home
    Created on : 27-Feb-2022, 21:16:47
    Author     : Bach
--%>

<%@page import="model.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="./css/style.css" rel="stylesheet" type="text/css"/>
        <link href="./css/home.css" rel="stylesheet" type="text/css"/>
        <title>Rent</title>
        <%
        Account account = (Account) request.getSession().getAttribute("account");
        %>
    </head>
    <body>
        <section class="login_signup">
            <a href="logout">Log Out</a>
        </section>
        <!-- navbar  -->

        <header>
            <nav class="navbar">
                <a href="room/search" class="navbar_item">Room</a>
                <a href="tenant/search" class="navbar_item">Tenant</a>
                <a href="invoice" class="navbar_item">Invoice</a>
                <a href="balance" class="navbar_item">Balance</a>
            </nav>
        </header>
        <div class="container">
            <h1>Welcome <%=account.getDisplayName()%> !</h1>
            <img src="./img/undraw_select_house_qbag.svg">
        </div>
    </body>
</html>
