package team16.sidedish;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@ComponentScan(basePackages = {"team16"})
@SpringBootApplication
public class SidedishApplication {

    public static void main(String[] args) {
        SpringApplication.run(SidedishApplication.class, args);
    }

}
