import java.util.Comparator;

public class actionQueueComparator implements Comparator<Action>{

	public int compare(Action o1, Action o2) {
		if(o1 instanceof Training && o2 instanceof Training) {
			Training train1 = (Training) o1;
			Training train2 = (Training) o2;
			if(Math.abs(train1.getArrivalTime() - train2.getArrivalTime()) < 0.0000000001) {
				if(train1.getPlayer().getID() > train2.getPlayer().getID()) {
					return 1;
				}
				else if(train1.getPlayer().getID() < train2.getPlayer().getID()) {
					return -1;
				}
				else {
					return 0;
				}
			}
			
			if(train1.getArrivalTime() > train2.getArrivalTime()) {
				return 1;
			}
			
			if(train1.getArrivalTime() < train2.getArrivalTime()) {
				return -1;
			}
		}
		
		if(o1 instanceof Physiotherapy && o2 instanceof Physiotherapy) {
			Physiotherapy physio1 = (Physiotherapy) o1;
			Physiotherapy physio2 = (Physiotherapy) o2;
			if(Math.abs(physio1.getArrivalTime() - physio2.getArrivalTime()) < 0.0000000001) {
				if(physio1.getPlayer().getID() > physio2.getPlayer().getID()) {
					return 1;
				}
				else if(physio1.getPlayer().getID() < physio2.getPlayer().getID()) {
					return -1;
				}
				else {
					return 0;
				}
			
		}
			if(physio1.getArrivalTime() > physio2.getArrivalTime()) {
				return 1;
			}
			if(physio1.getArrivalTime() < physio2.getArrivalTime()) {
				return -1;
			}
		}
		
		if(o1 instanceof Massage && o2 instanceof Massage) {
			Massage massage1 = (Massage) o1;
			Massage massage2 = (Massage) o2;
			if(Math.abs(massage1.getArrivalTime() - massage2.getArrivalTime()) < 0.0000000001) {
				if(massage1.getPlayer().getID() > massage2.getPlayer().getID()) {
					return 1;
				}
				else if(massage1.getPlayer().getID() < massage2.getPlayer().getID()) {
					return -1;
				}
				else {
					return 0;
				}
			
		}
			if(massage1.getArrivalTime() > massage2.getArrivalTime()) {
				return 1;
			}
			if(massage1.getArrivalTime() < massage2.getArrivalTime()) {
				return -1;
			}
		}
		
		if(o1 instanceof Training && o2 instanceof Physiotherapy) {
			Training train = (Training) o1;
			Physiotherapy physio = (Physiotherapy) o2;
			if(Math.abs(train.getArrivalTime() - physio.getArrivalTime()) < 0.0000000001) {
				if(train.getPlayer().getID() > physio.getPlayer().getID()) {
					return 1;
				}
				else if(train.getPlayer().getID() < physio.getPlayer().getID()) {
					return -1;
				}
				else {
					return 0;
				}
			}
			if(train.getArrivalTime() > physio.getArrivalTime()) {
				return 1;
			}
			if(train.getArrivalTime() < physio.getArrivalTime()) {
				return -1;
			}

		}
		
		if(o1 instanceof Physiotherapy && o2 instanceof Training) {
			Training train = (Training) o2;
			Physiotherapy physio = (Physiotherapy) o1;
			if(Math.abs(train.getArrivalTime() - physio.getArrivalTime()) < 0.0000000001) {
				if(train.getPlayer().getID() > physio.getPlayer().getID()) {
					return -1;
				}
				else if(train.getPlayer().getID() < physio.getPlayer().getID()) {
					return 1;
				}
				else {
					return 0;
				}
			}
			if(train.getArrivalTime() > physio.getArrivalTime()) {
				return -1;
			}
			if(train.getArrivalTime() < physio.getArrivalTime()) {
				return 1;
			}
		}
		
		
		if(o1 instanceof Physiotherapy && o2 instanceof Massage) {
			Physiotherapy physio = (Physiotherapy) o1;
			Massage massage = (Massage) o2;
			if(Math.abs(massage.getArrivalTime() - physio.getArrivalTime()) < 0.0000000001) {
				if(massage.getPlayer().getID() > physio.getPlayer().getID()) {
					return -1;
				}
				else if(massage.getPlayer().getID() < physio.getPlayer().getID()) {
					return 1;
				}
				else {
					return 0;
				}
			}
			if(physio.getArrivalTime() > massage.getArrivalTime()) {
				return 1;
			}
			if(physio.getArrivalTime() < massage.getArrivalTime()) {
				return -1;
			}
			
			
		}
		
		if(o1 instanceof Massage && o2 instanceof Physiotherapy) {

			Physiotherapy physio = (Physiotherapy) o2;
			Massage massage = (Massage) o1;
			if(Math.abs(massage.getArrivalTime() - physio.getArrivalTime()) < 0.0000000001) {
				if(massage.getPlayer().getID() > physio.getPlayer().getID()) {
					return 1;
				}
				else if(massage.getPlayer().getID() < physio.getPlayer().getID()) {
					return -1;
				}
				else {
					return 0;
				}
			}
			
			if(physio.getArrivalTime() > massage.getArrivalTime()) {
				return -1;
			}
			if(physio.getArrivalTime() < massage.getArrivalTime()) {
				return 1;
			}
			
			
		}
		
		
		
		if(o1 instanceof Training && o2 instanceof Massage) {
			Training train = (Training) o1;
			Massage massage = (Massage) o2;
			if(Math.abs(massage.getArrivalTime() - train.getArrivalTime()) < 0.0000000001) {
				if(massage.getPlayer().getID() > train.getPlayer().getID()) {
					return -1;
				}
				else if(massage.getPlayer().getID() < train.getPlayer().getID()) {
					return 1;
				}
				else {
					return 0;
				}
			}
			if(train.getArrivalTime() > massage.getArrivalTime()) {
				return 1;
			}
			if(train.getArrivalTime() < massage.getArrivalTime()) {
				return -1;
			}
			
		}
		
		if(o1 instanceof Massage && o2 instanceof Training) {
			Training train = (Training) o2;
			Massage massage = (Massage) o1;
			if(Math.abs(massage.getArrivalTime() - train.getArrivalTime()) < 0.0000000001) {
				if(massage.getPlayer().getID() > train.getPlayer().getID()) {
					return 1;
				}
				else if(massage.getPlayer().getID() < train.getPlayer().getID()) {
					return -1;
				}
				else {
					return 0;
				}
			}
			if(train.getArrivalTime() > massage.getArrivalTime()) {
				return -1;
			}
			if(train.getArrivalTime() < massage.getArrivalTime()) {
				return 1;
			}
			
		}
		return 0;
	}

}
