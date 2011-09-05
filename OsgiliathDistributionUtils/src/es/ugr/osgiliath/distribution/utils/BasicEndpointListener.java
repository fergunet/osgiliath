package es.ugr.osgiliath.distribution.utils;

import org.osgi.framework.Bundle;
import org.osgi.framework.ServiceReference;
import org.osgi.service.remoteserviceadmin.EndpointDescription;
import org.osgi.service.remoteserviceadmin.ExportReference;
import org.osgi.service.remoteserviceadmin.ImportReference;
import org.osgi.service.remoteserviceadmin.RemoteServiceAdminEvent;
import org.osgi.service.remoteserviceadmin.RemoteServiceAdminListener;

public class BasicEndpointListener implements RemoteServiceAdminListener {

	@Override
	public void remoteAdminEvent(RemoteServiceAdminEvent event) {
		// Print the source bundle for the event
		final Bundle source = event.getSource();
		System.out.println("Received RSA event from "
				+ source.getSymbolicName());
 
		// Handle a few example event types
		switch (event.getType()) {
 
		// A new service was imported into the local framework
		case RemoteServiceAdminEvent.IMPORT_REGISTRATION:
 
			final ImportReference importReference = event.getImportReference();
 
			// Retrieve the ServiceReference and the endpoint it points to
			final ServiceReference importedService = importReference.getImportedService();
			final EndpointDescription importedEndpoint = importReference.getImportedEndpoint();
 
			System.out.println(importedService + " has been imported from "
					+ importedEndpoint.getFrameworkUUID());
			
		
 
			break;
 
		// The export of a service has been removed
		case RemoteServiceAdminEvent.EXPORT_UNREGISTRATION:
 
			final ExportReference exportReference = event.getExportReference();
 
			// Retrieve the ServiceReference
			final ServiceReference exportedService = exportReference
					.getExportedService();
 
			System.out.println(exportedService + " is no longer exported");
 
			break;
 
		default:
			break;
		}
 
	}
		
	

}
