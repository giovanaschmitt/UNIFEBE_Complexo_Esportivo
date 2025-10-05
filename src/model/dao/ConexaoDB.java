package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexaoDB {

    private Connection Conn = null;
    private Statement s = null;

    public ConexaoDB(String IP, String database) {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            this.Conn = DriverManager.getConnection("jdbc:oracle:thin:dev/dev@" + IP + ":1521:" + database);
                    //"jdbc:oracle:thin:@" + IP + ":1521:" + database
            this.s = this.Conn.createStatement();
            System.out.println("Conectado ao banco de dados: "+database);
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public Connection getConn() {
        return Conn;
    }

    public Statement getS() {
        return s;
    }
}
