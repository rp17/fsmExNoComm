package west.model;

public class Main {
	public static void main(String[] args) {
		Miner miner = new Miner(458, "Bob");
		MinersWife wife = new MinersWife(82, "Elsa");
		try {
			for(int i=0; i<40; ++i){
				miner.update();
				wife.update();
				Thread.sleep(800);
			}
			System.out.println(miner.getName() + " and " + wife.getName() + " have stopped");
		}
		catch(InterruptedException ex){System.out.println("Main thread interrupted");}
	}
}
