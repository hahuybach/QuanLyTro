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
                <a href="#house" id="room" class="navbar_item">House</a>
                <a href="#tenants" class="navbar_item">Tenant</a>
                <a href="#invoices" class="navbar_item">Invoice</a>
                <a href="#payments" class="navbar_item">Payment</a>
                <a href="#balances" class="navbar_item">Balance</a>
            </nav>
        </header>

        <div class="container">
            <div class="title">Update Room</div>
            <div class="content">
                <form action="update" method="POST">
                    <div class="user-details">
                        <div class="input-box">
                            <span class="details">Room</span>
                            <input name="roomID" type="text" placeholder="Enter room ID" value="${requestScope.room.roomID}" required>
                        </div>
                        <br/>
                        <div class="input-box">
                            <span class="details">Price</span>
                            <input name="price" type="text" placeholder="Enter room price" value="${requestScope.room.price}" required>
                        </div>
                        <div class="input-box">
                            <span class="details">Status</span>
                            <select name="status" >
                                <option ${not (requestScope.room.status)?"selected=\"selected\"":""} value="full">Full</option>
                                <option ${(requestScope.room.status)?"selected=\"selected\"":""}value="empty">Empty</option>
                            </select>
                        </div>
                    <div class="button">
                        <input type="submit" value="Update Room">
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
