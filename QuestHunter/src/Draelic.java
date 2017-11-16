/*David Murges
 * 657384
 */

import org.newdawn.slick.SlickException;

/**
 * @author davidmurges
 * class to instantiate draelic.
 * inherits from aggMosnters and Unit
 */
public class Draelic extends aggMonsters{
	// set attributes
	private static int damage = 30;
	private static int maxHP = 140;
	private static int cooldown = 400; 
	
	/**
	 * @param image_path path of image file
	 * @param x  initial xPosition of Draelic
	 * @param y initial yposition of Draelic
	 * @throws SlickException
	 */
	public Draelic(String image_path,float x,float y) throws SlickException{
		super(image_path,maxHP,damage,cooldown,x,y);
		
	}
}
