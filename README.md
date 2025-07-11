## [REST API](http://localhost:8080/doc)

## Концепция:

- Spring Modulith
    - [Spring Modulith: достигли ли мы зрелости модульности](https://habr.com/ru/post/701984/)
    - [Introducing Spring Modulith](https://spring.io/blog/2022/10/21/introducing-spring-modulith)
    - [Spring Modulith - Reference documentation](https://docs.spring.io/spring-modulith/docs/current-SNAPSHOT/reference/html/)

```
  url: jdbc:postgresql://localhost:5432/jira
  username: jira
  password: JiraRush
```

- Есть 2 общие таблицы, на которых не fk
    - _Reference_ - справочник. Связь делаем по _code_ (по id нельзя, тк id привязано к окружению-конкретной базе)
    - _UserBelong_ - привязка юзеров с типом (owner, lead, ...) к объекту (таска, проект, спринт, ...). FK вручную будем
      проверять

## Аналоги

- https://java-source.net/open-source/issue-trackers

## Тестирование

- https://habr.com/ru/articles/259055/

Список выполненных задач:

2. Удалить социальные сети: vk, yandex.

5. Написать тесты для всех публичных методов контроллера ProfileRestController.

6. Сделать рефакторинг метода com.javarush.jira.bugtracking.attachment.FileUtil#upload


7. Добавить новый функционал: добавления тегов к задаче (REST API + реализация на сервисе),

в следующих классах:
-TaskController, -TaskRepository,TaskService, 
один тест написал в-TaskControllerTest. запросы тестил в Swagger

8. Добавить подсчет времени.  Написать 2 метода на уровне сервиса

   -метод calculateWorkTime Сколько задача находилась в работе (ready_for_review минус in_progress )

   -метод calculateTotalTimeReviewToDone Сколько задача находилась на тестировании (done минус ready_for_review).


9. Написать Dockerfile для основного сервера.

запуск с --network jira-network

команды:

docker network create jira-network

docker run -p 5432:5432 --name postgres-db --network jira-network -e POSTGRES_USER=jira -e POSTGRES_PASSWORD=JiraRush -e POSTGRES_DB=jira -e PGDATA=/var/lib/postgresql/data/pgdata -v ./pgdata:/var/lib/postgresql/data -d postgres

docker stop jira-app-container; docker rm jira-app-container

docker build -t jira-app .

docker run -d --name jira-app-container --network jira-network -p 8080:8080 jira-app

10. Написать docker-compose файл для запуска контейнера сервера вместе с БД и nginx.

для запуска http://localhost:80

11. Добавить локализацию минимум на двух языках для шаблонов писем (mails) и стартовой страницы index.html.