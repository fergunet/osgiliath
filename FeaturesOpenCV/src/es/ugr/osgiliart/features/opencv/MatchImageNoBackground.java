/*
 * MatchImageNoBackground.java
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

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

public class MatchImageNoBackground implements Feature {
	
	static public int SIZE = 100;
	static public int PENALTY = 50000;
	
	Mat templateResized;
	
	public MatchImageNoBackground (String templatePath){
		Mat template = Highgui.imread(templatePath);
		templateResized = new Mat (SIZE,SIZE, template.type());
		
		Imgproc.resize(template, templateResized, new Size(SIZE,SIZE));		
	}
	
	/*
	 * Fitness function
	 * If the pixel is white, take the penalty
	 * if not, Euclidean distance squared
	 */
	
	public double match (String path){
		
		Mat img = Highgui.imread(path);
		Mat imgResized = new Mat (SIZE,SIZE, img.type());
		Imgproc.resize(img, imgResized, new Size(SIZE,SIZE));
		
		double distance = 0;
		
		
		for (int i = 0; i < SIZE; i++){
			for (int j = 0; j < SIZE; j++){
				if ((imgResized.get(i, j)[0] == 255) &&  
					(imgResized.get(i, j)[1] == 255) &&
					(imgResized.get(i, j)[2] == 255)){
					
					distance += PENALTY;
				}
				else{
					distance += Math.pow (imgResized.get(i, j)[0] - templateResized.get(i, j)[0], 2) +
								Math.pow (imgResized.get(i, j)[1] - templateResized.get(i, j)[1], 2) +
								Math.pow (imgResized.get(i, j)[2] - templateResized.get(i, j)[2], 2);
				}
			}
		}

		return -distance;
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
