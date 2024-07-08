<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Готовые заказы</title>
    <style>
        .order-container {
            margin-bottom: 20px;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        .individual-code {
            font-size: 2em;
            font-weight: bold;
        }
    </style>
</head>
<body>
<h1>Готовые заказы</h1>

<#list orders as order>
    <div class="order-container">
        <p class="individual-code">${order.individualCode}</p>
    </div>
</#list>

</body>
</html>