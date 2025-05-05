package ingressart.teatro.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ValidaEntrada {

    public static boolean emailValido(String email) {
        return email != null && email.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }

    public static boolean cpfValido(String cpf) {
        return cpf != null && cpf.matches("\\d{11}");
    }

    public static boolean telefoneValido(String telefone) {
        return telefone != null && telefone.matches("\\d{10,11}");
    }

    public static boolean dataNascimentoValida(String data) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
            LocalDate.parse(data, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static LocalDate converterData(String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        return LocalDate.parse(data, formatter);
    }
}

