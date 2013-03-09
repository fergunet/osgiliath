package es.ugr.osgiliart.Primitives;

import processing.*;
import java.awt.Point;


//Clase padre de donde derivan todas las demas primitivas
public class Shaper {
	
	private
		Point p; //Coordenadas de posicion
	
    public
    	void setLocation(int x, int y) // Asignar  la posicion de la figura
    	{
    		p.x = x;
    		p.y = y;
    	}
		Shaper() //Constructor por defecto
		{
			p.x = 0;
			p.y = 0;
		}
		Shaper( double x, double y) //Constructor con parametros
		{
			(double) p.x = x;
			(double) p.y = y;
		}
		Point Localizar_Figura()
		{
			return p;
	}; //Devuelve posicion figura
}
