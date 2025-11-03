package model.service;

import model.dao.Usuario;
import model.dao.UsuarioDAOImpl;
import java.util.ArrayList;

public class UsuarioService {
    private UsuarioDAOImpl usuarioDao = new UsuarioDAOImpl();

    public boolean adicionarUsuario(String nome, int matricula, String senha, char tipo) {
        Usuario u = new Usuario(nome, matricula, senha, tipo);
        int res = usuarioDao.inserirUsuario(u);
        return res > 0;
    }

    public boolean removerUsuario(int matricula) {
        Usuario u = usuarioDao.buscarUsuarioPorMatricula(matricula);
        if (u == null) return false;
        int res = usuarioDao.removerUsuarioPorMaticula(u);
        return res > 0;
    }

    public Usuario buscarPorMatricula(int matricula) {
        return usuarioDao.buscarUsuarioPorMatricula(matricula);
    }

    public ArrayList<Usuario> buscarPorNome(String nome) {
        return usuarioDao.buscarUsuarioPorNome(nome);
    }

    public ArrayList<Usuario> listarPorTipo(char tipo) {
        ArrayList<Usuario> all = usuarioDao.selectUsuarios();
        ArrayList<Usuario> filtrados = new ArrayList<>();
        for (Usuario u : all) {
            if (u.getTipo() == tipo) filtrados.add(u);
        }
        return filtrados;
    }
}