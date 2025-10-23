package Enemies;

import Level.Enemy;
import Level.MapEntity;
import Level.Player;
import Utils.AirGroundState;
import Utils.Direction;
import Utils.Point;

import java.util.HashMap;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;

public class SwordPirate extends Enemy {
    //This class is for the Sword pirate enemy, It's meant to be kinda like the dinosaur Enemy (meant to walk between 2 points)
    //but if it collides with the player it will swing it's sword and deal damage (maybe 20% hp?) 
    protected Point startLocation;
    protected Point endLocation;

    private float gravity = .5f;
    protected Float movementSpeed = 1.3f;
    protected Direction startFacingDirection;
    protected Direction facingDirection;
    protected AirGroundState airGroundState;
    private int damageAmount= 20;
    private int dmgCooldown = 0;

    public SwordPirate(Point startLocation, Point endLocation, Direction facingDirection) {
        super(startLocation.x, startLocation.y, new SpriteSheet(ImageLoader.load("enemySpriteSheet.png"), 32, 32), "WALK_RIGHT");
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.startFacingDirection = facingDirection;
        this.initialize();
    }
    @Override
    public void update(Player player) {
        float startBound = startLocation.x;
        float endBound = endLocation.x;
        float moveAmountX = 0;
        float moveAmountY = 0;

         if (facingDirection == Direction.RIGHT) {
                moveAmountX += movementSpeed;
            } else {
                moveAmountX -= movementSpeed;
            }

        // add gravity (if in air, this will cause bug to fall)
        moveAmountY += gravity;
        if (airGroundState == AirGroundState.AIR) {
            moveAmountY += gravity;
            } else {
            moveAmountY = 0; // prevent sliding downward
}
/* 
        // if on ground, walk forward based on facing direction
        if (airGroundState == AirGroundState.GROUND) {
           
        }
*/
        // move bug
        moveYHandleCollision(moveAmountY);
        moveXHandleCollision(moveAmountX);

        if(dmgCooldown >0){
            dmgCooldown--;
        }

        if (getX1() + getWidth() >= endBound) {
                float difference = endBound - (getX2());
                moveXHandleCollision(-difference);
                facingDirection = Direction.LEFT;
            } else if (getX1() <= startBound) {
                float difference = startBound - getX1();
                moveXHandleCollision(difference);
                facingDirection = Direction.RIGHT;
            }

                if (this.intersects(player) && dmgCooldown == 0) {
            player.takeDamage(damageAmount); // deal damage
            dmgCooldown = 60;

            // play attack animation
            if (currentAnimationName.equals("WALK_LEFT")) {
                currentAnimationName = "HIT_ENEMY_LEFT";
            } else {
                currentAnimationName = "HIT_ENEMY_RIGHT";
            }
        }
        
        

        super.update(player);
    }

    @Override
    public void onEndCollisionCheckX(boolean hasCollided, Direction direction, MapEntity entityCollidedWith) {

    }

    
    @Override
    public void onEndCollisionCheckY(boolean hasCollided, Direction direction, MapEntity entityCollidedWith) {

    }


     @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("WALK_LEFT", new Frame[]{
                    new FrameBuilder(spriteSheet.getSprite(0, 0), 14)
                            .withScale(3)
                            .withBounds(4, 2, 10, 13)
                            .build(),
            });

            put("WALK_RIGHT", new Frame[]{
                    new FrameBuilder(spriteSheet.getSprite(0, 0), 14)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(4, 2, 10, 13)
                            .build(),
            });
            put("HIT_ENEMY_LEFT", new Frame[]{
                new FrameBuilder(spriteSheet.getSprite(1, 0),14)
                .withScale(3)
                .withBounds(4,2,10,13)
                .build(),
            });
             put("HIT_ENEMY_RIGHT", new Frame[]{
                new FrameBuilder(spriteSheet.getSprite(1, 0),14)
                .withScale(3)
                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                .withBounds(4,2,10,13)
                .build(),
            });

            
        }};
    }
    


    
    

    
}
