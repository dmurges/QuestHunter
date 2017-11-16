/*David Murges
 * 657384
 */

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public abstract class Bars {
	
	
	static Color LABEL = new Color(0.9f, 0.9f, 0.4f);          // Gold
    static Color VALUE = new Color(1.0f, 1.0f, 1.0f);          // White
    static Color BAR_BG = new Color(0.0f, 0.0f, 0.0f, 0.8f);   // Black, transp
    static Color BAR = new Color(0.8f, 0.0f, 0.0f, 0.8f);      // Red, transp
   

    
    /**
     * @param g graphics Container
     * @param dialogue dialogue to display
     * @param x xPosition of dialogue
     * @param y yPosition of dialogue
     */
    static public void Dialogue(Graphics g,String dialogue,float x, float y) {
    	//set variables like the ones given in renderPanel
    	float bar_x,bar_y;
    	float bar_width,bar_height;
    	float text_x,text_y;
    	
    	//set bar width and height
    	bar_width=g.getFont().getWidth(dialogue)+6;
    	bar_height = 21;
        
    	//set bar position
    	bar_x = x-200;
        bar_y = y-66;
        
        //set text position
        text_x = bar_x;
        text_y = bar_y;
        
        //display bars
        g.setColor(BAR_BG);
        g.fillRect(bar_x, bar_y, bar_width, bar_height);
        
        g.setColor(VALUE);
        g.drawString(dialogue, text_x, text_y);
        
    }

    
    /**
     * @param name unit name
     * @param g graphics Container
     * @param x xPosition of bar
     * @param y yPosition of bar
     * @param healthPercentage how full health bar is
     */
    static public void HealthBar(String name, Graphics g,float x, float y, float healthPercentage) throws SlickException {
    	
    	//set variables like the ones given in renderPanel
    	float bar_x,bar_y;
    	float bar_width,bar_height;
    	float hp_bar_width;
    	float text_x,text_y;
        
    	// bar dimensions
    	bar_width=g.getFont().getWidth(name)+6;
    	bar_height = 21;
    	
    	//bar position
    	bar_x = x-30;
        bar_y = y-44;
        
        //hpBarwidth relative to healthStatus
        hp_bar_width = bar_width * healthPercentage;
        
        //set text position
        text_x = bar_x;
        text_y=bar_y;
        
        //display bar
        g.setColor(BAR_BG);
        g.fillRect(bar_x, bar_y, bar_width, bar_height);
        
        g.setColor(BAR);
        g.fillRect(bar_x, bar_y, hp_bar_width, bar_height);
        
        g.setColor(VALUE);
        g.drawString(name, text_x, text_y);
        
        
    }
    
}
    

