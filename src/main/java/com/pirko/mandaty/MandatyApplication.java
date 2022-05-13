package com.pirko.mandaty;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class MandatyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MandatyApplication.class, args);

    }
}
