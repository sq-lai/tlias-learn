package org.lsq;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class SpringbootwebprojectApplicationTests {

    @Test
    public void jwtGenTest(){

        Map<String, Object> claim = new HashMap<String, Object>();
        claim.put("usrname", "admin");
        claim.put("pwd", "123456");
        String jwts = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,"lsq666")
                .setClaims(claim)
                .setExpiration(new Date(System.currentTimeMillis()+24 * 3600 * 1000))
                .compact();
        System.out.println(jwts);

    }

    @Test
    public void iwtParseTest(){
        Claims claims = Jwts.parser().setSigningKey("lsq666").parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJwd2QiOiIxMjM0NTYiLCJleHAiOjE3Mjg0NzgxMzMsInVzcm5hbWUiOiJhZG1pbiJ9.3BbehhRzraGgpR7sx_Z_7yGZ3q8mF2ppTnRSlj7nOrQ")
                .getBody();
        System.out.println(claims);
    }

}
