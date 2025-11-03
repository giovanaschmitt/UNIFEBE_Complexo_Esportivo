import controller.ValidarLogin;
import model.dao.*;
import view.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

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
                                    new ElementosComuns().opcaoInvalida();
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
                                    new ElementosComuns().opcaoInvalida();
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
        ArrayList<Ambientes> listaAmbientes;
        AmbientesDAOImpl consultas = new AmbientesDAOImpl();

        do {
            new TelaUsuario().consultarAmbientes();
            this.opcao = this.leia.nextInt();

            switch (this.opcao) {
                case 1: // Consultar todos os ambientes
                    System.out.println(this.YELLOW + "\nConsultando ambientes..." + this.RESET);

                    listaAmbientes = consultas.consultarAmbientes();

                    if (!listaAmbientes.isEmpty()) {
                        System.out.println("\nAmbiente(s) encontrados:");
                        for (int i = 0; i < listaAmbientes.size(); i++) {
                            System.out.print("\n");
                            System.out.println("Ambiente: " + listaAmbientes.get(i).getNome_ambiente());
                            System.out.println("Descrição: " + listaAmbientes.get(i).getDescricao());
                        }
                    } else {
                        new ElementosComuns().semDadoEncontrado();
                    }
                    break;

                case 2: // Pesquisar um ambiente
                    System.out.print("\nDigite o nome do ambiente para a pesquisa: ");
                    String nomeTmp = leia.next();

                    System.out.println(this.YELLOW + "\nConsultando ambientes..." + this.RESET);

                    listaAmbientes = consultas.buscarAmbientePorNome(nomeTmp);

                    if (!listaAmbientes.isEmpty()) {
                        System.out.println("\nAmbiente(s) encontrados:");
                        for (int i = 0; i < listaAmbientes.size(); i++) {
                            System.out.print("\n");
                            System.out.println("Ambiente: " + listaAmbientes.get(i).getNome_ambiente());
                            System.out.println("Descrição: " + listaAmbientes.get(i).getDescricao());
                        }
                    } else {
                        new ElementosComuns().semDadoEncontrado();
                    }
                    break;

                case 0:
                    this.replay = 0;
                    break;

                default:
                    new ElementosComuns().opcaoInvalida();
                    break;
            }

        } while (this.replay == 1);
    }

    public void agendamentoDeAmbientes(Usuario usuario) {
        // Atributos
        this.replay = 1;
        this.opcao = 0;
        int opcao_ambiente;
        int dia;
        int mes;
        int ano;
        String hora_inicio;
        String hora_fim;
        AgendamentosDAOImpl consulta = new AgendamentosDAOImpl();
        AmbientesDAOImpl consultaAmbiente = new AmbientesDAOImpl();
        Ambientes ambiente;
        ArrayList<Ambientes> listaAmbientes;
        ArrayList<Agendamentos> listaAgendamentos;

        do {
            new TelaUsuario().agendamentoDeAmbientes();
            this.opcao = this.leia.nextInt();

            switch (this.opcao) {
                case 1: // Agendar um ambiente
                    System.out.println("Agendar um ambiente\n");


                    System.out.println("---------------------------");
                    System.out.println("= REALIZAR UM AGENDAMENTO =");
                    System.out.println("---------------------------");

                    System.out.println(this.YELLOW + "\nConsultando ambientes..." + this.RESET);
                    System.out.print("\n");
                    System.out.println("---------------------------");
                    System.out.println("-- Ambientes disponíveis --");
                    System.out.println("---------------------------");

                    listaAmbientes = consultaAmbiente.consultarAmbientes();

                    if (!listaAmbientes.isEmpty()) {
                        for (int i = 0; i < listaAmbientes.size(); i++) {
                            System.out.print("\n");
                            System.out.println("- " + (i+1) + " = Ambiente: " + listaAmbientes.get(i).getNome_ambiente());
                        }
                    } else {
                        new ElementosComuns().semDadoEncontrado();
                    }


                    while (true) {
                        System.out.print("\nEscolha um ambiente: ");
                        opcao_ambiente = this.leia.nextInt();
                        if (opcao_ambiente > 0 && opcao_ambiente <= listaAmbientes.size()) {
                            ambiente = listaAmbientes.get(opcao_ambiente - 1);
                            break;
                        }
                        System.out.println(this.YELLOW + "\nOpção inválida!\n" + this.RESET);
                    }

                    while (true) {
                        System.out.print("Digite o dia: ");
                        dia = this.leia.nextInt();
                        if (dia > 0 && dia < 30) {
                            break;
                        }
                        System.out.println(this.YELLOW + "\nDia inválido!\n" + this.RESET);
                    }

                    while (true) {
                        System.out.print("Digite o mês: ");
                        mes = this.leia.nextInt();
                        if (mes >= 1 && mes <= 12) {
                            break;
                        }
                        System.out.println(this.YELLOW + "\nMês inválido!\n" + this.RESET);
                    }

                    while (true) {
                        System.out.print("Digite o ano: ");
                        ano = this.leia.nextInt();
                        if (ano >= 2025) {
                            break;
                        }
                        System.out.println(this.YELLOW + "\nAno inválido!\n" + this.RESET);
                    }

                    new ElementosComuns().impressaoHorario();
                    System.out.print("\n");
                    System.out.print("Escolha uma hora de início: ");
                    hora_inicio = leia.next();
                    System.out.print("Escolha uma hora de término: ");
                    hora_fim = leia.next();

                    String inicio_solicitado = String.format("%02d", dia) + "/" + String.format("%02d", mes) + "/" + ano + " " + hora_inicio;
                    String fim_solicitado = String.format("%02d", dia) + "/" + String.format("%02d", mes) + "/" + ano + " " + hora_fim;

                    System.out.print("\n");
                    System.out.println("Ambiente solicitado: " + ambiente.getNome_ambiente());
                    System.out.println("Inicio solicitado: " + inicio_solicitado);
                    System.out.println("Fim solicitado: " + fim_solicitado);

                    System.out.println(this.YELLOW + "\nVerificando disponibilidade de agendamento..." + this.RESET);

                    if (consulta.solicitaHorario(inicio_solicitado, fim_solicitado, ambiente.getId_AMBIENTES())) {
                        // Montagem do agendamento
                        Agendamentos agendamento = new Agendamentos();
                        agendamento.setAMBIENTE_ID_AMBIENTES(ambiente.getId_AMBIENTES());
                        agendamento.setUSUARIO_ID_USUARIO(usuario.getId_USUARIO());
                        agendamento.setData_Hora_Inicio(inicio_solicitado);
                        agendamento.setData_Hora_Fim(fim_solicitado);
                        agendamento.setData_Hora_Agendamento(LocalDateTime.now().format(formatter));
                        agendamento.setStatus_agendamento('V');


                        consulta.inserirAgendamento(agendamento);
                        System.out.println(this.GREEN + "\nAgendamento realizado!" + this.RESET);
                    } else {
                        System.out.println(this.RED + "\nAgendamento indisponível!" + this.RESET);
                    }
                    break;

                case 2: // Cancelar um agendamento
                    System.out.println("---------------------------");
                    System.out.println("= CANCELAR UM AGENDAMENTO =");
                    System.out.println("---------------------------");

                    System.out.println(this.YELLOW + "\nConsultando meus agendamentos..." + this.RESET);
                    System.out.print("\n");
                    System.out.println("-----------------------");
                    System.out.println("-- Meus agendamentos --");
                    System.out.println("-----------------------");

                    listaAgendamentos = consulta.consultarAgendamentosUsuarioFuturo(usuario.getId_USUARIO());
                    listaAmbientes = consultaAmbiente.consultarAmbientes();

                    if (!listaAgendamentos.isEmpty()) {
                        System.out.print("\n");
                        for (int i = 0; i < listaAgendamentos.size(); i++) {

                            String aux_nome_ambiente = "";
                            for (int j = 0; j < listaAmbientes.size(); j++) {
                                Ambientes aux_ambiente = listaAmbientes.get(j);
                                if (aux_ambiente.getId_AMBIENTES() == listaAgendamentos.get(i).getAMBIENTE_ID_AMBIENTES()) {
                                    aux_nome_ambiente = aux_ambiente.getNome_ambiente();
                                    break;
                                }
                            }

                            System.out.println("--------------------------------------------------------------");
                            System.out.println("- Opção = " + (i + 1));
                            System.out.println("- Ambiente: " + aux_nome_ambiente);
                            System.out.println("- Data e hora de início: " + listaAgendamentos.get(i).getData_Hora_Inicio());
                            System.out.println("- Data e hora de fim: " + listaAgendamentos.get(i).getData_Hora_Fim());
                            System.out.println("- Data e hora do agendamento: " + listaAgendamentos.get(i).getData_Hora_Agendamento());
                            if (listaAgendamentos.get(i).getStatus_agendamento() == 'V') {
                                System.out.println("- " + this.GREEN + "Agendamento válido" + this.RESET);
                            } else {
                                System.out.println("- " + this.YELLOW + "Agendamento inválido" + this.RESET);
                            }
                        }
                        System.out.println("--------------------------------------------------------------");
                        System.out.print("\n");
                        System.out.println(this.YELLOW + "Observação: Não é possível cancelar um agendamento que esteja inválido" + this.RESET);
                        System.out.print("\nEscolha um agendamento para cancelar: ");
                        int tmp = this.leia.nextInt();

                        if (tmp > 0 && tmp <= listaAgendamentos.size()) {
                            consulta.cancelarAgendamento(listaAgendamentos.get(tmp - 1));
                        } else {
                            new ElementosComuns().opcaoInvalida();
                        }

                    } else {
                        new ElementosComuns().semDadoEncontrado();
                    }

                    break;

                case 3: // Consultar todos os meus agendamentos
                    System.out.println("-----------------------------------");
                    System.out.println("= VERIFICAR TODOS OS AGENDAMENTOS =");
                    System.out.println("-----------------------------------");

                    System.out.println(this.YELLOW + "\nConsultando todos os meus agendamentos..." + this.RESET);
                    System.out.print("\n");
                    System.out.println("--------------------------------");
                    System.out.println("-- Todos os meus agendamentos --");
                    System.out.println("--------------------------------");

                    listaAgendamentos = consulta.consultarAgendamentosUsuario(usuario.getId_USUARIO());
                    listaAmbientes = consultaAmbiente.consultarAmbientes();

                    if (!listaAgendamentos.isEmpty()) {
                        System.out.print("\n");
                        for (int i = 0; i < listaAgendamentos.size(); i++) {

                            String aux_nome_ambiente = "";
                            for (int j = 0; j < listaAmbientes.size(); j++) {
                                Ambientes aux_ambiente = listaAmbientes.get(j);
                                if (aux_ambiente.getId_AMBIENTES() == listaAgendamentos.get(i).getAMBIENTE_ID_AMBIENTES()) {
                                    aux_nome_ambiente = aux_ambiente.getNome_ambiente();
                                    break;
                                }
                            }

                            System.out.println("--------------------------------------------------------------");
                            System.out.println("- Ambiente: " + aux_nome_ambiente);
                            System.out.println("- Data e hora de início: " + listaAgendamentos.get(i).getData_Hora_Inicio());
                            System.out.println("- Data e hora de fim: " + listaAgendamentos.get(i).getData_Hora_Fim());
                            System.out.println("- Data e hora do agendamento: " + listaAgendamentos.get(i).getData_Hora_Agendamento());
                            if (listaAgendamentos.get(i).getStatus_agendamento() == 'V') {
                                System.out.println("- " + this.GREEN + "Agendamento válido" + this.RESET);
                            } else {
                                System.out.println("- " + this.YELLOW + "Agendamento inválido" + this.RESET);
                            }
                        }
                        System.out.println("--------------------------------------------------------------");
                        System.out.print("\n");
                        System.out.println(this.YELLOW + "Observação: Os registros inválidos foram cancelados" + this.RESET);
                    } else {
                        new ElementosComuns().semDadoEncontrado();
                    }


                    break;

                case 4: // Consultar os horários já ocupados de um ambiente
                    System.out.println("----------------------------------------------------------");
                    System.out.println("= CONSULTAR TODOS OS AGENDAMENTOS FUTUROS DE UM AMBIENTE =");
                    System.out.println("----------------------------------------------------------");

                    System.out.println(this.YELLOW + "\nConsultando ambientes..." + this.RESET);
                    System.out.print("\n");
                    System.out.println("---------------------------");
                    System.out.println("-- Ambientes disponíveis --");
                    System.out.println("---------------------------");

                    listaAmbientes = consultaAmbiente.consultarAmbientes();

                    if (!listaAmbientes.isEmpty()) {
                        for (int i = 0; i < listaAmbientes.size(); i++) {
                            System.out.print("\n");
                            System.out.println("- " + (i+1) + " = Ambiente: " + listaAmbientes.get(i).getNome_ambiente());
                        }
                    } else {
                        new ElementosComuns().semDadoEncontrado();
                    }

                    while (true) {
                        System.out.print("\nEscolha um ambiente: ");
                        opcao_ambiente = this.leia.nextInt();
                        if (opcao_ambiente > 0 && opcao_ambiente <= listaAmbientes.size()) {
                            ambiente = listaAmbientes.get(opcao_ambiente - 1);
                            break;
                        }
                        System.out.println(this.YELLOW + "\nOpção inválida!\n" + this.RESET);
                    }
                    listaAgendamentos = consulta.consultarAgendamentosAmbienteFuturos(ambiente.getId_AMBIENTES());

                    System.out.print(this.YELLOW + "\nConsultando agendamentos futuros do ambiente...\n" + this.RESET);

                    if (!listaAgendamentos.isEmpty()) {
                        System.out.print("\n");
                        for (int i = 0; i < listaAgendamentos.size(); i++) {

                            System.out.println("--------------------------------------------------------------");
                            System.out.println("- Data e hora de início: " + listaAgendamentos.get(i).getData_Hora_Inicio());
                            System.out.println("- Data e hora de fim: " + listaAgendamentos.get(i).getData_Hora_Fim());
                            System.out.println("- Data e hora do agendamento: " + listaAgendamentos.get(i).getData_Hora_Agendamento());
                            if (listaAgendamentos.get(i).getStatus_agendamento() == 'V') {
                                System.out.println("- " + this.GREEN + "Agendamento válido" + this.RESET);
                            } else {
                                System.out.println("- " + this.YELLOW + "Agendamento inválido" + this.RESET);
                            }
                        }
                        System.out.println("--------------------------------------------------------------");
                        System.out.print("\n");
                        System.out.println(this.YELLOW + "Observação: Os agendamentos inválidos foram cancelados" + this.RESET);

                    } else {
                        new ElementosComuns().semDadoEncontrado();
                    }
                    break;

                case 0:
                    this.replay = 0;
                    break;

                default:
                    new ElementosComuns().opcaoInvalida();
                    break;
            }

        } while (this.replay == 1);
    }

    public static void main(String[] args) {
        new Main().run();
    }
}