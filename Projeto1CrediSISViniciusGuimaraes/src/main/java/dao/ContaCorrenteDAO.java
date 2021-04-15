package dao;

import connection.CrediSISMySQLConnection;
import entities.ContaCorrente;
import exceptions.MySQLConnectionException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ContaCorrenteDAO {

    //Cadastra uma conta corrente.
    public static void insert(ContaCorrente contaCorrente) {
        Connection con = CrediSISMySQLConnection.getConnection();
        String sql = "INSERT INTO conta_corrente (codigo, nome_titular, saldo) VALUES (?,?,?);";

        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, contaCorrente.getCodigo());
            stm.setString(2, contaCorrente.getNomeTitular());
            stm.setDouble(3, contaCorrente.getSaldo());
            stm.executeUpdate();

        } catch (Exception e) {
        }
    }

    //Remove uma conta corrente
    public static void remove(Long id) {
        Connection con = CrediSISMySQLConnection.getConnection();
        String sql = "DELETE FROM conta_corrente WHERE codigo = ?";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setLong(1, id);
            stm.executeUpdate();
            stm.close();

        } catch (Exception e) {
            System.out.println("Erro ao remover Conta Corrente: " + e.getMessage());
        }
    }

    //Atualiza uma conta corrente
    public static void update(ContaCorrente contaCorrente) {
        Connection con = CrediSISMySQLConnection.getConnection();
        String sql = "UPDATE conta_corrente SET  nome_titular = ?, saldo = ? WHERE codigo = ?";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, contaCorrente.getNomeTitular());
            stm.setDouble(2, contaCorrente.getSaldo());
            stm.setString(3, contaCorrente.getCodigo());
            stm.executeUpdate();
            stm.close();
        } catch (Exception e) {
            System.out.println("Erro ao remover Conta Corrente: " + e.getMessage());
        }
    }

    //Retorna conta corrente por id
    public static ContaCorrente getContaCorrenteByCodigo(String codigo) {
        Connection con = CrediSISMySQLConnection.getConnection();
        String sql = "SELECT * FROM conta_corrente where codigo = ?";
        ContaCorrente cc = new ContaCorrente();

        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, codigo);
            ResultSet result = stm.executeQuery();
            while (result.next()) {
                cc.setId(result.getLong("id"));
                cc.setCodigo(result.getString("codigo"));
                cc.setNomeTitular(result.getString("nome_titular"));
                cc.setSaldo(result.getDouble("saldo"));
            }
        } catch (Exception e) {
            System.out.println("Erro ao pegar Conta Corrente por código: " + e.getMessage());
        }
        return cc;
    }

    //Retorna todas as contas corrente do banco de dados
    public static List<ContaCorrente> getListContaCorrente()  {
        Connection con = CrediSISMySQLConnection.getConnection();
        //Lembrando que nunca é uma boa ideia retornar todos os conteúdos de uma tabela.
        String sql = "SELECT * FROM conta_corrente;";
        List<ContaCorrente> contaCorrenteList = new ArrayList<>();
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            ResultSet resultSet = stm.executeQuery();
            while (resultSet.next()) {
                ContaCorrente cc = new ContaCorrente();
                cc.setId(resultSet.getLong("id"));
                cc.setCodigo(resultSet.getString("codigo"));
                cc.setNomeTitular(resultSet.getString("nome_titular"));
                cc.setSaldo(resultSet.getDouble("saldo"));
                contaCorrenteList.add(cc);
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar todas as Contas Correntes: " + e.getMessage());
        }
        return contaCorrenteList;
    }
}