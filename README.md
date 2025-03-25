# Calorie Tracker ###
## REST API Приложение для подсчета калорий

## Инструкция по запуску и использованию

1) Скачайте и рапакуйте архив с приложением с GitHub\склонируйте его
2) Убедитесь что Docker запущен
3) Находясь в корне проекта выполните команду   docker-compose up --build
4) Приложение, БД PostgreSQL и pgAdmin запущены
5) Для остановки приложения выполните команду docker-compose down

* Приложение запускается на локальном порту: 8080
* Демо-коллекция для Postman лежит в корне проекта: Calorie Tracker API.postman_collection.json. Добавляется в Postman через import
* Ссылка на документацию: http://localhost:8080/swagger-ui/index.html (приложение должно быть запущено)

### Примеры эндпоинтов для получения отчетов для User с ID = 1 и даты 2025-03-24T23:30:10 (остальные эндпоинты полностью стандартные и описаны в документации):
* http://localhost:8080/reports/1/daily?date=2025-03-24T23:30:10
* http://localhost:8080/reports/1/check-limit?date=2025-03-24T23:30:10
* http://localhost:8080/reports/1/history

### Приложение создано в демо-режиме. Полная документация, полная функциональность, контейнерное тестирование и прочие плюшки будут доступны только в платном режиме ;)

по всем вопросам и предложениям писать владельцу репозитория. Email: sshibkodev@gmail.com



-------------------------------------------------------------------------------------------------------------------------------
