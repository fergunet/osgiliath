package es.ugr.osgiliart.Primitives;

// El tri‡ngulo.
public class Triangle extends Shaper {
	
	private
	
	double height, width; //El alto y el ancho.
	
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
