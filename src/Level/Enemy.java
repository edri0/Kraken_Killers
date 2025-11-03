package Level;

import GameObject.Frame;
import GameObject.SpriteSheet;

import java.awt.Color;
import java.util.HashMap;

import Engine.GraphicsHandler;

// This class is a base class for all enemies in the game -- all enemies should extend from it
public class Enemy extends MapEntity {
    protected int contactDamage = 20;
    protected int damageCooldown = 0;
    protected int damageCooldownMax = 60;

    public Enemy(float x, float y, SpriteSheet spriteSheet, String startingAnimation) {
        super(x, y, spriteSheet, startingAnimation);
    }

    public Enemy(float x, float y, HashMap<String, Frame[]> animations, String startingAnimation) {
        super(x, y, animations, startingAnimation);
    }

    public Enemy(float x, float y, Frame[] frames) {
        super(x, y, frames);
    }

    public Enemy(float x, float y, Frame frame) {
        super(x, y, frame);
    }

    public Enemy(float x, float y) {
        super(x, y);
    }

    @Override
    public void initialize() {
        super.initialize();
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

    // A subclass can override this method to specify what it does when it touches the player
    public void touchedPlayer(Player player) {
        player.hurtPlayer(this);
    }

}
