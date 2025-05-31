package ingressart.teatro.util;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

import ingressart.teatro.model.Pessoa;

public class ValidadorPessoaTest {

    @Test
    public void deveValidarPessoaCorreta() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Paulo Egito");
        pessoa.setEmail("pEgito@email.com");
        pessoa.setCpf("77456789012");
        pessoa.setTelefone("11999999999");
        pessoa.setData_nascimento(LocalDate.of(1990, 5, 20));
        pessoa.setEndereco("Cais do Apolo, 77");
        pessoa.setSenha("senhaSegura77");
        pessoa.setTipo_usuario("admin");

        assertNull(ValidadorPessoa.validar(pessoa), "Pessoa válida não deve retornar erro");
    }

    @Test
    public void deveDetectarNomeVazio() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(" ");
        pessoa.setEmail("pEgito@email.com");
        pessoa.setCpf("77456789012");
        pessoa.setTelefone("11999999999");
        pessoa.setData_nascimento(LocalDate.of(1990, 5, 20));
        pessoa.setEndereco("Cais do Apolo, 77");
        pessoa.setSenha("senhaSegura77");
        pessoa.setTipo_usuario("admin");

        assertEquals("Nome é obrigatório", ValidadorPessoa.validar(pessoa));
    }

    @Test
    public void deveDetectarEmailInvalido() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Paulo Egito");
        pessoa.setEmail("pEgitoemail.com"); // email inválido
        pessoa.setCpf("77456789012");
        pessoa.setTelefone("11999999999");
        pessoa.setData_nascimento(LocalDate.of(1990, 5, 20));
        pessoa.setEndereco("Cais do Apolo, 77");
        pessoa.setSenha("senhaSegura77");
        pessoa.setTipo_usuario("admin");

        assertEquals("Email inválido", ValidadorPessoa.validar(pessoa));
    }

    @Test
    public void deveDetectarCpfInvalido() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Paulo Egito");
        pessoa.setEmail("pEgito@email.com");
        pessoa.setCpf("774"); // CPF inválido
        pessoa.setTelefone("11999999999");
        pessoa.setData_nascimento(LocalDate.of(1990, 5, 20));
        pessoa.setEndereco("Cais do Apolo, 77");
        pessoa.setSenha("senhaSegura77");
        pessoa.setTipo_usuario("admin");

        assertEquals("CPF inválido", ValidadorPessoa.validar(pessoa));
    }

    @Test
    public void deveDetectarTelefoneVazio() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Paulo Egito");
        pessoa.setEmail("pEgito@email.com");
        pessoa.setCpf("77456789012");
        pessoa.setTelefone(" "); // vazio
        pessoa.setData_nascimento(LocalDate.of(1990, 5, 20));
        pessoa.setEndereco("Cais do Apolo, 77");
        pessoa.setSenha("senhaSegura77");
        pessoa.setTipo_usuario("admin");

        assertEquals("Telefone é obrigatório", ValidadorPessoa.validar(pessoa));
    }

    @Test
    public void deveDetectarDataNascimentoFutura() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Paulo Egito");
        pessoa.setEmail("pEgito@email.com");
        pessoa.setCpf("77456789012");
        pessoa.setTelefone("11999999999");
        pessoa.setData_nascimento(LocalDate.now().plusDays(1)); // data no futuro
        pessoa.setEndereco("Cais do Apolo, 77");
        pessoa.setSenha("senhaSegura77");
        pessoa.setTipo_usuario("admin");

        assertEquals("Data de nascimento inválida", ValidadorPessoa.validar(pessoa));
    }

    @Test
    public void deveDetectarEnderecoVazio() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Paulo Egito");
        pessoa.setEmail("pEgito@email.com");
        pessoa.setCpf("77456789012");
        pessoa.setTelefone("11999999999");
        pessoa.setData_nascimento(LocalDate.of(1990, 5, 20));
        pessoa.setEndereco(" "); // vazio
        pessoa.setSenha("senhaSegura77");
        pessoa.setTipo_usuario("admin");

        assertEquals("Endereço é obrigatório", ValidadorPessoa.validar(pessoa));
    }

    @Test
    public void deveDetectarSenhaVazia() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Paulo Egito");
        pessoa.setEmail("pEgito@email.com");
        pessoa.setCpf("77456789012");
        pessoa.setTelefone("11999999999");
        pessoa.setData_nascimento(LocalDate.of(1990, 5, 20));
        pessoa.setEndereco("Cais do Apolo, 77");
        pessoa.setSenha(" "); // vazio
        pessoa.setTipo_usuario("admin");

        assertEquals("Senha é obrigatória", ValidadorPessoa.validar(pessoa));
    }

    @Test
    public void deveDetectarTipoUsuarioVazio() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Paulo Egito");
        pessoa.setEmail("pEgito@email.com");
        pessoa.setCpf("77456789012");
        pessoa.setTelefone("11999999999");
        pessoa.setData_nascimento(LocalDate.of(1990, 5, 20));
        pessoa.setEndereco("Cais do Apolo, 77");
        pessoa.setSenha("senhaSegura77");
        pessoa.setTipo_usuario(" "); // vazio

        assertEquals("Tipo de usuário é obrigatório", ValidadorPessoa.validar(pessoa));
    }
}
