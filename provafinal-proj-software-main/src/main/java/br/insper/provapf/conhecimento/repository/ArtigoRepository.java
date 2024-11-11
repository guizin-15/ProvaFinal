package br.insper.provapf.conhecimento.repository;

import br.insper.provapf.conhecimento.model.Artigo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtigoRepository extends MongoRepository<Artigo, String> {
}
