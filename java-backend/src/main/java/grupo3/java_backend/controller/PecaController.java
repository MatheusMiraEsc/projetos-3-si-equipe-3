package grupo3.java_backend.controller;

import grupo3.java_backend.model.Peca;
import grupo3.java_backend.repository.PecaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pecas")
@CrossOrigin(origins = "*")
public class PecaController {

    @Autowired
    private PecaRepository pecaRepository;

    @GetMapping
    public List<Peca> listar() {
        return pecaRepository.findAll();
    }

    @PostMapping
    public Peca criar(@RequestBody Peca peca) {
        return pecaRepository.save(peca);
    }

    @GetMapping("/{id}")
    public Peca buscarPorId(@PathVariable Long id) {
        return pecaRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Peca atualizar(@PathVariable Long id, @RequestBody Peca novaPeca) {
        return pecaRepository.findById(id).map(peca -> {
            peca.setNome(novaPeca.getNome());
            peca.setDescricao(novaPeca.getDescricao());
            peca.setData(novaPeca.getData());
            peca.setHora(novaPeca.getHora());
            peca.setValorIngresso(novaPeca.getValorIngresso());
            return pecaRepository.save(peca);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        pecaRepository.deleteById(id);
    }
}
