<%-- 
    Document   : shoppingOnline
    Created on : Jul 4, 2021, 2:39:18 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping Online Page</title>
    </head>
    <body>
        <a href="login">Login Page</a>
        <h1>Shopping Online Page</h1>
        <c:set var="ERROR" value="${requestScope.SHOPPING_ERROR}"/>
        <c:if test="${empty ERROR}">
            <c:set var="LIST" value="${requestScope.PRODUCT_LIST}"/>
            <c:if test="${not empty LIST}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Name</th>
                            <th>Price</th>
                            <th>Description</th>
                            <th>Quantity</th>
                            <th>Add Items to Cart</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="list" items="${LIST}" varStatus="counter">
                        <form action="addItemsToCart_servlet">
                            <tr>
                                <td>
                                    ${counter.count}
                                    <input type="hidden" name="txtSku" value="${list.sku}" />
                                </td>
                                <td>
                                    ${list.name}
                                </td>
                                <td>
                                    <f:formatNumber var="price" 
                                                    value="${list.price}" 
                                                    minIntegerDigits="0"/>
                                    ${price}
                                </td>
                                <td>
                                    ${list.description}
                                </td>
                                <td>
                                    ${list.quantity}
                                </td>
                                <td>
                                    <input type="submit" value="Add" name="btAction" />
                                    <input type="hidden" name="ItemsToCart" value="${list.sku}">
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                </tbody>
            </table>
            <form action="viewCart_servlet">
                <input type="submit" value="View Your Cart" name="btAction" />
            </form>
        </c:if>
    </c:if>

</body>
</html>
