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
    final String YELLOW = "\u001B[33m";

    // Atributos padrão
    private Scanner leia = new Scanner(System.in);
    private int replay;
    private int opcao;

    private ValidarLogin validarLogin = new ValidarLogin();

    private void run(){
        // Atributos
        Usuario usuario;
        this.replay = 1;
        this.opcao = 0;


        System.out.println(this.BLUE +"=========================================================================" + this.RESET);
        System.out.println(this.BLUE +"== SISTEMA DE AGENDAMENTO DOS ESPAÇOS DO COMPLEXO ESPORTIVO DA UNIFEBE ==" + this.RESET);
        System.out.println(this.BLUE +"=========================================================================" + this.RESET);

        do {
            System.out.print("\n");
            System.out.print("Digite sua matrícula: ");
            int matricula = this.leia.nextInt();
            System.out.print("Digite sua senha: ");
            String senha = this.leia.next();

            if (this.validarLogin.validarEntrada(matricula, senha)) {
                System.out.println(this.GREEN + "\nLogin validado!\n" + this.RESET);

                UsuarioDAOImpl funcaoUser = new UsuarioDAOImpl();
                usuario = funcaoUser.buscarUsuarioPorMatricula(matricula);

                switch (usuario.getTipo()) {

                    case 'A':
                        new TelaAdmin().sayHello(usuario.getNome());
                        do {
                            new TelaAdmin().opcoesAcoes();
                            this.opcao = this.leia.nextInt();

                            switch (this.opcao) {
                                case 1:
                                    new TelaAdmin().gerenciarUsuarios();
                                    break;

                                case 2:
                                    new TelaAdmin().gerenciarAmbientes();
                                    break;

                                case 3:
                                    new TelaAdmin().gerenciarAgendamentos();
                                    break;

                                case 0:
                                    this.replay = 0;
                                    break;

                                default:
                                    new TelaAdmin().opcaoInvalida();
                                    break;
                            }

                        } while (this.replay == 1);
                        break;

                    case 'C':
                        new TelaUsuario().sayHello(usuario.getNome());
                        do {
                            new TelaUsuario().opcoesAcoes();
                            this.opcao = this.leia.nextInt();

                            switch (this.opcao) {
                                case 1:
                                    new Main().consultaAmbientes(usuario);
                                    break;

                                case 2:
                                    new Main().agendamentoDeAmbientes(usuario);
                                    break;

                                case 0:
                                    this.replay = 0;
                                    break;

                                default:
                                    new TelaUsuario().opcaoInvalida();
                                    break;
                            }

                        } while (this.replay == 1);
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
                this.replay = leia.nextInt();
            }

        } while (this.replay == 1);
        System.out.println(this.YELLOW + "\nSistema encerrando..." + this.RESET);
    }

    public void consultaAmbientes(Usuario usuario) {
        // Atributos
        this.replay = 1;
        this.opcao = 0;

        do {
            new TelaUsuario().consultarAmbientes();
            this.opcao = this.leia.nextInt();

            switch (this.opcao) {
                case 1: // Consultar todos os ambientes
                    System.out.println("Consultar todos os ambientes");
                    break;

                case 2: // Pesquisar um ambiente
                    System.out.println("Pesquisar um ambiente");
                    break;

                case 0:
                    this.replay = 0;
                    break;

                default:
                    new TelaUsuario().opcaoInvalida();
                    break;
            }

        } while (this.replay == 1);
    }

    public void agendamentoDeAmbientes(Usuario usuario) {
        // Atributos
        this.replay = 1;
        this.opcao = 0;

        do {
            new TelaUsuario().agendamentoDeAmbientes();
            this.opcao = this.leia.nextInt();

            switch (this.opcao) {
                case 1: // Agendar um ambiente
                    System.out.println("Agendar um ambiente");
                    break;

                case 2: // Cancelar um agendamento
                    System.out.println("Cancelar um agendamento");
                    break;

                case 3: // Consultar todos os meus agendamentos
                    System.out.println("Consultar todos os meus agendamentos");
                    break;

                case 4: // Consultar os horários já ocupados de um ambiente
                    System.out.println("Consultar horários já ocupados de um ambiente");
                    break;

                case 0:
                    this.replay = 0;
                    break;

                default:
                    new TelaUsuario().opcaoInvalida();
                    break;
            }

        } while (this.replay == 1);
    }





    public static void main(String[] args) {
        new Main().run();
    }
}