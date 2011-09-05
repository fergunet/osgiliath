package es.ugr.osgiliath.network;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;

//import ch.ethz.iks.r_osgi.RemoteOSGiException;

public class OsgiliathServer implements Server {

	int finished = 0;//TODO ESTO ES PARA CONTROLAR LOS QUE TERMINAN
	private List<Node> connectedNodes = new ArrayList<Node>();

	public OsgiliathServer(){
	}
	
	/**
	 * This function is used to connect to the server
	 */
	public void connect(String uri) throws Exception{
		Node n = new Node();
		n.setUri(uri);
		connectedNodes.add(n);//TODO control if is not added
		System.out.println("Node Added: "+n.getUri());		
		
	}

	
	public void disconnect(String uri) {
		for(int i = 0; i<connectedNodes.size();i++){
			if(connectedNodes.get(i).getUri().equals(uri))
				this.connectedNodes.remove(i);
			break;
		}
		
	}

	
	public List<Node> getConnectedNodes() {
		return this.connectedNodes;
	}
	
	/*public void updateTasks() {
		System.out.println("UPDATING TASKS");
		for(Node n:this.connectedNodes)
			try{
			n.connect(this.context);
			}catch(Exception ex){
				ex.printStackTrace();
				System.out.println("Nah");
			}
	}*/

	public void finished() {
		
		System.out.println("Not implemented yet");
	}
	
	
	
}
