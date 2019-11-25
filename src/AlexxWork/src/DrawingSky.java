

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
	
	ArrayList<String> starLabels;
	ArrayList<String> constLabels;
	ArrayList<String> planetLabels;
	ArrayList<String> mesrLabels;
	int[] hercules = new int[44];
	ArrayList<String> herculesNames;
	ArrayList<Integer> herculesLocations;
	
	
	ArrayList<String> constNames;
	
    public ImageIcon draw() {
    	//loadConstNames();
    	
        JFrame frame = new JFrame("My Drawing");
        frame.setBackground(Color.BLACK);
        Canvas canvas = new DrawingSky();
        //canvas.setSize(1800, 1800);
        canvas.setSize(2250, 2250);
        frame.add(canvas);
        frame.pack();
        //frame.setVisible(true);
        return takeSnapShot(canvas);
    }

    public void paint(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());
        loadConstNames();
        ArrayList<Integer> list = createList();
    	int a = 0;
    	int b = 1;
    	int c = 2;
    	int d = 3;
    	int index;
    	int color;
    	g.setColor(Color.white);
    	System.out.println("----------------------\n"+(list.size()/4)+"\n-----------------------------\n");
    	for(int i = 0; i < (list.size()/4); i++) {
    		/*color = getRandom(1,4);
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
    		*/
    		
    		g.fillOval(list.get(a), list.get(b), list.get(c), list.get(d));
    		a = d + 1;
    		b = a + 1;
    		c = b + 1;
    		d = c + 1;
    	}
    	index = 0;
    	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~"+(constLabels.size()/3)+"~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    	for(int i = 0; i < (constLabels.size()/3); i++) {
    		g.drawString(constLabels.get(index), Integer.valueOf(constLabels.get(index+1)), Integer.valueOf(constLabels.get(index+2)));
    		index +=3;
    	}
    	index = 0;
    	for(int i = 0; i < (starLabels.size()/3); i++) {
    		g.drawString(starLabels.get(index), Integer.valueOf(starLabels.get(index+1)), Integer.valueOf(starLabels.get(index+2)));
    		index +=3;
    	}
    	for(int i = 0; i < 44; i++) {
    		System.out.println(i+" : " +hercules[i]);
    	}
    	//g.drawLine(herculesLocations.get(0), herculesLocations.get(1), herculesLocations.get(2), herculesLocations.get(3));
    	g.drawLine(hercules[0], hercules[1], hercules[2], hercules[3]);
    	g.drawLine(hercules[0], hercules[1], hercules[10], hercules[11]);
    	g.drawLine(hercules[2], hercules[3], hercules[10], hercules[11]);
    	g.drawLine(hercules[2], hercules[3], hercules[4], hercules[5]);
    	g.drawLine(hercules[4], hercules[5], hercules[8], hercules[9]);
    	g.drawLine(hercules[8], hercules[9], hercules[6], hercules[7]);
    }
    
    public ArrayList<Integer> createList(){
    	ArrayList<Integer> test = new ArrayList<>();
    	starLabels = new ArrayList<>();
    	constLabels = new ArrayList<>();
    	planetLabels = new ArrayList<>();
    	mesrLabels = new ArrayList<>();
    	herculesLocations = new ArrayList<>();
    	
    	int a = 0;
    	int b = 1;
    	int c = 2;
    	int d = 3;
    	
    	int size;
    	
    	
    	herculesNames.sort(null);
    	System.out.println("Size: "+herculesNames.size());
    	System.out.println(herculesNames);
    	for(int i = 0; i < AlexxWork2.spaceObjList.size(); i++) {
    		/*
    		if(AlexxWork2.spaceObjList.get(i).getType() == "MESR") {
    			System.out.println(AlexxWork2.spaceObjList.get(i).getProperName());
    			System.out.println(AlexxWork2.spaceObjList.get(i).getMagnitude());
    			System.out.println(AlexxWork2.spaceObjList.get(i).getAltitude());
    		}
    		if(AlexxWork2.spaceObjList.get(i).getType() == "PLAN") {
    			System.out.println(AlexxWork2.spaceObjList.get(i).getProperName());
    			System.out.println(AlexxWork2.spaceObjList.get(i).getAltitude());
    		}
    		
    		if(AlexxWork2.spaceObjList.get(i).getType() == "CONST") {
    			System.out.println(AlexxWork2.spaceObjList.get(i).getConstName());
    			System.out.println(AlexxWork2.spaceObjList.get(i).getMagnitude());
    			System.out.println(AlexxWork2.spaceObjList.get(i).getAltitude());
    		}
    		*/
    		if(AlexxWork2.spaceObjList.get(i).getMagnitude() != null 
    				&& (Double.valueOf(AlexxWork2.spaceObjList.get(i).getMagnitude()) <= 6.0) 
    				&& AlexxWork2.spaceObjList.get(i).getAltitude() > 1) {
	    		
    			int x = getX(2250, 2250, 1000, 
    					(int)AlexxWork2.spaceObjList.get(i).getAzimuth(), 
    					(int)AlexxWork2.spaceObjList.get(i).getAltitude());
    			
				int y = getY(2250, 2250, 1000, 
						(int)AlexxWork2.spaceObjList.get(i).getAzimuth(), 
						(int)AlexxWork2.spaceObjList.get(i).getAltitude());
				
				if(AlexxWork2.starNamesCB) {
					if(AlexxWork2.spaceObjList.get(i).getType() == "STAR" 
							&& AlexxWork2.spaceObjList.get(i).getProperName() != null 
							&& AlexxWork2.spaceObjList.get(i).getProperName() != "") {
					
						if(herculesNames.contains(AlexxWork2.spaceObjList.get(i).getProperName())) {
							String name = AlexxWork2.spaceObjList.get(i).getProperName();
							//System.out.println("Hercules Contains: "+AlexxWork2.spaceObjList.get(i).getProperName());
							//loadHerculesLocation(name, x, y);
					    	System.out.println("Name:"+ name);
					    	System.out.println("X: "+ x);
					    	System.out.println("Y: "+ y);
					    	if(name.contains("Rasalgethi")) {
					    		hercules[0] = x;
					    		hercules[1] = y;
					    		System.out.println("-----------X: "+ hercules[0]);
						    	System.out.println("-----------Y: "+ hercules[1]);
					    		
					    	}
					    	if(name.contains(" 27Bet Her")) {
					    		hercules[2] = x;
					    		hercules[3] = y;
					    		
					    	}
					    	if(name.contains(" 20Gam Her")) {
					    		hercules[4] = x;
					    		hercules[5] = y;
					    		
					    	}
					    	if(name.contains(" 24Ome Her")) {
					    		hercules[6] = x;
					    		hercules[7] = y;
					    		
					    	}
					    	if(name.contains("  7Kap Her")) {
					    		hercules[8] = x;
					    		hercules[9] = y;
					    		
					    	}
					    	if(name.contains(" 65Del Her")) {
					    		hercules[10] = x;
					    		hercules[11] = y;
					    		
					    	}
					    	if(name.contains(" 76Lam Her")) {
					    		hercules[12] = x;
					    		hercules[13] = y;
					    		
					    	}
					    	if(name.contains(" 86Mu  Her")) {
					    		hercules[14] = x;
					    		hercules[15] = y;
					    		
					    	}
					    	if(name.contains(" 40Zet Her")) {
					    		hercules[16] = x;
					    		hercules[17] = y;
					    		
					    	}
					    	if(name.contains("103Omi Her")) {
					    		hercules[18] = x;
					    		hercules[19] = y;
					    		
					    	}
					    	if(name.contains("102    Her")) {
					    		hercules[20] = x;
					    		hercules[21] = y;
					    		
					    	}
					    	if(name.contains(" 92Xi  Her")) {
					    		hercules[22] = x;
					    		hercules[23] = y;
					    		
					    	}
					    	if(name.contains(" 58Eps Her")) {
					    		hercules[24] = x;
					    		hercules[25] = y;
					    		
					    	}
					    	if(name.contains(" 67Pi  Her")) {
					    		hercules[26] = x;
					    		hercules[27] = y;
					    		
					    	}
					    	if(name.contains(" 75Rho Her")) {
					    		hercules[28] = x;
					    		hercules[29] = y;
					    		
					    	}
					    	if(name.contains(" 91The Her")) {
					    		hercules[30] = x;
					    		hercules[31] = y;
					    		
					    	}
					    	if(name.contains(" 85Iot Her")) {
					    		hercules[32] = x;
					    		hercules[33] = y;
					    		
					    	}
					    	if(name.contains(" 44Eta Her")) {
					    		hercules[34] = x;
					    		hercules[35] = y;
					    		
					    	}
					    	if(name.contains(" 35Sig Her")) {
					    		hercules[36] = x;
					    		hercules[37] = y;
					    		
					    	}
					    	if(name.contains(" 22Tau Her")) {
					    		hercules[38] = x;
					    		hercules[39] = y;
					    		
					    	}
					    	if(name.contains(" 11Phi Her")) {
					    		hercules[40] = x;
					    		hercules[41] = y;
					    		
					    	}
					    	if(name.contains("  1Chi Her")) {
					    		hercules[42] = x;
					    		hercules[43] = y;
					    		
					    	}
							
						}
						
							
							
							
							try {
								int testInt = Integer.parseInt(AlexxWork2.spaceObjList.get(i).getProperName());
								} catch (NumberFormatException | NullPointerException nfe) {
									starLabels.add(AlexxWork2.spaceObjList.get(i).getProperName());
									starLabels.add(String.valueOf(x));
									starLabels.add(String.valueOf(y));
						
								}
					}
				}				
	    		test.add(a, x);
	    		test.add(b, y);
	    		if(AlexxWork2.spaceObjList.get(i).getType() == "MESR") {
	    			size = 25;
	    			System.out.println(AlexxWork2.spaceObjList.get(i).getType());
	    		}
	    		else {
	    			size = getSize(Double.valueOf(AlexxWork2.spaceObjList.get(i).getMagnitude()));
	    		}
	    		
	    		test.add(c, size);
	    		test.add(d, size);
	    		a = d + 1;
	    		b = a + 1;
	    		c = b + 1;
	    		d = c + 1;
    		}
    		
    		if(AlexxWork2.constellationsCB) {
				if(AlexxWork2.spaceObjList.get(i).getType() == "CONST" 
						&& AlexxWork2.spaceObjList.get(i).getConstName() != null 
						&& AlexxWork2.spaceObjList.get(i).getConstName() != ""
						&& AlexxWork2.spaceObjList.get(i).getAltitude() > 1) {
					int x = getX(2250, 2250, 1000, (int)AlexxWork2.spaceObjList.get(i).getAzimuth(), (int)AlexxWork2.spaceObjList.get(i).getAltitude());
					int y = getY(2250, 2250, 1000, (int)AlexxWork2.spaceObjList.get(i).getAzimuth(), (int)AlexxWork2.spaceObjList.get(i).getAltitude());
					
					//System.out.println("X: "+ x);
					//System.out.println("Y: "+ y);
					
					constLabels.add(AlexxWork2.spaceObjList.get(i).getConstName());
					constLabels.add(String.valueOf(x));
					constLabels.add(String.valueOf(y));
				
				}
			}
    		
    	}
    	for(int i = 0; i < 44; i++) {
    		System.out.println("In Create List: "+hercules[i]);
    		herculesLocations.add(hercules[i]);
    	}
    	//System.out.println(herculesNames);
    	return test;
    }
    
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
    
   
    public void loadHerculesLocation(String name, int x, int y) {
    	System.out.println("Name: "+ name);
    	System.out.println("X: "+ x);
    	System.out.println("Y: "+ y);
    	if(name == "Rasalgethi") {
    		hercules[0] = x;
    		hercules[1] = y;
    		
    	}
    	if(name == " 27Bet Her") {
    		hercules[2] = x;
    		hercules[3] = y;
    		
    	}
    	if(name == " 20Gam Her") {
    		hercules[4] = x;
    		hercules[5] = y;
    		
    	}
    	if(name == " 24Ome Her") {
    		hercules[6] = x;
    		hercules[7] = y;
    		
    	}
    	if(name == "  7Kap Her") {
    		hercules[8] = x;
    		hercules[9] = y;
    		
    	}
    	if(name == " 65Del Her") {
    		hercules[10] = x;
    		hercules[11] = y;
    		
    	}
    	if(name == " 76Lam Her") {
    		hercules[12] = x;
    		hercules[13] = y;
    		
    	}
    	if(name == " 86Mu  Her") {
    		hercules[14] = x;
    		hercules[15] = y;
    		
    	}
    	if(name == " 40Zet Her") {
    		hercules[16] = x;
    		hercules[17] = y;
    		
    	}
    	if(name == "103Omi Her") {
    		hercules[18] = x;
    		hercules[19] = y;
    		
    	}
    	if(name == "102    Her") {
    		hercules[20] = x;
    		hercules[21] = y;
    		
    	}
    	if(name == " 92Xi  Her") {
    		hercules[22] = x;
    		hercules[23] = y;
    		
    	}
    	if(name == " 58Eps Her") {
    		hercules[24] = x;
    		hercules[25] = y;
    		
    	}
    	if(name == " 67Pi  Her") {
    		hercules[26] = x;
    		hercules[27] = y;
    		
    	}
    	if(name == " 75Rho Her") {
    		hercules[28] = x;
    		hercules[29] = y;
    		
    	}
    	if(name == " 91The Her") {
    		hercules[30] = x;
    		hercules[31] = y;
    		
    	}
    	if(name == " 85Iot Her") {
    		hercules[32] = x;
    		hercules[33] = y;
    		
    	}
    	if(name == " 44Eta Her") {
    		hercules[34] = x;
    		hercules[35] = y;
    		
    	}
    	if(name == " 35Sig Her") {
    		hercules[36] = x;
    		hercules[37] = y;
    		
    	}
    	if(name == " 22Tau Her") {
    		hercules[38] = x;
    		hercules[39] = y;
    		
    	}
    	if(name == " 11Phi Her") {
    		hercules[40] = x;
    		hercules[41] = y;
    		
    	}
    	if(name == "  1Chi Her") {
    		hercules[42] = x;
    		hercules[43] = y;
    		
    	}
    	
    }
    
    public void loadConstNames() {
    	loadHerculesNames();
 
    }
    public void loadHerculesNames() {
    	
    	herculesNames = new ArrayList<>();
    	herculesNames.add("Rasalgethi");
    	herculesNames.add(" 27Bet Her");
    	herculesNames.add(" 20Gam Her");
    	herculesNames.add(" 24Ome Her");
    	herculesNames.add("  7Kap Her");
    	herculesNames.add(" 65Del Her");
    	herculesNames.add(" 76Lam Her");
    	herculesNames.add(" 86Mu  Her");
    	herculesNames.add(" 40Zet Her");
    	herculesNames.add("103Omi Her");
    	herculesNames.add("102    Her");
    	herculesNames.add(" 92Xi  Her");
    	herculesNames.add(" 58Eps Her");
    	herculesNames.add(" 67Pi  Her");
    	herculesNames.add(" 75Rho Her");
    	herculesNames.add(" 91The Her");
    	herculesNames.add(" 85Iot Her");
    	herculesNames.add(" 44Eta Her");
    	herculesNames.add(" 35Sig Her");
    	herculesNames.add(" 22Tau Her");
    	herculesNames.add(" 11Phi Her");
    	herculesNames.add("  1Chi Her");	
    }
  
    public static int getRandom(int min, int max){
        int x = (int) ((Math.random()*((max-min)+1))+min);
        return x;
    }

}