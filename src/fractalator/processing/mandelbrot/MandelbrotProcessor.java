package fractalator.processing.mandelbrot;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.text.NumberFormat;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;

import fractalator.converter.ImageToConverter;
import fractalator.converter.colors.Colortheme;
import fractalator.main.output.OutputManager;
import fractalator.main.surface.graphicalinterface.GraphicalInterface;
import fractalator.main.surface.userinterface.Abstract_UserInterface;
import fractalator.main.surface.userinterface.UserInterfaceV2;

public class MandelbrotProcessor implements Runnable {

	private final int MAX_DEPTH;
	private final Point START;
	private final Dimension RESOLUTION;
	private final double ZOOM;
	private final Colortheme CT;
	private ExecutorService service;
	private OutputManager output;
	private Abstract_UserInterface UI;
	private final boolean RENDERONLY;
	private NumberFormat nf;
	private NumberFormat nf2;
	
	private boolean kill = false;
	
	
	private BufferedImage I;
	private double zx, zy, cX, cY, tmp;

	public MandelbrotProcessor(int depth, Point start, Dimension resolution, int zoom, Colortheme ct, ExecutorService service,
			OutputManager output, Abstract_UserInterface UI, boolean renderonly) {
		MAX_DEPTH = depth;
		START = start;
		RESOLUTION = resolution;
		ZOOM = zoom;
		CT = ct;
		this.service = service;
		this.output= output;
		this.UI = UI;
		RENDERONLY = renderonly;
		
		nf = NumberFormat.getIntegerInstance();
		nf.setMinimumIntegerDigits(3);
		nf.setGroupingUsed(false);
		
		nf2 = NumberFormat.getIntegerInstance();
		nf2.setMinimumIntegerDigits(String.valueOf(resolution.width).length());
		nf2.setGroupingUsed(false);

		

	}
	
	public void kill(){
		kill = true;
	}

	@Override
	public void run() {
		try {
			output.print("  Calculating Mandelbrot ...");
		} catch (InterruptedException ignored) {
		}
		long totaltime = -1;
		long starttime = System.currentTimeMillis();
		double progress = 0;

		I = new BufferedImage(RESOLUTION.width, RESOLUTION.height, BufferedImage.TYPE_3BYTE_BGR);
		
		for (int y = 0; y < RESOLUTION.width; y++) {
			if(kill){
				break;
			}
			for (int x = 0; x < RESOLUTION.height; x++) {
				if(kill){
					break;
				}
				zx = zy = 0;
				cX = (x + START.x) / ZOOM;
				cY = (y - START.y) / ZOOM;
				int iter = MAX_DEPTH;
				while (zx * zx + zy * zy < 4 && iter > 0) {
					tmp = zx * zx - zy * zy + cX;
					zy = 2.0* zx * zy + cY;
					zx = tmp;
					iter--;
				}
				
				double d = (iter * 255 / MAX_DEPTH);
				I.setRGB(x, y, CT.getHexColorOf((int) d + 1));
			}
			try {
				int oldprogress = (int) progress;
				progress += 100 / RESOLUTION.getWidth();
				if(oldprogress!=(int)progress){
					output.print("  Calculating Mandelbrot ........ intermediate state: ("+nf2.format(y)+"/"+(int)RESOLUTION.getWidth()+") ......................... (" + nf.format(progress) + "%)");
				}
			} catch (InterruptedException ignored) {
			}

			totaltime = System.currentTimeMillis() - starttime;
		}
		//TODO Uncomment this
		UI.updateMPL(this);
		if(!kill){
			try {
				output.print("  Finished Mandelbrot!");
			} catch (InterruptedException ignored) {
			}
			if (RENDERONLY) {
				new ImageToConverter("mandelbrot", I);
			} else {
				GraphicalInterface GI = new GraphicalInterface(I,service, "Mandelbrot", CT.name(),
						RESOLUTION.width + " x " + RESOLUTION.height, String.valueOf(MAX_DEPTH), String.valueOf((int)ZOOM),
						totaltime);
				GI.setLocationRelativeTo(null);
				GI.setVisible(true);
			}
		}else{
			try {
				output.print("  MANDELBROT PROCESS IS TERMINATED!");
			} catch (InterruptedException ignored) {
			}
		}
		
	}
	class PointData{
		public int x,y,depth;
		public PointData(int x, int y, int depth){
			this.x = x;
			this.y = y;
			this.depth = depth;
		}
	}

}
