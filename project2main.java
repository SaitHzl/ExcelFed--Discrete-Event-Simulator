import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Scanner;

public class project2main {

	public static void main(String[] args) throws FileNotFoundException {
		Locale.setDefault(new Locale("en","US"));
		Scanner in = new Scanner(new File(args[0]));
		PrintStream out = new PrintStream(new File(args[1]));
		Operations operation = new Operations();
		LinkedList<Player> playerList = new LinkedList<Player>();
		int nOfPlayers = in.nextInt();
		String someLine = in.nextLine();
		//Creating player objects and adding them to the playerList.
		for(int i = 0; i < nOfPlayers; i++) {;
			String playerLines = in.nextLine();
			String playerArray[] = playerLines.split(" ");
			Player newPlayer = new Player(Integer.valueOf(playerArray[0]), Integer.valueOf(playerArray[1]));
			playerList.add(newPlayer);
		
		}
		int totalArrivals = in.nextInt();
		String someLine2 = in.nextLine();
		//Creating arrival objects and adding them to the actionQueue.
		for(int j = 0; j < totalArrivals; j++) {
			String arrivalLines = in.nextLine();
			String arrivalArray[] = arrivalLines.split(" ");
			if(arrivalArray[0].equals("t")) {
				Training newTraining = new Training(Double.valueOf(arrivalArray[2]), Double.valueOf(arrivalArray[3]), playerList.get(Integer.valueOf(arrivalArray[1])));
				operation.getActionQueue().add(newTraining);
			}
			if(arrivalArray[0].equals("m")) {
				Massage newMassage = new Massage(Double.valueOf(arrivalArray[2]), Double.valueOf(arrivalArray[3]), playerList.get(Integer.valueOf(arrivalArray[1])));
				operation.getActionQueue().add(newMassage);
			}
		}
		
	
		int nOfPhysiotheraphists = in.nextInt();
		//Creating physiotherapists.
		for(int k = 0; k < nOfPhysiotheraphists; k++) {
			Double serviceTime = Double.valueOf(in.next());
			Physiotherapist newPhysio = new Physiotherapist(k, serviceTime);
			operation.getPhysiotherapists().add(newPhysio);
		}
		int nOfTrainingCoaches = in.nextInt(); // Number of coaches in the simulation.
		int nOfMasseurs = in.nextInt(); // Number of masseurs in the simulation
		operation.setnOfMasseurCoaches(nOfMasseurs);
		operation.setnOfTrainingCoaches(nOfTrainingCoaches);
		
		//Main part of the simulation. Get the top of the action queue and proceed it according to it's type.
		while(operation.getActionQueue().peek() != null) {
			if(operation.getActionQueue().peek() instanceof Training) {
				Training train = (Training) operation.getActionQueue().peek();
				operation.setCurrentTime(train.getArrivalTime());
				if(train.ifEndObject()) {
					operation.leavingTraining(train);
				}
				if(!train.ifEndObject()) {
					operation.enteringTraining(train);
				}
			}
			
			if(operation.getActionQueue().peek() instanceof Physiotherapy ) {
				Physiotherapy physio = (Physiotherapy) operation.getActionQueue().peek();
				operation.setCurrentTime(physio.getArrivalTime());
				if(physio.ifLeaving()) {
					operation.leavingPhysio(physio);
					
				}
			}
			
			if(operation.getActionQueue().peek() instanceof Massage) {
				Massage massage = (Massage) operation.getActionQueue().peek();
				operation.setCurrentTime(massage.getArrivalTime());
				if(!massage.ifLeaving()) {
					operation.enteringMassage(massage);
				}
				if(massage.ifLeaving()) {
					operation.leavingMassage(massage);
				}
			}
			operation.getActionQueue().poll();
		}
		
		//output part
		out.print(operation.getMaxLengthTraining());
		out.println();
		out.print(operation.getMaxLengthPhysio());
		out.println();
		out.print(operation.getMaxLengthMassage());
		out.println();
		
		Double averageTotalWaitingInTraining = 0.0;
		Double averageTotalWaitingInMassage = 0.0;
		Double averageTotalWaitingInPhysio = 0.0;
		Double totalTrainingTime = 0.0;
		Double totalPhysioTime = 0.0;
		Double totalMassageTime = 0.0;
		Double turnAroundTime = 0.0;
		Player mostWaitedInPhysio = playerList.get(0);
		Player leastWaitedInMassage = null;
		for(Player player2 : playerList) {
			if(player2.getnOfMassages() == 3) {
				leastWaitedInMassage = player2;
				break;
			}
		}
		int a = 0;
		for(Player player : playerList) {
			averageTotalWaitingInTraining += player.getWaitingTimeInTrainingQueue();
			
			averageTotalWaitingInPhysio += player.getWaitingTimeInPhysiotherapyQueue();
			
			averageTotalWaitingInMassage += player.getWaitingTimeInMassageQueue();
			
			totalTrainingTime += player.getTrainingTime();
			
			totalPhysioTime += player.getPhysiotherapyTime();
			
			totalMassageTime += player.getMassageTime();
			
			turnAroundTime += player.getTurnAroundTime();
			
			if(player.getWaitingTimeInPhysiotherapyQueue() > mostWaitedInPhysio.getWaitingTimeInPhysiotherapyQueue()) {
				mostWaitedInPhysio = player;
			}
			
			if(player.getnOfMassages() == 3) {
				a = 1;
				if(player.getWaitingTimeInMassageQueue() < leastWaitedInMassage.getWaitingTimeInMassageQueue()) {
					leastWaitedInMassage = player;
				}
				
			}
			
			
		}
		if(operation.getTotalValidTraining() == 0) {
			out.printf("%.3f",0.000);
		}
		else {
			averageTotalWaitingInTraining = averageTotalWaitingInTraining/operation.getTotalValidTraining();
			out.printf("%.3f", averageTotalWaitingInTraining);
			
		}
		out.println();
		
		if(operation.getTotalValidPhysio() == 0) {
			out.printf("%.3f",0.000);
		}
		else {
			averageTotalWaitingInPhysio = averageTotalWaitingInPhysio/operation.getTotalValidPhysio();
			out.printf("%.3f", averageTotalWaitingInPhysio);
		}
		out.println();
		
		if(operation.getTotalValidMassage() == 0) {
			out.printf("%.3f",0.000);
		}
		else {
			averageTotalWaitingInMassage = averageTotalWaitingInMassage/operation.getTotalValidMassage();
			out.printf("%.3f", averageTotalWaitingInMassage );
		}
		out.println();
		
		if(operation.getTotalValidTraining() == 0) {
			out.printf("%.3f",0.000);
		}
		else {
			totalTrainingTime = totalTrainingTime/operation.getTotalValidTraining();
			out.printf("%.3f", totalTrainingTime);
		}
		out.println();
		
		if(operation.getTotalValidPhysio() == 0) {
			out.printf("%.3f",0.000);
		}
		else {
			totalPhysioTime = totalPhysioTime/operation.getTotalValidTraining();
			out.printf("%.3f", totalPhysioTime);
			
		}
		out.println();
		
		if(operation.getTotalValidMassage() == 0) {
			out.printf("%.3f",0.000);
		}
		else {
			totalMassageTime = totalMassageTime/operation.getTotalValidMassage();
			out.printf("%.3f", totalMassageTime);
			
		}
		out.println();
		
		if(operation.getTotalValidTraining() == 0) {
			out.printf("%.3f",0.000);
		}
		else {
			turnAroundTime = turnAroundTime/operation.getTotalValidTraining();
			out.printf("%.3f", turnAroundTime);
		}
		out.println();
		
		out.print(mostWaitedInPhysio.getID() + " ");
		out.printf("%.3f", mostWaitedInPhysio.getWaitingTimeInPhysiotherapyQueue());
		out.println();
		
		if(leastWaitedInMassage == null) {
			out.print(-1 + " " + -1);
		}
		else {
			out.print(leastWaitedInMassage.getID() + " ");
			out.printf("%.3f", leastWaitedInMassage.getWaitingTimeInMassageQueue());
		}
		
		out.println();
		
		out.print(operation.getInvalidAttempts());
		out.println();
		
		out.print(operation.getCanceledAttempts());
		out.println();
		
		out.printf("%.3f", operation.getCurrentTime());
		
		
		
		
	}

}
