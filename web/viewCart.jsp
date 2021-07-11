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
        <link rel="stylesheet" href="css/tableCss.css" type="text/css"/>
        <link rel="stylesheet" href="css/viewCartCss.css" type="text/css"/>
        <title>View Cart Page</title>
    </head>
    <body>
        <jsp:include page="header.jsp" flush="true"/>
        <c:set var="cart" value="${sessionScope.CART.items}"/>
        <c:choose>
            <c:when test="${not empty cart}">
                <h1>Your cart</h1>
                <table border="1" class="content_table">
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
                                    <div class="checkboxCart">
                                        <input type="checkbox" 
                                               name="chkItems" 
                                               value="${items.key}" />
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td colspan="4">
                                <a href="shoppingServlet">Add more items to cart</a>
                            </td>
                            <td>
                                <input type="submit" 
                                       value="Remove Items" 
                                       name="btAction"
                                       class="button_Remove"/>
                            </td>
                            <td colspan="2">
                                <input type="submit" 
                                       value="Check Out" 
                                       name="btAction"/>
                            </td>
                        </tr>
                    </form>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <div class="viewCartEmpty">
                <h1>Your Cart is empty</h1>
            </div>
        </c:otherwise>
    </c:choose>
</body>
</html>
