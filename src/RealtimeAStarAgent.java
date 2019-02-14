import java.util.Scanner; 


public class RealtimeAStarAgent extends AI_Agent{

	int maxNumOfExpands;
	boolean askUser = true;
	
	
	public RealtimeAStarAgent(int agentTypes, int positions, int peopleInCar) {
		super(agentTypes, positions, peopleInCar);
	}

	@Override
	public double timeForNextAction() {
		if(askUser) {
			Scanner sc = Main.scan;
			System.out.println("Please enter maximum number of expands");
			int ans = sc.nextInt();
			while(ans<0) {
				System.out.println("Maximum number of expands should be positive integer");
				ans = sc.nextInt();
			}
			maxNumOfExpands=ans;
			askUser= false;
		}

		nextStep = calculateNextStep(position, true, maxNumOfExpands);
		
		if (nextStep==-1 || nextStep==position) {
			System.out.println("no-op = 1");
			return 1;//no-op
		}
		else {
			double w = Double.POSITIVE_INFINITY;
			if(position<nextStep && Main.vertexMatrix[position][nextStep]>0) 
				w = DijkstraAlgorithm.cTime(Main.vertexMatrix[position][nextStep], peopleInCar, Main.k_parameter);
			else if(position>nextStep && Main.vertexMatrix[nextStep][position]>0) 
				w = DijkstraAlgorithm.cTime(Main.vertexMatrix[nextStep][position], peopleInCar, Main.k_parameter);
			else {
				System.out.println("Shuold not happen(RealtimeAStarAgent timeForNextAction)");
			}			
			timeForAction= w;
		}
		System.out.println("___ time for action ____");
		return timeForAction;
	}

	
	@Override
	public int doAction() {
		if (nextStep==-1) { //no-op
			System.out.println("___ do action no-op____");
			return position;
		}
		
		// take people to car if needed
		peopleInCar += Main.peopleToSave[nextStep];
		Main.peopleToSave[nextStep]=0;
				
		// put people in shelter if needed
		if (Main.shelters[nextStep]){
			score += peopleInCar;
			peopleInCar = 0;
		}
				
		//update position
		position = nextStep;
		System.out.println("___ do action ____");
		return position;
		
	}
	
}
