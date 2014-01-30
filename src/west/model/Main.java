package west.model;
import west.messaging.Dispatcher;
public class Main {
	public static void main(String[] args) {
		EntityManager.reset();
		Miner miner = new Miner(0, "Bob");
		EntityManager.registerEntity(miner);
		MinersWife wife = new MinersWife(1, "Elsa");
		EntityManager.registerEntity(wife);
		try {
			double delta = 0.8;
			long currentTime = System.nanoTime();
			Clock.reset();
			for(int i=0; i<40; ++i){
				long lastTime = currentTime;
				wife.update();
				miner.update();
				Dispatcher.dispatchDelayedMessages(delta);
				Thread.sleep(800);
				currentTime = System.nanoTime();
				delta = (currentTime - lastTime)*1.0e-9;
			}
			System.out.println(miner.getName() + " and " + wife.getName() + " have stopped");
		}
		catch(InterruptedException ex){System.out.println("Main thread interrupted");}
	}
}
