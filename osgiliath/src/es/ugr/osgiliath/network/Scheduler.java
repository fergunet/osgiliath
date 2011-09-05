package es.ugr.osgiliath.network;

import java.util.ArrayList;
import java.util.List;

import org.osgi.framework.BundleException;
import org.osgi.framework.ServiceReference;

/*import ch.ethz.iks.r_osgi.RemoteOSGiService;
import ch.ethz.iks.r_osgi.RemoteServiceReference;
import ch.ethz.iks.r_osgi.URI;*/


//Pa mi que esto ser�a mejor hacerlo con una interfaz, pero weno
public class Scheduler {
	
	
	//TODO darle una pensada a esto
	/*public TaskOutput sendTask(TaskInput taskInput, Node n){
	
		
		
		final RemoteServiceReference[] srefs = n.getRemote().getRemoteServiceReferences(new URI(n.getUri()),
			Task.class.getName(), null);
		
		for(RemoteServiceReference r:srefs){
			System.out.println(r.getURI());
			String[] interfaces = r.getServiceInterfaces();
			for(String s:interfaces)
				System.out.println(" INTERFAZ: "+s);
			String[] props = r.getPropertyKeys();
			for(String p:props)
				System.out.println(" PROPERTY: "+p+"->"+r.getProperty(p));
			
			
		}
		//Task task = (Task) remote.getRemoteService(srefs[0]);
		//TaskOutput to = task.calculateTask(task);
		
		//return to;
		return null;
	}
	*/
	

}
