package com.cnu.teamProj.teamProj.security.jwt;

import com.cnu.teamProj.teamProj.security.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTGenerator {
    public String generateToken(Authentication authentication){
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime()+ SecurityConstants.JWT_EXPIRATION);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SecurityConstants.SECRET_KEY, SignatureAlgorithm.ES256)
                .compact();
    }
    public String getUserNameFromJWT(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SecurityConstants.SECRET_KEY).build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();//
    }

    public boolean validateToken(String token){
        try{
            Jwts.parserBuilder().setSigningKey(SecurityConstants.SECRET_KEY).build().parseClaimsJws(token);
            return true;
        } catch(Exception ex){
            throw new AuthenticationCredentialsNotFoundException("JWT was expired or incorrect");
        }
    }
}
