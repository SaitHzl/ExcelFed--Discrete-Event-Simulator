
public class Massage extends Action implements Comparable<Massage>{
	private double arrivalTime;
	private double duration;
	private Player player;
	private double endTime;
	
	public Massage(double arrivalTime, double duration, Player player) {
		this.arrivalTime = arrivalTime;
		this.duration = duration;
		this.player = player;
	}
	
	// This method determines if an massage object is a type of leaving or not.
	public boolean ifLeaving() {
		if(this.duration == -10) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public int compareTo(Massage o) {
		if(this.player.getSkillLevel() == o.player.getSkillLevel()) {
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
		}
		if(this.player.getSkillLevel() > o.player.getSkillLevel()) {
			return -1;
		}
		if(this.player.getSkillLevel() < o.player.getSkillLevel()) {
			return 1;
		}
		return 0;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
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
	public double getEndTime() {
		return endTime;
	}
	public void setEndTime(double endTime) {
		this.endTime = endTime;
	}
	
}
