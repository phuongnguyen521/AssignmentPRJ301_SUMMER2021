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
        <link href="css/editCss.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <c:set var="Errors" value="${requestScope.EDIT_ERROR}"/>
        <c:if test="${empty sessionScope.USERNAME}">
            <c:set var="INFORM" value="" />
        </c:if>
        <jsp:include page="header.jsp" flush="true"/>
        <div class="account_box">
            <h1>Edit Account</h1>
            <form action="editAccount" method="POST">
                <p>Username</p>  
                <input type="text" name="pk" 
                       value="${INFORM.username}" disabled="disabled" /></br>
                <input type="hidden" name="txtUsernameEdit" 
                       value="${INFORM.username}"/>
                <input type="hidden" name="lastSearchValue" 
                       value="${requestScope.lastSearchValue}"/>
                <p>Password</p> 
                <input type="password" name="txtPasswordEdit" 
                       value="${INFORM.password}" /></br>
                <c:if test="${not empty Errors.passswordLengthErr}">
                    <font color="red">
                    ${Errors.passswordLengthErr}
                    </font></br>
                </c:if>
                <p>Lastname</p>
                <input type="text" name="txtLastNameEdit" 
                                       value="${INFORM.lastname}" /></br>
                <c:if test="${not empty Errors.lastnameLengthErr}">
                    <font color="red">
                    ${Errors.lastnameLengthErr}
                    </font></br>
                </c:if>
                <p>is Admin? <input type="checkbox" name="chkAdminEdit" value="ADMIN"
                                        <c:if test="${INFORM.role}">
                                            checked="checked"
                                        </c:if> />
                </p>
                <input type="submit" value="Update" name="btAction"/>
            </form>
        </div>
    </body>
</html>
