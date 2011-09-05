package es.ugr.osgiliath.network;

import java.util.List;

public interface Server {

	public void connect(String uri) throws Exception;
	public List<Node> getConnectedNodes();
	public void disconnect(String uri);
	//public void updateTasks();
	public void finished();
}
