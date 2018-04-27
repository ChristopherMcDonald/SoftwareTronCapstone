/* ===========================================================
* JFreeChart : a free chart library for the Java(tm) platform
* ===========================================================
*
* (C) Copyright 2000-2004, by Object Refinery Limited and Contributors.
*
* Project Info: http://www.jfree.org/jfreechart/index.html
*
* This library is free software; you can redistribute it and/or modify it under the terms
* of the GNU Lesser General Public License as published by the Free Software Foundation;
* either version 2.1 of the License, or (at your option) any later version.
*
* This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
* without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
* See the GNU Lesser General Public License for more details.
*
* You should have received a copy of the GNU Lesser General Public License along with this
* library; if not, write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330,
* Boston, MA 02111-1307, USA.
*
* [Java is a trademark or registered trademark of Sun Microsystems, Inc.
* in the United States and other countries.] *

* (C) Copyright 2004, by Object Refinery Limited and Contributors.
*
* Original Author: David Gilbert (for Object Refinery Limited);
* Contributor(s): -;
*
* $Id: LineChartDemo6.java,v 1.5 2004/04/26 19:11:55 taqua Exp $
*
* Changes:
* 27-Jan-2004 : Version 1 (DG);
*
*/
package ui;

import java.awt.Color;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;


/**
* A simple demonstration application showing how to create a line chart using data from an
* {@link XYDataset}.
*
*/
public class Graph extends ApplicationFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ChartPanel chartPanel;
	/**
	* Creates a new demo.
	*
	* @param title the frame title.
	*/
	public Graph(final String title, Double[] data, String xAxis, int step) {
		super(title);
		CategoryDataset dataset = createDataset(data, step, xAxis);
		JFreeChart chart = createChart(dataset, xAxis);
		chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(403, 170));
		setContentPane(chartPanel);
	}
	
	/**
	* Creates a sample dataset.
	*
	* @return a sample dataset.
	*/
	private CategoryDataset createDataset(Double[] data, int step, String xAxis) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for(int i=0; i<data.length;i++) {
			if(data[i] != -1.0) {
				if(xAxis.equals("zone")) {
					dataset.setValue((Number)data[i], "Returns", i+2);
				}else {
					dataset.setValue((Number)data[i],"Returns", i*step);
				}
			}
		}
		
		return dataset;
	
	}
	
	/**
	* Creates a chart.
	*
	* @param dataset the data for the chart.
	*
	* @return a chart.
	*/
	private JFreeChart createChart(final CategoryDataset dataset, String xAxis) {
		
		// create the chart
		final JFreeChart chart = ChartFactory.createBarChart(
		"", // chart title
		xAxis, // x axis label
		"% Return", // y axis label
		dataset, // data
		PlotOrientation.VERTICAL,
		false, // include legend
		true, // tooltips
		false // urls
		);
		
		// NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART
		chart.setBackgroundPaint(Color.white);
		
		// get a reference to the plot for further customisation
		//final Plot plot = chart.getPlot();
		
//		NumberAxis range = (NumberAxis) plot.getRangeAxis();
//        range.setRange(0, 100);
//        range.setTickUnit(new NumberTickUnit(20));
		chart.getCategoryPlot().getRangeAxis().setLowerBound(0.0);
		chart.getCategoryPlot().getRangeAxis().setUpperBound(100.0);
        
//		CategoryAxis axis = new CategoryAxis();
//		chart.getCategoryPlot().setDomainAxis(axis);
//		axis.setPlot(plot);
//        if (xAxis.equals("zone")){
//        	 NumberAxis domain = (NumberAxis) plot.getDomainAxis();
//             plot.setRange(2, 17);
//             domain.setTickUnit(new NumberTickUnit(1));
//             domain.setVerticalTickLabels(true);
//        }
//        else if(xAxis.equals("roll")){
//        	 NumberAxis domain = (NumberAxis) plot.getDomainAxis();
//             domain.setRange(0, 270);
//             domain.setTickUnit(new NumberTickUnit(90));
//             domain.setVerticalTickLabels(true);
//        }
//        else if(xAxis.equals("pitch")){
//        	 NumberAxis domain = (NumberAxis) plot.getDomainAxis();
//             domain.setRange(0, 30);
//             domain.setTickUnit(new NumberTickUnit(10));
//             domain.setVerticalTickLabels(true);
//        }
        
//		plot.setBackgroundPaint(Color.lightGray);
//		plot.setDomainGridlinePaint(Color.white);
//		plot.setRangeGridlinePaint(Color.white);
//		
//		final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
//		renderer.setSeriesLinesVisible(0, false);
//		renderer.setSeriesShapesVisible(1, false);
//		plot.setRenderer(renderer);
//		
		// change the auto tick unit selection to integer units only
//		final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
//		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
//		
		return chart;	
	}
}