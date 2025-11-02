package model.dao;

import java.util.ArrayList;

public interface IAgendamentosDAOImpl {
    // Inserir agendamento
    int inserirAgendamento(Agendamentos agendamento);

    // Validar horário solicitado
    boolean solicitaHorario(String Data_Hora_Inicio, String Data_Hora_Fim, int id_ambiente);

    // Cancelar agendamento
    int cancelarAgendamento(Agendamentos agendamento);

    // Consultar todos os agendamentos de um user
    ArrayList<Agendamentos> consultarAgendamentosUsuario(int id_usuario);

    // Consultar todos os agendamentos de um ambiente
    ArrayList<Agendamentos> consultarAgendamentosAmbiente(int id_ambiente);

    // Consultar todos os agendamentos futuros de um ambiente
    ArrayList<Agendamentos> consultarAgendamentosAmbienteFuturos(int id_ambiente);
}
