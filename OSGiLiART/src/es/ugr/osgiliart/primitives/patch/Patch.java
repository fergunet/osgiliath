package es.ugr.osgiliart.primitives.patch;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;

import es.ugr.osgiliart.core.Point;
import es.ugr.osgiliart.primitives.Primitive;


public class Patch implements Primitive {

	protected Point location;
	
	protected String filePath;
	
	public Patch(Point location, String filePath){
		this.location = location;
		this.filePath = filePath;
		
	}
	
	
	public Mat getMat(){
		return Highgui.imread(this.filePath);
	}
	
	//------- GETTERS & SETTERS ---------
	public Point getLocation() {
		return location;
	}
	public void setLocation(Point p) {
		this.location = p;
	}
	
	
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	
	public String toString(){
		return this.filePath+" ["+this.location+"]";
	}
	@Override
	public Object clone()  {
		return new Patch(this.location, this.filePath);
	}
	
	public static void main(String[] args){
		new Patch(new Point(0,0),"/Users/anabpel/Desktop/logo2.jpeg");
	}

}
