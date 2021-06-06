package controller.beans;

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
import modelo.DAO.DaoUsuario;
import modelo.entidades.Usuario;

@ManagedBean
@ViewScoped
public class BeanUsuario {
    private int id;
    private String nome;
    private String username;
    private String senha;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

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

    public void salvar() {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage msg = null;

        if (nome.isEmpty()) {
            msg = new FacesMessage("Informe nome");
            context.addMessage(null, msg);
        }

        if (username.isEmpty()) {
            msg = new FacesMessage("Informe o Username");
            context.addMessage(null, msg);
        }

        if (senha.isEmpty()) {
            msg = new FacesMessage("Informe a senha");
            context.addMessage(null, msg);
        }

        if (msg == null) {
            Usuario usuario = new Usuario(this.nome, this.username, this.senha);
            if (DaoUsuario.salvar(usuario)) {
                msg = new FacesMessage("Usuário salvo com sucesso");
                context.addMessage(null, msg);
            }
        }
    }

}
