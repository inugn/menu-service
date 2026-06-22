# Menu Service

REST API сервис для управления меню ресторана. Позволяет создавать категории блюд и управлять блюдами внутри каждой категории. У каждого блюда есть состав ингредиентов.

## Технологии

- Java 17
- Spring Boot 3.5
- Spring Data JPA + Hibernate
- PostgreSQL 16
- Lombok
- Docker + docker-compose

## Запуск через Docker

```bash
# 1. Скопировать шаблон и заполнить пароль
cp .env.example .env

# 2. Запустить
docker-compose up --build

# 3. Приложение доступно на
http://localhost:8080
```

## Запуск локально

1. Создать базу данных в PostgreSQL:
```sql
CREATE DATABASE menudb;
```
2. Задать переменные окружения (см. `.env.example`)
3. Запустить `MenuApplication.java` в IntelliJ IDEA

## Переменные окружения

Скопируй `.env.example` в `.env` и заполни значения:
POSTGRES_DB=menudb

POSTGRES_USER=postgres

POSTGRES_PASSWORD=твой_пароль

## Структура базы данных
menu_category   — категории меню

dish            — блюда (привязаны к категории)

ingredient      — справочник ингредиентов

dish_ingredient — связь блюдо и ингредиенты 

## API Endpoints

### Категории

| Метод | URL | Описание |
|-------|-----|----------|
| POST | /api/categories | Создать категорию |
| GET | /api/categories | Список всех категорий |
| DELETE | /api/categories/{id} | Удалить (запрещено если есть блюда) |

### Блюда

| Метод | URL | Описание |
|-------|-----|----------|
| POST | /api/dishes?categoryId={id} | Создать блюдо |
| GET | /api/dishes | Все блюда с составом |
| GET | /api/dishes/category/{id} | Блюда конкретной категории |
| PUT | /api/dishes/{id} | Обновить (частично) |
| DELETE | /api/dishes/{id} | Удалить блюдо |

## Примеры запросов

### Создать категорию
```json
POST /api/categories
{
    "name": "Салаты",
    "description": "Свежие салаты"
}
```

### Создать блюдо с составом
```json
POST /api/dishes?categoryId=1
{
    "name": "Цезарь",
    "price": 350.0,
    "isVegetarian": false,
    "ingredients": ["Курица", "Листья салата", "Пармезан"]
}
```

### Ответ
```json
{
    "id": 1,
    "name": "Цезарь",
    "price": 350.0,
    "isVegetarian": false,
    "categoryId": 1,
    "categoryName": "Салаты",
    "ingredients": ["Курица", "Листья салата", "Пармезан"]
}
```

### Частичное обновление блюда
```json
PUT /api/dishes/1
{
    "price": 400.0
}
```
Можно передавать только те поля которые нужно изменить — остальные останутся прежними.

## Обработка ошибок

Все ошибки возвращаются в едином формате:

```json
{
    "status": 404,
    "message": "описание ошибки",
    "timestamp": "2026-06-19T12:00:00"
}
```

| Код | Когда возникает |
|-----|-----------------|
| 400 | Невалидные данные (пустое имя, отрицательная цена, неверный JSON) |
| 404 | Запись не найдена или нарушено бизнес-правило |
| 409 | Нарушение уникальности (дублирующееся имя категории) |

## Автор

Арина Павлова — [github.com/inugn](https://github.com/inugn)