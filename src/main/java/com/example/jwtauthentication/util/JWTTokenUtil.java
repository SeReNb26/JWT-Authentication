package com.example.jwtauthentication.util;

import java.io.ObjectStreamClass;
import java.io.Serializable;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTTokenUtil implements Serializable {
    private static final long SERIAL_VERSION_UID = ObjectStreamClass.lookup(JWTTokenUtil.class).getSerialVersionUID();

    @Value("${jwt.secret}")
    private String secret;

    public Optional<String> getUsernameFromToken(String token) {
        String decodedPayload = decodePayload(token.substring(token.indexOf(".") + 1, token.lastIndexOf(".")));
        try {
            JSONObject object = new JSONObject(decodedPayload);
            String username = object.getString("sub");
            return Optional.of(username);
        } catch (JSONException e) {
            return Optional.empty();
        }
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final Optional<String> username = getUsernameFromToken(token);
        return username.isPresent()
                && getSignatureFromToken(token).equals(getSignatureFromToken(generateToken(userDetails)));
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("pass", userDetails.getPassword());
        return doGenerateToken(claims, userDetails.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject)
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    private String decodePayload(String payload) {
        final byte[] payloadByteArray = Base64.getDecoder().decode(payload);
        StringBuilder builder = new StringBuilder();
        for (byte b : payloadByteArray) {
            builder.append((char) b);
        }
        return builder.toString();
    }

    private String getSignatureFromToken(String token) {
        return token.substring(token.lastIndexOf(".") + 1);
    }
}
