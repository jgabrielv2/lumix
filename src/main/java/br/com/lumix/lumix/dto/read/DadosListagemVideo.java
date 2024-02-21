package br.com.lumix.lumix.dto.read;

import br.com.lumix.lumix.entity.Video;


public record DadosListagemVideo(Long id, String titulo, String descricao, String url) {

    public DadosListagemVideo(Video video){
        this(video.id(), video.titulo(), video.descricao(), video.url());
    }

}

