public class HotelRoom
{
	final String singleRoom    = "single";
	final String doubleRoom    = "double";
	final String businessSuite = "business";


	// define your fields here
	private String guestname;
	private String roomtype;
	private int days;

	public HotelRoom(String guestname, String roomtype, int days)
	{
		// complete this method
		this.guestname = guestname;
		this.roomtype = roomtype;
		this.days = days;
	}

	public String getGuestName(){
		return this.guestname; // replace this line with your code
	}

	public String getRoomType()
	{
		return this.roomtype; // replace this line with your code
	}

	public int getReservationLength()
	{
		return this.days; // replace this line with your code
	}
    
	public double getEstimatedPrice()
	{
		if(this.roomtype.equals(singleRoom))
			return this.days * 80;
		if(this.roomtype.equals(doubleRoom))
			return this.days * 120;
		if(this.roomtype.equals(businessSuite))
			return this.days * 200;
		else
		return 0.00; // replace this line with your code
	}

	public void setRoomType(String roomtype)
	{
		// complete this method
		this.roomtype = roomtype;
	}

	public void setReservationLength(int days)
	{
		// complete this method
		this.days = days;
	}

	public double getActualPrice(int daysStayed)
	{

		if(daysStayed < getReservationLength()){
			double x = getEstimatedPrice();
			while(daysStayed != getReservationLength()){
				x += 5;
				String y = getRoomType();
				if(y.equals(singleRoom))
					x-= 80;
				if(y.equals(doubleRoom))
					x-= 120;
				if(y.equals(businessSuite))
					x-= 200;
				daysStayed++;
			}
			return x;
		}
		if(daysStayed > getReservationLength()){
			double x = getEstimatedPrice();
			while(daysStayed > getReservationLength()){
				String y = getRoomType();
				if(y.equals(singleRoom))
					x+= 80;
				if(y.equals(doubleRoom))
					x+= 120;
				if(y.equals(businessSuite))
					x+= 200;
				daysStayed--;
			}
			return x;
		}
		return getEstimatedPrice();
	}

}
