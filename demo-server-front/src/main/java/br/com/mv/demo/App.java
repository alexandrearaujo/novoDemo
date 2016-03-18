package br.com.mv.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"br.com.mv.demo", "br.com.mv.modulo"})
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
  
}
