<%-- 
    Document   : confirmDelete
    Created on : Jul 11, 2021, 7:40:31 AM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirm Delete</title>
        <link href="css/confirmCss.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <jsp:include page="header.jsp" flush="true"/>
        <div class="account_box">
            <form action="deleteProcess">
                <input type="hidden" name="pk" value="${sessionScope.USERNAMEDELETE}" />
                <input type="hidden" name="lastSearchValue" value="${sessionScope.SEARCHVALUE}" />
                <h3>Do you want to delete your account?</br>
                When it is done, you will be in login page and cannot access this account anymore
                </h3>
                <input type="submit" value="Confirm" name="btAction" />
                <input type="submit" value="Cancel" name="btAction" />
            </form>
        </div>
    </body>
</html>
