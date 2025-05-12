// src/main/java/grupo3/java_backend/controller/MenuCliente.java
package ingressart.teatro.controller;

import ingressart.teatro.dao.EventoDAO;
import ingressart.teatro.dao.IngressoDAO;
import ingressart.teatro.dao.PecaDAO;
import ingressart.teatro.dao.SessaoDAO;
import ingressart.teatro.dao.VendaDAO;
import ingressart.teatro.model.Ingresso;
import ingressart.teatro.model.Peca;
import ingressart.teatro.model.Pessoa;
// import ingressart.teatro.model.Evento;
// import ingressart.teatro.model.Sessao;
// import ingressart.teatro.model.Venda;



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
            System.out.println("2 - Buscar Peça");
            System.out.println("3 - Meus Eventos");
            System.out.println("4 - Editar Perfil");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");
            String opc = scanner.nextLine();

            switch (opc) {
                case "1":
                    visualizarPecas(cliente);
                    break;
                case "2":
                    buscarPeca(cliente);
                    break;
                case "3":
                    listarMeusEventos(cliente);
                    break;
                case "4":
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

    private void buscarPeca(Pessoa cliente){
        try {
            List<Peca> pecas = pecaDAO.findAll();
            Peca peca = null;
            while(peca ==null) {
                System.out.println("Digite o nome da peça que deseja buscar (ou 0 para voltar): ");
                String nome_peca = scanner.nextLine();
                if("0".equals(nome_peca)) return;
                for(Peca p : pecas){
                    if (nome_peca.equalsIgnoreCase(p.getNome())){
                        peca = p;
                        break;
                    }
                }
                if (peca == null) {
                    System.out.println("Peça não encontrada :(");
                }
            }
            
        System.out.printf("%d - %s (%s %s)%n", 
        peca.getId_peca(), 
        peca.getNome(), 
        peca.getData(), 
        peca.getHora());
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
    try {
        List<Ingresso> ingressos = ingressoDAO.findByClienteId(cliente.getId_pessoa());

        if (ingressos.isEmpty()) {
            System.out.println("Você ainda não comprou ingressos.\n");
            return;
        }

        System.out.println("--- Meus Ingressos ---");
        for (Ingresso ingresso : ingressos) {
            Peca peca = pecaDAO.findById(ingresso.getId_peca());
            System.out.printf("Peça: %s (%s %s)\n", 
                peca.getNome(), 
                peca.getData(), 
                peca.getHora());
            System.out.printf("Tipo: %s | Preço: R$%.2f | Status: %s\n\n", 
                ingresso.getTipo_ingresso(), 
                ingresso.getPreco_ingresso(), 
                ingresso.isStatus() ? "Ativo" : "Inativo");
        }

    } catch (SQLException ex) {
        System.err.println("Erro ao listar seus eventos: " + ex.getMessage());
    }
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
