<%-- 
    Document   : viewCart
    Created on : Jul 5, 2021, 10:39:10 AM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart Page</title>
    </head>
    <body>
        <c:set var="cart" value="${sessionScope.CART.items}"/>
        <c:if test="${empty cart}">
            <h1>Your Cart is empty</h1>
            <a href="shopping_servlet">
                Click here to return Shopping Online Page</a></br>
            <a href="login">Click here to return Login Page</a>
        </c:if>
        <c:if test="${not empty cart}">
            <h1>Your cart</h1>
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Name</th>
                        <th>Price (unit)</th>
                        <th>Quantity</th>
                        <th>Remove</th>
                    </tr>
                </thead>
                <tbody>
                <form action="processCart"method="POST">
                    <c:forEach var="items" items="${cart}" 
                               varStatus="Counter">
                        <tr>
                            <c:set var="value" value="${items.value}"/>
                            <td>
                                ${Counter.count}
                                <input type="hidden" name="txtSku" 
                                       value="${items.key}" />
                            </td>
                            <td>
                                ${value.getName()}
                            </td>
                            <td>
                                <f:formatNumber var="price" 
                                                value="${value.getPrice()}" 
                                                minIntegerDigits="0"/>
                                ${price}
                            </td>
                            <td>
                                ${value.getQuantity()}
                            </td>
                            <td>
                                <input type="checkbox" 
                                       name="chkItems" 
                                       value="${items.key}" />
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="4">
                            <a href="shopping_servlet">Add more items to cart</a>
                        </td>
                        <td>
                            <input type="submit" 
                                   value="Remove Items From Cart" 
                                   name="btAction" />
                        </td>
                        <td>
                            <input type="submit" 
                                   value="Check Out" 
                                   name="btAction" />
                        </td>
                    </tr>
                </form>
            </tbody>
        </table>
    </c:if>
</body>
</html>
