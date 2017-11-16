/*David Murges
 * 657384
 */

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


/**
 * @author davidmurges
 * villager class includes all villagers, interactions and rendering
 */
public class Villager {
	
	
	//Prince Aldric Dialogue
	private static final String HoldsElixir ="The elixir! My father is cured! Thankyou!";
	private static final String NoElixir = "Please seek out the Elixir of Life to cure the king.";
	
	//Elvira Dialogue
	private static final String FullHealth = "Return to me if you ever need healing.";
	private static final String NeedHealth = "You're looking much healthier now.";
	
	//Garth Dialogue
	private static final String NoAmulet = "Find the Amulet of Vitality, across the river to the west.";
	private static final String NoSword = "Find the Sword of Strength - cross the river and back, on the east side.";
	private static final String NoTome = "Find the Tome of Agility, in the Land of Shadows.";
	private static final String HoldsTreasures = "You have found all the treasure I know of.";
	
	//Villager attributes
	float x,y,width,height;
	private int interacts = 4000;
	private Image villager;
	private String activeDialogue;
	private boolean HasElixir;
	private boolean damaged;
	private String name;
	
	

	/**
	 * @param image_path path to the image file
	 * @param x xPosition
	 * @param y yPosition
	 * @param name Villager name
	 */
	public Villager(String image_path,float x, float y, String name) 
			throws SlickException {
		if(name.equals("aldric")){
			villager = new Image(image_path);
			this.name = name;
			this.x = x;
			this.y = y;
			this.width = villager.getWidth();
			this.height = villager.getHeight();
		}else if(name.equals("elvira")){
			villager = new Image(image_path);
			this.name = name;
			this.x = x;
			this.y = y;
			this.width = villager.getWidth();
			this.height = villager.getHeight();
			
		}else{
			villager = new Image(image_path);
			this.name = name;
			this.x = x;
			this.y = y;
			this.width = villager.getWidth();
			this.height = villager.getHeight();
			
		}
	}
	
	
	
	/**
	 * @param player the Player of the game
	 * @param key_T the T button on keyboard
	 * @param name the villager name
	 * @param delta Time passed since last frame
	 */
	public void update(Player player,float key_T,String name,int delta){
		
		//if villager is 50 pixels from player and T is pressed
		if (Math.sqrt(Math.pow(xDist(player), 2) + Math.pow(yDist(player), 2)) <=50 && key_T > 0){
			if (name.equals("aldric")){
				AldricInteractions(player);
			}
			if(name.equals("elvira")){
				ElviraInteractions(player);
			}
			if(name.equals("garth")){
				GarthInteractions(player);
			}
			interacts = 0;
		}
		
		// display until interacts == 4000
		interacts += delta;
	}
	
	
	/**
	 * @param player hero of the game
	 */
	public void ElviraInteractions(Player player){
		
		//if full health display dialogue
		if(player.getHP() == player.getMaxHP()){
			setDialogue(FullHealth);
		}
		
		else{
			//if damaged, heal and display dialogue
			player.setHP(player.getMaxHP());
			setDialogue(NeedHealth);
			
		}

		
		

	}
	
	/**
	 * @param player hero of the game
	 */
	public void AldricInteractions(Player player){
		
		//go through item list
		for(int i = 0; i< player.getItemList().size();i++) {
			Item item = player.getItemList().get(i);
			// if one of the items is the elixir remove item and display dialogue
			if(item.getName().equals("elixir")){
				
				player.getItemList().remove(item);
				HasElixir = true;
				setDialogue(HoldsElixir);
			}
		}
		
		//display dialogue
		if(!HasElixir)
			setDialogue(NoElixir);
		
	}
	
	/**
	 * @param player hero of the game
	 */
	public void GarthInteractions(Player player){
		// items in inventory
		boolean HasAmulet = false;
		boolean HasSword = false;
		boolean HasTome = false;
		
		//if item is in inventory set bool to true
		for (Item item : player.getItemList()){
			if (item.getName().equals("amulet")){
				HasAmulet = true;
			}
			if (item.getName().equals("sword")){
				HasSword = true;
			}
			if(item.getName().equals("tome")){
				HasTome = true;
				}
		}
		
		//depending on items collected Garth displays different dialogues
		if (!HasAmulet){
			setDialogue(NoAmulet);
			return;
		}
		if (!HasSword){
			setDialogue(NoSword);
			return;
		}
		if (!HasTome){
			setDialogue(NoTome);
			return;
		}
		
		//if all treasure have been found
		setDialogue(HoldsTreasures);
			
		
	}
	
	
	/**
	 * @param g graphics container of Slick2d
	 */
	void render(Graphics g) throws SlickException {
			villager.drawCentered(x, y);
			//display health bar
			Bars.HealthBar(getName(),g,x,y,1.0f);
			//for 4 seconds display dialogue
			if (interacts<4000) {
				Bars.Dialogue(g,activeDialogue,x, y);	
			}
	}

	/**
	 * @return xPosition
	 */
	public float getX() {
		return x;
	}

	/**
	 * @param x xPosition
	 */
	public void setX(float x) {
		this.x = x;
	}

	/**
	 * @return yPosition
	 */
	public float getY() {
		return y;
	}

	/**
	 * @param yPosition
	 */
	public void setY(float y) {
		this.y = y;
	}
	
    /**
     * @param player hero in game
     * @return xDistance between hero and villager
     */
    public float xDist(Player player){
    	float xDist = player.getX()-getX();
    	return xDist;
    }
    
    /**
     * @param player hero in game
     * @return ydistance between hero and villager
     */
    public float yDist(Player player){
    	float yDist = player.getY()-getY();
    	return yDist;
    }
	
	/**
	 * @return Image
	 */
	public Image getImage() {
		return villager; 
	}
	
	/**
	 * @param activeDialogue dialogue to display
	 */
	public void setDialogue(String activeDialogue){
		this.activeDialogue = activeDialogue;
	}
	
	/**
	 * @return dialogue to display
	 */
	public String getDialogue(){
		return activeDialogue;
	}
	
	/**
	 * @return villager name
	 */
	public String getName(){
		return name;
	}
	
}
