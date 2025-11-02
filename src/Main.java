import controller.ValidarLogin;
import model.dao.Agendamentos;
import model.dao.AgendamentosDAOImpl;
import model.dao.Ambientes;
import model.dao.AmbientesDAOImpl;

import java.util.ArrayList;

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
        //ConexaoDB db = new ConexaoDB(IP, database, user, senha);
        /*
        AmbientesDAOImpl teste = new AmbientesDAOImpl(IP, database, user, senha);

        Ambientes ambiente = new Ambientes();
        ambiente.setNome_ambiente("teste_update");
        ambiente.setDescricao("teste_update");
        ambiente.setId_AMBIENTES(23);

        */
        //teste.inserirAmbiente(ambiente);
        //teste.removerAmbiente(ambiente);
        //teste.atualizarAmbiente(ambiente);
        /*
        ArrayList<Ambientes> a = teste.buscarAmbientePorNome("de");

        if (!a.isEmpty()) {
            for (int i = 0; i < a.size(); i++) {
                Ambientes imprimir = a.get(i);
                System.out.println(imprimir.getId_AMBIENTES() + " " + imprimir.getNome_ambiente());
            }
        } else {
            System.out.println("Sem resultado");
        }


        ArrayList<Ambientes> ambientes = teste.consultarAmbientes();

        for (int i = 0; i < ambientes.size(); i++) {
            Ambientes imprimir = ambientes.get(i);
            System.out.println(imprimir.getId_AMBIENTES() + " " + imprimir.getNome_ambiente());
        }
        */
        /*
        int matricula = 2024100100;
        String pwd = "12";


        ValidarLogin valida = new ValidarLogin(IP, database, user, senha);
        if (valida.validarEntrada(matricula, pwd)) {
            System.out.println("Entrada VÁLIDA");
        } else {
            System.out.println("Entrada inválida");
        }

         */

        AgendamentosDAOImpl teste = new AgendamentosDAOImpl(IP, database, user, senha);

        System.out.println(teste.solicitaHorario("04/11/2025 09:01:48", "04/11/2025 10:01:48", 3));

/*
        ArrayList<Agendamentos> a = teste.consultarAgendamentosAmbienteFuturos(3);

        for (int i = 0; i < a.size(); i++) {
            Agendamentos imprimir = a.get(i);
            System.out.println(
                    "\n" + imprimir.getID_AGENDAMENTOS() +
                            "\n" + imprimir.getAMBIENTE_ID_AMBIENTES() +
                            "\n" + imprimir.getUSUARIO_ID_USUARIO() +
                            "\n" + imprimir.getData_Hora_Inicio() +
                            "\n" + imprimir.getData_Hora_Fim() +
                            "\n" + imprimir.getData_Hora_Agendamento() +
                            "\n" + imprimir.getStatus_agendamento() + "\n"
            );


        }

 */


    }
}