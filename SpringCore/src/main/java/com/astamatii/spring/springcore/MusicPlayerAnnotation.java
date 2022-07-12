package com.astamatii.spring.springcore;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MusicPlayerAnnotation {
	
	@Value("${musicPlayer.name}")
	private String name;
	@Value("${musicPlayer.volume}")
	private int volume;
	
//	@Autowired 
//	@Qualifier("popBean")
	private Music music1;
	
//	@Autowired 
//	@Qualifier("rockBean")
	private Music music2;
	
	public MusicPlayerAnnotation() {}
	
	/**
	 * <p>This is <i>MusicPlayerAnnotation</i> public constructor.<br>
	 * It initiates the music field with specified <i>Music</i> interface object.</p> 
	 * <p>It is used for demonstration of creating Beans by Annotations.</p>
	 * <p>Also it is used for demonstration of the Inversion of Control (IoC) 
	 * and for manual Dependency Injection (DI) in the <i>main</i> function.</p>
	 *  
	 * @param music is used for <i>music</i> field initialization with a <i>Music</i> interface object.
	 */
	@Autowired //Works when it`s only 1 Bean of <I>Music type created 
	public MusicPlayerAnnotation(  @Qualifier("popBean")  Music music1,
			@Qualifier("rockBean2")  Music music2) {
		this.music1 = music1;
		this.music2 = music2;
	}
	
	@PostConstruct
	public void doMyInit() {
		System.out.println("Doing init-method for a MusicPlayerAnnotation Bean: " + this.hashCode() + "\n");
	}	
	
	@PreDestroy
	public void doMyDestroy() {
		System.out.println("\nDoing destroy-method for a MusicPlayerAnnotation Bean " + this.hashCode());
	}
	
	public Music getMusic1() { return music1; }
	public Music getMusic2() { return music2; }
	public String getName() { return name; }
	public int getVolume() { return volume; }
	
//	@Autowired 
//	@Qualifier("popBean") 
	public void setMusic( Music music1) {
	this.music1 = music1; }
	 
	public String playMusic() {
		return "Playing: " + music1.getSong();
	}
	
	public String playMusic(Genre genre) {
		
		switch(genre) {
			case POP:
				return "Playing: " + music1.getSong();
			case ROCK:
				return "Playing: " + music2.getSong();
			default:
				return "Wrong genre";
		}
	}	
}
