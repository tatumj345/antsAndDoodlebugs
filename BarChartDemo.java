/* BarChartDemo class adapted from: BarChartDemo1.java
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
        */


import java.awt.Color;
import java.awt.Dimension;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class BarChartDemo extends ApplicationFrame {

    private static final long serialVersionUID = 1L;

    static {
        // set a theme using the new shadow generator feature available in
        // 1.0.14 - for backwards compatibility it is not enabled by default
        //ChartFactory.setChartTheme(new StandardChartTheme("JFree/Shadow",
               // true));
    }

    /**
     * Creates a new demo instance.
     *
     * @param dataset the data to plot.
     */
    public BarChartDemo(CategoryDataset dataset) {
        super("Population"); //from ApplicationFrame
        //CategoryDataset dataset = createDataset(); //bar chart settings
        JFreeChart chart = createChart(dataset);
        Main.frame.getContentPane().add(new ChartPanel(chart));
        /*
        ChartPanel chartPanel = new ChartPanel(chart, false);
        chartPanel.setBackground(null);
        chartPanel.setFillZoomRectangle(true);
        chartPanel.setMouseWheelEnabled(true);
        chartPanel.setDismissDelay(Integer.MAX_VALUE);
        chartPanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(chartPanel);

         */
        Main.frame.pack();
        Main.frame.setVisible(true);
    }

    /**
     * Returns a sample dataset.
     *
     * @return The dataset.
     */
    private static CategoryDataset createDataset() {
        /*
        DefaultCategoryDataset dataset = new DefaultCategoryDataset(); //where add values to be plotted
        dataset.addValue(7445, "JFreeSVG", "Warm-up");
        dataset.addValue(24448, "Batik", "Warm-up");
        dataset.addValue(4297, "JFreeSVG", "Test");
        dataset.addValue(21022, "Batik", "Test");

         */
        DefaultCategoryDataset dataset = new DefaultCategoryDataset(); //where add values to be plotted

        dataset.addValue(7445, "Ants", "1");
        dataset.addValue(24448, "DoodleBugs", "1");
        dataset.addValue(4297, "Ants", "2");
        dataset.addValue(21022, "DoodleBugs", "2");
        return dataset;
    }

    /**
     * Creates a sample chart.
     *
     * @param dataset the dataset.
     * @return The chart.
     */
    private static JFreeChart createChart(CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createLineChart(
                "DoodleBug and Ant Population v. Time","Time" /* x-axis label*/,
                "# of organisms" /* y-axis label */, dataset); //need to change this to a line graph. changed to createLineChart
        //chart.addSubtitle(new TextTitle("Time to generate 1000 charts in SVG "
                //+ "format (lower bars = better performance)"));
        chart.setBackgroundPaint(null);
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setBackgroundPaint(null);

        // ******************************************************************
        //  More than 150 demo applications are included with the JFreeChart
        //  Developer Guide...for more information, see:
        //
        //  >   http://www.object-refinery.com/jfreechart/guide.html
        //
        // ******************************************************************

        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
        renderer.setDrawOutlines(false);
        chart.getLegend().setFrame(BlockBorder.NONE);
        return chart;
    }
}
