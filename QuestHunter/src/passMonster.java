/*David Murges
 * 657384
 */

import org.newdawn.slick.Image;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 * @author davidmurges
 * class contains AI and rendering of passive monsters
 */
public class passMonster extends Unit{
    
	//instantiate bat object
	private Image bat = null;
	
	//set speed of bat
    private static final float SPEED = 0.2f;
    
    //set attributes
    private float x, y;
    private float width, height;
    private boolean dead = false;
    private static int maxHP = 40;
    private static int damage = 0;
    private static int cooldown = 0;
    private float newX,newY;

    
    
    
    /**
     * @param image_path path of image file
     * @param x initial xPosition of bat
     * @param y initial yPosition of bat
     * @throws SlickException
     */
    public passMonster(String image_path, float x, float y)
            throws SlickException
        {
    		super(maxHP,damage,cooldown,x,y);
            bat = new Image(image_path);
            this.x = x;
            this.y = y;
            this.width = bat.getWidth();
            this.height = bat.getHeight();
            
        }
    
    /**
     * @param player hero in game
     * @param world object of the game world
     */
    public void AI(Player player,World world){
    	
    	// new x and y positions
    	newX = getX()-algorithm1(player,"x");
    	newY = getY()-algorithm1(player,"y");
    	
    	//check for terrainBlocks
    	if(!world.terrainBlocks(newX, getY())){
    		x = newX;
    	}
    	if(!world.terrainBlocks(getX(), newY)){
    		y = newY;
    	}
    	
    	//if bat is dead
    	if(getHP()<=0){
    		setDead(true);
    	}
    	
    }
    
    

    
    /**
     * @param g graphics container
     * @throws SlickException
     */
    public void render(Graphics g) throws SlickException{
    	bat.draw(x,y);
    	//display healbar above bat
    	Bars.HealthBar("DreadBat", g,getX()+30,getY()+30, healthPercentage());
    }
    
    /**
     * @param player hero in game
     * @return xDsitance between hero and monster
     */
    public float xDist(Player player){
    	float xDist = player.getX()-this.getX();
    	return xDist;
    }
    
    
    /**
     * @param player hero in game
     * @return yDistance between player and monster
     */
    public float yDist(Player player){
    	float yDist = player.getY()-this.getY();
    	return yDist;
    }
    /** The x coordinate of the player (pixels). */
    public float getX()
    {
        return x;
    }
    
    /** The x coordinate of the player (pixels). */
    public void setX(float x){
    	this.x = x;
    }
    /** The y coordinate of the player (pixels). */
    public float getY()
    {
        return y;
    }
    /** The y coordinate of the player (pixels). */
    public void setY(float y){
    	this.y = y;
    }
    
    /**if monster is dead*/
    public boolean isDead(){
    	return dead;
    }
    
    /**update death status*/
    public void setDead(boolean dead){
    	this.dead = dead;
    }
    
    
    /**
     * @param player hero in game
     * @param axis x or y distance to return
     * @return x or y distance
     * 
     * follow algorithm 1
     */
    public float algorithm1(Player player, String axis){
    	float xDist = player.getX()-getX();
    	float yDist = player.getY()-getY();
    	float distance = (float)Math.sqrt(Math.pow(xDist, 2)+ Math.pow(yDist, 2));
    	float xDistance = xDist * SPEED/ distance;
    	float yDistance = yDist * SPEED/ distance;
    	if(axis.equals("x")){
    		return xDistance;
    	}else{
    		return yDistance;
    	}
    }


    
}
