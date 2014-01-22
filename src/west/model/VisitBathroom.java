package west.model;

public class VisitBathroom extends State<MinersWife>{
	private volatile static VisitBathroom singleton;
	 
	   private VisitBathroom(){}
	 
	   // synchronized keyword has been removed from here
	   public static VisitBathroom getSingleton(){
	     // needed because once there is singleton available no need to acquire
	     // monitor again & again as it is costly
	     if(singleton==null) {
	       synchronized(VisitBathroom.class){
	          // this is needed if two threads are waiting at the monitor at the
	          // time when singleton was getting instantiated
	          if(singleton==null)
	          singleton = new VisitBathroom();
	       }
	     }
	     return singleton;
	   }
	   public void enter(MinersWife wife){
		   System.out.println(wife.getName() + " Walkin' to the can. Need to powda mah pretty li'lle nose");
	   }
	   public void execute(MinersWife wife){
		   System.out.println(wife.getName() + " Ahhhhhh! Sweet relief!");
		   wife.getFSM().revertToPreviousState();
	   }
	   public void exit(MinersWife wife){
		   System.out.println(wife.getName() + " Leavin' the Jon");
	   }
}
