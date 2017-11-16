/*David Murges
 * 657384
 */


import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import java.util.Random;

/**
 * @author davidmurges
 * abstract class that includes all the attributes the units share except the items
 */
public abstract class Unit {
	
	
	// initialize all attributes
	private float x,y;
	private int HP;
	private int maxHP;
	private int damage;
	private int cooldown;
	private float CooldownCount = 0;
	private Random rand = new Random();
	
	
	/**
	 * @param maxHP maxHp of unit
	 * @param damage damage of Unit
	 * @param cooldown Cooldown of unit
	 * @param x xPosition of unit
	 * @param y yPosition of unit
	 */
	public Unit(int maxHP,int damage,int cooldown,
			float x,float y) throws SlickException{
		this.maxHP = maxHP;
		this.damage = damage;
		this.cooldown = cooldown;
		this.HP = maxHP;
		this.x = x;
		this.y = y;
		
		
		
	}
	
	
	
	/**
	 * @return maxHp of unit
	 */
	public int getMaxHP(){
		return maxHP;
	}
	
	/**
	 * @param maxHP of unit 
	 */
	public void setMaxHP(int maxHP){
		this.maxHP = maxHP;
	}
	
	
	/**
	 * @return current HP of unit
	 */
	public int getHP(){
		return HP;
	}
	
	/**
	 * @param HP current HP of unit
	 */
	public void setHP(int HP){
		this.HP = HP;
	}
	
	/**
	 * @return fraction of total HP
	 */
	public float healthPercentage() {
		return ((float)HP/maxHP);
	}
	
	/**
	 * @return cooldown of unit
	 */
	public int getCooldown(){
		return cooldown;
	}
	
	/**
	 * @param cooldown of unit
	 */
	public void setCoolDown(int cooldown){
		this.cooldown = cooldown;
	}
	
	/**
	 * @return how much of cooldown is left
	 */
	public float getCooldownCount(){
		return CooldownCount;
	}
	
	/**
	 * @param CooldownCount set the cooldown that's left
	 */
	public void setCooldownCount(float CooldownCount){
		this.CooldownCount = CooldownCount;
	}
	
	/**
	 * @param damage damage of unit
	 */
	public void setDamage(int damage){
		this.damage = damage;
	}
	
	/**
	 * @return damage of unit
	 */
	public int getDamage(){
		return damage;
	}
	

	
	/**
	 * @param player hero of the game
	 * attack mechanics of mosnter attacking hero
	 */
	public void MonsterAttack(Player player) {
			// the HP of player
			int HP =player.getHP();
			//deduction random amount between 0 and damage
			int newHP = HP-Rand(rand,this.getDamage());
			//set HP
			player.setHP(newHP);
			
			//set cooldown to unit's cooldown value
			setCooldownCount(getCooldown());
		
	}

	/**
	 * @param monster monster to be attacked
	 * player attacking aggressive monster
	 */
	public void PlayerAttack(aggMonsters monster){
			// HP of monster
			int HP =monster.getHP();
			//deduce random value between damage and 0 when hit
			int newHP = HP-Rand(rand,this.getDamage());
			// set new HP
			monster.setHP(newHP);
			//set cooldown to unit's cooldown value
			setCooldownCount(getCooldown());
		
	}
	
	/**
	 * @param bat passive monster to be attacked
	 */
	public void BatAttack(passMonster bat){
			// HP of bat
			int HP =bat.getHP();
			
			//deduce random value between damage and 0 when hit
			int newHP = HP-Rand(rand,this.getDamage());
			//set new HP
			bat.setHP(newHP);
			//set cooldown to unit's cooldown value
			setCooldownCount(getCooldown());
		
	}
	
	
	/**
	 * @param rand random number generator
	 * @param max max number of range
	 * @return randomly generated number between 0 and damage
	 */
	public int Rand(Random rand,int max){
		int Rand = rand.nextInt(max + 1);
		return Rand;
		
	}
	
}
