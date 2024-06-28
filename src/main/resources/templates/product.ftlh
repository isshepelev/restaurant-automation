<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Создание продукта</title>
</head>
<body>
<h1>Создание продукта</h1>
<form action="/product/create" method="post" enctype="multipart/form-data">
    <div>
        <label for="name">Название:</label>
        <input type="text" id="name" name="name" required>
    </div>
    <div>
        <label for="description">Описание:</label>
        <textarea id="description" name="description" required></textarea>
    </div>
    <div>
        <label for="price">Цена:</label>
        <input type="number" step="0.01" id="price" name="price" required>
    </div>
    <div>
        <label for="count">Количество товара:</label>
        <input type="number" id="count" name="count" required>
    </div>
    <div>
        <input type="file" name="file">
    </div>
    <div>
        <button type="submit">Создать продукт</button>
    </div>
</form>
</body>
</html>