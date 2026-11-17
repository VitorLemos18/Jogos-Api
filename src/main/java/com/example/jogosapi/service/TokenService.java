package com.example.jogosapi.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.jogosapi.database.models.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;


    public String gerarToken(Usuario usuario) {
        try{
            Algorithm algoritimo = Algorithm.HMAC256(secret);

            return JWT.create()
                    .withIssuer("jogos-api")
                    .withSubject(usuario.getEmail())
                    .withExpiresAt(gerarDataExpiracao())
                    .sign(algoritimo);
        }catch(JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar Token: " + exception.getMessage());
        }
    }


    public String validarToken(String token){
        try{
            Algorithm algoritimo = Algorithm.HMAC256(secret);

            return JWT.require(algoritimo)
                    .withIssuer("jogos-api")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch(JWTVerificationException exception){
            return "";
        }
    }

    public Instant gerarDataExpiracao(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-02:00"));
    }

}
// DEPOIS VOLTAR AQUI E ENTENDER MELHOR A PARTE DE SEGURANÇA