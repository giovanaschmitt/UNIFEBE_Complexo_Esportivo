package model.dao;

// Bibliotecas
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class AgendamentosDAOImpl {

    // Atributos
    private Statement s = null;

    public AgendamentosDAOImpl(String IP, String database, String user, String senha) {
        ConexaoDB db = new ConexaoDB(IP, database, user, senha);
        this.s = db.getS();
    }

    // Inserir agendamento
    // --> tenho de ver como fazer a inserção com a sequence no banco
    public int inserirAgendamento(Agendamentos agendamento){
        int linhasAfetadas = 0;
        try{
            String SQL = "INSERT INTO dev.agendamentos (id_AGENDAMENTOS, AMBIENTE_id_AMBIENTES, USUARIO_id_USUARIO, Data_Hora_Inicio, Data_Hora_Fim) " +
                           "VALUES ('"+aluno.getNome()+"','"+aluno.getCurso()+"', '"+aluno.getMatricula()+"',"+aluno.getFase()+")";
            linhasAfetadas = this.s.executeUpdate(SQL); //executa
            System.out.println("Dados Inseridos: "+linhasAfetadas);

        }catch(Exception e){
            e.printStackTrace();
        }
        return linhasAfetadas;
    }

    // Selecionar todos os dados da tabela
    //--> Testar implementação do select
    public ArrayList<Agendamentos> selectAgendamentos(){
        ArrayList<Agendamentos> lista = new ArrayList<>();
        try{
            String SQL = "SELECT * FROM dev.agendamentos";
            ResultSet rset = s.executeQuery(SQL);
            while(rset.next()){
                Agendamentos agendamentos = new Agendamentos();
                agendamentos.setID_AGENDAMENTOS(rset.getInt("id_AGENDAMENTOS"));
                agendamentos.setAMBIENTE_ID_AMBIENTES(rset.getInt("AMBIENTE_id_AMBIENTES"));
                agendamentos.setUSUARIO_ID_USUARIO(rset.getInt("USUARIO_id_USUARIO"));
                agendamentos.setDataHoraInicio(rset.getString("Data_Hora_Inicio"));
                agendamentos.setDataHoraFim(rset.getString("Data_Hora_Fim"));
                lista.add(agendamentos);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return lista;
    }
}
