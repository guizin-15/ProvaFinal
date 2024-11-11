package br.insper.provapf.conhecimento.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArtigoDTO {
    private String id;
    private String titulo;
    private String conteudo;
    private String autor;
    private String dataCriacao;
}
