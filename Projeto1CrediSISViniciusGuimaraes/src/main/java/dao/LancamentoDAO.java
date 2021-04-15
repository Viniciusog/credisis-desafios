package dao;

import connection.CrediSISMySQLConnection;
import entities.ContaCorrente;
import entities.Lancamento;
import enuns.TipoLancamento;
import enuns.TipoSistema;
import exceptions.MySQLConnectionException;
import exceptions.SaldoInsuficienteException;
import utils.FormatDate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class LancamentoDAO {

    //Adiciona um lançamento por ID
    public static void insert(Lancamento lancamento) {
        Connection con = CrediSISMySQLConnection.getConnection();
        String sql = "INSERT INTO lancamento (id_conta, conta, debito, credito, tipo_lancamento, " +
                "descricao, criado_em, sistema) VALUES (?, ?,?,?,?,?,?,?)";

        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setLong(1, lancamento.getContaCorrente().getId());
            stm.setString(2, lancamento.getContaCorrente().getCodigo());
            stm.setDouble(3, lancamento.getValorDebito());
            stm.setDouble(4, lancamento.getValorCredito());
            stm.setString(5, lancamento.getTipoLancamento().name());
            stm.setString(6, lancamento.getDescricao());
            stm.setString(7, lancamento.getCriadoEm().toString());
            stm.setString(8, lancamento.getSistema().name());
            stm.executeUpdate();
            stm.close();
        } catch (Exception e) {
            System.out.println("Erro ao inserir lancamento: " + e.getMessage());
        }
    }

    //Retorna todos os lançamentos por código de conta
    public static List<Lancamento> getLancamentosByCodigoConta(String contaCodigo)  {
        Connection con = CrediSISMySQLConnection.getConnection();
        String sql = "SELECT * FROM lancamento WHERE conta = ?;";
        List<Lancamento> lancamentoList = new ArrayList<>();
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, contaCodigo);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Long idLancamento = rs.getLong("id");
                String codigoConta = rs.getString("conta");
                Double valorDebito = rs.getDouble("debito");
                Double valorCredito = rs.getDouble("credito");
                TipoLancamento tipoLancamento = TipoLancamento.valueOf(rs.getString("tipo_lancamento"));
                String descricao = rs.getString("descricao");
                String instante = rs.getString("criado_em");
                TipoSistema tipoSistema = TipoSistema.valueOf(rs.getString("sistema"));

                Lancamento l = new Lancamento();
                l.setId(idLancamento);
                l.setTipoLancamento(tipoLancamento);
                l.setDescricao(descricao);
                l.setSistema(tipoSistema);
                l.setCriadoEm(instante);
                l.setValorCredito(valorCredito);
                l.setValorDebito(valorDebito);

                ContaCorrente cc = new ContaCorrente();
                cc.setCodigo(codigoConta);
                l.setContaCorrente(cc);

                lancamentoList.add(l);
            }

        } catch (Exception e) {
            System.out.println("Erro ao pegar lançamentos por código de Conta Corrente: " + e.getMessage());
        }
        return lancamentoList;
    }

    //Retorna todos os lançamentos por código de conta e data.
    public static List<Lancamento> getLancamentosByCodigoContaEData(String conta, Instant dataInstante) {
        Connection con = CrediSISMySQLConnection.getConnection();
        String sql = "SELECT * FROM lancamento WHERE conta = ? AND DATE(criado_em) <= '?'";
        List<Lancamento> lancamentoList = new ArrayList<>();
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, conta);
            stm.setDate(2, FormatDate.formatDateTZ(dataInstante));
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Long idLancamento = rs.getLong("id");
                String codigoConta = rs.getString("conta");
                Double valorDebito = rs.getDouble("debito");
                Double valorCredito = rs.getDouble("credito");
                TipoLancamento tipoLancamento = TipoLancamento.valueOf(rs.getString("tipo_lancamento"));
                String descricao = rs.getString("descricao");
                Instant instant = Instant.from(rs.getDate("criado_em").toInstant());
                TipoSistema tipoSistema = TipoSistema.valueOf(rs.getString("sistema"));

                Lancamento l = new Lancamento();
                l.setId(idLancamento);
                l.setTipoLancamento(tipoLancamento);
                l.setDescricao(descricao);
                l.setSistema(tipoSistema);
                l.setCriadoEm(instant.toString());
                l.setValorCredito(valorCredito);
                l.setValorDebito(valorDebito);

                ContaCorrente cc = new ContaCorrente();
                cc.setCodigo(codigoConta);
                l.setContaCorrente(cc);

                lancamentoList.add(l);
            }
        } catch (Exception e) {
            System.out.println("Erro ao pegar lançamentos por código de Conta Corrente e data: " + e.getMessage());
        }
        return lancamentoList;
    }

    public static Double calcularSaldoCooperado(String codigoConta, String dataTZ) {
        List<Lancamento> lancamentosCooperado = getLancamentosByCodigoConta(codigoConta);
        double saldoCooperado = 0.0;
        for (Lancamento l : lancamentosCooperado) {
            if (Instant.parse(l.getCriadoEm()).isBefore(Instant.parse(dataTZ))) {
                if (l.getTipoLancamento().equals(TipoLancamento.CREDITO)) {
                     saldoCooperado += l.getValorCredito();
                } else {
                    saldoCooperado-= l.getValorDebito();
                }
            }
        }
        return saldoCooperado;
    }
}