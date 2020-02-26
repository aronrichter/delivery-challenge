# delivery-challenge

Tecnologias utilizadas:
- Java 11 
- Spring Boot 2.2.4
- MongoDB
- Maven

Para executar o projeto é necessário:
- iniciar o container utilizando o arquivo docker-compose;
- executar o build da aplicação através do Maven, sugere-se utilizar o comando: "mvn clean package -U";
- iniciar o serivço utilizando o Java, com o comando "java -Xmx256m -jar target/delivery-0.0.1-SNAPSHOT.jar";

O projeto possui apenas um teste unitário, bem como um teste de integração utilizando o Spring Cloud Contract, apenas para exemplificar o uso.

 O projeto possui um arquivo chamado "insomnia.json" que contém as requisições que atendem aos requisitos:\
 1- criação do ponto de venda;\
 2- busca do ponto de venda pelo ID;\
 3- busca do ponto de venda mais próximo com base nas coordenadas;
