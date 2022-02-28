package com.example.pocspringbootmicrometerinflux.controller;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/main")
public class MainController {

    @GetMapping("/hello")
    public String hello(@RequestParam Integer number) {
        String hello = "Hello ";

        if (number % 2 == 0) {
            increaseCountSuccess();
        } else {
            increaseCountError();
        }

        return hello + number;
    }

    private void increaseCountError() {
        Counter counter = Metrics.counter("main_hello", "status", "ERROR");
        counter.increment();
    }

    private void increaseCountSuccess() {
        Counter counter = Metrics.counter("main_hello", "status", "SUCCESS");
        counter.increment();
    }
}
