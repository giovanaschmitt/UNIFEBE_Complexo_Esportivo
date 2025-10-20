import controller.UsuarioController;

public class Main {
    public static void main(String[] args) {

        String database = "orcl";
        String IP = "192.168.1.107";
        String user = "dev";
        String senha = "dev";

        // Criar controller de usuário
        UsuarioController usuarioController = new UsuarioController(IP, database, user, senha);

        // Exibir menu
        usuarioController.exibirMenu();
    }
}