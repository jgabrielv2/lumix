package br.com.lumix.lumix.repository;

import br.com.lumix.lumix.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    List<Categoria> findAllByAtivoTrue();

}
