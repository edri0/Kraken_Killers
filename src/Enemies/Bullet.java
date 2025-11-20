package Enemies;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import Game.GameState;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Inventory.Weapon;
import Level.Enemy;
import Level.MapEntity;
import Level.MapEntityStatus;
import Level.NPC;
import Level.Player;
import Level.PlayerState;
import Utils.Direction;
import Utils.Point;

import java.util.ArrayList;
import java.util.HashMap;

// This class is for the fireball enemy that the DinosaurEnemy class shoots out
// it will travel in a straight line (x axis) for a set time before disappearing
// it will disappear early if it collides with a solid map tile
public class Bullet extends NPC {
    private float movementSpeed;
    private int existenceFrames;
    private int attackDamage = 3; 

    public Bullet(Point location, float movementSpeed, int existenceFrames) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("Bullet.png"), 7, 7),  "DEFAULT");
        this.movementSpeed = movementSpeed;

        // how long the fireball will exist for before disappearing
        this.existenceFrames = existenceFrames;

        initialize();
    }

   
    public void update(Player player) {
        // if timer is up, set map entity status to REMOVED
        // the camera class will see this next frame and remove it permanently from the map
        if (existenceFrames == 0) {
            updateShoot(map.getActiveEnemies());
            this.mapEntityStatus = MapEntityStatus.REMOVED;
        } else {
            // move fireball forward
            moveXHandleCollision(movementSpeed);
            // super.update(player);
        }
        existenceFrames--;
    }

    @Override
    public void onEndCollisionCheckX(boolean hasCollided, Direction direction, MapEntity entityCollidedWith) {
        // if fireball collides with anything solid on the x axis, it is removed
        if (hasCollided) {
            this.mapEntityStatus = MapEntityStatus.REMOVED;
        }
    }

     public void updateShoot(ArrayList<Enemy> enemies){


        // if (hasDealtDamageThisAttack){
        //     return; 
        // }

        // if(getInventory().getEquippedWeapon() instanceof Weapon weapon) {
        //     attackDamage = weapon.getDamage(); 
        // }

            for(Enemy enemy : enemies){
                if(intersects(enemy)){
                    enemy.takeDamage(attackDamage); 
                   // hasDealtDamageThisAttack = true; 
                    System.out.println("Enemy hit! " + attackDamage + "damage"); 
                    break;
                }
            }
    }


    // public void touchedEnemy(ArrayList<Enemy> enemies) {
    //     // if fireball touches player, it disappears
    //     this.mapEntityStatus = MapEntityStatus.REMOVED;
    // }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("DEFAULT", new Frame[]{
                    new FrameBuilder(spriteSheet.getSprite(0, 0))
                            .withScale(3)
                            .withBounds(1, 1, 5, 5)
                            .build()
            });
        }};
    }
}
