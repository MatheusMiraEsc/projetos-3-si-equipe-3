package ingressart.teatro.controller;

import ingressart.teatro.util.ValidadorDePeca;
import ingressart.teatro.dao.*;
import ingressart.teatro.model.Evento;
import ingressart.teatro.model.Ingresso;
import ingressart.teatro.model.Pessoa;
import ingressart.teatro.model.Sessao;
import ingressart.teatro.model.Sala;
import ingressart.teatro.model.Peca;
import ingressart.teatro.model.Review;
import java.util.Map;
import java.sql.SQLException;
//import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class MenuTeatro {
    private final Scanner scanner = new Scanner(System.in);
    private final EventoDAO eventoDAO = new EventoDAO();
    private final SalaDAO salaDAO = new SalaDAO();
    private final SessaoDAO sessaoDAO = new SessaoDAO();
    private final PecaDAO pecaDAO = new PecaDAO();
    private final IngressoDAO ingressoDAO = new IngressoDAO();
    private final PessoaDAO pessoaDAO = new PessoaDAO();
    private final ReviewDAO reviewDAO = new ReviewDAO();

    public void iniciar() {
        while (true) {
            System.out.println("\n--- Menu Teatro ---");
            System.out.println("1 - Peças");
            System.out.println("2 - Cadastrar Sala");
            System.out.println("3 - Cadastrar Sessão");
            System.out.println("4 - Listar Salas");
            System.out.println("5 - Ver compradores por peça");
            System.out.println("6 - Ver usuários cadastrados");

            System.out.println("0 - Sair");
            System.out.print("Opção: ");
            String opc = scanner.nextLine();
    
            switch (opc) {
                case "1": menuPeca(); break;
                case "2": cadastrarSala(); break;
                case "3": cadastrarSessao(); break;
                case "4": listarSalas(); break;
                case "5": listarCompradoresPorPeca(); break;
                //case "6": listarUsuarios(); break;
                case "0": return;
                default: System.out.println("Opção inválida.\n");
            }
        }
    }

    private void menuPeca() {
        while (true) {
            System.out.println("\n--- Menu Peça ---");
            System.out.println("1 - Cadastrar Peça");
            System.out.println("2 - Listar Peças");
            System.out.println("3 - Alterar Peça");
            System.out.println("4 - Desativar Peça");
            System.out.println("5 - Reativar Peça");
            System.out.println("6 - Deletar Peça");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");
            String opc = scanner.nextLine();
    
            switch (opc) {
                case "1": cadastrarPeca(); break;
                case "2": listarPecas(); break;
                case "3": alterarPeca(); break;
                case "4": desativarPeca(); break;
                case "5": reativarPeca(); break;
                case "6": deletarPeca(); break;
                case "0": return;
                default: System.out.println("Opção inválida.\n");
            }
        }
    }

    private void cadastrarPeca() {
        try {
            System.out.println("\n--- Cadastro de Nova Peça ---");
    
            System.out.print("Nome da peça: ");
            String nome = scanner.nextLine();
            System.out.print("Descrição: ");
            String descricao = scanner.nextLine();
    
            List<Sala> salas = salaDAO.findAll();
            if (salas.isEmpty()) {
                System.out.println("Nenhuma sala cadastrada. Cadastre uma sala primeiro.\n");
                return;
            }
    
            System.out.println("\nSalas disponíveis:");
            for (Sala s : salas) {
                System.out.printf("  %d - %s (Capacidade: %d)%n",
                    s.getId_sala(), s.getTipo(), s.getCapacidade());
            }
    
            System.out.print("\nID da sala para esta peça: ");
            int idSala = Integer.parseInt(scanner.nextLine());
            Sala sala = salaDAO.findById(idSala);
            if (sala == null) {
                System.out.println("Sala não encontrada!\n");
                return;
            }
    
            System.out.println("\nInforme a data e hora da peça:");
            System.out.print("Dia: ");    int dia = Integer.parseInt(scanner.nextLine());
            System.out.print("Mês: ");    int mes = Integer.parseInt(scanner.nextLine());
            System.out.print("Ano: ");    int ano = Integer.parseInt(scanner.nextLine());
            System.out.print("Hora: ");   int hora = Integer.parseInt(scanner.nextLine());
            System.out.print("Minuto: "); int min  = Integer.parseInt(scanner.nextLine());
            java.time.LocalDate data = java.time.LocalDate.of(ano, mes, dia);
            java.time.LocalTime horaPeca = java.time.LocalTime.of(hora, min);
    
            System.out.print("\nPreço do ingresso: ");
            double valorIngresso = Double.parseDouble(scanner.nextLine());
    
            Peca peca = new Peca();
            peca.setNome(nome);
            peca.setDescricao(descricao);
            peca.setData(data);
            peca.setHora(horaPeca);
            peca.setValor_ingresso(valorIngresso);
    
            // validação antes do insert no banco de dados
            String erro = ValidadorDePeca.validar(peca);
            if (erro != null) {
                System.out.println("Erro ao cadastrar peça: " + erro);
                return;
            }
    
            pecaDAO.insert(peca);
            System.out.println("\nPeça cadastrada com sucesso!");
            System.out.printf("Peça ID: %d\n", peca.getId_peca());
    
        } catch (Exception ex) {
            System.err.println("Erro ao cadastrar peça: " + ex.getMessage());
        }
    }    

    private void listarPecas() {
        try {
            List<Peca> pecas = pecaDAO.findAll();
            List<Sessao> sessoes = sessaoDAO.findAll();
            if (pecas.isEmpty()) {
                System.out.println("\nNenhuma peça cadastrada.");
                return;
            }

            System.out.println("\n--- Lista de Peças ---");
            for (Peca peca : pecas) {
                System.out.printf("ID: %d | Nome: %s | Descrição: %s | Data: %s | Hora: %s | Valor: R$%.2f\n",
                    peca.getId_peca(), peca.getNome(), peca.getDescricao(),
                    peca.getData(), peca.getHora(), peca.getValor_ingresso());
                for (Sessao sessao : sessoes){
                    if (sessao.getId_peca()== peca.getId_peca()){
                        System.out.println("Status - Ativo");
                    }
                }
                System.out.println("Status - Desativado");
            }
        
        } catch (SQLException ex) {
            System.err.println("Erro ao listar peças: " + ex.getMessage());
        }
    }

    private void alterarPeca() {
        try {
            listarPecas();
            System.out.print("\nDigite o ID da peça que deseja alterar: ");
            int id = Integer.parseInt(scanner.nextLine());
            Peca peca = pecaDAO.findById(id);
            
            if (peca == null) {
                System.out.println("Peça não encontrada.");
                return;
            }

            System.out.println("\n--- Alteração de Peça ---");
            System.out.println("1 - Nome da Peça");
            System.out.println("2 - Descrição da Peça");
            System.out.println("3 - Data da Peça");
            System.out.println("4 - Hora da Peça");
            System.out.println("5 - Valor do Ingresso");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");
            String opc = scanner.nextLine();

            switch (opc) {
                case "1":
                    System.out.print("Novo nome da peça: ");
                    peca.setNome(scanner.nextLine());
                    break;
                case "2":
                    System.out.print("Nova descrição: ");
                    peca.setDescricao(scanner.nextLine());
                    break;
                case "3":
                    System.out.println("\nNova data:");
                    System.out.print("Dia: ");    int dia = Integer.parseInt(scanner.nextLine());
                    System.out.print("Mês: ");    int mes = Integer.parseInt(scanner.nextLine());
                    System.out.print("Ano: ");    int ano = Integer.parseInt(scanner.nextLine());
                    peca.setData(java.time.LocalDate.of(ano, mes, dia));
                    break;
                case "4":
                    System.out.println("\nNova hora:");
                    System.out.print("Hora: ");   int hora = Integer.parseInt(scanner.nextLine());
                    System.out.print("Minuto: "); int min  = Integer.parseInt(scanner.nextLine());
                    peca.setHora(java.time.LocalTime.of(hora, min));
                    break;
                case "5":
                    System.out.print("Novo valor do ingresso: ");
                    peca.setValor_ingresso(Double.parseDouble(scanner.nextLine()));
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Opção inválida.");
                    return;
            }

            String erro = ValidadorDePeca.validar(peca);
                if (erro != null) {
                    System.out.println("Erro ao atualizar peça: " + erro);
                    return;
            }

            pecaDAO.update(peca);
            System.out.println("\nPeça atualizada com sucesso!");
        } catch (Exception ex) {
            System.err.println("Erro ao alterar peça: " + ex.getMessage());
        }
    }

    private void desativarPeca(){
        try {
            listarPecas();
            System.out.print("\nDigite o ID da peça que deseja desativar: ");
            int id = Integer.parseInt(scanner.nextLine());
            Peca peca = pecaDAO.findById(id);

            if (peca == null) {
                System.out.println("Peça não encontrada.");
                return;
            }

            String info = String.format(
                "\nID: %d | Nome: %s | Descrição: %s | Data: %s | Hora: %s | Valor: R$%.2f\n",
                peca.getId_peca(),
                peca.getNome(),
                peca.getDescricao(),
                peca.getData(),
                peca.getHora(),
                peca.getValor_ingresso()
            );

            System.out.println("Deseja realmente desativar a peça abaixo?");
            System.out.println(info);
            System.out.print("Digite 'S' para confirmar: ");
            String confirmacao = scanner.nextLine();

            if (confirmacao.equalsIgnoreCase("S")) {
                List<Sessao> sessoes = sessaoDAO.findAll();
                List<Ingresso> ingressos = ingressoDAO.findAll();
                for (Ingresso ingresso : ingressos){
                    id = ingresso.getId_peca();
                    if(id == peca.getId_peca()){
                        ingresso.setStatus(false);
                        ingressoDAO.update(ingresso);
                    }
                }
                for(Sessao sessao : sessoes){
                    id = sessao.getId_peca();
                    if(id == peca.getId_peca()){
                        sessaoDAO.delete(sessao.getId_sessao());
                        sessaoDAO.update(sessao);
                    }

                }
                System.out.println("\nPeça desativada com sucesso!");
            } else {
                System.out.println("Desativação cancelada.");
            }
        } catch (Exception ex) {
            System.err.println("Erro ao deletar peça: " + ex.getMessage());
        }
    }

    private void reativarPeca() {
        try {
            listarPecas();
            System.out.println("\n--- Reativação de Peça ---");
            System.out.print("Digite o ID da peça (evento) para reativa-la: ");
            int idEvento = Integer.parseInt(scanner.nextLine());
            Peca peca = pecaDAO.findById(idEvento);
            if (peca == null) {
                System.out.println("Evento não encontrado!");
                return;
            }

            System.out.println("\n--- Digite a sala em que deseja colocar o evento ---");
            System.out.println("\nSalas disponíveis:");
            List<Sala> salas = salaDAO.findAll();
            for (Sala s : salas) {
                System.out.printf("  %d - %s (Capacidade: %d)%n",
                    s.getId_sala(), s.getTipo(), s.getCapacidade());
            }
            System.out.print("\nDigite o ID da sala escolhida: ");
            int idSala = Integer.parseInt(scanner.nextLine());
            Sala sala = salaDAO.findById(idSala);
            if (sala == null) {
                System.out.println("Sala não encontrada!");
                return;
            }

            System.out.println("\nAgora, informe a data e hora da sessão:");
            System.out.print("Dia: ");
            int dia = Integer.parseInt(scanner.nextLine());
            System.out.print("Mês: ");
            int mes = Integer.parseInt(scanner.nextLine());
            System.out.print("Ano: ");
            int ano = Integer.parseInt(scanner.nextLine());
            System.out.print("Hora (0-23): ");
            int hora = Integer.parseInt(scanner.nextLine());
            System.out.print("Minuto (0-59): ");
            int minuto = Integer.parseInt(scanner.nextLine());
            java.time.LocalDateTime inicio = java.time.LocalDateTime.of(ano, mes, dia, hora, minuto);

            System.out.print("\nPreço do ingresso: ");
            float preco = Float.parseFloat(scanner.nextLine());

            int disponiveis = sala.getCapacidade();

            Sessao sessao = new Sessao();
            sessao.setId_peca(idEvento);
            sessao.setId_sala(idSala);
            sessao.setData_inicio(inicio);
            sessao.setPreco_sessao(preco);
            sessao.setNum_ingressos_disp(disponiveis);
            sessaoDAO.insert(sessao);

            System.out.println("\nEvento reativado com sucesso!"
            
            );
        } catch (Exception e) {
            System.err.println("Erro ao cadastrar sessão: " + e.getMessage());
        }
    }

    private void deletarPeca() {
        try {
            listarPecas();
            System.out.print("\nDigite o ID da peça que deseja remover: ");
            int id = Integer.parseInt(scanner.nextLine());
            Peca peca = pecaDAO.findById(id);
            
            if (peca == null) {
                System.out.println("Peça não encontrada.");
                return;
            }

            String info = String.format(
                "\nID: %d | Nome: %s | Descrição: %s | Data: %s | Hora: %s | Valor: R$%.2f\n",
                peca.getId_peca(),
                peca.getNome(),
                peca.getDescricao(),
                peca.getData(),
                peca.getHora(),
                peca.getValor_ingresso()
            );

            System.out.println("Deseja realmente remover a peça abaixo?");
            System.out.println(info);
            System.out.print("Digite 'S' para confirmar: ");
            String confirmacao = scanner.nextLine();
            List<Ingresso> ingressos = ingressoDAO.findAll();
            List<Review> reviews = reviewDAO.findByPecaId(peca.getId_peca());
            for(Review review : reviews){
                if (review.getId_peca() == peca.getId_peca()){
                    reviewDAO.delete(review.getId_review());
                }
            }
            for (Ingresso ingresso : ingressos){
                if (ingresso.getId_peca() == peca.getId_peca()) ingressoDAO.delete(ingresso.getId_ingresso());
            }
            
            if (confirmacao.equalsIgnoreCase("S")) {
                pecaDAO.delete(id);
                System.out.println("\nPeça removida com sucesso!");
            } else {
                System.out.println("Remoção cancelada.");
            }
        } catch (Exception ex) {
            System.err.println("Erro ao deletar peça: " + ex.getMessage());
        }
    }

    private void cadastrarSala() {
        try {
            System.out.println("\n--- Cadastro de Nova Sala ---");
            System.out.print("Digite o nome da sala: ");
            String tipo = scanner.nextLine();

            System.out.print("Digite a capacidade da sala (número de assentos): ");
            int capacidade = Integer.parseInt(scanner.nextLine());

            Sala sala = new Sala();
            sala.setCapacidade(capacidade);
            sala.setTipo(tipo);

            salaDAO.insert(sala);
            System.out.println("\nSala cadastrada com sucesso! ID: " + sala.getId_sala());
        } catch (Exception e) {
            System.err.println("Erro ao cadastrar sala: " + e.getMessage());
        }
    }

    private void listarSalas() {
        try {
            List<Sala> salas = salaDAO.findAll();
            if (salas.isEmpty()) {
                System.out.println("\nNenhuma sala cadastrada.");
                return;
            }

            System.out.println("\n--- Lista de Salas ---");
            for (Sala sala : salas) {
                System.out.println("\nSala ID: " + sala.getId_sala() + " - " + sala.getTipo() + " (Capacidade: " + sala.getCapacidade() + ")");
                System.out.println("Sessões agendadas:");

                List<Sessao> sessoes = sessaoDAO.findBySalaId(sala.getId_sala());
                if (sessoes.isEmpty()) {
                    System.out.println("Nenhuma sessão agendada.");
                } else {
                    for (Sessao sessao : sessoes) {
                        Evento evento = eventoDAO.findById(sessao.getId_peca());
                        System.out.println("  - Sessão ID: " + sessao.getId_sessao() + " | " +
                                           "Evento: " + (evento != null ? evento.getNomeEvento() : "Evento não encontrado") + " | " +
                                           "Data e Hora: " + sessao.getData_inicio() + " | " +
                                           "Ingressos Disponíveis: " + sessao.getNum_ingressos_disp());
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao listar salas: " + e.getMessage());
        }
    }

    private void cadastrarSessao() {
        try {
            System.out.println("\n--- Cadastro de Nova Sessão ---");
            listarPecas();
            System.out.print("Digite o ID da peça (evento) para criar a sessão: ");
            int idEvento = Integer.parseInt(scanner.nextLine());
            Peca peca = pecaDAO.findById(idEvento);
            if (peca == null) {
                System.out.println("Evento não encontrado!");
                return;
            }

            System.out.println("\nSalas disponíveis:");
            List<Sala> salas = salaDAO.findAll();
            for (Sala s : salas) {
                System.out.printf("  %d - %s (Capacidade: %d)%n",
                    s.getId_sala(), s.getTipo(), s.getCapacidade());
            }
            System.out.print("\nDigite o ID da sala escolhida: ");
            int idSala = Integer.parseInt(scanner.nextLine());
            Sala sala = salaDAO.findById(idSala);
            if (sala == null) {
                System.out.println("Sala não encontrada!");
                return;
            }

            System.out.println("\nAgora, informe a data e hora da sessão:");
            System.out.print("Dia: ");
            int dia = Integer.parseInt(scanner.nextLine());
            System.out.print("Mês: ");
            int mes = Integer.parseInt(scanner.nextLine());
            System.out.print("Ano: ");
            int ano = Integer.parseInt(scanner.nextLine());
            System.out.print("Hora (0-23): ");
            int hora = Integer.parseInt(scanner.nextLine());
            System.out.print("Minuto (0-59): ");
            int minuto = Integer.parseInt(scanner.nextLine());
            java.time.LocalDateTime inicio = java.time.LocalDateTime.of(ano, mes, dia, hora, minuto);

            System.out.print("\nPreço do ingresso: ");
            float preco = Float.parseFloat(scanner.nextLine());

            int disponiveis = sala.getCapacidade();

            Sessao sessao = new Sessao();
            sessao.setId_peca(idEvento);
            sessao.setId_sala(idSala);
            sessao.setData_inicio(inicio);
            sessao.setPreco_sessao(preco);
            sessao.setNum_ingressos_disp(disponiveis);
            sessaoDAO.insert(sessao);

            System.out.println("\nSessão cadastrada com sucesso! ID: " + sessao.getId_sessao());
        } catch (Exception e) {
            System.err.println("Erro ao cadastrar sessão: " + e.getMessage());
        }
    }

    private void listarCompradoresPorPeca(){
        try{
            listarPecas();
            System.out.print("\nDigite o ID da peça: ");
            int idPeca = Integer.parseInt(scanner.nextLine());
            Peca peca = pecaDAO.findById(idPeca);
            if (peca == null) {
                System.out.println(("Peça não encontrada."));
                return;
            }
            Map<Integer,Integer> ingressosPorCliente = ingressoDAO.contarIngressosPorCliente(idPeca);
            if (ingressosPorCliente.isEmpty()){
                System.out.println("Nenhum ingresso foi comprado para essa peça");
            }
            System.out.println("\n--- Compradores da peça: " + peca.getNome() + " ---");
            for(Map.Entry<Integer, Integer> entry : ingressosPorCliente.entrySet()){
                Pessoa cliente = pessoaDAO.findById(entry.getKey());
                if(cliente != null){
                    System.out.printf("Cliente: %s | Email: %s | CPF: %s | Ingressos: %d\n", cliente.getNome(), cliente.getEmail(), cliente.getCpf(), entry.getValue());
                }
            }}
            catch(Exception e){
                System.err.println("Erro ao listar compradores: " + e.getMessage());
            }
        }
    }

