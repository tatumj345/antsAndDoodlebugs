README

Ants and Doodlebugs- A simulation to demonstrate the effects of predator-prey relationships on population dynamics in a habitat.
Tatum Johnson
10/17/23

About: Ants and doodlebugs live in a habitat, and doodlebugs prey on ants. The habitat is represented by a grid, the ants are represented by black dots, and the doodlebugs are represented by red dots. Users can specify the size of the habitat and the initial populations of ants and doodlebugs. When the user presses enter, time in the simulation advances by one "timestep." Every timestep, the ants and doodlebugs move according to the following rules: 

Rules for ants: Every timestep, each ant randomly moves to an empty adjacent cell in the grid. If the ant survives for three timesteps, it spawns another ant in a randomly selected adjacent cell if there is room.

Rules for doodlebugs: Every timestep, the doodlebug can move to an adjacent cell in the grid as long as another doodlebug doesn't occupy that spot. If the adjacent cell contains an ant, the doodlebug "eats" the ant. If a doodlebug doesn't eat for three timesteps, it "dies." If the doodlebug survives for eight timesteps, it spawns another doodlebug in a random, adjacent empty cell.

After every timestep, the number of doodlebugs and number of ants is displayed in a graph. This allows the user to compare how the populations change over time with different initial habitat sizes and initial population numbers.


BarChartDemo.java is adapted from: BarChartDemo1.java. Copyright notice:
        * ==================
        *
        * Copyright (c) 2005-2014, Object Refinery Limited.
        * All rights reserved.
        *
        * http://www.jfree.org/jfreechart/index.html
        *
        * Redistribution and use in source and binary forms, with or without
        * modification, are permitted provided that the following conditions are met:
        *   - Redistributions of source code must retain the above copyright
        *     notice, this list of conditions and the following disclaimer.
        *   - Redistributions in binary form must reproduce the above copyright
        *     notice, this list of conditions and the following disclaimer in the
        *     documentation and/or other materials provided with the distribution.
        *   - Neither the name of the Object Refinery Limited nor the
        *     names of its contributors may be used to endorse or promote products
        *     derived from this software without specific prior written permission.
        *
        * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
        * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
        * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
        * ARE DISCLAIMED. IN NO EVENT SHALL OBJECT REFINERY LIMITED BE LIABLE FOR ANY
        * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
        * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
        * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
        * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
        * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
        * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
        *
        * Original Author:  David Gilbert (for Object Refinery Limited);
        * Contributor(s):   -;
        *
        * Changes
        * -------
        * 09-Mar-2005 : Version 1 (DG);
        * 11-Mar-2014 : Use new ChartFactory method (DG);
        * 25-Jun-2014 : Update to use real data (DG);
        *

DrawingPanel.java authorship and information:
/*
 * =====================================================================
 * DrawingPanel.java
 * Simplified Java drawing window class
 * to accompany Building Java Programs textbook and associated materials
 * 
 * authors: Stuart Reges, University of Washington
 *          Marty Stepp
 * version: 4.07, 2022/04/07 (BJP 5th edition)
 * (make sure to also update version string in Javadoc header below!)
 * =====================================================================
 *
 * COMPATIBILITY NOTE: This version of DrawingPanel requires Java 8 or higher.
 * If you need a version that works on Java 7 or lower, please see our
 * web site at http://www.buildingjavaprograms.com/ .
 * To make this file work on Java 7 and lower, you must make two small
 * modifications to its source code.
 * Search for the two occurrences of the annotation @FunctionalInterface
 * and comment them out or remove those lines.
 * Then the file should compile and run properly on older versions of Java.
 * 
 * =====================================================================
 * 
 * The DrawingPanel class provides a simple interface for drawing persistent
 * images using a Graphics object.  An internal BufferedImage object is used
 * to keep track of what has been drawn.  A client of the class simply
 * constructs a DrawingPanel of a particular size and then draws on it with
 * the Graphics object, setting the background color if they so choose.
 * See JavaDoc comments below for more information.
 */
        
