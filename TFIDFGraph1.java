package project1;

import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
import javax.swing.*;

public class TFIDFGraph1 extends JPanel {
	private static final long serialVersionUID = 1L;
	Words w = new Words();
	probability p = new probability();
	ArrayList<Double> dataList = new ArrayList<Double>();
	ArrayList<Double> idflist = new ArrayList<Double>();
	double temp = 0.0;
	double t = 0.0;

	double maxfij = 0.0;
	double maxni = 0.0;

	TFIDFGraph1() {

		for (String s : w.d) {
			temp = w.FrequencyMap.get(s); // fij
			dataList.add(1.0);
			idflist.add(1.0);
		}
	}

	final int PAD = 20;

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		int w = getWidth();
		int h = getHeight();
		
		// Draw ordinate.
		g2.draw(new Line2D.Double(PAD, PAD, PAD, h - PAD));
		
		// Draw abcissa.
		g2.draw(new Line2D.Double(PAD, h - PAD, w - PAD, h - PAD));
		double xInc = (double) (w - 2 * PAD) / (2000 - 1);
		double scale = (double) (h - 2 * PAD) / getMax();

		// plot tf values
		g2.setPaint(Color.red);
		for (int i = 0; i < dataList.size(); i++) {
			double x = PAD + i * xInc;
			double y = h - PAD - scale * dataList.get(i);
			g2.fill(new Ellipse2D.Double(x - 2, y - 2, 4, 4));
		}
			
		//plot Idf values
		g2.setPaint(Color.blue);
		for (int i = 0; i < idflist.size(); i++) {
			double x = PAD + i * xInc;
			// double y = h - PAD * idflist.get(i);
			double y = h - PAD - scale - 5 * idflist.get(i);
			// y value is scaled as it is overriding tf values
			g2.fill(new Ellipse2D.Double(x - 2, y - 2, 4, 4));
		}

	}

	private Double getMax() {
		Double max = (double) -Integer.MAX_VALUE;
		for (int i = 0; i < dataList.size(); i++) {
			if (dataList.get(i) > max)
				max = dataList.get(i);
		}
		return max;
	}

	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(new TFIDFGraph1());
		f.setSize(1000, 600);
		f.setLocation(0, 0);
		f.setVisible(true);
	}
}
