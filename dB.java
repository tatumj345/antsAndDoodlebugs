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

        if (allCoordsAround.size() == 0){
            ArrayList<Integer> newA = new ArrayList<>();
            newA.add(this.getX());
            newA.add(this.getY());
            allCoordsAround.add(newA);
        }

        return allCoordsAround;
    }

    public void move(ArrayList<Integer> coords){
        super.move(coords); //first, move the dB
        //check if the dB moved to a cell with an Ant. if so,  change the Ant's status to dead
        boolean hasEaten = false;
        int eatIndex = 0;
        ArrayList<ArrayList<Integer>> allCurrentPosAnt = new ArrayList<>();
        for (int i = 0; i < Main.allOrgs.size(); i++){
            //get all coordpairs for ants
            if (Main.allOrgs.get(i) instanceof Ants) {
                if (this.getX() == Main.allOrgs.get(i).getX() && this.getY() == Main.allOrgs.get(i).getY()){
                    //Main.allOrgs.get(i).die(); //change to dead
                    eatIndex = i;
                    //set turnsSinceEaten to 0 again
                    hasEaten = true;
                }
            }
        }
       if (hasEaten){
           //Main.allOrgs.remove(Main.allOrgs.get(eatIndex));
           Main.allOrgs.get(eatIndex).die();
           turnsSinceEaten = 0;
       }
       else {
           turnsSinceEaten++;
           addSurvivalCount();
       }
       if (turnsSinceEaten >= 3){ //change to dead
           this.die();
           //this.deleteOrg(); delete in main instead
       }
       else{
           //the dB hasn't been deleted and can reproduce
           if (getSurvivalCount() % 8 == 0 && getSurvivalCount() > 0){
               reproduce();
           }
       }
    }
}
