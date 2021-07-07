<%-- 
    Document   : editAccount
    Created on : Jul 2, 2021, 4:49:36 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Account</title>
        <script src="js/editAccount.js" type="text/javascript"/>
    </head>
    <body>
        <c:set var="nameUserLogin" value="${sessionScope.USERNAME}"/>
        <c:set var="Errors" value="${requestScope.EDIT_ERROR}"/>
        <font color="red">
        Welcome ${nameUserLogin}
        </font>
        <form action="logOut_Account">
            <input type="submit" value="Sign Out" name="btAction" />
        </form>
        <h1>Edit Account</h1>
        <form action="edit_Account" method="POST">
            Username  <input type="text" name="pk" 
                             value="${INFORM.username}" disabled="disabled" /></br>
            <input type="hidden" name="txtUsernameEdit" 
                   value="${INFORM.username}"/>
            <input type="hidden" name="lastSearchValue" 
                   value="${requestScope.lastSearchValue}"/>
            Password <input type="password" name="txtPasswordEdit" 
                            value="${INFORM.password}" /></br>
            <c:if test="${not empty Errors.passswordLengthErr}">
                <font color="red">
                ${Errors.passswordLengthErr}
                </font></br>
            </c:if>
            Lastname <input type="text" name="txtLastNameEdit" 
                            value="${INFORM.lastname}" /></br>
            <c:if test="${not empty Errors.lastnameLengthErr}">
                <font color="red">
                ${Errors.lastnameLengthErr}
                </font></br>
            </c:if>
            is Admin? <input type="checkbox" name="chkAdminEdit" value="ADMIN"
                            <c:if test="${dto.role}">
                                checked="checked"
                            </c:if> /></br>
            <input type="submit" value="Update" name="btAction" onclick="confirmUpdate()"/>
            <input type="hidden" value="cancel" name="confirm" id="confirm"/>
        </form>
    </body>
</html>
