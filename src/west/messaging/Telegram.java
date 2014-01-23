package west.messaging;

public class Telegram implements Comparable<Telegram> {
	public double dispatchDelay;
	public int senderID;
	public int receiverID;
	public Message msg;
	public Object extraInfo;
	
	public Telegram() {
		dispatchDelay = -1;
		senderID = -1;
		receiverID = -1;
		msg = Message.MSG_BLANK;
		
	}
	public Telegram(long dispatchTime, int senderID, int receiverID, Message msg, Object extraInfo) {
		this.dispatchDelay = dispatchTime;
		this.senderID = senderID;
		this.receiverID = receiverID;
		this.msg = msg;
		this.extraInfo = extraInfo;
	}
	
	@Override
	public int compareTo(Telegram other) {
		return (int)(this.dispatchDelay - other.dispatchDelay);
	}
}
