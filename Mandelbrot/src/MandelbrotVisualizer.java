import javax.swing.JFrame;

public class MandelbrotVisualizer extends JFrame
{
	MandelbrotPanel mp;
	
	public MandelbrotVisualizer()
	{
		super("Mandelbrot Visualizer");
		mp = new MandelbrotPanel(); 
		
		setContentPane(mp);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args)
	{
		MandelbrotVisualizer frame = new MandelbrotVisualizer();
		frame.pack();
		frame.setVisible(true);
	}
}