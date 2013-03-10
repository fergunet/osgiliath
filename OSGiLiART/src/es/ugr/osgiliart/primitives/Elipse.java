package es.ugr.osgiliart.primitives;

// La elipse.
public class Elipse implements Primitive {
	
	protected float height;
	protected float width; 

	public Elipse ( float width, float height ) {
		this.width = width;
		this.height = height;
	}

	
	public float getHeight() {
		return height;
	}



	public void setHeight(float height) {
		this.height = height;
	}



	public float getWidth() {
		return width;
	}



	public void setWidth(float width) {
		this.width = width;
	}



	public Object clone() {
		return new Elipse( width, height );
	}
}

