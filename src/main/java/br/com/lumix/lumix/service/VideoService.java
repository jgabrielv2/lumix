package br.com.lumix.lumix.service;

import br.com.lumix.lumix.dto.create.DadosCriacaoVideo;
import br.com.lumix.lumix.dto.read.DadosListagemVideo;
import br.com.lumix.lumix.dto.update.DadosAtualizacaoVideo;
import br.com.lumix.lumix.entity.Categoria;
import br.com.lumix.lumix.entity.Video;
import br.com.lumix.lumix.exception.CategoriaNotFoundException;
import br.com.lumix.lumix.exception.VideoNotFoundException;
import br.com.lumix.lumix.repository.CategoriaRepository;
import br.com.lumix.lumix.repository.VideoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class VideoService {

    private final VideoRepository videoRepository;
    private final CategoriaRepository categoriaRepository;

    public VideoService(VideoRepository repository, CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
        this.videoRepository = repository;
    }

    @Transactional
    public DadosListagemVideo create(DadosCriacaoVideo dadosCriacaoVideo) {
        var categoriaId = dadosCriacaoVideo.categoriaId();
        if (categoriaId == null) categoriaId = 1L;
        var categoria = buscarCategoriaPorId(categoriaId);
        var video = new Video();
        video.setCategoria(categoria)
                .setTitulo(dadosCriacaoVideo.titulo())
                .setDescricao(dadosCriacaoVideo.descricao())
                .setUrl(dadosCriacaoVideo.url())
                .setAtivo(true);
        videoRepository.save(video);
        return new DadosListagemVideo(video);
    }

    public List<DadosListagemVideo> findAll() {
        return videoRepository.findAllByAtivoTrue().stream().map(DadosListagemVideo::new).toList();
    }

    public DadosListagemVideo findById(Long id) {
        var video = buscarVideoPorId(id);
        return new DadosListagemVideo(video);
    }

    public List<DadosListagemVideo> findByTituloContainsIgnoreCase (String titulo){
        return videoRepository.findByTituloContainsIgnoreCase(titulo).stream().map(DadosListagemVideo::new).toList();
    }
    @Transactional
    public DadosListagemVideo update(Long id, DadosAtualizacaoVideo dadosAtualizacaoVideo) {
        var video = buscarVideoPorId(id);
        var categoria = buscarCategoriaPorId(dadosAtualizacaoVideo.categoriaId());
        // ao avaliar a expressao booleana, se for verdadeira, o codigo
        // apos o ? será executado. Caso contrario, o codigo apos o : será executado
        video.setCategoria(categoria != null ? categoria : video.getCategoria());
        video.setTitulo(dadosAtualizacaoVideo.titulo() != null ? dadosAtualizacaoVideo.titulo() : video.getTitulo());
        video.setDescricao(dadosAtualizacaoVideo.descricao() != null ? dadosAtualizacaoVideo.descricao() : video.getDescricao());
        video.setUrl(dadosAtualizacaoVideo.url() != null ? dadosAtualizacaoVideo.url() : video.getUrl());
        videoRepository.save(video);
        return new DadosListagemVideo(video);
    }

    public void delete(Long id) {
        var video = buscarVideoPorId(id);
        video.setAtivo(false);
        videoRepository.save(video);
    }

    private Categoria buscarCategoriaPorId(Long id) {
        return categoriaRepository.findByIdAndAtivoTrue(id).orElseThrow(() -> new CategoriaNotFoundException("Não encontrado"));
    }

    private Video buscarVideoPorId(Long id) {
        return videoRepository.findByIdAndAtivoTrue(id).orElseThrow(() -> new VideoNotFoundException("Não encontrado"));
    }
}
