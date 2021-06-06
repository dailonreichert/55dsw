package modelo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import modelo.entidades.Login;
import modelo.entidades.Usuario;
import org.jboss.weld.module.web.HttpSessionBean;
import utils.Conexao;

public class DaoLogin {

  public static boolean login(Login login){
      try {
          Connection conexao = Conexao.conectar();
          String sql = "select * from usuario where username = ? and senha = ?";
          PreparedStatement stm = conexao.prepareStatement(sql);
          stm.setString(1, login.getUsername());
          stm.setString(2, login.getSenha());
          ResultSet resposta = stm.executeQuery();
          
          while (resposta.next()) {
              Usuario user = new Usuario();
              user.setId(resposta.getInt("idusuario"));
              user.setNome(resposta.getString("nome"));
              user.setUsername(resposta.getString("username"));
              
              /*FacesContext context = FacesContext.getCurrentInstance();
                HttpSessionBean session = (HttpSession) context.getExternalContext().getSession(true);

              session.setAttribute("usuario", user);*/

              return true;
          }

          return false;

      } catch (SQLException ex) {
          throw new RuntimeException("Erro ao fazer login: " + ex.getMessage());
      }
  }
}
