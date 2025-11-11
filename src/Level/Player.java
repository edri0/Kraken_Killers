package Level;

import Engine.Config;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import Engine.Key;
import Engine.KeyLocker;
import Engine.Keyboard;
import Engine.SoundPlayer;
import Game.ArmorType;
import Game.GameState;
import GameObject.GameObject;
import GameObject.Sprite;
import GameObject.SpriteSheet;
import Inventory.Armor;
import Inventory.PlayerInventory;
import UI.HealthBar;
import Utils.AirGroundState;
import Utils.Direction;
import java.awt.Color;
import Game.ArmorTimer;
import NPCs.Chest;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import Level.Map; 

public abstract class Player extends GameObject {
    // values that affect player movement
    // these should be set in a subclass
    protected float walkSpeed = 0;
    protected float gravity = 0;
    protected float jumpHeight = 0;
    protected float jumpDegrade = 0;
    protected float terminalVelocityY = 0;
    protected float momentumYIncrease = 0;

    protected int attackDamage = 25;
    protected int attackCooldown = 0;
    protected int attackCooldownMax = 30;
    protected int attackRange = 40; 
    protected boolean hasDealtDamageThisAttack = false; 

    // values used to handle player movement
    protected float jumpForce = 0;
    protected float momentumY = 0;
    protected float moveAmountX, moveAmountY;
    protected float lastAmountMovedX, lastAmountMovedY;

    // values used to keep track of player's current state
    protected PlayerState playerState;
    protected PlayerState previousPlayerState;
    protected Direction facingDirection;
    protected AirGroundState airGroundState;
    protected AirGroundState previousAirGroundState;
    protected LevelState levelState;
    private Armor equippedArmor;
    private SpriteSheet playSpriteSheet;
    private HealthBar healthBar;

    private int preArmorHealth = -1;
    private int preArmorMaxHealth = -1;

    private ArmorType armorType = ArmorType.NONE;
    private GameState avatarType = GameState.JACK;
    protected boolean isTouchingLeftWall = false;
    protected boolean isTouchingRightWall = false; 

   


    private ArmorTimer armorTimer = new ArmorTimer();

    // classes that listen to player events can be added to this list
    protected ArrayList<PlayerListener> listeners = new ArrayList<>();

    protected ArrayList<Enemy> enemies;

    // define keys
    protected KeyLocker keyLocker = new KeyLocker();
    protected Key JUMP_KEY = Key.UP;
    protected Key MOVE_LEFT_KEY = Key.LEFT;
    protected Key MOVE_RIGHT_KEY = Key.RIGHT;
    protected Key CROUCH_KEY = Key.DOWN;
    protected Key CLIMB_KEY = Key.C; 
    protected Key ATTACK_KEY = Key.A; 

    //health bar
    private int currentHealth = 100;
    private int maxHealth;

    private boolean walkingSoundPlaying = false; 
    


    private SpriteSheet spriteSheet;

    // flags
    protected boolean isInvincible = false; // if true, player cannot be hurt by enemies (good for testing)
    public Object getHealthBar;
        
            
                
        
        public Player(SpriteSheet spriteSheet, float x, float y, String startingAnimationName) {
             super(spriteSheet, x, y, startingAnimationName);
             facingDirection = Direction.RIGHT;
             airGroundState = AirGroundState.AIR;
             previousAirGroundState = airGroundState;
             playerState = PlayerState.STANDING;
             previousPlayerState = playerState;
             levelState = LevelState.RUNNING;
             playerState = PlayerState.ATTACKING;
             this.maxHealth = 100;
             this.currentHealth = maxHealth;
             this.healthBar = new HealthBar(this);

            }
            
            public void updatePlayerSprite(String playerName, ArmorType armorType){
                   
                    String fileName = playerName;
                    switch (armorType){
                        case NONE:
                        fileName += ".png";
                        break;
                        case BRONZE:
                        fileName += "Bronze.png";
                        break;
                        case IRON:
                        fileName += "Iron.png";
                        break;
                        case DIAMOND:
                        fileName += "Diamond.png";
                        break;
                    }
                    System.out.println("switching to armor sprite" + fileName);
                    BufferedImage image = ImageLoader.load(fileName);
                    SpriteSheet newSheet = new SpriteSheet(image,32,32);
                    
                    reloadAnimations(newSheet);
                   

                }
                
    


