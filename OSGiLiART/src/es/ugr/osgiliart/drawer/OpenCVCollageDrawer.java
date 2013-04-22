package es.ugr.osgiliart.drawer;

import java.util.List;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;


import es.ugr.osgiliart.ArtisticGenome;
import es.ugr.osgiliart.ArtisticIndividual;
import es.ugr.osgiliart.ArtisticParameters;
import es.ugr.osgiliart.primitives.Drawer;
import es.ugr.osgiliart.primitives.Primitive;
import es.ugr.osgiliart.primitives.patch.Patch;
import es.ugr.osgiliath.OsgiliathService;

public class OpenCVCollageDrawer extends OsgiliathService implements Drawer {

		public static final String IMAGE_TYPE_PNG  = "PNG";
		public static final String IMAGE_TYPE_JPEG = "JPEG";
		
		
		public static final int COLOR_SCALE = 256;
		
		@Override
		public void draw(List<Primitive> list) {
			
		}

		@Override
		public void draw(ArtisticIndividual artistic) {
			/*
			 *  
			 */
			
			int imageWidth = (Integer) this.getAlgorithmParameters().getParameter(ArtisticParameters.IMAGE_WIDTH);
			int imageHeight = (Integer) this.getAlgorithmParameters().getParameter(ArtisticParameters.IMAGE_HEIGHT);
			String imageType = (String) this.getAlgorithmParameters().getParameter(ArtisticParameters.IMAGE_TYPE);
			String folderPath = (String) this.getAlgorithmParameters().getParameter(ArtisticParameters.DATA_FOLDER);
			
			List<Primitive> primitives = ((ArtisticGenome) artistic.getGenome()).getPrimitives();		
			Mat orig = new Mat(imageWidth, imageHeight, CvType.CV_8UC3, new Scalar(0));
			
			for(Primitive p:primitives){
				Patch patch = (Patch) p;
				Mat pm = patch.getMat();
				int posCol = (int)(patch.getLocation().x*orig.cols());
				int posRow = (int)(patch.getLocation().y*orig.rows());
				int finalCol = posCol + pm.cols();
				int finalRow = posRow + pm.rows();
				
				if(finalCol > orig.cols())
					finalCol = orig.cols();
				if(finalRow > orig.rows())
					finalRow = orig.rows();
				//System.out.println("Poniendo imagen de tamaño "+pm.rows()+","+pm.cols()+" en "+posRow+","+posCol+" hasta "+finalRow+","+finalCol);
				Mat bSubmat = orig.submat(posRow,finalRow,posCol,finalCol);    
			
		    	pm.copyTo(bSubmat);
		    	
		    	
				
			}
			/*
			 * draw image
			 ****************************************************/
			
			
			
			
			/*save image */		
			String imageExtension = null; 
			if ( imageType.equalsIgnoreCase(IMAGE_TYPE_JPEG) ) {
				 imageExtension = "jpg";
			} else if ( imageType.equalsIgnoreCase(IMAGE_TYPE_PNG)) {
				imageExtension = "png";
			}
			
			if ( imageExtension != null ) {
				String imagePath = String.format("%s/%s.%s", folderPath, artistic.getId(), imageExtension);			
				//System.out.println("Saving... " + imagePath + " primitives: " + primitives.size());
				//graphics.save(imagePath);
				//applet.save(imagePath);
				Highgui.imwrite(imagePath, orig);
				artistic.setImagePath ( imagePath );
			}		
		}
	
}
