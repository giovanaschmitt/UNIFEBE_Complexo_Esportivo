package model.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class UsuarioDAOImpl {

    private Statement s = null;

    public UsuarioDAOImpl(String IP, String database, String user, String senha) {
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


//NOVO USUARIO
    public int inserirUsuario(Usuario usuario) {
        int linhasAfetadas = 0;
        try {
            // GERADOR DE ID AUTOMATICO
            String SQL = "INSERT INTO sisagenda.usuario (id_USUARIO, Nome, Matricula, Senha, Tipo) " +
                    "VALUES (sisagenda.increment_usuario.nextval, '" + usuario.getNome() + "', " +
                    usuario.getMatricula() + ", '" + usuario.getSenha() + "', '" + usuario.getTipo() + "')";
            linhasAfetadas = this.s.executeUpdate(SQL);
            System.out.println("Usuário Inserido com sucesso: " + linhasAfetadas + " linha(s)");
        } catch (Exception e) {
            System.out.println("Erro ao inserir usuário: " + e.getMessage());
            e.printStackTrace();
        }
        return linhasAfetadas;
    }

// BUSCA MATRICULA
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


 //BUSCA NOME
    public Usuario buscarUsuarioPorNome(String nome) {
        Usuario usuario = null;
        try {
            String SQL = "SELECT * FROM dev.usuario WHERE Nome = '" + nome + "'";
            ResultSet rset = s.executeQuery(SQL);

            if (rset.next()) {
                usuario = new Usuario();usuario.setNome(rset.getString("Nome"));
                usuario.setSenha(rset.getString("Senha"));
                usuario.setMatricula(rset.getInt("Matricula"));
                usuario.setTipo(rset.getString("Tipo").charAt(0));
            }
        } catch (Exception e) {System.out.println("Erro ao buscar usuário: " + e.getMessage());
            e.printStackTrace();
        }
        return usuario;
    }
    //LISTA DE USUÁRIO
    public ArrayList<Usuario> selectUsuarios() {
        ArrayList<Usuario> lista = new ArrayList<>();
        try {
            String SQL = "SELECT * FROM dev.usuario";
            ResultSet rset = s.executeQuery(SQL);

            while (rset.next()) {
                Usuario usuario = new Usuario();
                usuario.setNome(rset.getString("Nome"));
                usuario.setSenha(rset.getString("Senha"));
                usuario.setMatricula(rset.getInt("Matricula"));
                usuario.setTipo(rset.getString("Tipo").charAt(0));
                lista.add(usuario);
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar usuários: " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }
}