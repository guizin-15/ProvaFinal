package br.insper.provapf.conhecimento.service;

import br.insper.provapf.conhecimento.dto.ArtigoDTO;
import br.insper.provapf.conhecimento.model.Artigo;
import br.insper.provapf.conhecimento.repository.ArtigoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtigoService {

    @Autowired
    private ArtigoRepository artigoRepository;

    public Artigo criarArtigo(ArtigoDTO artigoDTO) {
        Artigo artigo = new Artigo();
        artigo.setTitulo(artigoDTO.getTitulo());
        artigo.setConteudo(artigoDTO.getConteudo());
        artigo.setAutor(artigoDTO.getAutor());
        artigo.setDataCriacao(artigoDTO.getDataCriacao());
        return artigoRepository.save(artigo);
    }

    public void deletarArtigo(String id) {
        artigoRepository.deleteById(id);
    }

    public List<Artigo> listarArtigos() {
        return artigoRepository.findAll();
    }

    public Artigo obterArtigoPorId(String id) {
        return artigoRepository.findById(id).orElse(null);
    }
}
