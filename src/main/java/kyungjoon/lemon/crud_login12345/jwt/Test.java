package kyungjoon.lemon.crud_login12345.jwt;

public class Test {

    public static void main(String[] args){

        System.out.println("sldkfsldkf");

        String token001=JwtService.createJwt("kyungjoongo");

        System.out.println("access_token은==>"+ token001);
    }
}
