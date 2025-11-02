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
        System.out.println("Olá! Bem vindo " + nome + "!");
        System.out.println(this.YELLOW + "\nVOCÊ ESTÁ LOGADO COM UM USUÁRIO COMUM\n" + this.RESET);
    }

    public void opcoesAcoes() {
        System.out.println("------------------------------------------------------");
        System.out.println("=========== ESCOLHA UMA AÇÃO PARA REALIZAR ===========");
        System.out.println("------------------------------------------------------");
        System.out.println("- 1 = Consultar ambientes                            -");
        System.out.println("- 2 = Agendamentos                                   -");
        System.out.println("------------------------------------------------------");

        System.out.print("\nEscolha uma opção: ");
    }

}
