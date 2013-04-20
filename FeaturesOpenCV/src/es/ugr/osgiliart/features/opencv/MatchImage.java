package es.ugr.osgiliart.features.opencv;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.features2d.Features2d;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;


public class MatchImage {

	static public int SIZE = 50;
	static public int FILTER_SIZE = 3;
	
	static{ 
		//System.load("/home/dcalandria/5hackathon/eclipse/FeaturesOpenCV/libopencv_java244.so");		
		System.load("/Users/dcalandria/hackathon/workspace/osgiliath-code/FeaturesOpenCV/libopencv_java245.dylib");
	}

	
	List<Mat> templateChannels;
	
	
	public MatchImage ( String templatePath ) {
		Mat template = Highgui.imread(templatePath);	
		Mat resized = new Mat(SIZE, SIZE,  template.type());
		//Mat blurred = new Mat();
		Imgproc.resize(template, resized, new Size(SIZE,SIZE));
		//Imgproc.blur(resized, blurred, new Size(FILTER_SIZE,FILTER_SIZE) );
		templateChannels = new ArrayList<Mat>();
		templateChannels.add(new Mat());
		templateChannels.add(new Mat());
		templateChannels.add(new Mat());
		Core.split(resized, templateChannels);		
	}
	 
	public double match ( String path ) {
		Mat img = Highgui.imread(path);	
		Mat resizedImg = new Mat(SIZE, SIZE, img.type());
		//Mat blurredImg = new Mat();
		Imgproc.resize(img, resizedImg, new Size(SIZE,SIZE));
		//Imgproc.blur(resizedImg, blurredImg, new Size(FILTER_SIZE,FILTER_SIZE) );
		
		ArrayList<Mat> channels = new ArrayList<Mat>();
		channels.add(new Mat());
		channels.add(new Mat());
		channels.add(new Mat());
		Core.split(resizedImg, channels);
		
		double corrcoef = 0;
		for ( int i = 0; i < 3; ++i ) {
			Mat result = new Mat();			
			Imgproc.matchTemplate(channels.get(i), templateChannels.get(i), result, Imgproc.TM_CCOEFF_NORMED);
			corrcoef += result.get(0, 0)[0];		
		}
		corrcoef /= 3.0;
		return corrcoef;
	}
	
}
