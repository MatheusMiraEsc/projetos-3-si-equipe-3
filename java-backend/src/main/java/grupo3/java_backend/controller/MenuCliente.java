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
import grupo3.java_backend.model.Peca;
import grupo3.java_backend.dao.PecaDAO;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MenuCliente {
    private Scanner scanner = new Scanner(System.in);
    private EventoDAO eventoDAO = new EventoDAO();
    private SessaoDAO sessaoDAO = new SessaoDAO();
    private IngressoDAO ingressoDAO = new IngressoDAO();
    private VendaDAO vendaDAO = new VendaDAO();
    private PecaDAO pecaDAO = new PecaDAO();

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
            List<Peca> pecas = pecaDAO.findAll();
            for (Peca p : pecas) {
                System.out.printf("%d - %s (%s %s)%n", 
                    p.getId_peca(), 
                    p.getNome(), 
                    p.getData(), 
                    p.getHora());
            }
            System.out.print("Escolha a peça (ID) ou 0 para voltar: ");
            int id = Integer.parseInt(scanner.nextLine());
            if (id == 0) return;

            Peca peca = pecaDAO.findById(id);
            System.out.println("Descrição: " + peca.getDescricao());
            System.out.println("Valor: R$" + peca.getValor_ingresso());
            System.out.println("1 - Comprar ingresso");
            System.out.println("0 - Voltar");
            if ("1".equals(scanner.nextLine())) {
                realizarCompra(cliente, peca);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Erro ao acessar peças: " + ex.getMessage());
        }
    }

    private void listarMeusEventos(Pessoa cliente) {
        System.out.println("Funcionalidade 'Meus Eventos' ainda não implementada.\n");
    }

    private void realizarCompra(Pessoa cliente, Peca peca) {
        try {
            Ingresso ingresso = new Ingresso();
            ingresso.setId_cliente(cliente.getId_pessoa());
            ingresso.setId_peca(peca.getId_peca());
            ingresso.setTipo_ingresso("Inteira");
            ingresso.setPreco_ingresso(peca.getValor_ingresso());
            ingresso.setStatus(true);
            
            ingressoDAO.insert(ingresso);
            System.out.println("Ingresso comprado com sucesso!");
        } catch (SQLException ex) {
            System.err.println("Erro ao comprar ingresso: " + ex.getMessage());
        }
    }
}
