package br.com.lumix.lumix.repository;

import br.com.lumix.lumix.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    List<Categoria> findAllByAtivoTrue();

    Optional<Categoria> findByIdAndAtivoTrue(Long id);


}
