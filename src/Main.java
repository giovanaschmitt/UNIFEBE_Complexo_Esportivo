import model.dao.ConexaoDB;

public class Main {
    public static void main(String[] args) {

        String database = "orcl";
        String IP = "192.168.1.107";
        String user = "dev";
        String senha = "dev";
        //teste

        ConexaoDB db = new ConexaoDB(IP, database, user, senha);
    }
}