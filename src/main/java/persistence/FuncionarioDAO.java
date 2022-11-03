package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Funcionario;

public class FuncionarioDAO {

    public void create(Funcionario funcionarios) {

        String insertSQL = "INSERT INTO funcionario(Nome,dt_aniversario,foto,Fk_IdSetor) VALUES(?,?,?,?);";
        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            conn = ConnectionUtil.createConnectionToMySQL();
            pstm = conn.prepareStatement(insertSQL); // Passsando query de insert
            
            //pstm.setInt(1, funcionarios.getId());     
            pstm.setString(1, funcionarios.getNome());
            pstm.setDate(2, funcionarios.getDtAniversario());
            pstm.setString(3, funcionarios.getFoto());
            pstm.setInt(4, funcionarios.getIdSetor());
            pstm.execute();
            pstm.close();
        } catch (Exception err) {
            err.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {

                e.printStackTrace();
            }
        }
    }

    public List<Funcionario> read() {
        String selectSQL = "select f.idfuncionarios,f.nome,f.dt_aniversario,f.foto, f.Fk_IdSetor , s.idSetor, s.NomeSetor  from funcionario f INNER join  setor s where  f.Fk_IdSetor  = s.idSetor;";
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;
        List<Funcionario> funcsList = new ArrayList<Funcionario>();
        try {
            conn = ConnectionUtil.createConnectionToMySQL();
            pstm = conn.prepareStatement(selectSQL);
            pstm.execute();
            rset = pstm.executeQuery(); // Executa a query e guarda o valor na variavel rset

            while (rset.next()) { // Esse loop pega as informações do banco separa e adiciona na instancia de
                                    // setor que vai jogar para a listagem

                Funcionario funcionarios = new Funcionario();
                funcionarios.setId(rset.getInt("idfuncionarios"));
                funcionarios.setNome(rset.getString("Nome"));
                funcionarios.setDtAniversario(rset.getDate("dt_aniversario"));
                funcionarios.setFoto(rset.getString("foto"));
                funcionarios.setIdSetor(rset.getInt("idSetor"));
                funcionarios.setSetor(rset.getString("NomeSetor"));
                funcsList.add(funcionarios);
            }
        } catch (Exception err) {
            err.printStackTrace();

        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return funcsList;
    }

    public void update(int id,Funcionario funcionarios) {
        String updateSQL = "UPDATE funcionario SET Nome = ?, dt_aniversario = ?, foto = ?, Fk_IdSetor = ? "
                + "WHERE idfuncionarios = " + id;
        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            conn = ConnectionUtil.createConnectionToMySQL();
            pstm = conn.prepareStatement(updateSQL);

            pstm.setString(1, funcionarios.getNome());
            pstm.setDate(2, funcionarios.getDtAniversario());
            pstm.setString(3, funcionarios.getFoto());
            pstm.setInt(4, funcionarios.getIdSetor());
            pstm.execute();
            pstm.close();
        } catch (Exception err) {
            err.printStackTrace();

        } finally {
            try {
                conn.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Funcionario> find(int id) {
        String findSQL = "select f.idfuncionarios,f.nome,f.dt_aniversario,f.foto, s.idSetor, s.NomeSetor as setor from funcionario f  \r\n"
                + "    INNER JOIN\r\n"
                + "    setor s \r\n"
                + "    ON f.Fk_IdSetor  = s.idSetor\r\n"
                + "    where idfuncionarios = " + id;
        List<Funcionario> funcionarioFound = new ArrayList<Funcionario>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset;
        try {
            conn = ConnectionUtil.createConnectionToMySQL();
            pstm = conn.prepareStatement(findSQL);
            rset = pstm.executeQuery();

            Funcionario funcionarios = new Funcionario();
            if (rset.next()) {
                funcionarios.setId(rset.getInt("idfuncionarios"));
                funcionarios.setNome(rset.getString("Nome"));
                funcionarios.setDtAniversario(rset.getDate("dt_aniversario"));
                funcionarios.setFoto(rset.getString("foto"));
                funcionarios.setIdSetor(rset.getInt("idSetor"));
                funcionarios.setSetor(rset.getString("setor"));
                funcionarioFound.add(funcionarios);

                pstm.execute();
                pstm.close();
            }
        } catch (Exception err) {
            err.printStackTrace();

        } finally {
            try {
                conn.close();
            } catch (SQLException e) {

                e.printStackTrace();
            }
        }
        return funcionarioFound;

    }

    public void delete(int id) {
        String deleteSQL = "DELETE FROM funcionario WHERE idfuncionarios = ?;";
        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            conn = ConnectionUtil.createConnectionToMySQL();
            pstm = conn.prepareStatement(deleteSQL);
            pstm.setInt(1, id);
            pstm.execute();
            pstm.close();
        } catch (Exception err) {
            err.printStackTrace();

        } finally {
            try {
                conn.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}