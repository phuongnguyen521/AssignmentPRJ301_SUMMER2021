<%-- 
    Document   : header
    Created on : Jul 10, 2021, 4:11:23 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="css/headerCss.css" type="text/css"/>
<header>
    <nav>
        <p>Welcome ${sessionScope.USERNAME}</p>
        <ul class="nav_link">
            <c:if test="${empty sessionScope.USERNAME}">
                <li>
                    <a class="feature_link" href="login">Login</a>
                </li>  
            </c:if>
            <c:if test="${not empty sessionScope.USERNAME}">
                <li>
                    <a class="feature_link" href="search">Search</a>
                </li>
            </c:if>
            <li>
                <a class="feature_link" href="shoppingServlet">Shopping Online</a>
            </li>
            <li>
                <a class="feature_link" href="createNewAccount">Create new Account</a>
            </li>
        </ul>
        <c:if test="${not empty sessionScope.USERNAME}">
            <form action="logOutAccount">
                <input type="submit" value="Log Out" name="btAction" 
                       class="button_LogOut"/>
            </form>
        </c:if>
    </nav>
</header>