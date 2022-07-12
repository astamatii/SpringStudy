package com.astamatii.spring.springcore;

import java.util.ArrayList;
import java.util.List;


/** 
 *<style>
 * table, th {
 * border: 1px solid black;
 * border-collapse: collapse;
 * padding-left: 10px;
 * padding-right: 10px;
 * text-align: left;}
 * </style>
 * 
 * <p><i>MusicPlayerXML</i> class includes the following <b>fields</b>:</p>
 *
 * <p><table>
 * <tr> <th><b>Field name</b> </th> <th><b>Description</b> </th></tr>
 * <tr> <th><i>musicList</i> </th> <th> private collection of <i>Music</i> interface objects</th></tr>
 * <tr> <th><i>music</i> </th> <th> private <i>Music</i> interface object</th></tr>
 * <tr> <th><i>name</i> </th> <th>private <i>String</i> type variable </th></tr>
 * <tr> <th><i>volume</i> </th> <th>private <i>integer</i> type variable </th></tr>
 * </table></p>
 * 
 * <p>This class includes the following constructors:</p>
 * 
 * <p><table>
 * <tr> <th><b>Constructor</b> </th> <th><b>Description</b> </th></tr>
 * <tr> <th><i>MusicPlayerXML()</i> </th> <th>public empty constructor </th></tr>
 * <tr> <th><i>MusicPlayerXML(music)</i> </th> <th>public constructor with <i>music</i> parameter</th></tr>
 * <tr> <th><i>MusicPlayerXML(musicList)</i> </th> <th>private constructor with <i>musicList</i> parameter </th></tr>
 * </table></p>
 * 
 * <p>This class includes the following methods:</p>
 * 
 * <p><table>
 * <tr> <th><b>Method</b> </th> <th><b>Description</b> </th></tr>
 * <tr> <th><i>doMyInit()</i> </th> <th>The public method applied after object is initiated. The <i>init-method</i> for beans </th></tr>
 * <tr> <th><i>doMyDestroy()</i> </th> <th>The public method that destroys the object. The <i>destroy-method</i> for beans </th></tr>
 * <tr> <th><i>getMusicPlayer()</i> </th> <th>The public method that does <i>MusicPlayer</i> object initialization. Is the <i>factory-method</i> for beans </th></tr>
 * <tr> <th><i>setMusic()</i> </th> <th>The setter for <i>music</i> field </th></tr>
 * <tr> <th><i>setName()</i> </th> <th>The setter for <i>name</i> field </th></tr>
 * <tr> <th><i>getName()</i> </th> <th>The getter for <i>name</i> field </th></tr>
 * <tr> <th><i>setVolume()</i> </th> <th>The setter for <i>volume</i> field </th></tr>
 * <tr> <th><i>getVolume()</i> </th> <th>The getter for <i>volume</i> field </th></tr>
 * <tr> <th><i>playMusic()</i> </th> <th>The public method that prints the <i>music.getSong()</i> value </th></tr>
 * <tr> <th><i>playList()</i> </th> <th> The public method that prints the <i>getSong()</i> value for all objects in <i>musicList</i> collection </th></tr>
 * </table></p>
 * 
 * 
 * @author Alexandr Stamatii
 */

public class MusicPlayerXML {
	
	private List<Music> musicList = new ArrayList<Music>();
	private Music music;
	private String name;
	private int volume;

	/**
	 * <p>This is <i>MusicPlayerXML</i> empty public constructor.</p>
	 * <p>It`s used for demonstration of the Dependency Injection, via setter 
	 * and references to other beans specified in <i>musicPlayer2</i> bean 
	 * from the <b>applicationContext.xml</b> file.</p>
	 */
	public MusicPlayerXML() {};
		
