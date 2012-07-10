package es.ugr.osgiliath.distribution.utils;

import java.io.IOException;
import java.util.Dictionary;
import java.util.Hashtable;


import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.component.ComponentContext;


public class RemoteConfigurator {
	
	ConfigurationAdmin configAdmin;
	/*
	 *  <property name="service.exported.interfaces" type="String" value="*"/>
   <property name="service.exported.configs" type="String" value="ecf.generic.server"/>
   <property name="ecf.exported.containerfactoryargs" type="String" value="ecftcp://localhost:3787/server"/>
	 * */
	
	public void activate(ComponentContext context){
		assignDistributionProperties();
		ServiceReference sr = context.getServiceReference();
		//context.get
		
	}
	public void assignDistributionProperties(){
		try {
			System.out.println("[Osgiliath Remote Configurator]");
			//Configuration yeja = configAdmin.getConfiguration("es.ugr.osgiliath.evolutionary.elements.FitnessCalculator");
			Configuration yeja3 = configAdmin.getConfiguration("OsgiliathFunctionsProblemsFitnessCalculator");
			//Configuration yeja2 = configAdmin.getConfiguration("pollas");
			
			//OsgiliathFunctionsProblemsFitnessCalculator
			Dictionary dict = yeja3.getProperties();
			System.out.println(dict);
			System.out.println("QUE HAY EN DICT"+dict.keys());
			
				dict = new Hashtable();
				dict.put("service.exported.interfaces", "*");
				dict.put("service.exported.configs", "ecf.generic.server");
				dict.put("ecf.exported.containerfactoryargs", "ecftcp://localhost:3789/server");
			
		
			yeja3.update(dict);
				
			/*if(dict.get("remoteService").equals("true")){
			
				dict.put("service.exported.interfaces", "*");
				dict.put("service.exported.configs", "ecf.generic.server");
				dict.put("ecf.exported.containerfactoryargs", "ecftcp://localhost:3787/server");
			
				yeja.update(dict);
			}
			yeja3.update();
			
			
			Configuration[] all = configAdmin.listConfigurations(null);
			//setting to null to avoid security check
			//for
			
			if(all==null){
				System.out.println("No he pillado configuracion");
				return;
			}
			
			
			for(Configuration con:all){
				Dictionary d = con.getProperties();
				System.out.println("PROPS");
				System.out.println(d);
				
				if(d.get("remoteService")!=null){
				
					d.put("service.exported.interfaces", "*");
					d.put("service.exported.configs", "ecf.generic.server");
					d.put("ecf.exported.containerfactoryargs", "ecftcp://localhost:3787/server");
				
					con.update(dict);
				}
			}*/
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		
	}
	
	public void setConfigurationAdmin(ConfigurationAdmin conf){
		this.configAdmin = conf;
	}

}
