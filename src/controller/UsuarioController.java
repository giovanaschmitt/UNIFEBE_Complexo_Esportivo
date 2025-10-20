package controller;

import model.dao.Usuario;
import model.dao.UsuarioDAOImpl;
import java.util.Scanner;
import java.util.ArrayList;

public class UsuarioController {

    private UsuarioDAOImpl usuarioDAO;
    private Scanner scanner;

    public UsuarioController(String IP, String database, String user, String senha) {
        this.usuarioDAO = new UsuarioDAOImpl(IP, database, user, senha);
        this.scanner = new Scanner(System.in);

    }

    public void exibirMenu() {
        int opcao = 0;
        while (opcao != 3) {
            System.out.println("\n=== SISTEMA DE CADASTRO DE USUÁRIOS ===");
            System.out.println("1 - Cadastrar novo usuário");
            System.out.println("2 - Listar usuários");
            System.out.println("3 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarUsuario();
                    break;
                case 2:
                    listarUsuarios();
                    break;

                    case 3:
                    System.out.println("Encerrando...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
        scanner.close();
    }


    private void cadastrarUsuario() {
        System.out.println("\n--- CADASTRAR NOVO USUÁRIO ---");

        Usuario novoUsuario = new Usuario();


        System.out.print("Nome: ");
        novoUsuario.setNome(scanner.nextLine());

        System.out.print("Senha: ");
        novoUsuario.setSenha(scanner.nextLine());

        System.out.print("Matrícula: ");
        novoUsuario.setMatricula(scanner.nextInt());
        scanner.nextLine();

        System.out.print("Tipo (A=Admin, U=Usuário): ");
        String tipo = scanner.nextLine();
        if (!tipo.isEmpty()) {
            novoUsuario.setTipo(tipo.toUpperCase().charAt(0));
        }

        // INSERIR NO BANCO
        int resultado = usuarioDAO.inserirUsuario(novoUsuario);

        if (resultado > 0) {
            System.out.println("Usuário cadastrado com sucesso!");
        } else {
            System.out.println("Erro ao cadastrar usuário!");
        }
    }


    private void listarUsuarios() {
        System.out.println("\n--- USUÁRIOS CADASTRADOS ---");
        ArrayList<Usuario> usuarios = usuarioDAO.selectUsuarios();

        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
        } else {
            System.out.println("Total de usuários: " + usuarios.size());
            for (Usuario u : usuarios) {

                System.out.println("Nome: " + u.getNome() +
                        " | Matrícula: " + u.getMatricula() +

                        " | Tipo: " + u.getTipo());
            }
        }
    }
}