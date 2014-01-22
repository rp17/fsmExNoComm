package west.model;

public class GoHomeAndSleepTillRested extends State<Miner> {

	private volatile static GoHomeAndSleepTillRested singleton;
	 
	   private GoHomeAndSleepTillRested(){}
	 
	   // synchronized keyword has been removed from here
	   public static GoHomeAndSleepTillRested getSingleton(){
	     // needed because once there is singleton available no need to acquire
	     // monitor again & again as it is costly
	     if(singleton==null) {
	       synchronized(GoHomeAndSleepTillRested.class){
	          // this is needed if two threads are waiting at the monitor at the
	          // time when singleton was getting instantiated
	          if(singleton==null)
	          singleton = new GoHomeAndSleepTillRested();
	       }
	    }
	   return singleton;
	  }

	   public void enter(Miner miner){
		   if(miner.getLocation() != Location.shack){
			   System.out.println(miner.getName() + " Walkin' home");
			   miner.changeLocation(Location.shack);
		   }
	   }
	   public void execute(Miner miner){
		   //if miner is not fatigued start to dig for nuggets again.
		   if (!miner.fatigued())
		   {
			   System.out.println(miner.getName() + " What a God darn fantastic nap! Time to find more gold");

		      miner.changeState(EnterMineAndDigForNugget.getSingleton());
		   }

		   else 
		   {
		     //sleep
		     miner.decreaseFatigue();
		     System.out.println(miner.getName() + " ZZZZ...");

		   } 
	   }
	   public void exit(Miner miner){
		   System.out.println(miner.getName() + " Leavin' the house");
	   }
}
