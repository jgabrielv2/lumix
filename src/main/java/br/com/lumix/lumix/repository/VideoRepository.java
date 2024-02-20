package br.com.lumix.lumix.repository;

import br.com.lumix.lumix.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Long> {
}
