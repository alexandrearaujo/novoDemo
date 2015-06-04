package br.com.mv.demo;

import org.springframework.boot.test.SpringApplicationConfiguration;

import br.com.mv.modulo.WebDriverTestsConfig;

@SpringApplicationConfiguration(classes = App.class)
public abstract class DemoWebDriverTestsConfig extends WebDriverTestsConfig {

}
