package es.ugr.osgiliart.primitives.basic;

import es.ugr.osgiliart.core.Color;
import es.ugr.osgiliart.core.Point;
import es.ugr.osgiliart.core.Texture;
import es.ugr.osgiliart.primitives.Primitive;

public class Triangle implements Primitive {
	
	protected Point p1;
	protected Point p2;
	protected Point p3;
	
	protected Color color;
	protected Texture texture;
	
	
	public Triangle ( Point p1, Point p2, Point p3, Color color ) {
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		this.color = color;
		this.texture = null;
	}
	
	public  Triangle ( Point p1, Point p2, Point p3, Texture texture  ) {
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		this.texture = texture;
		this.color = null;
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

	public Point getP3() {
		return p3;
	}

	public void setP3(Point p3) {
		this.p3 = p3;
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
		Triangle newTriangle = new Triangle(p1, p2, p3, color);
		return newTriangle;
	}
}
