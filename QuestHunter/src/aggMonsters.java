/*David Murges
 * 657384
 */

import org.newdawn.slick.Image;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 * @author davidmurges
 * includes the aggressive monster AI and rendering
 */
public abstract class aggMonsters extends Unit{

	//set attributes
	private float x,y;
	private float width,height;
	private Image monster = null;
	private boolean isDead = false;
	private float xDist,yDist;
	
	//set mosnter speed
	private static final float SPEED = 0.25f;
	private float newX,newY;

	
	/**
	 * @param image_path path of image file
	 * @param maxHP unit's max HP
	 * @param damage unit's max damage
	 * @param cooldown unit's max cooldown
	 * @param x initial xPosition of monster
	 * @param y initial yPosition of monster
	 * @throws SlickException
	 */
	public aggMonsters(String image_path,int maxHP, int damage, int cooldown, float x, float y) throws SlickException {
		super(maxHP, damage, cooldown, x, y);
		monster = new Image(image_path);
		this.x = x;
		this.y = y;
		this.width = monster.getWidth();
		this.height = monster.getHeight();
	}
	
	/**
	 * @param player hero in game
	 * @param delta time passed
	 * @param world object of the game world
	 */
	public void AI(Player player,float delta, World world) {
    	//following algorithm1 given in the spec
		float xDist = player.getX()-getX();
    	float yDist = player.getY()-getY();
    	float distance = (float)Math.sqrt(Math.pow(xDist, 2)+ Math.pow(yDist, 2));
    	float xDistance = xDist * SPEED/ distance;
    	float yDistance = yDist * SPEED/ distance;
		
    	// new x and y position according to algo1
		newX = getX()+xDistance;
		newY = getY()+yDistance;
		
		/**if player is 50 to 150 pixels from monster, the monster will approach
		 player and try to attack*/
		
		if (distance < 150 && distance > 50) {
			
			//check for terrainBlocks
			if(!world.terrainBlocks(newX, this.getY())) {
				x=newX;
			}
			if(!world.terrainBlocks(this.getX(),newY)) {
				y=newY;
			}

		}
		
		//update the cooldownCount
		if(getCooldownCount() - delta > 0){
			setCooldownCount(getCooldownCount() - delta);
		}
		if(getCooldownCount()-delta<=0){
			setCooldownCount(0);
		}
		
		// if 50 pixels from player and alive and cooldown is 0
		if (distance<=50 && isDead()==false && this.getCooldownCount()==0){
			MonsterAttack(player);
			
		}
		

		// if mosnter is dead, set death to true
    	if(getHP()<=0){
    		setDead(true);
    	}
		
	}
	
	
	/**
	 * @param g graphics container
	 * @param name name of monster
	 * @throws SlickException
	 */
	public void render(Graphics g,String name) throws SlickException{
		monster.draw(x,y);
		Bars.HealthBar(name, g,getX()+30,getY()+30, healthPercentage());
	}
	
	/** update death status*/
	public void setDead(boolean isDead){
		this.isDead = isDead;
	}
	/**if mosnter is dead*/
	public boolean isDead(){
		return isDead;
	}
	
    /**
     * @param player hero in game
     * @return yDistance between monster and hero
     */
    public float xDist(Player player){
    	float xDist = player.getX()-this.getX();
    	return xDist;
    }
    /**
     * @param player hero in game
     * @return xDistance between monster and hero
     */
    public float yDist(Player player){
    	float xDist = player.getY()-this.getY();
    	return xDist;
    }
    /** The x coordinate of the player (pixels). */
    public void setX(float x){
    	this.x = x;
    }
    /** The x coordinate of the player (pixels). */
    public float getX(){
    	return x;
    }
    /** The y coordinate of the player (pixels). */
    public void setY(float y){
    	this.y = y;
    }
    
    /** The y coordinate of the player (pixels). */
    public float getY(){
    	return y;
    }
    
    

    
}
