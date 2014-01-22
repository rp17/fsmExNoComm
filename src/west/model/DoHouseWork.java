package west.model;
import java.util.Random;
public class DoHouseWork extends State<MinersWife>{
	private volatile static DoHouseWork singleton;
	private Random rand = new Random();
	   private DoHouseWork(){}
	 
	   // synchronized keyword has been removed from here
	   public static DoHouseWork getSingleton(){
	     // needed because once there is singleton available no need to acquire
	     // monitor again & again as it is costly
	     if(singleton==null) {
	       synchronized(DoHouseWork.class){
	          // this is needed if two threads are waiting at the monitor at the
	          // time when singleton was getting instantiated
	          if(singleton==null)
	          singleton = new DoHouseWork();
	       }
	     }
	     return singleton;
	   }
	   public void enter(MinersWife wife){}
	   public void execute(MinersWife wife){
		   switch(rand.nextInt(3)) {
		   	case 0:
		   		System.out.println(wife.getName() + " Moppin' the floor");
		   		break;
		   	case 1:
		   		System.out.println(wife.getName() + " Washin' the dishes");
		   		break;
		   	case 2:
		   		System.out.println(wife.getName() + " Makin' the bed");
		   		break;
		   }
	   }
	   public void exit(MinersWife wife){}
}
