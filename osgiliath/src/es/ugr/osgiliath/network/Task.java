package es.ugr.osgiliath.network;


public interface Task{
	TaskOutput calculateTask(TaskInput it);
	
	String getOwner();
	int nada();
	
}