    public void update() {
        moveAmountX = 0;
        moveAmountY = 0;

        isTouchingLeftWall = map.collidesWithTileOnLeft(this);
        isTouchingRightWall = map.collidesWithTileOnRight(this);
        
        // if player is currently playing through level (has not won or lost)
        if (levelState == LevelState.RUNNING) {
            applyGravity();

            // update player's state and current actions, which includes things like determining how much it should move each frame and if its walking or jumping
            do {
                previousPlayerState = playerState;
                handlePlayerState();
            } while (previousPlayerState != playerState);

            previousAirGroundState = airGroundState;

            // move player with respect to map collisions based on how much player needs to move this frame
            lastAmountMovedX = super.moveXHandleCollision(moveAmountX);
            lastAmountMovedY = super.moveYHandleCollision(moveAmountY);

            handlePlayerAnimation();

            updateLockedKeys();

            if(healthBar != null){
                healthBar.update();
            }


            // update player's animation
            super.update();

            //check if armor timer is done
            if(equippedArmor != null && !armorTimer.isActive()){
                equippedArmor.unequip(this);
                equippedArmor = null;
                System.out.println("armor expired");
            }

            if (playerState == PlayerState.ATTACKING) {
                updateAttack(map.getActiveEnemies()); 
            }
        }

        // if player has beaten level
        else if (levelState == LevelState.LEVEL_COMPLETED) {
            updateLevelCompleted();
        }

        // if player has lost level
        else if (levelState == LevelState.PLAYER_DEAD) {
            updatePlayerDead();
        }

        //attack logic
        else if (playerState == PlayerState.ATTACKING) {
            // updateAttack();
        }

    }
    public void reloadAnimations(SpriteSheet newSheet){
        this.spriteSheet = newSheet;
        this.animations = this.loadAnimations(newSheet);
        this.currentAnimationName = facingDirection == Direction.RIGHT ?  "STAND_RIGHT" : "STAND_LEFT";
        System.out.println("loaded animations:" + animations.keySet());
        this.currentFrameIndex = 0;
        this.currentFrame = animations.get(currentAnimationName)[0];
        
    }

    // add gravity to player, which is a downward force
    protected void applyGravity() {
        if (playerState != PlayerState.CLIMBING) {
            moveAmountY += gravity + momentumY;
        }
    }
    

    // based on player's current state, call appropriate player state handling method
    protected void handlePlayerState() {
        switch (playerState) {
            case STANDING:
                playerStanding();
                break;
            case WALKING:
                playerWalking();
                break;
            case CROUCHING:
                playerCrouching();
                break;
            case JUMPING:
                playerJumping();
                break;
            case CLIMBING:
                playerClimbing();
                break;
            case ATTACKING:
                playerAttacking();
                break;
        }
    }

    // player STANDING state logic
    protected void playerStanding() {
        SoundPlayer.stopMusic(); 
        // if walk left or walk right key is pressed, player enters WALKING state
        if (Keyboard.isKeyDown(MOVE_LEFT_KEY) || Keyboard.isKeyDown(MOVE_RIGHT_KEY)) {
            playerState = PlayerState.WALKING;
        }

        // if jump key is pressed, player enters JUMPING state
        else if (Keyboard.isKeyDown(JUMP_KEY) && !keyLocker.isKeyLocked(JUMP_KEY)) {
            keyLocker.lockKey(JUMP_KEY);
            playerState = PlayerState.JUMPING;
        }

        // if crouch key is pressed, player enters CROUCHING state
        else if (Keyboard.isKeyDown(CROUCH_KEY)) {
            playerState = PlayerState.CROUCHING;
        }

        //if Climb key is pressed, 
        else if (Keyboard.isKeyDown(CLIMB_KEY) && (isTouchingLeftWall || isTouchingRightWall)) {
            playerState = PlayerState.CLIMBING;
            momentumY = 0; 
        }

        else if (Keyboard.isKeyDown(ATTACK_KEY) && !keyLocker.isKeyLocked(ATTACK_KEY)){
            keyLocker.lockKey(ATTACK_KEY); 
            playerState = PlayerState.ATTACKING; 
            return; 
        }
    }

