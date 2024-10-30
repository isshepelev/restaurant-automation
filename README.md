# Restaurant Automation

## Описание

Restaurant Automation — это система автоматизации для ресторанов, вдохновленная концепциями быстрого питания, такими как McDonald's и KFC. Она включает в себя функциональность для управления заказами, отображения активных заказов на кухне и позволяет пользователям легко взаимодействовать с меню.

## Функциональность

- **Управление заказами**: Пользователи могут добавлять и удалять элементы из корзины, а также оформлять заказы.
- **Индивидуальные коды заказов**: После оформления заказа пользователи получают уникальный код, аналогичный номерам заказов в KFC.
- **Отображение активных заказов**: Кухня может видеть текущие заказы и помечать их как готовые.
- **Отправка заказов на кухню через Kafka**: Заказы отправляются в очередь и обрабатываются, что гарантирует их получение даже в случае сбоя приложения.
- **Отчетность**: Генерация отчетов о заказах за определенные временные периоды.

## Технологии

- **Spring Boot**: Основной фреймворк для разработки приложения.
- **Kafka**: Используется для обмена сообщениями между компонентами системы.
- **PostgreSQL**: Система управления базами данных для хранения данных о заказах и клиентах.
- **Redis**: Используется для кеширования и улучшения производительности.
- **Docker**: Контейнеризация приложения для упрощения развертывания и управления зависимостями.
