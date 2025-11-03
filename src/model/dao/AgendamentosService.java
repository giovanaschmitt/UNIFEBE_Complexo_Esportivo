package model.dao;

import model.dao.Agendamentos;
import model.dao.AgendamentosDAOImpl;
import java.util.ArrayList;

public class AgendamentosService {
    private AgendamentosDAOImpl dao = new AgendamentosDAOImpl();

    public boolean adicionarAgendamento(Agendamentos agendamento) {
        return dao.inserirAgendamento(agendamento) > 0;
    }

    public boolean cancelarAgendamento(int idAgendamento) {
        Agendamentos a = new Agendamentos();
        a.setID_AGENDAMENTOS(idAgendamento);
        return dao.cancelarAgendamento(a) > 0;
    }

    public ArrayList<Agendamentos> consultarAgendamentosUsuario(int idUsuario) {
        return dao.consultarAgendamentosUsuario(idUsuario);
    }

    public ArrayList<Agendamentos> consultarAgendamentosAmbiente(int idAmbiente) {
        return dao.consultarAgendamentosAmbiente(idAmbiente);
    }

    public ArrayList<Agendamentos> consultarAgendamentosFuturosAmbiente(int idAmbiente) {
        return dao.consultarAgendamentosAmbienteFuturos(idAmbiente);
    }
}