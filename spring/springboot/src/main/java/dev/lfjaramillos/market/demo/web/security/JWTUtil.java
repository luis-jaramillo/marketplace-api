package dev.lfjaramillos.market.demo.web.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {

    private static  final String key="market-demo";
    public String generateToken(UserDetails userDetails){
        return Jwts.builder().setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis() + 1000 * 60*60 +10))
                .signWith(SignatureAlgorithm.ES256,key).compact();
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
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
    }
}
