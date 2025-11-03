package view;

import model.dao.*;
import model.dao.UsuarioService;
import model.service.AmbienteService;
import model.dao.AgendamentosService;
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
    private AmbienteService ambienteService = new AmbienteService();
    private AgendamentosService agendamentoService = new AgendamentosService();

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

    // ----------- GERÊNCIA DE USUÁRIOS -----------
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
        System.out.print("\nAperte ENTER para voltar ao menu...");
        scanner.nextLine();
    }

    // ----------- GERÊNCIA DE AMBIENTES -----------
    public void gerenciarAmbientes() {
        int opcao;
        do {
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
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    adicionarAmbiente();
                    break;
                case 2:
                    removerAmbiente();
                    break;
                case 3:
                    atualizarAmbiente();
                    break;
                case 4:
                    listarAmbientes();
                    break;
                case 5:
                    buscarAmbientePorNome();
                    break;
                case 0:
                    System.out.println("Saindo do menu de ambientes...");
                    break;
                default:
                    opcaoInvalida();
            }
        } while (opcao != 0);
    }

    private void adicionarAmbiente() {
        System.out.print("Nome do ambiente: ");
        String nome = scanner.nextLine();
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        if (ambienteService.adicionarAmbiente(nome, descricao))
            System.out.println(GREEN+"Ambiente adicionado com sucesso!"+RESET);
        else
            System.out.println(RED+"Erro ao adicionar ambiente."+RESET);
        pausaVoltarMenu();
    }

    private void removerAmbiente() {
        System.out.print("Informe o ID do ambiente a ser removido: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        if (ambienteService.removerAmbiente(id))
            System.out.println(GREEN+"Ambiente removido com sucesso!"+RESET);
        else
            System.out.println(RED+"Ambiente não encontrado ou erro ao remover."+RESET);
        pausaVoltarMenu();
    }

    private void atualizarAmbiente() {
        System.out.print("Informe o ID do ambiente a ser atualizado: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Novo nome do ambiente: ");
        String nome = scanner.nextLine();
        System.out.print("Nova descrição: ");
        String descricao = scanner.nextLine();
        if (ambienteService.atualizarAmbiente(id, nome, descricao))
            System.out.println(GREEN+"Ambiente atualizado com sucesso!"+RESET);
        else
            System.out.println(RED+"Ambiente não encontrado ou erro na atualização."+RESET);
        pausaVoltarMenu();
    }

    private void listarAmbientes() {
        ArrayList<Ambientes> lista = ambienteService.listarAmbientes();
        if (lista.isEmpty()) {
            System.out.println(YELLOW+"Nenhum ambiente cadastrado."+RESET);
        } else {
            for (Ambientes ambiente : lista) {
                System.out.println("ID: " + ambiente.getId_AMBIENTES() +
                        " | Nome: " + ambiente.getNome_ambiente() +
                        " | Descrição: " + ambiente.getDescricao());
            }
        }
        pausaVoltarMenu();
    }

    private void buscarAmbientePorNome() {
        System.out.print("Informe o nome (ou parte) do ambiente: ");
        String nome = scanner.nextLine();
        ArrayList<Ambientes> lista = ambienteService.buscarPorNome(nome);
        if (lista.isEmpty()) {
            System.out.println(YELLOW+"Nenhum ambiente encontrado."+RESET);
        } else {
            for (Ambientes ambiente : lista) {
                System.out.println("ID: " + ambiente.getId_AMBIENTES() +
                        " | Nome: " + ambiente.getNome_ambiente() +
                        " | Descrição: " + ambiente.getDescricao());
            }
        }
        pausaVoltarMenu();
    }

    // ----------- GERÊNCIA DE AGENDAMENTOS -----------
    public void gerenciarAgendamentos() {
        int opcao;
        do {
            System.out.print("\n");
            System.out.println("--------------------------------------------------------");
            System.out.println("================ GESTÃO DE AGENDAMENTOS ================");
            System.out.println("--------------------------------------------------------");
            System.out.println("- 1 = Adicionar um novo agendamento                    -");
            System.out.println("- 2 = Cancelar um agendamento                          -");
            System.out.println("- 3 = Consultar todos os agendamentos de um usuário    -");
            System.out.println("- 4 = Consultar todos os agendamentos de um ambiente   -");
            System.out.println("- 5 = Consultar agendamentos futuros de um ambiente    -");
            System.out.println("-                                                      -");
            System.out.println("- 0 = Voltar                                           -");
            System.out.println("--------------------------------------------------------");

            System.out.print("\nEscolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch(opcao) {
                case 1:
                    adicionarAgendamento();
                    break;
                case 2:
                    cancelarAgendamento();
                    break;
                case 3:
                    consultarAgendamentosUsuario();
                    break;
                case 4:
                    consultarAgendamentosAmbiente();
                    break;
                case 5:
                    consultarAgendamentosFuturosAmbiente();
                    break;
                case 0:
                    System.out.println("Saindo do menu de agendamentos...");
                    break;
                default:
                    opcaoInvalida();
            }
        } while (opcao != 0);
    }

    private void adicionarAgendamento() {
        Agendamentos a = new Agendamentos();
        System.out.print("ID do ambiente: ");
        a.setAMBIENTE_ID_AMBIENTES(scanner.nextInt());
        System.out.print("ID do usuário: ");
        a.setUSUARIO_ID_USUARIO(scanner.nextInt());
        scanner.nextLine();
        System.out.print("Data e hora início (yyyy-MM-dd HH:mm): ");
        a.setData_Hora_Inicio(scanner.nextLine());
        System.out.print("Data e hora fim (yyyy-MM-dd HH:mm): ");
        a.setData_Hora_Fim(scanner.nextLine());
        a.setStatus_agendamento('A');
        boolean ok = agendamentoService.adicionarAgendamento(a);
        if (ok)
            System.out.println(GREEN + "Agendamento realizado com sucesso!" + RESET);
        else
            System.out.println(RED + "Erro ao criar agendamento!" + RESET);
        pausaVoltarMenu();
    }

    private void cancelarAgendamento() {
        System.out.print("ID do agendamento a cancelar: ");
        int idAgendamento = scanner.nextInt();
        scanner.nextLine();
        boolean ok = agendamentoService.cancelarAgendamento(idAgendamento);
        if (ok)
            System.out.println(GREEN + "Agendamento cancelado com sucesso!" + RESET);
        else
            System.out.println(RED + "Erro ao cancelar agendamento!" + RESET);
        pausaVoltarMenu();
    }

    private void consultarAgendamentosUsuario() {
        System.out.print("Informe a matrícula do usuário: ");
        int matricula = scanner.nextInt();
        scanner.nextLine();

        // Busca agendamentos pela matrícula
        ArrayList<Agendamentos> lista = agendamentoService.consultarAgendamentosUsuario(matricula);
        if (lista.isEmpty()) {
            System.out.println(YELLOW + "Nenhum agendamento encontrado para esse usuário." + RESET);
        } else {
            for (Agendamentos a : lista) {
                System.out.println("ID: " + a.getID_AGENDAMENTOS() +
                        " | Ambiente: " + a.getAMBIENTE_ID_AMBIENTES() +
                        " | Início: " + a.getData_Hora_Inicio() +
                        " | Fim: " + a.getData_Hora_Fim() +
                        " | Status: " + a.getStatus_agendamento());
            }
        }
        pausaVoltarMenu();
    }

    private void consultarAgendamentosAmbiente() {
        System.out.print("ID do ambiente: ");
        int idAmbiente = scanner.nextInt();
        scanner.nextLine();
        ArrayList<Agendamentos> lista = agendamentoService.consultarAgendamentosAmbiente(idAmbiente);
        if (lista.isEmpty()) {
            System.out.println(YELLOW + "Nenhum agendamento encontrado para esse ambiente." + RESET);
        } else {
            for (Agendamentos a : lista) {
                System.out.println("ID: " + a.getID_AGENDAMENTOS() +
                        " | Usuário: " + a.getUSUARIO_ID_USUARIO() +
                        " | Início: " + a.getData_Hora_Inicio() +
                        " | Fim: " + a.getData_Hora_Fim() +
                        " | Status: " + a.getStatus_agendamento());
            }
        }
        pausaVoltarMenu();
    }

    private void consultarAgendamentosFuturosAmbiente() {
        System.out.print("ID do ambiente: ");
        int idAmbiente = scanner.nextInt();
        scanner.nextLine();
        ArrayList<Agendamentos> lista = agendamentoService.consultarAgendamentosFuturosAmbiente(idAmbiente);
        if (lista.isEmpty()) {
            System.out.println(YELLOW + "Nenhum agendamento futuro encontrado para esse ambiente." + RESET);
        } else {
            for (Agendamentos a : lista) {
                System.out.println("ID: " + a.getID_AGENDAMENTOS() +
                        " | Usuário: " + a.getUSUARIO_ID_USUARIO() +
                        " | Início: " + a.getData_Hora_Inicio() +
                        " | Fim: " + a.getData_Hora_Fim() +
                        " | Status: " + a.getStatus_agendamento());
            }
        }
        pausaVoltarMenu();
    }

    private void opcaoInvalida() {
        System.out.println(YELLOW + "\nOpção inválida" + RESET);
    }
}