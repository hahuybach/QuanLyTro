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
        <link href="${pageContext.request.contextPath}/css/invoice/searchInvoice.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/tenant/searchTenant.css" rel="stylesheet" type="text/css"/>
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
            <div class="title"><h1 style="color:#a18cd1; ">Detail Invoice</h1></div>
            <div class="">
                <span>Room: ${requestScope.invoice.roomID}</span><br/>
                <span>Month: ${requestScope.invoice.month}</span><br/>
                <span>Year: ${requestScope.invoice.year}</span><br/>
            </div>
            <table class="content-table">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Old Meter</th>
                        <th>New Meter</th>
                        <th>Total Used</th>
                        <th>Unit Price</th>
                        <th>Price</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Room Price</td>
                        <td></td>
                        <td></td>
                        <td style="text-align:center;">1</td>
                        <td>${requestScope.invoice.roomPrice}</td>
                        <td>${requestScope.invoice.roomPrice}</td>
                    </tr>
                    <tr>
                        <td>Electric</td>
                        <td style="text-align:center;">${requestScope.invoice.old_electric_num}</td>
                        <td style="text-align:center;">${requestScope.invoice.electric_num}</td>
                        <td style="text-align:center;">${requestScope.invoice.electric_num-requestScope.invoice.old_electric_num}</td>
                        <td style="text-align:right;">${requestScope.invoice.electric_price}</td>
                        <td style="text-align:right;">${(requestScope.invoice.electric_num-requestScope.invoice.old_electric_num)*requestScope.invoice.electric_price}</td>
                    </tr>
                    <tr>
                        <td>Water</td>
                        <td style="text-align:center;">${requestScope.invoice.old_water_num}</td>
                        <td style="text-align:center;">${requestScope.invoice.water_num}</td>
                        <td style="text-align:center;">${requestScope.invoice.water_num-requestScope.invoice.old_water_num}</td>
                        <td style="text-align:right;">${requestScope.invoice.water_price}</td>
                        <td style="text-align:right;">${(requestScope.invoice.water_num-requestScope.invoice.old_water_num)*requestScope.invoice.water_price}</td>
                    </tr>
                    <tr>
                        <td>Internet</td>
                        <td></td>
                        <td></td>
                        <td style="text-align:center;">1</td>
                        <td style="text-align:right;">${requestScope.invoice.internet_price}</td>
                        <td style="text-align:right;">${requestScope.invoice.internet_price}</td>
                    </tr>
                    <tr>
                        <td>Cable TV</td>
                        <td></td>
                        <td></td>
                        <td style="text-align:center;">${requestScope.invoice.tv_num}</td>
                        <td style="text-align:right;">${requestScope.invoice.cable_tv_price}</td>
                        <td style="text-align:right;">${requestScope.invoice.tv_num*requestScope.invoice.cable_tv_price}</td>
                    </tr>
                    <tr>
                        <td>Cleaning</td>
                        <td></td>
                        <td></td>
                        <td style="text-align:center;">1</td>
                        <td style="text-align:right;">${requestScope.invoice.cleaning_price}</td>
                        <td style="text-align:right;">${requestScope.invoice.cleaning_price}</td>
                    </tr>
                    <tr>
                        <td>Bike</td>
                        <td></td>
                        <td></td>
                        <td style="text-align:center;">${requestScope.invoice.bike_num}</td>
                        <td style="text-align:right;">${requestScope.invoice.bike_price}</td>
                        <td style="text-align:right;">${requestScope.invoice.bike_num*requestScope.invoice.bike_price}</td>
                    </tr>
                    <tr>
                        <td>Service</td>
                        <td></td>
                        <td></td>
                        <td style="text-align:center;">1</td>
                        <td style="text-align:right;">${requestScope.invoice.service_price}</td>
                        <td style="text-align:right;">${requestScope.invoice.service_price}</td>
                    </tr>
                    <tr>
                        <td style="font-weight: 800;">Total</td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td style="text-align:right; font-weight: 800;">${requestScope.invoice.total}</td>
                    </tr>
                    <tr>
                        <td style="font-weight: 800;">Paid</td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td style="text-align:right; font-weight: 800;">${requestScope.invoice.paid}</td>
                    </tr>
                </tbody>
            </table>
                    <div>
                        <form action="charge" method="POST">
                            <input type="hidden" name="roomID" value="${requestScope.invoice.roomID}">
                            <input type="hidden" name="month" value="${requestScope.invoice.month}">
                            <input type="hidden" name="year"  value="${requestScope.invoice.year}">
                            Input charge number: <input type="text" name="paid">
                            <button type="submit" name="charge">Charge</button>
                        </form>
                    </div>
                    
                
        </div>
    </body>
</html>
