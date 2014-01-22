package west.model;

public abstract class BaseGameEntity {
	//every entity must have a unique identifying number
	private int m_ID;
	private String name;
	//this is the next valid ID. Each time a BaseGameEntity is instantiated
	  //this value is updated

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
}
