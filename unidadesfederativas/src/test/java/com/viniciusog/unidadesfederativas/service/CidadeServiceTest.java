package com.viniciusog.unidadesfederativas.service;

import com.viniciusog.unidadesfederativas.entities.Cidade;
import com.viniciusog.unidadesfederativas.repositores.CidadeRepository;
import com.viniciusog.unidadesfederativas.services.CidadeService;
import com.viniciusog.unidadesfederativas.util.CidadeBuilder;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.text.html.Option;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CidadeServiceTest {

    @Mock
    private CidadeRepository cidadeRepository;

    @InjectMocks
    private CidadeService cidadeService;

    @Test
    public void whenCityInformedThenItShouldBeCreated() {
        //TESTES NÃO FUNCIONANDO, NÃO TIVE TEMPO SUFICIENTE PARA REALIZAR TODOS OS PASSOS.
        CidadeBuilder cb = new CidadeBuilder();

        Cidade expectedCity = new Cidade();
        expectedCity.setId(cb.getId());
        expectedCity.setNome(cb.getNome());
        expectedCity.setPrefeito(cb.getPrefeito());
        expectedCity.setPopulacao(cb.getPopulacao());

        //mock
        //Quando for salvar uma cidade, retorna a cidade esperada
        Mockito.when(cidadeRepository.save(expectedCity)).thenReturn(expectedCity);

        //then
        //Passa os dados da cidade criada.
        Cidade createdCity = cidadeService.insert(expectedCity);

        //Validando os dados de cidade
        assertThat(createdCity.getId(), is(equalTo(expectedCity.getId())));
        assertThat(createdCity.getNome(), is(equalTo(expectedCity.getNome())));
        assertThat(createdCity.getPopulacao(), is(equalTo(expectedCity.getPopulacao())));
        assertThat(createdCity.getPrefeito(), is(equalTo(expectedCity.getPrefeito())));
    }
}
