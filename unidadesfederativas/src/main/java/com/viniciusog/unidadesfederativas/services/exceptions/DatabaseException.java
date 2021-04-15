package com.viniciusog.unidadesfederativas.services.exceptions;

public class DatabaseException extends RuntimeException {
    //Essa classe de exceção será utiliza para quando tivermos um erro que envolva o nosso banco de dados
    //Exemplo: quando tentar remover uma entidade que já possui relacionamentos, levantaremos essa exceção;
    private static final Long serialVersionUID = 1L;

    public DatabaseException(String message) {
        super(message);
    }
}