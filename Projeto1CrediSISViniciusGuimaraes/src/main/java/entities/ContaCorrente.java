package entities;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ContaCorrente {

    private Long id;
    private String codigo;
    private String nomeTitular;
    private Double saldo;
    List<Lancamento> operacoes = new ArrayList<>();

    public ContaCorrente(String codigo, String nomeTitular, Double saldo) {
        this.codigo = codigo;
        this.nomeTitular = nomeTitular;
        this.saldo = saldo;
    }
}