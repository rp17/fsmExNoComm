package west.model;

import java.util.Random;

import west.messaging.Telegram;
import west.messaging.Message;

public class WifesGlobalState extends State<MinersWife>{
	private volatile static WifesGlobalState singleton;
	private Random rand = new Random();
	   private WifesGlobalState(){}
	 
	   // synchronized keyword has been removed from here
	   public static WifesGlobalState getSingleton(){
	     // needed because once there is singleton available no need to acquire
	     // monitor again & again as it is costly
	     if(singleton==null) {
	       synchronized(WifesGlobalState.class){
	          // this is needed if two threads are waiting at the monitor at the
	          // time when singleton was getting instantiated
	          if(singleton==null)
	          singleton = new WifesGlobalState();
	       }
	     }
	     return singleton;
	   }
	   public void enter(MinersWife wife){}
	   public void execute(MinersWife wife){
		   if(rand.nextFloat() < 0.1){
			   wife.getFSM().changeState(VisitBathroom.getSingleton());
		   }
	   }
	   public void exit(MinersWife wife){}
	   boolean onMessage(MinersWife wife, final Telegram msg){
		   if(msg.getMsgID() == Message.MSG_IM_HOME) {
			   System.out.println("Message handled by " + wife.getName() + " at time: " + Clock.getTimeInSec());
			   System.out.println(wife.getName() + ": Hi honey. Let me make you some of mah fine country stew");
			   wife.getFSM().changeState(CookStew.getSingleton());
		   }
		   
		   return true;
	   }
}
