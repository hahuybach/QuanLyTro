<%-- 
   Document   : searchTenant
   Created on : 01-Mar-2022, 16:03:35
   Author     : Bach
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/style.css" rel="stylesheet" type="text/css"/>
        <link href="../css/tenant/searchTenant.css" rel="stylesheet" type="text/css"/>
        <title>Rent</title>
        <script src="https://kit.fontawesome.com/04a2d528b9.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta2/css/all.min.css" integrity="sha512-YWzhKL2whUzgiheMoBFwW8CKV4qpHQAEuvilg9FAn5VJUDwKZZxkJNuGM4XkWuk94WCrrwslk8yWNGmY1EduTA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <script>
            function submitForm()
            {
                document.getElementById("frmSearch").submit();
            }
            function deleteTenant(id)
            {
                var result = confirm("are you sure?");
                if (result)
                {
                    window.location.href = 'delete?id=' + id;
                }
            }
        </script>    
        <script src="../js/pagger.js" type="text/javascript"></script>
        <link href="../css/room/pagger.css" rel="stylesheet" type="text/css"/>
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
            <form class="searchbox" action="searchByAjax" method="GET">
                <input type="text" placeholder="Search tenant by name" name="search">
                <button type="submit"><i class="fa fa-search"></i></button>
            </form>
            <br/>
            <div class="insert_tenant">
                <a href="../tenant/insert"><i class="fa-solid fa-plus"></i><span> </span>Insert new tenant</a>
            </div>

            <table class="content-table" >
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Phone</th>
                        <th>Identification Number</th>
                        <th>Place Of Birth</th>
                        <th></th>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach items="${requestScope.tenants}" var="t">
                        <tr>
                            <td>${t.id}</td>
                            <td>${t.fullName}</td>
                            <td>${t.phoneNumber}</td>
                            <td>${t.identificationNumber}</td>
                            <td>${t.poB}</td>
                            <td>
                                <a href="../tenant/update?id=${t.id}">Update</a>
                                &ensp;
                                <a href="#" onclick="deleteTenant(${t.id})">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <div id="containerbot" class="pagger">  </div>
            <script>
            pagger("containerbot",${requestScope.pageindex},${requestScope.totalpage}, 3);
            </script>
        </div>

    </body>
</html>
