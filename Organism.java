//////////////////////////////////////////////////////////////////////////////////////////////////////
//Name: Tatum Johnson
//Date: 10-13-23
//Description: Organism.java- Base class for predator-prey simulation.
//////////////////////////////////////////////////////////////////////////////////////////////////////

import java.awt.*;
import java.sql.Array;
import java.util.*;
public class Organism {
    private int survivalCount = 0; //number of timesteps the organism survived
    private boolean dead = false;
    DrawingPanel panel;
    Graphics g;
    Color color;
    static int buffer = 30;
    private int x = buffer; //position
    private int y = buffer; //position
    static int cellDimension = 50;// cell width and height
    static int rowmax = 10; //number of rows of cells
    static int colmax = 12; //number of cols of cells

    //ref point for drawing organisms is their upper left corner.
    //Bounds for organisms in x: [buffer, buffer + colmax*cellDimension]
    //Bounds for organisms in y: [buffer, buffer + rowmax*cellDimension]
    public Organism(){ //default constructor

    }

    public Organism(int x, int y, DrawingPanel panel, Color color){
        this.setX(x);
        this.setY(y);
        this.panel = panel;
        this.g = panel.getGraphics();
        this.color = color;
    }

    public void move(ArrayList<Integer> coords){ //maybe add something here to make sure coords is 2 long
        //move to the coord pair specified by the input
        Graphics gBlack = this.panel.getGraphics();
        gBlack.setColor(Color.white);
        gBlack.fillRect(this.getX(), this.getY(), this.cellDimension, this.cellDimension);
        gBlack.setColor(Color.black);
        gBlack.drawRect(this.getX(), this.getY(), this.cellDimension, this.cellDimension);
        if (coords.size() == 0){
            coords.add(this.getX());
            coords.add(this.getY());
        }


        this.setX(coords.get(0));
        this.setY(coords.get(1));

        this.draw();

    }


public void draw(){
        if (this.dead == false) {
            g.setColor(color);
            g.fillOval(x, y, cellDimension, cellDimension);
    }
}

    public int getX() {
        return x;
    }
    public int getY(){
        return y;
    }
    public void setX(int x){
        if (x >= buffer && x<= buffer + (colmax-1)*cellDimension && ((x-buffer) % cellDimension == 0) && (x >= buffer)) { //in grid bounds. also must be multiple of cell dimension.
            this.x = x;
        }
    }
    public void setY(int y){
        if (y >= buffer && y<= buffer + (rowmax-1)*cellDimension && ((y-buffer) % cellDimension == 0) && (y >= buffer)) { //in grid bounds. also must be multiple of cell dimension.
            this.y = y;
        }
    }



    public void addSurvivalCount(){
        this.survivalCount++;
    }

    public int getSurvivalCount(){
        return survivalCount;
    }

    public ArrayList<ArrayList<Integer>> getCoordsAround(){
        ArrayList<Integer> adjX = new ArrayList<Integer>();
        adjX.add(this.x);
        adjX.add(this.x + cellDimension);
        adjX.add(this.x - cellDimension);
        ArrayList<Integer> adjY = new ArrayList<Integer>();
        adjY.add(this.y + cellDimension);
        adjY.add(this.y);
        adjY.add(this.y-cellDimension);

        //Main.printArrayList(coords);

        ArrayList<ArrayList<Integer>> coords = new ArrayList<ArrayList<Integer>>();

        for (int i = 0; i < adjX.size(); i++){
            for (int j = 0; j < adjY.size(); j++) {
                if (adjX.get(i).intValue() >= buffer && adjY.get(j).intValue() >= buffer) { //check if in lower bounds
                    if (adjX.get(i).intValue() <= (buffer + (colmax-1) * cellDimension) && adjY.get(j).intValue() <= (buffer + (rowmax-1) * cellDimension)) { //check if in upper bounds
                        //this is an okay x and y position to move to, so add it to the combos list
                       //can't move to the cell that it's already in
                        if (!((adjX.get(i).intValue() == this.getX()) && (adjY.get(j).intValue() == this.getY()))){
                            ArrayList<Integer> newA = new ArrayList<>();
                            newA.add(adjX.get(i).intValue());
                            newA.add(adjY.get(j).intValue());
                            coords.add(newA);
                        }
                    }
                }
            }
        }


        return coords;
    }


    public void die(){
        this.dead = true;
    }

    public boolean isDead(){
        return dead;
    }

    public void deleteOrg() {
        //draw a single grid in the place of organism. then delete from array.
        Graphics gBlack = this.panel.getGraphics();
        gBlack.setColor(Color.white);
        gBlack.fillRect(this.getX(), this.getY(), (this.cellDimension), (this.cellDimension));
        gBlack.setColor(Color.BLACK);
        gBlack.drawRect(this.getX(), this.getY(), (this.cellDimension), (this.cellDimension));
        //remove myOrg from allOrgs- not yet! do the actual removal in main
        //Main.allOrgs.remove(this);
    }


    public void reproduce(){
        ArrayList<ArrayList<Integer>> validCoords = this.getValidCoords(); //get all empty coords around organism

        //don't reproduce in same cell
        ArrayList<Integer> current = new ArrayList<>();
        current.add(this.getX());
        current.add(this.getY());

        //System.out.println(this.getX()+" "+this.getY());

        validCoords.remove(current);

        if (validCoords.size() != 0) {
            //create a new object in a random coord
            Random rand = new Random();

            ArrayList<Integer> randomCoord = validCoords.get(rand.nextInt(validCoords.size()));
            if (this instanceof dB) { //should fix this if loop to be more than two options for subclass in case want to add more
                Main.allOrgs.add(new dB(randomCoord.get(0), randomCoord.get(1), panel, Color.RED));
                Main.allOrgs.get(Main.allOrgs.size()-1).draw();
            } else if (this instanceof Ants) {
                Main.allOrgs.add(new Ants(randomCoord.get(0), randomCoord.get(1), panel, Color.BLACK));
                Main.allOrgs.get(Main.allOrgs.size()-1).draw();
            }
        }
    }

    public ArrayList<ArrayList<Integer>> getValidCoords(){ // this is where we can move
        //first, get all coords around ant
        ArrayList<ArrayList<Integer>> allCoordsAround = this.getCoordsAround();

        //now, get the coords that have a dB in them. we'll remove these from allCoordsAround
        ArrayList<ArrayList<Integer>> coordsWithdB = Main.coordsArounddB(this, Main.allOrgs);
        //now, get the coords that have an ant in them. we'll remove these from allCoordsAround
        ArrayList<ArrayList<Integer>> coordsWithAnt = Main.coordsAroundAnt(this, Main.allOrgs);


        allCoordsAround.removeAll(coordsWithdB);
        allCoordsAround.removeAll(coordsWithAnt);

        if (allCoordsAround.size() == 0){
            ArrayList<Integer> newA = new ArrayList<>();
            newA.add(this.getX());
            newA.add(this.getY());
            allCoordsAround.add(newA);
        }

        return allCoordsAround;
    }
}
