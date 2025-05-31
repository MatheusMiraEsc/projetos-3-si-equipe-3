
package ingressart.teatro;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

import ingressart.teatro.controller.ControleLogin;
import ingressart.teatro.controller.MenuCliente;
import ingressart.teatro.controller.MenuTeatro;
import ingressart.teatro.model.Pessoa;

public class App {

     // Método para executar arquivos SQL
    public static void executarScriptsSQL(String[] caminhosArquivos, String url, String user, String password) {
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {

            for (String arquivoSQL : caminhosArquivos) {
                System.out.println("Executando script: " + arquivoSQL);
                String sql = new String(Files.readAllBytes(Paths.get(arquivoSQL)));
                String[] comandos = sql.split(";");
                for (String comando : comandos) {
                    comando = comando.trim();
                    if (!comando.isEmpty()) {
                        stmt.execute(comando);
                    }
                }
            }

            System.out.println("Scripts SQL executados com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {

          // Configure aqui os parâmetros do banco
        String url = "jdbc:postgresql://localhost:5432/ingressart";
        String user = "postgres";     // altere para seu usuário
        String password = "postgres";   // altere para sua senha

        String[] scripts = {
            "src/main/java/ingressart/teatro/database/create_tables.sql",
            "src/main/java/ingressart/teatro/database/schema.sql",
            "src/main/java/ingressart/teatro/database/update_schema.sql"
        };

        // Executa os scripts SQL antes do menu iniciar
        executarScriptsSQL(scripts, url, user, password);
        
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
