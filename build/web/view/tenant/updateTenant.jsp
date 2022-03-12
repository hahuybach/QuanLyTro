<%-- 
    Document   : updateTenant
    Created on : 05-Mar-2022, 16:10:01
    Author     : Bach
--%>

<%@page import="model.Tenant"%>
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
        <%
            Tenant t = (Tenant) request.getAttribute("tenant");
        %>
    </head>
    <body>
        <section class="login_signup">
            <a href="">Log Out</a>
        </section>
        <!-- navbar  -->

        <header>
            <nav class="navbar">
                <a href="#house" class="navbar_item">House</a>
                <a href="#tenants" id="tenant"class="navbar_item">Tenant</a>
                <a href="#invoices" class="navbar_item">Invoice</a>
                <a href="#payments" class="navbar_item">Payment</a>
                <a href="#balances" class="navbar_item">Balance</a>
            </nav>
        </header>

        <div class="container">
            <div class="title">Update Tenant</div>
            <div class="content">
                <form action="update" method="POST">
                    <label for="text">Tenant ID: ${requestScope.tenant.id}
                    <input type="hidden" name="id" value="${requestScope.tenant.id}"/>
                    <div class="user-details">
                        <div class="input-box">
                            <span class="details">Full Name</span>
                            <input name="name" type="text" placeholder="Enter tenant name" value="${requestScope.tenant.fullName}" required>
                        </div>
                        <div class="input-box">
                            <span class="details">Identification Number</span>
                            <input name="identificationNumber" type="text" placeholder="Enter tenant identification number" value="${requestScope.tenant.identificationNumber}" required>
                        </div>
                        <div class="input-box">
                            <span class="details">Phone Number</span>
                            <input name="phoneNumber" type="text" placeholder="Enter tenant phone number" value="${requestScope.tenant.phoneNumber}" required>
                        </div>
                        <div class="input-box">
                            <span class="details">Place Of Birth</span>
                            <input name="PoB" type="text" placeholder="Enter tenant place of birth" value="${requestScope.tenant.poB}" required>
                        </div>
                        <div class="input-box">
                            <span class="details">Permanent Residence</span>
                            <input name="permanentResidence" type="text" placeholder="Enter tenant permanent resisdent" value="${requestScope.tenant.permanentResidence}" required>
                        </div>
                        <div class="input-box">
                            <span class="details">Date Of Birth</span>
                            <input name="DoB" type="date" placeholder="Enter tenant date of birth" value="${requestScope.tenant.doB}" required>
                        </div>
                        <div class="input-box">
                            <span class="details">Start Date</span>
                            <input name="startDate" type="date" placeholder="Enter tenant start date" value="${requestScope.rent.startDate}" required>
                        </div>
                        <div class="input-box">
                            <span class="details">End Date</span>
                            <input name="endDate" type="date" placeholder="Enter tenant end date" value="${requestScope.rent.endDate}" required>
                        </div>
                        <div class="input-box">
                            <span class="details">Room</span>
                            <select name="room" onchange="submitForm();">
                                <option value="-1">-----SELECT ROOM-----</option>
                                <c:forEach items="${requestScope.rooms}" var="r">
                                    <option
                                        ${(r.roomID == requestScope.rent.roomId)?"selected=\"selected\"":""} 
                                        value="${r.roomID}">Room ${r.roomID}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="gender-details">
                        <input type="radio" 
                               ${(t.gender)?"checked=\"checked\"":""}
                               name="gender" id="dot-1">
                        <input type="radio"
                               ${not (t.gender)?"checked=\"checked\"":""}
                               name="gender" id="dot-2">
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
                        <input type="submit" value="Update">
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
