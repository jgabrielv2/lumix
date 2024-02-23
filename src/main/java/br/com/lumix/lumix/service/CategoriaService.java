package br.com.lumix.lumix.service;

import br.com.lumix.lumix.dto.create.DadosCriacaoCategoria;
import br.com.lumix.lumix.dto.read.DadosListagemCategoria;
import br.com.lumix.lumix.dto.update.DadosAtualizacaoCategoria;
import br.com.lumix.lumix.entity.Categoria;
import br.com.lumix.lumix.exception.CategoriaNotFoundException;
import br.com.lumix.lumix.repository.CategoriaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository repository;

    public CategoriaService(CategoriaRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public DadosListagemCategoria create(DadosCriacaoCategoria dados) {
        var categoria = new Categoria(dados);
        repository.save(categoria);
        return new DadosListagemCategoria(categoria);
    }

    public List<DadosListagemCategoria> findAll() {
        return repository.findAllByAtivoTrue().stream().map(DadosListagemCategoria::new).toList();
    }

    public DadosListagemCategoria findById(Long id) {
        var categoria = buscarCategoriaPorId(id);
        return new DadosListagemCategoria(categoria);
    }

    @Transactional
    public DadosListagemCategoria update(Long id, DadosAtualizacaoCategoria dadosAtualizacaoCategoria) {
        var categoria = buscarCategoriaPorId(id);

        // ao avaliar a expressao booleana, se for verdadeira, o codigo
        // apos o ? será executado. Caso contrario, o codigo apos o : será executado
        categoria.setTitulo(dadosAtualizacaoCategoria.titulo() != null ? dadosAtualizacaoCategoria.titulo() : categoria.titulo());
        categoria.setCor(dadosAtualizacaoCategoria.cor() != null ? dadosAtualizacaoCategoria.cor() : categoria.cor());
        repository.save(categoria);
        return new DadosListagemCategoria(categoria);
    }

    public void delete(Long id) {
        var categoria = buscarCategoriaPorId(id);
        categoria.setAtivo(false);
        repository.save(categoria);
    }

    private Categoria buscarCategoriaPorId(Long id) {
        return repository.findByIdAndAtivoTrue(id).orElseThrow(() -> new CategoriaNotFoundException("Não encontrado"));
    }
}
