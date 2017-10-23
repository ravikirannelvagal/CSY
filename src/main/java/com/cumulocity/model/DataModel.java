package com.cumulocity.model;

public class DataModel {

	//private String type;
	private String time;
	private int srcValue;
	private double zDirection;
	private double yDirection;
	private double xDirection;
	private int gpsState;
	private int gpsSpeed;
	private double bearing;
	private double longitude;
	private double latitude;
	private int engineState;
	private int vehSpeed;
	private int rpm;
	private int manifoldAbsPressure;
	private int intakeAirTemp;
	private double massAirFlowRate;
	public static final int NO_OF_PARAMS = 16;
	
	public DataModel(String inp){
		String [] fields = inp.split("@");
		
		this.time=fields[1];
		this.srcValue=Integer.parseInt(fields[2]);
		this.zDirection=Double.parseDouble(fields[3]);
		this.yDirection=Double.parseDouble(fields[4]);
		this.xDirection=Double.parseDouble(fields[5]);
		this.gpsState=Integer.parseInt(fields[6]);
		this.gpsSpeed=Integer.parseInt(fields[7]);
		this.bearing=Double.parseDouble(fields[8]);
		this.longitude=Double.parseDouble(fields[9]);
		this.latitude=Double.parseDouble(fields[10]);
		this.engineState=Integer.parseInt(fields[11]);
		this.vehSpeed=Integer.parseInt(fields[12]);
		this.rpm=Integer.parseInt(fields[13]);
		this.manifoldAbsPressure=Integer.parseInt(fields[14]);
		this.intakeAirTemp=Integer.parseInt(fields[15]);
		this.massAirFlowRate=Double.parseDouble(fields[16]);
	}
	/*public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}*/
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getSrcValue() {
		return srcValue;
	}
	public void setSrcValue(int srcValue) {
		this.srcValue = srcValue;
	}
	public double getzDirection() {
		return zDirection;
	}
	public void setzDirection(double zDirection) {
		this.zDirection = zDirection;
	}
	public double getyDirection() {
		return yDirection;
	}
	public void setyDirection(double yDirection) {
		this.yDirection = yDirection;
	}
	public double getxDirection() {
		return xDirection;
	}
	public void setxDirection(double xDirection) {
		this.xDirection = xDirection;
	}
	public int getGpsState() {
		return gpsState;
	}
	public void setGpsState(int gpsState) {
		this.gpsState = gpsState;
	}
	public int getGpsSpeed() {
		return gpsSpeed;
	}
	public void setGpsSpeed(int gpsSpeed) {
		this.gpsSpeed = gpsSpeed;
	}
	public double getBearing() {
		return bearing;
	}
	public void setBearing(double bearing) {
		this.bearing = bearing;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public int getEngineState() {
		return engineState;
	}
	public void setEngineState(int engineState) {
		this.engineState = engineState;
	}
	public int getVehSpeed() {
		return vehSpeed;
	}
	public void setVehSpeed(int vehSpeed) {
		this.vehSpeed = vehSpeed;
	}
	public int getRpm() {
		return rpm;
	}
	public void setRpm(int rpm) {
		this.rpm = rpm;
	}
	public int getManifoldAbsPressure() {
		return manifoldAbsPressure;
	}
	public void setManifoldAbsPressure(int manifoldAbsPressure) {
		this.manifoldAbsPressure = manifoldAbsPressure;
	}
	public int getIntakeAirTemp() {
		return intakeAirTemp;
	}
	public void setIntakeAirTemp(int intakeAirTemp) {
		this.intakeAirTemp = intakeAirTemp;
	}
	public double getMassAirFlowRate() {
		return massAirFlowRate;
	}
	public void setMassAirFlowRate(double massAirFlowRate) {
		this.massAirFlowRate = massAirFlowRate;
	}
	
	
}
