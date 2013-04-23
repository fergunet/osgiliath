/*
 * Rectangle.java
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
 */
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