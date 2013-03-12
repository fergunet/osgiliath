package es.ugr.osgiliart.histogram;

public class Histogram {
	public static final String HUE = "HUE";
	public static final String SATURATION = "SATURATION";
	public static final String BRIGHTNESS = "BRIGHTNESS";
	public static final String RED = "RED";
	public static final String GREEN = "GREEN";
	public static final String BLUE = "BLUE";

	private String type;
	
	private double[] values;
	
	public String getType(){
		return type;
	}
	
	
	public Histogram(String type){
		this.values = new double[256];
		this.type = type;

	}
	
	public double[] getValues(){
		return this.values;
	}
	
	public double getDifference(Histogram another){

		
		double dif = 0;
		
		for (int i=0; i<256; i++){
			//System.out.println(h1[i]+" "+h2[i]);
			dif += Math.abs(this.values[i] - another.getValues()[i]);
		}
		
		return  dif/256;
	}
}
