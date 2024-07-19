package com.ms.player;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * Springboot main class to create application context 
 * 
 * Please see the {@link com.ms.player.PlayerApplication} class
 * 
 * @author 047929
 * @version 1.0.0
 *
 */
@SpringBootApplication
public class PlayerApplication {
	
    /**
     * MessageSource bean configuration.
     * 
     * @return MessageSource
     */
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    /**
     * LocalValidatorFactoryBean bean configuration.
     * 
     * @return LocalValidatorFactoryBean
     */
    @Bean
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }

	/**
	 * Bootstrap the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(PlayerApplication.class, args);
	}

}
