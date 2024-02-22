package br.com.lumix.lumix.dto.read;

import br.com.lumix.lumix.entity.Categoria;
import br.com.lumix.lumix.entity.Video;


public record DadosListagemCategoria(Long id, String titulo, String cor) {

    public DadosListagemCategoria(Categoria categoria){
        this(categoria.id(), categoria.titulo(), categoria.cor());
    }

}

