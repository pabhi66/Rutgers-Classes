package linear;

public class QueueApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BoundedQueue<Integer> bq 
			= new BoundedQueue<Integer>(3);
		try {
			bq.enqueue(1);
			bq.enqueue(2);
			bq.enqueue(3);
			bq.enqueue(4);
		} catch (QueueFullException e) {
			System.out.println(e.getMessage());
		}

	}

}
