package uz.cus.forumproject.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import uz.cus.forumproject.model.Form;

import javax.crypto.SecretKey;

@Service
public class TokenServiceImpl {


    public String generateToken(Form form){
        return Jwts.builder()
                .subject(form.getId().toString())
                .signWith(key())
                .compact();
    }
    public Claims parseAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(key())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    public SecretKey key() {
        return Keys.hmacShaKeyFor("randomnmadurlayoze0jsefsjfosejfsofjseofjseoifjsofjseoifjseofsjefoiejfisojfibtawadm".getBytes());
    }
}