    // player WALKING state logic
    protected void playerWalking() {

        boolean moving = false; 
        // if walk left key is pressed, move player to the left
        if (Keyboard.isKeyDown(MOVE_LEFT_KEY)) {
            moveAmountX -= walkSpeed;
            facingDirection = Direction.LEFT;
            moving = true; 
        }

        // if walk right key is pressed, move player to the right
        else if (Keyboard.isKeyDown(MOVE_RIGHT_KEY)) {
            moveAmountX += walkSpeed;
            facingDirection = Direction.RIGHT;
             moving = true; 
        } 
        
        else if (Keyboard.isKeyUp(MOVE_LEFT_KEY) && Keyboard.isKeyUp(MOVE_RIGHT_KEY)) {
            playerState = PlayerState.STANDING;
        }

        if (moving && !walkingSoundPlaying) {
            SoundPlayer.playMusic("Resources/walking.wav", true); 
            walkingSoundPlaying = true; 
        }
        else if(!moving && walkingSoundPlaying) {
            SoundPlayer.stopMusic(); 
            walkingSoundPlaying = false; 
        }


        //if Climb key is pressed, 
        else if (Keyboard.isKeyDown(CLIMB_KEY) && (isTouchingLeftWall || isTouchingRightWall)) {
            playerState = PlayerState.CLIMBING;
            momentumY = 0; 
        }

        // if jump key is pressed, player enters JUMPING state
        if (Keyboard.isKeyDown(JUMP_KEY) && !keyLocker.isKeyLocked(JUMP_KEY)) {
            keyLocker.lockKey(JUMP_KEY);
            playerState = PlayerState.JUMPING;

            SoundPlayer.playMusic("Resources/jump.wav", false); 
        }

        // if crouch key is pressed,
        else if (Keyboard.isKeyDown(CROUCH_KEY)) {
            playerState = PlayerState.CROUCHING;
        }
         
        else if (Keyboard.isKeyDown(ATTACK_KEY) && !keyLocker.isKeyLocked(ATTACK_KEY)){
            keyLocker.lockKey(ATTACK_KEY); 
            playerState = PlayerState.ATTACKING; 
            SoundPlayer.playMusic("Resources/swords.wav", false); 
            //System.out.println("Music file exists: " + new File("Resources/swords.wav").exists());
            return; 
        }

    }

    // player CROUCHING state logic
    protected void playerCrouching() {
        // if crouch key is released, player enters STANDING state
        if (Keyboard.isKeyUp(CROUCH_KEY)) {
            playerState = PlayerState.STANDING;
        }

        // if jump key is pressed, player enters JUMPING state
        if (Keyboard.isKeyDown(JUMP_KEY) && !keyLocker.isKeyLocked(JUMP_KEY)) {
            keyLocker.lockKey(JUMP_KEY);
            playerState = PlayerState.JUMPING;
        }
    }

    // player JUMPING state logic
    protected void playerJumping() {
        // if last frame player was on ground and this frame player is still on ground, the jump needs to be setup
        if (previousAirGroundState == AirGroundState.GROUND && airGroundState == AirGroundState.GROUND) {

            // sets animation to a JUMP animation based on which way player is facing
            currentAnimationName = facingDirection == Direction.RIGHT ? "JUMP_RIGHT" : "JUMP_LEFT";

            // player is set to be in air and then player is sent into the air
            airGroundState = AirGroundState.AIR;
            jumpForce = jumpHeight;
            if (jumpForce > 0) {
                moveAmountY -= jumpForce;
                jumpForce -= jumpDegrade;
                if (jumpForce < 0) {
                    jumpForce = 0;
                }
            }
            SoundPlayer.playMusic("Resources/jump.wav", false);
        }

        // if player is in air (currently in a jump) and has more jumpForce, continue sending player upwards
        else if (airGroundState == AirGroundState.AIR) {
            if (jumpForce > 0) {
                moveAmountY -= jumpForce;
                jumpForce -= jumpDegrade;
                if (jumpForce < 0) {
                    jumpForce = 0;
                }
            }

            // allows you to move left and right while in the air
            if (Keyboard.isKeyDown(MOVE_LEFT_KEY)) {
                moveAmountX -= walkSpeed;
            } else if (Keyboard.isKeyDown(MOVE_RIGHT_KEY)) {
                moveAmountX += walkSpeed;
            }

            // if player is falling, increases momentum as player falls so it falls faster over time
            if (moveAmountY > 0) {
                increaseMomentum();
            }

            //if Climb key is pressed, 
        else if (Keyboard.isKeyDown(CLIMB_KEY) && (isTouchingLeftWall || isTouchingRightWall)) {
            playerState = PlayerState.CLIMBING;
            momentumY = 0; 
        }
        }

        // if player last frame was in air and this frame is now on ground, player enters STANDING state
        else if (previousAirGroundState == AirGroundState.AIR && airGroundState == AirGroundState.GROUND) {
            playerState = PlayerState.STANDING;
        }
    }


