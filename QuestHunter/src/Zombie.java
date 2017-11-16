/*David Murges
 * 657384
 */

import org.newdawn.slick.SlickException;

/**
 * @author davidmurges
 * class to instantiate zombies.
 * inherits from aggMonsters and Unit
 */
public class Zombie extends aggMonsters{
	//set attributes
	private static int damage = 10;
	private static int maxHP = 60;
	private static int cooldown = 800; 
	
	/**
	 * @param image_path path of image file
	 * @param x initial xPosition of Zombie
	 * @param y initial yPosition of Zombie
	 */
	public Zombie(String image_path,float x,float y) throws SlickException{
		super(image_path,maxHP,damage,cooldown,x,y);
		
	}
	
}
