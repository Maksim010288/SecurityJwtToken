package com.baziuk.SpringSecuredDemo.dto;

import com.baziuk.SpringSecuredDemo.properties.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Slf4j
public class JwtAuthToken {
    JwtProperties jwt = new JwtProperties();

    public String generatedToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        List<String> rolesList = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        claims.put("roles", rolesList);

        Date inseptionDate = new Date();
        Date clamesDate = new Date(inseptionDate.getTime() + jwt.getLifetime().toMillis());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(inseptionDate)
                .setExpiration(clamesDate)
                .signWith(SignatureAlgorithm.HS256, jwt.getSecret())
                .compact();
    }

    public String getUsername(String token) {
        return getAllClaimsToken(token).getSubject();
    }

    public List<String> getRoles(String token) {
        return getAllClaimsToken(token).get("roles", List.class);
    }

    public Claims getAllClaimsToken(String token) {
        return Jwts.parser().setSigningKey(jwt.getSecret())
                .parseClaimsJws(token)
                .getBody();
    }

}



