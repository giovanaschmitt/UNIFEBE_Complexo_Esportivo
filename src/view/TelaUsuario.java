package view;

public class TelaUsuario {
    // Códigos ANSI
    final String RESET = "\u001B[0m";
    final String RED = "\u001B[31m";
    final String GREEN = "\u001B[32m";
    final String YELLOW = "\u001B[33m";

    public TelaUsuario() {
    }

    public void sayHello(String nome) {
        System.out.println("Olá! Bem vindo(a) " + nome + "!");
        System.out.println(this.YELLOW + "\nVOCÊ ESTÁ LOGADO COM UM USUÁRIO COMUM\n" + this.RESET);
    }

    public void opcoesAcoes() {
        System.out.println("------------------------------------------------------");
        System.out.println("=========== ESCOLHA UMA AÇÃO PARA REALIZAR ===========");
        System.out.println("------------------------------------------------------");
        System.out.println("- 1 = Consultar ambientes                            -");
        System.out.println("- 2 = Agendamentos                                   -");
        System.out.println("-                                                    -");
        System.out.println("- 0 = Sair do sistema                                -");
        System.out.println("------------------------------------------------------");

        System.out.print("\nEscolha uma opção: ");
    }

        public void consultarAmbientes() {
        System.out.print("\n");
        System.out.println("------------------------------------------------------");
        System.out.println("=============== CONSULTA DOS AMBIENTES ===============");
        System.out.println("------------------------------------------------------");
        System.out.println("- 1 = Consultar todos os ambientes                   -");
        System.out.println("- 2 = Consultar um ambiente pelo seu nome            -");
        System.out.println("-                                                    -");
        System.out.println("- 0 = Sair do sistema                                -");
        System.out.println("------------------------------------------------------");

        System.out.print("\nEscolha uma opção: ");
    }

    public void agendamentoDeAmbientes() {
        System.out.print("\n");
        System.out.println("--------------------------------------------------------");
        System.out.println("============== AGENDAMENTOS DOS AMBIENTES ==============");
        System.out.println("--------------------------------------------------------");
        System.out.println("- 1 = Pesquisar ambientes                              -");
        System.out.println("- 2 = Adicionar um novo agendamento                    -");
        System.out.println("- 3 = Cancelar um agendamento                          -");
        System.out.println("- 4 = Consultar todos os meus agendamentos             -");
        System.out.println("- 5 = Consultar horários já ocupados de um ambiente    -");
        System.out.println("-                                                      -");
        System.out.println("- 0 = Sair do sistema                                  -");
        System.out.println("--------------------------------------------------------");

        System.out.print("\nEscolha uma opção: ");
    }

}
