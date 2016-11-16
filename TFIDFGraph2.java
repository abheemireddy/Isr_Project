package project1;

import java.awt.*;
import java.awt.geom.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javax.swing.*;

public class TFIDFGraph2 extends JPanel {
	private static final long serialVersionUID = 1L;
	Words w = new Words();
	probability p = new probability();
	ArrayList<Double> dataList = new ArrayList<Double>();
	ArrayList<Double> idflist = new ArrayList<Double>();
	double temp=0.0;
	double t=0.0;
	Integer val=2;
	/* int [] data={1,5,7,9,45}; */
	TFIDFGraph2() {
		
		for (String s : w.d) {
			temp=w.FrequencyMap.get(s); //fij
			dataList.add(temp);
			t=p.numberOfWords(s); //ni
			t=20/t;
			idflist.add(Math.log(t) / Math.log(2));
			Collections.sort(dataList);
			Collections.reverse(dataList);
			 System.out.println("#####"+ idflist.size());
			//Collections.sort(idflist);
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
		// g2.draw(new Line2D.Double(PAD, PAD, PAD, PAD));
		// Draw abcissa.
		g2.draw(new Line2D.Double(PAD, h - PAD, w - PAD, h - PAD));
		double xInc = (double) (w - 2 * PAD) / (5000 - 1);
		double scale = (double) (h - 2 * PAD) / getMax();
		g2.setPaint(Color.red);
		// probability p=new probability();
		for (int i = 0; i < dataList.size(); i++) {
			double x = PAD + i * xInc;
			double y = h - PAD - scale * dataList.get(i);
			g2.fill(new Ellipse2D.Double(x - 2, y - 2, 4, 4));
		}

		g2.setPaint(Color.blue);
		for (int i = 0; i < idflist.size(); i++) {
			double x = PAD + i * xInc;
			double y = h - PAD  * idflist.get(i);
			//double y=5;
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
		f.add(new TFIDFGraph2());
		f.setSize(1800, 1500);
		f.setLocation(0, 0);
		f.setVisible(true);
	}
}
