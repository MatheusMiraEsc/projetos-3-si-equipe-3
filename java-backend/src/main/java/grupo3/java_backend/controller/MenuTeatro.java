package grupo3.java_backend.controller;

import grupo3.java_backend.dao.EventoDAO;
import grupo3.java_backend.dao.SessaoDAO;
import grupo3.java_backend.dao.SalaDAO;
import grupo3.java_backend.model.Evento;
import grupo3.java_backend.model.Sessao;
import grupo3.java_backend.model.Sala;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MenuTeatro {
    private Scanner scanner = new Scanner(System.in);
    private EventoDAO eventoDAO = new EventoDAO();
    private SalaDAO salaDAO = new SalaDAO();
    private SessaoDAO sessaoDAO = new SessaoDAO();

    public void iniciar() {
        while (true) {
            System.out.println("--- Menu Teatro ---");
            System.out.println("1 - Cadastrar Peça");
            System.out.println("2 - Listar Peças");
            System.out.println("3 - Cadastrar Sala");
            System.out.println("4 - Cadastrar Sessão");
            System.out.println("5 - Listar Salas");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");
            String opc = scanner.nextLine();
    
            switch (opc) {
                case "1": cadastrarPeca(); break;
                case "2": listarPecas(); break;
                case "3": cadastrarSala(); break;
                case "4": cadastrarSessao(); break;
                case "5": listarSalas(); break;  // A nova opção de listar salas
                case "0": return;
                default: System.out.println("Opção inválida.\n");
            }
        }
    }
    private void cadastrarPeca() {
        try {
            System.out.println("--- Cadastro de Nova Peça ---");
    
            // 1) Nome e descrição
            System.out.print("Nome da peça: ");
            String nome = scanner.nextLine();
            System.out.print("Descrição: ");
            String descricao = scanner.nextLine();
    
            // 2) Escolha de SALA
            List<Sala> salas = salaDAO.findAll();
            if (salas.isEmpty()) {
                System.out.println("Nenhuma sala cadastrada. Cadastre uma sala primeiro.\n");
                return;
            }
            System.out.println("Salas disponíveis:");
            for (Sala s : salas) {
                System.out.printf("  %d - %s (Capacidade: %d)%n",
                    s.getId_sala(), s.getTipo(), s.getCapacidade());
            }
            System.out.print("ID da sala para esta peça: ");
            int idSala = Integer.parseInt(scanner.nextLine());
            Sala sala = salaDAO.findById(idSala);
            if (sala == null) {
                System.out.println("Sala não encontrada!\n");
                return;
            }
    
            // 3) Data e hora de INÍCIO
            System.out.println("Informe a data e hora de início da peça:");
            System.out.print("Dia: ");    int dia = Integer.parseInt(scanner.nextLine());
            System.out.print("Mês: ");    int mes = Integer.parseInt(scanner.nextLine());
            System.out.print("Ano: ");    int ano = Integer.parseInt(scanner.nextLine());
            System.out.print("Hora: ");   int hora = Integer.parseInt(scanner.nextLine());
            System.out.print("Minuto: "); int min  = Integer.parseInt(scanner.nextLine());
            java.time.LocalDateTime inicio = java.time.LocalDateTime.of(ano, mes, dia, hora, min);
    
            // 4) Categoria e classificação
            System.out.print("Categoria: ");
            String categoria = scanner.nextLine();
            System.out.print("Classificação indicativa: ");
            int classInd = Integer.parseInt(scanner.nextLine());
    
            // 5) Cria Evento
            Evento evento = new Evento();
            evento.setNomeEvento(nome);
            evento.setDescricao(descricao);
            evento.setData_inicio(inicio);
            evento.setCategoria(categoria);
            evento.setClass_indicativa(classInd);
            evento.setId_sala(idSala);
            evento.setStatus(true);
            eventoDAO.insert(evento);
    
            // 6) Preço e Sessão (com ingressos = capacidade da sala)
            System.out.print("Preço do ingresso: ");
            float preco = Float.parseFloat(scanner.nextLine());
            Sessao sessao = new Sessao();
            sessao.setData_inicio(inicio);
            sessao.setPreco_sessao(preco);
            sessao.setNum_ingressos_disp(sala.getCapacidade());
            sessao.setId_evento(evento.getId_evento());
            sessao.setId_sala(idSala);
            sessaoDAO.insert(sessao);
    
            System.out.println("Peça e sessão cadastradas com sucesso!");
            System.out.printf("Evento ID: %d | Sessão ID: %d\n\n",
                evento.getId_evento(), sessao.getId_sessao());
    
        } catch (Exception ex) {
            System.err.println("Erro ao cadastrar peça: " + ex.getMessage());
        }
    }
    


    private void listarPecas() {
        // Implementação de listagem de peças
    }

    private void cadastrarSala() {
        try {
            System.out.print("Digite o nome da sala: ");
            String tipo = scanner.nextLine();

            System.out.print("Digite a capacidade da sala (número de assentos): ");
            int capacidade = Integer.parseInt(scanner.nextLine());

            Sala sala = new Sala();
            sala.setCapacidade(capacidade);
            sala.setTipo(tipo);

            salaDAO.insert(sala); // Insere no banco a sala criada
            System.out.println("Sala cadastrada com sucesso! ID: " + sala.getId_sala());
        } catch (Exception e) {
            System.err.println("Erro ao cadastrar sala: " + e.getMessage());
        }
    }

    private void listarSalas() {
        try {
            // Lista todas as salas
            List<Sala> salas = salaDAO.findAll();
            if (salas.isEmpty()) {
                System.out.println("Nenhuma sala cadastrada.");
                return;
            }

            // Para cada sala, listar as sessões agendadas nela
            for (Sala sala : salas) {
                System.out.println("Sala ID: " + sala.getId_sala() + " - " + sala.getTipo() + " (Capacidade: " + sala.getCapacidade() + ")");
                System.out.println("Sessões agendadas:");

                // Buscando as sessões associadas à sala
                List<Sessao> sessoes = sessaoDAO.findBySalaId(sala.getId_sala());

                if (sessoes.isEmpty()) {
                    System.out.println("Nenhuma sessão agendada.");
                } else {
                    for (Sessao sessao : sessoes) {
                        System.out.println("  - Sessão ID: " + sessao.getId_sessao() + " | " +
                                           "Evento: " + eventoDAO.findById(sessao.getId_evento()).getNomeEvento() + " | " +
                                           "Data e Hora: " + sessao.getData_inicio() + " | " +
                                           "Ingressos Disponíveis: " + sessao.getNum_ingressos_disp());
                    }
                }
                System.out.println();
            }
        } catch (Exception e) {
            System.err.println("Erro ao listar salas: " + e.getMessage());
        }
    }

    // Ainda dentro da classe MenuTeatro, após cadastrarSala():

private void cadastrarSessao() {
    try {
        // 1) Escolher o evento
        System.out.print("Digite o ID da peça (evento) para criar a sessão: ");
        int idEvento = Integer.parseInt(scanner.nextLine());
        Evento evento = eventoDAO.findById(idEvento);
        if (evento == null) {
            System.out.println("Evento não encontrado!");
            return;
        }

        // 2) Mostrar e escolher a sala
        System.out.println("Salas disponíveis:");
        List<Sala> salas = salaDAO.findAll();
        for (Sala s : salas) {
            System.out.printf("  %d - %s (Capacidade: %d)%n",
                s.getId_sala(), s.getTipo(), s.getCapacidade());
        }
        System.out.print("Digite o ID da sala escolhida: ");
        int idSala = Integer.parseInt(scanner.nextLine());
        Sala sala = salaDAO.findById(idSala);
        if (sala == null) {
            System.out.println("Sala não encontrada!");
            return;
        }

        // 3) Ler data/hora da sessão
        System.out.println("Agora, informe a data e hora da sessão:");
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

        // 4) Ler preço
        System.out.print("Preço do ingresso: ");
        float preco = Float.parseFloat(scanner.nextLine());

        // 5) Número de ingressos disponíveis = capacidade da sala
        int disponiveis = sala.getCapacidade();

        // 6) Montar e salvar Sessão
        Sessao sessao = new Sessao();
        sessao.setId_evento(idEvento);
        sessao.setId_sala(idSala);
        sessao.setData_inicio(inicio);
        sessao.setPreco_sessao(preco);
        sessao.setNum_ingressos_disp(disponiveis);
        sessaoDAO.insert(sessao);

        System.out.println("Sessão cadastrada com sucesso! ID: " + sessao.getId_sessao());
    } catch (SQLException e) {
        System.err.println("Erro ao cadastrar sessão: " + e.getMessage());
    }
}

}
