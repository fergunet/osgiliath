/*
 * BooleanGene.java
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
 */
package es.ugr.osgiliath.evolutionary.basiccomponents.individuals;

import es.ugr.osgiliath.evolutionary.individual.Gene;

public class BooleanGene implements Gene, Cloneable{
	boolean b;
	
	public BooleanGene(boolean b){
		this.b = b;
	}
	
	public Object clone(){
		return new BooleanGene(this.b);
	}
	
	public void setValue(boolean b){
		this.b = b;
	}

	public boolean getValue(){
		return this.b;
	}
	
	public String toString(){
		if(this.b==true)
			return "1";
		else
			return "0";
	}
}
