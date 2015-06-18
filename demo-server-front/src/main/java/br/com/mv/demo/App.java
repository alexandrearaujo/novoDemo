package br.com.mv.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@SpringBootApplication
@ComponentScan({"br.com.mv.demo", "br.com.mv.modulo"})
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
    
    
    @Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource srbms = new ReloadableResourceBundleMessageSource();
		srbms.setDefaultEncoding("UTF-8");
		srbms.setBasenames("classpath:org/springframework/security/messages",
				"classpath:org/hibernate/validator/ValidationMessages",
				"classpath:/messagesApp/messages",
				"classpath:/messages/messages");
		return srbms;
	}
}
