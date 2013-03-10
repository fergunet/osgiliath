package es.ugr.osgiliath.noosgi;

import es.ugr.osgiliart.Histogram;
import es.ugr.osgiliart.fitnesscalculators.ArtisticHistogramFitnessCalculator;

public class HistogramTest {
	public static void main(String[] args){
		String filename = "/Users/anabpel/Documents/workspace/osgilath/osgiliath/OSGiLiART/scripts/yo.jpg";
		Histogram.Test(filename);
		
		
		System.out.println("\n-----------------------------");
		
		/*filename = "/Users/anabpel/Documents/workspace/osgilath/osgiliath/OSGiLiART/scripts/yo copia.jpg";
		Histogram.Test(filename);*/
		ArtisticHistogramFitnessCalculator.Test();
		System.exit(0);
	}
}
