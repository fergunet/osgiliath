package es.ugr.osgiliart.primitives.basic;

import es.ugr.osgiliart.core.Color;
import es.ugr.osgiliart.core.Point;
import es.ugr.osgiliart.core.Texture;
import es.ugr.osgiliart.primitives.Primitive;

public class Quad implements Primitive {
	
	protected Point p1 = null;
	protected Point p2 = null;
	protected Point p3 = null;
	protected Point p4 = null;	
	protected Color color = null;
	protected Texture texture = null;
	
	
	public Quad ( Point p1, Point p2, Point p3, Point p4, Color color ) {
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		this.p4 = p4;
		this.color = color;	
	}
	
	public  Quad ( Point p1, Point p2, Point p3, Point p4, Texture texture  ) {
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		this.p4 = p4;
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

	public Point getP3() {
		return p3;
	}

	public void setP3(Point p3) {
		this.p3 = p3;
	}

	public Point getP4() {
		return p4;
	}

	public void setP4(Point p4) {
		this.p4 = p4;
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
		Quad newQuad = new Quad(p1, p2, p3, p4, color);
		return newQuad;
	}
	
	
}
