package com.quickclinic.patient.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

@Component
public class JwtUtil {

    private final  String SECRET_KEY="very_secure_secret_key_for_jwt_quickclinic";

    private SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }
   public String extractUsername(String token){
       Claims claims = extractAllClaims(token);
       return claims.getSubject();
   }

   public List<String> extractRoles(String token){
        Claims claims = extractAllClaims(token);
        return claims.get("roles", List.class);
   }
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    public Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }
    public Boolean validateToken(String  token){
        return !isTokenExpired(token);
    }

    private Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }

}
