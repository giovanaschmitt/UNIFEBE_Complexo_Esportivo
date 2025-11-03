package model.dao;

// Bibliotecas
import controller.CredenciaisBanco;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class AgendamentosDAOImpl implements IAgendamentosDAOImpl {

    // Atributos
    private Statement s = null;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public AgendamentosDAOImpl() {
        CredenciaisBanco credencial = new CredenciaisBanco();
        ConexaoDB db = new ConexaoDB(
                credencial.getIP(),
                credencial.getDatabase(),
                credencial.getUser(),
                credencial.getPwd_banco()
        );
        this.s = db.getS();
    }

    // Inserir agendamento
    @Override
    public int inserirAgendamento(Agendamentos agendamento) {
        int linhasAfetadas = 0;

        try {
            String SQL = "INSERT INTO sisagenda.agendamentos (id_AGENDAMENTOS, " +
                                                             "AMBIENTE_id_AMBIENTES, " +
                                                             "USUARIO_id_USUARIO, " +
                                                             "Data_Hora_Inicio, " +
                                                             "Data_Hora_Fim, " +
                                                             "Data_Hora_Agendamento, " +
                                                             "Status_agendamento) " +
                    "VALUES (" +
                        "sisagenda.increment_agendamentos.nextval, '" +
                        agendamento.getAMBIENTE_ID_AMBIENTES() + "', '" +
                        agendamento.getUSUARIO_ID_USUARIO() + "', " +
                        "TO_DATE(agendamento.getData_Hora_Inicio(), 'DD/MM/YYYY HH24:MI:SS')," +
                        "TO_DATE(tagendamento.getData_Hora_Fim() 'DD/MM/YYYY HH24:MI:SS'),"+
                        "TO_DATE(tagendamento.getData_Hora_Agendamento() 'DD/MM/YYYY HH24:MI:SS'), '"+
                        agendamento.getStatus_agendamento() + "')";

            linhasAfetadas = this.s.executeUpdate(SQL);
            System.out.println("Ambiente agendado: " + linhasAfetadas + " linha(s)");
        } catch (Exception e) {
            System.out.println("Erro ao agendar o ambiente: " + e.getMessage());
            e.printStackTrace();
        }
        return linhasAfetadas;
    }

    // Validar horário solicitado
    @Override
    public boolean solicitaHorario(String Data_Hora_Inicio, String Data_Hora_Fim, int id_ambiente) {

        // Horários solicitados
        LocalDateTime inicio_solicitado = LocalDateTime.parse(Data_Hora_Inicio, formatter);
        LocalDateTime fim_solicitado = LocalDateTime.parse(Data_Hora_Fim, formatter);

        // Disponibilidade
        boolean status = false;

        if (inicio_solicitado.isBefore(LocalDateTime.now())) { // Valida se o horário solicitado é posterior a data atual
            return status;
        } else if (fim_solicitado.isBefore(inicio_solicitado)) { // Valida se o horário de fim é posterior ao horário de início
            return status;
        }

        // Consulta todos os agendamentos futuros no banco referentes aquele ambiente
        ArrayList<Agendamentos> lista = this.consultarAgendamentosAmbienteFuturos(id_ambiente);

        if (!lista.isEmpty()) {
            for (int i = 0; i < lista.size(); i++) {
                Agendamentos agendamento = lista.get(i);

                // Dados dos agendamentos já agendados
                LocalDateTime inicio = LocalDateTime.parse(agendamento.getData_Hora_Inicio(), formatter);
                LocalDateTime fim = LocalDateTime.parse(agendamento.getData_Hora_Fim(), formatter);

                if (agendamento.getStatus_agendamento() != 'I') {
                    if (inicio_solicitado.isBefore(inicio) || fim_solicitado.isBefore(inicio)) {
                        status = true;
                    } else if (inicio_solicitado.isAfter(fim)) {
                        status = true;
                    } else {
                        status = false;
                    }
                }
            }
        } else {
            // Valida o insert se nenhum registro for encontrado
            return true;
        }
        return status;
    }


    // Cancelar agendamento
    @Override
    public int cancelarAgendamento(Agendamentos agendamento) {
        int linhasAfetadas = 0;

        try {
            String SQL = "UPDATE sisagenda.ambientes " +
                         "SET Status_agendamento = 'I'" +
                         "WHERE id_AGENDAMENTOS = " + agendamento.getID_AGENDAMENTOS();
            linhasAfetadas = this.s.executeUpdate(SQL);
            System.out.println("Agendamento cancelado com sucesso: " + linhasAfetadas + " linha(s)");
        } catch (Exception e) {
            System.out.println("Erro ao cancelar o agendamento: " + e.getMessage());
            e.printStackTrace();
        }
        return linhasAfetadas;
    }

    // Consultar todos os agendamentos de um user
    @Override
    public ArrayList<Agendamentos> consultarAgendamentosUsuario(int id_usuario) {
        ArrayList<Agendamentos> lista = new ArrayList<>();

        try {
            String SQL = "SELECT * FROM sisagenda.agendamentos WHERE USUARIO_id_USUARIO = " + id_usuario;
            ResultSet rset = s.executeQuery(SQL);

            while (rset.next()) {
                Agendamentos agendamento = new Agendamentos();
                agendamento.setID_AGENDAMENTOS(rset.getInt("id_AGENDAMENTOS"));
                agendamento.setAMBIENTE_ID_AMBIENTES(rset.getInt("AMBIENTE_id_AMBIENTES"));
                agendamento.setUSUARIO_ID_USUARIO(rset.getInt("USUARIO_id_USUARIO"));
                agendamento.setData_Hora_Inicio(sdf.format(rset.getTimestamp("Data_Hora_Inicio")));
                agendamento.setData_Hora_Fim(sdf.format(rset.getTimestamp("Data_Hora_Fim")));
                agendamento.setData_Hora_Agendamento(sdf.format(rset.getTimestamp("Data_Hora_Agendamento")));
                agendamento.setStatus_agendamento(rset.getString("Status_agendamento").charAt(0));
                lista.add(agendamento);
            }
        } catch (Exception e) {
            System.out.println("Erro ao consultar os agendamentos do usuário: " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }

    // Consultar todos os agendamentos de um ambiente
    @Override
    public ArrayList<Agendamentos> consultarAgendamentosAmbiente(int id_ambiente) {
        ArrayList<Agendamentos> lista = new ArrayList<>();

        try {
            String SQL = "SELECT * FROM sisagenda.agendamentos WHERE AMBIENTE_id_AMBIENTES = " + id_ambiente;
            ResultSet rset = s.executeQuery(SQL);

            while (rset.next()) {
                Agendamentos agendamento = new Agendamentos();
                agendamento.setID_AGENDAMENTOS(rset.getInt("id_AGENDAMENTOS"));
                agendamento.setAMBIENTE_ID_AMBIENTES(rset.getInt("AMBIENTE_id_AMBIENTES"));
                agendamento.setUSUARIO_ID_USUARIO(rset.getInt("USUARIO_id_USUARIO"));
                agendamento.setData_Hora_Inicio(sdf.format(rset.getTimestamp("Data_Hora_Inicio")));
                agendamento.setData_Hora_Fim(sdf.format(rset.getTimestamp("Data_Hora_Fim")));
                agendamento.setData_Hora_Agendamento(sdf.format(rset.getTimestamp("Data_Hora_Agendamento")));
                agendamento.setStatus_agendamento(rset.getString("Status_agendamento").charAt(0));
                lista.add(agendamento);
            }
        } catch (Exception e) {
            System.out.println("Erro ao consultar os agendamentos do ambiente: " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }

    // Consultar todos os agendamentos futuros de um ambiente
    @Override
    public ArrayList<Agendamentos> consultarAgendamentosAmbienteFuturos(int id_ambiente) {
        ArrayList<Agendamentos> lista = new ArrayList<>();
        LocalDateTime agora = LocalDateTime.now();
        String aux = agora.format(formatter);

        try {
            String SQL = "SELECT * FROM sisagenda.agendamentos " +
                         "WHERE AMBIENTE_id_AMBIENTES = " + id_ambiente + " AND " +
                         "Data_Hora_Inicio >= TO_DATE('" + aux + "', 'DD/MM/YYYY HH24:MI:SS')" +
                         "ORDER BY Data_Hora_Inicio";
            ResultSet rset = s.executeQuery(SQL);

            while (rset.next()) {
                Agendamentos agendamento = new Agendamentos();
                agendamento.setID_AGENDAMENTOS(rset.getInt("id_AGENDAMENTOS"));
                agendamento.setAMBIENTE_ID_AMBIENTES(rset.getInt("AMBIENTE_id_AMBIENTES"));
                agendamento.setUSUARIO_ID_USUARIO(rset.getInt("USUARIO_id_USUARIO"));
                agendamento.setData_Hora_Inicio(sdf.format(rset.getTimestamp("Data_Hora_Inicio")));
                agendamento.setData_Hora_Fim(sdf.format(rset.getTimestamp("Data_Hora_Fim")));
                agendamento.setData_Hora_Agendamento(sdf.format(rset.getTimestamp("Data_Hora_Agendamento")));
                agendamento.setStatus_agendamento(rset.getString("Status_agendamento").charAt(0));
                lista.add(agendamento);
            }
        } catch (Exception e) {
            System.out.println("Erro ao consultar os agendamentos do ambiente: " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }

     // Consultar todos os agendamentos futuros de um user
     @Override
     public ArrayList<Agendamentos> consultarAgendamentosUsuarioFuturo(int id_ambiente, int id_usuario) {
        ArrayList<Agendamentos> lista = new ArrayList<>();
        LocalDateTime agora = LocalDateTime.now();
        String aux = agora.format(formatter);

        try {
            String SQL = "SELECT * FROM sisagenda.agendamentos " +
                         "WHERE USUARIO_id_USUARIO = " + id_usuario +
                         "Data_Hora_Inicio >= TO_DATE('" + aux + "', 'DD/MM/YYYY HH24:MI:SS')" +
                         "ORDER BY Data_Hora_Inicio";
            ResultSet rset = s.executeQuery(SQL);

            while (rset.next()) {
                Agendamentos agendamento = new Agendamentos();
                agendamento.setID_AGENDAMENTOS(rset.getInt("id_AGENDAMENTOS"));
                agendamento.setAMBIENTE_ID_AMBIENTES(rset.getInt("AMBIENTE_id_AMBIENTES"));
                agendamento.setUSUARIO_ID_USUARIO(rset.getInt("USUARIO_id_USUARIO"));
                agendamento.setData_Hora_Inicio(sdf.format(rset.getTimestamp("Data_Hora_Inicio")));
                agendamento.setData_Hora_Fim(sdf.format(rset.getTimestamp("Data_Hora_Fim")));
                agendamento.setData_Hora_Agendamento(sdf.format(rset.getTimestamp("Data_Hora_Agendamento")));
                agendamento.setStatus_agendamento(rset.getString("Status_agendamento").charAt(0));
                lista.add(agendamento);
            }
        } catch (Exception e) {
            System.out.println("Erro ao consultar os agendamentos do ambiente: " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }

}