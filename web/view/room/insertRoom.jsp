<%-- 
    Document   : insertRoom
    Created on : 08-Mar-2022, 00:28:40
    Author     : Bach
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/style.css" rel="stylesheet" type="text/css"/>
        <link href="../css/room/insertRoom.css" rel="stylesheet" type="text/css"/>
        <title>Rent</title>
        <script src="https://kit.fontawesome.com/04a2d528b9.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta2/css/all.min.css" integrity="sha512-YWzhKL2whUzgiheMoBFwW8CKV4qpHQAEuvilg9FAn5VJUDwKZZxkJNuGM4XkWuk94WCrrwslk8yWNGmY1EduTA==" crossorigin="anonymous" referrerpolicy="no-referrer" />

    </head>
    <body>
        <section class="login_signup">
            <a href="">Log Out</a>
        </section>
        <!-- navbar  -->

        <header>
            <nav class="navbar">
                <a href="../room/search" id="room" class="navbar_item">Room</a>
                <a href="../tenant/search" class="navbar_item">Tenant</a>
                <a href="../invoice" class="navbar_item">Invoice</a>
                <a href="../balance" class="navbar_item">Balance</a>
            </nav>
        </header>

        <div class="container">
            <div class="title">Insert Room</div>
            <div class="content">
                <form action="insert" method="POST">
                    <div class="user-details">
                        <div class="input-box">
                            <span class="details">Room</span>
                            <input name="roomID" type="text" placeholder="Enter room ID" required>
                        </div>
                        <br/>
                        <div class="input-box">
                            <span class="details">Price</span>
                            <input name="price" type="text" placeholder="Enter room price" required>
                        </div>
                        <div class="button">
                            <input type="submit" value="Insert Room">
                        </div>
                </form>
            </div>
        </div>
    </body>
</html>
