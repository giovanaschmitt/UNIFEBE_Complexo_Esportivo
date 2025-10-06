package model.dao;

public class Ambientes {

    // Atributos
    private String nome_Ambiente;
    private String descricao;


    // Construtor
    public Ambientes() {
    }

    // Get and set
    public String getNomeAmbiente() {
        return nome_Ambiente;
    }

    public void setNomeAmbiente(String nome_Ambiente) {
        this.nome_Ambiente = nome_Ambiente;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
