package persistence;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Setor;

public class SetorDAO {

    public void create(Setor setor) {
        String insertSQL = "INSERT INTO setor(NomeSetor) VALUES (?)";
        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            conn = ConnectionUtil.createConnectionToMySQL();
            pstm = conn.prepareStatement(insertSQL); // Passsando query de insert
            
            pstm.setString(1, setor.getNome());
            pstm.execute();
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

    public List<Setor> read() {
        String selectSQL = "SELECT * FROM setor";
        Connection conn = null;
        PreparedStatement pstm = null;
        List<Setor> setores = new ArrayList<Setor>();
        ResultSet rset = null;
        try {
            conn = ConnectionUtil.createConnectionToMySQL();
            pstm = conn.prepareStatement(selectSQL);
            pstm.execute();
            rset = pstm.executeQuery();// Executa a query e guarda o valor
            while (rset.next()) { // Esse loop pega as informações do banco separa e adiciona na instancia de
                                    // setor que vai jogar para a listagem

                Setor setor = new Setor();
                setor.setIdSetor(rset.getInt("idSetor"));
                setor.setNome(rset.getString("NomeSetor"));
                setores.add(setor);
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

        return setores;
    }

    public void update(int id, Setor setor) {
        String updateSQL = "UPDATE setor SET NomeSetor = ? WHERE idSetor = " + id;
        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            conn = ConnectionUtil.createConnectionToMySQL();
            pstm = conn.prepareStatement(updateSQL);

            pstm.setString(1, setor.getNome());
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

    public List<Setor> findSetor(int id) {
        String findSQL = "SELECT * FROM setor WHERE idSetor = " + id;
        Connection conn = null;
        PreparedStatement pstm = null;
        List<Setor> setores = new ArrayList<Setor>();
        ResultSet rset = null;
        try {
            conn = ConnectionUtil.createConnectionToMySQL();
            pstm = conn.prepareStatement(findSQL);
            rset = pstm.executeQuery();

            Setor setor = new Setor();
            if (rset.next()) {
                setor.setIdSetor(rset.getInt("idSetor"));
                setor.setNome(rset.getString("NomeSetor"));
                setores.add(setor);
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

        return setores;
    }

    public void delete(int id) {
        String deleteSQL = "DELETE FROM setor WHERE idSetor = ?;";
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