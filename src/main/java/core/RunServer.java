package core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
@EnableAutoConfiguration
@SpringBootApplication
@EnableAsync
public class RunServer {

    public static void main(String[] args) {
        SpringApplication.run(RunServer.class, args);
    }
}
