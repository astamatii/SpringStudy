package com.astamatii.spring.springcore;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.astamatii.spring.springcore")
@PropertySource("classpath:musicPlayer.properties")
public class SpringConfig {
	
	@Bean //Manual bean setting
	PopMusicAnnotation /*id*/popBean2() {
		return new PopMusicAnnotation();
	}
	
	@Bean //Manual bean setting
	RockMusicAnnotation /*id*/rockBean3() {
		return new RockMusicAnnotation();
	}
    
    @Bean //Manual bean setting
    MusicPlayerAnnotation /*id*/musicPlayerAnnotation2() {
        return new MusicPlayerAnnotation(popBean2(), rockBean3());
    }
    
    @Bean
    public ComputerAnnotation computer3() {
    	return new ComputerAnnotation(musicPlayerAnnotation2());
    }
}
