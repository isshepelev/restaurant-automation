<!DOCTYPE html>
<html>
<head>
    <title>Меню</title>
</head>
<body>
<h4>Меню</h4>
<form action="/basket" method="get">
    <input type="submit" value="корзина">
</form>
<#list products as product>
    <b>Название товара: </b>${product.name}<br>
    <b>Описание товара: </b>${product.description}<br>
    <b>Цена: </b>${product.price}<br>
    <img src="/img/${product.filename}" height="70px"/><br>
    <form action="/basket/addToBasket/${product.id}" method="post">
        <input type="submit" value="Добавить"/>
    </form>
    <hr>
</#list>
</body>
</html>