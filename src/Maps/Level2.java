package Maps;

import Level.Map;
import Tilesets.CommonTileset;
import Utils.Point;

public class Level2 extends Map{

    public Level2(){
        super("level2.txt", new CommonTileset());
        this.playerStartPosition = new Point(2, 11);
    }
    
}
