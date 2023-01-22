package Osherz.barberShop.Services;

import Osherz.barberShop.Models.Person;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;

@Service
public class TokenService {

  private Instant now = Instant.now();
  private String seed = "o40dCNjd8mmDN2+/nfHdIB2ZWta80foXqDx2rouL4nw=";
  private byte[] secret = Base64.getDecoder().decode(seed);

  public String createToken(int personId) {
    return Jwts.builder()
        .setSubject("barbershop")
        .claim("person_id", personId)
        .setIssuedAt(Date.from(now))
        .signWith(Keys.hmacShaKeyFor(secret))
        .compact();
  }

  public String cleanReceivedToken(String receivedToken) {
    return receivedToken.substring(receivedToken.indexOf(' '));
  }

  public int parseToken(String token) {
    token = cleanReceivedToken(token);
    Jws<Claims> parsed =
        Jwts.parser()
            .requireSubject("barbershop")
            .setAllowedClockSkewSeconds(65)
            .setSigningKey(Keys.hmacShaKeyFor(secret))
            .parseClaimsJws(token);

    return parsed.getBody().get("person_id", Integer.class);
  }
}
