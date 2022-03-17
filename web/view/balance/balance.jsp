<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/room/searchRoom.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/invoice/searchInvoice.css" rel="stylesheet" type="text/css"/>
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
                <a href="./room/search" class="navbar_item">Room</a>
                <a href="./tenant/search" class="navbar_item">Tenant</a>
                <a href="./invoice/search"  class="navbar_item">Invoice</a>
                <a href="./balance" id="balance" class="navbar_item">Balance</a>
            </nav>
        </header>
        <div class="container">
            <br/>
            <form action="balance" method="POST">
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
                    <button class="button" type="submit" name="search">Search</button>
                </div>
            </form>
            <div class="revenue">Revenue
                <table class="content-table" >
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>VND</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>Total</td>
                            <td>${requestScope.revenue}</td>
                        </tr>

                    </tbody>
                </table>
            </div>

            <div class="expense" >Expense
                <table class="content-table" >
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>VND</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.expenses}" var="e">
                            <tr>
                                <td>${e.name}</td>
                                <td>${e.price}</td>
                            </tr>
                        </c:forEach>

                        <tr>
                            <td>Total</td>
                            <td>
                                <c:set var="total" value="${0}"/>
                                <c:forEach items="${requestScope.expenses}" var="e">
                                    <c:set var="total" value="${total + e.price}"/>
                                </c:forEach>
                                <c:out value="${total}" />
                            </td>
                        </tr>
                    </tbody>
                </table>
                <div class="insert_room">
                    <a href="./balance/insert"><i class="fa-solid fa-plus"></i><span> </span>Insert expense</a>
                </div>            
            </div>

        </div>

    </body>
</html>
