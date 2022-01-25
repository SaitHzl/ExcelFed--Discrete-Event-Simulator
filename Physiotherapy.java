
public class Physiotherapy extends Action implements Comparable<Physiotherapy> {
	private double arrivalTime;
	private Training training;
	private Player player;
	private double endTime;
	public Physiotherapy(double arrivalTime, Training training, Player player) {
		this.arrivalTime = arrivalTime;
		this.training = training;
		this.player = player;
	}
	
	//This method determines if a physiotherapy object is a leaving object or not. 
	public boolean ifLeaving() {
		if(this.endTime == -100.0) {
			return true;
		}
		else {
			return false;
		}
		
	}
	public int compareTo(Physiotherapy o) {
		if(Math.abs(this.training.getDuration() - o.training.getDuration()) < 0.0000000001) {
			if(Math.abs(this.arrivalTime - o.arrivalTime) < 0.0000000001) {
				if(this.player.getID() > o.player.getID()) {
					return 1;
				}
				if(this.player.getID() < o.player.getID()) {
					return -1;
				}
				if(this.player.getID() == o.player.getID()) {
					return 0;
				}
			}
			if(this.arrivalTime > o.arrivalTime) {
				return 1;
			}
			if(this.arrivalTime < o.arrivalTime) {
				return -1;
			}
			return 0;
		}
		if(this.training.getDuration() > o.training.getDuration()) {
			return -1;
		}
		if(this.training.getDuration() < o.training.getDuration()) {
			return 1;
		}
		return 0;
	}
	public double getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(double arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public Training getTraining() {
		return training;
	}
	public void setTraining(Training training) {
		this.training = training;
	}
	
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public double getEndTime() {
		return endTime;
	}
	public void setEndTime(double endTime) {
		this.endTime = endTime;
	}
	
	
}
