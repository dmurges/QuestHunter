/*David Murges
 * 657384
 */

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * @author davidmurges
 * Item class includes all the items to be collected as well as 
 * methods to indicate whether object has been picked up and item attributes.
 */
public class Item {
	
	//initialize values
	private Image item;
	private float width,height,x,y;
	private boolean SwordCollected = false;
	private boolean AmuletCollected = false;
	private boolean TomeCollected = false;
	private boolean ElixirCollected = false;
	private String name;
	
	
	/**
	 * @param image_path path to image file
	 * @param x xPosition of item
	 * @param y yPosition of item
	 * @param name item name
	 */
	public Item (String image_path,float x,float y,String name) throws SlickException{
		if(name.equals("amulet")){
			item = new Image(image_path);
			this.name = name;
	        this.x = x;
	        this.y = y;
	        this.width = item.getWidth();
	        this.height = item.getHeight();
		}else if(name.equals("sword")){
			item = new Image(image_path);
			this.name = name;
	        this.x = x;
	        this.y = y;
	        this.width = item.getWidth();
	        this.height = item.getHeight();
		}else if (name.equals("tome")){
			item = new Image(image_path);
			this.name = name;
	        this.x = x;
	        this.y = y;
	        this.width = item.getWidth();
	        this.height = item.getHeight();
		}else {
			item = new Image(image_path);
			this.name = name;
	        this.x = x;
	        this.y = y;
	        this.width = item.getWidth();
	        this.height = item.getHeight();
		}
	}
	
	

	
	/**
	 * @param player hero in game
	 * @param name item name
	 */
	public void changeAttributes(Player player,String name){
		
		// increase maxHP and heal player
		if(name.equals("amulet")){
			player.setMaxHP(player.getMaxHP()+80);
			player.setHP(player.getHP()+80);
		}
		//decrease cooldown
		if(name.equals("tome")){
			player.setCoolDown(player.getCooldown()-300);
		}
		
		//increase damage
		if(name.equals("sword")){
			player.setDamage(player.getDamage()+30);
		}
	}
	
    /**
     * render the item on map
     */
    public void render()
    {
    	item.drawCentered((int)x,(int)y);
    }
    
    
	
    /**
     * @param name item name
     * @return if item has been collected
     */
    public boolean isCollected(String name){
    	if(name.equals("sword")){
    		return SwordCollected;
    	}
    	if(name.equals("amulet")){
    		return AmuletCollected;
    	}
    	if(name.equals("tome")){
    		return TomeCollected;
    	}
    	if(name.equals("elixir")){
    		return ElixirCollected;
    	}
    	return false;
    	
    }
	
    
    /**
     * @param name item name
     * @param collected whether item has been picked up
     */
    public void setCollected(String name,boolean collected){
    	if(name.equals("sword")){
    		this.SwordCollected = collected;
    	}
    	if(name.equals("amulet")){
    		this.AmuletCollected = collected;
    	}
    	if(name.equals("tome")){
    		this.TomeCollected = collected;
    	}
    	if(name.equals("elixir")){
    		this.ElixirCollected = collected;
    	}
    
    }
    
    
    /**
     * @param player hero in game
     * @return xDistance between hero and item
     */
    public float xDist(Player player){
    	float xDist = player.getX()-getX();
    	return xDist;
    }
    
    /**
     * @param player hero in game
     * @return yDistance between hero and item
     */
    public float yDist(Player player){
    	float yDist = player.getY()-getY();
    	return yDist;
    }
    
    /**
     * @return xPosition of item
     */
    public float getX(){
    	return x;
    }
    
    /**
     * @return yPosition of item
     */
    public float getY(){
    	return y;
    }
    
    /**
     * @return item name
     */
    public String getName(){
    	return name;
    }
    
    /**
     * @return item Image
     */
    public Image getImage(){
    	return item;
    }
    
}
