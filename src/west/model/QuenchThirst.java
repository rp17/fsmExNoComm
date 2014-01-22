package west.model;

public class QuenchThirst extends State<Miner> {
	private volatile static QuenchThirst singleton;
	 
	   private QuenchThirst(){}
	 
	   // synchronized keyword has been removed from here
	   public static QuenchThirst getSingleton(){
	     // needed because once there is singleton available no need to acquire
	     // monitor again & again as it is costly
	     if(singleton==null) {
	       synchronized(QuenchThirst.class){
	          // this is needed if two threads are waiting at the monitor at the
	          // time when singleton was getting instantiated
	          if(singleton==null)
	          singleton = new QuenchThirst();
	       }
	     }
	     return singleton;
	   }
	   public void enter(Miner miner){
		   if (miner.getLocation() != Location.saloon)
		   {    
		     miner.changeLocation(Location.saloon);
		     System.out.println(miner.getName() + " Boy, ah sure is thusty! Walking to the saloon");
		   }	   
	   }
	   public void execute(Miner miner){
		   if (miner.thirsty())
		   {
		     miner.buyAndDrinkAWhiskey();
		     System.out.println(miner.getName() + " That's mighty fine sippin' liquer");
		     miner.getFSM().changeState(EnterMineAndDigForNugget.getSingleton());
		   }
		  else 
		  {
		    System.out.println("\nERROR!\nERROR!\nERROR!");
		  }  

	   }
	   public void exit(Miner miner){
		   System.out.println(miner.getName() + " Leaving the saloon, feelin' good");
	   }
}
