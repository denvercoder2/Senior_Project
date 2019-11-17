import JohnWork.main.SkyMap_Formulae_J;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Graphics;
import java.awt.List;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;


import com.jogamp.nativewindow.util.Rectangle;




public class DrawingSky extends Canvas{
	
	ArrayList<SpaceObj> spaceObjects;
	
	public ImageIcon draw(ArrayList<SpaceObj> spaceObjects) {
		this.spaceObjects = spaceObjects;
		JFrame frame = new JFrame("My Drawing");
        frame.setBackground(Color.BLACK);
        Canvas canvas = new DrawingSky();
        canvas.setSize(3300, 2550);
        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);
        
        return takeSnapShot(canvas);
        
	}
	
	public void paint(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());
    	ArrayList<Integer> list = createList();
    	int a = 0;
    	int b = 1;
    	int c = 2;
    	int d = 3;
    	g.setColor(Color.white);
    	for(int i = 0; i < (list.size())/4; i++) {
    		g.fillOval(list.get(a), list.get(b), list.get(c), list.get(d));
    		a = d + 1;
    		b = a + 1;
    		c = b + 1;
    		d = c + 1;
    	}
	}
	
	public ArrayList<Integer> createList(){
		// java - get screen size using the Toolkit class
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		// the screen height
		int screenHeight = (int)screenSize.getHeight();

		// the screen width
		int screenWidth = (int)screenSize.getWidth();
		
		//List to hold all locations to be drawn
    	ArrayList<Integer> locations = new ArrayList<>();
    	
    	
    	int a = 0;
    	int b = 1;
    	int c = 2;
    	int d = 3;
    	int size;
    	
    	for(int i = 0; i < spaceObjects.size(); i++) {
    		
    		//Check if mag is less than or equal to 6, add location to list
    		if((spaceObjects.get(i).getMag() <= 6) || !(spaceObjects.get(i).getType().equals("STAR"))) {
	    		locations.add(a, getX(screenHeight, screenWidth, 10, spaceObjects.get(i).getAzimuth()));
	    		locations.add(b, getY(screenHeight, screenWidth, 10, spaceObjects.get(i).getAltitude()));
	    		size = getSize(spaceObjects.get(i));
	    		locations.add(c, size);
	    		locations.add(d, size);
	    		a = d + 1;
	    		b = a + 1;
	    		c = b + 1;
	    		d = c + 1;
    		}
    	}
    	return locations;
    }
	
	public int getX(int screenHeight, int screenWidth, int r, int z, int a) {
		int x;
		
		//Cartesian coordinate
		x = (int) (r * Math.sin(z) * Math.cos(a));
		
		
		//Pixel Coordinate
		x = x + (screenWidth/2);
		
		return x;	
	}
	
	public int getY(int screenHeight, int screenWidth, int r, int z, int a) {
		int y;
		
		//Cartesian coordinate
		y = (int) (r * Math.cos(z) * Math.cos(a));
				
				
		//Pixel Coordinate
		y = (screenHeight/2) - y;		
		
		return y;	
	}
	
	public int getSize(SpaceObj object) {
		int size;
		
		//TEST SIZE -- Need to update method
		size = 5;
		
		return size;
	}
	
	public ImageIcon takeSnapShot(Component panel){
	       BufferedImage bufImage = new BufferedImage(panel.getSize().width, panel.getSize().height,BufferedImage.TYPE_INT_RGB);
	       System.out.println(panel.getSize().width+" "+panel.getSize().height);
	       panel.paint(bufImage.createGraphics());
	       String snapshotLocation = "C:\\Users\\alexx\\OneDrive\\Documents\\Fall 2019\\CS 499\\testScreenShot.jpeg";
		File imageFile = new File(snapshotLocation);
		ImageIcon imageIcon = new ImageIcon(bufImage);
	    try{
	        imageFile.createNewFile();
	        ImageIO.write(bufImage, "jpeg", imageFile);
	        System.out.println("Created picture");
	        
	    }catch(Exception ex){
	    	System.out.println("Did not create picture");
	    } 
	    
	    return imageIcon;
	}
	
}