	/**
	 * <p>This is <i>MusicPlayerXML</i> private constructor.<br>
	 * It initiates the musicList field with specified collection of <i>Music</i> interface objects.</p>
	 * <p>It has private modifier to forbid creation of the object by "<i>new</i>" keyword 
	 * in manual mode.</p>
	 * <p>It is used for demonstration of Dependency Injection, using external properties file, 
	 * constructor, setter and references specified in <i>musicPlayer3</i> and <i>musicPlayer4</i> beans 
	 * from the <b>applicationContext.xml</b> file.</p>   
	 *  
	 * @param musicList is used for <i>musicList</i> field initialization with collection of <i>Music</i> interface objects.
	 */	
	private MusicPlayerXML(List<Music> musicList) {
		this.musicList = musicList;
	}
	
	/**
	 * The <i>doMyInit()</i> is the public method that represents the <i>init-method</i> for 
	 * the <i>musicPlayer1</i> - <i>musicPlayer4</i> beans
	 * used in <b>applicationContext.xml</b> file.
	 */
	public void doMyInit() {
		System.out.println("Doing init-method for a MusicPlayer Bean " + this.hashCode() + "\n");
	}	
	
	/**
	 * The <i>doMyDestroy()</i> is the public method that represents the <i>destroy-method</i> 
	 * for the <i>musicPlayer1</i> - <i>musicPlayer4</i> beans
	 * used in <b>applicationContext.xml</b> file.
	 */
	public void doMyDestroy() {
		System.out.println("\nDoing destroy-method for a MusicPlayer Bean " + this.hashCode());
		//Destroy method is not called for beans of scope prototype, 
		//because the context doesn't keep track of the prototype scope objects 
	}
	
	/**
	 * <p>The <i>getMusicPlayer()</i> is public static method that creates a <i>MusicPlayer</i> object, 
	 * using Factory Method Pattern.</p>
	 * <p>It is the factory-method specified in <b>applicationContext.xml</b> file.</p>
	 * 
	 * @param musicList is used for <i>musicList</i> field initialization with collection of <i>Music</i> interface objects.
	 * @return returns the instance of <i>MusicPlayer</i> child object with specified <i>musicList</i> value.
	 */
	public static MusicPlayerXML getMusicPlayer(List<Music> musicList) {
		System.out.println("getMusicPlayer factory-method used");
		return new MusicPlayerXML(musicList);
	}
	
	/**
	 * The <i>setMusic()</i> is the setter public method for the <i>music</i> field of the <i>MusicPlayer</i> object.
	 * 
	 * @param music sets the <i>Music</i> interface value of the <i>music</i> field.
	 */
	public void setMusic(Music music) {
		this.music = music;
	}
	
	/**
	 * The <i>setName()</i> is the setter public method for the <i>name</i> field of the <i>MusicPlayer</i> object.
	 * 
	 * @param name sets the <i>String</i> value of the <i>name</i> field.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * The <i>getName()</i> is the getter public method for the <i>name</i> field of the <i>MusicPlayer</i> object.
	 * 
	 * @return returns the <i>String</i> value of the <i>name</i> field.
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * The <i>setVolume()</i> is the setter public method for the <i>volume</i> field of the <i>MusicPlayer</i> object.
	 * 
	 * @param volume sets the <i>integer</i> value of the <i>volume</i> field.
	 */
	public void setVolume(int volume) {
		this.volume = volume;
	}
	
	/**
	 * The <i>setVolume()</i> is the getter public method for the <i>volume</i> field of the <i>MusicPlayer</i> object.
	 * 
	 * @return returns the <i>integer</i> value of the <i>volume</i> field.
	 */
	public int getVolume() {
		return this.volume;
	}
	
	/**
	 * The public method <i>playMusic()</i> prints on the screen <i>music.getSong()</i> value of the <i>MusicPlayer</i> object.   
	 */
	public void playMusic() {
		System.out.println("Playing: " + music.getSong());
	}
	
	/**
	 * 	The public method <i>playList()</i> prints on the screen <i>getSong()</i> value for all objects in <i>musicList</i> collection of the <i>MusicPlayer</i> object.
	 */
	public void playList() {
		musicList.forEach(song -> System.out.println(song.getSong()));
	}
}

