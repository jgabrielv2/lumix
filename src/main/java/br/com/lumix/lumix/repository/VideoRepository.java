package br.com.lumix.lumix.repository;

import br.com.lumix.lumix.entity.Categoria;
import br.com.lumix.lumix.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VideoRepository extends JpaRepository<Video, Long> {
    List<Video> findAllByAtivoTrue();

    Optional<Video> findByIdAndAtivoTrue(Long id);

    List<Video> findByCategoria_Id(Long id);

    ;


}
