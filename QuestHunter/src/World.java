/* RPG Game Engine
 * Author: David Murges 657384
 */

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.nio.file.Paths;
import java.util.ArrayList;


/** Represents the entire game world.
 * (Designed to be instantiated just once for the whole game).
 */
public class World
{
	
    /** X and Y positions of player,villagers, items and draelic*/
    private static final int PLAYER_START_X = 756, PLAYER_START_Y = 684;
    private static final int AMULET_X = 965, AMULET_Y = 3563;
    private static final int SWORD_X = 4791, SWORD_Y = 1253;
    private static final int TOME_X = 546, TOME_Y = 6707;
    private static final int ELIXIR_X = 1976, ELIXIR_Y = 402;
    private static final int ALDRIC_X = 467, ALDRIC_Y = 679;
    private static final int ELVIRA_X = 738, ELVIRA_Y = 549;
    private static final int GARTH_X = 756, GARTH_Y = 870;
    private static final int DRAELIC_X = 2069,DRAELIC_Y = 510;
    
   
    /**instantiate objects*/
    private Player player = null;
    private TiledMap map = null;
    private Camera camera = null;
    
    /**instantiate objects*/
    private Item amulet = null;
    private Item sword = null;
    private Item tome = null;
    private Item elixir = null;
    
    /**instantiate objects*/
    private Villager aldric = null;
    private Villager elvira = null;
    private Villager garth = null;
    private Draelic draelic = null;
    
    /**instantiate objects and create coordinates array*/
    private String Coordinates[];
    private passMonster bat = null;
    private Skeleton skeleton = null;
    private Bandit bandit = null;
    private Zombie zombie = null;
    
    /**file to be inputted and scanner class*/
    private File file;
    private Scanner scanner;
    
    /**generic arraylist of all units in the game*/
    private ArrayList<passMonster> bats = new ArrayList<passMonster>(30);
    private ArrayList<Skeleton> skeletons = new ArrayList<Skeleton>(24);
    private ArrayList<Bandit> bandits = new ArrayList<Bandit>(34);
    private ArrayList<Zombie> zombies = new ArrayList<Zombie>(38);
    private ArrayList<Item> items = new ArrayList<Item>(4);
    private ArrayList<Villager> villagers = new ArrayList<Villager>(3);
    

    /** Map width, in pixels. */
    private int getMapWidth()
    {
        return map.getWidth() * getTileWidth();
    }

    /** Map height, in pixels. */
    private int getMapHeight()
    {
        return map.getHeight() * getTileHeight();
    }

    /** Tile width, in pixels. */
    private int getTileWidth()
    {
        return map.getTileWidth();
    }

    /** Tile height, in pixels. */
    private int getTileHeight()
    {
        return map.getTileHeight();
    }

