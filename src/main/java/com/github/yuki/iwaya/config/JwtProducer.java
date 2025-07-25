package com.github.yuki.iwaya.config;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

@Component
public class JwtProducer {
    
    public String createToken(String userName){
        try{

            Date expireTime = new Date();
            expireTime.setTime(expireTime.getTime() + 600000l);

            Algorithm algorithm = Algorithm.HMAC256("secret");

            String token = JWT.create()
                .withIssuer("auth0") //トークン発行者情報
                .withSubject("any token name") // トークンの主体
                .withClaim("userName", userName)
                .withExpiresAt(expireTime)
                .sign(algorithm);

            return token;

        } catch (JWTCreationException e){
            return null;
        }
    }

    public DecodedJWT verifyToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");

            JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("auth0") // トークンから復元したクレーム値が同じかをチェックする
                .build();

            // 指定されたトークンに対して検証を実施
            DecodedJWT jwt = verifier.verify(token);
            return jwt;

        } catch (JWTVerificationException e){
            e.printStackTrace();
            return null;
        }
    }

}
