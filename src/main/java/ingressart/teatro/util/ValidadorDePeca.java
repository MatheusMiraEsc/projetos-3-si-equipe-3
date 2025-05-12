package ingressart.teatro.util;

import ingressart.teatro.model.Peca;
import java.time.LocalDate;

public class ValidadorDePeca {
    public static String validar(Peca peca) {
        if (peca.getNome() == null || peca.getNome().trim().isEmpty()) {
            return "Nome é obrigatório";
        }
        if (peca.getDescricao() == null || peca.getDescricao().trim().isEmpty()) {
            return "Descrição é obrigatória";
        }
        if (peca.getData() == null || peca.getData().isBefore(LocalDate.now())) {
            return "Data inválida";
        }
        if (peca.getValor_ingresso() <= 0) {
            return "Valor inválido";
        }
        return null; // válido
    }
}
