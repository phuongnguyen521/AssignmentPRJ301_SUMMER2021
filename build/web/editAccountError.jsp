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
        <font color="red">
        Welcome ${sessionScope.USERNAME}
        </font>
        <form action="logOutAccount">
            <input type="submit" value="Sign Out" name="btAction" />
        </form>
        <h1>Edit Account Error Page</h1>
        <h2>
            <font color="">
                Cannot Edit Account
            </font>
        </h2>
        <a href="searchAccount">Click here to return Search Page</a>
    </body>
</html>
