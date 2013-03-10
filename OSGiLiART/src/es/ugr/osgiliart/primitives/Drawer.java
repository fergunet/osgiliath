package es.ugr.osgiliart.primitives;

import java.util.List;

import es.ugr.osgiliart.ArtisticIndividual;
 
public interface Drawer {
	void draw(List<Primitive> list);
	void draw(ArtisticIndividual artistic );
}
