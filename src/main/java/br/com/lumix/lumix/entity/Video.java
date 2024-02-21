package br.com.lumix.lumix.entity;

import br.com.lumix.lumix.dto.create.DadosCriacaoVideo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Video {

    public Video() {
    }

    public Video(DadosCriacaoVideo dadosCriacaoVideo) {
        this.setTitulo(dadosCriacaoVideo.titulo())
                .setDescricao(dadosCriacaoVideo.descricao())
                .setUrl(dadosCriacaoVideo.url())
                .setAtivo(true);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String titulo;
    private String descricao;
    private String url;
    private Boolean ativo;

    public long id() {
        return id;
    }

    public Video setId(long id) {
        this.id = id;
        return this;
    }

    public String titulo() {
        return titulo;
    }

    public Video setTitulo(String titulo) {
        this.titulo = titulo;
        return this;
    }

    public String descricao() {
        return descricao;
    }

    public Video setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public String url() {
        return url;
    }

    public Video setUrl(String url) {
        this.url = url;
        return this;
    }

    public Boolean isAtivo(){
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
