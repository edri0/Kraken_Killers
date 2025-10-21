package UI;

import java.awt.Color;
import java.awt.Font;

import Engine.GraphicsHandler;
import Engine.ScreenManager;
import Level.Player;

public class HealthBar {
    private final Player player;

    private int x, y, width, height;

    public HealthBar(Player player){
        this.player = player;
        this.width = 200;
        this.height = 20;
        this.x = 20;
        this.y = ScreenManager.getScreenHeight() - 80;
    }
    public void update(){
    }
    public void draw(GraphicsHandler graphicsHandler){
        int currentHealth = player.getCurrentHealth();
        int maxHealth = player.getMaxHealth();
        float healthPercent = (float) currentHealth / maxHealth;
        
        Color barColor;
        if(healthPercent >0.5f){
            barColor = Color.GREEN;
        } else if (healthPercent > 0.2f) {
            barColor = Color.YELLOW;
        } else {
            barColor = Color.RED;
        }
        
        graphicsHandler.drawFilledRectangle(x,y,width,height,new Color(70,70,70));

        int fillWidth = (int)(width * healthPercent);
        graphicsHandler.drawRectangle(x,y,width,height,Color.WHITE);

        graphicsHandler.drawString("HP: " + currentHealth + "/" + maxHealth, x+ 10, y-8, new Font("Arial", Font.BOLD, 16), Color.WHITE);

    }



}
