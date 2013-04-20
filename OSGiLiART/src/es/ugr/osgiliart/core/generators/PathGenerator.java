package es.ugr.osgiliart.core.generators;

import java.io.File;
import java.util.Random;

import es.ugr.osgiliart.core.Generator;

public class PathGenerator implements Generator<String> {

	protected String folder;
	
	public PathGenerator(String folder){
		this.folder = folder;
	}
	
	@Override
	public String generate() {
		File dir = new File(folder);
		if (!dir.isDirectory())
			return null;
		Random randomGenerator = new Random();
		File[] ficheros = dir.listFiles();
		int index = randomGenerator.nextInt(ficheros.length);
		return ficheros[index].getAbsolutePath();
	}

}
