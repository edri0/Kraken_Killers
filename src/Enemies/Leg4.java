package Enemies;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.Enemy;
import Level.Player;
import Utils.AirGroundState;
import Utils.Direction;
import Utils.Point;

import java.util.HashMap;

public class Leg4 extends Enemy {

    protected Point startLocation;
    protected Point endLocation;

    protected float movementSpeed = 1f;
    protected Direction facingDirection;
    protected Direction startFacingDirection;

    protected AirGroundState airGroundState;

    protected Leg1State legState;
    protected Leg1State previousLegState;

    protected int shootWaitTimer;
    protected int shootTimer;

    // Hurt flash timer
    private int hurtTimer = 0;

    public Leg4(Point startLocation, Point endLocation, Direction facingDirection) {
        super(startLocation.x, startLocation.y,
        new SpriteSheet(ImageLoader.load("Leg4.png"), 16, 16), "WALK_RIGHT");
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.startFacingDirection = facingDirection;
        this.contactDamage = 20;
        initialize();
    }

    @Override
    public void initialize() {
        super.initialize();
        legState = Leg1State.STAND;
        previousLegState = legState;
        facingDirection = startFacingDirection;

        currentAnimationName = (facingDirection == Direction.RIGHT) ? "WALK_RIGHT" : "WALK_LEFT";

        airGroundState = AirGroundState.GROUND;

        shootWaitTimer = 65;
    }

    @Override
    public void update(Player player) {

        // ------------------------------
        // HURT OVERRIDE
        // ------------------------------
        if (hurtTimer > 0) {
            hurtTimer--;
            currentAnimationName = (facingDirection == Direction.RIGHT) ? "HURT_RIGHT" : "HURT_LEFT";
            super.update(player);
            return; // skip movement/shooting while hurt
        }

        float startBound = startLocation.x;
        float endBound = endLocation.x;

        // shooting logic
        if (shootWaitTimer == 0 && legState != Leg1State.ATTACKING) {
            legState = Leg1State.ATTACKING;
        } else {
            shootWaitTimer--;
        }

        // movement (stand/walk)
        if (legState == Leg1State.STAND) {

            if (facingDirection == Direction.RIGHT) {
                currentAnimationName = "WALK_RIGHT";
                moveXHandleCollision(movementSpeed);
            } else {
                currentAnimationName = "WALK_LEFT";
                moveXHandleCollision(-movementSpeed);
            }

            // turn around at edges
            if (getX1() + getWidth() >= endBound) {
                facingDirection = Direction.LEFT;
            } else if (getX1() <= startBound) {
                facingDirection = Direction.RIGHT;
            }
        }

        // start shooting animation
        if (legState == Leg1State.ATTACKING) {
            if (previousLegState == Leg1State.STAND) {
                shootTimer = 65;
                currentAnimationName = (facingDirection == Direction.RIGHT) ? "SHOOT_RIGHT" : "SHOOT_LEFT";
            } else if (shootTimer == 0) {
                legState = Leg1State.SHOOT;
            } else {
                shootTimer--;
            }
        }

        // end shooting
        if (legState == Leg1State.SHOOT) {
            legState = Leg1State.STAND;
            shootWaitTimer = 130;
        }

        super.update(player);
        previousLegState = legState;
    }

    // ------------------------------
    // ON HIT — switch to red frames
    // ------------------------------
    // @Override
    // public void onHit(Player player) {
    //     if (hurtTimer > 0) return; // already hurt
    //     hurtTimer = 20; // flash red
    //     legState = Leg1State.STAND; // stop movement/attack while hurt
    //     shootTimer = 0;
    // }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet sheet) {
        return new HashMap<String, Frame[]>() {{

            put("WALK_LEFT", new Frame[]{
                    new FrameBuilder(sheet.getSprite(0, 0), 14)
                            .withScale(3)
                            .withBounds(4, 2, 16, 16)
                            .build()
            });

            put("WALK_RIGHT", new Frame[]{
                    new FrameBuilder(sheet.getSprite(1, 0), 14)
                            .withScale(3)
                            .withBounds(4, 2, 16, 16)
                            .build()
            });

            put("SHOOT_RIGHT", new Frame[]{
                    new FrameBuilder(sheet.getSprite(2, 0), 14)
                            .withScale(3)
                            .withBounds(4, 2, 16, 16)
                            .build()
            });

            put("SHOOT_LEFT", new Frame[]{
                    new FrameBuilder(sheet.getSprite(1, 1), 14)
                            .withScale(3)
                            .withBounds(4, 2, 16, 16)
                            .build()
            });

            // HURT animations (3 red frames)
            put("HURT_RIGHT", new Frame[]{
                    new FrameBuilder(sheet.getSprite(0, 1), 5).withScale(3).withBounds(4,2,16,16).build(),
                    new FrameBuilder(sheet.getSprite(1, 1), 5).withScale(3).withBounds(4,2,16,16).build(),
                    new FrameBuilder(sheet.getSprite(2, 1), 5).withScale(3).withBounds(4,2,16,16).build()
            });

            put("HURT_LEFT", new Frame[]{
                    new FrameBuilder(sheet.getSprite(0, 1), 5).withScale(3).withBounds(4,2,16,16).withImageEffect(ImageEffect.FLIP_HORIZONTAL).build(),
                    new FrameBuilder(sheet.getSprite(1, 1), 5).withScale(3).withBounds(4,2,16,16).withImageEffect(ImageEffect.FLIP_HORIZONTAL).build(),
                    new FrameBuilder(sheet.getSprite(2, 1), 5).withScale(3).withBounds(4,2,16,16).withImageEffect(ImageEffect.FLIP_HORIZONTAL).build()
            });
        }};
    }

    public enum Leg1State { SHOOT_WAIT, SHOOT, STAND, ATTACKING }
}
