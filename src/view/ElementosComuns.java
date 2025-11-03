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

    public void impressaoHorario() {
        System.out.print("\n");
		System.out.println("-------------------------------------------------");
        System.out.println("= TABELA DE HORÁRIOS QUE PODEM SER SELECIONADOS =");
        System.out.println("-------------------------------------------------");
        System.out.println("- 07:00:00                                      -");
        System.out.println("- 08:00:00                                      -");
        System.out.println("- 09:00:00                                      -");
        System.out.println("- 10:00:00                                      -");
        System.out.println("- 11:00:00                                      -");
        System.out.println("- 12:00:00                                      -");
        System.out.println("- 13:00:00                                      -");
        System.out.println("- 14:00:00                                      -");
        System.out.println("- 15:00:00                                      -");
        System.out.println("- 16:00:00                                      -");
        System.out.println("- 17:00:00                                      -");
        System.out.println("- 18:00:00                                      -");
        System.out.println("- 19:00:00                                      -");
        System.out.println("- 20:00:00                                      -");
        System.out.println("- 21:00:00                                      -");
        System.out.println("- 22:00:00                                      -");
        System.out.println("-------------------------------------------------");
    }
}
