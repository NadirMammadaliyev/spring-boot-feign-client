package az.nadir.springbootfeignclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("client")
public class SpringBootFeignClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootFeignClientApplication.class, args);
    }

}
