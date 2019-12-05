

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.filechooser.FileSystemView;

import JohnWork.main.SpaceObj;


public class DrawingSky extends Canvas {
	
	ArrayList<String> starLabels;
	ArrayList<String> constLabels;
	ArrayList<String> planetLabels;
	ArrayList<String> mesrLabels;
	ArrayList<String> constNames;
	ArrayList<String> moonLabel;
	
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
	int[] aquarius = new int[26];
	ArrayList<String> aquariusNames;
	int[] aquila = new int[16];
	ArrayList<String> aquilaNames;
	int[] aries = new int[8];
	ArrayList<String> ariesNames;
	int[] auriga = new int[14];
	ArrayList<String> aurigaNames;
	int[] bootes = new int[20];
	ArrayList<String> bootesNames;
	int[] cancer = new int[10];
	ArrayList<String> cancerNames;
	int[] canisMajor = new int[20];
	ArrayList<String> canisMajorNames;
	int[] canisMinor = new int[4];
	ArrayList<String> canisMinorNames;
	int[] capricornus = new int[10];
	ArrayList<String> capricornusNames;
	int[] cassiopeia = new int[10];
	ArrayList<String> cassiopeiaNames;
	int[] centaurus = new int[30];
	ArrayList<String> centaurusNames;
	int[] cepheus = new int[18];
	ArrayList<String> cepheusNames;
	int[] crux = new int[8];
	ArrayList<String> cruxNames;
	int[] cygnus = new int[14];
	ArrayList<String> cygnusNames;
	int[] draco = new int[28];
	ArrayList<String> dracoNames;
	int[] gemini = new int[28];
	ArrayList<String> geminiNames;
	int[] hydra = new int[28];
	ArrayList<String> hydraNames;
	int[] leo = new int[26];
	ArrayList<String> leoNames;
	int[] lyra = new int[12];
	ArrayList<String> lyraNames;
	int[] orion = new int[32];
	ArrayList<String> orionNames;
	int[] pegasus = new int[16];
	ArrayList<String> pegasusNames;
	int[] perseus = new int[28];
	ArrayList<String> perseusNames;
	int[] pisces = new int[20];
	ArrayList<String> piscesNames;
	int[] sagittarius = new int[16];
	ArrayList<String> sagittariusNames;
	int[] scorpio = new int[20];
	ArrayList<String> scorpioNames;
	int[] taurus= new int[16];
	ArrayList<String> taurusNames;
	
	// Generate SkyMap
    public ImageIcon draw() {
        JFrame frame = new JFrame("My Drawing");
        frame.setBackground(Color.BLACK);
        Canvas canvas = new DrawingSky();
        canvas.setSize(2250, 2250);
        frame.add(canvas);
        frame.pack();
        return takeSnapShot(canvas);
    }

    //Paint Image
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

    	//Draw stars
    	for(int i = 0; i < (list.size()/4); i++) {
    		g.fillOval(list.get(a), list.get(b), list.get(c), list.get(d));
    		a = d + 1;
    		b = a + 1;
    		c = b + 1;
    		d = c + 1;
    	}
    	
    	//Draw star labels if checked
    	index = 0;
    	for(int i = 0; i < (starLabels.size()/3); i++) {
    		g.drawString(starLabels.get(index), Integer.valueOf(starLabels.get(index+1)), Integer.valueOf(starLabels.get(index+2)));
    		index +=3;
    	}
    	
    	g.setFont(new Font("Helvetica", Font.PLAIN, 20)); 
    	
    	//Draw constellation labels if checked
    	index = 0;
    	for(int i = 0; i < (constLabels.size()/3); i++) {
    		g.drawString(constLabels.get(index), Integer.valueOf(constLabels.get(index+1)), Integer.valueOf(constLabels.get(index+2)));
    		index +=3;
    	}
    	
    	//Draw planet labels if checked
    	index = 0;
    	for(int i = 0; i < (planetLabels.size()/3); i++) {
    		g.drawString(planetLabels.get(index), Integer.valueOf(planetLabels.get(index+1)), Integer.valueOf(planetLabels.get(index+2)));
    		index +=3;
    	}
    	
    	//Draw messier labels if checked
    	index = 0;
    	for(int i = 0; i < (mesrLabels.size()/3); i++) {
    		g.drawString(mesrLabels.get(index), Integer.valueOf(mesrLabels.get(index+1)), Integer.valueOf(mesrLabels.get(index+2)));
    		index +=3;
    	}
    	
    	//Draw moon with label
    	if(moonLabel.size() > 0) {
    		g.drawString(moonLabel.get(0), Integer.valueOf(moonLabel.get(1)), Integer.valueOf(moonLabel.get(2)));
    	}
    	
    	//Draw Constellations
    	if(checkTaurus()) {
    		g.drawLine(taurus[0], taurus[1], taurus[2], taurus[3]);
    		g.drawLine(taurus[4], taurus[5], taurus[6], taurus[7]);
    		g.drawLine(taurus[2], taurus[3], taurus[8], taurus[9]);
    		g.drawLine(taurus[6], taurus[7], taurus[8], taurus[9]);
    		g.drawLine(taurus[8], taurus[9], taurus[10], taurus[11]);
    		g.drawLine(taurus[10], taurus[11], taurus[12], taurus[13]);
    		g.drawLine(taurus[12], taurus[13], taurus[14], taurus[15]);
    	}
    	
    	if(checkPisces()) {
    		g.drawLine(pisces[0], pisces[1], pisces[2], pisces[3]);
    		g.drawLine(pisces[2], pisces[3], pisces[4], pisces[5]);
    		g.drawLine(pisces[4], pisces[5], pisces[6], pisces[7]);
    		g.drawLine(pisces[6], pisces[7], pisces[8], pisces[9]);
    		g.drawLine(pisces[8], pisces[9], pisces[10], pisces[11]);
    		g.drawLine(pisces[10], pisces[11], pisces[12], pisces[13]);
    		g.drawLine(pisces[12], pisces[13], pisces[4], pisces[5]);
    		g.drawLine(pisces[0], pisces[1], pisces[14], pisces[15]);
    		g.drawLine(pisces[14], pisces[15], pisces[16], pisces[17]);
    		g.drawLine(pisces[16], pisces[17], pisces[18], pisces[19]);
    		g.drawLine(pisces[18], pisces[19], pisces[14], pisces[15]);
    	}
    	
    	if(checkHydra()) {
    		g.drawLine(hydra[0], hydra[1], hydra[2], hydra[3]);
    		g.drawLine(hydra[2], hydra[3], hydra[4], hydra[5]);
    		g.drawLine(hydra[4], hydra[5], hydra[6], hydra[7]);
    		g.drawLine(hydra[6], hydra[7], hydra[8], hydra[9]);
    		g.drawLine(hydra[8], hydra[9], hydra[10], hydra[11]);
    		g.drawLine(hydra[10], hydra[11], hydra[12], hydra[13]);
    		g.drawLine(hydra[12], hydra[13], hydra[14], hydra[15]);
    		g.drawLine(hydra[14], hydra[15], hydra[16], hydra[17]);
    		g.drawLine(hydra[16], hydra[17], hydra[18], hydra[19]);
    		g.drawLine(hydra[18], hydra[19], hydra[20], hydra[21]);
    		g.drawLine(hydra[20], hydra[21], hydra[22], hydra[23]);
    		g.drawLine(hydra[24], hydra[25], hydra[26], hydra[27]);
    		g.drawLine(hydra[24], hydra[25], hydra[26], hydra[27]);
    		g.drawLine(hydra[26], hydra[27], hydra[20], hydra[21]);
    		
    	}
    	
