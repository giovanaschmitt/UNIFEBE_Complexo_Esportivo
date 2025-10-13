package model.dao;

public class AmbienteAgendamento {

    // Atributos
    private int AGENDAMENTOS_ID_AGENDAMENTOS;
    private int AMBIENTES_ID_AMBIENTES;


    // Construtor
    public AmbienteAgendamento() {
    }

    // Get and set
    public int getAGENDAMENTOS_ID_AGENDAMENTOS() {
        return AGENDAMENTOS_ID_AGENDAMENTOS;
    }

    public void setAGENDAMENTOS_ID_AGENDAMENTOS(int AGENDAMENTOS_ID_AGENDAMENTOS) {
        this.AGENDAMENTOS_ID_AGENDAMENTOS = AGENDAMENTOS_ID_AGENDAMENTOS;
    }

    public int getAMBIENTES_ID_AMBIENTES() {
        return AMBIENTES_ID_AMBIENTES;
    }

    public void setAMBIENTES_ID_AMBIENTES(int AMBIENTES_ID_AMBIENTES) {
        this.AMBIENTES_ID_AMBIENTES = AMBIENTES_ID_AMBIENTES;
    }
}
