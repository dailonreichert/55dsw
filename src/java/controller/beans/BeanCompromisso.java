package controller.beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import modelo.DAO.DaoCompromisso;
import modelo.entidades.Compromisso;
import modelo.entidades.Usuario;
import utils.SessionUtil;

@ManagedBean
@ViewScoped
public class BeanCompromisso {
    private int idcompromisso;
    private String descricao;
    private String local;
    private Date data;
    private int idusuario;
    private int idcontato;
    private List<Compromisso> compromissos = new ArrayList<>();

    public void getAll(int idContato){
        compromissos.clear();
        try {
            Usuario usuario = (Usuario) SessionUtil.getParam("usuarioLogado");

            ResultSet rs = DaoCompromisso.getAll(usuario.getId(), idContato);
            while(rs.next()){
               compromissos.add(new Compromisso(rs.getInt("idcompromisso"), rs.getString("descricao"), rs.getString("local"), rs.getDate("data"), rs.getInt("idusuario"), rs.getInt("idcontato")));
            }
        } catch (SQLException ex) {
            //
        }
    }

    public void salvar(int idContato) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage msg = null;

        if (descricao.isEmpty()) {
            msg = new FacesMessage("Informe a descrição");
            context.addMessage(null, msg);
        }

        if (local.isEmpty()) {
            msg = new FacesMessage("Informe o local");
            context.addMessage(null, msg);
        }

        if (msg == null) {
            Usuario usuario = (Usuario) SessionUtil.getParam("usuarioLogado");

            Compromisso compromisso = new Compromisso(this.descricao, this.local, this.data, usuario.getId(), idContato);

            if (DaoCompromisso.salvar(compromisso)) {
                msg = new FacesMessage("Compromisso salvo com sucesso");
                context.addMessage(null, msg);
            }
        }
    }

    public int getIdcompromisso() {
        return idcompromisso;
    }

    public void setIdcompromisso(int idcompromisso) {
        this.idcompromisso = idcompromisso;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public int getIdcontato() {
        return idcontato;
    }

    public void setIdcontato(int idcontato) {
        this.idcontato = idcontato;
    }

    public List<Compromisso> getCompromissos() {
        return compromissos;
    }

    public void setCompromissos(List<Compromisso> compromissos) {
        this.compromissos = compromissos;
    }
    
    
}
