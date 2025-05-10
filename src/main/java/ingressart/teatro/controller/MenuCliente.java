// src/main/java/grupo3/java_backend/controller/MenuCliente.java
package ingressart.teatro.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import ingressart.teatro.dao.EventoDAO;
import ingressart.teatro.dao.IngressoDAO;
import ingressart.teatro.dao.PecaDAO;
import ingressart.teatro.dao.PessoaDAO;
import ingressart.teatro.dao.ReviewDAO;
import ingressart.teatro.dao.SessaoDAO;
import ingressart.teatro.dao.VendaDAO;
import ingressart.teatro.model.Ingresso;
import ingressart.teatro.model.Peca;
import ingressart.teatro.model.Pessoa;
import ingressart.teatro.model.Review;
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
    private ReviewDAO reviewDAO = new ReviewDAO();
    private PessoaDAO pessoaDAO = new PessoaDAO(); 
    public void iniciar(Pessoa cliente) {
        while (true) {
            System.out.println("--- Menu Cliente ---");
            System.out.println("1 - Visualizar Peças");
            System.out.println("2 - Buscar Peça");
            System.out.println("3 - Meus Eventos");
            System.out.println("4 - Editar Perfil");
            System.out.println("5 - Avaliar Peça");
            System.out.println("6 - Ver Avaliações");
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
                case "5":
                    avaliarPeca(cliente);
                    break;
                case "6":
                    visualizarAvaliacoes();
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

    private void avaliarPeca(Pessoa cliente) {
        try {
            List<Ingresso> ingressos = ingressoDAO.findByClienteId(cliente.getId_pessoa());
            if (ingressos.isEmpty()) {
                System.out.println("\nVocê ainda não comprou ingressos para nenhuma peça.");
                return;
            }

            System.out.println("\n--- Avaliar Peça ---");
            System.out.println("Peças que você assistiu:");
            
            for (Ingresso ingresso : ingressos) {
                Peca peca = pecaDAO.findById(ingresso.getId_peca());
                System.out.printf("%d - %s\n", peca.getId_peca(), peca.getNome());
            }

            System.out.print("\nDigite o ID da peça que deseja avaliar (ou 0 para voltar): ");
            int idPeca = Integer.parseInt(scanner.nextLine());
            if (idPeca == 0) return;

            Peca peca = pecaDAO.findById(idPeca);
            if (peca == null) {
                System.out.println("Peça não encontrada!");
                return;
            }

            List<Review> existingReviews = reviewDAO.findByClienteId(cliente.getId_pessoa());
            for (Review review : existingReviews) {
                if (review.getId_peca() == idPeca) {
                    System.out.println("Você já avaliou esta peça!");
                    return;
                }
            }

            System.out.print("Digite sua avaliação (1-5 estrelas): ");
            int rating = Integer.parseInt(scanner.nextLine());
            if (rating < 1 || rating > 5) {
                System.out.println("Avaliação inválida! Use uma nota de 1 a 5.");
                return;
            }

            System.out.print("Digite seu comentário (opcional): ");
            String comentario = scanner.nextLine();

            Review review = new Review();
            review.setId_cliente(cliente.getId_pessoa());
            review.setId_peca(idPeca);
            review.setRating(rating);
            review.setComentario(comentario);
            review.setData_review(java.time.LocalDateTime.now());

            reviewDAO.insert(review);
            System.out.println("\nAvaliação registrada com sucesso!");

        } catch (SQLException ex) {
            System.err.println("Erro ao avaliar peça: " + ex.getMessage());
        }
    }

    private void visualizarAvaliacoes() {
        try {
            System.out.println("\n--- Avaliações das Peças ---");
            
            List<Peca> pecas = pecaDAO.findAll();
            

            if (pecas.isEmpty()){
                System.out.println("Nenhuma peça encontrada.");
            }
            for (Peca peca : pecas) {
        
                List<Review> reviews = reviewDAO.findByPecaId(peca.getId_peca());
                if(reviews.isEmpty()){
                    System.out.printf("\nPeça: %s\n", peca.getNome());
                    System.out.println("Nenhuma avaliação encontrada.");
                    continue;
                }
                if (!reviews.isEmpty()) {
                
                    double mediaRating = reviews.stream()
                        .mapToInt(Review::getRating)
                        .average()
                        .orElse(0.0);  

                    long quantidadeAvaliacoes = reviews.size();  
                    
                    System.out.printf("\nPeça: %s\n", peca.getNome());
                    System.out.printf("Média de avaliações: %.1f estrelas (%d avaliações)\n", mediaRating, quantidadeAvaliacoes);
                    System.out.println("Avaliações:");

            
                    for (Review review : reviews) {
                        // Recupera o cliente que fez a avaliação
                        Pessoa clienteAvaliado = pessoaDAO.findById(review.getId_cliente());
                        String nomeCliente = clienteAvaliado != null ? clienteAvaliado.getNome() : "Desconhecido";

                  
                        System.out.printf("- %d estrelas: %s (Comentário: %s)\n", 
                            review.getRating(), 
                            nomeCliente, 
                            review.getComentario() != null ? review.getComentario() : "(Sem comentário)");
                    }
                }
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao visualizar avaliações: " + ex.getMessage());
        }
    }

    public void menuCliente(Pessoa cliente) {
        while (true) {
            System.out.println("\n--- Menu Cliente ---");
            System.out.println("1 - Visualizar Peças");
            System.out.println("2 - Buscar Peça");
            System.out.println("3 - Meus Eventos");
            System.out.println("4 - Avaliar Peça");
            System.out.println("5 - Ver Avaliações");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");
            String opc = scanner.nextLine();

            switch (opc) {
                case "1": visualizarPecas(cliente); break;
                case "2": buscarPeca(cliente); break;
                case "3": listarMeusEventos(cliente); break;
                case "4": avaliarPeca(cliente); break;
                case "5": visualizarAvaliacoes(); break;
                case "0": return;
                default: System.out.println("Opção inválida.\n");
            }
        }
    }
}