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

import west.messaging.Telegram;

public abstract class State<T extends BaseGameEntity> {

	private static final String name = "";
	//this will execute when the state is entered
	abstract public void enter(T entity);
	
	//this is the states normal update function
	abstract public void execute(T entity);
	
	//this will execute when the state is exited. (My word, isn't
	//life full of surprises... ;o))
	abstract public void exit(T entity);
	boolean onMessage(T entity, final Telegram msg){return false;}
	public String getStateType(){return name;}
}
