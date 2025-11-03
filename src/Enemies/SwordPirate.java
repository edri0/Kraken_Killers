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
    protected Point startLocation, endLocation;

    private float gravity = .5f;
    protected Float movementSpeed = 1.3f;
    protected Direction startFacingDirection;
    protected Direction facingDirection;
    protected AirGroundState airGroundState;


    public SwordPirate(Point startLocation, Point endLocation, Direction facingDirection) {
        super(startLocation.x, startLocation.y, new SpriteSheet(ImageLoader.load("enemySpriteSheet.png"), 32, 32), "WALK_RIGHT");
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.startFacingDirection = facingDirection;
        this.contactDamage = 20;
        initialize();
    }
    @Override
    public void update(Player player) {
        float moveX = (facingDirection == Direction.RIGHT) ? movementSpeed : -movementSpeed;
        moveXHandleCollision(moveX);
        moveYHandleCollision(0);


         if (getX1() + getWidth() >= endLocation.x){
            facingDirection = Direction.LEFT;
            
         } else if (getX1() <= startLocation.x){
            facingDirection = Direction.RIGHT;
         }
         currentAnimationName = facingDirection == Direction.RIGHT ? "WALK_RIGHT" : "WALK_LEFT";
         super.update(player);
    }

     @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("WALK_LEFT", new Frame[]{
                    new FrameBuilder(spriteSheet.getSprite(0, 0), 14)
                            .withScale(3)
                            .withBounds(4, 2, 10, 13)
                            .build()
            });

            put("WALK_RIGHT", new Frame[]{
                    new FrameBuilder(spriteSheet.getSprite(0, 0), 14)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(4, 2, 10, 13)
                            .build()
            });
        }};
    }
}