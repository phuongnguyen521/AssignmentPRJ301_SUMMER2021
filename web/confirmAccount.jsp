<%-- 
    Document   : confirmAccount
    Created on : Jul 9, 2021, 11:34:33 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirm Page</title>
        <link href="css/confirmCss.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div class="account_box">
            <form action="confirmAccount">
                <h3>Do you want to update this account</h3>
                <input type="submit" value="Confirm" name="btAction" />
                <input type="submit" value="Cancel" name="btAction" />
            </form>
        </div>
    </body>
</html>
