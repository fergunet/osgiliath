/*
 * Patch.java
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
 * Ana Belen Pelegrina
 */
package es.ugr.osgiliart.primitives.patch;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;

import es.ugr.osgiliart.core.Point;
import es.ugr.osgiliart.primitives.Primitive;


public class Patch implements Primitive {

	protected Point location;
	
	protected String filePath;
	
	public Patch(Point location, String filePath){
		this.location = location;
		this.filePath = filePath;
		
	}
	
	
	public Mat getMat(){
		return Highgui.imread(this.filePath);
	}
	
	//------- GETTERS & SETTERS ---------
	public Point getLocation() {
		return location;
	}
	public void setLocation(Point p) {
		this.location = p;
	}
	
	
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	
	public String toString(){
		return this.filePath+" ["+this.location+"]";
	}
	@Override
	public Object clone()  {
		return new Patch(this.location, this.filePath);
	}
	
	public static void main(String[] args){
		new Patch(new Point(0,0),"/Users/anabpel/Desktop/logo2.jpeg");
	}

}
