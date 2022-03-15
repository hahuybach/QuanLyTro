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
                <a href="../tenant/search" id="tenant"class="navbar_item">Tenant</a>
                <a href="../invoice/search" class="navbar_item">Invoice</a>
                <a href="../balance" class="navbar_item">Balance</a>
            </nav>
        </header>

        <div class="container">
            <div class="title">Insert Invoice</div>
            <div class="content">
                <form action="insert" method="POST">
                    <div class="user-details">
                        <div class="input-box">
                            <span class="details">Month</span>
                            <select id="month" name="month">
                                <option>Month</option>
                                <option value="1">January</option>
                                <option value="2">February</option>
                                <option value="3">March</option>
                                <option value="4">April</option>
                                <option value="5">May</option>
                                <option value="6">June</option>
                                <option value="7">July</option>
                                <option value="8">August</option>
                                <option value="9">September</option>
                                <option value="10">October</option>
                                <option value="11">November</option>
                                <option value="12">December</option>
                            </select>
                        </div>
                        <div class="input-box">
                            <span class="details">Year</span>
                            <select id="year" name="year">
                                <option>Year</option>
                                <option value="2010">2010</option>
                                <option value="2011">2011</option>
                                <option value="2012">2012</option>
                                <option value="2013">2013</option>
                                <option value="2014">2014</option>
                                <option value="2015">2015</option>
                                <option value="2016">2016</option>
                                <option value="2017">2017</option>
                                <option value="2018">2018</option>
                                <option value="2019">2019</option>
                                <option value="2020">2020</option>
                                <option value="2021">2021</option>
                                <option value="2022">2022</option>
                            </select>
                        </div>
                        <div class="input-box">
                            <span class="details">RoomID</span>
                            <select name="room">
                                <option value="-1">RoomID</option>
                                <c:forEach items="${requestScope.rooms}" var="r">
                                    <option value="${r.roomID}">Room ${r.roomID}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="input-box">
                            <span class="details">Electricity</span>
                            <input name="electricity" type="text" placeholder="Enter electricity number" required>
                        </div>
                        <div class="input-box">
                            <span class="details">Water</span>
                            <input name="water" type="text" placeholder="Enter water number" required>
                        </div>
                        <div class="input-box">
                            <span class="details">Number Of Bikes </span>
                            <input name="bike" type="text" placeholder="Enter number of bikes" required>
                        </div>
                        <div class="input-box">
                            <span class="details">Number Of TVs</span>
                            <input name="tv" type="text" placeholder="Enter number of TVs" required>
                        </div>
                    </div>
                    <div class="button">
                        <input type="submit" value="Insert">
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
