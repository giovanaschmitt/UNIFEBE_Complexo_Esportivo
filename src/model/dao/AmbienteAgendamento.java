package model.dao;

public class AmbienteAgendamento {

    // Atributos
    private int AGENDAMENTOS_idAGENDAMENTOS;
    private int AMBIENTES_idAMBIENTES;


    // Construtor
    public AmbienteAgendamento() {
    }

    // Get and set
    public int getAGENDAMENTOS_idAGENDAMENTOS() {
        return AGENDAMENTOS_idAGENDAMENTOS;
    }

    public void setAGENDAMENTOS_idAGENDAMENTOS(int AGENDAMENTOS_idAGENDAMENTOS) {
        this.AGENDAMENTOS_idAGENDAMENTOS = AGENDAMENTOS_idAGENDAMENTOS;
    }

    public int getAMBIENTES_idAMBIENTES() {
        return AMBIENTES_idAMBIENTES;
    }

    public void setAMBIENTES_idAMBIENTES(int AMBIENTES_idAMBIENTES) {
        this.AMBIENTES_idAMBIENTES = AMBIENTES_idAMBIENTES;
    }
}
