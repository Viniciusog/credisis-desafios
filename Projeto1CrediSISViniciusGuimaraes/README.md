## Sobre o projeto
1 - O programa interpreta um Json, extrai as informações necessárias e guarda no banco de dados
<br> as conta correntes e os lançamentos referentes a elas. <br>

2 - INSERT, DELETE, UPDATE e SELECT de Conta Corrente e métodos de banco de dados para 
manipular os lançamentos.

3 - Utilização do banco de dados MySQL e biblioteca ```json-simple```para leitura de Json

## Requisitos

- MySQL instalado.
- Java 11 ou versões superiores.
- Maven 3.6.3 ou versões superiores.
- Intellj IDEA Community Edition ou sua IDE favorita.

## Como executar ?

1 - Primeiro entre com o projeto na sua IDE, vá no arquivo ```application.properties``` e troque o nome de usuário e senha  <br>
para os respectivos do seu MySQL
```properties
spring.datasource.username=COLOQUE_SEU_NOME_USUARIO_MYSQL
spring.datasource.password=COLOQUE_SUA_SENHA_MYSQL
```

2 - Abra o seu MySQL Workbench e digite os seguintes comandos SQL:
```roomsql
CREATE DATABASE credisis2db;

USE credisis2db;

CREATE TABLE conta_corrente (
	id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
	codigo VARCHAR(100) NOT NULL,
	saldo DECIMAL (10,2) NOT NULL,
	nome_titular VARCHAR(200) NOT NULL
);

CREATE TABLE lancamento (
        id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
        id_conta INTEGER NOT NULL,
        conta VARCHAR(100) NOT NULL,
        debito DECIMAL (10,2) NOT NULL,
        credito DECIMAL (10,2) NOT NULL,
        tipo_lancamento VARCHAR(50) NOT NULL,
        descricao VARCHAR(150) NOT NULL,
        criado_em VARCHAR(100) NOT NULL,
        sistema VARCHAR(20) NOT NULL,
        FOREIGN KEY (id_conta) REFERENCES conta_corrente (id)
);

INSERT INTO Conta_Corrente (codigo, saldo, nome_titular) VALUES ("0000120-5", 0.0, "Anderson");
INSERT INTO Conta_Corrente (codigo, saldo, nome_titular) VALUES ("ABC123", 0.0, "Felipe");
```

4 - Feito o passo anterior, execute o arquivo ```Programa``` para que o projeto começe a funcionar. <br>
5 - Se tudo tiver dado certo, o saldo da Conta Corrente de Anderson e Felipe será <br> mostrado no console da sua IDE da seguinte forma: <br>
```ssh
| ---- DADOS DE CONTA CORRENTE ---- |
| SALDO ANDERSON: 650.0
| SALDO FELIPE: 150.0
```
6 - Você pode mudar a data de filtro colocando uma nova data na variável ```DATA_PARA_FILTRAR```, <br>
de acordo com o padrão de datas definido pela ISO 8601:
```properties 
yyyy-MM-ddTHH:mm:ssZ
```

7 - Vá ao MySQL Workbench e digite:
 - Para ver as contas correntes:
```roomsql
SELECT * FROM conta_corrente;
```
 - Para ver os lançamentos:
```roomsql
SELECT * FROM lancamento;
```
Feito por Vinícius Guimarães
