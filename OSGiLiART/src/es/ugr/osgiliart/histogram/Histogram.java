package es.ugr.osgiliart.histogram;

public class Histogram {
	public static final String HUE = "HUE";
	public static final String SATURATION = "SATURATION";
	public static final String BRIGHTNESS = "BRIGHTNESS";
	public static final String RED = "RED";
	public static final String GREEN = "GREEN";
	public static final String BLUE = "BLUE";

	private int type;
	
	private double[] values;
	
	public int getType(){
		return type;
	}
	
	
	public Histogram(int type){
		this.values = new double[256];

	}
	
	public double[] getValues(){
		return this.values;
	}
	
	public double getDifference(Histogram another){
		return 0;
	}
}
