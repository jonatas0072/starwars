# API Star Wars!

![Author](http://img.shields.io/badge/Author%20-Jonatas%20Macedo-green)

#  Projeto
> O projeto consite em ter API's de interação para o gerenciamento de Rebeldes.

### Tecnologias

Este projeto foi desenvolvido utilizando as seguintes tecnologia:

* [Java](https://www.java.com/en/download/help/java8.html) - Linguagem de Programação do Projeto
* [Spring Boot](https://spring.io/projects/spring-boot) - Projeto do framework Spring utilizado para agilizar a configuração e publicação da aplicação
* [Hibernate](https://hibernate.org/) -Ferramenta ORM
* [JPA](https://www.oracle.com/java/technologies/persistence-jsp.html) - API para frameworks de persistência de dados
* [Lombok](https://projectlombok.org/) - Utilizada para deixa o codigo menos verboso e aumento de agilidade
* [Junit 5](https://junit.org/junit5/) - Framework utilizado para testes
* [H2](https://www.h2database.com/html/main.html) - Bando de dados relacional em memória
* [Swagger](https://swagger.io/) - Ferramenta para mapear e documentar as API's

## Requisitos para Compilação

Acesse seu projeto via terminal

## Step 1

Faça o Download do projeto:
````
git clone https://github.com/jonatas0072/starwars.git
````

## Step 2

Para o compilar o projeto é necessário : 

````
mvn clean package -DskipTests
````
## Step 3

Acesse a pasta target, logo em seguida execute :
````
java -jar starwars-0.0.1-SNAPSHOT.jar
````

## Step 4
````
http://localhost:8080/swagger-ui.html#/rebelde-controller
````


