package br.mack.ps2.dao;

import br.mack.ps2.api.Economia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EconomiaDAOMySQL implements EconomiaDao {
    private String createSQL = "INSERT INTO economia (date, value) VALUES (?,?)";
    private String readSQL = "SELECT * FROM economia";
    private String updateSQL= "UPDATE economia SET date = ?, value = ? WHERE id = ?";
    private String deleteSQL = "DELETE FROM economia WHERE id = ?";

    private final MySQLConnection mysql = new MySQLConnection();


    @Override
    public boolean create(Economia economia) {
        Connection conexao = mysql.getConnection();
        try{
            PreparedStatement stm = conexao.prepareStatement(createSQL);
            stm.setDate(1, new java.sql.Date(economia.getDate().getTime()));
            stm.setInt(2, economia.getValue());
            int registro =stm.executeUpdate();
            return(registro>0);
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        finally {
            try{
                conexao.close();
            } catch (SQLException throwables){
                throwables.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public List<Economia> read() {
        Connection conexao = mysql.getConnection();

        List<Economia> economias= new ArrayList();
        try {
            PreparedStatement stm = conexao.prepareStatement(readSQL);
            ResultSet registro = stm.executeQuery();
            while (registro.next()) {
                Economia economia = new Economia();
                economia.setId(registro.getInt("id"));
                economia.setDate(registro.getDate("date"));
                economia.setValue(registro.getInt("value"));

                economias.add(economia);
            }
            return economias;

        } catch (final SQLException throwables) {
            throwables.printStackTrace();
        } catch (final Exception ex){
            ex.printStackTrace();
        }
        finally {
            try {
                conexao.close();
            } catch (final SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return economias;

    }

    @Override
    public boolean update(Economia economia) {
        Connection conexao = mysql.getConnection();
        int registros = -1;
        try {
            PreparedStatement stm = conexao.prepareStatement(updateSQL);

            stm.setDate(1,  new java.sql.Date(economia.getDate().getTime()));
            stm.setInt(2, economia.getValue());
            stm.setInt(3, economia.getId());

            registros = stm.executeUpdate();



        } catch (final SQLException e){
            System.out.println("Falha de conexão com a base de dados!");
            e.printStackTrace();
        } finally {
            try{
                conexao.close();
            } catch (final Exception e){
                e.printStackTrace();
            }
        }
        return registros > 0;


    }

    @Override
    public boolean delete(Economia economia) {
        Connection conexao = mysql.getConnection();
        try{
            PreparedStatement stm = conexao.prepareStatement(deleteSQL);
            stm.setInt(1, economia.getId());
            int registros = stm.executeUpdate();
            return registros > 0;

        } catch (SQLException e){
            System.out.println("Falha de conexão com a base de dados!");
            e.printStackTrace();
        } catch(final Exception e){
            e.printStackTrace();
        } finally {
            try{
                conexao.close();
            } catch (final Exception e){
                e.printStackTrace();
            }
        }

        return false;


    }
}
