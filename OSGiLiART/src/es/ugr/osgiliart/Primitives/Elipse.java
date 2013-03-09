package es.ugr.osgiliart.Primitives;

// La elipse.
public class Elipse extends Figura {
	
private
	double height, width; // Alto y ancho

public
	void setHeight(int h)
	{
		height = h;
	}
	void setWidth(int w)
	{
		width = w;
	}
	double GetWidth()
	{
		return width;
	}
	double GetHeight()
	{
		return height;
	}

}

