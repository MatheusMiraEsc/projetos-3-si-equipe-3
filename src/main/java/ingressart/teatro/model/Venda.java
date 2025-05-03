package ingressart.teatro.model;
import java.time.LocalDateTime;


// Venda.java
public class Venda {
    private int id_venda;
    private LocalDateTime data_venda;
    private String forma_pagamento;
    private int id_cliente;

    public int getId_venda() {
        return id_venda;
    }

    public void setId_venda(int id_venda) {
        this.id_venda = id_venda;
    }

    public LocalDateTime getData_venda() {
        return data_venda;
    }

    public void setData_venda(LocalDateTime data_venda) {
        this.data_venda = data_venda;
    }

    public String getForma_pagamento() {
        return forma_pagamento;
    }

    public void setForma_pagamento(String forma_pagamento) {
        this.forma_pagamento = forma_pagamento;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public void finalizarVenda() {
        // Lógica para finalizar venda
    }

    public void cancelarVenda() {
        // Lógica para cancelar venda
    }

    public float calcValorTotal() {
        // Lógica para calcular valor total da venda
        return 0.0f;
    }
}

