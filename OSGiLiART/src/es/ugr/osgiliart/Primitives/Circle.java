package es.ugr.osgiliart.Primitives;


//La Circunferencia.
public class Circle extends Shaper{
	private
		double ratio;  // Es el radio de la circunferencia.
	public
		void setRatio(double rad)
		{
			ratio = rad;
		}
		double getRatio()
		{
			return ratio;
		}
}
