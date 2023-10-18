import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import java.awt.Color;
import java.awt.Dimension;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import javax.swing.*;

//getters and setter for static variables in Organism

//add graphing functionality

//inheritance with subclass default constructors
//import
//setters and getters for turnsSinceEaten
//cite drawing panel
public class Main {
    static ArrayList<Organism> allOrgs = new ArrayList<>();

    static JFrame frame = new JFrame("Doodlebugs and Ants");

    public static void main(String[] args) {
        //demo.pack();
        //RefineryUtilities.centerFrameOnScreen(demo);
        //demo.setVisible(true);

        frame.setLayout( new FlowLayout() );

        DefaultCategoryDataset dataset = new DefaultCategoryDataset(); //where add values to be plotted
        //create chart
        BarChartDemo demo = new BarChartDemo(dataset);

        Scanner scnr = new Scanner(System.in); //ERROR HANDLING, improper input and negative ints
        /*
        System.out.println("Type number of rows (positive integer, suggested = 10):");

        int rowsIn = scnr.nextInt();
        while (rowsIn <= 0) {
            System.out.println("Input positive integer for number of rows:");
            rowsIn = scnr.nextInt();
        }
        System.out.println("Type number of columns (positive integer, suggested = 10):");
        int colsIn = scnr.nextInt();
        while (colsIn <= 0) {
            System.out.println("Input positive integer for number of cols:");
            colsIn = scnr.nextInt();
        }

         */

        int rowsIn = 20;
        int colsIn = 20;
        int cellsIn = 15;

        int maxOrgs = rowsIn * colsIn;

        Organism.cellDimension = cellsIn; //50
        Organism.rowmax = rowsIn; //10
        Organism.colmax = colsIn; //12


        //int dBCount = 3;
        //int antsCount = 2;

//
        int cellDimension = Organism.cellDimension;// cell width and height
        int rowmax = Organism.rowmax; //number of rows of cells
        int colmax = Organism.colmax; //number of cols of cells
        int buffer = Organism.buffer;

        DrawingPanel panel = new DrawingPanel(colmax * cellDimension + 2 * buffer, rowmax * cellDimension + 2 * buffer);
        //draw the grid on the window
        Graphics gBlack = panel.getGraphics();

        ArrayList<ArrayList<Integer>> allInitialPos = new ArrayList<>();
        for (int row = 0; row < rowmax; row++) {
            for (int col = 0; col < colmax; col++) {
                gBlack.drawRect(buffer + col * cellDimension, buffer + row * cellDimension, cellDimension, cellDimension);
                ArrayList<Integer> newA = new ArrayList<>();
                newA.add(buffer + col * cellDimension);

                newA.add(buffer + row * cellDimension);
                //System.out.println(newA.get(0)+" "+newA.get(1));
                allInitialPos.add(newA);
            }
        }
        /*
        System.out.println("Type dimension of cells in pixels (suggested = 50 pixels for 10 by 10 grid):");
        int cellsIn = scnr.nextInt();
        while (cellsIn <= 0) {
            System.out.println("Input positive integer for cell dimension in pixels:");
            cellsIn = scnr.nextInt();
        }

         */

        System.out.println("Type the number of initial doodlebugs- must be less than or equal to: " + maxOrgs);
        int dBCount = scnr.nextInt();
        while (dBCount < 0 && dBCount > maxOrgs) {
            System.out.println("Input positive integer or zero for number of doodlebugs. Number of doodlebugs must be less than or equal to: " + maxOrgs);
            dBCount = scnr.nextInt();
        }
        System.out.println("Type the number of ants- must be less than or equal to: " + (maxOrgs - dBCount));
        int antsCount = scnr.nextInt();
        while (antsCount < 0 && antsCount > (maxOrgs - dBCount)) {
            System.out.println("Input positive integer or zero for number of ants- must be less than or equal to: " + (maxOrgs - dBCount));
            antsCount = scnr.nextInt();
        }
        System.out.println("Creating window. Doodlebugs = red, ants = black.");
        System.out.println("Press enter to start:");



        //create window


        //ref point for drawing organisms is their upper left corner.
        //Bounds for organisms in x: [buffer, buffer + colmax*cellDimension]
        //Bounds for organisms in y: [buffer, buffer + rowmax*cellDimension]

        //ArrayList<Organism> allOrgs = new ArrayList<>();
        Random rand = new Random();
        ArrayList<ArrayList<Integer>> allInitial = new ArrayList<>();
        for (int row = 0; row < rowmax; row++) {
            for (int col = 0; col < colmax; col++) {
                gBlack.drawRect(buffer + col * cellDimension, buffer + row * cellDimension, cellDimension, cellDimension);
                ArrayList<Integer> newA = new ArrayList<>();
                newA.add(buffer + col * cellDimension);

                newA.add(buffer + row * cellDimension);
                //System.out.println(newA.get(0)+" "+newA.get(1));
                allInitial.add(newA);
            }
        }
        //create all organisms in dB
        for (int i = 0; i < antsCount; i++) {
            ArrayList<Integer> myPos = allInitialPos.get(rand.nextInt(allInitialPos.size()));
            //System.out.println("x: "+myPos.get(0)+ " y: "+myPos.get(1));
            allOrgs.add(new Ants(myPos.get(0), myPos.get(1), panel, Color.BLACK));
            //delete myPos from allInitialPos
            allInitialPos.remove(myPos);

        }


        for (int i = 0; i < dBCount; i++) {
            ArrayList<Integer> myPosdB = allInitialPos.get(rand.nextInt(allInitialPos.size()));

            allOrgs.add(new dB(myPosdB.get(0), myPosdB.get(1), panel, Color.RED));
            allInitialPos.remove(myPosdB);
        }

        /*
        //test- move to random coord in array returned by getValidCoords
        Random rand = new Random();
        allOrgs.get(0).move(allOrgs.get(0).getValidCoords().get(rand.nextInt(allOrgs.get(0).getValidCoords().size())));
            */

        for (int i = 0; i < allOrgs.size(); i++) {
            allOrgs.get(i).draw();
           // allOrgs.get(i).deleteOrg();
        }


        //append new vals to dataset everytime. then, pass dataset into BarChartDemo constructor
/*
        DefaultCategoryDataset dataset = new DefaultCategoryDataset(); //where add values to be plotted

        //create chart
        BarChartDemo demo = new BarChartDemo(dataset);
        demo.pack();
        //RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
/*
        /*
        dataset.addValue(7445, "Ants", "1");
        dataset.addValue(24448, "DoodleBugs", "1");
        dataset.addValue(4297, "Ants", "2");
        dataset.addValue(21022, "DoodleBugs", "2");


        BarChartDemo demo = new BarChartDemo(dataset);
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);


        //((Integer) myVal).toString()) as col key
        dataset.addValue(4297, "Ants", "3");
        dataset.addValue(21022, "DoodleBugs", "3");

        demo.pack();

         */

        //main loop

        int j = 0;
        String readin = scnr.nextLine();
        int habitat = 0;

        while (scnr.hasNextLine() && habitat <= maxOrgs) {

            if (readin.equals("")) {
                //panel.sleep(500);
                System.out.println("Timestep: " + (j + 1));
                j++;
                /*panel.clear();
                System.out.println("Timestep: " + (j + 1));
                j++;
                //grid
                gBlack.setColor(Color.BLACK);
                for (int row = 0; row < rowmax; row++) {
                    for (int col = 0; col < colmax; col++) {
                        gBlack.drawRect(buffer + col * cellDimension, buffer + row * cellDimension, cellDimension, cellDimension);
                    }
                }

                 */

                //orgs
                //start with dbs, then go to ants

                int dbPop = 0; //dbpop at certain timestep
                for (int i = 0; i < allOrgs.size(); i++) {
                    if (allOrgs.get(i) instanceof dB) {
                        //printArrayList(allOrgs.get(i).getCoordsAround());

                        if (allOrgs.get(i).isDead()) {
                            //delete
                            allOrgs.get(i).deleteOrg(); //draw blank grid over space where org was
                            allOrgs.remove(allOrgs.get(i)); //remove it.



                        } else {
                            dbPop++;
                            allOrgs.get(i).move(allOrgs.get(i).getValidCoords().get(rand.nextInt(allOrgs.get(i).getValidCoords().size())));
                        }
                    }
                }

                dataset.addValue(dbPop, "DoodleBugs", ((Integer) j).toString());


                int antPop = 0;
                for (int i = 0; i < allOrgs.size(); i++) {
                    if (allOrgs.get(i) instanceof Ants) {
                        if (allOrgs.get(i).isDead()) {
                            //allOrgs.get(i).deleteOrg(); //draw blank grid over the space where the org was
                            allOrgs.remove(allOrgs.get(i)); //delete it from array

                        } else {
                            //implement draw in move
                            antPop++;
                            allOrgs.get(i).move(allOrgs.get(i).getValidCoords().get(rand.nextInt(allOrgs.get(i).getValidCoords().size())));

                        }
                    }
                }

                dataset.addValue(antPop, "Ants", ((Integer) j).toString());

            /*
                for (int i = 0; i < allOrgs.size(); i++) {
                    //System.out.println("Org: "+allOrgs.get(i).getX()+" "+allOrgs.get(i).getY());
                    allOrgs.get(i).draw();
                    /*if (allOrgs.get(i) instanceof dB){
                        System.out.println(1);
                    }
                }
            */
                //System.out.println(allOrgs.size());

                demo.pack();

                readin = scnr.nextLine();
            }
        }

        //System.out.println("Habitat is at capacity.");


    }

