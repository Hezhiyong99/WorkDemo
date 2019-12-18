package com.ketai.edu.util;

import com.bdqn.common.constants.ResultCodeEnum;
import com.bdqn.common.exception.KetaiException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.ServletException;
import java.util.Date;

/*
 *生成token 的工具类
 */
public class JwtUtil {
    final static String base64EncodedSecretKey = "2168a0c2-7205-4162-a3c6-d50305374792";//私钥uuid
    final static long TOKEN_EXP = 1000 * 60 * 30;//过期时间,默认设置30分钟

    public static String getToken(String userName) {
        System.out.println(userName);
        return Jwts.builder()
                .setSubject(userName)
                .claim("roles", "admin")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXP)) /*过期时间*/
                .signWith(SignatureAlgorithm.HS256, base64EncodedSecretKey)
                .compact();
    }

    /**
     * 检查token,只要不正确就会抛出异常
     **/
    public static Claims checkToken(String token) throws ServletException {
        try {
            Claims claims =
                    Jwts.parser().setSigningKey(base64EncodedSecretKey).parseClaimsJws(token).getBody();
            return claims;
        } catch (ExpiredJwtException e1) {
            throw new
                    KetaiException(ResultCodeEnum.USERLOGIN_INVALID_ERROR);
        } catch (Exception e) {
            throw new KetaiException(ResultCodeEnum.USERUNLOGIN_ERROR);
        }
    }

}