    /** Create a new World object. */
    public World()
    throws SlickException, IOException
    {
    	/**temporary values for unit position*/
    	int x,y;
    	
    	/**create villagers*/
        aldric = new Villager(RPG.ASSETS_PATH + "/units/prince.png",ALDRIC_X,ALDRIC_Y,"aldric");
        elvira = new Villager(RPG.ASSETS_PATH + "/units/shaman.png",ELVIRA_X,ELVIRA_Y,"elvira");
        garth = new Villager(RPG.ASSETS_PATH + "/units/peasant.png",GARTH_X,GARTH_Y,"garth");
        
        //add villagers to arrayList
        villagers.add(aldric);
        villagers.add(elvira);
        villagers.add(garth);
    	
    	/**create map, camera, player and draelic*/
        map = new TiledMap(RPG.ASSETS_PATH + "/map.tmx", RPG.ASSETS_PATH);
        player = new Player(RPG.ASSETS_PATH + "/units/player.png", PLAYER_START_X, PLAYER_START_Y);
        camera = new Camera(player, RPG.screenwidth, RPG.screenheight);
        draelic = new Draelic(RPG.ASSETS_PATH + "/units/necromancer.png",DRAELIC_X,DRAELIC_Y);
        
        
        /**create items*/
        amulet = new Item(RPG.ASSETS_PATH + "/items/amulet.png",AMULET_X,AMULET_Y,"amulet");
        sword = new Item(RPG.ASSETS_PATH + "/items/sword.png",SWORD_X,SWORD_Y,"sword");
        tome = new Item(RPG.ASSETS_PATH + "/items/tome.png",TOME_X,TOME_Y,"tome");
        elixir = new Item(RPG.ASSETS_PATH + "/items/elixir.png",ELIXIR_X,ELIXIR_Y,"elixir");
        
        // add items to arrayList
        items.add(amulet);
        items.add(sword);
        items.add(tome);
        items.add(elixir);
        
        
        
        /**read in file*/
        file = new File(RPG.ASSETS_PATH +"/data/bats.txt");
        scanner = new Scanner(file);
        
        /**scan bat positions, create object and add to arrayList*/
        while(scanner.hasNext()){
        	Coordinates = scanner.next().split(",");
        	x = Integer.parseInt(Coordinates[0]);
        	y = Integer.parseInt(Coordinates[1]);
        	
        	bat = new passMonster(RPG.ASSETS_PATH +"/units/dreadbat.png",x,y);
        	bats.add(bat);
        }
        
        /**read in file*/
        file = new File(RPG.ASSETS_PATH +"/data/skeletons.txt");
        scanner = new Scanner(file);
        
        /**scan skeleton positions, create object and add to arrayList*/
        while(scanner.hasNext()){
        	Coordinates = scanner.next().split(",");
        	x = Integer.parseInt(Coordinates[0]);
        	y = Integer.parseInt(Coordinates[1]);
        	
        	skeleton = new Skeleton(RPG.ASSETS_PATH +"/units/skeleton.png",x,y);
        	skeletons.add(skeleton);
        	
        }
        
        /**read in file*/
        file = new File(RPG.ASSETS_PATH +"/data/bandits.txt");
        scanner = new Scanner(file);
        
        /**scan bandit positions, create object and add to arrayList*/
        while(scanner.hasNext()){
        	Coordinates = scanner.next().split(",");
        	x = Integer.parseInt(Coordinates[0]);
        	y = Integer.parseInt(Coordinates[1]);
        	
        	bandit = new Bandit(RPG.ASSETS_PATH +"/units/bandit.png",x,y);
        	bandits.add(bandit);
        }
        
        /**read in file*/
        file = new File(RPG.ASSETS_PATH +"/data/zombies.txt");
        scanner = new Scanner(file);
        
        /**scan zombie positions, create object and add to arrayList*/
        while(scanner.hasNext()){
        	Coordinates = scanner.next().split(",");
        	x = Integer.parseInt(Coordinates[0]);
        	y = Integer.parseInt(Coordinates[1]);
        	
        	zombie = new Zombie(RPG.ASSETS_PATH +"/units/zombie.png",x,y);
        	zombies.add(zombie);
        }
        
        

        
        
        

    }

