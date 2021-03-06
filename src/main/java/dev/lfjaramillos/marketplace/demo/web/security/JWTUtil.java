package dev.lfjaramillos.marketplace.demo.web.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {

    private static  final String KEY="market";
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder().setSubject(userDetails.getUsername()).setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS512, KEY).compact();
    }

    public boolean validateToken(String token, UserDetails userDetails){
            return userDetails.getUsername().equals(getUserName(token)) && !isTokenExperied(token);
    }

    public String getUserName (String toke){
        return getClaims(toke).getSubject();
    }
    public boolean isTokenExperied (String toke){
        return getClaims(toke).getExpiration().before(new Date());
    }
    public Claims getClaims(String token){
        return Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
    }
}
