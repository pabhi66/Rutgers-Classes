package linear;

public class QueueFullException extends Exception {
	
	public QueueFullException() {
		super();   // calls the Exception class (superclass) constructor
	}

	public QueueFullException(String msg) {
		super(msg);   // calls the Exception class (superclass) constructor
	}
	
}
