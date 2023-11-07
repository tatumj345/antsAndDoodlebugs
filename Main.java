import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;


import java.awt.Color;

import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;

//getters and setter for static variables in Organism

//add graphing functionality

//inheritance with subclass default constructors
//import
//setters and getters for turnsSinceEaten
//cite drawing panel
//maybe have single dialogue box and press enter to control. Also press keyboard input?
public class Main {
    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;
    static ArrayList<Organism> allOrgs = new ArrayList<>();
    static JFrame frame = new JFrame("Doodlebugs and Ants");
    static GridBagConstraints c = new GridBagConstraints();
    static int[] initVals = new int[2];
    public static void main(String[] args) {
        frame.setFocusable(true);
        frame.setResizable(false);
        frame.addKeyListener(new Keycheck());
        frame.setLayout(new GridBagLayout());
        if (shouldFill) {
            //natural height, maximum width
            c.fill = GridBagConstraints.HORIZONTAL;
        }

        if (shouldWeightX) {
            c.weightx = 0.5;
        }
        frame.setVisible(true);

        c.fill = GridBagConstraints.HORIZONTAL;


        c.gridx = 0;

        c.gridy = 0;


        DefaultCategoryDataset dataset = new DefaultCategoryDataset(); //where add values to be plotted
        //create chart
        BarChartDemo demo = new BarChartDemo(dataset);

        int rowsIn = 20;
        int colsIn = 20;
        int cellsIn = 15;

        int maxOrgs = rowsIn * colsIn;

        Organism.cellDimension = cellsIn; //50
        Organism.rowmax = rowsIn; //10
        Organism.colmax = colsIn; //12

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
        JTextField feedback = new JTextField("Welcome! Input initial values and click Go to start.");
        feedback.setFont(new java.awt.Font("Arial", Font.BOLD, 11));
        c.gridx = 0;
        c.gridy = 1;
        frame.add(feedback, c);

        JTextField space = new JTextField();
        space.setEditable(false);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        frame.add(space, c);

        JTextField antsField = new JTextField("Enter initial number of ants below:");
        c.fill = GridBagConstraints.HORIZONTAL;
        antsField.setFont(new java.awt.Font("Arial", Font.BOLD, 11));
        antsField.setEditable(false);
        c.gridx = 0;
        c.gridy = 3;
        frame.add(antsField, c);

        JTextField antsFieldVal = new JTextField();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 4;
        frame.add(antsFieldVal, c);

        JTextField dBField = new JTextField("Enter the initial number of doodlebugs below:");
        dBField.setEditable(false);
        dBField.setFont(new java.awt.Font("Arial", Font.BOLD, 11));
        c.gridx = 0;
        c.gridy = 5;
        frame.add(dBField, c);

        JTextField dBFieldVal = new JTextField();
        c.gridx = 0;
        c.gridy = 6;
        frame.add(dBFieldVal, c);
/*
        JTextField space2 = new JTextField();
        space2.setEditable(false);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 7;
        frame.add(space, c);

 */c.weighty = 0;
        c.ipady = 0;
        //c.ipady = 60;
        JTextArea info = new JTextArea("About");
        info.setEditable(false);
        info.setFont(new java.awt.Font("Arial",Font.PLAIN, 10));
        info.setLineWrap(true);
        info.setWrapStyleWord(true);
        //info.setPreferredSize(new Dimension(100,100));
        //info.setMaximumSize(new Dimension(100, 100));
        info.setText("About: Ants and doodlebugs live in a grid-like habitat, and doodlebugs prey on ants. The ants are represented by blue dots, and the doodlebugs are represented by red dots. Type in the initial populations of ants and doodlebugs, then press \"Go\" to advance the simulation by one timestep. Every timestep, the ants and doodlebugs move according to the following rules:" + "\n"+

                "\nRules for ants: Every timestep, each ant randomly moves to an empty adjacent cell in the grid. If the ant survives for three timesteps, it spawns another ant in a randomly selected adjacent cell if there is room." + "\n"+

                "\nRules for doodlebugs: Every timestep, the doodlebug can move to an adjacent cell in the grid as long as another doodlebug doesn't occupy that spot. If the adjacent cell contains an ant, the doodlebug \"eats\" the ant. If a doodlebug doesn't eat for three timesteps, it \"dies.\" If the doodlebug survives for eight timesteps, it spawns another doodlebug in a random, adjacent empty cell." + "\n"+

                "\nAfter every timestep, the number of doodlebugs and number of ants is displayed in a graph.");

        //c.weighty = 1;
        c.gridheight  = 7;
        c.gridx = 1;
        c.gridy = 1;
        frame.add(info, c);
        c.ipady = 0;

        JButton button = new JButton();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 40;
        button.setFont(new java.awt.Font("Arial", Font.BOLD, 14));
        c.gridx = 0;
        c.weighty = .5;
        c.gridy = 7;
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setBackground(Color.GREEN);
        button.setForeground(Color.BLACK);
        button.setText("Go (You can also select this button and press enter repeatedly.)");
        frame.add(button, c);



        frame.setSize(1100, 700);

        //make action listener for text field that automatically sets the text to 10. If user wants to change, they have to enter a valid number. if text value changed this triggers.

        initVals[0] = -1;
        initVals[1] = -1;
        button.addActionListener(new ActionListener() {
            int j = 0;
            Random rand1 = new Random();
            public void actionPerformed(ActionEvent e) {
                if (initVals[0] == -1) {
                    initVals = validate(antsFieldVal, dBFieldVal, feedback);
                    if (initVals[0] != -1){
                        //initial draw

                        Random rand1 = new Random();
                        int antsCount = initVals[0];
                        int dBCount = initVals[1];

                        //create all organisms in antsCount
                        for (int i = 0; i < antsCount; i++) {
                            ArrayList<Integer> myPos = allInitialPos.get(rand1.nextInt(allInitialPos.size()));
                            //System.out.println("x: "+myPos.get(0)+ " y: "+myPos.get(1));
                            allOrgs.add(new Ants(myPos.get(0), myPos.get(1), panel, Color.BLUE));
                            //delete myPos from allInitialPos
                            allInitialPos.remove(myPos);

                        }


                        for (int i = 0; i < dBCount; i++) {
                            ArrayList<Integer> myPosdB = allInitialPos.get(rand1.nextInt(allInitialPos.size()));

                            allOrgs.add(new dB(myPosdB.get(0), myPosdB.get(1), panel, Color.RED));
                            allInitialPos.remove(myPosdB);
                        }

                        for (int i = 0; i < allOrgs.size(); i++) {
                            allOrgs.get(i).draw();
                        }
                    }
                }
                else{
                    //run body of program. advance timestep
                    //System.out.println("in body");

                    int habitat = 0;
                    feedback.setText("Timestep: " + (j + 1));
                    //System.out.println("Timestep: " + (j + 1));
                    j++;

                    //orgs
                    //start with dbs, then go to ants

                    int dbPop = 0; //dbpop at certain timestep
                    for (int i = 0; i < allOrgs.size(); i++) {
                        if (allOrgs.get(i) instanceof dB) {

                            if (allOrgs.get(i).isDead()) {
                                //delete
                                allOrgs.get(i).deleteOrg(); //draw blank grid over space where org was
                                allOrgs.remove(allOrgs.get(i)); //remove it.


                            } else {
                                dbPop++;
                                allOrgs.get(i).move(allOrgs.get(i).getValidCoords().get(rand1.nextInt(allOrgs.get(i).getValidCoords().size())));
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
                                allOrgs.get(i).move(allOrgs.get(i).getValidCoords().get(rand1.nextInt(allOrgs.get(i).getValidCoords().size())));

                            }
                        }
                    }

                    dataset.addValue(antPop, "Ants", ((Integer) j).toString());

                    demo.pack();
                }
            }
        });
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
            //System.out.print("Pair " + (i + 1) + ": ");
            for (int j = 0; j < myArrayList.get(i).size(); j++) {
                //System.out.print(myArrayList.get(i).get(j) + " ");
            }
            //System.out.println();
        }
    }

    //whenever button is pressed, run this.
    public static int[] validate(JTextField myantsfield, JTextField mydbfield, JTextField feedback){
        int antsCount;
        int dBCount;
        int[] myreturns = new int[2];
        String antsfieldVal = myantsfield.getText();
        String dbfieldVal = mydbfield.getText();
        try {

           antsCount = Integer.valueOf(antsfieldVal);
            dBCount = Integer.valueOf(dbfieldVal);

            if (antsCount < 0) {
                throw new NumberFormatException();
            }
            if (dBCount < 0) {
                throw new NumberFormatException();
            }

            if (dBCount + antsCount > 400){
                throw new NumberFormatException();
            }

            myreturns[0] = antsCount;
            myreturns[1] = dBCount;

            return myreturns;

        } catch (NumberFormatException e) {
            feedback.setText("Initial # must be positive integer. Also, # of ants + # of doodlebugs must be <= 400.");

            myreturns[0] = -1;
            myreturns[1] = -1;
            return myreturns;

        }
    }
}