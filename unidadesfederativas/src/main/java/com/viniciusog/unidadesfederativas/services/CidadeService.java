package com.viniciusog.unidadesfederativas.services;

import com.viniciusog.unidadesfederativas.repositores.CidadeRepository;
import com.viniciusog.unidadesfederativas.services.exceptions.DatabaseException;
import com.viniciusog.unidadesfederativas.services.exceptions.ResourceNotFoundException;
import com.viniciusog.unidadesfederativas.entities.Cidade;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CidadeService {

    private final CidadeRepository cidadeRepository;

    public List<Cidade> findAll() {
        return cidadeRepository.findAll();
    }

    //certo
    public Cidade findById(Long id) {
        Optional<Cidade> optinal = cidadeRepository.findById(id);
        //Quando não encontrar Cidade com o Id, gere: ResourceNotFoundException.
        return optinal.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Cidade insert(Cidade cidade) {
        return cidadeRepository.save(cidade);
    }

    public void deleteById(Long id) {
        try {
            cidadeRepository.deleteById(id);
        }
        //Quando não encontrar Cidade com o Id.
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        }
        //Quando a Cidade tiver relacionamentos no banco de dados.
        catch (DataIntegrityViolationException di) {
            throw new DatabaseException(di.getMessage());
        }
    }

    public Cidade updateById(Long id, Cidade cidadeFromController) {
        try {
            //Pegamos a cidade do banco de dados
            Cidade cidadeFromDatabase = cidadeRepository.getOne(id);
            //Atualizamos a cidade já existente no banco de dados com as novas informações
            updateData(cidadeFromController, cidadeFromDatabase);
            //Em seguida salva e retorna a cidade atualizada
            return cidadeRepository.save(cidadeFromDatabase);
        }
        //Quando não encontrar Cidade com o Id.
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Cidade cidadeFromController, Cidade cidadeFromDatabase) {
        //TENHO QUE VERIFICAR CASO TENTE ATUALIZAR A CIDADE COM UM ESTADO QUE NÃO EXISTE
        cidadeFromDatabase.setNome(cidadeFromController.getNome());
        cidadeFromDatabase.setPopulacao(cidadeFromController.getPopulacao());
        cidadeFromDatabase.setPrefeito(cidadeFromController.getPrefeito());
    }
}