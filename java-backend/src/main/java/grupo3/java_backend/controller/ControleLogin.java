package grupo3.java_backend.controller;

import java.sql.SQLException;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import grupo3.java_backend.dao.PessoaDAO;
import grupo3.java_backend.model.Pessoa;

public class ControleLogin {
    private PessoaDAO pessoaDAO = new PessoaDAO();
    private Scanner scanner = new Scanner(System.in);

    public Pessoa login() {
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        try {
            for (Pessoa p : pessoaDAO.findAll()) {
                if (p.getEmail().equals(email) && p.getSenha().equals(senha)) {
                    System.out.println("Login realizado com sucesso!\n");
                    return p;
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar usuário: " + e.getMessage());
        }

        System.out.println("Credenciais inválidas.\n");
        return null;
    }

    public Pessoa cadastro() {
        Pessoa p = new Pessoa();
        System.out.print("Nome: "); p.setNome(scanner.nextLine());
        System.out.print("Email: "); p.setEmail(scanner.nextLine());
        System.out.print("CPF: "); p.setCpf(scanner.nextLine());
        System.out.print("Telefone: "); p.setTelefone(scanner.nextLine());
        System.out.print("Data de Nascimento (DDMMYYYY): "); 
        String dataStr = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        p.setData_nascimento(LocalDate.parse(dataStr, formatter));
        System.out.print("Senha: "); p.setSenha(scanner.nextLine());
        p.setTipo_usuario("CLIENTE");

        try {
            pessoaDAO.insert(p);
            System.out.println("Cadastro realizado! ID: " + p.getId_pessoa() + "\n");
            return p;
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar: " + e.getMessage());
            return null;
        }
    }
}
