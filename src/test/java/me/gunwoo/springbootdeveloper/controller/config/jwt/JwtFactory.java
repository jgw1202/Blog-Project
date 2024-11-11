package me.gunwoo.springbootdeveloper.controller.config.jwt;


import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Builder;
import lombok.Getter;
import me.gunwoo.springbootdeveloper.config.jwt.JwtProperties;

import java.time.Duration;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import static java.util.Collections.emptyMap;

@Getter
public class JwtFactory {
    private String subject = "test@email.com";
    private Date issueAt = new Date();
    private Date expiration = new Date(
            new Date().getTime() + Duration.ofDays(14)
                    .toMillis());

    private Map<String, Object> claims = emptyMap();

    // 빌더 패턴을 사용해 설정이 필요한 데이터만 선택 결정
    @Builder
    public JwtFactory(String subject, Date issueAt, Date expiration, Map<String,Object> claims) {
        this.subject = subject != null ? subject :this.subject;
        this.issueAt = issueAt != null ? issueAt :this.issueAt;
        this.expiration = expiration != null ? expiration :this.expiration;
        this.claims = claims != null ? claims :this.claims;
    }

    public static JwtFactory withDefaultValues() {
        return JwtFactory.builder().build();
    }

    public String createToken(JwtProperties jwtProperties) {
        return Jwts.builder()
                .setSubject(subject)
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer(jwtProperties.getIssuer())
                .setIssuedAt(issueAt)
                .setExpiration(expiration)
                .addClaims(claims)
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .compact();
    }
}
