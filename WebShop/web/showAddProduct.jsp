<%-- 
    Document   : showAddProduct
    Created on : 28.04.2022, 0:07:00
    Author     : 37255
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Добавить товар</title>
    </head>
    <body>
        <h1>Создать товар</h1>
        
        <form action="createProduct" method="POST">
            Название товара<br>
            <input type="text" name="name"><br>
            
            Стоимость товара<br>
            <input type="text" name="price"><br>
            
            Количество товара<br>
            <input type="text" name="quantity"><br>
            
            <input type="submit" value="Создать">
        </form>
    </body>
</html>
