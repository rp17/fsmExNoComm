package west.model;
//------------------------------------------------------------------------
//
//  Name:   State.h
//
//  Desc:   abstract base class to define an interface for a state
//
//  Author: Mat Buckland 2002 (fup@ai-junkie.com)
//
//------------------------------------------------------------------------


public abstract class State<E> {

	private static final String name = "";
	//this will execute when the state is entered
	abstract public void enter(E obj);
	
	//this is the states normal update function
	abstract public void execute(E obj);
	
	//this will execute when the state is exited. (My word, isn't
	//life full of surprises... ;o))
	abstract public void exit(E obj);
	public String getStateType(){return name;}
}
