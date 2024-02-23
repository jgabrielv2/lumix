package br.com.lumix.lumix.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;


@Entity
@Table(name = "videos")
public class Video {

    public Video() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "O título deve ser preenchido")
    private String titulo;

    @NotBlank(message = "A descrição deve ser preenchida")
    private String descricao;

    @NotBlank(message = "A URL deve ser preenchida")
    private String url;
    private Boolean ativo;


    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    public Categoria getCategoria() {
        return categoria;
    }


    public Video setCategoria(Categoria categoria) {
        this.categoria = categoria;
        return this;
    }

    public long getId() {
        return id;
    }

    public Video setId(long id) {
        this.id = id;
        return this;
    }

    public String getTitulo() {
        return titulo;
    }

    public Video setTitulo(String titulo) {
        this.titulo = titulo;
        return this;
    }

    public String getDescricao() {
        return descricao;
    }

    public Video setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Video setUrl(String url) {
        this.url = url;
        return this;
    }

    public Boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
