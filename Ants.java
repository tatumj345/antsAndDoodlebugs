//////////////////////////////////////////////////////////////////////////////////////////////////////
//Name: Tatum Johnson
//Date: 10-16-23
//Description: Ant.java- Prey subclass for predator-prey simulation.
//////////////////////////////////////////////////////////////////////////////////////////////////////

import java.awt.*;
import java.util.*;
public class Ants extends Organism{
    public Ants(int x, int y, DrawingPanel panel, Color color){
        super(x,y,panel,color);
    }
    public Ants(){

    }

    /* //defined in organism now. Ants uses it, but it can also be used in reproduce
    public ArrayList<ArrayList<Integer>> getValidCoords(){ // this is where we can move
        //first, get all coords around ant
        ArrayList<ArrayList<Integer>> allCoordsAround = this.getCoordsAround();

        //now, get the coords that have a dB in them. we'll remove these from allCoordsAround
        ArrayList<ArrayList<Integer>> coordsWithdB = Main.coordsArounddB(this, Main.allOrgs);
        //now, get the coords that have an ant in them. we'll remove these from allCoordsAround
        ArrayList<ArrayList<Integer>> coordsWithAnt = Main.coordsAroundAnt(this, Main.allOrgs);


        allCoordsAround.removeAll(coordsWithdB);
        allCoordsAround.removeAll(coordsWithAnt);

        return allCoordsAround;
    }

     */

    public void move(ArrayList<Integer> coords){
        super.move(coords); //move to valid spot
        addSurvivalCount();
        if (getSurvivalCount() % 3 == 0 && getSurvivalCount() > 0){
            reproduce();
        }
    }
}
