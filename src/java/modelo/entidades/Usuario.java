
package modelo.entidades;

public class Usuario extends Object{
    private int id;
    private String nome;
    private String username;
    private String senha;

    public Usuario(String nome, String username, String senha) {
        this.nome     = nome;
        this.username = username;
        this.senha    = senha;
    }

    public Usuario() {}

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
}
