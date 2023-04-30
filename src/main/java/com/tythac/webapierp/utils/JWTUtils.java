package com.tythac.webapierp.utils;

import com.auth0.jwt.interfaces.DecodedJWT;

/**
 * @author CXY
 * @version Create Time: 2023/4/6
 * @Description Token 資料
 * <p>
 * 生成 Token-header.payload.signature
 * 驗證 Token
 */
public interface JWTUtils {
    // 生成 Token-header.payload.signature
    String getToken(String userId, String userName);

    // 驗證 Token
    DecodedJWT verifyToken(String token);

}
