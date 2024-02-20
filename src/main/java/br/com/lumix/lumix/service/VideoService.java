package br.com.lumix.lumix.service;

import br.com.lumix.lumix.dto.create.DadosCriacaoVideo;
import br.com.lumix.lumix.dto.read.DadosListagemVideo;
import br.com.lumix.lumix.entity.Video;
import br.com.lumix.lumix.repository.VideoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoService {

    private final VideoRepository videoRepository;

    public VideoService(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    public void create(DadosCriacaoVideo dadosCriacaoVideo) {
        Video video = new Video(dadosCriacaoVideo);
        videoRepository.save(video);
    }

    public List<DadosListagemVideo> findAll() {
        List<Video> videos = videoRepository.findAll();
        return null;
    }

}
