import java.util.LinkedList;
import java.util.PriorityQueue;


public class Operations {
	private PriorityQueue<Training> trainingQueue;
	private PriorityQueue<Massage> massageQueue;
	private PriorityQueue<Physiotherapy> physioQueue;
	private int nOfTrainingCoaches;
	private int nOfMasseurCoaches;
	private double currentTime;
	private LinkedList<Physiotherapist> physiotherapists;
	private int canceledAttempts;
	private int invalidAttempts;
	private PriorityQueue<Action> actionQueue;
	private int maxLengthTraining;
	private int maxLengthPhysio;
	private int maxLengthMassage;
	private int totalValidTraining;
	private int totalValidPhysio;
	private int totalValidMassage;
	

	public Operations() {
		this.nOfTrainingCoaches = 0;
		this.nOfMasseurCoaches = 0;
		this.trainingQueue = new PriorityQueue<Training>();
		this.massageQueue = new PriorityQueue<Massage>();
		this.physioQueue = new PriorityQueue<Physiotherapy>();
		this.physiotherapists = new LinkedList<Physiotherapist>();
		this.actionQueue = new PriorityQueue<Action>(new actionQueueComparator());
		this.canceledAttempts = 0;
		this.invalidAttempts = 0;
		this.currentTime = 0;
		this.maxLengthTraining = 0;
		this.maxLengthMassage = 0;
		this.maxLengthPhysio = 0;
		this.totalValidMassage = 0;
		this.totalValidPhysio = 0;
		this.totalValidTraining = 0;
	}
	
	//This method is called when an arrival object is created, and this method creates leaveTraining object.
	public void enteringTraining(Training training) {
		if(training.getPlayer().ifFree() == false) {
			this.canceledAttempts += 1;
		}
		else {
			this.totalValidTraining += 1;
			training.getPlayer().setIfFree(1);
			training.getPlayer().setCurrentTraining(training);
			if(this.nOfTrainingCoaches > 0) {
				training.setArrivalTime(currentTime);
				training.setEndTime(currentTime+training.getDuration());
				training.getPlayer().setTrainingTime(training.getPlayer().getTrainingTime()+training.getDuration());
				Training leaveTraining = new Training(training.getEndTime(), -10.0, training.getPlayer());
				this.actionQueue.add(leaveTraining);
				this.nOfTrainingCoaches = this.nOfTrainingCoaches - 1 ;
			}
			else {
				training.setArrivalTime(currentTime);
				this.trainingQueue.add(training);
				if(this.maxLengthTraining < trainingQueue.size()) {
					this.maxLengthTraining = trainingQueue.size();
				}
		}
		
	}
	}
	
	//This method is called when someone leaves the training. When someone leaves training, physiotherapy object is created.
	public void leavingTraining(Training training) {
		this.totalValidPhysio += 1;
		int a = 0;
		this.nOfTrainingCoaches += 1;
		Physiotherapy created = new Physiotherapy(currentTime, training.getPlayer().getCurrentTraining(), training.getPlayer());
		for(Physiotherapist phy : physiotherapists) {
			if(phy.ifEmpty()) {
				phy.setNumber(1);
				a = 1;
				training.getPlayer().setCurrentPhysiotherapists(phy);
				created.setEndTime(currentTime+phy.getServiceTime());
				created.getPlayer().setPhysiotherapyTime(created.getPlayer().getPhysiotherapyTime()+phy.getServiceTime());
				Physiotherapy leavePhysio = new Physiotherapy(created.getEndTime(), training.getPlayer().getCurrentTraining(), training.getPlayer());
				leavePhysio.setEndTime(-100.0);
				this.actionQueue.add(leavePhysio);
				break;
				
			}
		}
		if(a == 0) {
			created.setArrivalTime(currentTime);
			this.physioQueue.add(created);
			if(this.maxLengthPhysio < this.physioQueue.size()) {
				this.maxLengthPhysio = this.physioQueue.size();
			}
		}
		if(this.trainingQueue.peek()!=null) {
			this.trainingQueue.peek().setEndTime(currentTime+this.trainingQueue.peek().getDuration());
			this.trainingQueue.peek().getPlayer().setWaitingTimeInTrainingQueue(this.trainingQueue.peek().getPlayer().getWaitingTimeInTrainingQueue()+this.trainingQueue.peek().getEndTime()-(this.trainingQueue.peek().getArrivalTime()+this.trainingQueue.peek().getDuration()));
			this.trainingQueue.peek().getPlayer().setTrainingTime(this.trainingQueue.peek().getPlayer().getTrainingTime()+trainingQueue.peek().getDuration());
			Training leaveTraining = new Training(this.trainingQueue.peek().getEndTime(), -10.0, this.trainingQueue.peek().getPlayer());
			this.actionQueue.add(leaveTraining);
			this.trainingQueue.poll();
			this.nOfTrainingCoaches = this.nOfTrainingCoaches-1;
		}
		
		
	}
	
