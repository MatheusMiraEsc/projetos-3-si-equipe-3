package ingressart.teatro.controller;

import ingressart.teatro.dao.PessoaDAO;
import ingressart.teatro.model.Pessoa;
import ingressart.teatro.util.ValidaEntrada;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

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

        System.out.print("Nome: ");
        p.setNome(scanner.nextLine());

        // Email
        while (true) {
            System.out.print("Email: ");
            String email = scanner.nextLine();
            if (ValidaEntrada.emailValido(email)) {
                p.setEmail(email);
                break;
            } else {
                System.out.println("Email inválido. Ex: exemplo@dominio.com");
            }
        }

        // CPF
        while (true) {
            System.out.print("CPF (somente números): ");
            String cpf = scanner.nextLine();
            if (ValidaEntrada.cpfValido(cpf)) {
                p.setCpf(cpf);
                break;
            } else {
                System.out.println("CPF inválido. Deve conter 11 números.");
            }
        }

        // Telefone
        while (true) {
            System.out.print("Telefone (somente números): ");
            String telefone = scanner.nextLine();
            if (ValidaEntrada.telefoneValido(telefone)) {
                p.setTelefone(telefone);
                break;
            } else {
                System.out.println("Telefone inválido. Deve conter 10 ou 11 números.");
            }
        }

        // Data de Nascimento
        while (true) {
            System.out.print("Data de Nascimento (DDMMYYYY): ");
            String dataStr = scanner.nextLine();
            if (ValidaEntrada.dataNascimentoValida(dataStr)) {
                LocalDate data = ValidaEntrada.converterData(dataStr);
                p.setData_nascimento(data);
                break;
            } else {
                System.out.println("Data inválida. Use o formato DDMMYYYY.");
            }
        }

        // Senha
        System.out.print("Senha: ");
        p.setSenha(scanner.nextLine());

        p.setTipo_usuario("CLIENTE");

        try {
            pessoaDAO.insert(p);
            System.out.println("Cadastro realizado com sucesso! ID: " + p.getId_pessoa() + "\n");
            return p;
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar: " + e.getMessage());
            return null;
        }
    }
}
