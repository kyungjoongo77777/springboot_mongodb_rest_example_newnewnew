package kyungjoon.lemon.crud_login12345;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class SpringbootMainApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMainApplication.class, args);
    }

}
