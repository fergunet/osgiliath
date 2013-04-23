/*
 * IntelligentRandomManager.java
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

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

public class MatchHistogram implements Feature {
	
	static{ 
		System.load("/Users/dcalandria/hackathon/workspace/osgiliath-code/FeaturesOpenCV/libopencv_java245.dylib");
	}
	
	Histogram histogram = new Histogram();
	protected double[] templateHistogram ;
	
	public MatchHistogram (String templatePath) {
		Mat template = Highgui.imread(templatePath);	
		templateHistogram = histogram.extract(template);
		String s = "";
		for ( int i = 0; i < templateHistogram.length; ++i) {
			s += templateHistogram[i];
		}
	}
	
	@Override
	public double[] extract(String path) {
		Mat image = Highgui.imread(path);	
		return extract ( image );
	}

	@Override
	public double[] extract(Mat image) {
		double[] hist = histogram.extract(image);
		double distance = 0.0;
		for ( int i = 0; i < hist.length; ++i)
			distance += (hist[i] - templateHistogram[i])*(hist[i] - templateHistogram[i]);
		distance = 1.0/hist.length * Math.sqrt(distance);
		return new double[]{distance};
	}
}

