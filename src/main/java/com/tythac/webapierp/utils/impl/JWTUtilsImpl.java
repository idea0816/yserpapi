package com.tythac.webapierp.utils.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.tythac.webapierp.utils.JWTUtils;
import org.springframework.stereotype.Component;

import java.util.Calendar;

/**
 * @author CXY
 * @version Create Time: 2023/4/6
 * @Description Token 資料
 * <p>
 * 生成 Token-header.payload.signature
 * 驗證 Token
 */
@Component
public class JWTUtilsImpl implements JWTUtils {
    // Key
    private static final String signature = "!q2W#e4R%t6Y&u8I(o0P";

    // 生成 Token-header.payload.signature
    @Override
    public String getToken(String userId, String userName) {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE, 1); // 1天過期
        String token = JWT.create().withClaim("userId", userId)
                .withClaim("userName", userName)
                .withExpiresAt(instance.getTime())  // 指定 Token 過期時間
                .sign(Algorithm.HMAC256(signature));   // 簽名
        return token;
    }

    // 驗證 Token
    public DecodedJWT verifyToken(String token) {
        return JWT.require(Algorithm.HMAC256(signature)).build().verify(token);
    }

}
