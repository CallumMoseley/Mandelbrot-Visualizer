import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

public class MandelbrotPanel extends JPanel implements MouseListener, MouseMotionListener, KeyListener
{
	private int maxIter;
	
	private double xRange, yRange;
	private double xCentre, yCentre;
	
	public MandelbrotPanel()
	{
		setPreferredSize(new Dimension(1344, 768));
		
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
		setFocusable(true);
		
		xRange = 3.5;
		yRange = 2;
		xCentre = -0.75;
		yCentre = 0;
		
		maxIter = 150;
	}
	
	public int[] mapColour(double i, double x, double y)
	{
//		if (i < maxIter)
//		{
//			double zn = Math.sqrt(x * x + y * y);
//			double nu = Math.log(Math.log(zn) / Math.log(2)) / Math.log(2);
//			i = i + 1 - nu;
//		}
//		
//		int colour1 = (int) ((-Math.cos(2*Math.PI * (int) (i) / (2 * maxIter)) + 1) / 2 * 255);
//		int colour2 = (int) ((-Math.cos(2*Math.PI * (int) (i + 1) / (2 * maxIter)) + 1) / 2 * 255);
//		
//		int colour = (int) (Math.min(colour1, colour2) + Math.abs(colour1 - colour2) * (i % 1));
		
//		int colour = (int) ((-Math.cos(2*Math.PI * (int) (i) / (2 * maxIter)) + 1) / 2 * 255);
		
		int colour = (int) (Math.log(i) / Math.log(maxIter) * 255);
		
		return new int[] {colour, colour, colour};
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		for (int x1 = 0; x1 < getWidth(); x1++)
		{
			for (int y1 = 0; y1 < getHeight(); y1++)
			{
				double x0 = ((double) x1 / getWidth() * xRange - xRange / 2 + xCentre);
				double y0 = ((double) y1 / getHeight() * yRange - yRange / 2 + yCentre);
				
				double x = 0;
				double y = 0;
				
				int iter = 0;
				
				while (x * x + y * y < 4 && iter < maxIter)
				{
					double xTemp = x * x - y * y + x0;
					double yTemp = 2 * x * y + y0;
					if (x == xTemp && y == yTemp)
					{
						iter = maxIter;
						break;
					}
					x = xTemp;
					y = yTemp;
					iter++;
				}
				
				int[] colour = mapColour(iter, x, y);
				g.setColor(new Color(colour[0], colour[1], colour[2], 255));
				g.drawRect(x1, y1, 1, 1);
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_Z)
		{
			xRange *= 0.75;
			yRange *= 0.75;
		}
		if (e.getKeyCode() == KeyEvent.VK_X)
		{
			xRange /= 0.75;
			yRange /= 0.75;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			yCentre -= yRange / 10;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			yCentre += yRange / 10;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			xCentre -= xRange / 10;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			xCentre += xRange / 10;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			xRange = 3.5;
			xCentre = -0.75;
			yRange = 2;
			yCentre = 1;
		}
		
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
	}

	@Override
	public void mouseDragged(MouseEvent e)
	{
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
	}
}