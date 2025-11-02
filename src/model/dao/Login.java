package model.dao;

import java.sql.ResultSet;
import java.sql.Statement;

public class Login {

    // Atributos
    private Statement s = null;

    public Login (String IP, String database, String user, String senha) {
        ConexaoDB db = new ConexaoDB(IP, database, user, senha);
        this.s = db.getS();
    }

    // Validar a entrada do usuário
    public boolean validarLogin (int matricula, String senha) {
        Usuario usuario = null;

        try {
            String SQL = "SELECT * FROM sisagenda.usuario " +
                         "WHERE Matricula = " + matricula + " AND " +
                         "Senha = " + "'" + senha + "'";
            ResultSet rset = s.executeQuery(SQL);

            if (rset.next()) {
                usuario = new Usuario();
                usuario.setNome(rset.getString("Nome"));
                usuario.setSenha(rset.getString("Senha"));
                usuario.setMatricula(rset.getInt("Matricula"));
                usuario.setTipo(rset.getString("Tipo").charAt(0));
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar usuário: " + e.getMessage());
            e.printStackTrace();
        }
        if (usuario != null) {
            return true;
        }
        return false;
    }
}
