package com.viniciusog.unidadesfederativas.repositores;

import com.viniciusog.unidadesfederativas.entities.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {
}
