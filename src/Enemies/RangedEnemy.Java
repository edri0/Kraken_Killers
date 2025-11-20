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

public class RangedEnemy extends Enemy{
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

    public RangedEnemy(Point startLocation, Point endLocation, Direction facingDirection){
        super(startLocation.x, startLocation.y, new SpriteSheet(ImageLoader.load(RangedEnemy.png), 16, 16) "WALK_RIGHT");
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.startFacingDirection = facingDirection;
        this.initialize();

    }
    @Override
    public void initialize(){
        super.initialize();
        pirateState = DinosaurState.WALK;
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
    }


}
