package grupo3.java_backend.model;


// Cliente.java
public class Cliente extends Pessoa {
    // Não possui atributos adicionais além dos herdados de Pessoa

    // Métodos específicos do Cliente
    public int getId_cliente() {
        return getId_pessoa();
    }

    public void setId_cliente(int id_cliente) {
        setId_pessoa(id_cliente);
    }

    public void comprarIngresso() {
        // Lógica de compra de ingresso
    }

    public void listarIngressosComprados() {
        // Lógica para listar ingressos comprados
    }

    public void listarEventosDisponiveis() {
        // Lógica para listar eventos disponíveis
    }
}



