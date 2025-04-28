// src/main/java/grupo3/java_backend/controller/MenuCliente.java
package grupo3.java_backend.controller;

import grupo3.java_backend.dao.EventoDAO;
import grupo3.java_backend.dao.SessaoDAO;
import grupo3.java_backend.dao.IngressoDAO;
import grupo3.java_backend.dao.VendaDAO;
import grupo3.java_backend.model.Evento;
import grupo3.java_backend.model.Pessoa;
import grupo3.java_backend.model.Sessao;
import grupo3.java_backend.model.Ingresso;
import grupo3.java_backend.model.Venda;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MenuCliente {
    private Scanner scanner = new Scanner(System.in);
    private EventoDAO eventoDAO = new EventoDAO();
    private SessaoDAO sessaoDAO = new SessaoDAO();
    private IngressoDAO ingressoDAO = new IngressoDAO();
    private VendaDAO vendaDAO = new VendaDAO();

    public void iniciar(Pessoa cliente) {
        while (true) {
            System.out.println("--- Menu Cliente ---");
            System.out.println("1 - Visualizar Peças");
            System.out.println("2 - Meus Eventos");
            System.out.println("3 - Editar Perfil");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");
            String opc = scanner.nextLine();

            switch (opc) {
                case "1":
                    visualizarPecas(cliente);
                    break;
                case "2":
                    listarMeusEventos(cliente);
                    break;
                case "3":
                    System.out.println("Funcionalidade editar perfil...");
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Opção inválida.\n");
            }
        }
    }

    private void visualizarPecas(Pessoa cliente) {
        try {
            List<Evento> eventos = eventoDAO.findAll();
            for (Evento e : eventos) {
                System.out.printf("%d - %s (%s)%n", e.getId_evento(), e.getNomeEvento(), e.getData_inicio());
            }
            System.out.print("Escolha a peça (ID) ou 0 para voltar: ");
            int id = Integer.parseInt(scanner.nextLine());
            if (id == 0) return;

            Evento e = eventoDAO.findById(id);
            System.out.println("Descrição: " + e.getDescricao());

            // Chave: buscar a Sessão correta
            Sessao s = sessaoDAO.findByEventoId(e.getId_evento());
            if (s == null) {
                System.out.println("Sessão não encontrada para esta peça.");
                return;
            }

            System.out.println("Valor: R$" + s.getPreco_sessao());
            System.out.println("1 - Comprar ingresso");
            System.out.println("0 - Voltar");
            if ("1".equals(scanner.nextLine())) {
                realizarCompra(cliente, e, s);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Erro ao acessar eventos: " + ex.getMessage());
        }
    }

    private void listarMeusEventos(Pessoa cliente) {
        System.out.println("Funcionalidade 'Meus Eventos' ainda não implementada.\n");
    }

    private void realizarCompra(Pessoa cliente, Evento evento, Sessao sessao) {
        System.out.println("Chave PIX: 0001.2345.6789-00");
        String resp;
        do {
            System.out.print("Seu pagamento foi efetuado? [S/N]: ");
            resp = scanner.nextLine();
        } while (!"S".equalsIgnoreCase(resp));

        try {
            Venda v = new Venda();
            v.setData_venda(java.time.LocalDateTime.now());
            v.setForma_pagamento("PIX");
            v.setId_cliente(cliente.getId_pessoa());
            vendaDAO.insert(v);

            Ingresso i = new Ingresso();
            i.setId_assento(1);
            i.setTipo_ingresso("Único");
            i.setPreco_ingresso(sessao.getPreco_sessao());
            i.setStatus(true);
            i.setId_cliente(cliente.getId_pessoa());
            i.setId_sessao(sessao.getId_sessao());
            ingressoDAO.insert(i);

            sessao.setNum_ingressos_disp(sessao.getNum_ingressos_disp() - 1);
            sessaoDAO.update(sessao);

            System.out.printf("Compra realizada! Peça: %s | Data: %s | Sala: %d | Valor: R$%.2f%n",
                evento.getNomeEvento(), sessao.getData_inicio(), sessao.getId_sala(), i.getPreco_ingresso());
            System.out.println("Código: " + java.util.UUID.randomUUID().toString().substring(0, 8));
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Erro na compra: " + ex.getMessage());
        }
    }
}