	//This method is called when someone finishes her/his physiotherapy.
	public void leavingPhysio(Physiotherapy physio) {
		physio.getPlayer().setIfFree(0);
		physio.getPlayer().setTurnAroundTime(physio.getPlayer().getTurnAroundTime()+(currentTime-physio.getPlayer().getCurrentTraining().getArrivalTime()));
		Physiotherapist free = physio.getPlayer().getCurrentPhysiotherapists();
		free.setNumber(0);
		if(this.physioQueue.peek()!=null) {
			physioQueue.peek().getPlayer().setIfFree(1);
			this.physioQueue.peek().getPlayer().setCurrentPhysiotherapists(free);
			free.setNumber(1);
			this.physioQueue.peek().setEndTime(currentTime + free.getServiceTime());
			this.physioQueue.peek().getPlayer().setWaitingTimeInPhysiotherapyQueue(this.physioQueue.peek().getPlayer().getWaitingTimeInPhysiotherapyQueue()+(currentTime-this.physioQueue.peek().getArrivalTime()));
			this.physioQueue.peek().getPlayer().setPhysiotherapyTime(this.physioQueue.peek().getPlayer().getPhysiotherapyTime()+free.getServiceTime());
			Physiotherapy leavePhysio = new Physiotherapy(this.physioQueue.peek().getEndTime(),this.physioQueue.peek().getTraining() , this.physioQueue.peek().getPlayer());
			leavePhysio.setEndTime(-100.0);
			this.physioQueue.poll();
			this.actionQueue.add(leavePhysio);
		}
	}
	
	//When someone arrives for massage this method is called.
	public void enteringMassage(Massage massage) {
		if(massage.getPlayer().getnOfMassages() >= 3) {
			this.invalidAttempts += 1;
		}
		else if(massage.getPlayer().ifFree() == false) {
			this.canceledAttempts += 1;
		}
		
		else {
			this.totalValidMassage += 1;
			massage.getPlayer().setnOfMassages(massage.getPlayer().getnOfMassages()+1);
			massage.getPlayer().setIfFree(1);
			if(this.nOfMasseurCoaches > 0) {
				this.nOfMasseurCoaches = this.nOfMasseurCoaches - 1;
				massage.setArrivalTime(currentTime);
				massage.setEndTime(currentTime+massage.getDuration());
				massage.getPlayer().setMassageTime(massage.getPlayer().getMassageTime()+massage.getDuration());
				Massage leaveMassage = new Massage(massage.getEndTime(), -10, massage.getPlayer());
				this.actionQueue.add(leaveMassage);
			}
			else {
				massage.setArrivalTime(currentTime);
				this.massageQueue.add(massage);
				if(this.maxLengthMassage < this.massageQueue.size()) {
					this.maxLengthMassage = this.massageQueue.size();
				}
			}
		}
	}
	
