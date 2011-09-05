package es.ugr.osgiliath.network;

import java.io.IOException;
import java.io.Serializable;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.ServiceReference;

/*import ch.ethz.iks.r_osgi.RemoteOSGiException;
import ch.ethz.iks.r_osgi.RemoteOSGiService;
import ch.ethz.iks.r_osgi.RemoteServiceReference;
import ch.ethz.iks.r_osgi.URI;*/

public class Node implements Serializable {
	/**
	 *  
	 */
	private static final long serialVersionUID = -905148369235317063L;

	private String uri;
	private Task task;
	
//	private RemoteOSGiService remote;
	public String getUri() {
		return uri;
	}

	
	public void setUri(String uri) {
		this.uri = uri;
	}
	/**
	 * Connect to a node to extract its remote services
	 * @param context
	 * @throws BundleException
	 * @throws RemoteOSGiException
	 * @throws IOException
	 */
	public void connect(BundleContext context) {//throws BundleException, RemoteOSGiException, IOException{
/*		final ServiceReference sref = context.getServiceReference(RemoteOSGiService.class.getName());

		if (sref == null) { 
			throw new BundleException("No R-OSGi found"); 
		} 	
		RemoteOSGiService remote = (RemoteOSGiService) context.getService(sref);

		// connect 
		remote.connect(new URI(this.uri));
		System.out.println("Conectado a nodo "+this.uri);
		final RemoteServiceReference[] srefs = remote.getRemoteServiceReferences(new URI(this.uri),
					Task.class.getName(), null);
		
		for (RemoteServiceReference r : srefs) {
			System.out.println(r.getURI());
			String[] interfaces = r.getServiceInterfaces();
			for (String s : interfaces)
				System.out.println(" INTERFAZ: " + s);
			String[] props = r.getPropertyKeys();
			for (String p : props)
				System.out.println(" PROPERTY: " + p + "->" + r.getProperty(p));

		}
		this.task = (Task) remote.getRemoteService(srefs[0]);
		System.out.println("Obtenida tarea");*/

	
		
	}
	
	public Task getTask(){
		return this.task;
	}
	/*public RemoteOSGiService getRemote(){
		return this.remote;
	}*/
	
	
	
	

}
