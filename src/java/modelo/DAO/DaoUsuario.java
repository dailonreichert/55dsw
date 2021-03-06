package modelo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.entidades.Contato;
import modelo.entidades.Usuario;
import utils.Conexao;

public class DaoUsuario {
    
  public static boolean excluir(int id){
      try {
          Connection conexao = Conexao.conectar();
          String sql = "delete from contato "+
                       " where idcontato = ? ";
          PreparedStatement stm = conexao.prepareStatement(sql);
          stm.setInt(1, id);
          stm.execute();
      } catch (SQLException ex) {
          throw new RuntimeException("Erro ao excluir contato: " + ex.getMessage());
      }
      return true;
  }  
  
  
  public static boolean salvar(Usuario usuario){
      try {
          Connection conexao = Conexao.conectar();
          String sql = "insert into usuario(nome, username, senha) values (?, ?, ?)";
          PreparedStatement stm = conexao.prepareStatement(sql);
          stm.setString(1, usuario.getNome());
          stm.setString(2, usuario.getUsername());
          stm.setString(3, usuario.getSenha());
          stm.execute();
      } catch (SQLException ex) {
          throw new RuntimeException("Erro ao salvar usuario: " + ex.getMessage());
      }

      return true;
  }  
  
  public static ResultSet getAll(String filtro){
      ResultSet rs = null;
      try {          
          Connection conexao = Conexao.conectar();
          String sql = "select * from contato"+
                        " where nome like '%"+filtro+"%'";
          PreparedStatement stm = conexao.prepareStatement(sql);
          rs = stm.executeQuery();
      } catch (SQLException ex) {
          throw new RuntimeException("Erro de consulta: " + ex.getMessage());
      }
      return rs;
  }
  
  public static ResultSet getById(int id){
      ResultSet rs = null;
      try {          
          Connection conexao = Conexao.conectar();
          String sql = "select * from contato"+
                        " where idcontato = ? ";
          PreparedStatement stm = conexao.prepareStatement(sql);
          stm.setInt(1, id);
          rs = stm.executeQuery();
      } catch (SQLException ex) {
          throw new RuntimeException("Erro de consulta: " + ex.getMessage());
      }
      return rs;
  }
  
  public static int getIdByUsername(String username) {
      ResultSet rs = null;

      try {
          Connection conexao = Conexao.conectar();
          String sql = "select * from usuario where username = ?";
            PreparedStatement stm = conexao.prepareStatement(sql);
            stm.setString(1, username);
            rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("idusuario");
            }

            return 0;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro de SQL: " + ex.getMessage());
        }
  }
}
