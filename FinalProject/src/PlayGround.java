import java.util.Scanner;

/**
 *  this will implement the playground class
 * @author Omar Hany (20180184)
 * 		   Abd Elhadi Omar (20180373)
 *         Sherif Hisham (20180134)
 */

public class PlayGround {
	Scanner in = new Scanner(System.in);
	protected
		String location, name, avilabeHours;
		PlayGroundOwner currrentOwner = new PlayGroundOwner();
		int size;
		double pricePerHour, CancellationPeriod;
		boolean isApproved = false;
		public PlayGround(String location, String name, String avilabeHours, int size, double pricePerHour,double cancellationPeriod) {
			this.location = location;
			this.name = name;
			this.avilabeHours = avilabeHours;
			this.size = size;
			this.pricePerHour = pricePerHour;
			CancellationPeriod = cancellationPeriod;
		}
		//empty constructor
		public PlayGround() {}
		public PlayGroundOwner getCurrrentOwner() {
			return currrentOwner;
		}
		public void setCurrrentOwner(PlayGroundOwner currrentOwner) {
			this.currrentOwner = currrentOwner;
		}
		public Scanner getIn() {
			return in;
		}
		public String getLocation() {
			return location;
		}
		public String getName() {
			return name;
		}
		public String getAvilabeHours() {
			return avilabeHours;
		}
		public int getSize() {
			return size;
		}
		public double getPricePerHour() {
			return pricePerHour;
		}
		public double getCancellationPeriod() {
			return CancellationPeriod;
		}
		public void setLocation(String location) {
			this.location = location;
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setAvilabeHours(String avilabeHours) {
			this.avilabeHours = avilabeHours;
		}
		public void setSize(int size) {
			this.size = size;
		}
		public void setPricePerHour(double pricePerHour) {
			this.pricePerHour = pricePerHour;
		}
		public void setCancellationPeriod(double cancellationPeriod) {
			CancellationPeriod = cancellationPeriod;
		}
}
