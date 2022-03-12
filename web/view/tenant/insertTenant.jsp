<%-- 
    Document   : insertTenant
    Created on : 03-Mar-2022, 10:47:50
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
                <a href="../invoice" class="navbar_item">Invoice</a>
                <a href="../balance" class="navbar_item">Balance</a>
            </nav>
        </header>

        <div class="container">
            <div class="title">Insert Tenant</div>
            <div class="content">
                <form action="insert" method="POST">
                    <div class="user-details">
                        <div class="input-box">
                            <span class="details">Full Name</span>
                            <input name="name" type="text" placeholder="Enter tenant name" required>
                        </div>
                        <div class="input-box">
                            <span class="details">Identification Number</span>
                            <input name="identificationNumber" type="text" placeholder="Enter tenant identification number" required>
                        </div>
                        <div class="input-box">
                            <span class="details">Phone Number</span>
                            <input name="phoneNumber" type="text" placeholder="Enter tenant phone number" required>
                        </div>
                        <div class="input-box">
                            <span class="details">Place Of Birth</span>
                            <input name="PoB" type="text" placeholder="Enter tenant place of birth" required>
                        </div>
                        <div class="input-box">
                            <span class="details">Permanent Residence</span>
                            <input name="permanentResidence" type="text" placeholder="Enter tenant permanent residence" required>
                        </div>
                        <div class="input-box">
                            <span class="details">Date Of Birth</span>
                            <input name="DoB" type="date" placeholder="Enter tenant date of birth" required>
                        </div>
                        <div class="input-box">
                            <span class="details">Start Date</span>
                            <input name="startDate" type="date" placeholder="Enter tenant start date" required>
                        </div>
                        <div class="input-box">
                            <span class="details">End Date</span>
                            <input name="endDate" type="date" placeholder="Enter tenant end date" required>
                        </div>
                        <div class="input-box">
                            <span class="details">Room</span>
                            <select name="room" onchange="submitForm();">
                                <option value="-1">-----SELECT ROOM-----</option>
                                <c:forEach items="${requestScope.rooms}" var="r">
                                    <option value="${r.roomID}">Room ${r.roomID}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="gender-details">
                        <input type="radio" name="gender" id="dot-1">
                        <input type="radio" name="gender" id="dot-2">
                        <span class="gender-title">Gender</span>
                        <div class="category">
                            <label for="dot-1">
                                <span class="dot one"></span>
                                <span class="gender">Male</span>
                                &nbsp;&nbsp;&nbsp;&nbsp;
                                &nbsp;&nbsp;&nbsp;&nbsp;
                            </label>
                            <label for="dot-2">
                                <span class="dot two"></span>
                                <span class="gender">Female</span>
                            </label>
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