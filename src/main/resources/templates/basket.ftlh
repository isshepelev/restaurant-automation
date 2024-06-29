<!DOCTYPE html>
<html>
<head>
    <title>Корзина</title>
</head>
<body>
<h4>Корзина</h4>
<form action="basket/clear" method="post">
    <input type="submit" value="очистить корзину">
</form>
<form action="order" method="post">
    <input type="submit" value="оформить заказ">
</form>
    <#list basket.products as product>
        <b>Название товара: </b>${product.name}<br>
        <b>Описание товара: </b>${product.description}<br>
        <b>Цена: </b>${product.price}<br>
        <img src="/img/${product.filename}" height="70px"/><br>
        <form action="/basket/remove/${product.id}" method="post">
            <input type="submit" value="Удалить из корзины"/>
        </form>
        <hr>
    </#list>
</body>
</html>
