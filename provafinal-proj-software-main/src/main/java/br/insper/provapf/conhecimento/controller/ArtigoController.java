package br.insper.provapf.conhecimento.controller;

import br.insper.provapf.conhecimento.dto.ArtigoDTO;
import br.insper.provapf.conhecimento.model.Artigo;
import br.insper.provapf.conhecimento.service.ArtigoService;
import br.insper.provapf.conhecimento.service.AutenticadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artigos")
public class ArtigoController {

    @Autowired
    private ArtigoService artigoService;

    @Autowired
    private AutenticadorService autenticadorService;

    @PostMapping
    public ResponseEntity<?> criarArtigo(@RequestHeader("Authorization") String jwtToken, @RequestBody ArtigoDTO artigoDTO) {
        String role = autenticadorService.validarToken(jwtToken);

        if (!"ADMIN".equals(role)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acesso negado: apenas ADMIN pode criar artigos");
        }

        Artigo artigo = artigoService.criarArtigo(artigoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(artigo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarArtigo(@RequestHeader("Authorization") String jwtToken, @PathVariable String id) {
        String role = autenticadorService.validarToken(jwtToken);

        if (!"ADMIN".equals(role)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acesso negado: apenas ADMIN pode deletar artigos");
        }

        artigoService.deletarArtigo(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<?> listarArtigos(@RequestHeader("Authorization") String jwtToken) {
        String role = autenticadorService.validarToken(jwtToken);

        if (!"ADMIN".equals(role) && !"DEVELOPER".equals(role)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acesso negado: apenas ADMIN e DEVELOPER podem ver os artigos");
        }

        List<Artigo> artigos = artigoService.listarArtigos();
        return ResponseEntity.ok(artigos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obterArtigoPorId(@RequestHeader("Authorization") String jwtToken, @PathVariable String id) {
        String role = autenticadorService.validarToken(jwtToken);

        if (!"ADMIN".equals(role) && !"DEVELOPER".equals(role)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acesso negado: apenas ADMIN e DEVELOPER podem ver o artigo");
        }

        Artigo artigo = artigoService.obterArtigoPorId(id);
        return ResponseEntity.ok(artigo);
    }
}