    public static ArrayList<ArrayList<Integer>> coordsArounddB(Organism org, ArrayList<Organism> allOrgs) {
        ArrayList<ArrayList<Integer>> allCoordsAround = org.getCoordsAround();

        //cycle through allCoordsAround. Find the ones that have a dB in them.

        ArrayList<ArrayList<Integer>> dB = new ArrayList<>();

        for (int i = 0; i < allOrgs.size(); i++) { //cycle through allOrgs. Get the pair x, y for each org if it's a db
            if (allOrgs.get(i) instanceof dB) {
                ArrayList<Integer> newXY = new ArrayList<>();
                newXY.add(allOrgs.get(i).getX());
                newXY.add(allOrgs.get(i).getY());
                dB.add(newXY);
            }
        }

        allCoordsAround.retainAll(dB); //only keep the dB coords in allCoordsAround;

        return allCoordsAround;
    }

    public static ArrayList<ArrayList<Integer>> coordsAroundAnt(Organism org, ArrayList<Organism> allOrgs) {
        ArrayList<ArrayList<Integer>> allCoordsAround = org.getCoordsAround();

        //cycle through allCoordsAround. Find the ones that have a dB in them.

        ArrayList<ArrayList<Integer>> ants = new ArrayList<>();

        for (int i = 0; i < allOrgs.size(); i++) { //cycle through allOrgs. Get the pair x, y for each org if it's a db
            if (allOrgs.get(i) instanceof Ants) {
                ArrayList<Integer> newXY = new ArrayList<>();
                newXY.add(allOrgs.get(i).getX());
                newXY.add(allOrgs.get(i).getY());
                ants.add(newXY);
            }
        }

        allCoordsAround.retainAll(ants); //only keep the dB coords in allCoordsAround;

        return allCoordsAround;
    }

    public static void printArrayList(ArrayList<ArrayList<Integer>> myArrayList) {
        for (int i = 0; i < myArrayList.size(); i++) { //for every pair
            System.out.print("Pair " + (i + 1) + ": ");
            for (int j = 0; j < myArrayList.get(i).size(); j++) {
                System.out.print(myArrayList.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }
}






