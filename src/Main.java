import controller.ValidarLogin;
import model.dao.AgendamentosDAOImpl;
import model.dao.Usuario;
import model.dao.UsuarioDAOImpl;
import view.TelaAdmin;
import view.TelaUsuario;

import java.util.ArrayList;
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
                                    new Main().gerenciarUsuariosMenu(usuario);
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

    public void gerenciarUsuariosMenu(Usuario usuarioLogado) {
        // Atributos
        this.replay = 1;
        this.opcao = 0;
        UsuarioDAOImpl usuarioDAO = new UsuarioDAOImpl();

        do {
            new TelaAdmin().gerenciarUsuarios();
            this.opcao = this.leia.nextInt();

            switch (this.opcao) {
                case 1: // Adicionar um novo usuário
                    System.out.println("\n=== ADICIONAR NOVO USUÁRIO ===");
                    System.out.print("Digite o nome do usuário: ");
                    this.leia.nextLine(); // Limpa o buffer
                    String nome = this.leia.nextLine();
                    System.out.print("Digite a matrícula: ");
                    int matricula = this.leia.nextInt();
                    System.out.print("Digite a senha: ");
                    String senha = this.leia.next();
                    System.out.print("Digite o tipo (A = Administrativo, C = Comum): ");
                    char tipo = this.leia.next().toUpperCase().charAt(0);

                    // Validar tipo
                    if (tipo != 'A' && tipo != 'C') {
                        System.out.println(this.RED + "Tipo inválido! Use 'A' para Administrativo ou 'C' para Comum." + this.RESET);
                        break;
                    }

                    Usuario novoUsuario = new Usuario();
                    novoUsuario.setNome(nome);
                    novoUsuario.setMatricula(matricula);
                    novoUsuario.setSenha(senha);
                    novoUsuario.setTipo(tipo);

                    int resultado = usuarioDAO.inserirUsuario(novoUsuario);
                    if (resultado > 0) {
                        System.out.println(this.GREEN + "Usuário cadastrado com sucesso!" + this.RESET);
                    } else {
                        System.out.println(this.RED + "Erro ao cadastrar usuário." + this.RESET);
                    }
                    break;

                case 2: // Remover um usuário através da matrícula
                    System.out.println("\n=== REMOVER USUÁRIO ===");
                    System.out.print("Digite a matrícula do usuário a ser removido: ");
                    int matriculaRemover = this.leia.nextInt();

                    // Buscar o usuário para confirmar
                    Usuario usuarioRemover = usuarioDAO.buscarUsuarioPorMatricula(matriculaRemover);
                    if (usuarioRemover != null) {
                        System.out.println("Usuário encontrado: " + usuarioRemover.getNome() + " - Matrícula: " + usuarioRemover.getMatricula());
                        System.out.print("Confirma a remoção? (S/N): ");
                        char confirmacao = this.leia.next().toUpperCase().charAt(0);

                        if (confirmacao == 'S') {
                            Usuario usuarioParaRemover = new Usuario();
                            usuarioParaRemover.setMatricula(matriculaRemover);
                            int resultadoRemocao = usuarioDAO.removerUsuarioPorMaticula(usuarioParaRemover);
                            if (resultadoRemocao > 0) {
                                System.out.println(this.GREEN + "Usuário removido com sucesso!" + this.RESET);
                            } else {
                                System.out.println(this.RED + "Erro ao remover usuário." + this.RESET);
                            }
                        } else {
                            System.out.println(this.YELLOW + "Remoção cancelada." + this.RESET);
                        }
                    } else {
                        System.out.println(this.RED + "Usuário não encontrado." + this.RESET);
                    }
                    break;

                case 3: // Buscar um usuário através da matrícula
                    System.out.println("\n=== BUSCAR USUÁRIO POR MATRÍCULA ===");
                    System.out.print("Digite a matrícula: ");
                    int matriculaBusca = this.leia.nextInt();

                    Usuario usuarioEncontrado = usuarioDAO.buscarUsuarioPorMatricula(matriculaBusca);
                    if (usuarioEncontrado != null) {
                        System.out.println("\n" + this.GREEN + "Usuário encontrado:" + this.RESET);
                        System.out.println("--------------------------------------");
                        System.out.println("Nome: " + usuarioEncontrado.getNome());
                        System.out.println("Matrícula: " + usuarioEncontrado.getMatricula());
                        System.out.println("Tipo: " + (usuarioEncontrado.getTipo() == 'A' ? "Administrativo" : "Comum"));
                        System.out.println("--------------------------------------");
                    } else {
                        System.out.println(this.RED + "Usuário não encontrado." + this.RESET);
                    }
                    break;

                case 4: // Buscar um usuário através do nome
                    System.out.println("\n=== BUSCAR USUÁRIO POR NOME ===");
                    System.out.print("Digite o nome (ou parte do nome): ");
                    this.leia.nextLine(); // Limpa o buffer
                    String nomeBusca = this.leia.nextLine();

                    ArrayList<Usuario> usuariosEncontrados = usuarioDAO.buscarUsuarioPorNome(nomeBusca);
                    if (!usuariosEncontrados.isEmpty()) {
                        System.out.println("\n" + this.GREEN + "Usuários encontrados: " + usuariosEncontrados.size() + this.RESET);
                        System.out.println("--------------------------------------");
                        for (Usuario u : usuariosEncontrados) {
                            System.out.println("Nome: " + u.getNome());
                            System.out.println("Matrícula: " + u.getMatricula());
                            System.out.println("Tipo: " + (u.getTipo() == 'A' ? "Administrativo" : "Comum"));
                            System.out.println("--------------------------------------");
                        }
                    } else {
                        System.out.println(this.RED + "Nenhum usuário encontrado com esse nome." + this.RESET);
                    }
                    break;

                case 5: // Consultar todos os usuários administrativos
                    System.out.println("\n=== USUÁRIOS ADMINISTRATIVOS ===");
                    ArrayList<Usuario> usuariosAdmin = usuarioDAO.selectUsuariosPorTipo('A');
                    if (!usuariosAdmin.isEmpty()) {
                        System.out.println(this.GREEN + "Total de usuários administrativos: " + usuariosAdmin.size() + this.RESET);
                        System.out.println("--------------------------------------");
                        for (Usuario u : usuariosAdmin) {
                            System.out.println("Nome: " + u.getNome());
                            System.out.println("Matrícula: " + u.getMatricula());
                            System.out.println("--------------------------------------");
                        }
                    } else {
                        System.out.println(this.RED + "Nenhum usuário administrativo encontrado." + this.RESET);
                    }
                    break;

                case 6: // Consultar todos os usuários comuns
                    System.out.println("\n=== USUÁRIOS COMUNS ===");
                    ArrayList<Usuario> usuariosComuns = usuarioDAO.selectUsuariosPorTipo('C');
                    if (!usuariosComuns.isEmpty()) {
                        System.out.println(this.GREEN + "Total de usuários comuns: " + usuariosComuns.size() + this.RESET);
                        System.out.println("--------------------------------------");
                        for (Usuario u : usuariosComuns) {
                            System.out.println("Nome: " + u.getNome());
                            System.out.println("Matrícula: " + u.getMatricula());
                            System.out.println("--------------------------------------");
                        }
                    } else {
                        System.out.println(this.RED + "Nenhum usuário comum encontrado." + this.RESET);
                    }
                    break;

                case 0:
                    this.replay = 0;
                    break;

                default:
                    new TelaAdmin().opcaoInvalida();
                    break;
            }

        } while (this.replay == 1);
    }





    public static void main(String[] args) {
        new Main().run();
    }
}