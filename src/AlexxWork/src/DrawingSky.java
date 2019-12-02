

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
	ArrayList<String> constNames;
	
	int[] hercules = new int[44];
	ArrayList<String> herculesNames;
	int[] ursaMinor = new int[16];
	ArrayList<String> ursaMinorNames;
	int[] ursaMajor = new int[16];
	ArrayList<String> ursaMajorNames;
	int[] libra = new int[12];
	ArrayList<String> libraNames;
	int[] andromeda = new int[30];
	ArrayList<String> andromedaNames;
	int[] aquarius = new int[1];
	ArrayList<String> aquariusNames;
	int[] aquila = new int[16];
	ArrayList<String> aquilaNames;
	int[] aries = new int[8];
	ArrayList<String> ariesNames;
	int[] auriga = new int[14];
	ArrayList<String> aurigaNames;
	int[] bootes = new int[1];
	ArrayList<String> bootesNames;
	int[] cancer = new int[10];
	ArrayList<String> cancerNames;
	int[] canisMajor = new int[20];
	ArrayList<String> canisMajorNames;
	int[] canisMinor = new int[4];
	ArrayList<String> canisMinorNames;
	int[] capricornus = new int[1];
	ArrayList<String> capricornusNames;
	int[] cassiopeia = new int[10];
	ArrayList<String> cassiopeiaNames;
	int[] centaurus = new int[1];
	ArrayList<String> centaurusNames;
	int[] cepheus = new int[18];
	ArrayList<String> cepheusNames;
	int[] crux = new int[8];
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
	int[] lyra = new int[12];
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
    	index = 0;
    	for(int i = 0; i < (planetLabels.size()/3); i++) {
    		g.drawString(planetLabels.get(index), Integer.valueOf(planetLabels.get(index+1)), Integer.valueOf(planetLabels.get(index+2)));
    		index +=3;
    	}
    	index = 0;
    	for(int i = 0; i < (mesrLabels.size()/3); i++) {
    		g.drawString(mesrLabels.get(index), Integer.valueOf(mesrLabels.get(index+1)), Integer.valueOf(mesrLabels.get(index+2)));
    		index +=3;
    	}
    	
    	if(checkCanisMajor()) {
    		g.drawLine(canisMajor[0], canisMajor[1], canisMajor[2], canisMajor[3]);
    		g.drawLine(canisMajor[2], canisMajor[3], canisMajor[4], canisMajor[5]);
    		g.drawLine(canisMajor[2], canisMajor[3], canisMajor[6], canisMajor[7]);
    		g.drawLine(canisMajor[6], canisMajor[7], canisMajor[8], canisMajor[9]);
    		g.drawLine(canisMajor[8], canisMajor[9], canisMajor[10], canisMajor[11]);
    		g.drawLine(canisMajor[10], canisMajor[11], canisMajor[12], canisMajor[13]);
    		g.drawLine(canisMajor[12], canisMajor[13], canisMajor[6], canisMajor[7]);
    		g.drawLine(canisMajor[6], canisMajor[7], canisMajor[14], canisMajor[15]);
    		g.drawLine(canisMajor[14], canisMajor[15], canisMajor[16], canisMajor[17]);
    		g.drawLine(canisMajor[16], canisMajor[17], canisMajor[18], canisMajor[19]);
    		g.drawLine(canisMajor[18], canisMajor[19], canisMajor[0], canisMajor[1]);
    	}
    	
    	if(checkAquila()) {
    		g.drawLine(aquila[0], aquila[1], aquila[2], aquila[3]);
    		g.drawLine(aquila[2], aquila[3], aquila[4], aquila[5]);
    		g.drawLine(aquila[4], aquila[5], aquila[0], aquila[1]);
    		g.drawLine(aquila[2], aquila[3], aquila[6], aquila[7]);
    		g.drawLine(aquila[6], aquila[7], aquila[14], aquila[15]);
    		g.drawLine(aquila[14], aquila[15], aquila[4], aquila[5]);
    		g.drawLine(aquila[4], aquila[5], aquila[8], aquila[9]);
    		g.drawLine(aquila[8], aquila[9], aquila[10], aquila[11]);
    		g.drawLine(aquila[10], aquila[11], aquila[12], aquila[13]);
    	}
    	
    	if(checkCepheus()) {
    		g.drawLine(cepheus[16], cepheus[17], cepheus[0], cepheus[1]);
    		g.drawLine(cepheus[2], cepheus[3], cepheus[0], cepheus[1]);
    		g.drawLine(cepheus[2], cepheus[3], cepheus[14], cepheus[15]);
    		g.drawLine(cepheus[2], cepheus[3], cepheus[4], cepheus[5]);
    		g.drawLine(cepheus[4], cepheus[5], cepheus[6], cepheus[7]);
    		g.drawLine(cepheus[6], cepheus[7], cepheus[8], cepheus[9]);
    		g.drawLine(cepheus[8], cepheus[9], cepheus[4], cepheus[5]);
    		g.drawLine(cepheus[8], cepheus[9], cepheus[10], cepheus[11]);
    		g.drawLine(cepheus[10], cepheus[11], cepheus[12], cepheus[13]);
    		g.drawLine(cepheus[12], cepheus[13], cepheus[14], cepheus[15]);
    	}
    	
    	if(checkAuriga()) {
    		g.drawLine(auriga[0], auriga[1], auriga[2], auriga[3]);
    		g.drawLine(auriga[2], auriga[3], auriga[4], auriga[5]);
    		g.drawLine(auriga[4], auriga[5], auriga[6], auriga[7]);
    		g.drawLine(auriga[2], auriga[3], auriga[8], auriga[9]);
    		g.drawLine(auriga[0], auriga[1], auriga[10], auriga[11]);
    		g.drawLine(auriga[10], auriga[11], auriga[12], auriga[13]);
    		g.drawLine(auriga[10], auriga[11], auriga[2], auriga[3]);
    	}
    	
    	if(checkCancer()) {
    		g.drawLine(cancer[0], cancer[1], cancer[2], cancer[3]);
    		g.drawLine(cancer[2], cancer[3], cancer[4], cancer[5]);
    		g.drawLine(cancer[4], cancer[5], cancer[6], cancer[7]);
    		g.drawLine(cancer[4], cancer[5], cancer[8], cancer[9]);
    	}
    	
    	if(checkAries()) {
    		g.drawLine(aries[0], aries[1], aries[2], aries[3]);
    		g.drawLine(aries[2], aries[3], aries[4], aries[5]);
    		g.drawLine(aries[4], aries[5], aries[6], aries[7]);
    	}
    	
    	if(checkCassiopeia()) {
    		g.drawLine(cassiopeia[0], cassiopeia[1], cassiopeia[2], cassiopeia[3]);
    		g.drawLine(cassiopeia[2], cassiopeia[3], cassiopeia[4], cassiopeia[5]);
    		g.drawLine(cassiopeia[4], cassiopeia[5], cassiopeia[6], cassiopeia[7]);
    		g.drawLine(cassiopeia[6], cassiopeia[7], cassiopeia[8], cassiopeia[9]);
    	}
    	
    	if(checkCrux()) {
    		g.drawLine(crux[0],crux[1], crux[4], crux[5]);
    		g.drawLine(crux[6],crux[7], crux[2], crux[3]);
    	}
    	
    	if(checkCanisMinor()) {
    		g.drawLine(canisMinor[0], canisMinor[1], canisMinor[2], canisMinor[3]);
    	}
    	
    	if(checkLyra()) {
    		g.drawLine(lyra[0], lyra[1], lyra[2], lyra[3]);
    		g.drawLine(lyra[2], lyra[3], lyra[4], lyra[5]);
    		g.drawLine(lyra[4], lyra[5], lyra[0], lyra[1]);
    		g.drawLine(lyra[4], lyra[5], lyra[6], lyra[7]);
    		g.drawLine(lyra[6], lyra[7], lyra[8], lyra[9]);
    		g.drawLine(lyra[4], lyra[5], lyra[10], lyra[11]);
    		g.drawLine(lyra[8], lyra[9], lyra[10], lyra[11]);
    		
    	}
    	
    	if(checkAndromeda()) {
    		g.drawLine(andromeda[0], andromeda[1], andromeda[2], andromeda[3]);
    		g.drawLine(andromeda[4], andromeda[5], andromeda[6], andromeda[7]);
    		g.drawLine(andromeda[2], andromeda[3], andromeda[10], andromeda[11]);
    		g.drawLine(andromeda[26], andromeda[27], andromeda[28], andromeda[29]);
    		g.drawLine(andromeda[2], andromeda[3], andromeda[4], andromeda[5]);
    		g.drawLine(andromeda[6], andromeda[7], andromeda[8], andromeda[9]);
    		g.drawLine(andromeda[10], andromeda[11], andromeda[12], andromeda[13]);
    		g.drawLine(andromeda[22], andromeda[23], andromeda[26], andromeda[27]);
    		g.drawLine(andromeda[12], andromeda[13], andromeda[14], andromeda[15]);
    		g.drawLine(andromeda[14], andromeda[15], andromeda[16], andromeda[17]);
    		g.drawLine(andromeda[12], andromeda[13], andromeda[18], andromeda[19]);
    		g.drawLine(andromeda[2], andromeda[3], andromeda[20], andromeda[21]);
    		g.drawLine(andromeda[20], andromeda[21], andromeda[22], andromeda[23]);
    		g.drawLine(andromeda[22], andromeda[23], andromeda[24], andromeda[25]);
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
    	
    	//herculesNames.sort(null);
    	//System.out.println("Size: "+herculesNames.size());
    	//System.out.println(herculesNames);
    	for(int i = 0; i < AlexxWork2.spaceObjList.size(); i++) {
    		if(AlexxWork2.spaceObjList.get(i).getType().contains("LUNA") || AlexxWork2.spaceObjList.get(i).getType().contains("Luna") || AlexxWork2.spaceObjList.get(i).getType().contains("Moon")||AlexxWork2.spaceObjList.get(i).getType().contains("MOON")) {
				System.out.println("Moon Found");
				System.out.println("Mag: "+AlexxWork2.spaceObjList.get(i).getMagnitude());
				System.out.println("Alt: "+ AlexxWork2.spaceObjList.get(i).getAltitude());
			}
    		if(canisMajorNames.contains(AlexxWork2.spaceObjList.get(i).getProperName())) {
    			System.out.println("Name: "+AlexxWork2.spaceObjList.get(i).getProperName());
    		}
    		if(AlexxWork2.spaceObjList.get(i).getProperName() != null && AlexxWork2.spaceObjList.get(i).getProperName() != "") {
    			if(AlexxWork2.spaceObjList.get(i).getProperName().contains(" CMa")||AlexxWork2.spaceObjList.get(i).getProperName().contains("CMa")
    					||AlexxWork2.spaceObjList.get(i).getProperName().contains("Sirius") || AlexxWork2.spaceObjList.get(i).getProperName().contains("Adhara")) {
        			System.out.println("Aur:"+AlexxWork2.spaceObjList.get(i).getProperName());
        		}
    		}
    		
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
							&& Double.valueOf(AlexxWork2.spaceObjList.get(i).getMagnitude()) <= 6.0
							&& lyraNames.contains(AlexxWork2.spaceObjList.get(i).getProperName())) {
						try {
							int testInt = Integer.parseInt(AlexxWork2.spaceObjList.get(i).getProperName());
							} catch (NumberFormatException | NullPointerException nfe) {
								//System.out.println("Andromeda: "+andromedaNames.contains(AlexxWork2.spaceObjList.get(i).getProperName()));
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
					if(andromedaNames.contains(AlexxWork2.spaceObjList.get(i).getProperName())) {
						loadAndromedaLocation(AlexxWork2.spaceObjList.get(i).getProperName(), x, y);
					}
					if(aquariusNames.contains(AlexxWork2.spaceObjList.get(i).getProperName())) {
						loadAquariusLocation(AlexxWork2.spaceObjList.get(i).getProperName(), x, y);
					}
					if(aquilaNames.contains(AlexxWork2.spaceObjList.get(i).getProperName())) {
						loadAquilaLocation(AlexxWork2.spaceObjList.get(i).getProperName(), x, y);
					}
					if(ariesNames.contains(AlexxWork2.spaceObjList.get(i).getProperName())) {
						loadAriesLocation(AlexxWork2.spaceObjList.get(i).getProperName(), x, y);
					}
					if(aurigaNames.contains(AlexxWork2.spaceObjList.get(i).getProperName())) {
						loadAurigaLocation(AlexxWork2.spaceObjList.get(i).getProperName(), x, y);
					}
					if(bootesNames.contains(AlexxWork2.spaceObjList.get(i).getProperName())) {
						loadBootesLocation(AlexxWork2.spaceObjList.get(i).getProperName(), x, y);
					}
					if(cancerNames.contains(AlexxWork2.spaceObjList.get(i).getProperName())) {
						loadCancerLocation(AlexxWork2.spaceObjList.get(i).getProperName(), x, y);
					}
					if(canisMajorNames.contains(AlexxWork2.spaceObjList.get(i).getProperName())) {
						loadCanisMajorLocation(AlexxWork2.spaceObjList.get(i).getProperName(), x, y);
					}
					if(canisMinorNames.contains(AlexxWork2.spaceObjList.get(i).getProperName())) {
						loadCanisMinorLocation(AlexxWork2.spaceObjList.get(i).getProperName(), x, y);
					}
					if(capricornusNames.contains(AlexxWork2.spaceObjList.get(i).getProperName())) {
						loadCapricornusLocation(AlexxWork2.spaceObjList.get(i).getProperName(), x, y);
					}
					if(cassiopeiaNames.contains(AlexxWork2.spaceObjList.get(i).getProperName())) {
						loadCassiopeiaLocation(AlexxWork2.spaceObjList.get(i).getProperName(), x, y);
					}
					if(centaurusNames.contains(AlexxWork2.spaceObjList.get(i).getProperName())) {
						loadCentaurusLocation(AlexxWork2.spaceObjList.get(i).getProperName(), x, y);
					}
					if(cepheusNames.contains(AlexxWork2.spaceObjList.get(i).getProperName())) {
						loadCepheusLocation(AlexxWork2.spaceObjList.get(i).getProperName(), x, y);
					}
					if(cruxNames.contains(AlexxWork2.spaceObjList.get(i).getProperName())) {
						loadCruxLocation(AlexxWork2.spaceObjList.get(i).getProperName(), x, y);
					}
					if(cygnusNames.contains(AlexxWork2.spaceObjList.get(i).getProperName())) {
						loadCygnusLocation(AlexxWork2.spaceObjList.get(i).getProperName(), x, y);
					}
					if(dracoNames.contains(AlexxWork2.spaceObjList.get(i).getProperName())) {
						loadDracoLocation(AlexxWork2.spaceObjList.get(i).getProperName(), x, y);
					}
					if(geminiNames.contains(AlexxWork2.spaceObjList.get(i).getProperName())) {
						loadGeminiLocation(AlexxWork2.spaceObjList.get(i).getProperName(), x, y);
					}
					if(hydraNames.contains(AlexxWork2.spaceObjList.get(i).getProperName())) {
						loadHydraLocation(AlexxWork2.spaceObjList.get(i).getProperName(), x, y);
					}
					if(leoNames.contains(AlexxWork2.spaceObjList.get(i).getProperName())) {
						loadLeoLocation(AlexxWork2.spaceObjList.get(i).getProperName(), x, y);
					}
					if(lyraNames.contains(AlexxWork2.spaceObjList.get(i).getProperName())) {
						loadLyraLocation(AlexxWork2.spaceObjList.get(i).getProperName(), x, y);
					}
					if(orionNames.contains(AlexxWork2.spaceObjList.get(i).getProperName())) {
						loadOrionLocation(AlexxWork2.spaceObjList.get(i).getProperName(), x, y);
					}
					if(pegasusNames.contains(AlexxWork2.spaceObjList.get(i).getProperName())) {
						loadPegasusLocation(AlexxWork2.spaceObjList.get(i).getProperName(), x, y);
					}
					if(perseusNames.contains(AlexxWork2.spaceObjList.get(i).getProperName())) {
						loadPerseusLocation(AlexxWork2.spaceObjList.get(i).getProperName(), x, y);
					}
					if(piscesNames.contains(AlexxWork2.spaceObjList.get(i).getProperName())) {
						loadPiscesLocation(AlexxWork2.spaceObjList.get(i).getProperName(), x, y);
					}
					if(sagittariusNames.contains(AlexxWork2.spaceObjList.get(i).getProperName())) {
						loadSagittariusLocation(AlexxWork2.spaceObjList.get(i).getProperName(), x, y);
					}
					if(scorpioNames.contains(AlexxWork2.spaceObjList.get(i).getProperName())) {
						loadScorpioLocation(AlexxWork2.spaceObjList.get(i).getProperName(), x, y);
					}
					if(taurusNames.contains(AlexxWork2.spaceObjList.get(i).getProperName())) {
						loadTaurusLocation(AlexxWork2.spaceObjList.get(i).getProperName(), x, y);
					}
					
				}				
	    		test.add(a, x);
	    		test.add(b, y);
	    		
	    		if(AlexxWork2.spaceObjList.get(i).getType() == "PLAN"){
	    			size = 16;
	    		}
	    		else{
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
										
					constLabels.add(AlexxWork2.spaceObjList.get(i).getConstName());
					constLabels.add(String.valueOf(x));
					constLabels.add(String.valueOf(y));
				}
			}
    		if(AlexxWork2.planetsCB) {
				if(AlexxWork2.spaceObjList.get(i).getType() == "PLAN" 
						&& AlexxWork2.spaceObjList.get(i).getProperName() != null 
						&& AlexxWork2.spaceObjList.get(i).getProperName() != ""
						&& AlexxWork2.spaceObjList.get(i).getAltitude() > 1) {
					int x = getX(2250, 2250, 1000, (int)AlexxWork2.spaceObjList.get(i).getAzimuth(), (int)AlexxWork2.spaceObjList.get(i).getAltitude());
					int y = getY(2250, 2250, 1000, (int)AlexxWork2.spaceObjList.get(i).getAzimuth(), (int)AlexxWork2.spaceObjList.get(i).getAltitude());
										
					planetLabels.add(AlexxWork2.spaceObjList.get(i).getProperName());
					planetLabels.add(String.valueOf(x));
					planetLabels.add(String.valueOf(y));
				}
			}
    		if(AlexxWork2.messierCB) {
				if(AlexxWork2.spaceObjList.get(i).getType() == "MESR" 
						&& AlexxWork2.spaceObjList.get(i).getProperName() != null 
						&& AlexxWork2.spaceObjList.get(i).getProperName() != ""
						&& AlexxWork2.spaceObjList.get(i).getAltitude() > 1) {
					int x = getX(2250, 2250, 1000, (int)AlexxWork2.spaceObjList.get(i).getAzimuth(), (int)AlexxWork2.spaceObjList.get(i).getAltitude());
					int y = getY(2250, 2250, 1000, (int)AlexxWork2.spaceObjList.get(i).getAzimuth(), (int)AlexxWork2.spaceObjList.get(i).getAltitude());
										
					mesrLabels.add(AlexxWork2.spaceObjList.get(i).getProperName());
					mesrLabels.add(String.valueOf(x));
					mesrLabels.add(String.valueOf(y));
				}
			}
    		
    	}
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
    
    public void loadAndromedaLocation(String name, int x, int y) {
    	if(name.contains(" 50Ups And")) {
    		andromeda[0] = x;
    		andromeda[1] = y;
    	}
    	if(name.contains("Mirach")) {
    		andromeda[2] = x;
    		andromeda[3] = y;
    	}
    	if(name.contains(" 37Mu  And")) {
    		andromeda[4] = x;
    		andromeda[5] = y;
    	}
    	if(name.contains(" 35Nu  And")) {
    		andromeda[6] = x;
    		andromeda[7] = y;
    	}
    	if(name.contains(" 42Phi And")) {
    		andromeda[8] = x;
    		andromeda[9] = y;
    	}
    	if(name.contains(" 29Pi  And")) {
    		andromeda[10] = x;
    		andromeda[11] = y;
    	}
    	if(name.contains(" 17Iot And")) {
    		andromeda[12] = x;
    		andromeda[13] = y;
    	}
    	if(name.contains(" 19Kap And")) {
    		andromeda[14] = x;
    		andromeda[15] = y;
    	}
    	if(name.contains(" 16Lam And")) {
    		andromeda[16] = x;
    		andromeda[17] = y;
    	}
    	if(name.contains("  1Omi And")) {
    		andromeda[18] = x;
    		andromeda[19] = y;
    	}
    	if(name.contains(" 31Del And")) {
    		andromeda[20] = x;
    		andromeda[21] = y;
    	}
    	if(name.contains(" 30Eps And")) {
    		andromeda[22] = x;
    		andromeda[23] = y;
    	}
    	if(name.contains("Alpheratz")) {
    		andromeda[24] = x;
    		andromeda[25] = y;
    	}
    	if(name.contains(" 34Zet And")) {
    		andromeda[26] = x;
    		andromeda[27] = y;
    	}
    	if(name.contains(" 38Eta And")) {
    		andromeda[28] = x;
    		andromeda[29] = y;
    	}
    	
    }
    
    public void loadAquariusLocation(String name, int x, int y) {
    	
    }
    public void loadAquilaLocation(String name, int x, int y) {
    	if(name.contains(" 17Zet Aql")) {
    		aquila[0] = x;
    		aquila[1] = y;
    	}
    	if(name.contains(" 16Lam Aql")) {
    		aquila[2] = x;
    		aquila[3] = y;
    	}
    	if(name.contains(" 30Del Aql")) {
    		aquila[4] = x;
    		aquila[5] = y;
    	}
    	if(name.contains(" 41Iot Aql")) {
    		aquila[6] = x;
    		aquila[7] = y;
    	}
    	if(name.contains("Tarazed")) {
    		aquila[8] = x;
    		aquila[9] = y;
    	}
    	if(name.contains("Altair")) {
    		aquila[10] = x;
    		aquila[11] = y;
    	}
    	if(name.contains(" 60Bet Aql")) {
    		aquila[12] = x;
    		aquila[13] = y;
    	}
    	if(name.contains(" 65The Aql")) {
    		aquila[14] = x;
    		aquila[15] = y;
    	}
    }
    
    public void loadAriesLocation(String name, int x, int y) {
    	if(name.contains(" 41    Ari")) {
    		aries[0] = x;
    		aries[1] = y;
    	}
    	if(name.contains("Hamal")) {
    		aries[2] = x;
    		aries[3] = y;
    	}
    	if(name.contains("  6Bet Ari")) {
    		aries[4] = x;
    		aries[5] = y;
    	}
    	if(name.contains("  5Gam2Ari")) {
    		aries[6] = x;
    		aries[7] = y;
    	}
    }
    public void loadAurigaLocation(String name, int x, int y) {
    	if(name.contains(" 33Del Aur")) {
    		auriga[0] = x;
    		auriga[1] = y;
    	}
    	if(name.contains("Capella")) {
    		auriga[2] = x;
    		auriga[3] = y;
    	}
    	if(name.contains("  7Eps Aur")) {
    		auriga[4] = x;
    		auriga[5] = y;
    	}
    	if(name.contains("  8Zet Aur")) {
    		auriga[6] = x;
    		auriga[7] = y;
    	}
    	if(name.contains("  3Iot Aur")) {
    		auriga[8] = x;
    		auriga[9] = y;
    	}
    	if(name.contains(" 34Bet Aur")) {
    		auriga[10] = x;
    		auriga[11] = y;
    	}
    	if(name.contains(" 37The Aur")) {
    		auriga[12] = x;
    		auriga[13] = y;
    	}
    	
    }
    
    public void loadBootesLocation(String name, int x, int y) {
    	
    }
    public void loadCancerLocation(String name, int x, int y) {
    	if(name.contains(" 48Iot Cnc")) {
    		cancer[0] = x;
    		cancer[1] = y;
    	}
    	if(name.contains(" 43Gam Cnc")) {
    		cancer[2] = x;
    		cancer[3] = y;
    	}
    	if(name.contains(" 47Del Cnc")) {
    		cancer[4] = x;
    		cancer[5] = y;
    	}
    	if(name.contains(" 17Bet Cnc")) {
    		cancer[6] = x;
    		cancer[7] = y;
    	}
    	if(name.contains(" 65Alp Cnc")) {
    		cancer[8] = x;
    		cancer[9] = y;
    	}
    }
    
    public void loadCanisMajorLocation(String name, int x, int y) {
    	if(name.contains("Adhara")) {
    		canisMajor[0] = x;
    		canisMajor[1] = y;
    	}
    	if(name.contains(" 25Del CMa")) {
    		canisMajor[2] = x;
    		canisMajor[3] = y;
    	}
    	if(name.contains(" 31Eta CMa")) {
    		canisMajor[4] = x;
    		canisMajor[5] = y;
    	}
    	if(name.contains("Sirius")) {
    		canisMajor[6] = x;
    		canisMajor[7] = y;
    	}
    	if(name.contains(" 20Iot CMa")) {
    		canisMajor[8] = x;
    		canisMajor[9] = y;
    	}
    	if(name.contains(" 23Gam CMa")) {
    		canisMajor[10] = x;
    		canisMajor[11] = y;
    	}
    	if(name.contains(" 14The CMa")) {
    		canisMajor[12] = x;
    		canisMajor[13] = y;
    	}
    	if(name.contains("  2Bet CMa")) {
    		canisMajor[14] = x;
    		canisMajor[15] = y;
    	}
    	if(name.contains("  7Nu 2CMa")) {
    		canisMajor[16] = x;
    		canisMajor[17] = y;
    	}
    	if(name.contains(" 16Omi1CMa")) {
    		canisMajor[18] = x;
    		canisMajor[19] = y;
    	}
    	
    }
    public void loadCanisMinorLocation(String name, int x, int y) {
    	if(name.contains("Procyon")) {
    		canisMinor[0] = x;
    		canisMinor[1] = y;
    	}
    	if(name.contains("  3Bet CMi")) {
    		canisMinor[2] = x;
    		canisMinor[3] = y;
    	}
    }
    
    public void loadCapricornusLocation(String name, int x, int y) {
    	
    }
    public void loadCassiopeiaLocation(String name, int x, int y) {
    	if(name.contains("Caph")) {
    		cassiopeia[0] = x;
    		cassiopeia[1] = y;
    	}
    	if(name.contains("Shedir")) {
    		cassiopeia[2] = x;
    		cassiopeia[3] = y;
    	}
    	if(name.contains(" 27Gam Cas")) {
    		cassiopeia[4] = x;
    		cassiopeia[5] = y;
    	}
    	if(name.contains(" 37Del Cas")) {
    		cassiopeia[6] = x;
    		cassiopeia[7] = y;
    	}
    	if(name.contains(" 45Eps Cas")) {
    		cassiopeia[8] = x;
    		cassiopeia[9] = y;
    	}
    	
    }
    
    public void loadCentaurusLocation(String name, int x, int y) {
    	
    }
    public void loadCepheusLocation(String name, int x, int y) {
    	if(name.contains("  3Eta Cep")) {
    		cepheus[0] = x;
    		cepheus[1] = y;
    	}
    	if(name.contains("Alderamin")) {
    		cepheus[2] = x;
    		cepheus[3] = y;
    	}
    	if(name.contains("  8Bet Cep")) {
    		cepheus[4] = x;
    		cepheus[5] = y;
    	}
    	if(name.contains(" 35Gam Cep")) {
    		cepheus[6] = x;
    		cepheus[7] = y;
    	}
    	if(name.contains(" 32Iot Cep")) {
    		cepheus[8] = x;
    		cepheus[9] = y;
    	}
    	if(name.contains(" 27Del Cep")) {
    		cepheus[10] = x;
    		cepheus[11] = y;
    	}
    	if(name.contains(" 21Zet Cep")) {
    		cepheus[12] = x;
    		cepheus[13] = y;
    	}
    	if(name.contains(" 23Eps Cep")) {
    		cepheus[14] = x;
    		cepheus[15] = y;
    	}
    	if(name.contains("  2The Cep")) {
    		cepheus[16] = x;
    		cepheus[17] = y;
    	}
    	
    }
    
    public void loadCruxLocation(String name, int x, int y) {
    	if(name.contains("Acrux")) {
    		crux[0] = x;
    		crux[1] = y;
    	}
    	if(name.contains("   Del Cru")) {
    		crux[2] = x;
    		crux[3] = y;
    	}
    	if(name.contains("   Gam Cru")) {
    		crux[4] = x;
    		crux[5] = y;
    	}
    	if(name.contains("   Bet Cru")) {
    		crux[6] = x;
    		crux[7] = y;
    	}
    }
    public void loadCygnusLocation(String name, int x, int y) {
    	
    }
    
    public void loadDracoLocation(String name, int x, int y) {
    	
    }
    public void loadGeminiLocation(String name, int x, int y) {
    	
    }
    
    public void loadHydraLocation(String name, int x, int y) {
    	
    }
    public void loadLeoLocation(String name, int x, int y) {
    	
    }
    
    public void loadLyraLocation(String name, int x, int y) {
    	if(name.contains("Vega")) {
    		lyra[0] = x;
    		lyra[1] = y;
    	}
    	if(name.contains("  4Eps1Lyr")) {
    		lyra[2] = x;
    		lyra[3] = y;
    	}
    	if(name.contains("  6Zet1Lyr")) {
    		lyra[4] = x;
    		lyra[5] = y;
    	}
    	if(name.contains(" 11Del1Lyr")) {
    		lyra[6] = x;
    		lyra[7] = y;
    	}
    	if(name.contains(" 14Gam Lyr")) {
    		lyra[8] = x;
    		lyra[9] = y;
    	}
    	if(name.contains(" 10Bet Lyr")) {
    		lyra[10] = x;
    		lyra[11] = y;
    	}
    }
    public void loadOrionLocation(String name, int x, int y) {
    	
    }
    
    public void loadPegasusLocation(String name, int x, int y) {
    	
    }
    public void loadPerseusLocation(String name, int x, int y) {
    	
    }
    public void loadPiscesLocation(String name, int x, int y) {
    	
    }
    public void loadSagittariusLocation(String name, int x, int y) {
    	
    }
    public void loadScorpioLocation(String name, int x, int y) {
    	
    }
    public void loadTaurusLocation(String name, int x, int y) {
    	
    }

    public void loadConstNames() {
    	loadHerculesNames();
    	loadUrsaMinorNames();
    	loadUrsaMajorNames();
    	loadLibraNames();
    	loadAndromedaNames();
    	loadAquariusNames();
    	loadAquilaNames();
    	loadAriesNames();
    	loadAurigaNames();
    	loadBootesNames();
    	loadCancerNames();
    	loadCanisMajorNames();
    	loadCanisMinorNames();
    	loadCapricornusNames();
    	loadCassiopeiaNames();
    	loadCentaurusNames();
    	loadCepheusNames();
    	loadCruxNames();
    	loadCygnusNames();
    	loadDracoNames();
    	loadGeminiNames();
    	loadHydraNames();
    	loadLeoNames();
    	loadLyraNames();
    	loadOrionNames();
    	loadPegasusNames();
    	loadPerseusNames();
    	loadPiscesNames();
    	loadSagittariusNames();
    	loadScorpioNames();
    	loadTaurusNames();
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
    public void loadAndromedaNames() {
    	andromedaNames = new ArrayList<>();
    	andromedaNames.add(" 50Ups And");
    	andromedaNames.add("Mirach");
    	andromedaNames.add(" 37Mu  And");
    	andromedaNames.add(" 35Nu  And");
    	andromedaNames.add(" 42Phi And");
    	andromedaNames.add(" 29Pi  And");
    	andromedaNames.add(" 17Iot And");
    	andromedaNames.add(" 19Kap And");
    	andromedaNames.add(" 16Lam And");
    	andromedaNames.add("  1Omi And");
    	andromedaNames.add(" 31Del And");
    	andromedaNames.add(" 30Eps And");
    	andromedaNames.add("Alpheratz");
    	andromedaNames.add(" 34Zet And");
    	andromedaNames.add(" 38Eta And");
    	
    	
    }
    public void loadAquariusNames() {
    	aquariusNames = new ArrayList<>();
    	
    }
    public void loadAquilaNames() {
    	aquilaNames = new ArrayList<>();
    	aquilaNames.add(" 17Zet Aql");
    	aquilaNames.add(" 16Lam Aql");
    	aquilaNames.add(" 30Del Aql");
    	aquilaNames.add(" 41Iot Aql");
    	aquilaNames.add("Tarazed");
    	aquilaNames.add("Altair");
    	aquilaNames.add(" 60Bet Aql");
    	aquilaNames.add(" 65The Aql");
    	
    }
    public void loadAriesNames() {
    	ariesNames = new ArrayList<>();
    	ariesNames.add(" 41    Ari");
    	ariesNames.add("Hamal");
    	ariesNames.add("  6Bet Ari");
    	ariesNames.add("  5Gam2Ari");
    }
    public void loadAurigaNames() {
    	aurigaNames = new ArrayList<>();
    	aurigaNames.add(" 33Del Aur");
    	aurigaNames.add("Capella");
    	aurigaNames.add("  7Eps Aur");
    	aurigaNames.add("  8Zet Aur");
    	aurigaNames.add("  3Iot Aur");
    	aurigaNames.add(" 34Bet Aur");
    	aurigaNames.add(" 37The Aur");
    }
    public void loadBootesNames() {
    	bootesNames = new ArrayList<>();
    }
    public void loadCancerNames() {
    	cancerNames = new ArrayList<>();
    	cancerNames.add(" 48Iot Cnc");
    	cancerNames.add(" 43Gam Cnc");
    	cancerNames.add(" 47Del Cnc");
    	cancerNames.add(" 17Bet Cnc");
    	cancerNames.add(" 65Alp Cnc");
    	
    }
    public void loadCanisMajorNames() {
    	canisMajorNames = new ArrayList<>();
    	canisMajorNames.add("Adhara");
    	canisMajorNames.add(" 25Del CMa");
    	canisMajorNames.add(" 31Eta CMa");
    	canisMajorNames.add("Sirius");
    	canisMajorNames.add(" 20Iot CMa");
    	canisMajorNames.add(" 23Gam CMa");
    	canisMajorNames.add(" 14The CMa");
    	canisMajorNames.add("  2Bet CMa");
    	canisMajorNames.add("  7Nu 2CMa");
    	canisMajorNames.add(" 16Omi1CMa");
    	
    }
    public void loadCanisMinorNames() {
    	canisMinorNames = new ArrayList<>();
    	canisMinorNames.add("Procyon");
    	canisMinorNames.add(" 3Bet CMi");
    }
    public void loadCapricornusNames() {
    	capricornusNames = new ArrayList<>();
    }
    public void loadCassiopeiaNames() {
    	cassiopeiaNames = new ArrayList<>();
    	cassiopeiaNames.add("Caph");
    	cassiopeiaNames.add("Shedir");
    	cassiopeiaNames.add(" 27Gam Cas");
    	cassiopeiaNames.add(" 37Del Cas");
    	cassiopeiaNames.add(" 45Eps Cas");
    }
    public void loadCentaurusNames() {
    	centaurusNames = new ArrayList<>();
    }
    public void loadCepheusNames() {
    	cepheusNames = new ArrayList<>();
    	cepheusNames.add("  2The Cep");
    	cepheusNames.add("  3Eta Cep");
    	cepheusNames.add("Alderamin");
    	cepheusNames.add("  8Bet Cep");
    	cepheusNames.add(" 35Gam Cep");
    	cepheusNames.add(" 32Iot Cep");
    	cepheusNames.add(" 27Del Cep");
    	cepheusNames.add(" 21Zet Cep");
    	cepheusNames.add(" 23Eps Cep");
    	
    }
    public void loadCruxNames() {
    	cruxNames = new ArrayList<>();
    	cruxNames.add("Acrux");
    	cruxNames.add("   Del Cru");
    	cruxNames.add("   Gam Cru");
    	cruxNames.add("   Bet Cru");
    }
    public void loadCygnusNames() {
    	cygnusNames = new ArrayList<>();
    }
    public void loadDracoNames() {
    	dracoNames = new ArrayList<>();
    }
    public void loadGeminiNames() {
    	geminiNames = new ArrayList<>();
    }
    public void loadHydraNames() {
    	hydraNames = new ArrayList<>();
    }
    public void loadLeoNames() {
    	leoNames = new ArrayList<>();
    }
    public void loadLyraNames() {
    	lyraNames = new ArrayList<>();
    	lyraNames.add("Vega");
    	lyraNames.add("  4Eps1Lyr");
    	lyraNames.add("  6Zet1Lyr");
    	lyraNames.add(" 11Del1Lyr");
    	lyraNames.add(" 14Gam Lyr");
    	lyraNames.add(" 10Bet Lyr");
    	
    }
    public void loadOrionNames() {
    	orionNames = new ArrayList<>();
    }
    public void loadPegasusNames() {
    	pegasusNames = new ArrayList<>();
    }
    public void loadPerseusNames() {
    	perseusNames = new ArrayList<>();
    }
    public void loadPiscesNames() {
    	piscesNames = new ArrayList<>();
    }
    public void loadSagittariusNames() {
    	sagittariusNames = new ArrayList<>();
    }
    public void loadScorpioNames() {
    	scorpioNames = new ArrayList<>();
    }
    public void loadTaurusNames() {
    	taurusNames = new ArrayList<>();
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
    public Boolean checkAndromeda() {
    	Boolean flag = true;
    	for(int i = 0; i < 30; i++) {
    		if(andromeda[i] == 0) {
    			flag = false;
    		}
    	}
    	return flag;
    }
    public Boolean checkAquarius() {
    	Boolean flag = false;
    	
    	return flag;
    }
    public Boolean checkAquila() {
    	Boolean flag = true;
    	for(int i = 0; i < 16; i++) {
    		if(aquila[i] == 0) {
    			flag = false;
    		}
    	}
    	return flag;
    }
    public Boolean checkAries() {
    	Boolean flag = true;
    	for(int i = 0; i < 8; i++) {
    		if(aries[i] == 0) {
    			flag = false;
    		}
    	}
    	return flag;
    }
    public Boolean checkAuriga() {
    	Boolean flag = true;
    	for(int i = 0; i < 14; i++) {
    		if(auriga[i] == 0) {
    			flag = false;
    		}
    	}
    	return flag;
    }
    public Boolean checkBootes() {
    	Boolean flag = false;
    	
    	return flag;
    }
    public Boolean checkCancer() {
    	Boolean flag = true;
    	for(int i = 0; i < 10; i++) {
    		if(cancer[i] == 0) {
    			flag = false;
    		}
    	}
    	return flag;
    }
    public Boolean checkCanisMajor() {
    	Boolean flag = true;
    	for(int i = 0; i < 20; i++) {
    		if(canisMajor[i] == 0) {
    			flag = false;
    		}
    	}
    	return flag;
    }
    public Boolean checkCanisMinor() {
    	Boolean flag = true;
    	for(int i = 0; i < 4; i++) {
    		if(canisMinor[i] == 0) {
    			flag = false;
    		}
    	}
    	return flag;
    }
    public Boolean checkCapricornus() {
    	Boolean flag = false;
    	
    	return flag;
    }
    public Boolean checkCassiopeia() {
    	Boolean flag = true;
    	for(int i = 0; i < 10; i++) {
    		if(cassiopeia[i] == 0) {
    			flag = false;
    		}
    	}
    	return flag;
    }
    public Boolean checkCentaurus() {
    	Boolean flag = false;
    	
    	return flag;
    }
    public Boolean checkCepheus() {
    	Boolean flag = true;
    	for(int i = 0; i < 18; i++) {
    		if(cepheus[i] == 0) {
    			flag = false;
    		}
    	}
    	return flag;
    }
    public Boolean checkCrux() {
    	Boolean flag = true;
    	for(int i = 0; i < 8; i++) {
    		if(crux[i] == 0) {
    			flag = false;
    		}
    	}
    	return flag;
    }
    public Boolean checkCygnus() {
    	Boolean flag = false;
    	
    	return flag;
    }
    public Boolean checkDraco() {
    	Boolean flag = false;
    	
    	return flag;
    }
    public Boolean checkGemini() {
    	Boolean flag = false;
    	
    	return flag;
    }
    public Boolean checkHydra() {
    	Boolean flag = false;
    	
    	return flag;
    }
    public Boolean checkLeo() {
    	Boolean flag = false;
    	
    	return flag;
    }
    public Boolean checkLyra() {
    	Boolean flag = true;
    	for(int i = 0; i < 12; i++) {
    		if(lyra[i] == 0) {
    			flag = false;
    		}
    	}
    	return flag;
    }
    public Boolean checkOrion() {
    	Boolean flag = false;
    	
    	return flag;
    }
    public Boolean checkPegasus() {
    	Boolean flag = false;
    	
    	return flag;
    }
    public Boolean checkPerseus() {
    	Boolean flag = false;
    	
    	return flag;
    }
    public Boolean checkPisces() {
    	Boolean flag = false;
    	
    	return flag;
    }
    public Boolean checkSagittarius() {
    	Boolean flag = false;
    	
    	return flag;
    }
    public Boolean checkScorpio() {
    	Boolean flag = false;
    	
    	return flag;
    }    
    public Boolean checkTaurus() {
    	Boolean flag = false;
    	
    	return flag;
    }
    
    public static int getRandom(int min, int max){
        int x = (int) ((Math.random()*((max-min)+1))+min);
        return x;
    }

}