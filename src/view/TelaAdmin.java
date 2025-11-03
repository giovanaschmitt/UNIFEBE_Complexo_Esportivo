package view;

import model.dao.Usuario;
import model.service.UsuarioService;
import java.util.Scanner;
import java.util.ArrayList;

public class TelaAdmin {

    // Códigos ANSI
    final String RESET = "\u001B[0m";
    final String RED = "\u001B[31m";
    final String GREEN = "\u001B[32m";
    final String YELLOW = "\u001B[33m";

    private Scanner scanner = new Scanner(System.in);
    private UsuarioService usuarioService = new UsuarioService();

    public TelaAdmin() {}

    public void sayHello(String nome){
        System.out.println("Olá! Bem vindo(a) " + nome + "!");
        System.out.println(this.YELLOW + "\nVOCÊ ESTÁ LOGADO COM UM USUÁRIO ADMINISTRATIVO\n" + this.RESET);
    }

    public void opcoesAcoes() {
        System.out.print("\n");
        System.out.println("------------------------------------------------------");
        System.out.println("=========== ESCOLHA UMA AÇÃO PARA REALIZAR ===========");
        System.out.println("------------------------------------------------------");
        System.out.println("- 1 = Gerenciar usuários                             -");
        System.out.println("- 2 = Gerenciar ambientes                            -");
        System.out.println("- 3 = Gerenciar agendamentos                         -");
        System.out.println("-                                                    -");
        System.out.println("- 0 = Sair do sistema                                -");
        System.out.println("------------------------------------------------------");

        System.out.print("\nEscolha uma opção: ");
    }

    public void gerenciarUsuarios() {
        int opcao;
        do {
            System.out.print("\n");
            System.out.println("------------------------------------------------------");
            System.out.println("================= GESTÃO DE USUÁRIOS =================");
            System.out.println("------------------------------------------------------");
            System.out.println("- 1 = Adicionar um novo usuário                      -");
            System.out.println("- 2 = Remover um usuário através da matrícula        -");
            System.out.println("- 3 = Buscar um usuário através da matricula         -");
            System.out.println("- 4 = Buscar um usuário através do nome              -");
            System.out.println("- 5 = Consultar todos os usuários administrativos    -");
            System.out.println("- 6 = Consultar todos os usuários comuns             -");
            System.out.println("-                                                    -");
            System.out.println("- 0 = Sair do sistema                                -");
            System.out.println("------------------------------------------------------");
            System.out.print("\nEscolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    adicionarUsuario();
                    break;
                case 2:
                    removerUsuario();
                    break;
                case 3:
                    buscarPorMatricula();
                    break;
                case 4:
                    buscarPorNome();
                    break;
                case 5:
                    listarPorTipo('A');
                    break;
                case 6:
                    listarPorTipo('C');
                    break;
                case 0:
                    System.out.println("Saindo do menu de usuários...");
                    break;
                default:
                    opcaoInvalida();
            }
        } while (opcao != 0);
    }

    private void adicionarUsuario() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Matrícula: ");
        int matricula = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        System.out.print("Tipo (A - Admin / C - Comum): ");
        char tipo = scanner.nextLine().toUpperCase().charAt(0);

        if (usuarioService.adicionarUsuario(nome, matricula, senha, tipo))
            System.out.println(GREEN + "Usuário adicionado com sucesso!" + RESET);
        else
            System.out.println(RED + "Erro ao adicionar usuário." + RESET);
        pausaVoltarMenu();
    }

    private void removerUsuario() {
        System.out.print("Informe a matrícula do usuário a ser removido: ");
        int matricula = scanner.nextInt();
        scanner.nextLine();
        if (usuarioService.removerUsuario(matricula))
            System.out.println(GREEN + "Usuário removido com sucesso!" + RESET);
        else
            System.out.println(RED + "Usuário não encontrado ou erro ao remover." + RESET);
        pausaVoltarMenu();
    }

    private void buscarPorMatricula() {
        System.out.print("Informe a matrícula: ");
        int matricula = scanner.nextInt();
        scanner.nextLine();
        Usuario usuario = usuarioService.buscarPorMatricula(matricula);
        if (usuario != null) {
            System.out.println("Nome: " + usuario.getNome() +
                    "\nMatrícula: " + usuario.getMatricula() +
                    "\nTipo: " + usuario.getTipo());
        } else {
            System.out.println(YELLOW + "Usuário não encontrado." + RESET);
        }
        pausaVoltarMenu();
    }

    private void buscarPorNome() {
        System.out.print("Informe o nome (ou parte do nome): ");
        String nome = scanner.nextLine();
        ArrayList<Usuario> lista = usuarioService.buscarPorNome(nome);
        if (lista.isEmpty()) {
            System.out.println(YELLOW + "Nenhum usuário encontrado." + RESET);
        } else {
            for (Usuario usuario : lista) {
                System.out.println("Nome: " + usuario.getNome() +
                        " | Matrícula: " + usuario.getMatricula() +
                        " | Tipo: " + usuario.getTipo());
            }
        }
        pausaVoltarMenu();
    }

    private void listarPorTipo(char tipo) {
        ArrayList<Usuario> lista = usuarioService.listarPorTipo(tipo);
        if (lista.isEmpty()) {
            System.out.println(YELLOW + "Nenhum usuário do tipo solicitado encontrado." + RESET);
        } else {
            for (Usuario usuario : lista) {
                System.out.println("Nome: " + usuario.getNome() +
                        " | Matrícula: " + usuario.getMatricula() +
                        " | Tipo: " + usuario.getTipo());
            }
        }
        pausaVoltarMenu();
    }

    private void pausaVoltarMenu() {
        System.out.print("\nAperte ENTER para voltar ao menu de usuários...");
        scanner.nextLine();
    }

    public void gerenciarAmbientes() {
        System.out.print("\n");
        System.out.println("------------------------------------------------------");
        System.out.println("================ GESTÃO DOS AMBIENTES ================");
        System.out.println("------------------------------------------------------");
        System.out.println("- 1 = Adicionar um novo ambiente                     -");
        System.out.println("- 2 = Remover um ambiente                            -");
        System.out.println("- 3 = Atualizar o registro de um ambiente            -");
        System.out.println("- 4 = Consultar todos os ambientes                   -");
        System.out.println("- 5 = Consultar um ambiente pelo seu nome            -");
        System.out.println("-                                                    -");
        System.out.println("- 0 = Sair do sistema                                -");
        System.out.println("------------------------------------------------------");

        System.out.print("\nEscolha uma opção: ");
    }

    public void gerenciarAgendamentos() {
        System.out.print("\n");
        System.out.println("--------------------------------------------------------");
        System.out.println("================ GESTÃO DE AGENDAMENTOS ================");
        System.out.println("--------------------------------------------------------");
        System.out.println("- 1 = Adicionar um novo agendamento                    -");
        System.out.println("- 2 = Cancelar um agendamento                          -");
        System.out.println("- 3 = Consultar todos os agendamentos de um usuário    -");
        System.out.println("- 4 = Consultar todos os agendamentos de um ambiente   -");
        System.out.println("- 5 = Consultar os agendamentos futuros de um ambiente -");
        System.out.println("- 6 = Consultar um ambiente pelo seu nome              -");
        System.out.println("-                                                      -");
        System.out.println("- 0 = Sair do sistema                                  -");
        System.out.println("--------------------------------------------------------");

        System.out.print("\nEscolha uma opção: ");
    }

    public void opcaoInvalida() {
        System.out.println(this.YELLOW + "\nOpção inválida" + this.RESET);
    }
}