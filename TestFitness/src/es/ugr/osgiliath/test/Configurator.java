package es.ugr.osgiliath.test;

import java.util.Hashtable;

import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;

import es.ugr.osgiliath.utils.OsgiliathConfiguration;

public class Configurator {
	
	ConfigurationAdmin configAdmin;
	public void activate(){
	try {
		
		
		
		 String ip = System.getProperty(OsgiliathConfiguration.IP_PROP);
		 String port = System.getProperty(OsgiliathConfiguration.PORT_PROP);
		

		if(ip == null)
			System.out.println("[WARNING]: IP not set");
		if(port == null)
			System.out.println("[WARNING]: PORT not set");
		

		System.out.println("");
		String URL = "ecftcp://"+ip+":"+port+"/initializer";
		System.out.println("URL:::"+URL);
		Configuration alg1 = this.configAdmin.getConfiguration("InitializerSpec");
		
		Hashtable<String, Object> props1 = new Hashtable<String, Object>();	  
		props1.put("service.exported.interfaces", "*");
		props1.put("service.exported.config","ecf.generic.server");
		props1.put("ecf.exported.containerfactoryargs",URL);
		alg1.update(props1);
		alg1.update();
		System.out.println("Configured remote client!!!!!!!!!!!!!!!!!!!!!!!");
		
		/*Configuration migrationBuffer = configAdmin.getConfiguration("Migrator");
		//Configuration alg2 = this.configAdmin.getConfiguration("OsgiliathEvolutionary");
		Hashtable<String, Object> props2 = new Hashtable<String, Object>();
		props2.put("service.exported.interfaces", "*");
		props2.put("service.exported.config","ecf.generic.server");
		props2.put("ecf.exported.containerfactoryargs",URL);
		migrationBuffer.update(props2);
		migrationBuffer.update();
		System.out.println("Configured Migrator");*/
		System.out.println("---------REMOTE FINISHED--------");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


public void setConfigurationAdmin(ConfigurationAdmin configAdmin){
	this.configAdmin = configAdmin;
}

public void unsetConfigurationAdmin(ConfigurationAdmin configAdmin){
	this.configAdmin = null;
}

}
