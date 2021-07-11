<%-- 
    Document   : 500
    Created on : Jul 7, 2021, 9:29:15 AM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Internal Server Error</title>
    </head>
    <body>
        <c:if test="${not empty sessionScope.USERNAME}">
            <font color="red">
            Welcome ${sessionScope.USERNAME}
            </font>
            <form action="logOutAccount">
                <input type="submit" value="Sign Out" name="btAction" />
            </form>
        </c:if>
        <div align="center">
            <h2>Sorry, the server has encountered an error</h2>
            <br/>
            <br/>
            <a href="shoppingServlet">Click here to shopping online</a><br/>
        </div>
    </body>
</html>
