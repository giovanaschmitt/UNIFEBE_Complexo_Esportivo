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

    // Confirmar alterações
    public boolean commitInsert() {
        try {
            String SQL = "commit";
            this.s.executeUpdate(SQL);
            System.out.println("Commit realizado");
        } catch (Exception e) {
            System.out.println("Erro no commit: " + e.getMessage());
            e.printStackTrace();
        }
        return true;
    }

    // Cancelar alterações
    public boolean rollbackInsert() {
        try {
            String SQL = "rollback";
            this.s.executeUpdate(SQL);
            System.out.println("Rollback realizado");
        } catch (Exception e) {
            System.out.println("Erro no rollback: " + e.getMessage());
            e.printStackTrace();
        }
        return true;
    }

    // Inserir agendamento
    public int inserirAgendamento(Agendamentos agendamento){
        int linhasAfetadas = 0;
        try{
            String SQL = "INSERT INTO dev.agendamentos (id_AGENDAMENTOS, AMBIENTE_id_AMBIENTES, USUARIO_id_USUARIO, Data_Hora_Inicio, Data_Hora_Fim) " +
                    "VALUES ("+agendamento.getID_AGENDAMENTOS()+",'"+agendamento.getAMBIENTE_ID_AMBIENTES()+"', '"+agendamento.getUSUARIO_ID_USUARIO()+"','"+agendamento.getDataHoraInicio()+"','"+agendamento.getDataHoraFim()+"')";
            linhasAfetadas = this.s.executeUpdate(SQL); //executa
            System.out.println("Dados Inseridos: "+linhasAfetadas);

        }catch(Exception e){
            e.printStackTrace();
        }
        return linhasAfetadas;
    }

    // Selecionar todos os dados da tabela
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