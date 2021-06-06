package modelo.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.entidades.Compromisso;
import utils.Conexao;

public class DaoCompromisso {

  public static boolean salvar(Compromisso compromisso){
      try {
          Connection conexao = Conexao.conectar();
          String sql = "insert into compromisso(descricao, local, data, idusuario, idcontato)"+
                  "values(?, ?, ?, ?, ?)";
          PreparedStatement stm = conexao.prepareStatement(sql);
          stm.setString(1, compromisso.getDescricao());
          stm.setString(2, compromisso.getLocal());
          stm.setDate(3, new Date(compromisso.getData().getTime()));
          stm.setInt(4,    compromisso.getIdusuario());
          stm.setInt(5,    compromisso.getIdcontato());
          stm.execute();
      } catch (SQLException ex) {
          throw new RuntimeException("Erro ao salvar contato: " + ex.getMessage());
      }
      return true;
  }  
  
  public static ResultSet getAll(int idusuario, int idcontato){
      ResultSet rs = null;
      try {
          Connection conexao = Conexao.conectar();
          String sql = "select * from compromisso where idusuario = ? and idcontato = ?";
          PreparedStatement stm = conexao.prepareStatement(sql);
          stm.setInt(1, idusuario);
          stm.setInt(2, idcontato);
          rs = stm.executeQuery();
      } catch (SQLException ex) {
          throw new RuntimeException("Erro de consulta: " + ex.getMessage());
      }

      return rs;
  }
}
