## Sobre o projeto <br>
- URI padrão: ```http://localhost:8080/api/v1/```
- A API de gerenciamento de Estados e Cidades possui os seguintes métodos HTTP: <br>
```GET, PUT, DELETE, POST``` <br>
- Gerenciamento de exceções com geração de mensagem personalizada para cada tipo de erro.
- Uso do ```lombok``` para diminuição das dependências entre as classes.
- Banco de dados MySQL para o desenvolvimento e H2 Database para perfil de teste.

---
### Cidade
```json
{
    "nome": "Ji-Paraná",
    "prefeito": "Prefeito de Ji-Paraná",
    "populacao": 120000,
    "estado": {
        "id": 1,
        "nome": "Rondônia",
        "sigla": "RO"
    }
}
```

### Estado

```json
{
   "id": 1,
   "nome": "Rondônia",
   "sigla": "RO"
}
```

----
## Requisitos

- MySQL instalado.
- Java 11 ou versões superiores.
- Maven 3.6.3 ou versões superiores.
- Postman
- Intellj IDEA Community Edition ou sua IDE favorita.

## Execução
1 - Abra o projeto na sua IDE, vá até o arquivo ```application-dev.properties``` e coloque a senha e nome de usuário do seu MySQL
```properties
spring.datasource.username=SEU_NOME_USUARIO_MYSQL
spring.datasource.password=SUA_SENHA_MYSQL
```
2 - Abra o seu MySQL Workbench, digite e execute os códigos abaixo:
``` roomsql
CREATE DATABASE credisis1db;
USE credisis1db;

CREATE TABLE Estado (
	id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nome VARCHAR(100) NOT NULL,
	sigla VARCHAR(4) NOT NULL
);

CREATE TABLE Cidade (
	id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nome VARCHAR(100) NOT NULL,
	prefeito VARCHAR(100) NOT NULL,
	populacao INTEGER NOT NULL,
	estado_id INTEGER NOT NULL,
	FOREIGN KEY (estado_id) REFERENCES Estado (id)
);

INSERT INTO Estado (nome, sigla) VALUES ("Rondônia", "RO");
INSERT INTO Estado (nome, sigla) VALUES ("Rio de Janeiro", "RJ");
INSERT INTO Estado (nome, sigla) VALUES ("Paraná", "PR");

INSERT INTO Cidade (nome, populacao, prefeito, estado_id) VALUES ("Ji-Paraná", 120000, "Prefeito de Ji-Paraná", 1);
INSERT INTO Cidade (nome, populacao, prefeito, estado_id) VALUES ("Niterói", 450000, "Prefeito de Niterói", 2);
INSERT INTO Cidade (nome, populacao, prefeito, estado_id) VALUES ("Rio de Janeiro", 8000000, "Prefeito de Rio de Janeiro", 2);
INSERT INTO Cidade (nome, populacao, prefeito, estado_id) VALUES ("Curitiba", 1500000, "Prefeito de Curitiba", 3);

```

3 - Para que o projeto seja iniciado utilizando o MySQL, apenas execute a classe ```UnidadesfederativasApplication```

4 - Feito isso, caso ocorra tudo certo, a API será inicializada na URI
```http://localhost:8080/api/v1/estados``` e ```http://localhost:8080/api/v1/cidades```

## Uso da API

- Para conseguir usar todas as funcionalidades da API, abra o seu aplicativo Postman.
- Caso seja sua primeira vez utilizando o Postman, vá em: "Start with something new (Create new)" 
  -> Collections -> Selecione 'GET' e coloque a URI ```http://localhost:8080/api/v1/estados``` para ver todos os estados.
- Selecione 'GET' e coloque a URI ```http://localhost:8080/api/v1/cidades``` para ver todas as cidades
---
- Para inserir um estado selecione 'POST'. Em 'raw' selecione 'json' e digite:
```json
{
   "nome": "Paraíba",
   "sigla": "PB"
}
```
---
- Para inserir uma cidade selecione 'POST'. Em 'raw' selecione 'json' e digite:
```json
{
    "nome": "João Pessoa",
    "prefeito": "Prefeito de João Pessoa",
    "populacao": 817511,
    "estado": {
        "id": 5,
        "nome": "Paraíba",
        "sigla": "PB"
    }
}
```
---
- Para deletar o estado de id 2, coloque no campo da URI, o seguinte: ```http://localhost:8080/api/v1/estados/2``` e selecione a opção 'DELETE'
---
- Para deletar a cidade de id 2, coloque no campo da URI, o seguinte: ```http://localhost:8080/api/v1/cidades/2``` e selecione a opção 'DELETE'
---
- Para atualizar o estado de id 1, coloque no campo da URI, o seguinte: ```http://localhost:8080/api/v1/estados/1```, selecione a opção 'PUT'. 
  <br> Na aba 'Header' coloque ==> Key:Content-Type e Value:application/json. 
  <br> Na aba 'Body' coloque um objeto json que será atualizado no Estado. <br> Exemplo:

```json
{
  "nome": "Rondônia Editado",
  "sigla": "RO"
}
```
---
- Para atualizar a cidade de id 1, coloque no campo da URI, o seguinte: ```http://localhost:8080/api/v1/cidades/1```, selecione a opção 'PUT'.
  <br> Na aba 'Header' coloque ==> Key:Content-Type e Value:application/json.
  <br> Na aba 'Body' coloque um objeto json que será atualizado na Cidade. <br> Exemplo:

```json
{
  "nome": "Ji-Paraná Editada",
  "prefeito": "Prefeito de Ji-Paraná",
  "populacao": 120000
}
```

## Testando o projeto com H2 Database
Caso você tenha erro com o MySQL, pode executar o projeto utilizando o banco de dados <br>
em memória H2 Database. Para isso, basta seguir os seguintes passos:
<br> 
1 - Vá até o arquivo ```application.properties``` e deixe ele da seguinte forma:

```properties
#Será executado o arquivo application-test.properties
spring.profiles.active=test
spring.jpa.open-in-view=true
```

2 - Em seguinte execute o arquivo ```UnidadesfederativasApplication``` <br>
3 - Abra uma aba no seu navegador e digite a URI: ```http://localhost:8080/h2-console``` <br>
4 - Coloque as seguintes informações:
```properties
JDBC URL: jdbc:h2:mem:credisisdb
User Name: credisis
```
5 - Feito isso, aperte em ```Connect```, o banco de dados irá abrir, mostrando as tabelas 'cidade' e 'estado' <br>
6 - Clique na tabela 'Cidade', um comando SQL (```SELECT * FROM Cidade```) irá aparecer no campo de escrita <br>
7 - Aperte em ```Run``` para que o comando SQL seja executado. Dessa forma a tabela e seu conteúdo serão mostrados no navegador <br>
8 - Repita os dois últimos passos mudando para realizar para a tabela Estado.

### O que faltou fazer? 
- Testes Unitários com JUnit e Mockito
- Configuração do Docker e Docker Compose
- Sistema de Login

Feito por Vinícius Guimarães
