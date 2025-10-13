package model.dao;

public class Ambientes {

    // Atributos
    private String nome_ambiente;
    private String descricao;


    // Construtor
    public Ambientes() {
    }

    // Get and set
    public String getNomeAmbiente() {
        return nome_ambiente;
    }

    public void setNomeAmbiente(String nome_ambiente) {
        this.nome_ambiente = nome_ambiente;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
