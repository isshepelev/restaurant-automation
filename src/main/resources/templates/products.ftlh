<!DOCTYPE html>
<html>
<head>
    <title>Продукты</title>
</head>
<body>
<h4>Информация о товарах</h4>
<#list products as product>
    <b>Название товара: </b>${product.name}<br>
    <b>Описание товара: </b>${product.description}<br>
    <b>Цена: </b>${product.price}<br>
    <b>Кол-во: </b>${product.count}<br>
    <img src="/img/${product.filename}" height="70px"/><br>
    <form action="/product/delete/${product.id}" method="post">
        <input type="submit" value="Удалить товар"/>
    </form>
    <hr>
</#list>
</body>
</html>