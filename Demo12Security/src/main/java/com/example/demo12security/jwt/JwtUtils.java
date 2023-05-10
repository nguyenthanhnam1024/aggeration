package com.example.demo12security.jwt;

import com.example.demo12security.config.UserDetailsImp;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class JwtUtils {

    private final String JWT_SECRET = "NGUYEN THANH NAM";
    private  final Long JWT_EXPIRATION = 3600000L;

    public String generateJwt(UserDetailsImp userDetailsImp){
        return Jwts.builder().setSubject(userDetailsImp.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() +JWT_EXPIRATION))
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    public Claims getClaimsFromJwt(String token){
        return Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();
    }

    private Boolean isExpirationJwt(Claims claims){
        return claims.getExpiration().after(new Date());
    }

    public String getUserNameFromJwt(String token){
        Claims claims = getClaimsFromJwt(token);
        if (claims != null && isExpirationJwt(claims)){
            return claims.getSubject();
        }
        return null;
    }

    public boolean validateToken(String token, HttpServletResponse httpServletResponse){
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token);
            return true;
        }catch (SignatureException e){
            httpServletResponse.setStatus(400);
        }catch (MalformedJwtException e){
            httpServletResponse.setStatus(401);
        }catch (ExpiredJwtException e){
            httpServletResponse.setStatus(402);
        }catch (UnsupportedJwtException e){
            httpServletResponse.setStatus(403);
        }catch (IllegalArgumentException e){
            httpServletResponse.setStatus(404);
        }
        return false;
    }
}

