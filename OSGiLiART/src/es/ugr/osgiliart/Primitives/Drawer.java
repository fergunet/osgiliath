package es.ugr.osgiliart.Primitives;

import java.util.List;

import es.ugr.osgiliart.ArtisticIndividual;
 
public interface Drawer {
	void draw(List<Shaper> list);
	void draw(ArtisticIndividual artistic );
}
