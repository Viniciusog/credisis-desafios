package entities;


import enuns.TipoLancamento;
import enuns.TipoSistema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
public class Lancamento {
    private Long id;
    private ContaCorrente contaCorrente;
    private Double valorDebito;
    private Double valorCredito;
    private TipoLancamento tipoLancamento;
    private String descricao;
    //Formatar a data
    private String criadoEm;
    private TipoSistema sistema;

    public Lancamento(ContaCorrente contaCorrente, Double valorDebito, Double valorCredito,
                      TipoLancamento tipoLancamento, String descricao, String criadoEm, TipoSistema sistema) {
        this.contaCorrente = contaCorrente;
        this.valorDebito = valorDebito;
        this.valorCredito = valorCredito;
        this.tipoLancamento = tipoLancamento;
        this.descricao = descricao;
        this.criadoEm = criadoEm;
        this.sistema = sistema;
    }
}