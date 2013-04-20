package es.ugr.osgiliart.features.opencv;

import org.opencv.core.Mat;

public interface Feature {
	public double[] extract (String path);
	public double[] extract (Mat image);
}
