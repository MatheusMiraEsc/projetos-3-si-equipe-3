package ingressart.teatro.controller;

import ingressart.teatro.dao.SessaoDAO;
import ingressart.teatro.model.Sessao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/sessoes")
public class SessaoController {
    private final SessaoDAO sessaoDAO;

    public SessaoController(SessaoDAO sessaoDAO) {
        this.sessaoDAO = sessaoDAO;
    }

    @PostMapping
    public ResponseEntity<Sessao> createSessao(@RequestBody Sessao sessao) {
        try {
            sessaoDAO.insert(sessao);
            return ResponseEntity.ok(sessao);
        } catch (SQLException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sessao> getSessao(@PathVariable int id) {
        try {
            Sessao sessao = sessaoDAO.findById(id);
            if (sessao != null) {
                return ResponseEntity.ok(sessao);
            }
            return ResponseEntity.notFound().build();
        } catch (SQLException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Sessao>> getAllSessoes() {
        try {
            List<Sessao> sessoes = sessaoDAO.findAll();
            return ResponseEntity.ok(sessoes);
        } catch (SQLException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sessao> updateSessao(@PathVariable int id, @RequestBody Sessao sessao) {
        try {
            sessao.setId_sessao(id);
            sessaoDAO.update(sessao);
            return ResponseEntity.ok(sessao);
        } catch (SQLException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSessao(@PathVariable int id) {
        try {
            sessaoDAO.delete(id);
            return ResponseEntity.ok().build();
        } catch (SQLException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/peca/{idPeca}")
    public ResponseEntity<Sessao> getSessaoByPeca(@PathVariable int idPeca) {
        try {
            Sessao sessao = sessaoDAO.findByEventoId(idPeca);
            if (sessao != null) {
                return ResponseEntity.ok(sessao);
            }
            return ResponseEntity.notFound().build();
        } catch (SQLException e) {
            return ResponseEntity.badRequest().build();
        }
    }
} 