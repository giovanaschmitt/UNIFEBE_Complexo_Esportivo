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

    // Inserir um registro de ambiente
    public int inserirAmbiente(Ambientes ambiente) {
        int linhasAfetadas = 0;
        try {
            String SQL = "INSERT INTO sisagenda.ambientes (id_AMBIENTES, Nome_Ambiente, Descricao) " +
                    "VALUES (" +
                        "sisagenda.increment_ambientes.nextval, '" +
                        ambiente.getNomeAmbiente() + "', " +
                        ambiente.getDescricao() + ")";
            linhasAfetadas = this.s.executeUpdate(SQL);
            System.out.println("Ambiente inserido com sucesso: " + linhasAfetadas + " linha(s)");
        } catch (Exception e) {
            System.out.println("Erro ao inserir ambiente: " + e.getMessage());
            e.printStackTrace();
        }
        return linhasAfetadas;
    }

    // Remover um registro de ambiente
    public int removerAmbiente(Ambientes ambiente) {
        int linhasAfetadas = 0;
        try {
            String SQL = "DELETE sisagenda.ambientes" +
                         "WHERE id_AMBIENTES = ambiente.getId_AMBIENTES";
            linhasAfetadas = this.s.executeUpdate(SQL);
            System.out.println("Ambiente removido com sucesso: " + linhasAfetadas + " linha(s)");
        } catch (Exception e) {
            System.out.println("Erro ao remover ambiente: " + e.getMessage());
            e.printStackTrace();
        }
        return linhasAfetadas;
    }

    /*
    --> Continuar deste ponto em diante
     */
    public Usuario buscarUsuarioPorMatricula(int matricula) {
        Usuario usuario = null;
        try {
            String SQL = "SELECT * FROM sisagenda.usuario WHERE Matricula = " + matricula;
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

        return usuario;
    }

}
