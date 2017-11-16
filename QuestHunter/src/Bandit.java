/*David Murges
 * 657384
 */

import org.newdawn.slick.SlickException;

/**
 * @author davidmurges
 *  create bandit that inherits from aggMonsters and Unit
 */
public class Bandit extends aggMonsters{
	// set attributes
	private static int damage = 8;
	private static int maxHP = 40;
	private static int cooldown = 200; 
	
	/**
	 * @param image_path path of image file
	 * @param x initial xPosition of bandit
	 * @param y initial yPosition of bandit
	 */
	public Bandit(String image_path,float x,float y) throws SlickException{
		super(image_path,maxHP,damage,cooldown,x,y);
		
	}
	
}
