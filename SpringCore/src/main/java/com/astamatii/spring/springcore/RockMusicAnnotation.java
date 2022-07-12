package com.astamatii.spring.springcore;

import java.util.Random;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * <p>The <i>RockMusicAnnotation</i> class
 * <p>This class is used by the prototype scope bean <i>rockBean</i>
 * from <b>applicationContext.xml</b> file.</p>
 * 
 * @author Alexandr Stamatii
 *
 */
@Component("rockBean2")
@Scope("prototype")
public class RockMusicAnnotation implements Music{
		
	private String [] songs = {"Metal Wings", "Hard Rock Alleluya", "AC/DC"};
	
	@PostConstruct
	public void doMyInit() {
		System.out.println("Doing init-method for a RockMusicBean: " + this.hashCode() + "\n");
	}	
	
	@PreDestroy
	public void doMyDestroy() {
		System.out.println("\nDoing destroy-method for a RockMusicBean " + this.hashCode());
	}
	
	@Override
	public String getSong() {
		return songs[new Random().nextInt(songs .length)];
	}
	
}
