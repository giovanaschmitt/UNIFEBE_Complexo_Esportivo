package model.service;

import model.dao.Ambientes;
import model.dao.AmbientesDAOImpl;
import java.util.ArrayList;

public class AmbienteService {
    private AmbientesDAOImpl ambientesDao = new AmbientesDAOImpl();

    public boolean adicionarAmbiente(String nome, String descricao) {
        Ambientes ambiente = new Ambientes();
        ambiente.setNome_ambiente(nome);
        ambiente.setDescricao(descricao);
        int res = ambientesDao.inserirAmbiente(ambiente);
        return res > 0;
    }

    public boolean removerAmbiente(int id) {
        Ambientes ambiente = new Ambientes();
        ambiente.setId_AMBIENTES(id);
        int res = ambientesDao.removerAmbiente(ambiente);
        return res > 0;
    }

    public boolean atualizarAmbiente(int id, String nome, String descricao) {
        Ambientes ambiente = new Ambientes();
        ambiente.setId_AMBIENTES(id);
        ambiente.setNome_ambiente(nome);
        ambiente.setDescricao(descricao);
        int res = ambientesDao.atualizarAmbiente(ambiente);
        return res > 0;
    }

    public ArrayList<Ambientes> listarAmbientes() {
        return ambientesDao.consultarAmbientes();
    }

    public ArrayList<Ambientes> buscarPorNome(String nome) {
        return ambientesDao.buscarAmbientePorNome(nome);
    }
}