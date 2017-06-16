package fractalator.converter;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageToConverter {
	
	private BufferedImage image;
	private String path;
	private String fileextension;
	private String name;

	public ImageToConverter(String name, String path, BufferedImage image, String fileextension) {
		this.name = name;
		this.path = path;
		this.image = image;
		this. fileextension = fileextension;
		convert();
	}
	
	public ImageToConverter(String name, BufferedImage image, String fileextension){
		this.name = name;
		this.path = "./RenderedImages";
		this.image = image;
		this. fileextension = fileextension;
		convert();
	}
	
	public ImageToConverter(String name, String path, BufferedImage image ){
		this.name = name;
		this.path = path;
		this.image = image;
		this. fileextension = "png";
		convert();
	}
	
	public ImageToConverter(String name, BufferedImage image ){
		this.name = name;
		this.path = "./RenderedImages";
		this.image = image;
		this. fileextension = "png";
		convert();
	}
	
	private void convert(){
		try {
		    File outputfile = new File(path+"/"+name+"."+fileextension);
		    ImageIO.write(image, fileextension, outputfile);
		} catch (IOException ignored) {
		}
	}

}
