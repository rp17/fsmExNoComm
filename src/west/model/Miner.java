package west.model;

public class Miner extends BaseGameEntity {
	public static final int ComfortLevel = 5;
	public static final int MaxNuggets = 3;
	public static final int ThirstLevel = 5;
	public static final int TirednessThreshold = 5;
	private StateMachine<Miner> pStateMachine;
	private Location location;
	private int iGoldCarried;
	private int iMoneyInBank;
	private int iThirst;
	private int iFatigue;
	public Miner(int ID, String name){
		super(ID, name);
		location = Location.shack;
		iGoldCarried = 0;
		iMoneyInBank = 0;
		iThirst = 0;
		iFatigue = 0;
		pStateMachine = new StateMachine<Miner>(this);
		pStateMachine.setCurrentState(GoHomeAndSleepTillRested.getSingleton());
	}
	public void update(){
		iThirst += 1;
		pStateMachine.update();
	}
	public StateMachine<Miner> getFSM(){return pStateMachine;}
	public Location getLocation(){return location;}
	public void changeState(State<Miner> newState){
		pStateMachine.changeState(newState);
	}
	public void addToGoldCarried(int val){
		iGoldCarried += val;
		if(iGoldCarried < 0) iGoldCarried = 0;
	}
	
	public void addToWealth(int val){
		iMoneyInBank += val;
		if(iMoneyInBank < 0) iMoneyInBank = 0;
	}
	public boolean thirsty() {
		if(iThirst >= ThirstLevel){return true;}
		return false;
	}
	public boolean fatigued() {
		if(iFatigue > TirednessThreshold){return true;}
		return false;
	}
	public void decreaseFatigue(){iFatigue -= 1;}
	public void increaseFatigue(){iFatigue += 1;}
	public void changeLocation(Location loc){location = loc;}
	public int goldCarried(){return iGoldCarried;}
	public void setGoldCarried(int val){iGoldCarried = val;}
	public boolean pocketsFull(){return iGoldCarried >= MaxNuggets;}
	public int wealth(){return iMoneyInBank;}
	public void setWealth(int val){iMoneyInBank = val;}
	public void buyAndDrinkAWhiskey(){iThirst = 0; iMoneyInBank -= 2;}
}
