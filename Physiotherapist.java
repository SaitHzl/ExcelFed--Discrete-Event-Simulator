
public class Physiotherapist implements Comparable<Physiotherapist> {
	private int ID;
	private Double serviceTime;
	private int number; //This field determines if a physiotherapist is free or not. If number is zero then s/he is free. If not, s/he is not free.
	public Physiotherapist(int ID, Double serviceTime) {
		this.ID = ID;
		this.serviceTime = serviceTime;
		this.number = 0;
	}
	
	public int compareTo(Physiotherapist o) {
		if(this.ID > o.ID) {
			return 1;
		}
		if(this.ID < o.ID) {
			return -1;
		}
		return 0;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public double getServiceTime() {
		return serviceTime;
	}
	public void setServiceTime(Double serviceTime) {
		this.serviceTime = serviceTime;
	}
	public boolean ifEmpty() {
		if(this.number == 0) {
			return true;
		}
		if(this.number == 1) {
			return false;
		}
		return false;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	
}
