package com.tensquare.jwt;


import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class CreateJwt {
    public static void main(String[] args) {
        //指定头部和载荷,签名不用管，是他们俩+盐生成的
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId("666")
                .setSubject("喻世平")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256,"itcast")
                .setExpiration(new Date(new Date().getTime()+30000))
                .claim("role","admin");
        System.out.println(jwtBuilder.compact());
    }
}
