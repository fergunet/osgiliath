package es.ugr.osgiliath.planetwars;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Properties;

import es.ugr.osgiliath.algorithms.AlgorithmParameters;
import es.ugr.osgiliath.planetwars.fitness.PlanetWarsFitnessCalculator;
import es.ugr.osgiliath.planetwars.fitness.PlanetWarsHierarchicalFitness;
import es.ugr.osgiliath.util.impl.HashMapParameters;

public class IndividualTester {

	private static String readFileAsString(String filePath) throws IOException {
        StringBuffer fileData = new StringBuffer();
        BufferedReader reader = new BufferedReader(
                new FileReader(filePath));
        char[] buf = new char[1024];
        int numRead=0;
        while((numRead=reader.read(buf)) != -1){
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
        }
        reader.close();
        return fileData.toString();
    }
	
	static String extractIndividual(String file) throws IOException{
		String all = readFileAsString(file);
		String[] lines =  all.split("\n");
		String last = lines[lines.length-1];
		int ind = last.indexOf("if");
		String indStr= last.substring(ind);
		return indStr;
		
	}
	public static void main(String[] args) {
		try{
		String directory = args[0];
		
		String environment = args[1];
		
		String mapFolder = environment+"/maps";
		
		File folder = new File(directory);
		File[] listOfFiles = folder.listFiles();
		
		File folderM = new File(mapFolder);
		File[] listOfMaps = folderM.listFiles();
		
		
		PlanetWarsFitnessCalculator pf = new PlanetWarsFitnessCalculator(1);
		HashMapParameters parameters = new HashMapParameters();
		Properties props = new Properties();
		props.put(PlanetWarsParameters.ENVIRONMENT_FOLDER, environment);
		parameters.setup(props);
		pf.setAlgorithmParameters(parameters);
		
		ArrayList<PlanetWarsHierarchicalFitness> individualsFitness = new ArrayList<PlanetWarsHierarchicalFitness>();
		
		for(File f:listOfFiles){
			System.out.println("File "+f.getAbsolutePath());
			String individual = extractIndividual(f.getAbsolutePath());
			individual = individual.replace(" ", "@");
			System.out.println("Individual "+individual);
			PlanetWarsHierarchicalFitness total = new PlanetWarsHierarchicalFitness(0, 0);
			
			for(File m:listOfMaps){
				String mapFile = m.getAbsolutePath();
				if(mapFile.contains(".txt")){
					//System.out.println("EJECUTANDO EN MAPA "+m.getAbsolutePath());
					String[] parts = mapFile.split("/");
					String mapName = parts[parts.length-1];
					PlanetWarsHierarchicalFitness fit = (PlanetWarsHierarchicalFitness) pf.executeMap(individual, mapName);
					System.out.println(fit);
					total.setPrimaryFitness(fit.getPrimaryFitness()+total.getPrimaryFitness());
					total.setSecondaryFitness(fit.getSecondaryFitness()+total.getSecondaryFitness());
					
					
				}
			}
			individualsFitness.add(total);
			
			
		}
		System.out.println("END");
		for(PlanetWarsHierarchicalFitness ifs:individualsFitness)
			System.out.println(ifs);
		}catch(Exception ex){
			ex.printStackTrace();
		}

	}

}
