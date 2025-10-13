package model.dao;

public class Agendamentos {

    // Atributos
    private int ID_AGENDAMENTOS;
    private int AMBIENTE_ID_AMBIENTES;
    private int USUARIO_ID_USUARIO;
    private String data_hora_inicio;
    private String data_hora_fim;


    // Construtor
    public Agendamentos() {
    }

    // Get and set
    public int getID_AGENDAMENTOS() {
        return ID_AGENDAMENTOS;
    }

    public void setID_AGENDAMENTOS(int ID_AGENDAMENTOS) {
        this.ID_AGENDAMENTOS = ID_AGENDAMENTOS;
    }

    public int getAMBIENTE_ID_AMBIENTES() {
        return AMBIENTE_ID_AMBIENTES;
    }

    public void setAMBIENTE_ID_AMBIENTES(int AMBIENTE_ID_AMBIENTES) {
        this.AMBIENTE_ID_AMBIENTES = AMBIENTE_ID_AMBIENTES;
    }

    public int getUSUARIO_ID_USUARIO() {
        return USUARIO_ID_USUARIO;
    }

    public void setUSUARIO_ID_USUARIO(int USUARIO_ID_USUARIO) {
        this.USUARIO_ID_USUARIO = USUARIO_ID_USUARIO;
    }

    public String getDataHoraInicio() {
        return data_hora_inicio;
    }

    public void setDataHoraInicio(String data_hora_inicio) {
        this.data_hora_inicio = data_hora_inicio;
    }

    public String getDataHoraFim() {
        return data_hora_fim;
    }

    public void setDataHoraFim(String dataHoraFim) {
        this.data_hora_fim = data_hora_fim;
    }
}
