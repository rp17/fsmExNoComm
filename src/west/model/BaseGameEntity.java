package west.model;
import west.messaging.Telegram;
public abstract class BaseGameEntity<T extends BaseGameEntity> {
	//every entity must have a unique identifying number
	private int m_ID;
	private String name;
	protected StateMachine<T> pStateMachine;
	static private int m_iNextValidID = 0;
	
	public BaseGameEntity(int ID, String name){
		setID(ID);
		this.name = name;
	}
	abstract public void update();
	private void setID(int val){
		if( val >= m_iNextValidID ) {
			m_ID = val;
			m_iNextValidID = m_ID + 1;
		}
	}
	public int ID(){return m_ID;}
	public String getName(){return name;}
	abstract public boolean handleMessage(final Telegram msg);
}
