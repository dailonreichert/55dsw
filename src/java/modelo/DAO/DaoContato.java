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
import utils.SessionUtil;

public class DaoContato {
    
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
  
  
  public static boolean salvar(Contato ct){
      try {
          Connection conexao = Conexao.conectar();
          String sql = "insert into contato(nome,fone,email,idusuario)"+
                  "values(?,?,?,?)";
          PreparedStatement stm = conexao.prepareStatement(sql);
          stm.setString(1, ct.getNome());
          stm.setString(2, ct.getFone());
          stm.setString(3, ct.getEmail());
          stm.setInt(4, ct.getIdusuario());
          stm.execute();
      } catch (SQLException ex) {
          throw new RuntimeException("Erro ao salvar contato: " + ex.getMessage());
      }
      return true;
  }  
  
    public static boolean alterar(Contato ct){
      try {
          Connection conexao = Conexao.conectar();
          String sql = "update contato set nome = ?, fone = ?, email= ? "
                      + "where idusuario = ? and idcontato = ?";
          PreparedStatement stm = conexao.prepareStatement(sql);
          stm.setString(1, ct.getNome());
          stm.setString(2, ct.getFone());
          stm.setString(3, ct.getEmail());
          stm.setInt(4, ct.getIdusuario());
          stm.setInt(5, ct.getIdcontato());
          stm.execute();
      } catch (SQLException ex) {
          throw new RuntimeException("Erro ao salvar contato: " + ex.getMessage());
      }
      return true;
  }
  
  public static ResultSet getAll(String filtro){
      ResultSet rs = null;
      try {          
          Connection conexao = Conexao.conectar();

          Usuario usuario = (Usuario) SessionUtil.getParam("usuarioLogado");

          String sql = "select * from contato"+
                        " where nome like '%"+filtro+"%' and idusuario = ?";
          PreparedStatement stm = conexao.prepareStatement(sql);
          stm.setInt(1, usuario.getId());
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
}
