package com.example.cadastroLivros.controller;
import com.example.cadastroLivros.model.Livros;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.http.ResponseEntity;
import com.example.cadastroLivros.repository.LivrosRepository;
@CrossOrigin(origins = "http://localhost:5173")//Endereço do front
@RestController
@RequestMapping("/livros")
public class LivrosController {
    @Autowired
    private LivrosRepository livrosRepository;
    @GetMapping
    public List<Livros> listarLivros() {
        return livrosRepository.findAll();
    }
    @PostMapping
    public Livros criarLivros(@RequestBody Livros livro) {
        return livrosRepository.save(livro);
    }
     @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarLivro(@PathVariable Long id) {
        if (livrosRepository.existsById(id)) {
            livrosRepository.deleteById(id);
            return ResponseEntity.ok("Livro deletado com sucesso.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livros> atualizarLivro(@PathVariable(name = "id") Long id, @RequestBody Livros livroAtualizado) {
        if (livrosRepository.existsById(id)) {
            Livros livro = livrosRepository.findById(id).get();
            livro.setTitulo(livroAtualizado.getTitulo());
            livro.setEditora(livroAtualizado.getEditora());
            livro.setAutor(livroAtualizado.getAutor());
            livro.setGenero(livroAtualizado.getGenero());

            Livros livroAtualizadoBD = livrosRepository.save(livro);
            return ResponseEntity.ok(livroAtualizadoBD);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
