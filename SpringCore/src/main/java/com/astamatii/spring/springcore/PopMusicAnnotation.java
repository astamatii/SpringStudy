package com.astamatii.spring.springcore;

import java.util.Random;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Creating a bean with annotations
 * 
 * @author Alexandr Stamatii
 *
 */
@Component("popBean")
@Scope("singleton")
public class PopMusicAnnotation implements Music{
	
	String [] songs = {"Satisfaction", "Poker Face", "Hey Ya!"};
	
	@PostConstruct
	public void doMyInit() {
		System.out.println("Doing init-method for a PopMusicAnnotation Bean: " + this.hashCode() + "\n");
	}	
	
	@PreDestroy
	public void doMyDestroy() {
		System.out.println("\nDoing destroy-method for a PopMusicAnnotation Bean " + this.hashCode());
	}
	
	@Override
	public String getSong() {
		return songs[new Random().nextInt(songs.length)];
	}
}
