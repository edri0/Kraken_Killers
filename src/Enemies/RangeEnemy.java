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
import Enemies.Fireball;

public class RangeEnemy extends Enemy {
     private enum DinosaurState {
    WALK,
    SHOOT_WAIT,
    SHOOT
}
    //Class for ranged enemy; extremely similar to sword pirate class, main difference is that it shoots
    //after a certain period of time
    protected Point startLocation;
    protected Point endLocation;

    private float gravity = .5f;
    protected Float movementSpeed = 1.2f;
    protected Direction startFacingDirection;
    protected Direction facingDirection;
    protected AirGroundState airGroundState;

    protected int shootWaitTimer; 
    protected int shootTimer;

    protected DinosaurState pirateState;
    protected DinosaurState previousPirateState;

    public RangeEnemy(Point startLocation, Point endLocation, Direction facingDirection){
        super(startLocation.x, startLocation.y, new SpriteSheet(ImageLoader.load("RangedEnemy.png"), 16, 16), "WALK_RIGHT");
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.startFacingDirection = facingDirection;
        this.initialize();

    }
    @Override
    public void initialize(){
        super.initialize();
        pirateState = DinosaurState.WALK;
        previousPirateState = pirateState;
        facingDirection = startFacingDirection;
        if (facingDirection == Direction.RIGHT){
            currentAnimationName = "WALK_RIGHT";
        } else if (facingDirection == Direction.LEFT){
            currentAnimationName = "WALK_LEFT";
        }
        airGroundState = AirGroundState.GROUND;

        //number of frames until it shoots 
        shootWaitTimer = 65;


    }
    @Override
    public void update(Player player){
        float startBound = startLocation.x;
        float endBound = endLocation.x;

        if (shootWaitTimer == 0 && pirateState != DinosaurState.SHOOT_WAIT){
            pirateState = DinosaurState.SHOOT_WAIT;
        }else{
            shootWaitTimer--;
        }

        if(getX1() + getWidth() >= endBound){
            float difference = endBound - (getX2());
            moveXHandleCollision(-difference);
            facingDirection = Direction.LEFT;
        }else if (getX1() <= startBound){
            float difference = startBound - getX1();
            moveXHandleCollision(difference);
            facingDirection = Direction.RIGHT;
        }
         if (pirateState == DinosaurState.SHOOT_WAIT) {
            if (previousPirateState == DinosaurState.WALK) {
                shootTimer = 65;
               // currentAnimationName = facingDirection == Direction.RIGHT ? "SHOOT_RIGHT" : "SHOOT_LEFT";
            } else if (shootTimer == 0) {
                pirateState = DinosaurState.SHOOT;
            }
            else {
                shootTimer--;
            }
        }

        //shooting fireball
        if(pirateState == DinosaurState.SHOOT){
            int fireballX;
            float movementSpeed;
            if(facingDirection == Direction.RIGHT){
                fireballX = Math.round(getX()) + getWidth();
                movementSpeed = 1.5f;
            }else{
                fireballX = Math.round(getX() - 21);
                movementSpeed = -1.5f;
            }

            int fireballY = Math.round(getY())+4;

            Fireball fireball = new Fireball(new Point(fireballX, fireballY), movementSpeed, 60);

           if(map != null){
            fireball.setMap(map);
             map.addEnemy(fireball);
           }

            pirateState = DinosaurState.WALK;

            shootWaitTimer = 130;

            


            
        }

        // movement when walking
            if (pirateState == DinosaurState.WALK) {
                if (facingDirection == Direction.RIGHT) {
                    moveXHandleCollision(movementSpeed);
                    currentAnimationName = "WALK_RIGHT";
                } else {
                    moveXHandleCollision(-movementSpeed);
                    currentAnimationName = "WALK_LEFT";
                }
            }
        super.update(player);

         previousPirateState = pirateState;


    }
    @Override
    public void onEndCollisionCheckX(boolean hasCollided, Direction direction, MapEntity entityCollidedWith) {
        // if pirate enemy collides with something on the x axis, it turns around and walks the other way
        if (hasCollided) {
            if (direction == Direction.RIGHT) {
                facingDirection = Direction.LEFT;
                currentAnimationName = "WALK_LEFT";
            } else {
                facingDirection = Direction.RIGHT;
                currentAnimationName = "WALK_RIGHT";
            }
        }
    }

    @Override
      public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("WALK_LEFT", new Frame[]{
                    new FrameBuilder(spriteSheet.getSprite(0, 0), 14)
                            .withScale(3)
                            .withBounds(4, 2, 5, 13)
                            .build()
            });

            put("WALK_RIGHT", new Frame[]{
                    new FrameBuilder(spriteSheet.getSprite(0, 0), 14)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(4, 2, 5, 13)
                            .build()
            });
        }};
    }

}
