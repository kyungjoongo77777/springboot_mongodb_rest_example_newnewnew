package kyungjoon.lemon.crud_login12345.jwt;
//라이브러리 생략

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import kyungjoon.lemon.crud_login12345.utils.Constants;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Date;



@Service
public class JwtService {

     static String JWT_SECRET_KEY ="ldkflaskdflaslfk248329432894";

    /*
    JWT 생성
    @param userNum
    @return String
     */
    public static String createJwt(String userId){
        Date now = new Date();
        return Jwts.builder()
                .setHeaderParam("type","jwt")
                .claim("userId",userId)
                .setIssuedAt(now)
                .setExpiration(new Date(System.currentTimeMillis()+ (1000L * 60 * 60 * 24 * 365))) //발급날짜 계산
                .signWith(SignatureAlgorithm.HS256, Constants.SECRET_KEY) //signature 부분
                .compact();
    }

    /*
    Header에서 X-ACCESS-TOKEN 으로 JWT 추출
    @return String
     */
    public String getJwt(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        return request.getHeader("X-ACCESS-TOKEN");
    }

    public static String getUserId(String accessToken) throws Exception{
        //1. JWT 추출
        if(accessToken == null || accessToken.length() == 0){
            throw new Exception("EMPTY_JWT");
        }

        // 2. JWT parsing
        Jws<Claims> claims;


        try{
            claims = Jwts.parser()
                    .setSigningKey(JWT_SECRET_KEY)
                    .parseClaimsJws(accessToken);
        } catch (Exception ignored) {
            throw new Exception("INVALID_JWT");
        }

        // 3. userNum 추출
        return claims.getBody().get("userId",String.class);
    }



}
