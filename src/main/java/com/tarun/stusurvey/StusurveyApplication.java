package com.tarun.stusurvey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.tarun.stusurvey.controller", "com.tarun.stusurvey.Entity", "com.tarun.stusurvey.repository", "com.tarun.stusurvey.service"})

public class StusurveyApplication {

    public static void main(String[] args) {
        SpringApplication.run(StusurveyApplication.class, args);
    }
}