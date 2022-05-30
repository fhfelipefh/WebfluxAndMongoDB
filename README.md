# MongoDB and SpringBoot WebFlux

- SpringBoot: [SpringBoot](https://spring.io/projects/spring-boot)
- MongoDB: [MongoDB](https://www.mongodb.com/)

O que é MongoDB?

MongoDB é um software de banco de dados orientado a documentos livre, de código aberto e multiplataforma, escrito na linguagem C++.

O que é SpringBoot?

O Spring Boot é um framework Java open source que tem como objetivo facilitar esse processo em aplicações Java. Consequentemente, ele traz mais agilidade para o processo de desenvolvimento, uma vez que devs conseguem reduzir o tempo gasto com as configurações iniciais.

### Qual é a utilidade de usar o MongoDB?

Sendo uma base de dados orientada a documentos e Classificada como banco de dados NoSQL, o MongoDB evita a tradicional estrutura de banco de dados relacional em favor de documentos semelhantes a JSON com esquemas dinâmicos, tornando a integração de dados em certos tipos de aplicativos mais fácil e rápido. O MongoDB é um software livre e de código aberto.

O MongoDB armazena dados em BSON. Os campos nos documentos BSON podem armazenar matrizes de valores ou documentos incorporados. No MongoDB, a construção do banco de dados é um grupo de coleções relacionadas. Cada banco de dados possui um conjunto distinto de arquivos de dados e pode conter um grande número de coleções. Uma única implantação do MongoDB pode ter muitos bancos de dados.

### Como a integração de dados é feita?

Utilizando um driver chamado ReactiveMongo para acessar o MongoDB essa aplicação opera de maneira reativa e assíncrona.

### Quais endpoints podem ser utilizados?

- GET http://localhost:8082/cities - Lista todas as cidades
- GET http://localhost:8082/cities/{name} - Lista uma cidade específica por nome
- POST http://localhost:8082/cities - Insere uma nova cidade
- PUT http://localhost:8082/cities/{name} - Atualiza uma cidade
- DELETE http://localhost:8082/cities/{name} - Deleta uma cidade

[//]: # ( Path: src/main/java/com/example/demo/web/CityController.java)
