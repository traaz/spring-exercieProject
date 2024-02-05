package com.example.proje.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {
    private String SECRET ="404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";
    //secret ile key olusturacaz ve  jwt'yi sifreliyoz ve cozumleme yapiliyor.

    public String generateToken(String name){
        Map<String, Object> claims = new HashMap<>(); //payload  hepsi claim
        return createToken(claims, name);

    }

    public Boolean validateToken(String token, UserDetails userDetails){ //payloadda neler var bakmamiz lazim
        String name = extractUser(token);
        Date expiraionDate = extractExpiration(token);

        return userDetails.getUsername().equals(name) && expiraionDate.after(new Date()); //new date'ten sonra olmasi lazim
     }

    private Date extractExpiration(String token){
        Claims claims = Jwts            //payload icindeki key value degerleri ilk once hepsini alıyoz sonra sadece expritaion return ediyoz.
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getExpiration();

    }

    public String extractUser(String token){
        Claims claims = Jwts            //payload icindeki key value degerleri ilk once hepsini alıyoz sonra sadece subject yani username return ediyoz.
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();

    }

    private String createToken(Map<String, Object> claims, String name){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(name)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 30)) //30 sn gecerli
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    private Key getSignKey(){  //secret ile olusturdugum key ile  jwti tokeni sifreliyoz. bu secret'i bilmeyen kimse extract edemez
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes); //SECRET2i bytleara donusturduk ve bir key olusturduk. bytlar uzerinden olusturduk. tokenı
    }
}
