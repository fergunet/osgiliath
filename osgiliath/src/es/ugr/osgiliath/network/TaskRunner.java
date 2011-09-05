package es.ugr.osgiliath.network;



public class TaskRunner extends Thread{
	private Task t;
	private TaskOutput to;
	private TaskInput ti;
	
	public TaskRunner(Task t, TaskInput ti){
		this.t = t;
		this.ti = ti;
	}
	
	public void run() {
		this.to = t.calculateTask(ti);
		
	}
	public TaskOutput getTaskOutput() {
		return to;
	}
	
    
	
	
	

}
