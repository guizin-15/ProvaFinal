package br.insper.provapf.conhecimento.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import java.util.Map;

@Service
public class AutenticadorService {

    private static final String VALIDATE_URL = "http://184.72.80.215/usuario/validate";

    public String validarToken(String token) {
        RestTemplate restTemplate = new RestTemplate();
        
        // Cria o cabeçalho com o token de autorização
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        
        HttpEntity<String> entity = new HttpEntity<>(headers);
        
        try {
            // Faz a requisição ao endpoint de validação
            @SuppressWarnings("rawtypes")
            ResponseEntity<Map> response = restTemplate.exchange(VALIDATE_URL, HttpMethod.GET, entity, Map.class);
            
            // Extrai o papel (role) do corpo da resposta, assumindo que está no campo "role"
            @SuppressWarnings("unchecked")
            Map<String, String> body = response.getBody();
            return body != null ? body.get("role") : null;
        } catch (Exception e) {
            // Retorna null caso o token seja inválido ou ocorra algum erro
            return null;
        }
    }
}
