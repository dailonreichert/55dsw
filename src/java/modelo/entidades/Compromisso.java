package modelo.entidades;

import java.util.Date;


public class Compromisso {
    private int idcompromisso;
    private String descricao;
    private String local;
    private Date data;
    private int idusuario;
    private int idcontato;

    public Compromisso(int idCompromisso, String descicao, String local, Date data, int idusuario, int idcontato) {
        this.idcompromisso = idCompromisso;
        this.descricao = descicao;
        this.local = local;
        this.data = data;
        this.idusuario = idusuario;
        this.idcontato = idcontato;
    }

    public Compromisso(String descicao, String local, Date data, int idusuario, int idcontato) {
        this.descricao = descicao;
        this.local = local;
        this.data = data;
        this.idusuario = idusuario;
        this.idcontato = idcontato;
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
}
