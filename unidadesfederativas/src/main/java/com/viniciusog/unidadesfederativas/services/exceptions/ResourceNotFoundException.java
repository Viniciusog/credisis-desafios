package com.viniciusog.unidadesfederativas.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    //Retonaremos essa exceção quando uma entidade não for encontrada no banco de dados.

    private static final Long serialVersionUID = 1L;

    public ResourceNotFoundException(Object id) {
        super("Entity not found. Id: " + id);
    }
}