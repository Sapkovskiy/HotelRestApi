# Hotel REST API

## Описание

Hotel Management API - это RESTful приложение на Spring Boot, предназначенное для управления отелями. Оно позволяет получать информацию об отелях, добавлять новые отели, обновлять удобства и строить гистограммы по различным параметрам.

## Технологии

Java 17, Spring Boot, Hibernate, MapStruct, Spring Validation, Lombok, OpenAPI, H2, MySQL, Liquibase, JUnit5.

## Доступ к Swagger UI

API доступен по адресу: http://localhost:8092/swagger-ui.html

## API Endpoints

### Получение списка всех отелей
```
 GET /property-view/hotels
```
### Пример ответа:
``` 
[
  {
    "id": 1,
    "name": "DoubleTree by Hilton Minsk",
    "description": "A luxury hotel in Minsk",
    "address": "9 Pobediteley Avenue, Minsk, 220004, Belarus",
    "phone": "+375 17 309-80-00"
  }
]
```

### Получение отеля по ID
```
GET /property-view/hotels/{id}
```

### Добавление нового отеля
```
POST /property-view/hotels  
```

### Добавление удобства в отель по ID
```
POST /property-view/hotels/{id}/amenities
```

### Получение гистограммы по параметру (город,бренд,удобства)
```
GET /property-view/hotels/histogram/{param}
```