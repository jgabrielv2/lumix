package br.com.lumix.lumix.controller;

import br.com.lumix.lumix.dto.create.DadosCriacaoVideo;
import br.com.lumix.lumix.dto.read.DadosListagemVideo;
import br.com.lumix.lumix.dto.update.DadosAtualizacaoVideo;
import br.com.lumix.lumix.service.VideoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/videos")
public class VideoController {

    private final VideoService videoService;

    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @PostMapping
    public ResponseEntity<DadosListagemVideo> salvar(@Valid @RequestBody DadosCriacaoVideo dadosCriacaoVideo, UriComponentsBuilder uriComponentsBuilder) {
        var video = videoService.create(dadosCriacaoVideo);
        var uri = uriComponentsBuilder.path("/videos/{id}")
                .buildAndExpand(video.id()).toUri();
        return ResponseEntity.created(uri).body(video);
    }

    @GetMapping
    public ResponseEntity<List<DadosListagemVideo>> listarTodos() {
        var videos = videoService.findAll();
        return ResponseEntity.ok(videos);
    }


    @GetMapping("/{id}")
    public ResponseEntity<DadosListagemVideo> listarPorId(@PathVariable Long id) {
        var video = videoService.findById(id);
        return ResponseEntity.ok(video);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DadosListagemVideo> atualizar(@PathVariable Long id, @RequestBody DadosAtualizacaoVideo dadosAtualizacaoVideo) {
        var video = videoService.update(id, dadosAtualizacaoVideo);
        return ResponseEntity.ok(video);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id){
        videoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
