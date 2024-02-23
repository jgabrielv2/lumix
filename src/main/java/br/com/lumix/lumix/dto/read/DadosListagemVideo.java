package br.com.lumix.lumix.dto.read;

import br.com.lumix.lumix.entity.Video;


public record DadosListagemVideo(Long id, Long categoriaId, String titulo, String descricao, String url) {

    public DadosListagemVideo(Video video){
        this(video.getId(), video.getCategoria().id(), video.getTitulo(), video.getDescricao(), video.getUrl());
    }

}

