package Enemies;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.Enemy;
import Level.MapEntity;
import Level.Player;
import Utils.AirGroundState;
import Utils.Direction;
import Utils.Point;

import java.util.HashMap;

// Mr. Krabs enemy - walks like a Goomba and turns around at walls or cliffs
public class Krabs extends Enemy {
    private float gravity = 0.5f;
    private float movementSpeed = 0.5f;
    private Direction startFacingDirection;
    private Direction facingDirection;
    private AirGroundState airGroundState;

    public Krabs(Point location, Direction facingDirection) {
        super(location.x, location.y,
              new SpriteSheet(ImageLoader.load("Krabs.png"), 30, 31), //  Corrected frame size
              "WALK_RIGHT");
        this.startFacingDirection = facingDirection;
        this.initialize();
    }

    @Override
    public void initialize() {
        super.initialize();
        facingDirection = startFacingDirection;
        currentAnimationName = (facingDirection == Direction.RIGHT) ? "WALK_RIGHT" : "WALK_LEFT";
        airGroundState = AirGroundState.GROUND;
    }

    @Override
    public void update(Player player) {
        float moveAmountX = 0;
        float moveAmountY = gravity;

        // Move horizontally if on ground
        if (airGroundState == AirGroundState.GROUND) {
            moveAmountX = (facingDirection == Direction.RIGHT) ? movementSpeed : -movementSpeed;
        }

        // Apply movement and collision handling
        moveYHandleCollision(moveAmountY);
        moveXHandleCollision(moveAmountX);

        super.update(player);
    }

    @Override
    public void onEndCollisionCheckX(boolean hasCollided, Direction direction, MapEntity entityCollidedWith) {
        // Turn around when colliding horizontally
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
    public void onEndCollisionCheckY(boolean hasCollided, Direction direction, MapEntity entityCollidedWith) {
        if (direction == Direction.DOWN) {
            airGroundState = hasCollided ? AirGroundState.GROUND : AirGroundState.AIR;
        }
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            // Walking left animation (just one frame)
            put("WALK_LEFT", new Frame[]{
                new FrameBuilder(spriteSheet.getSprite(0, 0), 8)
                    .withScale(2)
                    .withBounds(0, 0, 30, 31)
                    .build()
            });
    
            // Walking right animation (mirrored horizontally)
            put("WALK_RIGHT", new Frame[]{
                new FrameBuilder(spriteSheet.getSprite(0, 0), 8)
                    .withScale(2)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .withBounds(0, 0, 30, 31)
                    .build()
            });
        }};
    }
    
    }
