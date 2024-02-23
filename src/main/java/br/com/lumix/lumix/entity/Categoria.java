package br.com.lumix.lumix.entity;

import br.com.lumix.lumix.dto.create.DadosCriacaoCategoria;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "categorias")
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

    @NotBlank(message = "O título é obrigatório")
    private String titulo;

    @NotBlank(message = "A cor é obrigatória")
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
