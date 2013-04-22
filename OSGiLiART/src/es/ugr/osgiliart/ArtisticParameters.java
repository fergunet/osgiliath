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
