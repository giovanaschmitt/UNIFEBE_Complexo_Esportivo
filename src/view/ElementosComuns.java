package view;

public class ElementosComuns {
    // Códigos ANSI
    final String RESET = "\u001B[0m";
    final String RED = "\u001B[31m";
    final String GREEN = "\u001B[32m";
    final String YELLOW = "\u001B[33m";

    public void opcaoInvalida() {
        System.out.println(this.YELLOW + "\nOpção inválida" + this.RESET);
    }

    public void semDadoEncontrado() {
        System.out.println(this.YELLOW + "\nNenhum dado foi encontrado" + this.RESET);
    }

}
