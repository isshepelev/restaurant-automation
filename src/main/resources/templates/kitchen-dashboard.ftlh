<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Панель кухни</title>
    <style>
        .order-container {
            margin-bottom: 20px;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        .order-item {
            margin-bottom: 10px;
        }
        .prepared-button {
            margin-top: 10px;
        }
    </style>
</head>
<body>
<h1>Активные заказы</h1>

<#list orders as order>
    <div class="order-container">
        <p><strong>Время заказа:</strong> ${order.timestamp?string("dd.MM.yyyy HH:mm:ss")}</p>
        <p><strong>Индивидуальный код:</strong> ${order.individualCode}</p>

        <h3>Товары:</h3>
        <#list order.items as item>
            <div class="order-item">
                <p><strong>Название:</strong> ${item.product.name}</p>
                <p><strong>Количество:</strong> ${item.quantity}</p>
            </div>
        </#list>

        <form action="/kitchen-dashboard/mark-as-prepared/${order.id}" method="post">
            <button class="prepared-button" type="submit">Пометить как готовый</button>
        </form>
    </div>
</#list>
</body>
</html>