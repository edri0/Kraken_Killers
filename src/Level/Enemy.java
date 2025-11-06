package Level;

import GameObject.Frame;
import GameObject.GameObject;
import GameObject.SpriteSheet;

import java.awt.Color;
import java.util.HashMap;

import Engine.GraphicsHandler;

// This class is a base class for all enemies in the game -- all enemies should extend from it
public class Enemy extends MapEntity {
    protected int contactDamage; // given value in each of the enemy classes
    protected int damageCooldown = 0;
    protected int damageCooldownMax = 60;
    protected LevelState levelState;

    private int currentHealth = 100;
    private int maxHealth;

    protected boolean isTinvincible = false; 

    public Enemy(float x, float y, SpriteSheet spriteSheet, String startingAnimation) {
        super(x, y, spriteSheet, startingAnimation);
        this.maxHealth = 100;
        this.currentHealth = maxHealth;
    }

    public Enemy(float x, float y, HashMap<String, Frame[]> animations, String startingAnimation) {
        super(x, y, animations, startingAnimation);
        this.maxHealth = 100;
        this.currentHealth = maxHealth;
    }

    public Enemy(float x, float y, Frame[] frames) {
        super(x, y, frames);
        this.maxHealth = 100;
        this.currentHealth = maxHealth;
    }

    public Enemy(float x, float y, Frame frame) {
        super(x, y, frame);
        this.maxHealth = 100;
        this.currentHealth = maxHealth;
    }

    public Enemy(float x, float y) {
        super(x, y);
        this.maxHealth = 100;
        this.currentHealth = maxHealth;
    }

    @Override
    public void initialize() {
        super.initialize();
        damageCooldown = 0;
    }

    public void update(Player player) {
        super.update();
        
        if(damageCooldown > 0){
            damageCooldown--;
        }

        if (intersects(player) && damageCooldown == 0) {
                touchedPlayer(player);
                damageCooldown = damageCooldownMax;
            }
    }
    public void hurtEnemy(GameObject gameObject) {
        if (gameObject instanceof Player){
                takeDamage(20);
        }
    }

    // A subclass can override this method to specify what it does when it touches the player
    public void touchedPlayer(Player player) {
        System.out.println("Touched PLayer");
        player.hurtPlayer(this);
    }


     public int getCurrentHealth() {
       return currentHealth;
    }
    public int getMaxHealth(){
        return maxHealth;
    }
    public void setCurrentHealth(int health){
        this.currentHealth = Math.max(0, Math.min(health, maxHealth));

    }

    public void takeDamage(int amount){
        if( currentHealth <= amount){
            //he dies
            setCurrentHealth(0);
            levelState = LevelState.ENEMY_DEAD;
            System.out.println("Enemy died");

        } else {
            //he takes damage
            setCurrentHealth(currentHealth - amount);
            System.out.println("player took: " + amount + "damage");
        }
    }

}
