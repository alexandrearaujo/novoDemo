package br.com.mv.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableSpringDataWebSupport
//@EnableJpaRepositories(basePackageClasses={NovoTeste.class, User.class, TipoFrequencia.class, Jsr310JpaConverters.class})
//@EnableJpaRepositories({"br.com.mv.modulo.repository", "br.com.mv.demo.repository"})
//@EnableJpaRepositories(basePackageClasses={NovoTeste.class, NovoTesteRepository.class, User.class, UserRepository.class, TipoFrequencia.class, Jsr310JpaConverters.class})
//@EntityScan({"br.com.mv.demo.model", "br.com.mv.geral.model", "org.springframework.data.jpa.domain.support", "br.com.mv.modulo.model"})
//@EntityScan(basePackageClasses={NovoTeste.class, User.class, TipoFrequencia.class, Jsr310JpaConverters.class})
@EnableJpaRepositories("br.com.mv.demo.repository")
@ComponentScan({"br.com.mv.modulo", "br.com.mv.demo"})
public class App extends WebMvcAutoConfigurationAdapter {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
    
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//      LocalContainerEntityManagerFactoryBean lemfb = new LocalContainerEntityManagerFactoryBean();
//      lemfb.setDataSource(dataSource());
//      lemfb.setJpaVendorAdapter(jpaVendorAdapter());
//      lemfb.setPackagesToScan("com.acme");
//      return lemfb;
//    }
    
    @Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource srbms = new ReloadableResourceBundleMessageSource();
		srbms.setDefaultEncoding("UTF-8");
		srbms.setBasenames("classpath:org/springframework/security/messages",
				"classpath:org/hibernate/validator/ValidationMessages",
				"classpath:/messages/messages",
				"classpath:/messagesApp/messages");
		return srbms;
	}
}
