package Game;
//class to make timers for armor once they are equpped 
//time depends on weather you got armor frim shop or chest

public class ArmorTimer {
    private long startTime;
    private int duration;
    private boolean active;

    public void start(int seconds){
        this.startTime = System.currentTimeMillis();
        this.duration = seconds;
        this.active = true;
    }
    public void stop(){
        this.active = false;

    }
    public boolean isActive(){
        return active && getReamianingSeconds() > 0;
    }
    public int getReamianingSeconds(){
        if (!active) return 0;
        long elapsed = (System.currentTimeMillis() - startTime) / 1000;
        return Math.max(0,duration - (int) elapsed);
    }
}
