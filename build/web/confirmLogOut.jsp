<%-- 
    Document   : confirmLogOut
    Created on : Jul 12, 2021, 6:48:43 AM
    Author     : DELL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirm Log Out</title>
        <link href="css/confirmCss.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <c:if test="${not empty sessionScope.USERNAME}">
            <div class="account_box">
                <form action="ConfirmLogOut">
                    <c:choose>
                        <c:when test="${not empty sessionScope.MSG}">
                            <p>${sessionScope.MSG}</p>
                            <input type="submit" value="Shopping" name="btAction" />
                        </c:when>
                        <c:otherwise>
                            <p>You are logged in another account</p>
                            <p>Click "Log Out" if you want to login by your account</p>
                            <input type="submit" value="Log Out" name="btAction" />
                        </c:otherwise>
                    </c:choose>
                    <input type="submit" value="Search" name="btAction" />
                </form>
            </div>
        </c:if>
        <c:if test="${empty sessionScope.USERNAME}">
                <div class="account_box">
                    <h3>You are logged out</h3>
                    <form action="login">
                        <input type="submit" value="Login" name="btAction" />
                    </form>
                    <form action="shoppingServlet">
                        <input type="submit" value="Shopping" name="btAction" />
                    </form>
                </div>
        </c:if>
    </body>
</html>
