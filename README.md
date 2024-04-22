# expandconverter
Демонстрационный проект для JPoint 2024

# Структура директорий и файлов:
    expandconverter                         # Корень проекта
    ├── expandconverter                     # Основной проект по конвертации с API
    ├── graphql                             # Проект демонстрации работы Spring GraphQL
    └── infrustructure                      # Проект для локальной инфраструктуры

# Запуск локальной среды
Локальная среда представляет из себя файл для docker-compose и включает:
* Postgres 15
* PgAdmin

Для запуска требуется
1. Перейти в папку проекта infrustructure
2. Запустить
```cmd
docker-compose up
```
Если необходимо остановить
```cmd
docker-compose down
```

# Работа с проектами
## Генерация данных
Для генерации данных требуется выполнить POST запрос к API
```
http://localhost:8080/api/v1/data/generateData
```

## API expandconverter
* API версии 1 (GET запрос к API)
```
http://localhost:8080/api/v1/orders?size=2000&expand=person.address,positions.item.category,positions.item.producer.country,deliveryType
```
* API версии 2 (GET запрос к API)
```
http://localhost:8080/api/v2/orders?size=2000&expand=person.address,positions.item.category,positions.item.producer.country,deliveryType
```

## graphql
Компилируем проект (для генерации классов из схемы)
```cmd
mvn compile
```
Доступен graphiql по адресу
```
http://localhost:8081/graphiql?path=/graphql
```
Пример запроса всех данных
```json
{
    orders {
        id
        positions {
            id
            count
            item {
                id
                description
                category {
                    id
                    description
                }
                producer {
                    id
                    name
                    country {
                        id
                        name
                    }
                }
            }
        }
        person {
            id
            firstName
            lastName
            address {
                id
                location
            }
        }
        deliveryType {
            id
            type
        }
    }
}
```