package ingressart.teatro.model;
import java.time.LocalDateTime;

// Pagamento.java
public class Pagamento {
    private int id_pagamento;
    private int id_venda;
    private float valor_pago;
    private LocalDateTime data_pagamento;
    private String status;
    private String metodo_pagamento;

    public int getId_pagamento() {
        return id_pagamento;
    }

    public void setId_pagamento(int id_pagamento) {
        this.id_pagamento = id_pagamento;
    }

    public int getId_venda() {
        return id_venda;
    }

    public void setId_venda(int id_venda) {
        this.id_venda = id_venda;
    }

    public float getValor_pago() {
        return valor_pago;
    }

    public void setValor_pago(float valor_pago) {
        this.valor_pago = valor_pago;
    }

    public LocalDateTime getData_pagamento() {
        return data_pagamento;
    }

    public void setData_pagamento(LocalDateTime data_pagamento) {
        this.data_pagamento = data_pagamento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMetodo_pagamento() {
        return metodo_pagamento;
    }

    public void setMetodo_pagamento(String metodo_pagamento) {
        this.metodo_pagamento = metodo_pagamento;
    }

    public void registrarPagamento() {
        // Lógica para registrar pagamento
    }

    public void estornarPagamento() {
        // Lógica para estornar pagamento
    }

    public String consultarStatus() {
        // Retorna status atual do pagamento
        return status;
    }
}
