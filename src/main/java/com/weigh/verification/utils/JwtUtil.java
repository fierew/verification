package com.weigh.verification.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * jwt工具
 *
 * @author xuyang
 */
public class JwtUtil {
    /**
     * 过期时间为一天
     * TODO 正式上线更换为15分钟
     */
    private static final long EXPIRE_TIME = 60 * 1000;

    /**
     * token私钥
     */
    private static final String TOKEN_SECRET = "joijsdfjlsjfljfljl5135313135";

    /**
     * 生成签名,15分钟后过期
     * @param userId 用户ID
     * @param username 用户名
     * @param role 角色
     * @return token
     */
    public static String sign(int userId, String username, String role) {
        //过期时间
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        //私钥及加密算法
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        //设置头信息
        HashMap<String, Object> header = new HashMap<>(2);
        header.put("typ", "JWT");
        header.put("alg", "HS256");
        //附带username和userID生成签名
        return JWT.create()
                .withHeader(header)
                .withClaim("username", username)
                .withClaim("userId", userId)
                .withClaim("role", role)
                .withExpiresAt(date).sign(algorithm);
    }

    /**
     * 解析Token
     * @param token jwt token
     * @return user info
     */
    public static Map<String, Claim> verity(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);

            return jwt.getClaims();
        } catch (IllegalArgumentException | JWTVerificationException e) {
            throw new RuntimeException("401");
        }

    }
}
