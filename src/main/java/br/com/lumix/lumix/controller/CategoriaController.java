package br.com.lumix.lumix.controller;

import br.com.lumix.lumix.dto.create.DadosCriacaoCategoria;
import br.com.lumix.lumix.dto.read.DadosListagemCategoria;
import br.com.lumix.lumix.dto.read.DadosListagemVideo;
import br.com.lumix.lumix.dto.update.DadosAtualizacaoCategoria;
import br.com.lumix.lumix.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService service;

    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DadosListagemCategoria> salvar(@Valid @RequestBody DadosCriacaoCategoria dados, UriComponentsBuilder uriComponentsBuilder) {
        var categoria = service.create(dados);
        var uri = uriComponentsBuilder.path("/categorias/{id}")
                .buildAndExpand(categoria.id()).toUri();
        return ResponseEntity.created(uri).body(categoria);
    }

    @GetMapping
    public ResponseEntity<List<DadosListagemCategoria>> listarTodos() {
        var categorias = service.findAll();
        return ResponseEntity.ok(categorias);
    }


    @GetMapping("/{id}")
    public ResponseEntity<DadosListagemCategoria> listarPorId(@PathVariable Long id) {
        var categoria = service.findById(id);
        return ResponseEntity.ok(categoria);
    }

    @GetMapping("/{id}/videos")
    public ResponseEntity<List<DadosListagemVideo>> listarPorCategoriaId(@PathVariable Long id) {
        var videos = service.findVideoByCategoriaId(id);
        return ResponseEntity.ok(videos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DadosListagemCategoria> atualizar(@PathVariable Long id, @RequestBody DadosAtualizacaoCategoria dados) {
        var categoria = service.update(id, dados);
        return ResponseEntity.ok(categoria);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
