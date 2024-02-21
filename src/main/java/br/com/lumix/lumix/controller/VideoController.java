package br.com.lumix.lumix.controller;

import br.com.lumix.lumix.dto.create.DadosCriacaoVideo;
import br.com.lumix.lumix.dto.read.DadosListagemVideo;
import br.com.lumix.lumix.service.VideoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("videos")
public class VideoController {

    private final VideoService videoService;

    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @PostMapping
    public ResponseEntity<DadosListagemVideo> salvar(@RequestBody DadosCriacaoVideo dadosCriacaoVideo, UriComponentsBuilder uriComponentsBuilder) {
        var video = videoService.create(dadosCriacaoVideo);
        var uri = uriComponentsBuilder.path("/videos/{id}")
                .buildAndExpand(video.id()).toUri();
        return ResponseEntity.created(uri).body(video);
    }

    public ResponseEntity<List<DadosListagemVideo>> listarTodos() {
        List<DadosListagemVideo> videos = videoService.findAll();
        return ResponseEntity.ok(videos);
    }
}
