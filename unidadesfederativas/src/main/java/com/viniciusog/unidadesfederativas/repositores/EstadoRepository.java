package com.viniciusog.unidadesfederativas.repositores;

import com.viniciusog.unidadesfederativas.entities.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {
}
