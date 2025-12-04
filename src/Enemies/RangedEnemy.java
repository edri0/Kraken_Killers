package Enemies;

import Level.Enemy;
import Level.Player;
import Utils.AirGroundState;
import Utils.Direction;
import Utils.Point;

import Engine.ImageLoader;
import GameObject.SpriteSheet;

public class RangedEnemy extends Enemy {

    protected Point startLocation;
    protected Point endLocation;

    private float gravity = .5f;
    protected float movementSpeed = 1.2f;
    protected Direction startFacingDirection;
    protected Direction facingDirection;
    protected AirGroundState airGroundState;

    protected int shootWaitTimer;
    protected int shootTimer;

    protected RangedEnemyState enemyState;
    protected RangedEnemyState previousEnemyState;

    public RangedEnemy(Point startLocation, Point endLocation, Direction facingDirection) {
        super(startLocation.x, startLocation.y,
                new SpriteSheet(ImageLoader.load("RangedEnemy.png"), 16, 16),
                "WALK_RIGHT");

        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.startFacingDirection = facingDirection;
        initialize();
    }

    @Override
    public void initialize() {
        super.initialize();

        enemyState = RangedEnemyState.WALK;
        previousEnemyState = enemyState;

        facingDirection = startFacingDirection;

        currentAnimationName = (facingDirection == Direction.RIGHT)
                ? "WALK_RIGHT"
                : "WALK_LEFT";

        airGroundState = AirGroundState.GROUND;

        shootWaitTimer = 65;
    }

    @Override
    public void update(Player player) {

        float startBound = startLocation.x;
        float endBound = endLocation.x;

        // Movement logic
        if (enemyState == RangedEnemyState.WALK) {
            if (facingDirection == Direction.RIGHT) {
                currentAnimationName = "WALK_RIGHT";
                moveXHandleCollision(movementSpeed);
            } else {
                currentAnimationName = "WALK_LEFT";
                moveXHandleCollision(-movementSpeed);
            }

            // Reverse direction at edges
            if (getX1() + getWidth() >= endBound) {
                facingDirection = Direction.LEFT;
            } else if (getX1() <= startBound) {
                facingDirection = Direction.RIGHT;
            }
        }

        super.update(player);
        previousEnemyState = enemyState;
    }

    // -----------------------------------
    // Enemy states
    // -----------------------------------
    public enum RangedEnemyState {
        WALK,
        SHOOTING
    }
}