    	if(checkScorpio()) {
    		g.drawLine(scorpio[0], scorpio[1], scorpio[2], scorpio[3]);
    		g.drawLine(scorpio[2], scorpio[3], scorpio[4], scorpio[5]);
    		g.drawLine(scorpio[4], scorpio[5], scorpio[6], scorpio[7]);
    		g.drawLine(scorpio[6], scorpio[7], scorpio[8], scorpio[9]);
    		g.drawLine(scorpio[8], scorpio[9], scorpio[10], scorpio[11]);
    		g.drawLine(scorpio[10], scorpio[11], scorpio[12], scorpio[13]);
    		g.drawLine(scorpio[12], scorpio[13], scorpio[14], scorpio[15]);
    		g.drawLine(scorpio[14], scorpio[15], scorpio[16], scorpio[17]);
    		g.drawLine(scorpio[18], scorpio[19], scorpio[14], scorpio[15]);
    	}
    	
    	if(checkSagittarius()) {
    		g.drawLine(sagittarius[0], sagittarius[1], sagittarius[2], sagittarius[3]);
    		g.drawLine(sagittarius[2], sagittarius[3], sagittarius[4], sagittarius[5]);
    		g.drawLine(sagittarius[4], sagittarius[5], sagittarius[6], sagittarius[7]);
    		g.drawLine(sagittarius[6], sagittarius[7], sagittarius[8], sagittarius[9]);
    		g.drawLine(sagittarius[4], sagittarius[5], sagittarius[8], sagittarius[9]);
    		g.drawLine(sagittarius[8], sagittarius[9], sagittarius[10], sagittarius[11]);
    		g.drawLine(sagittarius[10], sagittarius[11], sagittarius[12], sagittarius[13]);
    		g.drawLine(sagittarius[12], sagittarius[13], sagittarius[8], sagittarius[9]);
    		g.drawLine(sagittarius[12], sagittarius[13], sagittarius[14], sagittarius[15]);
    		g.drawLine(sagittarius[14], sagittarius[14], sagittarius[4], sagittarius[5]);
    		g.drawLine(sagittarius[0], sagittarius[1], sagittarius[14], sagittarius[15]);
    		
    	}
    	
    	if(checkPerseus()) {
    		g.drawLine(perseus[0], perseus[1], perseus[2], perseus[3]);
    		g.drawLine(perseus[0], perseus[1], perseus[26], perseus[27]);
    		g.drawLine(perseus[2], perseus[3], perseus[6], perseus[7]);
    		g.drawLine(perseus[6], perseus[7], perseus[4], perseus[5]);
    		g.drawLine(perseus[6], perseus[7], perseus[8], perseus[9]);
    		g.drawLine(perseus[8], perseus[9], perseus[10], perseus[11]);
    		g.drawLine(perseus[8], perseus[9], perseus[18], perseus[19]);
    		g.drawLine(perseus[6], perseus[7], perseus[24], perseus[25]);
    		g.drawLine(perseus[26], perseus[27], perseus[24], perseus[25]);
    		g.drawLine(perseus[24], perseus[25], perseus[20], perseus[21]);
    		g.drawLine(perseus[20], perseus[21], perseus[22], perseus[23]);
    		g.drawLine(perseus[20], perseus[21], perseus[18], perseus[19]);
    		g.drawLine(perseus[18], perseus[19], perseus[16], perseus[17]);
    		g.drawLine(perseus[16], perseus[17], perseus[14], perseus[15]);
    		g.drawLine(perseus[14], perseus[15], perseus[12], perseus[13]);
    		
    	}
    	
    	if(checkOrion()) {
    		g.drawLine(orion[0], orion[1], orion[2], orion[3]);
    		g.drawLine(orion[2], orion[3], orion[4], orion[5]);
    		g.drawLine(orion[4], orion[5], orion[6], orion[7]);
    		g.drawLine(orion[6], orion[7], orion[8], orion[9]);
    		g.drawLine(orion[8], orion[9], orion[10], orion[11]);
    		g.drawLine(orion[4], orion[5], orion[12], orion[13]);
    		g.drawLine(orion[12], orion[13], orion[14], orion[15]);
    		g.drawLine(orion[14], orion[15], orion[16], orion[17]);
    		g.drawLine(orion[16], orion[17], orion[12], orion[13]);
    		g.drawLine(orion[12], orion[13], orion[26], orion[27]);
    		g.drawLine(orion[26], orion[27], orion[28], orion[29]);
    		g.drawLine(orion[26], orion[27], orion[24], orion[25]);
    		g.drawLine(orion[24], orion[25], orion[30], orion[31]);
    		g.drawLine(orion[24], orion[25], orion[16], orion[17]);
    		g.drawLine(orion[16], orion[17], orion[18], orion[19]);
    		g.drawLine(orion[18], orion[19], orion[20], orion[21]);
    		g.drawLine(orion[20], orion[21], orion[22], orion[23]);
    		g.drawLine(orion[22], orion[23], orion[18], orion[19]);
    		
    	}
    	
    	if(checkGemini()) {
    		g.drawLine(gemini[0], gemini[1], gemini[2], gemini[3]);
    		g.drawLine(gemini[2], gemini[3], gemini[4], gemini[5]);
    		g.drawLine(gemini[2], gemini[3], gemini[6], gemini[7]);
    		g.drawLine(gemini[6], gemini[7], gemini[8], gemini[9]);
    		g.drawLine(gemini[6], gemini[7], gemini[10], gemini[11]);
    		g.drawLine(gemini[2], gemini[3], gemini[18], gemini[19]);
    		g.drawLine(gemini[18], gemini[19], gemini[20], gemini[21]);
    		g.drawLine(gemini[20], gemini[21], gemini[22], gemini[23]);
    		g.drawLine(gemini[18], gemini[19], gemini[24], gemini[25]);
    		g.drawLine(gemini[6], gemini[7], gemini[12], gemini[13]);
    		g.drawLine(gemini[12], gemini[13], gemini[16], gemini[17]);
    		g.drawLine(gemini[12], gemini[13], gemini[14], gemini[15]);
    		
    	}
    	
    	if(checkCentaurus()) {
    		g.drawLine(centaurus[0], centaurus[1], centaurus[4], centaurus[5]);
    		g.drawLine(centaurus[4], centaurus[5], centaurus[6], centaurus[7]);
    		g.drawLine(centaurus[6], centaurus[7], centaurus[2], centaurus[3]);
    		g.drawLine(centaurus[6], centaurus[7], centaurus[8], centaurus[9]);
    		g.drawLine(centaurus[8], centaurus[9], centaurus[10], centaurus[11]);
    		g.drawLine(centaurus[10], centaurus[11], centaurus[12], centaurus[13]);
    		g.drawLine(centaurus[8], centaurus[9], centaurus[14], centaurus[15]);
    		g.drawLine(centaurus[10], centaurus[11], centaurus[14], centaurus[15]);
    		g.drawLine(centaurus[14], centaurus[15], centaurus[16], centaurus[17]);
    		g.drawLine(centaurus[16], centaurus[17], centaurus[18], centaurus[19]);
    		g.drawLine(centaurus[16], centaurus[17], centaurus[20], centaurus[21]);
    		g.drawLine(centaurus[20], centaurus[21], centaurus[22], centaurus[23]);
    		g.drawLine(centaurus[22], centaurus[23], centaurus[24], centaurus[25]);
    		g.drawLine(centaurus[24], centaurus[25], centaurus[26], centaurus[27]);
    		g.drawLine(centaurus[24], centaurus[25], centaurus[28], centaurus[29]);
    		g.drawLine(centaurus[28], centaurus[29], centaurus[14], centaurus[15]);
    	}
    	
