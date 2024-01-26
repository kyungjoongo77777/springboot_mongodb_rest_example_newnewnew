package kyungjoon.lemon.crud_login12345.jwt;

public class Test2 {

    public static void main(String[] args) throws Exception {

        String access_token= "eyJ0eXBlIjoiand0IiwiYWxnIjoiSFMyNTYifQ.eyJ1c2VySWQiOiJreXVuZ2pvb25nbyIsImlhdCI6MTY5ODAyODA3NCwiZXhwIjoxNzI5NTY0MDc0fQ.fNpgHMx9-VeMl8XuRUUtwxoP0C5QN3fmTfI-1N_rj8U";

        String userId=JwtService.getUserId(access_token);

        System.out.println("userId====>"+ userId);
    }
}
