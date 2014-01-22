package west.model;

public class VisitBankAndDepositGold extends State<Miner> {
	private volatile static VisitBankAndDepositGold singleton;
	 
	   private VisitBankAndDepositGold(){}
	 
	   // synchronized keyword has been removed from here
	   public static VisitBankAndDepositGold getSingleton(){
	     // needed because once there is singleton available no need to acquire
	     // monitor again & again as it is costly
	     if(singleton==null) {
	       synchronized(VisitBankAndDepositGold.class){
	          // this is needed if two threads are waiting at the monitor at the
	          // time when singleton was getting instantiated
	          if(singleton==null)
	          singleton = new VisitBankAndDepositGold();
	       }
	     }
	     return singleton;
	   }
	   public void enter(Miner miner){
		   //on entry the miner makes sure he is located at the bank
		   if (miner.getLocation() != Location.bank)
		   {
		     System.out.println(miner.getName() + " Goin' to the bank. Yes siree");
		     miner.changeLocation(Location.bank);
		   }

	   }
	   public void execute(Miner miner){
		   //deposit the gold
		   miner.addToWealth(miner.goldCarried());
		   miner.setGoldCarried(0);
		   System.out.println(miner.getName() + " Depositing gold. Total savings now: " + miner.wealth());

		   //wealthy enough to have a well earned rest?
		   if (miner.wealth() >= Miner.ComfortLevel)
		   {
		     System.out.println(miner.getName() + " WooHoo! Rich enough for now. Back home to mah li'lle lady");
		     miner.getFSM().changeState(GoHomeAndSleepTillRested.getSingleton());      
		   }

		   //otherwise get more gold
		   else 
		   {
		     miner.getFSM().changeState(EnterMineAndDigForNugget.getSingleton());
		   }
	   }
	   public void exit(Miner miner){
		   System.out.println(miner.getName() + " Leavin' the bank");
	   }
}
