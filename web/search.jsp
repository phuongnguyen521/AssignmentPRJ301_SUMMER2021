<%-- 
    Document   : search
    Created on : Jul 2, 2021, 2:39:58 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
        <link href="css/searchCss.css" rel="stylesheet" type="text/css"/>
        <link href="css/tableCss.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <c:choose>
            <c:when test="${empty sessionScope.USERNAME}">
                <c:set var="SearchValueResult" value=""/>
            </c:when>
            <c:otherwise>
                 <c:set var="SearchValueResult" value="${sessionScope.LASTSEARCHVALUE}"/>
            </c:otherwise>
        </c:choose>
        <form action="searchAccount" class="search">
            <h1>Search Page</h1>
            Search Value <input type="text" name="txtSearchValue" 
                                value="${SearchValueResult}" /> <input type="submit" 
                                value="Search" name="btAction"/>
        </form>
        <br/>
        <c:if test="${not empty sessionScope.USERNAME}">
            <c:set var="searchValue" value="${param.txtSearchValue}"/>
            <c:if test="${not empty searchValue}">
                <c:set var="result" value="${sessionScope.SEARCHRESULT}"/>
                <c:if test="${not empty result}">
                    <table border="1" class="content_table">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>UserName</th>
                                <th>Password</th>
                                <th>LastName</th>
                                <th>Role</th>
                                <th>Delete</th>
                                <th>Update</th>
                                <th>Edit</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="dto" items="${result}" varStatus="counter">
                                <c:set var="error" value="${sessionScope.ERROR_PASS}"/>
                            <form action="updateRecord">
                                <tr>
                                    <td>
                                        ${counter.count}
                                    </td>
                                    <td>
                                        ${dto.username}
                                        <input type="hidden" name="txUsername" 
                                               value="${dto.username}" />
                                    </td>
                                    <td>
                                        <input type="text" name="txtPassword" 
                                               value="${dto.password}" />
                                    </td>
                                    <td>
                                        ${dto.lastname}
                                        <input type="hidden" name="txtLastname" 
                                               value="${dto.lastname}" />
                                    </td>
                                    <td>
                                        <input type="checkbox" name="chkAdmin"
                                               value="ADMIN"
                                               <c:if test="${dto.role}">
                                                   checked="checked"
                                               </c:if>/>
                                    </td>
                                    <td>
                                        <c:set var="deleteValue" value="deleteRecord"/>
                                        <c:if test="${dto.lastname eq sessionScope.USERNAME}">
                                            <c:set var="deleteValue" value="deleteProcess"/>
                                        </c:if>
                                        <c:url var="urlRewriting" value="${deleteValue}">
                                            <c:param name="pk" value="${dto.username}"/>
                                            <c:param name="lastSearchValue" 
                                                     value="${param.txtSearchValue}"/>
                                        </c:url>
                                        <a href="${urlRewriting}">Delete</a>
                                    </td>
                                    <td>
                                        <input type="submit" value="Update" 
                                               name="btAction" />
                                        <input type="hidden" name="lastSearchValue" 
                                               value="${param.txtSearchValue}" />
                                    </td>
                                    <td>
                                        <c:url var="urlRewritingEdit" 
                                               value="editAccount">
                                            <c:param name="pk" value="${dto.username}"/>
                                            <c:param name="lastSearchValue" 
                                                     value="${param.txtSearchValue}"/>
                                        </c:url>
                                        <a href="${urlRewritingEdit}">Edit</a>
                                    </td>
                                </tr>
                                <c:if test="${not empty error}">
                                    <c:if test="${error.username eq dto.username}">
                                        <tr>
                                            <td colspan="8">
                                                <font color="red">
                                                ${error.passwordLenghErr}
                                                </font>
                                            </td>
                                        </tr>
                                    </c:if>
                                </c:if>
                            </form>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <c:if test="${empty result}">
                <h2>No record is matched</h2>
            </c:if>
        </c:if>
    </c:if>
</body>
</html>
