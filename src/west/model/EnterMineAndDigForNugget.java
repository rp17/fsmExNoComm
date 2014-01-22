package west.model;

public class EnterMineAndDigForNugget extends State<Miner> {
	private volatile static EnterMineAndDigForNugget singleton;
	 
	   private EnterMineAndDigForNugget(){}
	 
	   // synchronized keyword has been removed from here
	   public static EnterMineAndDigForNugget getSingleton(){
	     // needed because once there is singleton available no need to acquire
	     // monitor again & again as it is costly
	     if(singleton==null) {
	       synchronized(EnterMineAndDigForNugget.class){
	          // this is needed if two threads are waiting at the monitor at the
	          // time when singleton was getting instantiated
	          if(singleton==null)
	          singleton = new EnterMineAndDigForNugget();
	       }
	    }
	   return singleton;
	  }
	   
	  public void enter(Miner miner){
		  //if the miner is not already located at the goldmine, he must
		  //change location to the gold mine
		  if (miner.getLocation() != Location.goldmine)
		  {
		    System.out.println(miner.getName() + " Walkin' to the goldmine");
		    miner.changeLocation(Location.goldmine);
		  }

	  }
	  public void execute(Miner miner){
		  //if the miner is at the goldmine he digs for gold until he
		  //is carrying in excess of MaxNuggets. If he gets thirsty during
		  //his digging he packs up work for a while and changes state to
		  //gp to the saloon for a whiskey.
		  miner.addToGoldCarried(1);
		  miner.increaseFatigue();
		  System.out.println(miner.getName() + " Pickin' up a nugget");

		  //if enough gold mined, go and put it in the bank
		  if (miner.pocketsFull())
		  {
		    miner.getFSM().changeState(VisitBankAndDepositGold.getSingleton());
		  }
		  if (miner.thirsty())
		  {
		    miner.getFSM().changeState(QuenchThirst.getSingleton());
		  }
	  }
	  public void exit(Miner miner){
		  System.out.println(miner.getName() + " Ah'm leavin' the goldmine with mah pockets full o' sweet gold");
	  }
}
