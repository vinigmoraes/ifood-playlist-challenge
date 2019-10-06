**Ifood technical challenge**

[![CircleCI](https://circleci.com/gh/vinigmoraes/ifood-challenge.svg?style=svg)](https://circleci.com/gh/vinigmoraes/ifood-challenge)

## Requirements

For building and running the application you need:

- [Gradle](https://gradle.org/)
- [Docker](https://www.docker.com/)

## Executing requests

[![Run in Postman](https://run.pstmn.io/button.svg)](https://documenter.getpostman.com/view/826217/SVtR3rEt)


#### Running with docker

```shell
./gradlew build && docker-compose up --build
```

##  Testing

```shell
./gradlew test
```

## Built With

- [Kotlin](https://kotlinlang.org/) - Programming language
- [IntelliJ](https://www.jetbrains.com/idea/) - IDE
- [Ktor](https://ktor.io) - Lightweight Web Framework
- [Gradle](https://gradle.org/) - Dependency Management
- [Docker](https://www.docker.com/) - Containerization Platform

