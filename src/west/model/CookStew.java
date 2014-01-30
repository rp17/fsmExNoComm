package west.model;
import west.messaging.Dispatcher;
import west.messaging.Message;
import west.messaging.Telegram;

public class CookStew extends State<MinersWife>{
	private volatile static CookStew singleton;
	 
	   private CookStew(){}
	 
	   // synchronized keyword has been removed from here
	   public static CookStew getSingleton(){
	     // needed because once there is singleton available no need to acquire
	     // monitor again & again as it is costly
	     if(singleton==null) {
	       synchronized(CookStew.class){
	          // this is needed if two threads are waiting at the monitor at the
	          // time when singleton was getting instantiated
	          if(singleton==null)
	          singleton = new CookStew();
	       }
	     }
	     return singleton;
	   }
	   public void enter(MinersWife wife){
		   if(!wife.isCooking()) {
			   System.out.println(wife.getName() + ": Puttin' the stew in the oven");
			   Dispatcher.dispatchMsg(3.0, wife.ID(), wife.ID(), Message.MSG_STEW_READY, null);
			   wife.setCooking(true);
		   }
		   
	   }
	   public void execute(MinersWife wife){
		   System.out.println(wife.getName() + " : Cooking stew");
	   }
	   public void exit(MinersWife wife){
		   //System.out.println(wife.getName() + " : Finished cooking stew !");
	   }
	   boolean onMessage(MinersWife wife, final Telegram msg) {
		   if(msg.getMsgID() == Message.MSG_STEW_READY) {
			   System.out.println("Message received by " + wife.getName() + " at time: " + Clock.getTimeInSec());
			   System.out.println(wife.getName() + " : Stew ready! Let's eat");
			   Dispatcher.dispatchMsg(0.0, wife.ID(), 0, Message.MSG_STEW_READY, null);
			   wife.setCooking(false);
			   wife.getFSM().changeState(DoHouseWork.getSingleton());
			   return true;
		   }
		   return false;
	   }
}
