/*
 * ColorHSV.java
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
package es.ugr.osgiliart.core;

public class ColorHSV {
	public float h;
	public float s;
	public float v;
	
	public ColorHSV (float h, float s, float v) {
		this.h = h;
		this.s = s;
		this.v = v;
	}		
	
	/* basado en 
	 * 
	 * http://martin.ankerl.com/2009/12/09/how-to-create-random-colors-programmatically/
	 */
	public Color toRGB () {
		float r = 0, g = 0, b = 0;
		int   _h = (int)(h * 6);
		
		float f = h * 6 - _h;
		float p = v * (1 - s);
		float q = v * (1 - f * s);
		float t = v * (1 - (1 - f) * s);

		switch ( _h ) {
		case 6:
		case 0:
	        r = v;
	        g = t;
	        b = p;
	        break;
		case 1:	    
	        r = q;
	        g = v;
	        b = p;
	        break;
		case 2:
	        r = p;
	        g = v;
	        b = t;
	        break;
		case 3:
	        r = p;
	        g = q;
	        b = v;
	        break;
		case 4:
	        r = t;
	        g = p;
	        b = v;
	        break;
		case 5:
	        r = v;
	        g = p;
	        b = q;
	        break;
	    }
	    return new Color(r, g, b);	    
	}
}
