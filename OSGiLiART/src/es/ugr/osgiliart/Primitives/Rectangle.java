package es.ugr.osgiliart.Primitives;


public class Rectangle extends Shaper {
	private
		double height, width;
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