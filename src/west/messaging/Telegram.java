package west.messaging;

public class Telegram implements Comparable<Telegram> {
	public double dispatchDelay;
	private int senderID;
	private int receiverID;
	private Message msgID;
	private Object extraInfo;
	
	public Telegram() {
		dispatchDelay = -1;
		senderID = -1;
		receiverID = -1;
		msgID = Message.MSG_BLANK;
		
	}
	public Telegram(long dispatchTime, int senderID, int receiverID, Message msgID, Object extraInfo) {
		this.dispatchDelay = dispatchTime;
		this.senderID = senderID;
		this.receiverID = receiverID;
		this.msgID = msgID;
		this.extraInfo = extraInfo;
	}
	
	public int getSenderID(){return senderID;}
	public int getReceiverID(){return receiverID;}
	public double getDelay(){return dispatchDelay;}
	public Message getMsgID(){return msgID;}
	@Override
	public int compareTo(Telegram other) {
		return (int)(this.dispatchDelay - other.dispatchDelay);
	}
}
