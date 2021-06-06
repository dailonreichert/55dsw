package controller.beans;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import modelo.DAO.DaoLogin;
import modelo.DAO.DaoUsuario;
import modelo.entidades.Login;
import modelo.entidades.Usuario;
import utils.SessionUtil;

@ManagedBean
public class BeanLogin {

    private String username;
    private String senha;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void logar(){
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage msg = null;

        if (username.isEmpty()) {
            msg = new FacesMessage("Informe o User");
            context.addMessage(null, msg);
        }

        if (senha.isEmpty()) {
            msg = new FacesMessage("Informe a Senha");
            context.addMessage(null, msg);
        }

        if (msg == null) {
            Login login = new Login(this.username, this.senha);
            if (DaoLogin.login(login)) {
                try {
                    msg = new FacesMessage("Logado com sucesso");
                    int id = DaoUsuario.getIdByUsername(username);
                    Usuario usuario = new Usuario();
                    usuario.setId(id);

                    SessionUtil.setParam("usuarioLogado", usuario);

                    context.getExternalContext().redirect("landing.jsf");
                } catch (IOException ex) {
                    Logger.getLogger(BeanLogin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{
                msg = new FacesMessage("Erro ao Logar. A senha ou o username está incorreto");
                context.addMessage(null, msg);
            }
        }
    }
    
}
