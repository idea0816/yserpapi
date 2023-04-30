package com.tythac.webapierp;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;

@SpringBootTest
class WebapierpApplicationTests {

    @Test
    void contextLoads() {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE, 7);
//        instance.add(Calendar.SECOND, 20);
        String token = JWT.create().withClaim("userid", "99999")
                .withClaim("username", "eric")
                .withExpiresAt(instance.getTime())  // 指定 Token 過期時間
                .sign(Algorithm.HMAC256("!q2W#e4R%t6Y&u8I(o0P"));   // 簽名
        System.out.println(token);
    }

    @Test
    public void test(){
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("!q2W#e4R%t6Y&u8I(o0P")).build();
//        DecodedJWT verify = jwtVerifier.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2ODAyNTU4MzIsInVzZXJpZCI6Ijk5OTk5IiwidXNlcm5hbWUiOiJlcmljIn0.2psXVqEAiaxMpx8GgFO3-RCXnlHo-dbkX9jguMrCnmc");

//        System.out.println(verify.getClaim("userid"));
//        System.out.println("過期時間 ： "+verify.getExpiresAt());
    }

}
