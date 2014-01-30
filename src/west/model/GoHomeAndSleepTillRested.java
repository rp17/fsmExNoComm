package west.model;
import west.messaging.Dispatcher;
import west.messaging.Message;
import west.messaging.Telegram;

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
			   
			   Dispatcher.dispatchMsg(0.0, miner.ID(), 1, Message.MSG_IM_HOME, null);
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
	   
	   boolean onMessage(Miner miner, final Telegram msg){
		   if(msg.getMsgID() == Message.MSG_STEW_READY) {
			   System.out.println("Message handled by " + miner.getName() + " at time: " + Clock.getTimeInSec());
			   System.out.println(miner.getName() + " : Okay hun, ahm a-coming' !");
			   return true;
		   }
		   return false;
	   }
}
