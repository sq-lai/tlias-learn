package org.lsq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class SpringbootwebprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootwebprojectApplication.class, args);
    }

}
