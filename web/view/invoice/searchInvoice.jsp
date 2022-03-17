<%-- 
    Document   : searchInvoice
    Created on : 13-Mar-2022, 00:04:22
    Author     : Bach
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/style.css" rel="stylesheet" type="text/css"/>
        <link href="../css/room/searchRoom.css" rel="stylesheet" type="text/css"/>
        <link href="../css/invoice/searchInvoice.css" rel="stylesheet" type="text/css"/>
        <title>Rent</title>
        <script src="https://kit.fontawesome.com/04a2d528b9.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta2/css/all.min.css" integrity="sha512-YWzhKL2whUzgiheMoBFwW8CKV4qpHQAEuvilg9FAn5VJUDwKZZxkJNuGM4XkWuk94WCrrwslk8yWNGmY1EduTA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <script>
            function deleteInvoice(roomID, month, year)
            {
                var result = confirm("are you sure?");
                if (result)
                {
                    window.location.href = 'delete?roomID=' + roomID + '&month=' + month + '&year=' + year;
                }
            }
        </script>    
    </head>
    <body>
        <section class="login_signup">
            <a href="../logout">Log Out</a>
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
            <div class="insert_room">
                <a href="../invoice/insert"><i class="fa-solid fa-plus"></i><span> </span>Insert invoice</a>
            </div>
            <br/>
            <form action="search" method="POST">
                <div class="search_invoice">
                    <select id="month" name="month" >
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
                    <select name="roomID">
                        <option value="-1">RoomID</option>
                        <c:forEach items="${requestScope.rooms}" var="r">
                            <option value="${r.roomID}">Room ${r.roomID}</option>
                        </c:forEach>
                    </select>
                    <button class="button" type="submit" name="search">Search</button>
                </div>
            </form>

            <table class="content-table" >
                <thead>
                    <tr>
                        <th>Room</th>
                        <th>Room Price</th>
                        <th>Electric Price</th>
                        <th>Water Price</th>
                        <th>Bike Price</th>
                        <th>Internet Price</th>
                        <th>Service Price</th>
                        <th>Cable TV Price</th>
                        <th>Clean Price</th>
                        <th>Total</th>
                        <th>Paid</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>${requestScope.invoice.roomID}</td>
                        <td>${requestScope.invoice.roomPrice*1}</td>
                        <td>${(requestScope.invoice.electric_num-requestScope.invoice.old_electric_num)*requestScope.invoice.electric_price}</td>
                        <td>${(requestScope.invoice.water_num-requestScope.invoice.old_water_num)*requestScope.invoice.water_price}</td>
                        <td>${requestScope.invoice.bike_num*requestScope.invoice.bike_price}</td>
                        <td>${requestScope.invoice.internet_price*1}</td>
                        <td>${requestScope.invoice.service_price*1}</td>
                        <td>${requestScope.invoice.cable_tv_price*1}</td>
                        <td>${requestScope.invoice.cleaning_price*1}</td>
                        <td>${requestScope.invoice.total*1}</td>

                        <c:choose>
                            <c:when test="${(requestScope.invoice.total - requestScope.invoice.paid)>0}">
                                <td style="text-align:center; color:red;"><i class="fa-solid fa-xmark"></i></td>
                            </c:when>
                            <c:otherwise>
                                <td style="text-align:center; color:green;"><i class="fa-solid fa-check"></i></td>
                            </c:otherwise>    
                            </c:choose>


                        <td>
                            <a href="../invoice/detail?roomID=${requestScope.invoice.roomID}&month=${requestScope.invoice.month}&year=${requestScope.invoice.year}">Detail</a><br/>
                            <a href="../invoice/update?roomID=${requestScope.invoice.roomID}&month=${requestScope.invoice.month}&year=${requestScope.invoice.year}">Update</a><br/>
                            <a href="" onclick="deleteInvoice(${requestScope.invoice.roomID},${requestScope.invoice.month},${requestScope.invoice.year})">Delete</a><br/>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>

    </body>
</html>
