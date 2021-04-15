package com.viniciusog.unidadesfederativas.controller;

import com.viniciusog.unidadesfederativas.entities.Cidade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.viniciusog.unidadesfederativas.services.CidadeService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/cidades")
@RequiredArgsConstructor
public class CidadeController {

    private final CidadeService cidadeService;

    @PostMapping
    public ResponseEntity<Cidade> insert(@RequestBody Cidade cidade) {
        Cidade createdCidade = cidadeService.insert(cidade);

        //Pegamos a URI atual e colocamos o id da cidade criada, para saber aonde a mesma se encontra.
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(createdCidade.getId()).toUri();

        return ResponseEntity.created(uri).body(createdCidade);
    }

    @GetMapping
    public ResponseEntity<List<Cidade>> findAll() {
        return ResponseEntity.ok().body(cidadeService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        Cidade cidade = cidadeService.findById(id);
        return ResponseEntity.ok().body(cidade);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        cidadeService.deleteById(id);
        //Estamos retornando no content para dizer que a cidade foi deleta com sucesso
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Cidade> updateById(@PathVariable Long id, @RequestBody Cidade cidade) {
        Cidade updatedCidade = cidadeService.updateById(id, cidade);
        return ResponseEntity.ok().body(updatedCidade);
    }
}