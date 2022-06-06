<%-- 
    Document   : listProduct
    Created on : 28.04.2022, 0:24:34
    Author     : 37255
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Список товаров в магазине</title>
    </head>
    <body>
        <h1>В наличии имеются следующие товары:</h1>
        
        <c:forEach var="product" items="${listProduct}">
            <p>${product}</p>
        </c:forEach>
        
    </body>
</html>
