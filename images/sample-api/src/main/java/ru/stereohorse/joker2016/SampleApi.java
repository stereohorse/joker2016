package ru.stereohorse.joker2016;


import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

import static java.lang.Thread.sleep;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static ru.stereohorse.joker2016.HealthStatus.DOWN;
import static ru.stereohorse.joker2016.HealthStatus.UP;


@SpringBootApplication
@RestController
public class SampleApi {

    private HealthStatus healthState = UP;


    @Value("${start.delay_millis:0}")
    private Long startDelayMillis;

    @Value("${job.length_millis:200}")
    private Long jobLengthMillis;


    @PostConstruct
    @SneakyThrows
    public void delayStart() {
        sleep(startDelayMillis);
    }


    @RequestMapping("/health")
    public ResponseEntity<HealthStatus> getAppHealth() {
        return status(healthState == UP ? OK : INTERNAL_SERVER_ERROR)
            .body(healthState);
    }

    @RequestMapping(value = "/health/toggle", method = POST)
    public ResponseEntity<HealthStatus> toggleHealth() {
        healthState = healthState == UP ? DOWN : UP;
        return ok(healthState);
    }


    @SneakyThrows
    @RequestMapping("/something")
    public String getSomething() {
        sleep(jobLengthMillis);
        return "take this!";
    }


    public static void main(String[] args) {
        SpringApplication.run(SampleApi.class, args);
    }
}


enum HealthStatus {
    UP, DOWN
}