package com.astamatii.spring.springcore;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ComputerAnnotation {
	
	private static int currentId;
	private int id;
	private MusicPlayerAnnotation musicPlayer;
	
	public ComputerAnnotation(@Qualifier("musicPlayerAnnotation") MusicPlayerAnnotation musicPlayer) {
		currentId++;
		this.id = currentId;
		this.musicPlayer = musicPlayer;
	}
	
	public String play(Genre genre) {
		return "Computer " + id + " " + musicPlayer.playMusic(genre);
	}
		
}
