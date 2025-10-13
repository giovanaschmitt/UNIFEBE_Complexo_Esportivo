package model.dao;

// Bibliotecas
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class AmbientesDAOImpl {

    // Atributos
    private Statement s = null;

    public AmbientesDAOImpl(String IP, String database, String user, String senha) {
        ConexaoDB db = new ConexaoDB(IP, database, user, senha);
        this.s = db.getS();
    }

}
