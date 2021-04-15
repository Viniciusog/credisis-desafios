package com.viniciusog.unidadesfederativas.controller;

import com.viniciusog.unidadesfederativas.entities.Estado;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.viniciusog.unidadesfederativas.services.EstadoService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/estados")
@RequiredArgsConstructor
public class EstadoController {

    private final EstadoService estadoService;

    @GetMapping
    public ResponseEntity<List<Estado>> findAll() {
        return ResponseEntity.ok().body(estadoService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Estado> findById(@PathVariable Long id) {
        Estado estado  = estadoService.findById(id);
        return ResponseEntity.ok().body(estado);
    }

    @PostMapping
    public ResponseEntity<Estado> insert(@RequestBody Estado estado) {
        Estado estadoCreated = estadoService.insert(estado);

        //Estamos criando a URI, que ficará: GET /estados/{id}, aonde o {id} será do Estado criado
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(estado.getId()).toUri();
        return ResponseEntity.created(uri).body(estadoCreated);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        estadoService.deleteById(id);
        //Iremos apenas retornar vazio, dizendo que foi deletado com sucesso
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Estado> updateById(@PathVariable Long id, @RequestBody Estado estado) {
        Estado updatedEstado = estadoService.updateById(id, estado);
        return ResponseEntity.ok().body(updatedEstado);
    }
}