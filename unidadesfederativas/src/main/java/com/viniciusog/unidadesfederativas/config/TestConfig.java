package com.viniciusog.unidadesfederativas.config;

import com.viniciusog.unidadesfederativas.entities.Cidade;
import com.viniciusog.unidadesfederativas.entities.Estado;
import com.viniciusog.unidadesfederativas.repositores.CidadeRepository;
import com.viniciusog.unidadesfederativas.repositores.EstadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Profile("test")
@RequiredArgsConstructor
public class TestConfig implements CommandLineRunner {

    private final EstadoRepository estadoRepository;
    private final CidadeRepository cidadeRepository;



    @Override
    public void run(String... args) throws Exception {

        Estado e1 = new Estado("Rondônia", "RO");
        Estado e2 = new Estado("Rio de Janeiro", "RJ");
        Estado e3 = new Estado("São Paulo", "SP");
        Estado e4 = new Estado("Espírito Santo", "ES");
        Estado e5 = new Estado("Paraná", "PR");

        List<Estado> estados = new ArrayList<>();
        estados.add(e1);
        estados.add(e2);
        estados.add(e3);
        estados.add(e4);
        estados.add(e5);

        estadoRepository.saveAll(estados);

        Cidade c1 = new Cidade("Ji-Paraná", "Prefeito de Ji-Paraná", 120000, e1);
        Cidade c2 = new Cidade("Jaru", "Prefeito de Jaru", 75000, e1);
        Cidade c3 = new Cidade("Niterói", "Prefeito de Niterói", 800000, e2);
        Cidade c4 = new Cidade("São Carlos", "Prefeito de São Carlos", 250000, e3);
        Cidade c5 = new Cidade("Vitória", "Prefeito de Vitória", 1200000, e4);
        Cidade c6 = new Cidade("Curitiba", "Prefeito de Curitiba", 750000, e5);
        Cidade c7 = new Cidade("Ponta Grossa", "Prefeito de Ponta Grossa", 430000, e5);

        List<Cidade> cidades = new ArrayList<>();
        cidades.add(c1);
        cidades.add(c2);
        cidades.add(c3);
        cidades.add(c4);
        cidades.add(c5);
        cidades.add(c6);
        cidades.add(c7);

        cidadeRepository.saveAll(cidades);

    }
}