    protected void playerClimbing() {

        momentumY = 0;
        moveAmountY = 0;

        currentAnimationName = facingDirection == Direction.RIGHT ? "CLIMB_RIGHT" : "CLIMB_LEFT" ;

        if(Keyboard.isKeyDown(Key.UP)){
            moveAmountY -= walkSpeed * 0.75f;

        }
        else if (Keyboard.isKeyDown(Key.DOWN)){
            moveAmountY += walkSpeed * 0.75f;

        }

        if ((!isTouchingLeftWall && facingDirection == Direction.LEFT) || (!isTouchingRightWall && facingDirection == Direction.RIGHT)){
            playerState = PlayerState.JUMPING;
            applyGravity();
            return;
        }
        if(Keyboard.isKeyDown(JUMP_KEY) && !keyLocker.isKeyLocked(JUMP_KEY)){
            keyLocker.lockKey(JUMP_KEY);
            playerState = PlayerState.JUMPING;
            momentumY = -jumpHeight * 0.8f;
            moveAmountX = facingDirection == Direction.RIGHT ? -walkSpeed * 1.5f : walkSpeed * 1.5f;
            airGroundState = AirGroundState.AIR;
        }
        if(Keyboard.isKeyUp(CLIMB_KEY)){
            playerState = PlayerState.JUMPING;
            applyGravity();
        }
    }

    public void updateAttack(ArrayList<Enemy> enemies){
        super.update(); 
        System.out.println("Updating");
        if(attackCooldown > 0){
            attackCooldown--;
        }

        for (Enemy enemy : enemies){
        if (intersects(enemy) && attackCooldown == 0) {
                touchedEnemy(enemy);
                System.out.println("Enemy Touched"); 
                attackCooldown = attackCooldownMax;
            }
        }

        if(playerState == PlayerState.ATTACKING && !hasDealtDamageThisAttack){
            for(Enemy enemy : enemies){
                if(intersects(enemy))
                    enemy.takeDamage(attackDamage); 
                    hasDealtDamageThisAttack = true; 
                    System.out.println("Enemy hit! " + attackDamage + "damage"); 
                    break;
                }
            }
            SoundPlayer.playMusic("Resources/swords.wav", false); 
            //System.out.println("Music file exists: " + new File("Resources/swords.wav").exists());
        }
    
    


    
    public void touchedEnemy(Enemy enemy) {
        enemy.hurtEnemy(this);
    }

    protected void playerAttacking() {
        if(attackCooldown == 0){
            hasDealtDamageThisAttack = false; 
            attackCooldown = attackCooldownMax; 
        }
        if(Keyboard.isKeyUp(ATTACK_KEY)){
            keyLocker.unlockKey(ATTACK_KEY); 
            playerState = PlayerState.STANDING; 
        }
    }

   
   

    // while player is in air, this is called, and will increase momentumY by a set amount until player reaches terminal velocity
    protected void increaseMomentum() {
        momentumY += momentumYIncrease;
        if (momentumY > terminalVelocityY) {
            momentumY = terminalVelocityY;
        }
    }

    protected void updateLockedKeys() {
        if (Keyboard.isKeyUp(JUMP_KEY)) {
            keyLocker.unlockKey(JUMP_KEY);
        }
    }

