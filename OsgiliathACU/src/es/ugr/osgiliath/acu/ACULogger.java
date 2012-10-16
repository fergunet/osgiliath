package es.ugr.osgiliath.acu;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Properties;

import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

import es.ugr.osgiliath.OsgiliathService;
import es.ugr.osgiliath.utils.Logger;

public class ACULogger extends OsgiliathService implements Logger,EventHandler{
	
	String filename;
	int run = 0;
	int iteration = 0;

	public void activate(){
		System.out.println("ACU LOGGER ACTIVATED"); //NOTA: EL SETUP LO HACE EL EVENTO DEL STARTALL
		//setup(null);
	}
	@Override
	public void info(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void warn(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void error(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void debug(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stats(String message) {
		try{
			FileWriter fstream = new FileWriter(filename,true); //EXISTENT FILE
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(iteration+";"+message);
			iteration++;
			out.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}

	}

	@Override
	public void setup(Properties props) {
		run++;
		iteration = 0;
		Date d = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd.hh'h'mm'm'ss's'");
		String timestamp = format.format(d);
		// Create file
		filename = "/home/pgarcia/Escritorio/pruebas/"+timestamp+"out.run."+this.run+"."+this.getFrameworkId();
		try{
			FileWriter fstream = new FileWriter(filename);
			BufferedWriter out = new BufferedWriter(fstream);

			ArrayList<String> keys = this.getAlgorithmParameters().getKeys();
			Collections.sort(keys);

			for(String k:keys)
				out.write(k+" = "+this.getAlgorithmParameters().getParameter(k)+"\n");
			out.write(filename+"\n");
			out.write("IT;BEST;AVERAGE;BESTSENT;AVERAGESENT;BESTRECEIVED;AVERAGERECEIVED;AVG_ACU;MAX_ACU;ACU_OF_BEST;TOTAL_ACUS;AVG_MIGRATION;AVG_FOREIGNS;SENT;RECEIVED;ACUS_REWARDED\n");
			out.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}

	}

	@Override
	public void handleEvent(Event arg0) {
		System.out.println(arg0.getTopic());
		//ESTO SE EJECUTA DESDE EL START ALL QUE RESETEA!!!
		this.setup(null);
		
	}
	
	

}
