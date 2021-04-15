package com.viniciusog.unidadesfederativas.services;

import com.viniciusog.unidadesfederativas.entities.Estado;
import com.viniciusog.unidadesfederativas.services.exceptions.DatabaseException;
import com.viniciusog.unidadesfederativas.services.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import com.viniciusog.unidadesfederativas.repositores.EstadoRepository;

import javax.persistence.EntityNotFoundException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

@Service
//Usamos @RequiredArgsConstructor devido ao padrão de remover ao máximo as dependências
//O programa, em tempo de compilação, criará um construtor com todos os atributos que estão
//final na nossa classe
@RequiredArgsConstructor
public class EstadoService {

    private final EstadoRepository estadoRepository;

    public Estado insert(Estado estado) {
        return estadoRepository.save(estado);
    }

    public Estado findById(Long id) {
       //VERIFICAR ERRO CASO NÃO CONSIGA ACHAR ESTADO POR ID
       Optional<Estado> optional =  estadoRepository.findById(id);
       return optional.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public void deleteById(Long id) {
        try {
            estadoRepository.deleteById(id);
        }
        //Caso não exista estado com o id passado por parâmetro
        catch (EmptyResultDataAccessException e1) {
            throw new ResourceNotFoundException(id);
        }
        //Caso estado já tenha relações no banco de dados
        catch (DataIntegrityViolationException e2) {
            throw new DatabaseException(e2.getMessage());
        }
    }

    public Estado updateById(Long id, Estado estadoFromController) {
        try {
            //VERIFICAR ERRO CASO NÃO CONSIGA ACHAR ESTADO POR ID
            //Pega o Estado já cadastrado no banco de dados
            Estado estadoFromDataBase = estadoRepository.getOne(id);
            //Atualiza as informações novas
            updateData(estadoFromController, estadoFromDataBase);
            //Salva o mesmo Estado, agora atualizado, no banco de dados.
            return estadoRepository.save(estadoFromDataBase);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    public List<Estado> findAll() {
        return estadoRepository.findAll();
    }

    private void updateData(Estado estadoFromController, Estado estadoFromDatabase) {
        //estadoFromDatabase.setCidades(estadoFromController.getCidades());
        estadoFromDatabase.setNome(estadoFromController.getNome());
        estadoFromDatabase.setSigla(estadoFromController.getSigla());
    }
}
