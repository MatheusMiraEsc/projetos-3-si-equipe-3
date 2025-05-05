
package ingressart.teatro;

import ingressart.teatro.controller.ControleLogin;
import ingressart.teatro.controller.MenuCliente;
import ingressart.teatro.controller.MenuTeatro;
import ingressart.teatro.model.Pessoa;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ControleLogin loginCtrl = new ControleLogin();
        MenuCliente menuCliente = new MenuCliente();
        MenuTeatro menuTeatro = new MenuTeatro();

        while (true) {
            System.out.println("=== IngressArt ===");
            System.out.println("1 - Área do Teatro (Admin)");
            System.out.println("2 - Área do Cliente");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            String opc = scanner.nextLine();

            switch (opc) {
                case "1":
                    // Aqui, você pode pedir uma senha fixa ou usar PessoaDAO
                    System.out.println("---- Login Administrador ----");
                    // Se preferir, reutilize ControleLogin mas filtrando por tipo "ADMIN"
                    menuTeatro.iniciar();
                    break;

                case "2":
                    System.out.println("---- Login/Cadastro Cliente ----");
                    System.out.println("1 - Login");
                    System.out.println("2 - Cadastro");
                    System.out.println("3 - Continuar sem cadastro");
                    System.out.print("Opção: ");
                    String sub = scanner.nextLine();
                    Pessoa cliente = null;
                    switch (sub) {
                        case "1":
                            cliente = loginCtrl.login();
                            break;
                        case "2":
                            cliente = loginCtrl.cadastro();
                            break;
                        case "3":
                            // Criamos um objeto “anônimo” só para poder passar algo ao menu
                            cliente = new Pessoa();
                            cliente.setTipo_usuario("ANONIMO");
                            break;
                        default:
                            System.out.println("Opção inválida.");
                    }
                    if (cliente != null) {
                        menuCliente.iniciar(cliente);
                    }
                    break;

                case "0":
                    System.out.println("Saindo... Até mais!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opção inválida.\n");
            }
        }
    }
}
