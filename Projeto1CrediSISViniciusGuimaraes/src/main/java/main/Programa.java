package main;

import dao.ContaCorrenteDAO;
import dao.LancamentoDAO;
import entities.ContaCorrente;
import entities.Lancamento;
import enuns.TipoLancamento;
import enuns.TipoSistema;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.Instant;
import java.util.List;

public class Programa {
    /*
        OBS: O Desafio poderia ser melhor explicado, principalmente
        na parte do Json. Pelo que eu entendi, seria realizado um lançamento (Crédito ou débito)
        para as contas.
        Fiz pensando na seguinte premissa:
        - Como Anderson está debitando 150 de sua conta, tem que creditar 150 na conta de Felipe
     */

    private final static String JSON_DATA = "" +
            "{\n" +
            "  \"conta\": \"0000120-5\",\n" +
            "  \"debito\": 150.00,\n" +
            "  \"credito\": 0.0,\n" +
            "  \"tipo_lancamento\": \"DEBITO\",\n" +
            "  \"descricao\": \"Transferência para Felipe\",\n" +
            "  \"criado_em\": \"2021-03-22T20:48:23Z\",\n" +
            "  \"sistema\": \"MOBILE\"\n" +
            "}";

    private static final String DATA_PARA_FILTRAR = "2021-04-15T20:48:23Z";

    public static void main(String[] args) throws JSONException {

        //Pega o nosso Json e separa cada atributo
        final JSONObject obj = new JSONObject(JSON_DATA);
        String conta = obj.getString("conta");
        Double debito = Double.parseDouble(obj.getString("debito"));
        Double credito = Double.parseDouble(obj.getString("credito"));
        String descricao = obj.getString("descricao");
        TipoLancamento tl = TipoLancamento.valueOf(obj.getString("tipo_lancamento"));
        String criadoEm = obj.getString("criado_em");
        TipoSistema ts = TipoSistema.valueOf(obj.getString("sistema"));

        /*--------------------------------------------------------------------------------*/
        ContaCorrente contaAnderson = ContaCorrenteDAO.getContaCorrenteByCodigo(conta);
        ContaCorrente contaFelipe = ContaCorrenteDAO.getContaCorrenteByCodigo("ABC123");

        //Saldo inicial de Anderson 800
        Lancamento l1 = new Lancamento();
        l1.setCriadoEm(Instant.now().toString());
        l1.setSistema(TipoSistema.MOBILE);
        l1.setContaCorrente(contaAnderson);
        l1.setValorCredito(800.0);
        l1.setValorDebito(0.0);
        l1.setTipoLancamento(TipoLancamento.CREDITO);
        l1.setDescricao("Saldo inicial Anderson");

        contaAnderson.setSaldo(800.0);
        ContaCorrenteDAO.update(contaAnderson);

        LancamentoDAO.insert(l1);

        //Débito de 150 conta Anderson
        Lancamento l2 = new Lancamento();
        l2.setCriadoEm(criadoEm);
        l2.setSistema(ts);
        l2.setContaCorrente(contaAnderson);
        l2.setValorCredito(credito);
        l2.setValorDebito(debito);
        l2.setTipoLancamento(tl);
        l2.setDescricao(descricao);

        LancamentoDAO.insert(l2);
        contaAnderson.setSaldo(contaAnderson.getSaldo() - 150.0);
        ContaCorrenteDAO.update(contaAnderson);

        //Crédito de 150 conta Anderson
        Lancamento l3 = new Lancamento();
        l3.setCriadoEm(criadoEm);
        l3.setSistema(TipoSistema.MOBILE);
        l3.setContaCorrente(contaFelipe);
        l3.setValorCredito(150.0);
        l3.setValorDebito(0.0);
        l3.setTipoLancamento(TipoLancamento.CREDITO);
        l3.setDescricao(descricao);

        contaFelipe.setSaldo(150.0);
        ContaCorrenteDAO.update(contaFelipe);

        LancamentoDAO.insert(l3);

        //Calcula o saldo do cooperado anterior a uma data e mostra o resultado no console.
        Double saldoAnderson = LancamentoDAO.calcularSaldoCooperado(contaAnderson.getCodigo(), DATA_PARA_FILTRAR);
        Double saldoFelipe = LancamentoDAO.calcularSaldoCooperado(contaFelipe.getCodigo(), DATA_PARA_FILTRAR);
        System.out.println("| ---- DADOS DE CONTA CORRENTE ---- |");
        System.out.println("| SALDO ANDERSON: " + saldoAnderson);
        System.out.println("| SALDO FELIPE: " + saldoFelipe);
    }
}
