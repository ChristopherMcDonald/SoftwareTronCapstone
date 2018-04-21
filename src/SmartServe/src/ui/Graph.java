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
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
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
	public Graph(final String title) {
	
		super(title);
		final XYDataset dataset = createDataset();
		final JFreeChart chart = createChart(dataset, "xAxis String");
		chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(363, 170));
		
		setContentPane(chartPanel);
	
	}
	
	/**
	* Creates a sample dataset.
	*
	* @return a sample dataset.
	*/
	private XYDataset createDataset() {
		
		final XYSeries series2 = new XYSeries("Second");
		series2.add(1.0, 5.0);
		series2.add(2.0, 7.0);
		series2.add(3.0, 6.0);
		series2.add(4.0, 8.0);
		series2.add(5.0, 4.0);
		series2.add(6.0, 4.0);
		series2.add(7.0, 2.0);
		series2.add(8.0, 1.0);
		
		
		final XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series2);
		
		return dataset;
	
	}
	
	/**
	* Creates a chart.
	*
	* @param dataset the data for the chart.
	*
	* @return a chart.
	*/
	private JFreeChart createChart(final XYDataset dataset, String xAxis) {
		
		// create the chart
		final JFreeChart chart = ChartFactory.createXYLineChart(
		"Line Chart Demo 6", // chart title
		xAxis, // x axis label
		"Y", // y axis label
		dataset, // data
		PlotOrientation.VERTICAL,
		true, // include legend
		true, // tooltips
		false // urls
		);
		
		// NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART
		chart.setBackgroundPaint(Color.white);
		
		// get a reference to the plot for further customisation
		final XYPlot plot = chart.getXYPlot();
		plot.setBackgroundPaint(Color.lightGray);
		plot.setDomainGridlinePaint(Color.white);
		plot.setRangeGridlinePaint(Color.white);
		
		// change the auto tick unit selection to integer units only
		final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		
		return chart;	
	}
}