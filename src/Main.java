import controller.ValidarLogin;
import model.dao.AgendamentosDAOImpl;
import model.dao.Usuario;
import model.dao.UsuarioDAOImpl;
import view.TelaAdmin;
import view.TelaUsuario;

import java.util.Scanner;

public class Main {

    // Códigos ANSI
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
        int opcao = 0;


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
                usuario = funcaoUser.buscarUsuarioPorMatricula(matricula);

                switch (usuario.getTipo()) {
                    case 'A':
                        new TelaAdmin().sayHello(usuario.getNome());
                        new TelaAdmin().opcoesAcoes();
                        new TelaAdmin().gerenciarUsuarios();
                        new TelaAdmin().gerenciarAmbientes();
                        new TelaAdmin().gerenciarAgendamentos();
                        break;
                    case 'C':
                        new TelaUsuario().sayHello(usuario.getNome());
                        new TelaUsuario().opcoesAcoes();

                        break;
                    default:
                        System.out.println(this.RED + "\nErro ao validar o tipo do usuário!" + this.RESET);
                        break;
                }

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


    }


    public static void main(String[] args) {
        new Main().run();
    }
}