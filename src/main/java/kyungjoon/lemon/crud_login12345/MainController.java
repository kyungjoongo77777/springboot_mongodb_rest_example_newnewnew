package kyungjoon.lemon.crud_login12345;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String main() {
        System.out.println("main");
        return "main";
    }
}
