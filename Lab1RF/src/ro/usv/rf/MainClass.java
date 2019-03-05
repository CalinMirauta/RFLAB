package ro.usv.rf;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class MainClass extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static double[][] mat;

	public MainClass() {
		super("XY Line Chart Example with JFreechart");

		JPanel chartPanel = createChartPanel();
		add(chartPanel, BorderLayout.CENTER);

		setSize(640, 480);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	private JPanel createChartPanel() {
		// creates a line chart object
		// returns the chart panel
		String chartTitle = "Objects Movement Chart";
		String xAxisLabel = "X";
		String yAxisLabel = "Y";

		XYDataset dataset = createDataset();

		JFreeChart chart = ChartFactory.createXYLineChart(chartTitle, xAxisLabel, yAxisLabel, dataset);
		XYPlot xyPlot = (XYPlot) chart.getPlot();
		xyPlot.setDomainCrosshairVisible(true);
		xyPlot.setRangeCrosshairVisible(true);
		XYItemRenderer renderer = xyPlot.getRenderer();
		renderer.setSeriesPaint(0, Color.blue);
		NumberAxis domain = (NumberAxis) xyPlot.getDomainAxis();
		domain.setRange(0.00, 1.00);
		return new ChartPanel(chart);
	}

	public XYDataset createDataset() {
		// creates an XY dataset...
		// returns the dataset
		XYSeriesCollection dataset = new XYSeriesCollection();
		XYSeries series1 = new XYSeries("NormalizedLearningSet");

		for (int i = 0; i < mat.length; i++)
			{
				series1.add(mat[i][0], mat[i][1]);
			}
		dataset.addSeries(series1);

		return dataset;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		double[][] learningSet = FileUtils.readLearningSetFromFile(
				"C:\\Users\\calin\\Desktop\\lab1rf\\Lab1RF\\bin\\in.txt");
		mat = FileUtils.normalizeLearningSet(learningSet);
		FileUtils.writeLearningSetToFile(
				"C:\\Users\\calin\\Desktop\\lab1rf\\Lab1RF\\bin\\out.csv",
				FileUtils.normalizeLearningSet(learningSet));
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new MainClass().setVisible(true);
			}
		});
	}
}
