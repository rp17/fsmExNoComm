package west.model;

public class MinersWife extends BaseGameEntity {
	private StateMachine<MinersWife> pStateMachine;
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
}
