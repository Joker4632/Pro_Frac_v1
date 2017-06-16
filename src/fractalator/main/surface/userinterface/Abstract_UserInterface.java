package fractalator.main.surface.userinterface;

import javax.swing.JFrame;

import fractalator.processing.mandelbrot.MandelbrotProcessor;

/*
 * Class used to create independent Superclass for UserInterfaces which will be used to separate Mandelbrot Processor Classes from the used UIs.
 */
public abstract class Abstract_UserInterface extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * 
	 */
	abstract void adjustSettings();

	/*
	 * 
	 */
	abstract void initializeGUI();

	/*
	* 
	*/
	public abstract void updateMPL(MandelbrotProcessor MP);

	/*
	 * 
	 */
	abstract void onExit();
}