	//When someone finisher her/his massage, this method executes.
	public void leavingMassage(Massage massage) {
		massage.getPlayer().setIfFree(0);
		this.nOfMasseurCoaches += 1;
		if(this.massageQueue.peek()!=null) {
			massageQueue.peek().getPlayer().setIfFree(1);
			this.massageQueue.peek().setEndTime(currentTime + this.massageQueue.peek().getDuration());
			this.massageQueue.peek().getPlayer().setWaitingTimeInMassageQueue(this.massageQueue.peek().getPlayer().getWaitingTimeInMassageQueue()+(currentTime-this.massageQueue.peek().getArrivalTime()));
			this.massageQueue.peek().getPlayer().setMassageTime(this.massageQueue.peek().getPlayer().getMassageTime()+massageQueue.peek().getDuration());
			Massage leaveMassage = new Massage(this.massageQueue.peek().getEndTime(), -10, this.massageQueue.peek().getPlayer());
			this.massageQueue.poll();
			this.actionQueue.add(leaveMassage);
			this.nOfMasseurCoaches = this.nOfMasseurCoaches - 1;
		}
	}
	public void setTrainingQueue(PriorityQueue<Training> trainingQueue) {
		this.trainingQueue = trainingQueue;
	}
	public PriorityQueue<Training> getTrainingQueue() {
		return trainingQueue;
	}
	public void setMassageQueue(PriorityQueue<Massage> massageQueue) {
		this.massageQueue = massageQueue;
	}
	public PriorityQueue<Massage> getMassageQueue() {
		return massageQueue;
	}
	public void setPhysioQueue(PriorityQueue<Physiotherapy> physioQueue) {
		this.physioQueue = physioQueue;
	}
	public PriorityQueue<Physiotherapy> getPhysioQueue() {
		return physioQueue;
	}
	public int getnOfMasseurCoaches() {
		return nOfMasseurCoaches;
	}
	public void setnOfMasseurCoaches(int nOfMasseurCoaches) {
		this.nOfMasseurCoaches = nOfMasseurCoaches;
	}
	public int getnOfTrainingCoaches() {
		return nOfTrainingCoaches;
	}
	public void setnOfTrainingCoaches(int nOfTrainingCoaches) {
		this.nOfTrainingCoaches = nOfTrainingCoaches;
	}
	public double getCurrentTime() {
		return currentTime;
	}
	public void setCurrentTime(double currentTime) {
		this.currentTime = currentTime;
	}
	public LinkedList<Physiotherapist> getPhysiotherapists() {
		return physiotherapists;
	}
	public void setPhysiotherapists(LinkedList<Physiotherapist> physiotherapists) {
		this.physiotherapists = physiotherapists;
	}
	public int getCanceledAttempts() {
		return canceledAttempts;
	}
	public void setCanceledAttempts(int canceledAttempts) {
		this.canceledAttempts = canceledAttempts;
	}
	public int getInvalidAttempts() {
		return invalidAttempts;
	}
	public void setInvalidAttempts(int invalidAttempts) {
		this.invalidAttempts = invalidAttempts;
	}
	public PriorityQueue<Action> getActionQueue() {
		return actionQueue;
	}
	public void setActionQueue(PriorityQueue<Action> actionQueue) {
		this.actionQueue = actionQueue;
	}
	public int getMaxLengthMassage() {
		return maxLengthMassage;
	}
	public void setMaxLengthMassage(int maxLengthMassage) {
		this.maxLengthMassage = maxLengthMassage;
	}
	public int getMaxLengthPhysio() {
		return maxLengthPhysio;
	}
	public void setMaxLengthPhysio(int maxLengthPhysio) {
		this.maxLengthPhysio = maxLengthPhysio;
	}
	public int getMaxLengthTraining() {
		return maxLengthTraining;
	}
	public void setMaxLengthTraining(int maxLengthTraining) {
		this.maxLengthTraining = maxLengthTraining;
	}
	public int getTotalValidMassage() {
		return totalValidMassage;
	}
	public void setTotalValidMassage(int totalValidMassage) {
		this.totalValidMassage = totalValidMassage;
	}
	public int getTotalValidPhysio() {
		return totalValidPhysio;
	}
	public void setTotalValidPhysio(int totalValidPhysio) {
		this.totalValidPhysio = totalValidPhysio;
	}
	public int getTotalValidTraining() {
		return totalValidTraining;
	}
	public void setTotalValidTraining(int totalValidTraining) {
		this.totalValidTraining = totalValidTraining;
	}
	
	
	
	
	
	
}
