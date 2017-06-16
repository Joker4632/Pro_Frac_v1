package fractalator.converter.colors;

import java.awt.Color;

public class Colortheme {
	private final String name;
	private Color[] colors = new Color[256];
	
	/**
	 * Creates a new Colortheme with the name 'name'. All Colors set to White.
	 * @param name of the colortheme
	 */
	public Colortheme(String name){
		this.name = name;
		for(Color c:colors){
			c = new Color(255,255,200);
		}
	}
	
	/**
	 * Get the name of the colortheme
	 * @return the name of colortheme
	 */
	public String name(){
		return name;
	}
	
	/**
	 * Set Color of Index 'index' to 'color'. Index Range: 0 to 255.
	 * @param index Index in Colortheme
	 * @param color Color the Index will have
	 */
	public void setColor(int index, Color color){
		colors[index] = color;
	}
	
	/**
	 * Set Color of the Indices of 'indices' to 'color'. Index Range: 0 to 255.
	 * @param indices Indices in Colortheme
	 * @param color Color the Index will have
	 */
	public void setColor(int[] indices, Color color){
		for(int index:indices){
			colors[index] = color;
		}
	}
	
	/**
	 * Returns the Color the value (0-255) describes in the Colortheme
	 * @param value is the index the color has in this theme
	 * @return the color of colors at Index 'value'
	 */
	public Color getColorOf(int value){
		return colors[value];
	}
	
	/**
	 * Returns the Color the value (0-255) describes in the Colortheme in Hexadecimal Code
	 * @param value is the index the color has in this theme
	 * @return the color of colors at Index 'value'
	 */
	public int getHexColorOf(int value){
		if(value<0){
			System.out.println("Colortheme.getHexColorOf() | value < 0");
			return colors[0].getRGB();
		}else if(value>255){
			System.out.println("Colortheme.getHexColorOf() | value > 255");
			return colors[255].getRGB();
		}else if(value<=255&&value>=0){
			return colors[value].getRGB();
		}else{
			System.out.println("Colortheme.getHexColorOf() | unexpected value: "+value);
			return colors[value].getRGB();
		}
		
	}
	
	/**
	 * NOT USED
	 * Get the color the colortheme has at Index ('value'+128). Input between -128 and 127.
	 * @param value to transform into color of colortheme
	 * @return color the colortheme refer to the value
	 */
	public Color getColorOf(byte value){
		return colors[(int)(value)+128];
	}
	
	/**
	 * NOT USED
	 * Get the color the colortheme has at Index ('value'+128). Input between -128 and 127.
	 * @param value to transform into color of colortheme
	 * @return color the colortheme refer to the value
	 */
	public Color getColorOf(short value){
		return colors[(int)(value)+128];
	}
	

}
