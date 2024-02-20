package br.com.lumix.lumix.dto.read;

import br.com.lumix.lumix.entity.Video;

public class DadosListagemVideo {

    public DadosListagemVideo(Video video){
        this.id = video.id();
        this.titulo = video.titulo();
        this.descricao = video.descricao();
        this.url = video.url();
    }

    private final Long id;
    private final String titulo;
    private final String descricao;
    private final String url;

    public Long id() {
        return id;
    }

    public String titulo() {
        return titulo;
    }

    public String descricao() {
        return descricao;
    }

    public String url() {
        return url;
    }
}
