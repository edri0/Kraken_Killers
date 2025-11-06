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

// Fitz enemy: walks back and forth between two points
public class Fitz extends Enemy {

    // Movement bounds (walks between start and end points)
    protected Point startLocation;
    protected Point endLocation;

    protected float movementSpeed = 1.2f;
    private Direction startFacingDirection;
    protected Direction facingDirection;
    protected AirGroundState airGroundState;

    public Fitz(Point startLocation, Point endLocation, Direction facingDirection) {
        super(startLocation.x, startLocation.y,
              new SpriteSheet(ImageLoader.load("fitz.png"), 32, 32),
              "WALK_RIGHT");

        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.startFacingDirection = facingDirection;
        this.contactDamage = 20;
        this.initialize();
    }

    @Override
    public void initialize() {
        super.initialize();
        facingDirection = startFacingDirection;
        airGroundState = AirGroundState.GROUND;
    }

    @Override
    public void update(Player player) {
        float moveX = (facingDirection == Direction.RIGHT) ? movementSpeed : -movementSpeed;

        moveXHandleCollision(moveX);
        moveYHandleCollision(0);

        if (getX1() <= startLocation.x) {
            facingDirection = Direction.RIGHT;
            currentAnimationName = "WALK_RIGHT";
        } else if (getX1() + getWidth() >= endLocation.x){
            facingDirection = Direction.LEFT;
            currentAnimationName = "WALK_LEFT";
        }

        super.update(player);
    }

    @Override
    public void onEndCollisionCheckX(boolean hasCollided, Direction direction, MapEntity entityCollidedWith) {
        if (hasCollided) {
            facingDirection = (facingDirection == Direction.LEFT) ? Direction.RIGHT : Direction.LEFT;
            currentAnimationName = (facingDirection == Direction.LEFT) ? "WALK_LEFT" : "WALK_RIGHT";
        }
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("WALK_LEFT", new Frame[]{
                new FrameBuilder(spriteSheet.getSprite(0, 0), 14)
                    .withScale(3)
                    .withBounds(2,2,14,26)
                    .build(),
            });

            put("WALK_RIGHT", new Frame[]{
                new FrameBuilder(spriteSheet.getSprite(0, 0), 14)
                    .withScale(3)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .withBounds(2,2,14,26)
                    .build(),
            });
        }};
    }
}
