package br.com.lumix.lumix.entity;

import br.com.lumix.lumix.dto.create.DadosCriacaoCategoria;
import jakarta.persistence.*;

@Entity
public class Categoria {

    public Categoria(){}
    public Categoria(DadosCriacaoCategoria dados){
        this.setTitulo(dados.titulo())
                .setCor(dados.cor())
                .setAtivo(true);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String cor;

    private Boolean ativo;

    public Long id() {
        return id;
    }

    public Categoria setId(Long id) {
        this.id = id;
        return this;
    }

    public String titulo() {
        return titulo;
    }

    public Categoria setTitulo(String titulo) {
        this.titulo = titulo;
        return this;
    }

    public String cor() {
        return cor;
    }

    public Categoria setCor(String cor) {
        this.cor = cor;
        return this;
    }

    public Boolean isAtivo(){
        return ativo;
    }

    public void setAtivo(Boolean ativo){
        this.ativo = ativo;
    }
}