    // anything extra the player should do based on interactions can be handled here
    protected void handlePlayerAnimation() {
        if (playerState == PlayerState.STANDING) {
            // sets animation to a STAND animation based on which way player is facing
            this.currentAnimationName = facingDirection == Direction.RIGHT ? "STAND_RIGHT" : "STAND_LEFT";

            // handles putting goggles on when standing in water
            // checks if the center of the player is currently touching a water tile
            int centerX = Math.round(getBounds().getX1()) + Math.round(getBounds().getWidth() / 2f);
            int centerY = Math.round(getBounds().getY1()) + Math.round(getBounds().getHeight() / 2f);
            MapTile currentMapTile = map.getTileByPosition(centerX, centerY);
            if (currentMapTile != null && currentMapTile.getTileType() == TileType.WATER) {
                this.currentAnimationName = facingDirection == Direction.RIGHT ? "SWIM_STAND_RIGHT" : "SWIM_STAND_LEFT";
            }
        }
        else if (playerState == PlayerState.WALKING) {
            // sets animation to a WALK animation based on which way player is facing
            this.currentAnimationName = facingDirection == Direction.RIGHT ? "WALK_RIGHT" : "WALK_LEFT";
        }
        else if (playerState == PlayerState.CROUCHING) {
            // sets animation to a CROUCH animation based on which way player is facing
            this.currentAnimationName = facingDirection == Direction.RIGHT ? "CROUCH_RIGHT" : "CROUCH_LEFT";
        }
        else if (playerState == PlayerState.JUMPING) {
            // if player is moving upwards, set player's animation to jump. if player moving downwards, set player's animation to fall
            if (lastAmountMovedY <= 0) {
                this.currentAnimationName = facingDirection == Direction.RIGHT ? "JUMP_RIGHT" : "JUMP_LEFT";
            } else {
                this.currentAnimationName = facingDirection == Direction.RIGHT ? "FALL_RIGHT" : "FALL_LEFT";
            }
        }
        else if (playerState == PlayerState.ATTACKING) {
            // sets animation to a WALK animation based on which way player is facing
            this.currentAnimationName = facingDirection == Direction.RIGHT ? "ATTACK_RIGHT" : "ATTACK_LEFT";
        }
    }

    @Override
    public void onEndCollisionCheckX(boolean hasCollided, Direction direction, MapEntity entityCollidedWith) { 
        if (direction == Direction.LEFT) {
            isTouchingLeftWall = hasCollided; 
            
        }
        else if (direction == Direction.RIGHT) {
            isTouchingRightWall = hasCollided; 
        }
    }

    @Override
    public void onEndCollisionCheckY(boolean hasCollided, Direction direction, MapEntity entityCollidedWith) {
        // if player collides with a map tile below it, it is now on the ground
        // if player does not collide with a map tile below, it is in air
        if (direction == Direction.DOWN) {
            if (hasCollided) {
                momentumY = 0;
                airGroundState = AirGroundState.GROUND;
            } else {
                playerState = PlayerState.JUMPING;
                airGroundState = AirGroundState.AIR;
            }
        }

        // if player collides with map tile upwards, it means it was jumping and then hit into a ceiling -- immediately stop upwards jump velocity
        else if (direction == Direction.UP) {
            if (hasCollided) {
                jumpForce = 0;
            }
        }
    }

    // other entities can call this method to hurt the player
    public void hurtPlayer(MapEntity mapEntity) {
        if (mapEntity instanceof Enemy enemy){
                int damage = enemy.getContactDamage();
                takeDamage(damage);


        }
    }

    // other entities can call this to tell the player they beat a level
    public void completeLevel() {
        levelState = LevelState.LEVEL_COMPLETED;
    }

    // if player has beaten level, this will be the update cycle
    public void updateLevelCompleted() {
        // if player is not on ground, player should fall until it touches the ground
        if (airGroundState != AirGroundState.GROUND && map.getCamera().containsDraw(this)) {
            currentAnimationName = "FALL_RIGHT";
            applyGravity();
            increaseMomentum();
            super.update();
            moveYHandleCollision(moveAmountY);
        }
        // move player to the right until it walks off screen
        else if (map.getCamera().containsDraw(this)) {
            currentAnimationName = "WALK_RIGHT";
            super.update();
            moveXHandleCollision(walkSpeed);
        } else {
            // tell all player listeners that the player has finished the level
            for (PlayerListener listener : listeners) {
                listener.onLevelCompleted();
            }
        }
    }

