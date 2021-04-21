package team16.sidedish;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"team16.dummy"})
@SpringBootApplication
public class SidedishApplication {

    public static void main(String[] args) {
        SpringApplication.run(SidedishApplication.class, args);
    }

}
