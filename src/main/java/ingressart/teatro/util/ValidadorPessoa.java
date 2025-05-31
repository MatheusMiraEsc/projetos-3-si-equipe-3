package ingressart.teatro.util;

import java.time.LocalDate;

import ingressart.teatro.model.Pessoa;

public class ValidadorPessoa {

    public static String validar(Pessoa pessoa) {
        if (pessoa.getNome() == null || pessoa.getNome().trim().isEmpty()) {
            return "Nome é obrigatório";
        }

        if (pessoa.getEmail() == null || !pessoa.getEmail().matches("^[\\w.-]+@[\\w.-]+\\.\\w+$")) {
            return "Email inválido";
        }

        if (pessoa.getCpf() == null || !pessoa.getCpf().matches("\\d{11}")) {
            return "CPF inválido";
        }

        if (pessoa.getTelefone() == null || pessoa.getTelefone().trim().isEmpty()) {
            return "Telefone é obrigatório";
        }

        LocalDate hoje = LocalDate.now();
        if (pessoa.getData_nascimento() == null || pessoa.getData_nascimento().isAfter(hoje)) {
            return "Data de nascimento inválida";
        }

        if (pessoa.getEndereco() == null || pessoa.getEndereco().trim().isEmpty()) {
            return "Endereço é obrigatório";
        }

        if (pessoa.getSenha() == null || pessoa.getSenha().trim().isEmpty()) {
            return "Senha é obrigatória";
        }

        if (pessoa.getTipo_usuario() == null || pessoa.getTipo_usuario().trim().isEmpty()) {
            return "Tipo de usuário é obrigatório";
        }

        return null; // Tudo certo
    }
}
