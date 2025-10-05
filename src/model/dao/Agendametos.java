package model.dao;

public class Agendametos {

    // Atributos
    private int AMBIENTE_idAMBIENTES;
    private int USUARIO_idUSUARIO;
    private String dataHoraInicio;
    private String dataHoraFim;


    // Construtor
    public Agendametos() {
    }

    // Get and set
    public int getAMBIENTE_idAMBIENTES() {
        return AMBIENTE_idAMBIENTES;
    }

    public void setAMBIENTE_idAMBIENTES(int AMBIENTE_idAMBIENTES) {
        this.AMBIENTE_idAMBIENTES = AMBIENTE_idAMBIENTES;
    }

    public int getUSUARIO_idUSUARIO() {
        return USUARIO_idUSUARIO;
    }

    public void setUSUARIO_idUSUARIO(int USUARIO_idUSUARIO) {
        this.USUARIO_idUSUARIO = USUARIO_idUSUARIO;
    }

    public String getDataHoraInicio() {
        return dataHoraInicio;
    }

    public void setDataHoraInicio(String dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    public String getDataHoraFim() {
        return dataHoraFim;
    }

    public void setDataHoraFim(String dataHoraFim) {
        this.dataHoraFim = dataHoraFim;
    }
}
