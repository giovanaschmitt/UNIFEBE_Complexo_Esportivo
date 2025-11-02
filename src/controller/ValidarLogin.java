package controller;

import model.dao.Login;

public class ValidarLogin {

    // Atributos
    private Login login;

    public ValidarLogin(String IP, String database, String user, String senha) {
        this.login = new Login(IP, database, user, senha);
    }

    // Validar a entrada do usuário
    public boolean validarEntrada(int matricula, String senha){
        if (login.validarLogin(matricula, senha)){
            return true;
        }else{
            return false;
        }
    }

}
