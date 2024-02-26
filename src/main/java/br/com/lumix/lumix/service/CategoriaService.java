package br.com.lumix.lumix.service;

import br.com.lumix.lumix.dto.create.DadosCriacaoCategoria;
import br.com.lumix.lumix.dto.read.*;
import br.com.lumix.lumix.dto.update.DadosAtualizacaoCategoria;
import br.com.lumix.lumix.entity.Categoria;
import br.com.lumix.lumix.exception.CategoriaNotFoundException;
import br.com.lumix.lumix.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final VideoRepository videoRepository;

    public CategoriaService(CategoriaRepository categoriaRepository, VideoRepository videoRepository) {
        this.categoriaRepository = categoriaRepository;
        this.videoRepository = videoRepository;
    }

    @Transactional
    public DadosListagemCategoria create(DadosCriacaoCategoria dados) {
        var categoria = new Categoria(dados);
        categoriaRepository.save(categoria);
        return new DadosListagemCategoria(categoria);
    }

    public List<DadosListagemCategoria> findAll() {
        return categoriaRepository.findAllByAtivoTrue().stream().map(DadosListagemCategoria::new).toList();
    }

    public DadosListagemCategoria findById(Long id) {
        return new DadosListagemCategoria(buscarCategoriaPorId(id));
    }

    public List<DadosListagemVideo> findVideoByCategoriaId(Long idCategoria) {
        return videoRepository.findByCategoria_Id(idCategoria).stream().map(DadosListagemVideo::new).toList();
    }

    @Transactional
    public DadosListagemCategoria update(Long id, DadosAtualizacaoCategoria dadosAtualizacaoCategoria) {
        var categoria = buscarCategoriaPorId(id);

        // ao avaliar a expressao booleana, se for verdadeira, o codigo
        // apos o ? será executado. Caso contrario, o codigo apos o : será executado
        categoria.setTitulo(dadosAtualizacaoCategoria.titulo() != null ? dadosAtualizacaoCategoria.titulo() : categoria.titulo());
        categoria.setCor(dadosAtualizacaoCategoria.cor() != null ? dadosAtualizacaoCategoria.cor() : categoria.cor());
        categoriaRepository.save(categoria);
        return new DadosListagemCategoria(categoria);
    }

    public void delete(Long id) {
        var categoria = buscarCategoriaPorId(id);
        categoria.setAtivo(false);
        categoriaRepository.save(categoria);
    }

    private Categoria buscarCategoriaPorId(Long id) {
        return categoriaRepository.findByIdAndAtivoTrue(id).orElseThrow(() -> new CategoriaNotFoundException("Não encontrado"));
    }
}
