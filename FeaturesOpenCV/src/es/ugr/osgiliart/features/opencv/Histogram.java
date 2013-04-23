/*
 * Histogram.java
 * 
 * Copyright (c) 2013, Pablo Garcia-Sanchez. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 * 
 * Contributors:
 * Daniel Calandria
 */
package es.ugr.osgiliart.features.opencv;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;


public class Histogram implements Feature {

	public static final int BINS = 32;
	public static final float MIN_VALUE = 0.0f;
	public static final float MAX_VALUE = 255.0f;
	
	
	static{ 
		//System.load("/home/dcalandria/5hackathon/eclipse/FeaturesOpenCV/libopencv_java244.so");		
		System.load("/Users/dcalandria/hackathon/workspace/osgiliath-code/FeaturesOpenCV/libopencv_java245.dylib");
	}

	/* generate a 32-bar histogram for each color channel */
	@Override
	public double[] extract(String path) {
		System.out.println("Path: " + path);	
		Mat image = Highgui.imread(path);
		
		return extract(image);
	}

	@Override
	public double[] extract(Mat image) {
		Mat hsvImage = new Mat(image.width(), image.height(), image.type());
		Mat histHue = new Mat();
		Mat histSaturation = new Mat();
		
		Imgproc.cvtColor(image, hsvImage, Imgproc.COLOR_BGR2HSV);
		List<Mat> channels = new ArrayList<Mat>();
		Core.split(hsvImage, channels);
		
		//Histogram for hue
		Imgproc.calcHist(Arrays.asList( new Mat[]{channels.get(0)} ), new MatOfInt(0), 
										new Mat(), histHue, new MatOfInt(BINS), new MatOfFloat(MIN_VALUE, MAX_VALUE));
		
		//Histogram for saturation
		Imgproc.calcHist(Arrays.asList( new Mat[]{channels.get(1)} ), new MatOfInt(0), 
				 						new Mat(), histSaturation, new MatOfInt(BINS), new MatOfFloat(MIN_VALUE, MAX_VALUE));

		
		double sum = Core.sumElems(histHue).val[0];
		double[] values = new double[histHue.height()+histSaturation.height()];
		int k = 0;
		for (int i = 0; i < histHue.height(); ++i ) {
			values[k++] = histHue.get(i, 0)[0]/sum;
		}
		sum = Core.sumElems(histSaturation).val[0];
		for ( int i = 0; i < histSaturation.height(); ++i) {
			values[k++] = histSaturation.get(i, 0)[0]/sum;
		}
		return values;
	}
}
