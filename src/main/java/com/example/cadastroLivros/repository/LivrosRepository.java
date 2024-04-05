package com.example.cadastroLivros.repository;


import com.example.cadastroLivros.model.Livros;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LivrosRepository extends JpaRepository<Livros, Long> {
}
