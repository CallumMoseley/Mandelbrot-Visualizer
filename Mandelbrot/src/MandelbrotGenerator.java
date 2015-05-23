import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MandelbrotGenerator
{
	public static final int WIDTH = 1920 * 13;
	public static final int HEIGHT = 1080 * 13;

	public static void main(String[] args)
	{
		BufferedImage bi = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		WritableRaster wr = bi.getRaster();
		
		for (int x = 0; x < WIDTH; x++)
		{
			for (int y = 0; y < HEIGHT; y++)
			{
				double x0 = ((double) x / WIDTH) * 3.5 - 2.5;
				double y0 = ((double) y / HEIGHT) * 2 - 1;
				
				double xa = 0;
				double ya = 0;
				
				int iteration = 0;
				int maxIteration = 5000;
				
				while (xa * xa + ya * ya < 4 && iteration < maxIteration)
				{
					double xtemp = xa * xa - ya * ya + x0;
					ya = 2 * xa * ya + y0;
					xa = xtemp;
					iteration++;
				}
				
				wr.setPixel(x, y, mapColour(iteration, maxIteration));
			}
		}
		
		try
		{
			int imgNo = 0;
			while (true)
			{
				File f = new File("mandelbrot" + imgNo + ".png");
				if (f.exists())
				{
					imgNo++;
				}
				else
				{
					break;
				}
			}
			ImageIO.write(bi, "png", new File("mandelbrot" + imgNo + ".png"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static int[] mapColour(int iter, int maxIter)
	{
		int colour = (int) (Math.log(iter) / Math.log(maxIter) * 255);
		
		return new int[] {colour, colour, colour};
	}
}