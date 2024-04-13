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