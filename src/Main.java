import controller.ValidarLogin;
import model.dao.AgendamentosDAOImpl;
import model.dao.Usuario;
import model.dao.UsuarioDAOImpl;

import java.util.Scanner;

public class Main {

    // Custom
    final String RESET = "\u001B[0m";
    final String BLUE = "\u001B[34m";
    final String RED = "\u001B[31m";
    final String GREEN = "\u001B[32m";

    private ValidarLogin validarLogin = new ValidarLogin();

    private void run(){
        // Atributos
        Scanner leia = new Scanner(System.in);
        Usuario usuario;
        int replay = 1;


        System.out.println(this.BLUE +"=========================================================================" + this.RESET);
        System.out.println(this.BLUE +"== SISTEMA DE AGENDAMENTO DOS ESPAÇOS DO COMPLEXO ESPORTIVO DA UNIFEBE ==" + this.RESET);
        System.out.println(this.BLUE +"=========================================================================" + this.RESET);

        do {
            System.out.print("\n");
            System.out.print("Digite sua matrícula: ");
            int matricula = leia.nextInt();
            System.out.print("Digite sua senha: ");
            String senha = leia.next();

            if (this.validarLogin.validarEntrada(matricula, senha)) {
                System.out.println(this.GREEN + "\nLogin validado!\n" + this.RESET);

                UsuarioDAOImpl funcaoUser = new UsuarioDAOImpl();
                funcaoUser.buscarUsuarioPorMatricula(matricula);

                //switch ()

            } else {
                System.out.println(this.RED + "\nMatrícula ou senha incorreta!\n" + this.RESET);
                System.out.println("Deseja tentar novamente?");
                System.out.println("Sim -> 1");
                System.out.println("Não -> 2");
                System.out.print("\nEscolha: ");
                replay = leia.nextInt();
            }

        } while (replay == 1);
        System.out.println("\nSistema encerrando...");

        //String opcao = leia.next();
        /*
        switch (Integer.parseInt(opcao)){
            case 1:
                if(controllerLogin.validarEntrada("user1","123456")){
                    //new TelaUsuario().sayHello();
                }else{
                    System.out.println("Dados incorretos!");
                }
                break;
            case 2: //new TelaAdmin().sayHello();
                break;
            case 3: System.exit(0);
                break;
            default:
                System.out.println("Opção Inválida!");
                break;
        }

         */
    }




    public static void main(String[] args) {
        new Main().run();
    }
}