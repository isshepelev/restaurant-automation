<!DOCTYPE html>
<html>
<head>
    <title>продукты</title>
</head>
<body>
<h4>информация о товарах</h4>
<#list products as product>
    <b>Название товара: </b>${product.name}<br>
    <b>Описание товара: </b>${product.description}<br>
    <b>Цена: </b>${product.price}<br>
    <b>Кол-во: </b>${product.count}<br>
    <form action="/product/delete/${product.id}" method="post">
        <input type="submit" value="Удалить товар"/>
    </form>
    <hr>
</#list>

</body>
</html>