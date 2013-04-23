/*
 * Starter.java
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
package es.ugr.osgiliath.starter;

import java.util.Hashtable;
import org.osgi.service.cm.ConfigurationAdmin;

import es.ugr.osgiliath.OsgiliathService;
import es.ugr.osgiliath.utils.OsgiliathConfiguration;
import org.osgi.service.cm.Configuration;

public class Starter extends OsgiliathService implements OsgiliathConfiguration{

	ConfigurationAdmin configAdmin;
	String ip;
	String frameworkId;
	String port;
	
	public void activate(){
		System.out.println("---------REMOTE CONFIGURATOR--------");
		try {
			
			
			 frameworkId = this.getFrameworkId();
			 ip = System.getProperty(OsgiliathConfiguration.IP_PROP);
			 port = System.getProperty(OsgiliathConfiguration.PORT_PROP);
			
			if(frameworkId == null)
				System.out.println("[WARNING]: frameworkID not set");
			if(ip == null)
				System.out.println("[WARNING]: IP not set");
			if(port == null)
				System.out.println("[WARNING]: PORT not set");
			
			System.out.println("FRAMEWORK ID"+frameworkId);
			
			String URL = "ecftcp://"+ip+":"+port+"/server";
			System.out.println("URL:::"+URL);
			Configuration alg1 = this.configAdmin.getConfiguration("OsgiliathEvolutionary");
			
			Hashtable<String, Object> props1 = new Hashtable<String, Object>();	  
			props1.put("service.exported.interfaces", "*");
			props1.put("service.exported.config","ecf.generic.server");
			props1.put("ecf.exported.containerfactoryargs",URL);
			alg1.update(props1);
			alg1.update();
			System.out.println("Configured remote algorithm");
			
			Configuration migrationBuffer = configAdmin.getConfiguration("Migrator");
			//Configuration alg2 = this.configAdmin.getConfiguration("OsgiliathEvolutionary");
			Hashtable<String, Object> props2 = new Hashtable<String, Object>();
			props2.put("service.exported.interfaces", "*");
			props2.put("service.exported.config","ecf.generic.server");
			props2.put("ecf.exported.containerfactoryargs",URL);
			migrationBuffer.update(props2);
			migrationBuffer.update();
			System.out.println("Configured Migrator");
			System.out.println("---------REMOTE FINISHED--------");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void configure() {
		

		
	}
	
	public void setConfigurationAdmin(ConfigurationAdmin configAdmin){
		this.configAdmin = configAdmin;
	}
	
	public void unsetConfigurationAdmin(ConfigurationAdmin configAdmin){
		this.configAdmin = null;
	}

}
