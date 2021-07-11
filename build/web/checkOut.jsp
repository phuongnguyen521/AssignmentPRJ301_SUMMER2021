<%-- 
    Document   : checkOut
    Created on : Jul 5, 2021, 2:38:26 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Check Out</title>
        <link href="css/tableCss.css" rel="stylesheet" type="text/css"/>
        <link href="css/shoppingCss.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <h1>Your order include</h1>
        <c:set var="cart" value="${sessionScope.CART.items}"/>
        <c:if test="${empty cart}">
            You haven't added any products into your cart. 
            Please come back when your cart having products.
            <a href="shoppingServlet">Shopping Online Page</a></br>
            <a href="login">Login Page</a>
        </c:if>
        <c:if test="${not empty cart}">
            <c:set var="totalOrder" value="${0}"/>
            <table border="1" class="content_table">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Total</th>
                    </tr>
                </thead>
                <tbody>
                <form action="checkOutCart"method="POST">
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
                                <f:formatNumber var="total" 
                                                value="${value.getTotal()}" 
                                                minIntegerDigits="0"/>
                                ${total}
                                <c:set var="totalOrder" 
                                       value="${Double.parseDouble(totalOrder) 
                                                + value.getTotal()}"/>
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="4">
                            Total Order
                        </td>
                        <td>
                            <f:formatNumber var="totalOrder" 
                                            value="${totalOrder}" 
                                            minIntegerDigits="0"/>
                            ${totalOrder}
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4">
                            <a href="shoppingServlet">Add more items to cart</a> 
                        </td>
                        <td>
                            <input type="submit" 
                                   value="Check Out" 
                                   name="btAction" 
                                   class="button"/>
                        </td>
                    </tr>
                </form>
            </tbody>
        </table>
    </c:if>
</body>
</html>
