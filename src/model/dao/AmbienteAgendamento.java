package model.dao;

public class AmbienteAgendamento {

    // Atributos
    private int AGENDAMENTOS_id_AGENDAMENTOS;
    private int AMBIENTES_id_AMBIENTES;


    // Construtor
    public AmbienteAgendamento() {
    }

    // Get and set
    public int getAGENDAMENTOS_id_AGENDAMENTOS() {
        return AGENDAMENTOS_id_AGENDAMENTOS;
    }

    public void setAGENDAMENTOS_id_AGENDAMENTOS(int AGENDAMENTOS_idAGENDAMENTOS) {
        this.AGENDAMENTOS_id_AGENDAMENTOS = AGENDAMENTOS_idAGENDAMENTOS;
    }

    public int getAMBIENTES_id_AMBIENTES() {
        return AMBIENTES_id_AMBIENTES;
    }

    public void setAMBIENTES_id_AMBIENTES(int AMBIENTES_id_AMBIENTES) {
        this.AMBIENTES_id_AMBIENTES = AMBIENTES_id_AMBIENTES;
    }
}
