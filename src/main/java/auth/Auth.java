package auth;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.TextCodec;

public class Auth {
    public static byte[] JWT_KEY = TextCodec.BASE64.decode("Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=");

    public static AuthToken parseToken(String jwt) {
        if (jwt == null || jwt.isEmpty()) {
            return null;
        }

        try {
            String subject = Jwts.parser()
                    .setSigningKey(Auth.JWT_KEY)
                    .parseClaimsJws(jwt)
                    .getBody()
                    .getSubject();

            return new AuthToken(subject);
        } catch (JwtException e) {
            return null;
        }
    }
}
