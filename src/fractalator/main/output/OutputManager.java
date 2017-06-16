package fractalator.main.output;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import fractalator.main.surface.log.ProcessInterface;


public class OutputManager implements Runnable {
	private volatile boolean shutdown;
	private BlockingQueue<String> queue = new ArrayBlockingQueue<String>(42);
	ProcessInterface PI = new ProcessInterface();
	
	
	
	public OutputManager(){
		shutdown=false;
		PI.setVisible(false);
	}

	public void shutdown() {
		shutdown = true;
	}

	public void print(String line) throws InterruptedException {
		queue.put(line);
	}
	
	public void showPI(){
		PI.setVisible(true);
	}
	

	@Override
	public void run() {
		
		while (shutdown == false) {
			String line = queue.poll();
			if (line!=null) {
				if(line.equals("  Calculating Mandelbrot ...")){
					PI.setVisible(true);
				}
				PI.textarea_console.append(line+"\n");
				System.out.println(line);
				if(line.equals("  Finished Mandelbrot!")){
					PI.setVisible(false);
				}
			}
			
			
		}
		while (!queue.isEmpty()) {
			try {
				String line = queue.take();
				System.out.println(line);
			} catch (InterruptedException e) {
			}
		}
	}
}
