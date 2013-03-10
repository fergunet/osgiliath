package es.ugr.osgiliart.primitives.basic;

import es.ugr.osgiliart.core.Color;
import es.ugr.osgiliart.core.Point;
import es.ugr.osgiliart.core.Texture;
import es.ugr.osgiliart.primitives.Primitive;

public class Circle implements Primitive {
	protected float  radius;
	protected Point  center = null;	
	protected Color  color = null;
	protected Texture texture = null;
		
	public Circle () {
		this.radius = 0;
		this.center = null;
		this.color = null;
	}
	
	public Circle ( float radius, Point center, Color color ) {
		this.radius = radius;
		this.center = center;
		this.color = color;
	}

	public Circle ( float radius, Point center, Texture texture ) {
		this.radius = radius;
		this.center = center;
		this.texture = texture;
		
	}
		
	public float getRadius() {
		return radius;
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}

	public Point getCenter() {
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
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
		Circle newCircle = new Circle(radius, center, color);
		return newCircle;
	}
	
}

