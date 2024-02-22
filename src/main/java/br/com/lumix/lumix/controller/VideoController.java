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

    private final VideoService service;

    public VideoController(VideoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DadosListagemVideo> salvar(@Valid @RequestBody DadosCriacaoVideo dados, UriComponentsBuilder uriComponentsBuilder) {
        var video = service.create(dados);
        var uri = uriComponentsBuilder.path("/videos/{id}")
                .buildAndExpand(video.id()).toUri();
        return ResponseEntity.created(uri).body(video);
    }

    @GetMapping
    public ResponseEntity<List<DadosListagemVideo>> listarTodos() {
        var videos = service.findAll();
        return ResponseEntity.ok(videos);
    }


    @GetMapping("/{id}")
    public ResponseEntity<DadosListagemVideo> listarPorId(@PathVariable Long id) {
        var video = service.findById(id);
        return ResponseEntity.ok(video);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DadosListagemVideo> atualizar(@PathVariable Long id, @RequestBody DadosAtualizacaoVideo dados) {
        var video = service.update(id, dados);
        return ResponseEntity.ok(video);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
