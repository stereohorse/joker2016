package ru.stereohorse.joker2016;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    private HealthStatus appStatus = UP;


    @RequestMapping("/health")
    public ResponseEntity<HealthStatus> getAppHealth() {
        return status(appStatus == UP ? OK : INTERNAL_SERVER_ERROR)
            .body(appStatus);
    }

    @RequestMapping(value = "/health/toggle", method = POST)
    public ResponseEntity<HealthStatus> toggleHealth() {
        appStatus = appStatus == UP ? DOWN : UP;

        return ok(appStatus);
    }


    public static void main(String[] args) {
        SpringApplication.run(SampleApi.class, args);
    }
}


enum HealthStatus {
    UP, DOWN
}