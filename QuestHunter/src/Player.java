/* RPG Game Engine
 * Author: David Murges 657384
 */

import org.newdawn.slick.Image;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import java.util.ArrayList;

/** The character which the user plays as.
 */
public class Player extends Unit
{
    private Image img = null;
    private Image img_flipped = null;
    private Image panel = null;
    
    
    // In pixels
    private float x, y;
    private float width, height;
    private boolean face_left = false;
    
    private ArrayList<Item> ItemList = new ArrayList<Item>(4);
    
    
    private static int maxHP = 100;
    private static int damage = 26;
    private static int cooldown = 600;

    // Pixels per millisecond
    private static final float SPEED = 0.25f;

    /** The x coordinate of the player (pixels). */
    public float getX()
    {
        return x;
    }

    /** The y coordinate of the player (pixels). */
    public float getY()
    {
        return y;
    }
    /** The x coordinate of the player (pixels). */
    public void setX(float x){
    	this.x = x;
    }
    

    /** The y coordinate of the player (pixels). */
    public void setY(float y){
    	this.y = y;
    }

    /** Creates a new Player.
     * @param image_path Path of player's image file.
     * @param x The Player's starting x location in pixels.
     * @param y The Player's starting y location in pixels.
     */
    public Player(String image_path, float x, float y)
        throws SlickException
    {
    	super(maxHP, damage,cooldown,x,y);
        img = new Image(image_path);
        img_flipped = img.getFlippedCopy(true, false);
        this.x = x;
        this.y = y;
        this.width = img.getWidth();
        this.height = img.getHeight();
        panel = new Image("assets/panel.png");
    }

    /** Move the player in a given direction.
     * Prevents the player from moving outside the map space, and also updates
     * the direction the player is facing.
     * @param world The world the player is on (to check blocking).
     * @param dir_x The player's movement in the x axis (-1, 0 or 1).
     * @param dir_y The player's movement in the y axis (-1, 0 or 1).
     * @param delta Time passed since last frame (milliseconds).
     */
    public void move(World world, float dir_x, float dir_y, float delta)
    {
        // Update player facing based on X direction
        if (dir_x > 0)
            this.face_left = false;
        else if (dir_x < 0)
            this.face_left = true;

        // Move the player by dir_x, dir_y, as a multiple of delta * speed
        float new_x = this.x + dir_x * delta * SPEED;
        float new_y = this.y + dir_y * delta * SPEED;

        // Move in x first
        float x_sign = Math.signum(dir_x);
        if(!world.terrainBlocks(new_x + x_sign * width / 2, this.y + height / 2) 
                && !world.terrainBlocks(new_x + x_sign * width / 2, this.y - height / 2)) {
            this.x = new_x;
        }
        
        // Then move in y
        float y_sign = Math.signum(dir_y);
        if(!world.terrainBlocks(this.x + width / 2, new_y + y_sign * height / 2) 
                && !world.terrainBlocks(this.x - width / 2, new_y + y_sign * height / 2)){
            this.y = new_y;
            
        }
        
        // set cooldown to current cooldown minus time passed if hasn't reached 0
		if(getCooldownCount() - delta > 0){
			setCooldownCount(getCooldownCount()-delta);
		}
		
		// if passed 0, set to 0
		if(getCooldownCount() - delta <= 0){
			setCooldownCount(0);
		}
		
		// if player is dead, teleport to set location and heal
		if(getHP() < 0){
			setX(738);
			setY(549);
			
			setHP(getMaxHP());
		}
   
    }

    /** Draw the player to the screen at the correct place.
     * @param g The current Graphics context.
     * @param cam_x Camera x position in pixels.
     * @param cam_y Camera y position in pixels.
     */
    public void render()
    {
    	
        Image which_img;
        which_img = this.face_left ? this.img_flipped : this.img;
        which_img.drawCentered((int) x, (int) y);
        
    }
    
    /**
     * @return panel Image
     */
    public Image getPanel(){
    	return panel;
    }
    
    /**
     * @param item add item to item list
     */
    public void addItemList(Item item){
    	ItemList.add(item);
    }
    
    /**
     * @return itemList
     */
    public ArrayList<Item> getItemList(){
    	return ItemList;
    }
    
    

    
}