    // if player has died, this will be the update cycle
    public void updatePlayerDead() {
        // change player animation to DEATH
        if (!currentAnimationName.startsWith("DEATH")) {
            if (facingDirection == Direction.RIGHT) {
                currentAnimationName = "DEATH_RIGHT";
            } else {
                currentAnimationName = "DEATH_LEFT";
            }
            super.update();

        }
        // if death animation not on last frame yet, continue to play out death animation
        else if (currentFrameIndex != getCurrentAnimation().length - 1) {
          super.update();
        }
        // if death animation on last frame (it is set up not to loop back to start), player should continually fall until it goes off screen
        else if (currentFrameIndex == getCurrentAnimation().length - 1) {
            if (map.getCamera().containsDraw(this)) {
                moveY(3);
            } else {
                // tell all player listeners that the player has died in the level
                for (PlayerListener listener : listeners) {
                    listener.onDeath();
                }
            }
        }
    }

    public PlayerState getPlayerState() {
        return playerState;
    }

    public void setPlayerState(PlayerState playerState) {
        this.playerState = playerState;
    }

    public AirGroundState getAirGroundState() {
        return airGroundState;
    }

    public Direction getFacingDirection() {
        return facingDirection;
    }

    public void setFacingDirection(Direction facingDirection) {
        this.facingDirection = facingDirection;
    }

    public void setLevelState(LevelState levelState) {
        this.levelState = levelState;
    }

    public void addListener(PlayerListener listener) {
        listeners.add(listener);
    }
    public void setArmor(Armor armor){
        if (this.equippedArmor == armor) return;
        
        preArmorHealth = this.currentHealth;
        preArmorMaxHealth = this.maxHealth;
        int bonus = armor.getHpValue();

    //apply armor bonus
        this.maxHealth += bonus;
        this.currentHealth += bonus;

        if(this.currentHealth > this.maxHealth)   
        this.currentHealth = this.maxHealth;
        


        this.equippedArmor = armor;

        // start armor timer
        //for now only from shop as chest is not fully implemented
        armorTimer.start(30);
        System.out.println("Armor timer started for 30s.");
        
    }
    public void removeArmor() {
        if (equippedArmor == null) return;

        int bonus = equippedArmor.getHpValue();

        this.maxHealth -= bonus;
        this.currentHealth -= bonus;
        
        if(this.currentHealth > this.maxHealth)
         this.currentHealth = this.maxHealth;

        if(this.currentHealth < 0)
        this.currentHealth = 0;

       
        preArmorHealth = -1;
        preArmorMaxHealth = -1;
        
        armorTimer.stop();
        equippedArmor = null;
         
        }
    
    public Armor getEquippedArmor(){
        return equippedArmor;
    }
    public ArmorTimer getArmorTimer(){
        return armorTimer;
    }
    // Uncomment this to have game draw player's bounds to make it easier to visualize

    public void draw(GraphicsHandler graphicsHandler) {
        
        super.draw(graphicsHandler);
        healthBar.draw(graphicsHandler);
        if (armorTimer.isActive()){
            graphicsHandler.drawArmorTimer(Config.GAME_WINDOW_WIDTH,40,armorTimer.getReamianingSeconds());

        }   
    }

    public int getCurrentHealth() {
       return currentHealth;
    }
    public int getMaxHealth(){
        return maxHealth;
    }
    public void setCurrentHealth(int health){
        this.currentHealth = Math.max(0, Math.min(health, maxHealth));

    }
    public void setMaxHealth(int health){
        this.maxHealth = health;
        if(currentHealth > maxHealth) currentHealth = maxHealth;
    }
    public void heal(int amount){
        setCurrentHealth(currentHealth + amount);
    }
    public void takeDamage(int amount){
        if (levelState != LevelState.RUNNING) return;

        if( currentHealth <= amount){
            //he dies
            setCurrentHealth(0);
            levelState = LevelState.PLAYER_DEAD;
            System.out.println("Player died");

        } else {
            //he takes damage
            setCurrentHealth(currentHealth - amount);
            System.out.println("player took: " + amount + "damage");
        }
    }

    public String getAvatarTypeName() {
        if( avatarType == GameState.JACK){
            return "JackSparrow";
        }else{
            return "WillTurner";
        }
    }

    public HealthBar getHealthBar(){
        return healthBar;
    }

    public abstract PlayerInventory getInventory();
  

    

    
    
}