    /** Update the game state for a frame.
     * @param dir_x The player's movement in the x axis (-1, 0 or 1).
     * @param dir_y The player's movement in the y axis (-1, 0 or 1).
     * @param delta Time passed since last frame (milliseconds).
     */
    public void update(int dir_x, int dir_y,float key_T,float key_A, int delta)
    throws SlickException
    {
        player.move(this, dir_x, dir_y, delta);
        camera.update();
        
        
        //interaction with villagers
        for(Villager villager : villagers){
        	villager.update(player, key_T, villager.getName(), delta);
        }
        
        
        for(Item item : items){
        	//if item hasn't been picked up
        	if (item.isCollected(item.getName())==false 
        			&& Math.sqrt(Math.pow(item.xDist(player), 2)+Math.pow(item.yDist(player), 2))<=50){
        		//add to player itemList
        		player.addItemList(item);
        		//set item pickUp status to true
        		item.setCollected(item.getName(), true);
        		//change player attributes
        		item.changeAttributes(player,item.getName());
        	}
        	
        	//attack draelic when in range, A is pressed and cooldown = 0
        	draelic.AI(player, delta, this);
    		if((key_A>0) && Math.sqrt(Math.pow(draelic.xDist(player), 2)+
    				Math.pow(draelic.yDist(player), 2))<=50 && player.getCooldownCount()==0) {
    			player.PlayerAttack(draelic);
    		}
    	
        	
        	for(passMonster bat : bats){
        		//bat wanders the map
        		bat.AI(player,this);
        		if((key_A>0) && Math.sqrt(Math.pow(bat.xDist(player), 2)+ 
        				Math.pow(bat.yDist(player), 2))<=50 && player.getCooldownCount()==0) {
        			//attack bat when in range, A is pressed and cooldown = 0
        			player.BatAttack(bat);
        		}
        	}
        	
        	for(Bandit bandit : bandits){
        		// bandit AI
        		bandit.AI(player, delta, this);
        		if((key_A > 0) && Math.sqrt(Math.pow(bandit.xDist(player), 2)+
        				Math.pow(bandit.yDist(player), 2))<=50 && player.getCooldownCount()==0){
        			//attack bandit when in range, A is pressed and cooldown = 0
        			player.PlayerAttack(bandit);
        		}
        	}
        	
        	for(Skeleton skeleton : skeletons){
        		//skeleton AI
        		skeleton.AI(player, delta, this);
        		if((key_A > 0) && Math.sqrt(Math.pow(skeleton.xDist(player), 2)+
        				Math.pow(skeleton.yDist(player), 2))<=50 && player.getCooldownCount()==0){
        			//attack skeleton when in range, A is pressed and cooldown = 0
        			player.PlayerAttack(skeleton);
        		}
        	}
        	
        	for(Zombie zombie : zombies){
        		// zombie AI
        		zombie.AI(player, delta, this);
        		if((key_A > 0) && Math.sqrt(Math.pow(zombie.xDist(player), 2)+
        				Math.pow(zombie.yDist(player), 2))<=50 && player.getCooldownCount()==0){
        			//attack zombie when in range, A is pressed and cooldown = 0
        			player.PlayerAttack(zombie);
        		}
        	}

        	
        	
        	
        	for (Villager villager : villagers){
        		//villager interactions
        		villager.update(player, key_T, villager.getName(), delta);
        	}
        	
        	
        }
        
        
        
        
    }

    /** Render the entire screen, so it reflects the current game state.
     * @param g The Slick graphics object, used for drawing.
     */
    public void render(Graphics g)
    throws SlickException
    {
    	
        // Render the relevant section of the map
        int x = -(camera.getMinX() % getTileWidth());
        int y = -(camera.getMinY() % getTileHeight());
        int sx = camera.getMinX() / getTileWidth();
        int sy = camera.getMinY()/ getTileHeight();
        int w = (camera.getMaxX() / getTileWidth()) - (camera.getMinX() / getTileWidth()) + 1;
        int h = (camera.getMaxY() / getTileHeight()) - (camera.getMinY() / getTileHeight()) + 1;
        map.render(x, y, sx, sy, w, h);
        
        

        renderPanel(player.getPanel(),g);
        
        
        // Translate the Graphics object
        g.translate(-camera.getMinX(), -camera.getMinY());
        
        // Render the player
        player.render();
        
        //render draelic if alive
        if(!draelic.isDead()){
        	draelic.render(g, "Draelic");
        }
        
        //render skeletons if alive
        for(Skeleton skeleton : skeletons){
        	if(!skeleton.isDead()){
        		skeleton.render(g, "Skeleton");
        	}
        }
        
        //render zombie if alive
        for (Zombie zombie : zombies){
        	if(!zombie.isDead()){
        		zombie.render(g, "Zombie");
        	}
        }
        
        //render bandit if alive
        for(Bandit bandit : bandits){
        	if(!bandit.isDead()){
        		bandit.render(g, "Bandit");
        	}
        }

        //render bat if alive
        for(passMonster bat : bats){
        	if(!bat.isDead()){
        		bat.render(g);
        	}
        }
        
        //render item if not picked up
        for(Item item : items){
        	if(item.isCollected(item.getName())==false){
        		item.render();
        	}
        }
        
        //render villagers
        for(Villager villager : villagers){
        	villager.render(g);
        }
        
        
        
        
        
    }

