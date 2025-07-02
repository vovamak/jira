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
5. Написать тесты для всех публичных методов контроллера ProfileRestController
6. Добавить новый функционал: добавления тегов к задаче (REST API + реализация на сервисе).
-TaskController, -TaskRepository,TaskService, TaskControllerTest
7. Добавить подсчет времени.  Написать 2 метода на уровне сервиса
   -calculateWorkTime Сколько задача находилась в работе (ready_for_review минус in_progress )
   -calculateTotalTimeReviewToDone Сколько задача находилась на тестировании (done минус ready_for_review).