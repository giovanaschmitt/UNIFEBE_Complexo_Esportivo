package model.dao;

public class Usuario {

    // Atributos
    private String nome;
    private String senha;
    private int matricula;
    private int id_USUARIO;
    private char tipo;


    // Construtor
    public Usuario() {
    }

    // Get and set
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public int getId_USUARIO() {
        return id_USUARIO;
    }

    public void setId_USUARIO(int id_USUARIO) {
        this.id_USUARIO = id_USUARIO;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }
}
