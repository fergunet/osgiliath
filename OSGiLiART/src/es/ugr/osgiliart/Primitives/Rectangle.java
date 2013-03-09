package es.ugr.osgiliart.Primitives;

// El rect‡ngulo.
public class Rectangle extends Shaper {
	private
		double height, width; // El alto y ancho.
	public
		
		double GetAncho()
		{
			return width;
		}
	
		double GetAlto()
		{
			return height;
		}
		

		void SetAlto( int h)
		{
			 height = h;
		}
		
		void SetAncho(int w)
		{
			width = w;
		}
}