    	if(checkAquarius()) {
    		g.drawLine(aquarius[0], aquarius[1], aquarius[2], aquarius[3]);
    		g.drawLine(aquarius[2], aquarius[3], aquarius[4], aquarius[5]);
    		g.drawLine(aquarius[2], aquarius[3], aquarius[6], aquarius[7]);
    		g.drawLine(aquarius[6], aquarius[7], aquarius[8], aquarius[9]);
    		g.drawLine(aquarius[8], aquarius[9], aquarius[10], aquarius[11]);
    		g.drawLine(aquarius[10], aquarius[11], aquarius[12], aquarius[13]);
    		g.drawLine(aquarius[12], aquarius[13], aquarius[6], aquarius[7]);
    		g.drawLine(aquarius[6], aquarius[7], aquarius[14], aquarius[15]);
    		g.drawLine(aquarius[14], aquarius[15], aquarius[16], aquarius[17]);
    		g.drawLine(aquarius[16], aquarius[17], aquarius[18], aquarius[19]);
    		g.drawLine(aquarius[18], aquarius[19], aquarius[20], aquarius[21]);
    		g.drawLine(aquarius[20], aquarius[21], aquarius[22], aquarius[23]);
    		g.drawLine(aquarius[22], aquarius[23], aquarius[24], aquarius[25]);
    		g.drawLine(aquarius[24], aquarius[25], aquarius[16], aquarius[17]);	
    	}
    	
    	if(checkLeo()) {
    		g.drawLine(leo[0], leo[1], leo[4], leo[5]);
    		g.drawLine(leo[4], leo[5], leo[6], leo[7]);
    		g.drawLine(leo[6], leo[7], leo[12], leo[13]);
    		g.drawLine(leo[8], leo[9], leo[10], leo[11]);
    		g.drawLine(leo[10], leo[11], leo[12], leo[13]);
    		g.drawLine(leo[12], leo[13], leo[14], leo[15]);
    		g.drawLine(leo[10], leo[11], leo[18], leo[19]);
    		g.drawLine(leo[12], leo[13], leo[16], leo[17]);
    		g.drawLine(leo[18], leo[19], leo[20], leo[21]);
    		g.drawLine(leo[20], leo[21], leo[16], leo[17]);
    		g.drawLine(leo[16], leo[17], leo[22], leo[23]);
    		g.drawLine(leo[22], leo[23], leo[24], leo[25]);
    		g.drawLine(leo[6], leo[7], leo[2], leo[3]);
    		g.drawLine(leo[2], leo[3], leo[0], leo[1]);
    		g.drawLine(leo[2], leo[3], leo[8], leo[9]);
    		
    	}
    	
    	if(checkPegasus()) {
    		g.drawLine(pegasus[0], pegasus[1], pegasus[2], pegasus[3]);
    		g.drawLine(pegasus[2], pegasus[3], pegasus[4], pegasus[5]);
    		g.drawLine(pegasus[4], pegasus[5], pegasus[6], pegasus[7]);
    		g.drawLine(pegasus[4], pegasus[5], pegasus[8], pegasus[9]);
    		g.drawLine(pegasus[8], pegasus[9], pegasus[10], pegasus[11]);
    		g.drawLine(pegasus[8], pegasus[9], pegasus[12], pegasus[13]);
    		g.drawLine(pegasus[12], pegasus[13], pegasus[14], pegasus[15]);
    	}
    	
    	if(checkDraco()) {
    		g.drawLine(draco[0], draco[1], draco[2], draco[3]);
    		g.drawLine(draco[2], draco[3], draco[4], draco[5]);
    		g.drawLine(draco[4], draco[5], draco[6], draco[7]);
    		g.drawLine(draco[6], draco[7], draco[8], draco[9]);
    		g.drawLine(draco[8], draco[9], draco[10], draco[11]);
    		g.drawLine(draco[10], draco[11], draco[14], draco[15]);
    		g.drawLine(draco[14], draco[15], draco[18], draco[19]);
    		g.drawLine(draco[14], draco[15], draco[12], draco[13]);
    		g.drawLine(draco[18], draco[19], draco[16], draco[17]);
    		g.drawLine(draco[18], draco[19], draco[20], draco[21]);
    		g.drawLine(draco[20], draco[21], draco[22], draco[23]);
    		g.drawLine(draco[22], draco[23], draco[24], draco[25]);
    		g.drawLine(draco[24], draco[25], draco[26], draco[27]);
    		g.drawLine(draco[26], draco[27], draco[20], draco[21]);
    		
    	}
    	
    	if(checkCapricornus()) {
    		g.drawLine(capricornus[0], capricornus[1], capricornus[2], capricornus[3]);
    		g.drawLine(capricornus[2], capricornus[3], capricornus[4], capricornus[5]);
    		g.drawLine(capricornus[4], capricornus[5], capricornus[6], capricornus[7]);
    		g.drawLine(capricornus[6], capricornus[7], capricornus[8], capricornus[9]);
    		g.drawLine(capricornus[8], capricornus[9], capricornus[0], capricornus[1]);
    	}
    	
    	if(checkCygnus()) {
    		g.drawLine(cygnus[0], cygnus[1], cygnus[2], cygnus[3]);
    		g.drawLine(cygnus[2], cygnus[3], cygnus[4], cygnus[5]);
    		g.drawLine(cygnus[4], cygnus[5], cygnus[6], cygnus[7]);
    		g.drawLine(cygnus[6], cygnus[7], cygnus[8], cygnus[9]);
    		g.drawLine(cygnus[6], cygnus[7], cygnus[10], cygnus[11]);
    		g.drawLine(cygnus[10], cygnus[11], cygnus[12], cygnus[13]);
    		g.drawLine(cygnus[12], cygnus[13], cygnus[0], cygnus[1]);
    		
    	}
    	
