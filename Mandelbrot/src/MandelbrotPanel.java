import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

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
		
		maxIter = 200;
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
					y = 2 * x * y + y0;
					x = xTemp;
					iter++;
				}
				
				int colour = (int) ((double) iter / maxIter * 255);
				g.setColor(new Color(colour, colour, colour, 255));
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