

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Graphics;
import java.awt.List;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import JohnWork.main.SpaceObj;


public class DrawingSky extends Canvas {
	
    public ImageIcon draw() {
        JFrame frame = new JFrame("My Drawing");
        frame.setBackground(Color.BLACK);
        Canvas canvas = new DrawingSky();
        //canvas.setSize(1800, 1800);
        canvas.setSize(2000, 2000);
        frame.add(canvas);
        frame.pack();
        //frame.setVisible(true);
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
    	int color;
    	//g.setColor(Color.white);
    	System.out.println("----------------------\n"+(list.size()/4)+"\n-----------------------------\n");
    	for(int i = 0; i < (list.size()/4); i++) {
    		color = getRandom(1,4);
    		if(color == 1) {
    			g.setColor(Color.white);
    		}
    		else if(color == 2) {
    			g.setColor(Color.red);
    		}
    		else if(color == 3) {
    			g.setColor(Color.blue);
    		}
    		else if(color == 4) {
    			g.setColor(Color.green);
    		}
    		
    		g.fillOval(list.get(a), list.get(b), list.get(c), list.get(d));
    		a = d + 1;
    		b = a + 1;
    		c = b + 1;
    		d = c + 1;
    	}
    }
    
    public ArrayList<Integer> createList(){
    	ArrayList<Integer> test = new ArrayList<>();
    	int a = 0;
    	int b = 1;
    	int c = 2;
    	int d = 3;
    	int size;
    	
    	// java - get screen size using the Toolkit class
    			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    			
    			// the screen height
    			int screenHeight = (int)screenSize.getHeight();

    			// the screen width
    			int screenWidth = (int)screenSize.getWidth();
    	
    	//this.SpaceObjectList
    	
    	/*
    	 * int x = getX(1000, 2000, 1000, (int)GUI.spaceObjList.get(i).getAzimuth(), (int)GUI.spaceObjList.get(i).getAltitude());
			int y = getY(1000, 2000, 1000, (int)GUI.spaceObjList.get(i).getAzimuth(), (int)GUI.spaceObjList.get(i).getAltitude());
    		test.add(a, x);
    		test.add(b, y);
    	 */
    	for(int i = 0; i < AlexxWork2.spaceObjList.size(); i++) {
    		if(AlexxWork2.spaceObjList.get(i).getMagnitude() != null && (Double.valueOf(AlexxWork2.spaceObjList.get(i).getMagnitude()) <= 6.0) 
    				&& AlexxWork2.spaceObjList.get(i).getAltitude() > 1) {
	    		//raduius = 50000
    			int x = getX(1000, 1000, 500, (int)AlexxWork2.spaceObjList.get(i).getAzimuth(), (int)AlexxWork2.spaceObjList.get(i).getAltitude());
				int y = getY(1000, 1000, 500, (int)AlexxWork2.spaceObjList.get(i).getAzimuth(), (int)AlexxWork2.spaceObjList.get(i).getAltitude());
	    		test.add(a, x);
	    		test.add(b, y);
	    		size = getSize(Double.valueOf(AlexxWork2.spaceObjList.get(i).getMagnitude()));
	    		//size = 5;
	    		test.add(c, size);
	    		test.add(d, size);
	    		a = d + 1;
	    		b = a + 1;
	    		c = b + 1;
	    		d = c + 1;
    		}
    		
    	}
    	return test;
    }
    
    /*
    
    
    
    
    
    
    
    for(int i = 0; i < spaceObjects.size(); i++) {
		
		//Check if mag is less than or equal to 6, add location to list
		if(spaceObjects.get(i).getMagnitude() != null && (Double.valueOf(spaceObjects.get(i).getMagnitude()) <= 6)) {
			x = getX(screenHeight, screenWidth, 10, (int)spaceObjects.get(i).getAzimuth(), (int)spaceObjects.get(i).getAltitude());
			y = getY(screenHeight, screenWidth, 10, (int)spaceObjects.get(i).getAzimuth(), (int)spaceObjects.get(i).getAltitude());
    		locations.add(a, x);
    		locations.add(b, y);
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
*/
public int getX(int screenHeight, int screenWidth, int r, int z, int a) {
	int x;
	
	//Cartesian coordinate
	x = (int) (r * Math.sin(Math.toRadians(z)) * Math.cos(Math.toRadians(a)));
	
	
	
	//Pixel Coordinate
	//x = (int) (Math.toRadians(x) + (screenWidth/2));
	x = x + (screenWidth/2);
	
	return x;	
}

public int getY(int screenHeight, int screenWidth, int r, int z, int a) {
	int y;
	
	//Cartesian coordinate
	y = (int) (r * Math.cos(Math.toRadians(z)) * Math.cos(Math.toRadians(a)));
			
			
	//Pixel Coordinate
	//y = (screenHeight/2) - (int) (Math.toDegrees(y));		
	//y = (screenHeight/2) - (int) (Math.toRadians(y));
	y = (screenHeight/2) - y;
	
	return y;	
}

public int getSize(Double mag) {
	int size = 0;
	if(mag >= 5.0) {
		size = 2;
	}
	else if(mag >= 4.0) {
		size = 4;
	}
	else if(mag >= 3.0) {
		size = 6;
	}
	else if(mag >= 2.0) {
		size = 8;
	}
	else if(mag >= 1.0) {
		size = 10;
	}
	else if(mag >= 0.0) {
		size = 12;
	}
	else {
		size = 14;
	}
	
	
	return size;
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
  /*  
    static 
    public static void captureComponent(Component component) {
    	java.awt.Rectangle rect = component.getBounds();
    	
    	try {
    		String format = "jpeg";
    		String fileName = "C:\\Users\\alexx\\OneDrive\\Documents\\Fall 2019\\CS 499\\drawing.jpeg";
    		BufferedImage captureImage =
    	    new BufferedImage(rect.width, rect.height,
    	    		BufferedImage.TYPE_INT_ARGB);
    		component.paint(captureImage.getGraphics());
    	
    		ImageIO.write(captureImage, format, new File(fileName));
    	
    		System.out.printf("The screenshot of %s was saved!", component.getName());
    		} catch (IOException ex) {
    			System.err.println(ex);
    	����}
    }
    */
    
    public ImageIcon takeSnapShot(Component panel){
	       BufferedImage bufImage = new BufferedImage(panel.getSize().width, panel.getSize().height,BufferedImage.TYPE_INT_RGB);
	       System.out.println(panel.getSize().width+" "+panel.getSize().height);
	       panel.paint(bufImage.createGraphics());
	       ImageIcon imageIcon = new ImageIcon(bufImage);
	       String snapshotLocation = "C:\\Users\\alexx\\OneDrive\\Documents\\Fall 2019\\CS 499\\testScreenShot.jpeg";
		File imageFile = new File(snapshotLocation);
	    try{
	        imageFile.createNewFile();
	        ImageIO.write(bufImage, "jpeg", imageFile);
	        System.out.println("Created picture");
	        
	    }catch(Exception ex){
	    	System.out.println("Did not create picture");
	    } 
	    return imageIcon;
	}
    public static int getRandom(int min, int max){
        int x = (int) ((Math.random()*((max-min)+1))+min);
        return x;
    }

}