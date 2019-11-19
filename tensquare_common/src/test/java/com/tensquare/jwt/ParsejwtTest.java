package com.tensquare.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.text.SimpleDateFormat;

public class ParsejwtTest {
    public static void main(String[] args) {
        String token=
                "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI2NjYiLCJzdWIiOiLllrvkuJblubMiLCJpYXQiOjE1NzM0NjA4MjQsImV4cCI6MTU3MzQ2MDg1NCwicm9sZSI6ImFkbWluIn0.8JTqvcdYpet5zpn8IVDIJi8Fb2Mk4a2TL_UYuMstZTc";
        Claims claims = Jwts.parser().setSigningKey("itcast").parseClaimsJws(token).getBody();
        System.out.println("用户id:"+claims.getId());
        System.out.println("用户名字:"+claims.getSubject());
        System.out.println("登陆时间:"+new SimpleDateFormat("yyyy-MM-ss HH:mm:ss").format(claims.getIssuedAt()));
        System.out.println("过期时间:"+new SimpleDateFormat("yyyy-MM-ss HH:mm:ss").format(claims.getExpiration()));
        System.out.println("用户角色:"+claims.get("role"));
    }
}
