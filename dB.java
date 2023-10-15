import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
public class dB extends Organism{
    int turnsSinceEaten = 0;
    public dB(int x, int y, DrawingPanel panel, Color color){
        super(x,y,panel,color);
    }
    public dB(){

    }

    public ArrayList<ArrayList<Integer>> getValidCoords(){ //this is where we can move to
        //first, get all coords around dB
        ArrayList<ArrayList<Integer>> allCoordsAround = this.getCoordsAround();

        //now, get the coords that have a dB in them. we'll remove these from allCoordsAround
        ArrayList<ArrayList<Integer>> coordsWithdB = Main.coordsArounddB(this, Main.allOrgs);

        allCoordsAround.removeAll(coordsWithdB);

        return allCoordsAround;
    }
}
