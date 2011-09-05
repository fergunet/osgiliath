package es.ugr.osgiliath.network;

import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;



public abstract class Service {
	boolean published = false;
	Hashtable props = new Hashtable();
	protected ComponentContext context;
	
	protected EventAdmin eventAdmin;

	void publish(){
//		if(!published){
//			BundleContext context;
//			props.put(RemoteOSGiService.R_OSGi_REGISTRATION,
//					Boolean.TRUE);
//
//			context.registerService(this.getClass().getInterfaces()[0].getName(),this, props);
//			published = true;
//		}
	}
	
	public String toString(){
		return "Service: "+this.getClass().getInterfaces()[0].getName()+":"+this.getClass().getName();
	}
	
	public void addProperty(String key, String value){
		this.props.put(key, value);
	}
	
	public void deleteProperty(String key){
		this.props.remove(key);
	}
	
	public void bindEventAdmin(EventAdmin eventAdmin){
		System.out.println(this.toString()+"BIND EVENT ADMIN");
		this.eventAdmin = eventAdmin;
	}
	
	public void unbindEventAdmin(EventAdmin eventAdmin){
		System.out.println(this.toString()+"UNBIND EVENT ADMIN");
		this.eventAdmin = null;
	}
	
	public void sendEvent(String topic, Dictionary dict){
		Event ev = new Event(null, dict);
		
		this.eventAdmin.sendEvent(ev);
	}
	
	protected void activate(ComponentContext context){
		System.out.println("MOLO PORQUE TENGO COMPONENT CONTEXT soy: "+this.toString());
		this.context = context;
	}
	protected void deactivate(ComponentContext context){
		System.out.println("Me las piro vampiros");
	}
}
