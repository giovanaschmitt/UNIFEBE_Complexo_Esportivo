package view;

public class TelaAdmin {

    // Códigos ANSI
    final String RESET = "\u001B[0m";
    final String RED = "\u001B[31m";
    final String GREEN = "\u001B[32m";
    final String YELLOW = "\u001B[33m";

    public TelaAdmin() {
    }
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
