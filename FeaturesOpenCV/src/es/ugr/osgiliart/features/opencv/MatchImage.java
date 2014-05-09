/*
 * MatchImage.java
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
import java.util.Collections;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.features2d.Features2d;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;


public class MatchImage  implements Feature {

	static public int SIZE = 100;
	static public int FILTER_SIZE = 3;
	


	List<Mat> templateChannels;
		
	public MatchImage ( String templatePath ) {
		Mat template = Highgui.imread(templatePath);	
		Mat resized = new Mat(SIZE, SIZE,  template.type());
		//Mat blurred = new Mat();
		Imgproc.resize(template, resized, new Size(SIZE,SIZE));
		//Imgproc.blur(resized, blurred, new Size(FILTER_SIZE,FILTER_SIZE) );
		templateChannels = new ArrayList<Mat>();
		Core.split(resized, templateChannels);		
	}
	 
	public double match ( String path ) {
		Mat img = Highgui.imread(path);	
		Mat resizedImg = new Mat(SIZE, SIZE, img.type());
		//Mat blurredImg = new Mat();
		Imgproc.resize(img, resizedImg, new Size(SIZE,SIZE));
		//Imgproc.blur(resizedImg, blurredImg, new Size(FILTER_SIZE,FILTER_SIZE) );
		
		ArrayList<Mat> channels = new ArrayList<Mat>();
			
		Core.split(resizedImg, channels);
		
		int conta = 0;
		
		double corrcoef = 0;
		for ( int i = 0; i < 1; ++i ) {
	/*		
			for(int px = 0; px < SIZE; px++){
				for(int py = 0; py < SIZE; py++){
					if(resizedImg.get(px, py)[i]!=0.0){
						double im_orig = templateChannels.get(i).get(px, py)[0];
						double im_indi = resizedImg.get(px, py)[i];
						
						corrcoef +=  Math.pow(im_orig ,2) - Math.pow(im_indi, 2);
						conta++;
					}
					
					
				}
			}*/
			
			
			
			Mat result = new Mat();			
			Imgproc.matchTemplate(channels.get(i), templateChannels.get(i), result, Imgproc.TM_CCOEFF_NORMED);
			//Imgproc.matchTemplate(channels.get(i), templateChannels.get(i), result, Imgproc.TM_SQDIFF);
			corrcoef += result.get(0, 0)[0];		
			//corrcoef += result.get(0, 0)[0];
		}
		corrcoef /= 3.0;
		//return (corrcoef/conta/(255*3));
		return (corrcoef);
	}
	
	
	@Override
	public double[] extract(String path) {
		Mat img = Highgui.imread(path);	
		return extract(img);
	}

	@Override
	public double[] extract(Mat image) {
		return null;
	}
	
	
}
