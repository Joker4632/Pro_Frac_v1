package fractalator.converter.colors;

import java.awt.Color;
import java.util.ArrayList;

public class PredefinedColorthemes {
	ArrayList<Colortheme> colorthemes = new ArrayList();
	Colortheme blackToWhite;
	Colortheme whiteToBlack;
	Colortheme blackToBlue;
	Colortheme rainbow;
	
	public PredefinedColorthemes(){
		blackToWhite = new Colortheme("Black-to-White");
		for(int i = 255; i > 0; i--){
			blackToWhite.setColor(i, new Color(i,i,i));
		}
		colorthemes.add(blackToWhite);
		
		whiteToBlack = new Colortheme("White-To-Black");
		int i2 = 255;
		for(int i = 0; i <= 255; i++){
			whiteToBlack.setColor(i2, new Color(i,i,i));
			i2--;
		}
		colorthemes.add(whiteToBlack);
		
		blackToBlue = new Colortheme("Black-To-Blue");
		for(int i = 0; i <= 255; i++){
			blackToBlue.setColor(i, new Color(0,(int)(i*208/255),i));
		}
		colorthemes.add(blackToBlue);
		
		rainbow = new Colortheme("Rainbow");
		for(int i = 0; i < 51; i++){
			rainbow.setColor(i+0, new Color(255,(int)(i*255/51),0));
		}
		for(int i = 0; i < 51; i++){
			rainbow.setColor(i+51, new Color(255-(int)(i*255/51),255,0));
		}
		for(int i = 0; i < 51; i++){
			rainbow.setColor(i+102, new Color(0,255,(int)(i*255/51)));
		}
		for(int i = 0; i < 51; i++){
			rainbow.setColor(i+153, new Color(0,255-(int)(i*255/51),255));
		}
		for(int i = 0; i < 52; i++){
			rainbow.setColor(i+204, new Color((int)(i*255/51),0,255));
		}
		rainbow.setColor(1, Color.BLACK);
		colorthemes.add(rainbow);
		
	}
	
	public ArrayList<Colortheme> importPredifined(){
		return colorthemes;
	}
	
	

}
