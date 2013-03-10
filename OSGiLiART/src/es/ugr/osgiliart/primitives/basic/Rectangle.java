package es.ugr.osgiliart.primitives.basic;

import es.ugr.osgiliart.core.Color;
import es.ugr.osgiliart.core.Point;
import es.ugr.osgiliart.core.Texture;
import es.ugr.osgiliart.primitives.Primitive;

/* 
 * Rectangle
 */
public class Rectangle implements Primitive {
	
	
	protected Point p1;  /* top-left square     */
	protected Point p2;  /* bottom-right square */
	
	protected Color color = null;
	protected Texture texture = null;
	
	public Rectangle ( Point p1, Point p2, Color color ) {
		this.p1 = p1;
		this.p2 = p2;
		this.color = color;
	}
	
	public Rectangle ( Point p1, Point p2, Texture texture ) {
		this.p1 = p1;
		this.p2 = p2;
		this.texture = texture;		
	}

	public Point getP1() {
		return p1;
	}

	public void setP1(Point p1) {
		this.p1 = p1;
	}

	public Point getP2() {
		return p2;
	}

	public void setP2(Point p2) {
		this.p2 = p2;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}
	
	public Object clone () {
		Rectangle newRectangle = new Rectangle(p1, p2, color);
		return newRectangle;
	}
	
}