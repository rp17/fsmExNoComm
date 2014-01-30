package west.model;
//------------------------------------------------------------------------
//
//  Name:   StateMachine.h
//
//  Desc:   State machine class. Inherit from this class and create some 
//          states to give your agents FSM functionality
//
//  Author: Mat Buckland 2002 (fup@ai-junkie.com)
//
//------------------------------------------------------------------------
import west.messaging.Telegram;

public class StateMachine<T extends BaseGameEntity> {
	
	//a pointer to the agent that owns this instance
	private T pOwner;
	
	//a record of the current state the agent is in
	private State<T> pCurrentState;
	
	//a record of the last state the agent was in
	private State<T> pPreviousState;
	
	//this is called every time the FSM is updated
	private State<T> pGlobalState;
	
	public StateMachine(T owner){
		pOwner = owner;
		pCurrentState = null;
		pPreviousState = null;
		pGlobalState = null;
	}
	
	//use these methods to initialize the FSM	
	public void setCurrentState(State<T> s){pCurrentState = s;}
	public void setPreviousState(State<T> s){pPreviousState = s;}
	public void setGlobalState(State<T> s){pGlobalState = s;}
	
	//call this to update the FSM
	public void update() {
		//if a global state exists, call its execute method, else do nothing
	    if(pGlobalState != null)   pGlobalState.execute(pOwner);

	    //same for the current state
	    if (pCurrentState != null) pCurrentState.execute(pOwner);
	}
	  //change to a new state
	public  void  changeState(State<T> pNewState)
	  {
	    if(pNewState == null) System.out.println("<StateMachine.changeState>: trying to change to NULL state");

	    //keep a record of the previous state
	    pPreviousState = pCurrentState;

	    //call the exit method of the existing state
	    pCurrentState.exit(pOwner);

	    //change state to the new state
	    pCurrentState = pNewState;

	    //call the entry method of the new state
	    pCurrentState.enter(pOwner);
	  }

	  //change state back to the previous state
	  public void  revertToPreviousState()
	  {
	    changeState(pPreviousState);
	  }

	  //returns true if the current state's type is equal to the type of the
	  //class passed as a parameter. 
	  public boolean  isInState(State<T> st)
	  {
	    return pCurrentState.getStateType() == st.getStateType();
	  }

	  public State<T>  getCurrentState()  {return pCurrentState;}
	  public State<T>  getGlobalState()   {return pGlobalState;}
	  public State<T>  getPreviousState() {return pPreviousState;}
	  
	  final boolean handleMessage(final Telegram msg) {
		if(pCurrentState != null && pCurrentState.onMessage(pOwner, msg)) {return true;}
		if(pGlobalState != null && pGlobalState.onMessage(pOwner, msg)){return true;}
		return false;
	  }
}
