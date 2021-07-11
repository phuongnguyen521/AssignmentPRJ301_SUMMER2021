<%-- 
    Document   : confirmLogOut
    Created on : Jul 12, 2021, 6:48:43 AM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirm Log Out</title>
        <link href="css/confirmCss.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="account_box">
            <form action="ConfirmLogOut">
                <h3>You already login with account ${sessionScope.USERNAME}
                    Click "Log Out" if you want to login with your account</h3>
                <input type="submit" value="Log Out" name="btAction" />
                <input type="submit" value="Search" name="btAction" />
            </form>
        </div>
    </body>
</html>
