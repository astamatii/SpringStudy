package com.astamatii.spring.springcore;

import java.util.Random;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p>This class contains the <i>main()</i> function and, as result, the first entries to the application.</p> 
 * </p>Here the <b>Spring</b> bean objects are created and analyzed.</p>
 *  
 * @author Alexandr Stamatii
 */
//@SpringBootApplication
public class TestSpringCoreApp {

	/**
	 * <p>The <i>main()</i> is the static function used for application entries.</p> 
	 * <p>Here the bean objects are created.<br> 
	 * Using <i>println</i> messages in console, 
	 * are revealed their distinctions and similarities.</p>
	 * 
	 * @param args are the CLI arguments typed on starting the application (not used). 
	 */
	public static void main(String[] args) {
				
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		AnnotationConfigApplicationContext context2 =  new AnnotationConfigApplicationContext(SpringConfig.class);
				
		System.out.println("__________________________________________________");
		
		//Dependency Injection by Spring
		//scope = singleton (by default) 
		System.out.println("\nAnalysing singleton bean objects: musicPlayerBean1, musicPlayerBean2 made by context musicPlayer3");
		
		MusicPlayerXML musicPlayerBean1 = context.getBean("musicPlayer3", MusicPlayerXML.class);
		MusicPlayerXML musicPlayerBean2 = context.getBean("musicPlayer3", MusicPlayerXML.class);
		
		System.out.println("\nValue from the properties file using setters:");
		System.out.println("Name value: " + musicPlayerBean1.getName());
		System.out.println("Volume value: " + musicPlayerBean1.getVolume());
		
		System.out.println("\nSong List value defined in constructor by XML:");
		musicPlayerBean1.playList();
		
		//verify objects (singleton)
		System.out.println("\nVerify singleton scope for operation musicPlayerBean1==musicPlayerBean2:");
		System.out.println(musicPlayerBean1 == musicPlayerBean2 ? "true - Same object" : "false - Different ojects");
		
		System.out.println("\nVerify singleton scope for both variables by hash code:");
		System.out.println("HashCode for musicPlayerBean1 - " + musicPlayerBean1.hashCode());
		System.out.println("HashCode for musicPlayerBean2 - " + musicPlayerBean2.hashCode());
		
		System.out.println("\nChanging volume to 30 only to musicPlayerBean1."
				+ "\nVerify volume for both variables:");
		musicPlayerBean1.setVolume(30);
		System.out.println("musicPlayerBean1 volume = " + musicPlayerBean1.getVolume() );
		System.out.println("musicPlayerBean2 volume = " + musicPlayerBean2.getVolume() );
		
		System.out.println("__________________________________________________");
		
		//scope = prototype
		System.out.println("\nAnalysing prototype bean objects: musicPlayerBean3, musicPlayerBean4 made by context musicPlayer4\n");
		
		MusicPlayerXML musicPlayerBean3 = context.getBean("musicPlayer4", MusicPlayerXML.class);
		MusicPlayerXML musicPlayerBean4 = context.getBean("musicPlayer4", MusicPlayerXML.class);
		
		//verify objects (prototype)
		System.out.println("Verify prototype scope for operation musicPlayerBean3==musicPlayerBean4:");
		System.out.println(musicPlayerBean3 == musicPlayerBean4 ? "true - Same object" : "false - Different ojects");
		
		System.out.println("\nVerify prototype scope for both variables by hash code:");
		System.out.println("HashCode for musicPlayerBean3 - " + musicPlayerBean3.hashCode());
		System.out.println("HashCode for musicPlayerBean4 - " + musicPlayerBean4.hashCode());
		
		System.out.println("\nChanging volume to 30 only to musicPlayerBean3."
				+ "\nVerify volume for both variables:");
		musicPlayerBean3.setVolume(30);
		System.out.println("musicPlayerBean3 volume = " + musicPlayerBean3.getVolume() );
		System.out.println("musicPlayerBean4 volume = " + musicPlayerBean4.getVolume() );
		
		System.out.println("__________________________________________________\n");
		
		System.out.println("Verify the Bean creation by annotations + XML config:");
		
		//Access to <I>Music for manual DI 
		Music rockBean = context.getBean("rockBean2", Music.class);
		PopMusicAnnotation popBean = context.getBean("popBean", PopMusicAnnotation.class);
		
		//Manual Dependency Injection 
		MusicPlayerAnnotation musicPlayerBean5 = new MusicPlayerAnnotation(popBean, rockBean);
		//It won`t do automatically initializing and destroy methods for musicPlayerBean5!
		
		//Dynamic Dependency Injection (No need to create PopBean manually
		MusicPlayerAnnotation musicPlayerBean6 = context.getBean("musicPlayerAnnotation", MusicPlayerAnnotation.class);
						
		System.out.println("\nHashCode for musicPlayerBean5 from popBean (" + musicPlayerBean5.getMusic1().hashCode() + ")"
				+ "\n and rockBean(" + musicPlayerBean5.getMusic2().hashCode() + ") made manual - " + musicPlayerBean5.hashCode());
		System.out.println("\nHashCode for musicPlayerBean6 from popBean (" + musicPlayerBean6.getMusic1().hashCode() + ")"
				+ "\n and rockBean(" + musicPlayerBean6.getMusic2().hashCode() + ") made dynamic - " + musicPlayerBean6.hashCode());
		System.out.println("\nValues:\nname: " + musicPlayerBean6.getName() + "\nvolume: " + musicPlayerBean6.getVolume() + "\n");
		
		System.out.println(musicPlayerBean6.playMusic());
		
		ComputerAnnotation computer = context.getBean("computerAnnotation", ComputerAnnotation.class);
		
		System.out.println(computer.play(Genre.values()[new Random().nextInt(2)]));
		
		System.out.println("__________________________________________________\n");
		
		System.out.println("Verify the Bean creation by annotations + Java config:");
		
		MusicPlayerAnnotation musicPlayerBean7 = context2.getBean("musicPlayerAnnotation", MusicPlayerAnnotation.class);
		
		ComputerAnnotation computer2 = context2.getBean("computerAnnotation", ComputerAnnotation.class);
		
		System.out.println(musicPlayerBean7.playMusic());
		System.out.println(computer2.play(Genre.values()[new Random().nextInt(2)]));
		
		System.out.println("\nVerify manual Bean creation directly in Java config:\n");
				
		ComputerAnnotation computer3 = context2.getBean("computer3", ComputerAnnotation.class);
		
		System.out.println(computer3.play(Genre.values()[new Random().nextInt(2)]));
		
		System.out.println("__________________________________________________\n");
		
		context.close();
		
		System.out.println("__________________________________________________\n");
		
		System.out.println("context object is closed");
		
		System.out.println("__________________________________________________\n");
		
		context2.close();
		
		System.out.println("__________________________________________________\n");
		
		System.out.println("context2 object is closed");
		
		System.out.println("__________________________________________________");
		
		//SpringApplication.run(Testwebapp1Application.class, args);
	}

}
