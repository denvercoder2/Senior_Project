package JohnWork.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/*
 * A lot of this, almost entirely, is derived from Scott's ParseCreateStars
 * It has a really good baseline, but work and functions were included in an
 * object, and to neatly separate it I split his .java into SpaceObj and SpaceObjListBuilder
 */


public class SpaceObj {
    private String starId;
    private String Hip;
    private String HD;
    private String HR;
    private String Gliese;
    private String BayerFlamsteed;
    private String ProperName;
    private String RA;
    private String Dec;
    private String Distance;
    private String Magnitude;
    private String AbsMag;
    private String Spectrum;
    private String ColorIndex;
    private double altitude;
    private double azimuth;
	
	public SpaceObj() {
		
	}
	
    // getters and setters if we want to use them later
    public void setAzimuth(double Azimuth){
        this.azimuth = Azimuth;
    }
    public double getAzimuth(){
        return this.azimuth;
    }
    public void setAltitude(double Altitude){
        this.altitude = Altitude;
    }
    public double getAltitude(){
        return this.altitude;
    }
    public void setStarID(String starID){
        this.starId = starID;
    }
    public String getStarID(){
        return this.starId;
    }
    public void setHip(String Hip){
        this.Hip = Hip;
    }
    public String getHip(){
        return this.Hip;
    }
    public void setHD(String HD){
        this.HD = HD;
    }
    public String getHD(){
        return this.HD;
    }
    public void setHR(String HR){
        this.HR = HR;
    }
    public String getHR(){
        return this.HR;
    }
    public void setGliese(String Gliese){
        this.Gliese = Gliese;
    }
    public String getGliese(){
        return this.Gliese;
    }
    public void setBayerFlamsteed(String BayerFlamsteed){
        this.BayerFlamsteed = BayerFlamsteed;
    }
    public String getBayerFlamsteed(){
        return this.BayerFlamsteed;
    }
    public void setProperName(String ProperName){
        this.ProperName = ProperName;
    }
    public String getProperName(){
        return this.ProperName;
    }
    public void setRA(String RA){
        this.RA = RA;
    }
    public String getRA(){
        return this.RA;
    }
    public void setDec(String Dec){
        this.Dec = Dec;
    }
    public String getDec(){
        return this.Dec;
    }
    public void setDistance(String Distance){
        this.Distance = Distance;
    }
    public String getDistance(){
        return this.Distance;
    }
    public void setMagnitude(String Magnitude){
        this.Magnitude = Magnitude;
    }
    public String getMagnitude(){
        return this.Magnitude;
    }
    public void setAbsMag(String AbsMag){
        this.AbsMag = AbsMag;
    }
    public String getAbsMag(){
        return this.AbsMag;
    }
    public void setSpectrum(String Spectrum){
        this.Spectrum = Spectrum;
    }
    public String getSpectrum(){
        return this.Spectrum;
    }
    public void setColorIndex(String ColorIndex){
        this.ColorIndex = ColorIndex;
    }
    public String getColorIndex(){
        return this.ColorIndex;
    }
    
    public void solveOwnLocation() {
    	this.solvingOwnLocation();
    }
    
    public void solveOwnLocation(Date chosenDate) {
    	this.solvingOwnLocation(chosenDate);
    }
    
    public void solveOwnLocation(double lat, double lon) {
    	this.solvingOwnLocation(lat, lon);
    }
    
    public void solveOwnLocation(double lat, double lon, Date chosenDate) {
    	this.solvingOwnLocation(lat, lon, chosenDate);
    }
    
    private void solvingOwnLocation() {
    	
    }
    
    private void solvingOwnLocation(Date chosenDate) {
    	
    }
    
    private void solvingOwnLocation(double lat, double lon) {
    	
    }
    
    private void solvingOwnLocation(double lat, double lon, Date chosenDate) {
    	
    }
}
