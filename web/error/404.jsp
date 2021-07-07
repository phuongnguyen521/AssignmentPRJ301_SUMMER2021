<%-- 
    Document   : 404
    Created on : Jul 7, 2021, 9:24:22 AM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Page Not Found</title>
    </head>
    <body>
        <c:if test="${not empty sessionScope.USERNAME}">
            <font color="red">
            Welcome ${sessionScope.USERNAME}
            </font>
            <form action="logOut_Account">
                <input type="submit" value="Sign Out" name="btAction" />
            </form>
        </c:if>
        <div align="center">
            <h2>Sorry, Page could not be found</h2>
            <br/>
            <br/>
            <a href="shopping_servlet">Click here to shopping online</a><br/>
            <a href="search_jsp">Click here to Search page</a>
        </div>
    </body>
</html>
