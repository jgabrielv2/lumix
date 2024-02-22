package br.com.lumix.lumix.service;

import br.com.lumix.lumix.dto.create.DadosCriacaoVideo;
import br.com.lumix.lumix.dto.read.DadosListagemVideo;
import br.com.lumix.lumix.dto.update.DadosAtualizacaoVideo;
import br.com.lumix.lumix.entity.Video;
import br.com.lumix.lumix.exception.VideoNotFoundException;
import br.com.lumix.lumix.repository.VideoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VideoService {

    private final VideoRepository videoRepository;

    public VideoService(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    public DadosListagemVideo create(DadosCriacaoVideo dadosCriacaoVideo) {
        Video video = new Video(dadosCriacaoVideo);
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

    @Transactional
    public DadosListagemVideo update(Long id, DadosAtualizacaoVideo dadosAtualizacaoVideo) {
        var video = videoRepository.getReferenceById(id);

        // ao avaliar a expressao booleana, se for verdadeira, o codigo
        // apos o ? será executado. Caso contrario, o codigo apos o : será executado
        video.setTitulo(dadosAtualizacaoVideo.titulo() != null ? dadosAtualizacaoVideo.titulo() : video.titulo());
        video.setDescricao(dadosAtualizacaoVideo.descricao() != null ? dadosAtualizacaoVideo.descricao() : video.descricao());
        video.setUrl(dadosAtualizacaoVideo.url() != null ? dadosAtualizacaoVideo.url() : video.url());
        videoRepository.save(video);
        return new DadosListagemVideo(video);
    }

    public void delete(Long id) {
        var video = buscarVideoPorId(id);
        video.setAtivo(false);
        videoRepository.save(video);
    }

    private Video buscarVideoPorId(Long id) {
        return videoRepository.findById(id).orElseThrow(() -> new VideoNotFoundException("Não encontrado"));
    }
}
