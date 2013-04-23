/*
 * ArtisticParameters.java
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
package es.ugr.osgiliart;

public class ArtisticParameters {


	
	/* list of supported primitives. Example: "circle,triangle,quad" */
	public static final String SUPPORTED_PRIMITIVES =	"parameters.osgiliart.primitives";
	
	/* size of genome ( primitives )*/
	public static final String GENOME_SIZE = 			"parameters.osgiliart.genome.size";
	
	public static final String IMAGE_MUTATION_PROB =    "parameters.osgiliart.mutation.prob";
	
	/* fitness: match with real image, histogram matching, probabilistic classifier, .... */
	public static final String FITNESS     = 			"parameters.osgiliart.fitness";

	
	public static final String IMAGE_WIDTH = 			"parameters.osgiliart.image.width";	
	public static final String IMAGE_HEIGHT =   		"parameters.osgiliart.image.height";	
	public static final String IMAGE_TYPE  = 			"parameters.osgiliart.image.type";
	public static final String DATA_FOLDER  = 			"parameters.osgiliart.data.folder";
	public static final String PATCHES_FOLDER  = 			"parameters.osgiliart.data.patches";
	public static final String MAX_RADIUS  =            "parameters.osgiliart.primitive.maxRadius";
			
	public static final String MATCHING_TAMPLATE  =       "parameters.osgiliart.features.matching.template";
	
	public static final String FRAGMENT_FOLDER = "parameters.osgiliart.collage.folder";
}
