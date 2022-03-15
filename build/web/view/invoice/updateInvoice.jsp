<%-- 
    Document   : insertInvoice
    Created on : 13-Mar-2022, 11:00:29
    Author     : Bach
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/style.css" rel="stylesheet" type="text/css"/>
        <link href="../css/tenant/insertTenant.css" rel="stylesheet" type="text/css"/>
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
                <a href="../room/search" class="navbar_item">Room</a>
                <a href="../tenant/search" class="navbar_item">Tenant</a>
                <a href="../invoice/search" id="invoice" class="navbar_item">Invoice</a>
                <a href="../balance" class="navbar_item">Balance</a>
            </nav>
        </header>

        <div class="container">
            <div class="title">Update Invoice</div>
            <div class="content">
                <form action="update" method="POST">
                    <div class="user-details">
                        <div class="input-box">
                            <span class="details">Month</span>
                            <select id="month" name="month">
                                <option value="${requestScope.invoice.month}">${requestScope.invoice.month}</option>
                            </select>
                        </div>
                        <div class="input-box">
                            <span class="details">Year</span>
                            <select id="year" name="year">
                                <option value="${requestScope.invoice.year}">${requestScope.invoice.year}</option>
                            </select>
                        </div>
                        <div class="input-box">
                            <span class="details">RoomID</span>
                            <select name="room">
                                <option value="${requestScope.invoice.roomID}"/>Room ${requestScope.invoice.roomID}</option>
                            </select>
                        </div>
                        <div class="input-box">
                            <span class="details">Electricity</span>
                            <input name="electricity" type="text" placeholder="Enter electricity number" value="${requestScope.invoice.electric_num}" required>
                        </div>
                        <div class="input-box">
                            <span class="details">Water</span>
                            <input name="water" type="text" placeholder="Enter water number" value="${requestScope.invoice.water_num}" required>
                        </div>
                        <div class="input-box">
                            <span class="details">Number Of Bikes </span>
                            <input name="bike" type="text" placeholder="Enter number of bikes" value="${requestScope.invoice.bike_num}" required>
                        </div>
                        <div class="input-box">
                            <span class="details">Number Of TVs</span>
                            <input name="tv" type="text" placeholder="Enter number of TVs" value="${requestScope.invoice.tv_num}" required>
                        </div>
                    </div>
                    <div class="button">
                        <input type="submit" value="Update">
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
