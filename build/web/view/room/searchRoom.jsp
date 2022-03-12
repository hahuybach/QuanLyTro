<%-- 
    Document   : searchRoom
    Created on : 06-Mar-2022, 23:03:58
    Author     : Bach
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:useBean id="a" class="dal.RoomDBContext" scope="request"></jsp:useBean>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <link href="../css/style.css" rel="stylesheet" type="text/css"/>
            <link href="../css/room/searchRoom.css" rel="stylesheet" type="text/css"/>
            <title>Rent</title>
            <script src="https://kit.fontawesome.com/04a2d528b9.js" crossorigin="anonymous"></script>
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta2/css/all.min.css" integrity="sha512-YWzhKL2whUzgiheMoBFwW8CKV4qpHQAEuvilg9FAn5VJUDwKZZxkJNuGM4XkWuk94WCrrwslk8yWNGmY1EduTA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
            <script>
                function submitForm()
                {
                    document.getElementById("frmSearch").submit();
                }
                function deleteRoom(id)
                {
                    var result = confirm("are you sure?");
                    if (result)
                    {
                        window.location.href = 'delete?id=' + id;
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
                    <a href="../room/search" id="room" class="navbar_item">Room</a>
                    <a href="../tenant/search" class="navbar_item">Tenant</a>
                    <a href="../invoice" class="navbar_item">Invoice</a>
                    <a href="../balance" class="navbar_item">Balance</a>
                </nav>
            </header>
            <div class="container">
                <div class="insert_room">
                    <a href="../room/insert"><i class="fa-solid fa-plus"></i><span> </span>Insert new room</a>
                </div>

                <table class="content-table" >
                    <thead>
                        <tr>
                            <th>Room</th>
                            <th></th>
                            <th>Price</th>
                            <th></th>
                            <th>Status</th>
                            <th></th>
                        </tr>
                    </thead>
                    <c:forEach items="${requestScope.rooms}" var="r">
                        <tr>
                            <td>${r.roomID}</td>
                            <td></td>
                            <td>${r.price}</td>
                            <td></td>
                            <c:choose>
                                <c:when test="${!r.status}">
                                    <td style="text-align:center; color:red;"><i class="fa-solid fa-xmark"></i></td>
                                </c:when>
                                <c:otherwise>
                                    <td style="text-align:center; color:green;"><i class="fa-solid fa-check"></i></td>
                                </c:otherwise>
                            </c:choose>
                            <td>
                                <a href="../room/detail?id=${r.roomID}">Detail</a>
                                &ensp;
                                <a href="../room/update?id=${r.roomID}">Update</a>
                                &ensp;
                                <a href="#" onclick="deleteRoom(${r.roomID})">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

    </body>
</html>