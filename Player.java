
public class Player {
	private double turnAroundTime;
	private double trainingTime;
	private double physiotherapyTime;
	private double massageTime;
	private double waitingTimeInTrainingQueue;
	private double waitingTimeInPhysiotherapyQueue;
	private double waitingTimeInMassageQueue;
	private int skillLevel;
	private int ID;
	private int ifFreeNumber; //This field determines if player is free for actions or not. If this number is 0, then s/he is free. If not, then s/he is not free.
	private Training currentTraining; //Player's current training.
	private Physiotherapist currentPhysiotherapists; // Player's current physiotherapist.
	private int nOfMassages;
	public Player(int ID, int skillLevel) {
		this.ID = ID;
		this.skillLevel = skillLevel;
		this.ifFreeNumber = 0;
		this.nOfMassages = 0;
		this.turnAroundTime = 0;
		this.trainingTime = 0;
		this.physiotherapyTime = 0;
		this.massageTime = 0;
		this.waitingTimeInMassageQueue = 0;
		this.waitingTimeInPhysiotherapyQueue = 0;
		this.waitingTimeInTrainingQueue = 0;
	}
	public int getIfFree() {
		return ifFreeNumber;
	}
	public void setIfFree(int ifFreeNumber) {
		this.ifFreeNumber = ifFreeNumber;
	}
	public int getSkillLevel() {
		return skillLevel;
	}
	public boolean ifFree() {
		if(ifFreeNumber==0) {
			return true;
		}
		else {
			return false;
		}
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public double getTurnAroundTime() {
		return turnAroundTime;
	}
	public void setTurnAroundTime(double turnAroundTime) {
		this.turnAroundTime = turnAroundTime;
	}
	public double getTrainingTime() {
		return trainingTime;
	}
	public void setTrainingTime(double trainingTime) {
		this.trainingTime = trainingTime;
	}
	public double getPhysiotherapyTime() {
		return physiotherapyTime;
	}
	public void setPhysiotherapyTime(double physiotherapyTime) {
		this.physiotherapyTime = physiotherapyTime;
	}
	public double getMassageTime() {
		return massageTime;
	}
	public void setMassageTime(double massageTime) {
		this.massageTime = massageTime;
	}
	public double getWaitingTimeInMassageQueue() {
		return waitingTimeInMassageQueue;
	}
	public void setWaitingTimeInMassageQueue(double waitingTimeInMassageQueue) {
		this.waitingTimeInMassageQueue = waitingTimeInMassageQueue;
	}
	public double getWaitingTimeInPhysiotherapyQueue() {
		return waitingTimeInPhysiotherapyQueue;
	}
	public void setWaitingTimeInPhysiotherapyQueue(double waitingTimeInPhysiotherapyQueue) {
		this.waitingTimeInPhysiotherapyQueue = waitingTimeInPhysiotherapyQueue;
	}
	public double getWaitingTimeInTrainingQueue() {
		return waitingTimeInTrainingQueue;
	}
	public void setWaitingTimeInTrainingQueue(double waitingTimeInTrainingQueue) {
		this.waitingTimeInTrainingQueue = waitingTimeInTrainingQueue;
	}
	public void setSkillLevel(int skillLevel) {
		this.skillLevel = skillLevel;
	}
	public Training getCurrentTraining() {
		return currentTraining;
	}
	public void setCurrentTraining(Training currentTraining) {
		this.currentTraining = currentTraining;
	}
	public Physiotherapist getCurrentPhysiotherapists() {
		return currentPhysiotherapists;
	}
	public void setCurrentPhysiotherapists(Physiotherapist currentPhysiotherapists) {
		this.currentPhysiotherapists = currentPhysiotherapists;
	}
	public int getnOfMassages() {
		return nOfMassages;
	}
	public void setnOfMassages(int nOfMassages) {
		this.nOfMassages = nOfMassages;
	}
	


	
}
