
public class Training extends Action implements Comparable<Training>{
	private double arrivalTime;
	private double duration;
	private Player player;
	private double endTime;
	
	public Training(double arrivalTime, double duration,Player player) {
		this.arrivalTime = arrivalTime;
		this.duration = duration;
		this.player = player;
	}
	public double getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(double arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public double getDuration() {
		return duration;
	}
	public void setDuration(double duration) {
		this.duration = duration;
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
	
	//This method determines if an training object is an leaving object or not.
	public boolean ifEndObject() {
		if(this.duration == -10.0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	
	
	public int compareTo(Training o) {
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
	
	
	
}

