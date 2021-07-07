<%-- 
    Document   : editAccountError
    Created on : Jul 4, 2021, 10:36:18 AM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Account Error Page</title>
    </head>
    <body>
        <c:set var="nameUserLogin" value="${sessionScope.USERNAME}"/>
        <font color="red">
        Welcome ${nameUserLogin}
        </font>
        <form action="logOut_Account">
            <input type="submit" value="Sign Out" name="btAction" />
        </form>
        <h1>Edit Account Error Page</h1>
        <h2>
            <font color="">
                Cannot Edit Account
            </font>
        </h2>
        <a href="search_Account">Click here to return Search Page</a>
    </body>
</html>
