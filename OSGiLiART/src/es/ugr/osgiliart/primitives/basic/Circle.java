/*
 * Generator.java
 * 
 * Copyright (c) 2013, Pablo Garcia-Sanchez. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 * 
 * Contributors:
 * Daniel Calandria
 * Roberto Morcillo
 */
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

