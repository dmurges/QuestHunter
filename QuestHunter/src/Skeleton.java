/*David Murges
 * 657384
 */

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * @author davidmurges
 * class to instantiate skeletons
 * inherits from aggMonsters and Unit
 */
public class Skeleton extends aggMonsters{
	//set attributes
	private static int damage = 16;
	private static int maxHP = 100;
	private static int cooldown = 500; 
	
	/**
	 * @param image_path path of image file
	 * @param x initial xPosition of skeletons
	 * @param y initial yPosition of skeletons
	 */
	public Skeleton(String image_path,float x,float y) throws SlickException{
		super(image_path,maxHP,damage,cooldown,x,y);
		
	}
	


	
	
}