    	if(checkBootes()) {
    		g.drawLine(bootes[0], bootes[1], bootes[4], bootes[5]);
    		g.drawLine(bootes[0], bootes[1], bootes[2], bootes[3]);
    		g.drawLine(bootes[2], bootes[3], bootes[4], bootes[5]);
    		g.drawLine(bootes[4], bootes[5], bootes[6], bootes[7]);
    		g.drawLine(bootes[6], bootes[7], bootes[8], bootes[9]);
    		g.drawLine(bootes[8], bootes[9], bootes[10], bootes[11]);
    		g.drawLine(bootes[10], bootes[11], bootes[12], bootes[13]);
    		g.drawLine(bootes[10], bootes[11], bootes[14], bootes[15]);
    		g.drawLine(bootes[10], bootes[11], bootes[16], bootes[17]);
    		g.drawLine(bootes[16], bootes[17], bootes[18], bootes[19]);
    		g.drawLine(bootes[18], bootes[19], bootes[6], bootes[7]);
    		
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
    
    //Create lists that are used to draw
    public ArrayList<Integer> createList(){
    	ArrayList<Integer> test = new ArrayList<>();
    	starLabels = new ArrayList<>();
    	constLabels = new ArrayList<>();
    	planetLabels = new ArrayList<>();
    	mesrLabels = new ArrayList<>();
    	moonLabel = new ArrayList<>();
    	
    	int a = 0;
    	int b = 1;
    	int c = 2;
    	int d = 3;
    	
    	int size;
    	
    	//Go through the spaceobjectlist
    	for(int i = 0; i < AlexxWork2.spaceObjList.size(); i++) {
    		
    		//If object is visible and object has positive altitude continue
    		if(AlexxWork2.spaceObjList.get(i).getMagnitude() != null 
    				&& (Double.valueOf(AlexxWork2.spaceObjList.get(i).getMagnitude()) <= 6.0) 
    				&& AlexxWork2.spaceObjList.get(i).getAltitude() > 0.5) {
	    		
    			
    			//Calculate X and Y
    			int x = getX(2250, 2250, 1000, 
    					(int)AlexxWork2.spaceObjList.get(i).getAzimuth(), 
    					(int)AlexxWork2.spaceObjList.get(i).getAltitude());
    			
				int y = getY(2250, 2250, 1000, 
						(int)AlexxWork2.spaceObjList.get(i).getAzimuth(), 
						(int)AlexxWork2.spaceObjList.get(i).getAltitude());
				
				//Load stars
				if(AlexxWork2.spaceObjList.get(i).getType() == "STAR" 
						&& AlexxWork2.spaceObjList.get(i).getProperName() != null 
						&& AlexxWork2.spaceObjList.get(i).getProperName() != "") {
					
					if(AlexxWork2.starNamesCB 
							&& Double.valueOf(AlexxWork2.spaceObjList.get(i).getMagnitude()) <= 6.0) {
						try {
							//Filter out number only star names
							int testInt = Integer.parseInt(AlexxWork2.spaceObjList.get(i).getProperName());
							} catch (NumberFormatException | NullPointerException nfe) {
								starLabels.add(AlexxWork2.spaceObjList.get(i).getProperName());
								starLabels.add(String.valueOf(x));
								starLabels.add(String.valueOf(y));
							}
					}
					
					//Load constellation data
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
				
				//Add coordinates to list
	    		test.add(a, x);
	    		test.add(b, y);
	    		
	    		//Add moon information if visible
	    		if(AlexxWork2.spaceObjList.get(i).getProperName() == "MOON") {
	    			size = 22;
	    			String moonName = AlexxWork2.spaceObjList.get(i).getProperName() + ": " + AlexxWork2.spaceObjList.get(i).getType();
	    			moonLabel.add(moonName);
					moonLabel.add(String.valueOf(x));
					moonLabel.add(String.valueOf(y));
	    		}
	    		
	    		//If object is planet, set the size
	    		else if(AlexxWork2.spaceObjList.get(i).getType() == "PLAN"){
	    			size = 16;
	    		}
	    		
	    		//Else set size based on mag
	    		else{
	    			size = getSize(Double.valueOf(AlexxWork2.spaceObjList.get(i).getMagnitude()));
	    		}
	    		
	    		//Add size to list
	    		test.add(c, size);
	    		test.add(d, size);
	    		a = d + 1;
	    		b = a + 1;
	    		c = b + 1;
	    		d = c + 1;
    		}
    		
    		//Load constellation labels
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
    		
    		//Load planet labels
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
    		
    		//Load messier labels
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
    	
    	//Return list 
    	return test;
    }
    
    public int getX(int screenHeight, int screenWidth, int r, int z, int a) {
	int x;
	
	//Cartesian coordinate
	x = (int) (r * Math.sin(Math.toRadians(z)) * Math.cos(Math.toRadians(a)));
	
	//Pixel Coordinate
	x = x + (screenWidth/2);
	
	return x;	
}

	public int getY(int screenHeight, int screenWidth, int r, int z, int a) {
	int y;
	
	//Cartesian coordinate
	y = (int) (r * Math.cos(Math.toRadians(z)) * Math.cos(Math.toRadians(a)));	
			
	//Pixel Coordinate
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

	// Take screenshot of skymap
	public ImageIcon takeSnapShot(Component panel){
       // BufferedImage bufImage = new BufferedImage(panel.getSize().width, panel.getSize().height,BufferedImage.TYPE_INT_RGB);
        BufferedImage bufImage = new BufferedImage(2250, 2250,BufferedImage.TYPE_INT_RGB);
        panel.paint(bufImage.createGraphics());
        ImageIcon imageIcon = new ImageIcon(bufImage);
        String snapshotLocation = FileSystemView.getFileSystemView().getDefaultDirectory().toString() + "\\spaceGUI\\yourImage.jpeg";
        File fc = new File(snapshotLocation.substring(0,snapshotLocation.indexOf("yourImage.jpeg")));
        if(!fc.exists()) {
            fc.mkdir();
         }
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
    
	//Load constellation locations
	
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
    	if(name.contains("Rasalgethi")) {
    		hercules[0] = x;
    		hercules[1] = y;
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
    	if(name.contains("  2Eps Aqr")) {
    		aquarius[0] = x;
    		aquarius[1] = y;
    	}
    	if(name.contains(" 22Bet Aqr")) {
    		aquarius[2] = x;
    		aquarius[3] = y;
    	}
    	if(name.contains(" 33Iot Aqr")) {
    		aquarius[4] = x;
    		aquarius[5] = y;
    	}
    	if(name.contains(" 34Alp Aqr")) {
    		aquarius[6] = x;
    		aquarius[7] = y;
    	}
    	if(name.contains(" 52Pi  Aqr")) {
    		aquarius[8] = x;
    		aquarius[9] = y;
    	}
    	if(name.contains(" 55Zet Aqr")) {
    		aquarius[10] = x;
    		aquarius[11] = y;
    	}
    	if(name.contains(" 48Gam Aqr")) {
    		aquarius[12] = x;
    		aquarius[13] = y;
    	}
    	if(name.contains(" 43The Aqr")) {
    		aquarius[14] = x;
    		aquarius[15] = y;
    	}
    	if(name.contains(" 73Lam Aqr")) {
    		aquarius[16] = x;
    		aquarius[17] = y;
    	}
    	if(name.contains(" 90Phi Aqr")) {
    		aquarius[18] = x;
    		aquarius[19] = y;
    	}
    	if(name.contains(" 93Psi2Aqr")) {
    		aquarius[20] = x;
    		aquarius[21] = y;
    	}
    	if(name.contains(" 76Del Aqr")) {
    		aquarius[22] = x;
    		aquarius[23] = y;
    	}
    	if(name.contains(" 69Tau1Aqr")) {
    		aquarius[24] = x;
    		aquarius[25] = y;
    	}
    	
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
    	if(name.contains(" 23The Boo")) {
    		bootes[0] = x;
    		bootes[1] = y;
    		
    	}
    	if(name.contains(" 17Kap2Boo")) {
    		bootes[2] = x;
    		bootes[3] = y;
    	}
    	if(name.contains(" 19Lam Boo")) {
    		bootes[4] = x;
    		bootes[5] = y;
    	}
    	if(name.contains(" 27Gam Boo")) {
    		bootes[6] = x;
    		bootes[7] = y;
    	}
    	if(name.contains(" 25Rho Boo")) {
    		bootes[8] = x;
    		bootes[9] = y;
    	}
    	if(name.contains("Arcturus")) {
    		bootes[10] = x;
    		bootes[11] = y;
    	}
    	if(name.contains("  8Eta Boo")) {
    		bootes[12] = x;
    		bootes[13] = y;
    	}
    	if(name.contains(" 30Zet Boo")) {
    		bootes[14] = x;
    		bootes[15] = y;
    	}
    	if(name.contains(" 49Del Boo")) {
    		bootes[16] = x;
    		bootes[17] = y;
    	}
    	if(name.contains(" 42Bet Boo")) {
    		bootes[18] = x;
    		bootes[19] = y;
    	}
    	
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
    	if(name.contains("  5Alp1Cap")) {
    		capricornus[0] = x;
    		capricornus[1] = y;
    	}
    	if(name.contains(" 18Ome Cap")) {
    		capricornus[2] = x;
    		capricornus[3] = y;
    	}
    	if(name.contains(" 34Zet Cap")) {
    		capricornus[4] = x;
    		capricornus[5] = y;
    	}
    	if(name.contains(" 49Del Cap")) {
    		capricornus[6] = x;
    		capricornus[7] = y;
    	}
    	if(name.contains(" 23The Cap")) {
    		capricornus[8] = x;
    		capricornus[9] = y;
    	}
    	
    	
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
    	if(name.contains("   Pi  Cen")) {
    		centaurus[0] = x;
    		centaurus[1] = y;
    	}
    	if(name.contains("   Omi1Cen")) {
    		centaurus[2] = x;
    		centaurus[3] = y;
    	}
    	if(name.contains("   Del Cen")) {
    		centaurus[4] = x;
    		centaurus[5] = y;
    	}
    	if(name.contains("   Sig Cen")) {
    		centaurus[6] = x;
    		centaurus[7] = y;
    	}
    	if(name.contains("   Gam Cen")) {
    		centaurus[8] = x;
    		centaurus[9] = y;
    	}
    	if(name.contains("   Eps Cen")) {
    		centaurus[10] = x;
    		centaurus[11] = y;
    	}
    	if(name.contains("Hadar")) {
    		centaurus[12] = x;
    		centaurus[13] = y;
    	}
    	if(name.contains("   Zet Cen")) {
    		centaurus[14] = x;
    		centaurus[15] = y;
    	}
    	if(name.contains("   Nu  Cen")) {
    		centaurus[16] = x;
    		centaurus[17] = y;
    	}
    	if(name.contains("   Iot Cen")) {
    		centaurus[18] = x;
    		centaurus[19] = y;
    	}
    	if(name.contains("  5The Cen")) {
    		centaurus[20] = x;
    		centaurus[21] = y;
    	}
    	if(name.contains("   Psi Cen")) {
    		centaurus[22] = x;
    		centaurus[23] = y;
    	}
    	if(name.contains("   Phi Cen")) {
    		centaurus[24] = x;
    		centaurus[25] = y;
    	}
    	if(name.contains("   Kap Cen")) {
    		centaurus[26] = x;
    		centaurus[27] = y;
    	}
    	if(name.contains("   Ups1Cen")) {
    		centaurus[28] = x;
    		centaurus[29] = y;
    	}
    	
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
    	if(name.contains("Deneb")) {
    		cygnus[0] = x;
    		cygnus[1] = y;
    	}
    	if(name.contains("  7Iot1Cyg")) {
    		cygnus[2] = x;
    		cygnus[3] = y;
    	}
    	if(name.contains(" 18Del Cyg")) {
    		cygnus[4] = x;
    		cygnus[5] = y;
    	}
    	if(name.contains(" 37Gam Cyg")) {
    		cygnus[6] = x;
    		cygnus[7] = y;
    	}
    	if(name.contains("  6Bet1Cyg")) {
    		cygnus[8] = x;
    		cygnus[9] = y;
    	}
    	if(name.contains(" 53Eps Cyg")) {
    		cygnus[10] = x;
    		cygnus[11] = y;
    	}
    	if(name.contains(" 64Zet Cyg")) {
    		cygnus[12] = x;
    		cygnus[13] = y;
    	}
    	
    }
    
    public void loadDracoLocation(String name, int x, int y) {
    	if(name.contains("  1Lam Dra")) {
    		draco[0] = x;
    		draco[1] = y;
    	}
    	if(name.contains("  5Kap Dra")) {
    		draco[2] = x;
    		draco[3] = y;
    	}
    	if(name.contains(" 11Alp Dra")) {
    		draco[4] = x;
    		draco[5] = y;
    	}
    	if(name.contains(" 12Iot Dra")) {
    		draco[6] = x;
    		draco[7] = y;
    	}
    	if(name.contains(" 13The Dra")) {
    		draco[8] = x;
    		draco[9] = y;
    	}
    	if(name.contains(" 22Zet Dra")) {
    		draco[10] = x;
    		draco[11] = y;
    	}
    	if(name.contains(" 44Chi Dra")) {
    		draco[12] = x;
    		draco[13] = y;
    	}
    	if(name.contains(" 43Phi Dra")) {
    		draco[14] = x;
    		draco[15] = y;
    	}
    	if(name.contains(" 63Eps Dra")) {
    		draco[16] = x;
    		draco[17] = y;
    	}
    	if(name.contains(" 57Del Dra")) {
    		draco[18] = x;
    		draco[19] = y;
    	}
    	if(name.contains(" 32Xi  Dra")) {
    		draco[20] = x;
    		draco[21] = y;
    	}
    	if(name.contains(" 24Nu 1Dra")) {
    		draco[22] = x;
    		draco[23] = y;
    	}
    	if(name.contains(" 23Bet Dra")) {
    		draco[24] = x;
    		draco[25] = y;
    	}
    	if(name.contains("Etamin")) {
    		draco[26] = x;
    		draco[27] = y;
    	}
    	
    }
    public void loadGeminiLocation(String name, int x, int y) {
    	if(name.contains("Pollux")) {
    		gemini[0] = x;
    		gemini[1] = y;
    	}
    	if(name.contains(" 69Ups Gem")) {
    		gemini[2] = x;
    		gemini[3] = y;
    	}
    	if(name.contains(" 77Kap Gem")) {
    		gemini[4] = x;
    		gemini[5] = y;
    	}
    	if(name.contains(" 46Tau Gem")) {
    		gemini[6] = x;
    		gemini[7] = y;
    	}
    	if(name.contains("Castor")) {
    		gemini[8] = x;
    		gemini[9] = y;
    	}
    	if(name.contains(" 34The Gem")) {
    		gemini[10] = x;
    		gemini[11] = y;
    	}
    	if(name.contains(" 27Eps Gem")) {
    		gemini[12] = x;
    		gemini[13] = y;
    	}
    	if(name.contains(" 13Mu  Gem")) {
    		gemini[14] = x;
    		gemini[15] = y;
    	}
    	if(name.contains(" 18Nu  Gem")) {
    		gemini[16] = x;
    		gemini[17] = y;
    	}
    	if(name.contains(" 55Del Gem")) {
    		gemini[18] = x;
    		gemini[19] = y;
    	}
    	if(name.contains(" 54Lam Gem")) {
    		gemini[20] = x;
    		gemini[21] = y;
    	}
    	if(name.contains(" 31Xi  Gem")) {
    		gemini[22] = x;
    		gemini[23] = y;
    	}
    	if(name.contains("Alhena")) {
    		gemini[24] = x;
    		gemini[25] = y;
    	}
    	
    }
    
    public void loadHydraLocation(String name, int x, int y) {
    	if(name.contains(" 49Pi  Hya")) {
    		hydra[0] = x;
    		hydra[1] = y;	
    	}
    	if(name.contains(" 46Gam Hya")) {
    		hydra[2] = x;
    		hydra[3] = y;	
    	}
    	if(name.contains("   Bet Hya")) {
    		hydra[4] = x;
    		hydra[5] = y;	
    	}
    	if(name.contains("   Xi  Hya")) {
    		hydra[6] = x;
    		hydra[7] = y;	
    	}
    	if(name.contains("   Nu  Hya")) {
    		hydra[8] = x;
    		hydra[9] = y;	
    	}
    	if(name.contains(" 42Mu  Hya")) {
    		hydra[10] = x;
    		hydra[11] = y;	
    	}
    	if(name.contains(" 41Lam Hya")) {
    		hydra[12] = x;
    		hydra[13] = y;	
    	}
    	if(name.contains(" 39Ups1Hya")) {
    		hydra[14] = x;
    		hydra[15] = y;	
    	}
    	if(name.contains("Alphard")) {
    		hydra[16] = x;
    		hydra[17] = y;	
    	}
    	if(name.contains(" 35Iot Hya")) {
    		hydra[18] = x;
    		hydra[19] = y;	
    	}
    	if(name.contains(" 11Eps Hya")) {
    		hydra[20] = x;
    		hydra[21] = y;	
    	}
    	if(name.contains("  4Del Hya")) {
    		hydra[22] = x;
    		hydra[23] = y;	
    	}
    	if(name.contains("  5Sig Hya")) {
    		hydra[24] = x;
    		hydra[25] = y;	
    	}
    	if(name.contains("  7Eta Hya")) {
    		hydra[26] = x;
    		hydra[27] = y;	
    	}
    	
    }
    public void loadLeoLocation(String name, int x, int y) {
    	if(name.contains("  1Kap Leo")) {
    		leo[0] = x;
    		leo[1] = y;
    	}
    	if(name.contains(" 24Mu  Leo")) {
    		leo[2] = x;
    		leo[3] = y;
    	}
    	if(name.contains("  4Lam Leo")) {
    		leo[4] = x;
    		leo[5] = y;
    	}
    	if(name.contains(" 17Eps Leo")) {
    		leo[6] = x;
    		leo[7] = y;
    	}
    	if(name.contains(" 36Zet Leo")) {
    		leo[8] = x;
    		leo[9] = y;
    	}
    	if(name.contains("Algieba")) {
    		leo[10] = x;
    		leo[11] = y;
    	}
    	if(name.contains(" 30Eta Leo")) {
    		leo[12] = x;
    		leo[13] = y;
    	}
    	if(name.contains("Regulus")) {
    		leo[14] = x;
    		leo[15] = y;
    	}
    	if(name.contains(" 70The Leo")) {
    		leo[16] = x;
    		leo[17] = y;
    	}
    	if(name.contains(" 68Del Leo")) {
    		leo[18] = x;
    		leo[19] = y;
    	}
    	if(name.contains("Denebola")) {
    		leo[20] = x;
    		leo[21] = y;
    	}
    	if(name.contains(" 78Iot Leo")) {
    		leo[22] = x;
    		leo[23] = y;
    	}
    	if(name.contains(" 77Sig Leo")) {
    		leo[24] = x;
    		leo[25] = y;
    	}
    	
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
    	if(name.contains("  9Omi2Ori")) {
    		orion[0] = x;
    		orion[1] = y;
    		
    	}
    	if(name.contains("  7Pi 1Ori")) {
    		orion[2] = x;
    		orion[3] = y;
    		
    	}
    	if(name.contains("  1Pi 3Ori")) {
    		orion[4] = x;
    		orion[5] = y;
    		
    	}
    	if(name.contains("  3Pi 4Ori")) {
    		orion[6] = x;
    		orion[7] = y;
    		
    	}
    	if(name.contains("  8Pi 5Ori")) {
    		orion[8] = x;
    		orion[9] = y;
    		
    	}
    	if(name.contains(" 10Pi 6Ori")) {
    		orion[10] = x;
    		orion[11] = y;
    		
    	}
    	if(name.contains("Bellatrix")) {
    		orion[12] = x;
    		orion[13] = y;
    		
    	}
    	if(name.contains(" 39Lam Ori")) {
    		orion[14] = x;
    		orion[15] = y;
    		
    	}
    	if(name.contains("Betelgeuse")) {
    		orion[16] = x;
    		orion[17] = y;
    		
    	}
    	if(name.contains(" 70Xi  Ori")) {
    		orion[18] = x;
    		orion[19] = y;
    		
    	}
    	if(name.contains(" 54Chi1Ori")) {
    		orion[20] = x;
    		orion[21] = y;
    		
    	}
    	if(name.contains(" 62Chi2Ori")) {
    		orion[22] = x;
    		orion[23] = y;
    		
    	}
    	if(name.contains("Alnitak")) {
    		orion[24] = x;
    		orion[25] = y;
    		
    	}
    	if(name.contains(" 34Del Ori")) {
    		orion[26] = x;
    		orion[27] = y;
    		
    	}
    	if(name.contains("Rigel")) {
    		orion[28] = x;
    		orion[29] = y;
    		
    	}
    	if(name.contains("Saiph")) {
    		orion[30] = x;
    		orion[31] = y;
    		
    	}
    	
    }
    
    public void loadPegasusLocation(String name, int x, int y) {
    	if(name.contains("  8Eps Peg")) {
    		pegasus[0] = x;
    		pegasus[1] = y;
    	}
    	if(name.contains(" 26The Peg")) {
    		pegasus[2] = x;
    		pegasus[3] = y;
    	}
    	if(name.contains("Markab")) {
    		pegasus[4] = x;
    		pegasus[5] = y;
    	}
    	if(name.contains("Algenib")) {
    		pegasus[6] = x;
    		pegasus[7] = y;
    	}
    	if(name.contains("Scheat")) {
    		pegasus[8] = x;
    		pegasus[9] = y;
    	}
    	if(name.contains(" 27Pi 1Peg")) {
    		pegasus[10] = x;
    		pegasus[11] = y;
    	}
    	if(name.contains(" 47Lam Peg")) {
    		pegasus[12] = x;
    		pegasus[13] = y;
    	}
    	if(name.contains(" 10Kap Peg")) {
    		pegasus[14] = x;
    		pegasus[15] = y;
    	}
    	
    }
    public void loadPerseusLocation(String name, int x, int y) {
    	if(name.contains(" 15Eta Per")) {
    		perseus[0] = x;
    		perseus[1] = y;
    	}
    	if(name.contains(" 18Tau Per")) {
    		perseus[2] = x;
    		perseus[3] = y;
    	}
    	if(name.contains(" 13The Per")) {
    		perseus[4] = x;
    		perseus[5] = y;
    	}
    	if(name.contains("   Iot Per")) {
    		perseus[6] = x;
    		perseus[7] = y;
    	}
    	if(name.contains("Algol")) {
    		perseus[8] = x;
    		perseus[9] = y;
    	}
    	if(name.contains(" 25Rho Per")) {
    		perseus[10] = x;
    		perseus[11] = y;
    	}
    	if(name.contains(" 38Omi Per")) {
    		perseus[12] = x;
    		perseus[13] = y;
    	}
    	if(name.contains(" 44Zet Per")) {
    		perseus[14] = x;
    		perseus[15] = y;
    	}
    	if(name.contains(" 46Xi  Per")) {
    		perseus[16] = x;
    		perseus[17] = y;
    	}
    	if(name.contains(" 45Eps Per")) {
    		perseus[18] = x;
    		perseus[19] = y;
    	}
    	if(name.contains(" 39Del Per")) {
    		perseus[20] = x;
    		perseus[21] = y;
    	}
    	if(name.contains(" 51Mu  Per")) {
    		perseus[22] = x;
    		perseus[23] = y;
    	}
    	if(name.contains("Mirphak")||name.contains("Mirfak")) {
    		perseus[24] = x;
    		perseus[25] = y;
    	}
    	if(name.contains(" 23Gam Per")) {
    		perseus[26] = x;
    		perseus[27] = y;
    	}
    	
    }
    public void loadPiscesLocation(String name, int x, int y) {
    	if(name.contains(" 99Eta Psc")) {
    		pisces[0] = x;
    		pisces[1] = y;	
    	}
    	if(name.contains("113Alp Psc")) {
    		pisces[2] = x;
    		pisces[3] = y;	
    	}
    	if(name.contains(" 17Iot Psc")) {
    		pisces[4] = x;
    		pisces[5] = y;	
    	}
    	if(name.contains(" 10The Psc")) {
    		pisces[6] = x;
    		pisces[7] = y;	
    	}
    	if(name.contains("  6Gam Psc")) {
    		pisces[8] = x;
    		pisces[9] = y;	
    	}
    	if(name.contains("  8Kap Psc")) {
    		pisces[10] = x;
    		pisces[11] = y;	
    	}
    	if(name.contains(" 18Lam Psc")) {
    		pisces[12] = x;
    		pisces[13] = y;	
    	}
    	if(name.contains(" 85Phi Psc")) {
    		pisces[14] = x;
    		pisces[15] = y;	
    	}
    	if(name.contains(" 83Tau Psc")) {
    		pisces[16] = x;
    		pisces[17] = y;	
    	}
    	if(name.contains(" 90Ups Psc")) {
    		pisces[18] = x;
    		pisces[19] = y;	
    	}
    	
    }
    public void loadSagittariusLocation(String name, int x, int y) {
    	if(name.contains(" 40Tau Sgr")) {
    		sagittarius[0] = x;
    		sagittarius[1] = y;
    		
    	}
    	if(name.contains(" 34Sig Sgr")) {
    		sagittarius[2] = x;
    		sagittarius[3] = y;
    		
    	}
    	if(name.contains(" 27Phi Sgr")) {
    		sagittarius[4] = x;
    		sagittarius[5] = y;
    		
    	}
    	if(name.contains(" 22Lam Sgr")) {
    		sagittarius[6] = x;
    		sagittarius[7] = y;
    		
    	}
    	if(name.contains(" 19Del Sgr")) {
    		sagittarius[8] = x;
    		sagittarius[9] = y;
    		
    	}
    	if(name.contains(" 10Gam2Sgr")) {
    		sagittarius[10] = x;
    		sagittarius[11] = y;
    		
    	}
    	if(name.contains("Kaus Australis")) {
    		sagittarius[12] = x;
    		sagittarius[13] = y;
    		
    	}
    	if(name.contains(" 38Zet Sgr")) {
    		sagittarius[14] = x;
    		sagittarius[15] = y;
    		
    	}
    }
    public void loadScorpioLocation(String name, int x, int y) {
    	if(name.contains("Shaula")) {
    		scorpio[0] = x;
    		scorpio[1] = y;	
    	}
    	if(name.contains("  1Iot1Sco")) {
    		scorpio[2] = x;
    		scorpio[3] = y;	
    	}
    	if(name.contains("   The Sco")) {
    		scorpio[4] = x;
    		scorpio[5] = y;	
    	}
    	if(name.contains("   Zet1Sco")) {
    		scorpio[6] = x;
    		scorpio[7] = y;	
    	}
    	if(name.contains(" 26Eps Sco")) {
    		scorpio[8] = x;
    		scorpio[9] = y;	
    	}
    	if(name.contains(" 23Tau Sco")) {
    		scorpio[10] = x;
    		scorpio[11] = y;	
    	}
    	if(name.contains("Antares")) {
    		scorpio[12] = x;
    		scorpio[13] = y;	
    	}
    	if(name.contains("  7Del Sco")) {
    		scorpio[14] = x;
    		scorpio[15] = y;	
    	}
    	if(name.contains("  8Bet1Sco")) {
    		scorpio[16] = x;
    		scorpio[17] = y;	
    	}
    	if(name.contains("  6Pi  Sco")) {
    		scorpio[18] = x;
    		scorpio[19] = y;	
    	}
    	
    }
    public void loadTaurusLocation(String name, int x, int y) {
    	if(name.contains("Alnath")) {
    		taurus[0] = x;
    		taurus[1] = y;
    	}
    	if(name.contains(" 74Eps Tau")) {
    		taurus[2] = x;
    		taurus[3] = y;
    	}
    	if(name.contains(" 91Sig1Tau")) {
    		taurus[4] = x;
    		taurus[5] = y;
    	}
    	if(name.contains(" 77The1Tau")) {
    		taurus[6] = x;
    		taurus[7] = y;
    	}
    	if(name.contains(" 54Gam Tau")) {
    		taurus[8] = x;
    		taurus[9] = y;
    	}
    	if(name.contains(" 35Lam Tau")) {
    		taurus[10] = x;
    		taurus[11] = y;
    	}
    	if(name.contains("123Zet Tau")) {
    		taurus[12] = x;
    		taurus[13] = y;
    	}
    	if(name.contains(" 38Nu  Tau")) {
    		taurus[14] = x;
    		taurus[15] = y;
    	}
    	
    }

    
    //Load all the constellation names
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
    	aquariusNames.add("  2Eps Aqr");
    	aquariusNames.add(" 22Bet Aqr");
    	aquariusNames.add(" 33Iot Aqr");
    	aquariusNames.add(" 34Alp Aqr");
    	aquariusNames.add(" 52Pi  Aqr");
    	aquariusNames.add(" 55Zet Aqr");
    	aquariusNames.add(" 48Gam Aqr");
    	aquariusNames.add(" 43The Aqr");
    	aquariusNames.add(" 73Lam Aqr");
    	aquariusNames.add(" 90Phi Aqr");
    	aquariusNames.add(" 93Psi2Aqr");
    	aquariusNames.add(" 76Del Aqr");
    	aquariusNames.add(" 69Tau1Aqr");
    	
    	
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
    	bootesNames.add(" 23The Boo");
    	bootesNames.add(" 17Kap2Boo");
    	bootesNames.add(" 19Lam Boo");
    	bootesNames.add(" 27Gam Boo");
    	bootesNames.add(" 25Rho Boo");
    	bootesNames.add("Arcturus");
    	bootesNames.add("  8Eta Boo");
    	bootesNames.add(" 30Zet Boo");
    	bootesNames.add(" 49Del Boo");
    	bootesNames.add(" 42Bet Boo");
    	
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
    	capricornusNames.add("  5Alp1Cap");
    	capricornusNames.add(" 18Ome Cap");
    	capricornusNames.add(" 34Zet Cap");
    	capricornusNames.add(" 49Del Cap");
    	capricornusNames.add(" 23The Cap");
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
    	centaurusNames.add("   Pi  Cen");
    	centaurusNames.add("   Omi1Cen");
    	centaurusNames.add("   Del Cen");
    	centaurusNames.add("   Sig Cen");
    	centaurusNames.add("   Gam Cen");
    	centaurusNames.add("   Eps Cen");
    	centaurusNames.add("Hadar");
    	centaurusNames.add("   Zet Cen");
    	centaurusNames.add("   Nu  Cen");
    	centaurusNames.add("   Iot Cen");
    	centaurusNames.add("  5The Cen");
    	centaurusNames.add("   Psi Cen");
    	centaurusNames.add("   Phi Cen");
    	centaurusNames.add("   Kap Cen");
    	centaurusNames.add("   Ups1Cen");
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
    	cygnusNames.add("Deneb");
    	cygnusNames.add("  7Iot1Cyg");
    	cygnusNames.add(" 18Del Cyg");
    	cygnusNames.add(" 37Gam Cyg");
    	cygnusNames.add("  6Bet1Cyg");
    	cygnusNames.add(" 53Eps Cyg");
    	cygnusNames.add(" 64Zet Cyg");
    }
    public void loadDracoNames() {
    	dracoNames = new ArrayList<>();
    	dracoNames.add("  1Lam Dra");
    	dracoNames.add("  5Kap Dra");
    	dracoNames.add(" 11Alp Dra");
    	dracoNames.add(" 12Iot Dra");
    	dracoNames.add(" 13The Dra");
    	dracoNames.add(" 32Xi  Dra");
    	dracoNames.add(" 44Chi Dra");
    	dracoNames.add(" 43Phi Dra");
    	dracoNames.add(" 63Eps Dra");
    	dracoNames.add(" 57Del Dra");
    	dracoNames.add(" 22Zet Dra");
    	dracoNames.add(" 24Nu 1Dra");
    	dracoNames.add(" 23Bet Dra");
    	dracoNames.add("Etamin");
    	
    }
    public void loadGeminiNames() {
    	geminiNames = new ArrayList<>();
    	geminiNames.add("Pollux");
    	geminiNames.add(" 69Ups Gem");
    	geminiNames.add(" 77Kap Gem");
    	geminiNames.add(" 46Tau Gem");
    	geminiNames.add("Castor");
    	geminiNames.add(" 34The Gem");
    	geminiNames.add(" 27Eps Gem");
    	geminiNames.add(" 13Mu  Gem");
    	geminiNames.add(" 18Nu  Gem");
    	geminiNames.add(" 55Del Gem");
    	geminiNames.add(" 54Lam Gem");
    	geminiNames.add(" 31Xi  Gem");
    	geminiNames.add("Alhena");
    	
    }
    public void loadHydraNames() {
    	hydraNames = new ArrayList<>();
    	hydraNames.add(" 49Pi  Hya");
    	hydraNames.add(" 46Gam Hya");
    	hydraNames.add("   Bet Hya");
    	hydraNames.add("   Xi  Hya");
    	hydraNames.add("   Nu  Hya");
    	hydraNames.add(" 42Mu  Hya");
    	hydraNames.add(" 41Lam Hya");
    	hydraNames.add(" 39Ups1Hya");
    	hydraNames.add("Alphard");
    	hydraNames.add(" 35Iot Hya");
    	hydraNames.add(" 11Eps Hya");
    	hydraNames.add("  4Del Hya");
    	hydraNames.add("  5Sig Hya");
    	hydraNames.add("  7Eta Hya");
    	
    }
    public void loadLeoNames() {
    	leoNames = new ArrayList<>();
    	leoNames.add("  1Kap Leo");
    	leoNames.add(" 24Mu  Leo");
    	leoNames.add("  4Lam Leo");
    	leoNames.add(" 17Eps Leo");
    	leoNames.add(" 36Zet Leo");
    	leoNames.add("Algieba");
    	leoNames.add(" 30Eta Leo");
    	leoNames.add("Regulus");
    	leoNames.add(" 70The Leo");
    	leoNames.add(" 68Del Leo");
    	leoNames.add("Denebola");
    	leoNames.add(" 78Iot Leo");
    	leoNames.add(" 77Sig Leo");
    	
    	
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
    	orionNames.add("  9Omi2Ori");
    	orionNames.add("  7Pi 1Ori");
    	orionNames.add("  1Pi 3Ori");
    	orionNames.add("  3Pi 4Ori");
    	orionNames.add("  8Pi 5Ori");
    	orionNames.add(" 10Pi 6Ori");
    	orionNames.add("Bellatrix");
    	orionNames.add(" 39Lam Ori");
    	orionNames.add("Betelgeuse");
    	orionNames.add(" 70Xi  Ori");
    	orionNames.add(" 54Chi1Ori");
    	orionNames.add(" 62Chi2Ori");
    	orionNames.add("Alnitak");
    	orionNames.add(" 34Del Ori");
    	orionNames.add("Rigel");
    	orionNames.add("Saiph");
    	
    }
    public void loadPegasusNames() {
    	pegasusNames = new ArrayList<>();
    	pegasusNames.add("  8Eps Peg");
    	pegasusNames.add(" 26The Peg");
    	pegasusNames.add("Markab");
    	pegasusNames.add("Algenib");
    	pegasusNames.add("Scheat");
    	pegasusNames.add(" 27Pi 1Peg");
    	pegasusNames.add(" 47Lam Peg");
    	pegasusNames.add(" 10Kap Peg");
    	
    	
    }
    public void loadPerseusNames() {
    	perseusNames = new ArrayList<>();
    	perseusNames.add(" 15Eta Per");
    	perseusNames.add(" 18Tau Per");
    	perseusNames.add(" 13The Per");
    	perseusNames.add("   Iot Per");
    	perseusNames.add("Algol");
    	perseusNames.add(" 25Rho Per");
    	perseusNames.add(" 38Omi Per");
    	perseusNames.add(" 44Zet Per");
    	perseusNames.add(" 46Xi  Per");
    	perseusNames.add(" 45Eps Per");
    	perseusNames.add(" 39Del Per");
    	perseusNames.add(" 51Mu  Per");
    	perseusNames.add("Mirphak");
    	perseusNames.add("Mirfak");
    	perseusNames.add(" 23Gam Per");
    	
    }
    public void loadPiscesNames() {
    	piscesNames = new ArrayList<>();
    	piscesNames.add(" 99Eta Psc");
    	piscesNames.add("113Alp Psc");
    	piscesNames.add(" 17Iot Psc");
    	piscesNames.add(" 10The Psc");
    	piscesNames.add("  6Gam Psc");
    	piscesNames.add("  8Kap Psc");
    	piscesNames.add(" 18Lam Psc");
    	piscesNames.add(" 85Phi Psc");
    	piscesNames.add(" 83Tau Psc");
    	piscesNames.add(" 90Ups Psc");
    	
    }
    public void loadSagittariusNames() {
    	sagittariusNames = new ArrayList<>();
    	sagittariusNames.add(" 40Tau Sgr");
    	sagittariusNames.add(" 34Sig Sgr");
    	sagittariusNames.add(" 27Phi Sgr");
    	sagittariusNames.add(" 22Lam Sgr");
    	sagittariusNames.add(" 19Del Sgr");
    	sagittariusNames.add(" 10Gam2Sgr");
    	sagittariusNames.add("Kaus Australis");
    	sagittariusNames.add(" 38Zet Sgr");
    	
    	
    }
    public void loadScorpioNames() {
    	scorpioNames = new ArrayList<>();
    	scorpioNames.add("Shaula");
    	scorpioNames.add("  1Iot1Sco");
    	scorpioNames.add("   The Sco");
    	scorpioNames.add("   Zet1Sco");
    	scorpioNames.add(" 26Eps Sco");
    	scorpioNames.add(" 23Tau Sco");
    	scorpioNames.add("Antares");
    	scorpioNames.add("  7Del Sco");
    	scorpioNames.add("  8Bet1Sco");
    	scorpioNames.add("  6Pi  Sco");
    	
    }
    public void loadTaurusNames() {
    	taurusNames = new ArrayList<>();
    	taurusNames.add("Alnath");
    	taurusNames.add(" 74Eps Tau");
    	taurusNames.add(" 91Sig1Tau");
    	taurusNames.add(" 77The1Tau");
    	taurusNames.add(" 54Gam Tau");
    	taurusNames.add(" 35Lam Tau");
    	taurusNames.add("123Zet Tau");
    	taurusNames.add(" 38Nu  Tau");
    }
    
    
    //Check if all stars in a constellation are loaded
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
        	Boolean flag = true;
        	for(int i = 0; i < 26; i++) {
        		if(aquarius[i] == 0) {
        			flag = false;
        		}
        	}
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
    	Boolean flag = true;
    	for(int i = 0; i < 20; i++) {
    		if(bootes[i] == 0) {
    			flag = false;
    		}
    	}
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
    	Boolean flag = true;
    	for(int i = 0; i < 10; i++) {
    		if(capricornus[i] == 0) {
    			flag = false;
    		}
    	}
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
    	Boolean flag = true;
    	for(int i = 0; i < 30; i++) {
    		if(centaurus[i] == 0) {
    			flag = false;
    		}
    	}
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
    	Boolean flag = true;
    	for(int i = 0; i < 14; i++) {
    		if(cygnus[i] == 0) {
    			flag = false;
    		}
    	}
    	return flag;
    }
    public Boolean checkDraco() {
    	Boolean flag = true;
    	for(int i = 0; i < 28; i++) {
    		if(draco[i] == 0) {
    			flag = false;
    		}
    	}
    	return flag;
    }
    public Boolean checkGemini() {
    	Boolean flag = true;
    	for(int i = 0; i < 26; i++) {
    		if(gemini[i] == 0) {
    			flag = false;
    		}
    	}
    	return flag;
    }
    public Boolean checkHydra() {
    	Boolean flag = true;
    	for(int i = 0; i < 28; i++) {
    		if(hydra[i] == 0) {
    			flag = false;
    		}
    	}
    	return flag;
    }
    public Boolean checkLeo() {
    	Boolean flag = true;
    	for(int i = 0; i < 26; i++) {
    		if(leo[i] == 0) {
    			flag = false;
    		}
    	}
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
    	Boolean flag = true;
    	for(int i = 0; i < 32; i++) {
    		if(orion[i] == 0) {
    			flag = false;
    		}
    	}
    	return flag;
    }
    public Boolean checkPegasus() {
    	Boolean flag = true;
    	for(int i = 0; i < 16; i++) {
    		if(pegasus[i] == 0) {
    			flag = false;
    		}
    	}
    	return flag;
    }
    public Boolean checkPerseus() {
      	Boolean flag = true;
    	for(int i = 0; i < 28; i++) {
    		if(perseus[i] == 0) {
    			flag = false;
    		}
    	}
    	return flag;
    }
    public Boolean checkPisces() {
    	Boolean flag = true;
    	for(int i = 0; i < 20; i++) {
    		if(pisces[i] == 0) {
    			flag = false;
    		}
    	}
    	return flag;
    }
    public Boolean checkSagittarius() {
    	Boolean flag = true;
    	for(int i = 0; i < 16; i++) {
    		if(sagittarius[i] == 0) {
    			flag = false;
    		}
    	}
    	return flag;
    }
    public Boolean checkScorpio() {
    	Boolean flag = true;
    	for(int i = 0; i < 20; i++) {
    		if(scorpio[i] == 0) {
    			flag = false;
    		}
    	}
    	return flag;
    }    
    public Boolean checkTaurus() {
    	Boolean flag = true;
    	for(int i = 0; i < 16; i++) {
    		if(taurus[i] == 0) {
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