    /** Determines whether a particular map coordinate blocks movement.
     * @param x Map x coordinate (in pixels).
     * @param y Map y coordinate (in pixels).
     * @return true if the coordinate blocks movement.
     */
    public boolean terrainBlocks(float x, float y)
    {
        // Check we are within the bounds of the map
        if (x < 0 || y < 0 || x > getMapWidth() || y > getMapHeight()) {
            return true;
        }
        
        // Check the tile properties
        int tile_x = (int) x / getTileWidth();
        int tile_y = (int) y / getTileHeight();
        int tileid = map.getTileId(tile_x, tile_y, 0);
        String block = map.getTileProperty(tileid, "block", "0");
        return !block.equals("0");
    }
    
    /**
     * @param panel panel image
     * @param g graphics Container
     * @throws SlickException
     */
    public void renderPanel(Image panel,Graphics g) throws SlickException
    {
    	
    	
        // Panel colours
        Color LABEL = new Color(0.9f, 0.9f, 0.4f);          // Gold
        Color VALUE = new Color(1.0f, 1.0f, 1.0f);          // White
        Color BAR_BG = new Color(0.0f, 0.0f, 0.0f, 0.8f);   // Black, transp
        Color BAR = new Color(0.8f, 0.0f, 0.0f, 0.8f);      // Red, transp

        // Variables for layout
        String text;                // Text to display
        int text_x, text_y;         // Coordinates to draw text
        int bar_x, bar_y;           // Coordinates to draw rectangles
        int bar_width, bar_height;  // Size of rectangle to draw
        int hp_bar_width;           // Size of red (HP) rectangle
        int inv_x, inv_y;           // Coordinates to draw inventory item

        float health_percent;       // Player's health, as a percentage

        // Panel background image
        panel.draw(0, RPG.screenheight - RPG.panelheight);

        // Display the player's health
        text_x = 15;
        text_y = RPG.screenheight - RPG.panelheight + 25;
        g.setColor(LABEL);
        g.drawString("Health:", text_x, text_y);
        text = ""+player.getHP()+"/"+player.getMaxHP();                                 // get HP and maxHP

        bar_x = 90;
        bar_y = RPG.screenheight - RPG.panelheight + 20;
        bar_width = 90;
        bar_height = 30;
        health_percent = player.healthPercentage();                         // get player's health percentage
        hp_bar_width = (int) (bar_width * health_percent);
        text_x = bar_x + (bar_width - g.getFont().getWidth(text)) / 2;
        g.setColor(BAR_BG);
        g.fillRect(bar_x, bar_y, bar_width, bar_height);
        g.setColor(BAR);
        g.fillRect(bar_x, bar_y, hp_bar_width, bar_height);
        g.setColor(VALUE);
        g.drawString(text, text_x, text_y);

        // Display the player's damage and cooldown
        text_x = 200;
        g.setColor(LABEL);
        g.drawString("Damage:", text_x, text_y);
        text_x += 80;
        text = ""+ player.getDamage();                                    // get player's damage
        g.setColor(VALUE);
        g.drawString(text, text_x, text_y);
        text_x += 40;
        g.setColor(LABEL);
        g.drawString("Rate:", text_x, text_y);
        text_x += 55;
        text = ""+ player.getCooldown();                                    // get player's cooldown
        g.setColor(VALUE);
        g.drawString(text, text_x, text_y);

        // Display the player's inventory
        g.setColor(LABEL);
        g.drawString("Items:", 420, text_y);
        bar_x = 490;
        bar_y = RPG.screenheight - RPG.panelheight + 10;
        bar_width = 288;
        bar_height = bar_height + 20;
        g.setColor(BAR_BG);
        g.fillRect(bar_x, bar_y, bar_width, bar_height);

        inv_x = 490;
        inv_y = RPG.screenheight - RPG.panelheight
            + ((RPG.panelheight - 72) / 2);
        for (Item item : player.getItemList())                //get each item in itemList
        {
            // Render the item to (inv_x, inv_y)
            item.getImage().draw(inv_x,inv_y);
        	inv_x += 72;
        }
    }

    
}
