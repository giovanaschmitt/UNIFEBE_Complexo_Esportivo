package model.dao;

// Bibliotecas
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class AmbienteAgendamentoDAOImpl {

    // Atributos
    private Statement s = null;

    public AmbienteAgendamentoDAOImpl(String IP, String database, String user, String senha) {
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

}
