/*
 * DistanceComparator.java
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
package es.ugr.osgiliath.evolutionary.individual.comparators;

import java.util.Comparator;

import es.ugr.osgiliath.evolutionary.individual.Individual;

public class DistanceComparator implements Comparator<Individual>{

	public int compare(Individual o1, Individual o2) {
		return ((Double) o1.getFitness().getDistance()).compareTo((Double)o2.getFitness().getDistance());
	}
	

}
