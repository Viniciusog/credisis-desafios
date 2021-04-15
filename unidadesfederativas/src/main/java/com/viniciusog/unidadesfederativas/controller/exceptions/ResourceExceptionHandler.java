package com.viniciusog.unidadesfederativas.controller.exceptions;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import com.viniciusog.unidadesfederativas.services.exceptions.DatabaseException;
import com.viniciusog.unidadesfederativas.services.exceptions.ResourceNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.Data;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {
    //Aqui é a nossa 'Central' de gerenciamento de todos os erros da aplicação
    //'Monitora' as exceções e dá o devido tratamento para elas.

    //Trata os erros do tipo ResourceNotFoundException
    //Recebe o Erro gerado e a request que causou no erro.
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        String error = "Resource not found.";
        HttpStatus status = HttpStatus.NOT_FOUND;

        //Cria o 'objeto de erro'
        StandardError standardError = new StandardError(Instant.now(), status.value(), error,
                e.getMessage(), request.getRequestURI());

        //Retorna o objeto de erro na RESPONSE
        return ResponseEntity.status(status).body(standardError);
    }

    //Trata os erros do tipo DatabaseException
    //Recebe o Erro gerado e a request que causou no erro.
    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request) {
        String error = "Database error.";
        HttpStatus status  = HttpStatus.BAD_REQUEST;

        StandardError standardError = new StandardError(Instant.now(), status.value(),
                error, e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(standardError);
    }
}