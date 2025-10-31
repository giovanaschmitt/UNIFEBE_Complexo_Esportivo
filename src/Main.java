import controller.UsuarioController;
import model.dao.ConexaoDB;

public class Main {
    public static void main(String[] args) {

        /*
        Lembrete: Ao final de todas as alteações deve-se chamar a função de commit ou rollback
        disponível em cada classe de implementação para garantir o funcionamento correto do
        sistema
         */
        String database = "orcl";
        String IP = "192.168.1.9";
        String user = "sisagenda";
        String senha = "sisagenda";

        // Criar controller de usuário
        //UsuarioController usuarioController = new UsuarioController(IP, database, user, senha);

        // Exibir menu
        //usuarioController.exibirMenu();
        ConexaoDB db = new ConexaoDB(IP, database, user, senha);
    }
}