package ingressart.teatro.util;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import ingressart.teatro.model.Peca;

public class ValidadorDePecaTest {
    
    @Test
    public void deveRetornarNullQuandoValida() {
        Peca peca = new Peca();
        peca.setNome("Hamlet");
        peca.setDescricao("Tragédia clássica");
        peca.setData(LocalDate.now().plusDays(1));
        peca.setHora(LocalTime.of(20, 0));
        peca.setValor_ingresso(50.0);
    }

    @Test
    public void deveRetornarNomeVazio() {
        Peca peca = new Peca();
        peca.setNome("  ");
        peca.setDescricao("Descrição qualquer");
        peca.setData(LocalDate.now().plusDays(1));
        peca.setHora(LocalTime.of(20,0));
        peca.setValor_ingresso(50.0);

        assertEquals("Nome é obrigatório", ValidadorDePeca.validar(peca));
    }

    @Test
    public void deveDetectarDescricaoVazia() {
        Peca peca = new Peca();
        peca.setNome("Nome qualquer");
        peca.setDescricao("  ");
        peca.setData(LocalDate.now().plusDays(1));
        peca.setHora(LocalTime.of(20, 0));
        peca.setValor_ingresso(50.0);

        assertEquals("Descrição é obrigatória", ValidadorDePeca.validar(peca));
    }

    @Test
    public void deveDetectarDataNoPassado() {
        Peca peca = new Peca();
        peca.setNome("Nome qualquer");
        peca.setDescricao("Descrição qualquer");
        peca.setData(LocalDate.now().minusDays(1));
        peca.setHora(LocalTime.of(20, 0));
        peca.setValor_ingresso(50.0);
        
        assertEquals("Data inválida", ValidadorDePeca.validar(peca));
    }

    @Test
    public void deveDetectarValorNegativo() {
        Peca peca = new Peca();
        peca.setNome("Nome qualquer");
        peca.setDescricao("Descrição qualquer");
        peca.setData(LocalDate.now().plusDays(1));
        peca.setHora(LocalTime.of(20, 0));
        peca.setValor_ingresso(-10.0);

        assertEquals("Valor inválido", ValidadorDePeca.validar(peca));
    }
}
