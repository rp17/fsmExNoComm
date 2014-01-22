package west.model;

import west.messaging.Telegram;

public class MinersWife extends BaseGameEntity {
	
	private Location location;
	public MinersWife(int ID, String name){
		super(ID, name);
		location = Location.shack;
		pStateMachine = new StateMachine<MinersWife>(this);
		pStateMachine.setCurrentState(DoHouseWork.getSingleton());
		pStateMachine.setGlobalState(WifesGlobalState.getSingleton());
	}
	@Override
	public void update() {
		pStateMachine.update();
	}
	public StateMachine<MinersWife> getFSM(){return pStateMachine;}
	public Location getLocation() {return location;}
	public void changeLocation(Location loc) {location = loc;}
	public boolean handleMessage(final Telegram msg){return false;}
}
