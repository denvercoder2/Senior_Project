

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
	int[] ursaMinor = new int[16];
	ArrayList<String> ursaMinorNames;
	int[] ursaMajor = new int[16];
	ArrayList<String> ursaMajorNames;
	int[] libra = new int[12];
	ArrayList<String> libraNames;
	int[] andromeda = new int[1];
	ArrayList<String> andromedaNames;
	int[] aquarius = new int[1];
	ArrayList<String> aquariusNames;
	int[] aquila = new int[1];
	ArrayList<String> aquilaNames;
	int[] aries = new int[1];
	ArrayList<String> ariesNames;
	int[] auriga = new int[1];
	ArrayList<String> aurigaNames;
	int[] bootes = new int[1];
	ArrayList<String> bootesNames;
	int[] cancer = new int[1];
	ArrayList<String> cancerNames;
	int[] canisMajor = new int[1];
	ArrayList<String> canisMajorNames;
	int[] canisMinor = new int[1];
	ArrayList<String> canisMinorNames;
	int[] capricornus = new int[1];
	ArrayList<String> capricornusNames;
	int[] cassiopeia = new int[1];
	ArrayList<String> cassiopeiaNames;
	int[] centaurus = new int[1];
	ArrayList<String> centaurusNames;
	int[] cepheus = new int[1];
	ArrayList<String> cepheusNames;
	int[] crux = new int[1];
	ArrayList<String> cruxNames;
	int[] cygnus = new int[1];
	ArrayList<String> cygnusNames;
	int[] draco = new int[1];
	ArrayList<String> dracoNames;
	int[] gemini = new int[1];
	ArrayList<String> geminiNames;
	int[] hydra = new int[1];
	ArrayList<String> hydraNames;
	int[] leo = new int[1];
	ArrayList<String> leoNames;
	int[] lyra = new int[1];
	ArrayList<String> lyraNames;
	int[] orion = new int[1];
	ArrayList<String> orionNames;
	int[] pegasus = new int[1];
	ArrayList<String> pegasusNames;
	int[] perseus = new int[1];
	ArrayList<String> perseusNames;
	int[] pisces = new int[1];
	ArrayList<String> piscesNames;
	int[] sagittarius = new int[1];
	ArrayList<String> sagittariusNames;
	int[] scorpio = new int[1];
	ArrayList<String> scorpioNames;
	int[] taurus= new int[1];
	ArrayList<String> taurusNames;
	
	
	
	
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
    		g.fillOval(list.get(a), list.get(b), list.get(c), list.get(d));
    		a = d + 1;
    		b = a + 1;
    		c = b + 1;
    		d = c + 1;
    	}
    	index = 0;
    	for(int i = 0; i < (constLabels.size()/3); i++) {
    		g.drawString(constLabels.get(index), Integer.valueOf(constLabels.get(index+1)), Integer.valueOf(constLabels.get(index+2)));
    		index +=3;
    	}
    	index = 0;
    	for(int i = 0; i < (starLabels.size()/3); i++) {
    		g.drawString(starLabels.get(index), Integer.valueOf(starLabels.get(index+1)), Integer.valueOf(starLabels.get(index+2)));
    		index +=3;
    	}
    	if(checkUrsaMajor()) {
    		g.drawLine(ursaMajor[0], ursaMajor[1], ursaMajor[2], ursaMajor[3]);
        	g.drawLine(ursaMajor[2], ursaMajor[3], ursaMajor[4], ursaMajor[5]);
        	g.drawLine(ursaMajor[4], ursaMajor[5], ursaMajor[6], ursaMajor[7]);
        	g.drawLine(ursaMajor[6], ursaMajor[7], ursaMajor[8], ursaMajor[9]);
        	g.drawLine(ursaMajor[8], ursaMajor[9], ursaMajor[10], ursaMajor[11]);
        	g.drawLine(ursaMajor[10], ursaMajor[11], ursaMajor[12], ursaMajor[13]);
        	g.drawLine(ursaMajor[12], ursaMajor[13], ursaMajor[14], ursaMajor[15]);
        	g.drawLine(ursaMajor[14], ursaMajor[15], ursaMajor[8], ursaMajor[9]);
    	}
    	if(checkUrsaMinor()) {
    		g.drawLine(ursaMinor[0], ursaMinor[1], ursaMinor[2], ursaMinor[3]);
        	g.drawLine(ursaMinor[2], ursaMinor[3], ursaMinor[4], ursaMinor[5]);
        	g.drawLine(ursaMinor[4], ursaMinor[5], ursaMinor[6], ursaMinor[7]);
        	g.drawLine(ursaMinor[6], ursaMinor[7], ursaMinor[8], ursaMinor[9]);
        	g.drawLine(ursaMinor[8], ursaMinor[9], ursaMinor[10], ursaMinor[11]);
        	g.drawLine(ursaMinor[10], ursaMinor[11], ursaMinor[12], ursaMinor[13]);
        	g.drawLine(ursaMinor[12], ursaMinor[13], ursaMinor[14], ursaMinor[15]);
        	g.drawLine(ursaMinor[14], ursaMinor[15], ursaMinor[6], ursaMinor[7]);
    	}
    	
    	if(checkLibra()) {
    		g.drawLine(libra[0], libra[1], libra[2], libra[3]);
    		g.drawLine(libra[2], libra[3], libra[4], libra[5]);
    		g.drawLine(libra[4], libra[5], libra[6], libra[7]);
    		g.drawLine(libra[6], libra[7], libra[8], libra[9]);
    		g.drawLine(libra[8], libra[9], libra[10], libra[11]);
    		g.drawLine(libra[4], libra[5], libra[8], libra[9]);
    	}
    	
    	if(checkHercules()) {
	    	g.drawLine(hercules[0], hercules[1], hercules[2], hercules[3]);
	    	g.drawLine(hercules[0], hercules[1], hercules[10], hercules[11]);
	    	g.drawLine(hercules[2], hercules[3], hercules[10], hercules[11]);
	    	g.drawLine(hercules[2], hercules[3], hercules[4], hercules[5]);
	    	g.drawLine(hercules[4], hercules[5], hercules[8], hercules[9]);
	    	g.drawLine(hercules[8], hercules[9], hercules[6], hercules[7]);
	    	g.drawLine(hercules[10], hercules[11], hercules[12], hercules[13]);
	    	g.drawLine(hercules[12], hercules[13], hercules[14], hercules[15]);
	    	g.drawLine(hercules[14], hercules[15], hercules[22], hercules[23]);
	    	g.drawLine(hercules[22], hercules[23], hercules[18], hercules[19]);
	    	g.drawLine(hercules[18], hercules[19], hercules[20], hercules[21]);
	    	g.drawLine(hercules[2], hercules[3], hercules[16], hercules[17]);
	    	g.drawLine(hercules[10], hercules[11], hercules[24], hercules[25]);
	    	g.drawLine(hercules[16], hercules[17], hercules[34], hercules[35]);
	    	g.drawLine(hercules[24], hercules[25], hercules[26], hercules[27]);
	    	g.drawLine(hercules[16], hercules[17], hercules[24], hercules[25]);
	    	g.drawLine(hercules[34], hercules[35], hercules[26], hercules[27]);
	    	g.drawLine(hercules[34], hercules[35], hercules[36], hercules[37]);
	    	g.drawLine(hercules[36], hercules[37], hercules[38], hercules[39]);
	    	g.drawLine(hercules[38], hercules[39], hercules[40], hercules[41]);
	    	g.drawLine(hercules[40], hercules[41], hercules[42], hercules[43]);
	    	g.drawLine(hercules[26], hercules[27], hercules[28], hercules[29]);
	    	g.drawLine(hercules[28], hercules[29], hercules[30], hercules[31]);
	    	g.drawLine(hercules[30], hercules[31], hercules[32], hercules[33]);
    	}
    	
    	
    	
    	
    }
    
    public ArrayList<Integer> createList(){
    	ArrayList<Integer> test = new ArrayList<>();
    	starLabels = new ArrayList<>();
    	constLabels = new ArrayList<>();
    	planetLabels = new ArrayList<>();
    	mesrLabels = new ArrayList<>();
    	
    	
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
    		*/
    		if(AlexxWork2.spaceObjList.get(i).getType() == "PLAN") {
    			System.out.println(AlexxWork2.spaceObjList.get(i).getProperName());
    			System.out.println(AlexxWork2.spaceObjList.get(i).getAltitude());
    		}
    		/*
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
				
				
				if(AlexxWork2.spaceObjList.get(i).getType() == "STAR" 
						&& AlexxWork2.spaceObjList.get(i).getProperName() != null 
						&& AlexxWork2.spaceObjList.get(i).getProperName() != "") {
					
					if(AlexxWork2.starNamesCB 
							&& Double.valueOf(AlexxWork2.spaceObjList.get(i).getMagnitude()) <= 6.0) {
						try {
							int testInt = Integer.parseInt(AlexxWork2.spaceObjList.get(i).getProperName());
							} catch (NumberFormatException | NullPointerException nfe) {
								//System.out.println(AlexxWork2.spaceObjList.get(i).getProperName());
								starLabels.add(AlexxWork2.spaceObjList.get(i).getProperName());
								starLabels.add(String.valueOf(x));
								starLabels.add(String.valueOf(y));
							}
					}
					
					if(herculesNames.contains(AlexxWork2.spaceObjList.get(i).getProperName())) {
						String name = AlexxWork2.spaceObjList.get(i).getProperName();
						loadHerculesLocation(name, x, y);
					}
					if(ursaMinorNames.contains(AlexxWork2.spaceObjList.get(i).getProperName())) {
						String name = AlexxWork2.spaceObjList.get(i).getProperName();
						loadUrsaMinorLocation(name, x, y);
					}
					if(ursaMajorNames.contains(AlexxWork2.spaceObjList.get(i).getProperName())) {
						String name = AlexxWork2.spaceObjList.get(i).getProperName();
						loadUrsaMajorLocation(name, x, y);
					}
					if(libraNames.contains(AlexxWork2.spaceObjList.get(i).getProperName())) {
						String name = AlexxWork2.spaceObjList.get(i).getProperName();
						loadLibraLocation(name, x, y);
					}
						
				}				
	    		test.add(a, x);
	    		test.add(b, y);
	    		if(AlexxWork2.spaceObjList.get(i).getType() == "MESR") {
	    			size = 25;
	    			//System.out.println(AlexxWork2.spaceObjList.get(i).getType());
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
						&& AlexxWork2.spaceObjList.get(i).getConstName() != "") {
						//&& AlexxWork2.spaceObjList.get(i).getAltitude() > 0) {
					int x = getX(2250, 2250, 1000, (int)AlexxWork2.spaceObjList.get(i).getAzimuth(), (int)AlexxWork2.spaceObjList.get(i).getAltitude());
					int y = getY(2250, 2250, 1000, (int)AlexxWork2.spaceObjList.get(i).getAzimuth(), (int)AlexxWork2.spaceObjList.get(i).getAltitude());
					
					System.out.println("Const: "+AlexxWork2.spaceObjList.get(i).getConstName());
					//System.out.println("Y: "+ y);
					
					//constLabels.add(AlexxWork2.spaceObjList.get(i).getConstName());
					//constLabels.add(String.valueOf(x));
					//constLabels.add(String.valueOf(y));
				
				}
			}
    		
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
    
    public void loadUrsaMinorLocation(String name, int x, int y) {
    	if(name.contains("Polaris")) {
    		ursaMinor[0] = x;
    		ursaMinor[1] = y;
    	}
    	if(name.contains(" 23Del UMi")) {
    		ursaMinor[2] = x;
    		ursaMinor[3] = y;
    	}
    	if(name.contains(" 22Eps UMi")) {
    		ursaMinor[4] = x;
    		ursaMinor[5] = y;
    	}
    	if(name.contains(" 16Zet UMi")) {
    		ursaMinor[6] = x;
    		ursaMinor[7] = y;
    	}
    	if(name.contains(" 15The UMi")) {
    		ursaMinor[8] = x;
    		ursaMinor[9] = y;
    	}
    	if(name.contains("Kochab")) {
    		ursaMinor[10] = x;
    		ursaMinor[11] = y;
    	}
    	if(name.contains(" 13Gam UMi")) {
    		ursaMinor[12] = x;
    		ursaMinor[13] = y;
    	}
    	if(name.contains(" 21Eta UMi")) {
    		ursaMinor[14] = x;
    		ursaMinor[15] = y;
    	}
    }
    
    public void loadUrsaMajorLocation(String name, int x, int y) {
    	if(name.contains("Alkaid")) {
    		ursaMajor[0] = x;
    		ursaMajor[1] = y;
    	}
    	if(name.contains(" 80    UMa")) {
    		ursaMajor[2] = x;
    		ursaMajor[3] = y;
    	}
    	if(name.contains("Mizar")) {
    		ursaMajor[4] = x;
    		ursaMajor[5] = y;
    	}
    	if(name.contains("Alioth")) {
    		ursaMajor[6] = x;
    		ursaMajor[7] = y;
    	}
    	if(name.contains(" 69Del UMa")) {
    		ursaMajor[8] = x;
    		ursaMajor[9] = y;
    	}
    	if(name.contains("Phad")) {
    		ursaMajor[10] = x;
    		ursaMajor[11] = y;
    	}
    	if(name.contains("Merak")) {
    		ursaMajor[12] = x;
    		ursaMajor[13] = y;
    	}
    	if(name.contains("Dubhe")) {
    		ursaMajor[14] = x;
    		ursaMajor[15] = y;
    	}
    	
    }
    
    public void loadLibraLocation(String name, int x, int y) {
    	if(name.contains(" 46The Lib")) {
    		libra[0] = x;
    		libra[1] = y;
    	}
    	if(name.contains(" 38Gam Lib")) {
    		libra[2] = x;
    		libra[3] = y;
    	}
    	if(name.contains(" 27Bet Lib")) {
    		libra[4] = x;
    		libra[5] = y;
    	}
    	if(name.contains("  8Alp1Lib")) {
    		libra[6] = x;
    		libra[7] = y;
    	}
    	if(name.contains(" 20Sig Lib")) {
    		libra[8] = x;
    		libra[9] = y;
    	}
    	if(name.contains(" 39Ups Lib")) {
    		libra[10] = x;
    		libra[11] = y;
    	}
    }
    
    public void loadUrsaMajorNames() {
    	ursaMajorNames = new ArrayList<>();
    	ursaMajorNames.add("Alkaid");
    	ursaMajorNames.add(" 80    UMa");
    	ursaMajorNames.add("Mizar");
    	ursaMajorNames.add("Alioth");
    	ursaMajorNames.add(" 69Del UMa");
    	ursaMajorNames.add("Phad");
    	ursaMajorNames.add("Merak");
    	ursaMajorNames.add("Dubhe");
    	
    }
    
    
    public void loadHerculesLocation(String name, int x, int y) {
    	//System.out.println("Name: "+ name);
    	//System.out.println("X: "+ x);
    	//System.out.println("Y: "+ y);
    	if(name.contains("Rasalgethi")) {
    		hercules[0] = x;
    		hercules[1] = y;
    		//System.out.println("-----------X: "+ hercules[0]);
	    	//System.out.println("-----------Y: "+ hercules[1]);
    		
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
    
    public void loadConstNames() {
    	loadHerculesNames();
    	loadUrsaMinorNames();
    	loadUrsaMajorNames();
    	loadLibraNames();
    }
    
    public void loadLibraNames() {
    	libraNames = new ArrayList<>();
    	libraNames.add(" 46The Lib");
    	libraNames.add(" 38Gam Lib");
    	libraNames.add(" 27Bet Lib");
    	libraNames.add("  8Alp1Lib");
    	libraNames.add(" 20Sig Lib");
    	libraNames.add(" 39Ups Lib");
    }
    
    public void loadUrsaMinorNames() {
    	ursaMinorNames = new ArrayList<>();
    	ursaMinorNames.add("Polaris");
    	ursaMinorNames.add(" 23Del UMi");
    	ursaMinorNames.add(" 22Eps UMi");
    	ursaMinorNames.add(" 16Zet UMi");
    	ursaMinorNames.add(" 15The UMi");
    	ursaMinorNames.add("Kochab");
    	ursaMinorNames.add(" 13Gam UMi");
    	ursaMinorNames.add(" 21Eta UMi");
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
  
    public Boolean checkHercules() {
    	Boolean flag = true;
    	for(int i = 0; i < 44; i++) {
    		if(hercules[i] == 0) {
    			flag = false;
    		}
    	}
    	return flag;
    }
    public Boolean checkUrsaMinor() {
    	Boolean flag = true;
    	for(int i = 0; i < 16; i++) {
    		if(ursaMinor[i] == 0) {
    			flag = false;
    		}
    	}
    	return flag;
    }
    public Boolean checkUrsaMajor() {
    	Boolean flag = true;
    	for(int i = 0; i < 16; i++) {
    		if(ursaMajor[i] == 0) {
    			flag = false;
    		}
    	}
    	return flag;
    }
    public Boolean checkLibra() {
    	Boolean flag = true;
    	for(int i = 0; i < 12; i++) {
    		if(libra[i] == 0) {
    			flag = false;
    		}
    	}
    	return flag;
    }
    
    public static int getRandom(int min, int max){
        int x = (int) ((Math.random()*((max-min)+1))+min);
        return x;
    }

}