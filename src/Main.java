import model.dao.ConexaoDB;

public class Main {
    public static void main(String[] args) {

        String database = "orcl";
        String IP = "192.168.1.9";

        ConexaoDB db = new ConexaoDB(IP, database);
    }
}