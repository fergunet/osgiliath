package es.ugr.osgiliath.problem.ndimfunctions;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import es.ugr.osgiliath.OsgiliathService;
import es.ugr.osgiliath.problem.ProblemParameters;

public class NdimFunctionProblemParameters extends OsgiliathService implements ProblemParameters {

	public static final String DIMENSIONS_PROP = "es.ugr.osgiliath.parameters.ndimfunction.dimensions";
	public static final String NUM_RANGES_PROP = "es.ugr.osgiliath.parameters.ndimfunction.ranges";
	public static final String RANGE_ID_PROP = "es.ugr.osgiliath.parameters.ndimfunction.ranges.id";
	
	private int dimensions;
	private List<Double> ranges;
	private boolean toMaximize;
	
	public void activate(){
		this.setup(null);
	}
	
	@Override
	public void setup(Properties props) {
		//TODO ARREGLAR ESTO
	
		if(props==null){
			System.out.println("No properties given: setting default ones");
			this.dimensions = 5;
			this.toMaximize = false;
			this.ranges = new ArrayList<Double>();
			this.ranges.add(new Double(-600));
			this.ranges.add(new Double( 600));
			return;
		}
		
		 String sDims = props.getProperty(DIMENSIONS_PROP);
		 this.dimensions = Integer.parseInt(sDims);
		 
		 
		 String sRanges = props.getProperty(NUM_RANGES_PROP);
		
		 if(sRanges!=null){
			 int nRanges = Integer.parseInt(sRanges);
			 ranges = new ArrayList<Double>();
			 for(int i = 0; i<nRanges;i++){
				 String rangeN = props.getProperty(RANGE_ID_PROP+"."+i);
				 Double rangeND = Double.parseDouble(rangeN);
				 this.ranges.add(rangeND);
			 }
				 
		 }
				 
		
	}
	
	public int getDimensions(){
		return this.dimensions;
	}
	
	public List<Double> ranges(){
		return this.ranges;
	}

	@Override
	public boolean toMaximize() {
		// TODO Auto-generated method stub
		return toMaximize;
	}
}


