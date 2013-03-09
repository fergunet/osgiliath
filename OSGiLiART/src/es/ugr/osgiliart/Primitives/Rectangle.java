package es.ugr.osgiliart.Primitives;

// El rect‡ngulo.
public class Rectangle extends Shaper {
	private
		double height, width; // El alto y ancho.
	public
		
		double getAncho()
		{
			return width;
		}
	
		double getAlto()
		{
			return height;
		}
		

		void setAlto( int h)
		{
			 height = h;
		}
		
		void setAncho(int w)
		{
			width = w;
		}
}